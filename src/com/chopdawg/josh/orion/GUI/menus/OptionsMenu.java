package com.chopdawg.josh.orion.GUI.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Menu;
import com.chopdawg.josh.orion.GUI.components.ButtonComponent;
import com.chopdawg.josh.orion.GUI.components.CheckBoxComponent;
import com.chopdawg.josh.orion.GUI.components.LabelComponent;
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
		
		//Testing basic Check Boxes, Radio Buttons, and Labels
		addComponent(new CheckBoxComponent(true, false, -1, 230) {
			public void onInitialization(GameContainer container) {
				addComponent(new LabelComponent("Checkbox #1", this));
			}
		});
		
		addComponent(new CheckBoxComponent(false, false, -1, 265) {
			public void onInitialization(GameContainer container) {
				addComponent(new LabelComponent("Checkbox #2", this));
			}
		});
		
		addComponent(new CheckBoxComponent(false, true, -1, 160) {
			public void onInitialization(GameContainer container) {
				addComponent(new LabelComponent("Radio Button #1", this));
			}
		});

		addComponent(new CheckBoxComponent(false, true, -1, 195) {
			public void onInitialization(GameContainer container) {
				addComponent(new LabelComponent("Radio Button #2", this));
			}
		});
				
		//Testing Inputs, Masking, and onChange Event
		addComponent(new TextInputComponent("I AM ERROR", "", -1, 300) {
			LabelComponent errorLabel = new LabelComponent("TYPE HERE", this);
			
			public void onValueChange() {
				errorLabel.setValue(getValue());
			}
		
			public void onInitialization(GameContainer container) {
				addComponent(errorLabel);
			}
		});
				
		addComponent(new TextInputComponent("I AM PASSWORD", "", -1, 335, true) {
			public void onInitialization(GameContainer container) {
				addComponent(new LabelComponent("Password", this));
			}
		});
	}
}
