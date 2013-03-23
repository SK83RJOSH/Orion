package com.chopdawg.josh.orion.GUI.components;

import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.chopdawg.josh.orion.GUI.Component;
import com.chopdawg.josh.orion.GUI.IValuedString;

/**
 * 
 * @author SK83RJOSH
 */
public class TextComponent extends Component implements IValuedString {
	private String content = "";
	private Color textColor = Color.white;
	
	public TextComponent(String content, Color textColor, int x, int y) {
		this.content = content;
		this.textColor = textColor;
		set(x, y);
	}
	
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.black);
		g.drawString(content, getX() + 2, getY() + 2);
		
		g.setColor(textColor);
		g.drawString(content, getX(), getY());
	}

	public void onInitialization(GameContainer container) {
		setSize(container.getDefaultFont().getWidth(content), container.getDefaultFont().getHeight(content));
	}

	public void onValueChange() {
		System.out.println("Default Action.");
	}

	public void setValue(String content) {
		this.content = content;
		onValueChange();
	}

	public String getValue() {
		return content;
	}
}
