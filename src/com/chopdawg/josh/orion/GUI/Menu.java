package com.chopdawg.josh.orion.GUI;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.chopdawg.josh.orion.Board;


public abstract class Menu implements IRenderable  {
	protected ArrayList<Component> components = new ArrayList<Component>();
	private Color color = new Color(255, 0, 255);
	
	public void render(GameContainer container, Graphics g) {
		g.setColor(getColor());
		g.fill(new Rectangle(0, 0, Board.getWidth(), Board.getHeight()));
		
		for(Component c : components)
			c.render(container, g);
	}
	
	public void update(GameContainer container) {
		for(Component c : components)
			c.update(container);
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}
	
	public void destroy() {
		components.clear();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
