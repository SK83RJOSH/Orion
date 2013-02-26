package com.chopdawg.josh.orion.GUI;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.GameContainer;

import com.chopdawg.josh.orion.Board;


public abstract class Component implements IRenderable {
	private Vector3f pos;
	private Dimension dim;
	private boolean active;
	
	public Component() {
		dim = new Dimension();
		pos = new Vector3f();
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
	
	public float getZ() {
		return pos.z;
	}
	
	public void setX(float x) {
		pos.setX(x);
	}
	
	public void setY(float y) {
		pos.setY(y);
	}
	
	public void setZ(float z) {
		pos.setZ(z);
	}
	
	public int getWidth() {
		return dim.getWidth();
	}
	
	public int getHeight() {
		return dim.getHeight();
	}
	
	public void setWidth(int width) {
		dim.setWidth(width);
	}
	
	public void setHeight(int height) {
		dim.setHeight(height);
	}
	
	public boolean isActive() {
		return active;
	}
}
