package com.chopdawg.josh.orion.GUI.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Component;
import com.chopdawg.josh.orion.GUI.IClickable;
import com.chopdawg.josh.orion.sprites.GameSprites;
import com.chopdawg.josh.orion.utils.RandomUtil;

/**
 * 
 * @author SK83RJOSH
 */
public class TileComponent extends Component implements IClickable {
	private int offsetX, offsetY; 
	private int frameX, frameY;
	private int animation, timeout;
	private boolean animating, rotated, flipped;
	Image image;
	
	public TileComponent(int x, int y) {
		offsetY = 5 - RandomUtil.randomInt(0, 1);
		
		image = GameSprites.TILESET.getSubImage(offsetX + frameX, offsetY + frameY);
		
		setSize(image.getWidth(), image.getHeight());
		set(x * getWidth(), y * getHeight());
		
		if(offsetY == 4) {
			if(RandomUtil.randomBoolean())
				flipped = true;
			
			if(RandomUtil.randomBoolean())
				rotated = true;
		}
		
		timeout = RandomUtil.randomInt(0, 5000);
	}
	
	public void update(GameContainer container) {
		super.update(container);
		
		timeout--;
		
		if(animating)
			animation++;
		
		if(isActive() && Board.mouseButtons.isDown(0) && !animating || timeout == 0)
			onClick();
		
		if(animating && frameX < 8 && animation % 5 == 0)
			frameX++;
		else if(animating && frameX == 8) {
			frameX = 0;
			animation = 0;
			animating = false;
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		image = GameSprites.TILESET.getSubImage(offsetX + frameX, offsetY + frameY);

		if(flipped) {
			image = image.getFlippedCopy(true, false);
		}
		
		if(rotated) {
			g.pushTransform();
			g.rotate(getX() + (getWidth() / 2), getY() + (getHeight() / 2), 90);
		}
		
		g.drawImage(image, getX(), getY());
	
		if(rotated) {
			g.popTransform();
		}
	}
	
	public void onClick() {
		animating = true;
		timeout = RandomUtil.randomInt(200, 5000);
	}
}
