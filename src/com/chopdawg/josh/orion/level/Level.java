package com.chopdawg.josh.orion.level;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.menus.PauseMenu;
import com.chopdawg.josh.orion.entities.Entity;
import com.chopdawg.josh.orion.entities.Player;
import com.chopdawg.josh.orion.utils.RenderedList;

public class Level {
	public boolean paused, loading, editing;
	public double progress;
	public int width, height;
	public String level;
	RenderedList<Entity> entities = new RenderedList<Entity>();
	
	public Level(String level) {
		this.level = level;
		entities.add(new Player(250, 250));
	}
	
	public void render(GameContainer container, Graphics g) {
		for(Entity e : entities)
			e.render(container, g);
	}
	
	private boolean toggled;
	
	public void update(GameContainer container) {
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
	    	//int ents, entsProcessed, sets, setsProcessed, phys, physProcessed;

	    	Builder parser = new Builder();
			Document d = parser.build(Board.class.getResourceAsStream(level));
			Element e = d.getRootElement();
				
			width = parseInt(e, "width");
			height = parseInt(e, "height");
			//ents = parseInt(e, "ents");
			//sets = parseInt(e, "sets");
			//phys = parseInt(e, "phys");
			
			//tiles = new Entity[width * height];
			
			parseElements(e);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	}
	
	private void parseElements(Element element) {
		switch(element.getLocalName()) {
			case "block":
				int x = parseInt(element, "x");
				int y = parseInt(element, "y");
				int width = parseInt(element, "width");
				int height = parseInt(element, "height");
				//Rectangle ent = null;
				
				if(width > 1 && height > 1) {
					for(int entX = x; entX < x + width; entX++) {
						for(int entY = y; entY < y + height; entY++) {
							//ent = new Rectangle(new Vector3f(16f * entX, 16f * entY, 0f), 16f , 16f);
							
//							if(element.getAttribute("type").getValue().equals("test")) {							
//								ent.offset = 0;	
//							}
//							else
//								ent.offset = 2;
//
//							ent.fixed = true;
//							tiles[entX + entY * this.width()] = ent;
						}
					}
				}
				else {
					//ent = new Rectangle(new Vector3f(16f * parseInt(element, "x"), 16f * parseInt(element, "y"), 0f), 16f, 16f);
				
//					ent.kinematic = parseBoolean(element, "kinematic");
//					ent.fixed = true;
//					tiles[x + y * width] = ent;
				}
			break;
		}
	}
		
	private int parseInt(Element element, String attribute) {
		try {
			return Integer.parseInt(element.getAttribute(attribute).getValue());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0x00;
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
	
	public void togglePaused() {
		paused = !paused;
		
		if(paused)
			Board.menuStack.add(new PauseMenu());
		else
			Board.menuStack.pop();
	}
	
	public void ready() {
		
	}
}
