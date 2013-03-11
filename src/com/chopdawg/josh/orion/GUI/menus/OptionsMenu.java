package com.chopdawg.josh.orion.GUI.menus;

import org.newdawn.slick.Color;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.components.ButtonComponent;
import com.chopdawg.josh.orion.GUI.components.TextInputComponent;
import com.chopdawg.josh.orion.GUI.components.TileComponent;

/**
 * 
 * @author SK83RJOSH
 */
public class OptionsMenu extends Menu {
	public OptionsMenu() {	
		setColor(new Color(0.65f, 0.65f, 0.65f));
		
		for(int x = 0; x < 20; x++)
			for(int y = 0; y < 16; y++)
				addComponent(new TileComponent(x, y));
		
		//Getting all JQuery up in here. But not quite. -Insert Wit-
		addComponent(new ButtonComponent("Back", -1, 385) {
						public void onClick() {
							Board.menuStack.pop();
							Board.menuStack.add(new StartMenu());
						}
					});
		
		addComponent(new TextInputComponent("I AM ERROR", "", -1, 300));
	}
}
