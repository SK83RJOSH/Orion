package com.chopdawg.josh.orion.entities;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;

import com.chopdawg.josh.orion.sprites.GameSprites;

public class Player extends Entity {	
	public Player(int x, int y) {
		set(x, y, 0);
		setSpriteSheet(GameSprites.PLAYER);
	}
	
	public void update(GameContainer container) {
		super.update(container);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			setVel(getVelX(), -2, getVelZ());
		} else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			setVel(getVelX(), 2, getVelZ());
 		} else {
			setVel(getVelX(), 0, getVelZ());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			setVel(-2, getVelY(), getVelZ());
			setAnimation(1);
		} else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			setVel(2, getVelY(), getVelZ());
			setAnimation(0);
		} else {
			setVel(0, getVelY(), getVelZ());
			
			if(getAnimation() != 2 && getAnimation() != 3)
				setAnimation(getAnimation() + 2);
		}
	}
}
