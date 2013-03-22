package com.chopdawg.josh.orion.GUI.menus;

import org.newdawn.slick.Color;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Component;
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
	protected static LabelComponent errorLabel;

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
		Component checkboxOne = new CheckBoxComponent(false, false, -1, 265) {
			public void onValueChange() {
				System.out.println(getValue()); //Testing Value Interface
			}
		};
		Component checkboxTwo = new CheckBoxComponent(true, false, -1, 230);
		Component radioOne = new CheckBoxComponent(false, true, -1, 195);
		Component radioTwo = new CheckBoxComponent(true, true, -1, 160);
		
		addComponent(checkboxOne);
		addComponent(checkboxTwo);
		addComponent(radioOne);
		addComponent(radioTwo);

		addComponent(new LabelComponent("Checkbox #1", checkboxTwo));
		addComponent(new LabelComponent("Checkbox #2", checkboxOne));
		addComponent(new LabelComponent("Radio Button #1", radioTwo));
		addComponent(new LabelComponent("Radio Button #2", radioOne));
		
		//Testing Inputs, Masking, and onChange Event
		Component errorInput = new TextInputComponent("I AM ERROR", "", -1, 300) {
			public void onValueChange() {
				OptionsMenu.errorLabel.setValue(getValue());
			}
		};
		errorLabel = new LabelComponent("TYPE HERE", errorInput);
		
		addComponent(errorInput);
		addComponent(errorLabel);
		
		Component passwordInput = new TextInputComponent("I AM PASSWORD", "", -1, 335, true);
		addComponent(passwordInput);
		addComponent(new LabelComponent("Password", passwordInput));
	}
}
