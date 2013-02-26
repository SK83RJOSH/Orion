package com.chopdawg.josh.orion.GUI.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;

/**
 * 
 * @author SK83RJOSH
 */
public class SplashScreen extends Menu {
	private int timeout = 120;
	
	public void render(GameContainer container, Graphics g) {
		try {
			g.pushTransform();
				Image splash = new Image("res/splash.png");
				g.scale((Board.getWidth() * 1f) / splash.getWidth(), (Board.getHeight() * 1f) / splash.getHeight());
				g.drawImage(splash, 0, 0);
			g.popTransform();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer container) {
		timeout--;
		
		if(timeout == 0) {
			Board.menuStack.pop();
			Board.menuStack.add(new StartMenu());
		}
	}
}
