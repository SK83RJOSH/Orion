package com.chopdawg.josh.orion.GUI.menus;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.components.ButtonComponent;

public class OptionsMenu extends Menu {
	public OptionsMenu() {				
		//Getting all JQuery up in here. But not quite. -Insert Wit-
		addComponent(new ButtonComponent("Back", -1, 385) {
						public void onClick() {
							Board.menuStack.pop();
							Board.menuStack.add(new StartMenu());
						}
					});
	}
}
