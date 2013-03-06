package com.chopdawg.josh.orion;

import java.awt.Font;
import java.io.InputStream;
import java.util.Stack;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.menus.SplashScreen;
import com.chopdawg.josh.orion.GUI.menus.StartMenu;
import com.chopdawg.josh.orion.level.Level;

/**
 * 
 * @author SK83RJOSH
 */
public class Board extends BasicGame {
	private static int STARTING_WIDTH = 640;
	private static int STARTING_HEIGHT = 512;
	private static ScalableGame game;
	
	public static Stack<Menu> menuStack = new Stack<Menu>();
	public static MouseButtons mouseButtons = new MouseButtons();
	public static Level level;
	
	public Board(String title) {
		super(title);
	}
	
    public static void main(String[] args) throws Exception {
        game = new ScalableGame(new Board("Project Orion"), STARTING_WIDTH, STARTING_HEIGHT) {
        	protected void renderOverlay(GameContainer container, Graphics g) {        		
        		if(!menuStack.isEmpty())
        			menuStack.peek().render(container, g);
        	}
        };
        
        AppGameContainer container = new AppGameContainer(game);
        container.setMultiSample(0);
        container.setVSync(true);
        container.start();
    }

	public void render(GameContainer container, Graphics g) throws SlickException {
        if(level != null)
        	level.render(container, g);
	}

	public void init(GameContainer container) throws SlickException {
    	menuStack.add(new SplashScreen());
    	
    	try {
    		InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Orion.ttf");    		
    		container.setDefaultFont(new TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(16f), false));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
	}

	public void update(GameContainer container, int delta) throws SlickException {
		for(int button = 0; button < 4; button++) {
			if(Mouse.isButtonDown(button)) {
		        mouseButtons.setNextState(button, true);
			} else {
		        mouseButtons.setNextState(button, false);
			}
		}
		
		if(!menuStack.isEmpty())
			menuStack.peek().update(container);
		
        mouseButtons.update();
	
        if(level != null)
        	level.update(container);
	}
	
	public static int getWidth() {
		return (int) (STARTING_WIDTH * game.getScaleX());
	}
	
	public static int getHeight() {
		return (int) (STARTING_HEIGHT * game.getScaleY());
	}
	
	public static void startGame() {
		menuStack.pop();
		Board.level = new Level("");
	}
	
	public static void exitGame() {
		menuStack.pop();
		Board.level = null;
		menuStack.add(new StartMenu());
	}
}
