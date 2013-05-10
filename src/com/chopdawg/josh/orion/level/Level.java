package com.chopdawg.josh.orion.level;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.menus.PauseMenu;
import com.chopdawg.josh.orion.entities.Entity;
import com.chopdawg.josh.orion.entities.Player;
import com.chopdawg.josh.orion.tiles.Tile;
import com.chopdawg.josh.orion.utils.RenderedList;

/**
 * 
 * @author SK83RJOSH
 */
public class Level {
	public boolean paused, loading, editing;
	public double progress;
	private int width, height;
	public static World world = new World(new Vec2(0f, 120f));
	public String level;
	public Player player;
	private int entitiesTotal, entitiesProcessed, tilesTotal, tilesProcessed;
	RenderedList<Entity> entities = new RenderedList<Entity>();
	public Tile[] tiles;

	public Level(String level) {
		this.level = level;
		load(level);
	}

	float nextZoom = 2f;
	float currentZoom = 2f;

	public void render(GameContainer container, Graphics g) {		
		if(player != null) {
			nextZoom = 2f - ((Math.abs(((Player) player).body.getLinearVelocity().x / 25f) + Math.abs(((Player) player).body.getLinearVelocity().y / 25f)));
			nextZoom = (nextZoom < 1f ? 1f : nextZoom);
			nextZoom = Math.round(nextZoom * 100f) / 100f;  

			currentZoom = Math.round(currentZoom * 100f) / 100f;

			if(currentZoom != nextZoom) {
				if(currentZoom > nextZoom)
					currentZoom -= 0.02f;
				else
					currentZoom += 0.03f;				
			}

			g.pushTransform();
				g.scale(currentZoom, currentZoom);
				g.translate(-player.getX() + (((Board.getWidth() / 2) - (16 * currentZoom)) / currentZoom), -player.getY() + (((Board.getHeight() / 2) - (16 * currentZoom)) / currentZoom));
		}

		if(tiles != null)
			for(Tile t : tiles)
				if(t != null)
					t.render(container, g);

		for(Entity e : entities)
			e.render(container, g);
				
		if(player != null) {
			g.popTransform();
		}
		
		if(player != null) {
			nextZoom = 2f - ((Math.abs(((Player) player).body.getLinearVelocity().x / 25f) + Math.abs(((Player) player).body.getLinearVelocity().y / 25f)));
			nextZoom = (nextZoom < 1f ? 1f : nextZoom);
			nextZoom = Math.round(nextZoom * 100f) / 100f;  

			currentZoom = Math.round(currentZoom * 100f) / 100f;

			if(currentZoom != nextZoom) {
				if(currentZoom > nextZoom)
					currentZoom -= 0.02f;
				else
					currentZoom += 0.03f;				
			}

			g.pushTransform();
				g.scale(currentZoom, currentZoom);
				g.translate(-256 - 48 -player.getX() + (((Board.getWidth() / 2) - (16 * currentZoom)) / currentZoom), -16 + (8.5f) * 32 + -player.getY() + (((Board.getHeight() / 2) - (16 * currentZoom)) / currentZoom));
				g.rotate(0, 0, 180);
		}
		
		//world.drawDebugData();
		
		if(player != null) {
			g.popTransform();
		}
	}

	private boolean toggled;

	boolean ready = false;
	
	public void update(GameContainer container, int delta) {		
		world.step(1.0f / container.getFPS(), 6, 2);
		
		if(!paused) {
			for(Entity e : entities)
				e.update(container);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !toggled) {
			togglePaused();
			toggled = true;
		} else if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			toggled = false;
		}	
	}

	public void load(String level) {
	    try {	    	
	    	Builder parser = new Builder();
			Document d = parser.build(Level.class.getResourceAsStream(level));
			Element e = d.getRootElement();

			width = parseInt(e, "width");
			height = parseInt(e, "height");
			entitiesTotal = parseInt(e, "entities");
			tilesTotal = parseInt(e, "tiles");

			tiles = new Tile[getWidth() * getHeight()];

			parseElements(e);
			ready();
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	}

	private void parseElements(Element element) {
		switch(element.getLocalName()) {
			case "tile":				
				int x = parseInt(element, "x");
				int y = parseInt(element, "y");
				int z = parseInt(element, "z");
				int width = parseInt(element, "width");
				int height = parseInt(element, "height");

				if(width > 1 || height > 1) {
					for(int entX = x; entX < x + width; entX++) {
						for(int entY = y; entY < y + height; entY++) {
							tiles[entX + entY * getWidth()] = parseTile(element, entX, entY, z);
						}
					}
				}
				else {
					tiles[x * y * this.width] = parseTile(element, x, y, z);
				}

				tilesProcessed++;
				break;
			case "entity":				
				switch(element.getAttribute("type").getValue()) {
					case "Player":
						if(player == null) {
							x = parseInt(element, "x") * Tile.TILE_WIDTH;
							y = parseInt(element, "y") * Tile.TILE_HEIGHT;
							player = new Player(x, y); 
							entities.add(player);
						} else {
							System.out.println("There was more than one player present on the Map!");
						}
						break;
				}

				entitiesProcessed++;
				break;
		}

		for (int i = 0; i < element.getChildCount(); i++) {
			Node node = element.getChild(i);
			if (node instanceof Element) {
				parseElements((Element)node);
			}
		}
	}

	private int parseInt(Element element, String attribute) {
		try {
			return Integer.parseInt(element.getAttribute(attribute).getValue());
		} catch(Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

//	private boolean parseBoolean(Element element, String attribute) {
//		try {
//			if(element.getAttribute(attribute).getValue() == "true")
//				return true;
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
	
	private Tile parseTile(Element element, int x, int y, int z) {
		String tileType = element.getAttributeValue("type");
		boolean validTileType = false;
		
		for(int currentTileType = 0; currentTileType < Tile.TILE_TYPES.length; currentTileType++)
			if(Tile.TILE_TYPES[currentTileType].equals(tileType))
				validTileType = true;
		
		if(validTileType) {
			Class<?> reflection;
			
			try {
				reflection = Class.forName("com.chopdawg.josh.orion.tiles." + tileType);
				Constructor<?> constructor = reflection.getConstructor(float.class, float.class, float.class);
				Tile tile = (Tile) constructor.newInstance(x, y, z);
				
				return tile;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public void togglePaused() {
		paused = !paused;

		if(paused)
			Board.menuStack.add(new PauseMenu());
		else
			Board.menuStack.pop();
	}

	public double getProgress() {
		return (entitiesProcessed + tilesProcessed) / (entitiesTotal + tilesTotal);
	}

	public void ready() {
		//Overridden
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}