package com.chopdawg.josh.orion.GUI.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.components.ButtonComponent;
import com.chopdawg.josh.orion.GUI.components.ImageComponent;
import com.chopdawg.josh.orion.GUI.components.TextComponent;
import com.chopdawg.josh.orion.GUI.components.TileComponent;

/**
 * 
 * @author SK83RJOSH
 */
public class StartMenu extends Menu {
	public StartMenu() {
		setColor(new Color(0.65f, 0.65f, 0.65f));
		
		for(int x = 0; x < 20; x++)
			for(int y = 0; y < 16; y++)
				addComponent(new TileComponent(x, y));
		
		addComponent(new ImageComponent(-1, 55, "res/logo.png"));
		
		//Getting all JQuery up in here. But not quite. -Insert Wit-
		addComponent(new ButtonComponent("Start Game", -1, 205) {
						public void onClick() {
							Board.startGame();
						}
					});
		
		addComponent(new ButtonComponent("Level Editor", -1, 265) {
						public void onClick() {
							System.out.println("Start Editor!");
						}
					});
		
		addComponent(new ButtonComponent("Options", -1, 325) {
						public void onClick() {							
							Board.menuStack.pop();
							Board.menuStack.add(new OptionsMenu());
						}
					});
		
		addComponent(new ButtonComponent("Exit", -1, 385) {
						public void onClick() {
							System.exit(0);
						}
					});
		
		//Test of the Text Component
		addComponent(new TextComponent("Ver.  0.0.6b;  Dev-Build", Color.yellow, 6, Board.getHeight() - 18) {
			private boolean toggled;
			private int tick;
			
			public void update(GameContainer container) {
				super.update(container);
				
				if(tick == 30) {
					if(toggled)
						set(getX() - 2, getY() + 2);
					else
						set(getX() + 2, getY() - 2);
					
					toggled = !toggled;
				}
				
				tick++;
				tick = tick % 60;
			}
		});
	}
}
