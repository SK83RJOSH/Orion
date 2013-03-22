package com.chopdawg.josh.orion.GUI.components;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.chopdawg.josh.orion.GUI.Component;

/**
 * 
 * @author SK83RJOSH
 */
public class LabelComponent extends Component {
	private String content = "";
	private Component parent;
	private boolean intialized = false;
	
	public LabelComponent(String content, Component parent) {
		this.content = content + ": ";
		this.parent = parent;
	}
	
	public void render(GameContainer container, Graphics g) {		
		g.setColor(Color.black);
		g.drawString(content, getX() + 2, getY() + 2);
		
		g.setColor(Color.white);
		g.drawString(content, getX(), getY());
	}
	
	public void update(GameContainer container) {
		super.update(container);
		
		if(!intialized) {
			setSize(container.getDefaultFont().getWidth(content), container.getDefaultFont().getHeight(content));
			set(parent.getX() - getWidth(), parent.getY() + (parent.getHeight() / 2) - (getHeight() / 2));
			
			intialized = true;
		}
	}
	
	public void setValue(String content) {
		this.content = content + ": ";
		intialized = false;
	}
	
	public String getValue() {
		return content;
	}
}
