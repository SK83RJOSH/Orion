package com.chopdawg.josh.orion.GUI;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;

import com.chopdawg.josh.orion.Board;

/**
 * 
 * @author SK83RJOSH
 */
public abstract class Component implements IRenderable {
	private Vector2f pos;
	private Dimension dim;
	private boolean active;
	
	public Component() {
		dim = new Dimension();
		pos = new Vector2f();
	}
	
	public void update(GameContainer container) {
		if(Mouse.getX() >= getX() && Mouse.getX() <= getX() + getWidth() && (container.getHeight() - Mouse.getY()) >= getY() && (container.getHeight() - Mouse.getY()) <= getY() + getHeight()) {
			active = true;
		} else {
			active = false;
		}
	}
	
	public float getX() {
		return (pos.x == -1 ? (Board.getWidth() / 2) - (getWidth() / 2) : pos.x);
	}
	
	public float getY() {
		return pos.y;
	}
	
	public void set(float x, float y) {
		pos.setX(x);
		pos.setY(y);
	}
	
	public int getWidth() {
		return dim.getWidth();
	}
	
	public int getHeight() {
		return dim.getHeight();
	}
	
	public void setSize(int width, int height) {
		dim.setWidth(width);
		dim.setHeight(height);
	}
	
	public boolean isActive() {
		return active;
	}
}
