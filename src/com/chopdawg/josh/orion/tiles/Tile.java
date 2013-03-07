package com.chopdawg.josh.orion.tiles;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.chopdawg.josh.orion.bb.IBounding;

public abstract class Tile implements IBounding {
	public final static int TILE_WIDTH = 32; 
	public final static int TILE_HEIGHT = 32;
	private Image sprite;
	private Vector3f position = new Vector3f();
	private boolean solid;
	
	public void render(GameContainer container, Graphics g) {
		g.drawImage(getSprite(), getX(), getY());
	}
	
	public void makeSolid() {
		solid = true;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void set(float x, float y, float z) {
		position.set(x, y, z);
	}
	
	public float getX() {
		return position.x * TILE_WIDTH;
	}
	
	public float getY() {
		return position.y * TILE_HEIGHT;
	}
	
	public float getZ() {
		return position.z;
	}

	public int getWidth() {
		return TILE_WIDTH;
	}

	public int getHeight() {
		return TILE_HEIGHT;
	}
	
	public boolean collides(IBounding bb) {
		if(bb.getX() >= getX() && bb.getY() >= getY() && bb.getX() + bb.getWidth() <= getX() + getWidth() && bb.getY() + bb.getHeight() <= getY() + getHeight() && bb.getZ() == getZ() && bb.isSolid() && isSolid())
			return true;
		
		return false;
	}
}
