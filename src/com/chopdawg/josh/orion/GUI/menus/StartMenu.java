package com.chopdawg.josh.orion.GUI.menus;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.components.ButtonComponent;
import com.chopdawg.josh.orion.GUI.components.ImageComponent;

public class StartMenu extends Menu {	
	public StartMenu() {		
		addComponent(new ImageComponent(-1, 55, "res/logo.png"));
		
		//Getting all JQuery up in here. But not quite. -Insert Wit-
		addComponent(new ButtonComponent("Start Game", -1, 205) {
						public void onClick() {
							System.out.println("Start Game!");
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
	}
}
