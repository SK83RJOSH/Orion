package com.chopdawg.josh.orion.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.sprites.GameSprites;


public class TileComponent extends Component implements IClickable {
	private int offsetX, offsetY; 
	private int frameX, frameY;
	private int animation, timeout;
	private boolean animating;
	
	public TileComponent(int x, int y) {
		setX(x * 32);
		setY(y * 32);
		setWidth(32);
		setHeight(32);
		
		offsetY = 5 - (int) (Math.random() * 2);
		timeout = (int) (Math.random() * ((5000) + 1));
	}
	
	public void update(GameContainer container) {
		super.update(container);
		
		timeout--;
		
		if(animating)
			animation++;
		
		if(isActive() && Board.mouseButtons.isDown(0) && !animating || timeout == 0) {
			onClick();
		}
		
		if(animating && frameX < 8 && animation % 5 == 0)
			frameX++;
		else if(animating && frameX == 8) {
			frameX = 0;
			animation = 0;
			animating = false;
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		g.drawImage(GameSprites.TILESET.getSubImage(offsetX + frameX, offsetY + frameY), getX(), getY());
	}
	
	public void onClick() {
		animating = true;
		timeout = 200 + (int)(Math.random() * ((5000 - 200) + 1));
	}
}
