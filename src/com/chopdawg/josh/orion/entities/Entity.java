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
	private Vector3f position = new Vector3f(), velocity = new Vector3f();
	private Dimension dimensions = new Dimension();
	private Image sprite;
	private SpriteSheet spriteSheet;
	private int animation, frame, tick, rotation;
	private boolean scaled, animated, paused, solid;

	public void render(GameContainer container, Graphics g) {
		g.pushTransform();
			g.rotate(getX(), getY(), rotation);
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
		
		set(getX() + getVelX(), getY() + getVelY(), getZ() + getVelZ());
		
//		if((Mouse.getX() - (Board.getWidth() / 2) + getX() + getWidth()) >= getX() && (Mouse.getX() - (Board.getWidth() / 2) + getX() + getWidth()) <= getX() + 2 * getWidth())
//			System.out.println("ASD");
//		else
//			System.out.println("Mouse: " + (Mouse.getX() - (Board.getWidth() / 2) + getX() + getWidth()) + " & " + Mouse.getY() + " Me: " + getX() + " & " + getY());
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
	
	public void setVel(float x, float y, float z) {
		velocity.set(x, y, z);
	}
	
	public float getVelX() {
		return velocity.x;
	}
	
	public float getVelY() {
		return velocity.y;
	}
	
	public float getVelZ() {
		return velocity.z;
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
	
	public boolean collides(IBounding bb) {
		if(bb.getX() >= getX() && bb.getY() >= getY() && bb.getX() + bb.getWidth() <= getX() + getWidth() && bb.getY() + bb.getHeight() <= getY() + getHeight() && bb.getZ() == getZ() && bb.isSolid() && isSolid())
			return true;
		
		return false;
	}
}
