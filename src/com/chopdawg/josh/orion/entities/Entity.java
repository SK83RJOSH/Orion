package com.chopdawg.josh.orion.entities;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.chopdawg.josh.orion.bb.IBounding;

/**
 * 
 * @author SK83RJOSH
 */
public abstract class Entity implements IBounding {
	private Vector3f position = new Vector3f();
	private Dimension dimensions = new Dimension();
	private Image sprite;
	private SpriteSheet spriteSheet;
	private int animation, frame, tick;
	protected int rotation;
	private boolean scaled, animated, paused, solid;

	public void render(GameContainer container, Graphics g) {
		g.pushTransform();
			g.rotate(getX() + (getWidth() / 2), getY() + (getHeight() / 2), rotation);
			if(scaled) {
				g.scale(getZ(), getZ());
				g.drawImage(sprite, getX() / getZ(), getY() / getZ());
			} else {
				g.drawImage(sprite, getX(), getY());
			}
		g.popTransform();
	}
	
	public void update(GameContainer container) {
		if(animated && !paused) {
			if(tick % 15 == 14) {
				frame = (frame + 1) % 4;
				tick = 0;
				setSprite(getSpriteSheet().getSubImage(frame, animation));
			} else {
				tick++;
			}
		}
	}

	public void makeSolid() {
		solid = true;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public void setSize(int width, int height) {
		dimensions.setSize(width, height);
	}
	
	public int getWidth() {
		return dimensions.getWidth();
	}
	
	public int getHeight() {
		return dimensions.getHeight();
	}
	
	public void set(float x, float y, float z) {
		position.set(x, y, z);
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getZ() {
		return position.z;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
		setSize(sprite.getWidth(), sprite.getHeight());
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
		sprite = spriteSheet.getSubImage(0, 0);
		animated = true;
		setSize(sprite.getWidth(), sprite.getHeight());
	}
	
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}
	
	public void setAnimation(int animation) {
		this.animation = animation;
		setSprite(getSpriteSheet().getSubImage(frame, animation));
	}
	
	public int getAnimation() {
		return animation;
	}
	
	public void pauseAnimation() {
		paused = true;
	}
	
	public void unpausedAnimation() {
		paused = false;
	}
	
	public void setScaled(boolean scaled) {
		this.scaled = scaled;
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public int getRotation() {
		return rotation;
	}
}
