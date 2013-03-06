package com.chopdawg.josh.orion.GUI.menus;

import org.newdawn.slick.Color;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.components.ButtonComponent;
import com.chopdawg.josh.orion.GUI.components.ImageComponent;

/**
 * 
 * @author SK83RJOSH
 */
public class PauseMenu extends Menu {
	public PauseMenu() {
		setColor(new Color(0f, 0f, 0f, 0.65f));
		
		addComponent(new ImageComponent(-1, 55, "res/logo.png"));
		
		//Getting all JQuery up in here. But not quite. -Insert Wit-
		addComponent(new ButtonComponent("Resume Game", -1, 205) {
						public void onClick() {
							Board.level.togglePaused();
						}
					});
		
		addComponent(new ButtonComponent("Main Menu", -1, 265) {
						public void onClick() {
							Board.exitGame();
						}
					});
		
		addComponent(new ButtonComponent("Exit Game", -1, 325) {
						public void onClick() {							
							System.exit(0);
						}
					});
	}
}
