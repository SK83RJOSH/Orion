package com.chopdawg.josh.orion.GUI.components;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.chopdawg.josh.orion.Board;
import com.chopdawg.josh.orion.GUI.Component;

public class TextInputComponent extends Component {
	private String placeholder = "", content = "";
	private boolean isSelected, password;
	private int tick;
	
	public TextInputComponent(String placeholder, String content, int x, int y) {
		this.placeholder = placeholder;
		this.content = content;
		set(x, y);
		setSize(200, 22);
	}
	
	public TextInputComponent(String placeholder, String content, int x, int y, boolean password) {
		this.placeholder = placeholder;
		this.content = content;
		this.password = password;
		set(x, y);
		setSize(200, 22);
	}
	
	public void render(GameContainer container, Graphics g) {
		String maskedContent = "";
		
		for(int index = 0; index < content.length(); index++)
			maskedContent += "*";
			
		
		g.setFont(container.getDefaultFont());
		
		g.setColor(Color.white);
		g.draw(new Rectangle(getX() - 1, getY(), getWidth() + 1, getHeight() + 1));
		if(isSelected)
			g.draw(new Rectangle(getX() - 2, getY() - 1, getWidth() + 3, getHeight() + 3));
		
		g.setColor((isActive() && !isSelected ? new Color(0f, 0f, 0f, 0.70f) : (isSelected ? new Color(0f, 0f, 0f, 0.75f) : new Color(0f, 0f, 0f, 0.65f))));
		g.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));
		
		g.setClip((int) getX() + 5, (int) getY(), getWidth(), getHeight());
			g.setColor((isSelected && content.length() > 0 ? Color.white : Color.gray));
			g.drawString((content.length() > 0 ? (password ? maskedContent : content) : placeholder), getX() + 6 - (container.getDefaultFont().getWidth((password ? maskedContent : content)) > getWidth() - 12 ? 12 + (container.getDefaultFont().getWidth((password ? maskedContent : content)) - getWidth()) : 0), getY() + 6);
			
			if(isSelected && tick > 20 && tick < 40) {
				g.setColor(Color.white);
				g.drawString("|", getX() + 4 + container.getDefaultFont().getWidth((password ? maskedContent : content)) - (container.getDefaultFont().getWidth((password ? maskedContent : content)) > getWidth() - 12 ? 12 + (container.getDefaultFont().getWidth((password ? maskedContent : content)) - getWidth()) : 0),  getY() + 6);
			}
		g.setClip(0, 0, container.getWidth(), container.getHeight());
	}
	
	char lastchar;
	
	public void update(GameContainer container) {
		super.update(container);
		
		if(isActive() && Board.mouseButtons.wasReleased(0))
			isSelected = !isSelected;
		else if(!isActive() && Board.mouseButtons.wasReleased(0))
			isSelected = false;
		
		tick++;
		tick = tick % 60;
		
		if(Keyboard.getEventKeyState() && lastchar != Keyboard.getEventCharacter() && isSelected || Keyboard.isRepeatEvent() && isSelected) {
			if(Keyboard.getEventCharacter() != 0 && Keyboard.getEventKey() != Keyboard.KEY_BACK && Keyboard.getEventKey() != Keyboard.KEY_RETURN) {
				content += (Keyboard.getEventCharacter());
			} else {
				switch(Keyboard.getEventKey()) {
					case Keyboard.KEY_BACK:
						if(content.length() > 0)
							content = content.substring(0, content.length() - 1);
						break;
					case Keyboard.KEY_RETURN:
						isSelected = false;
						break;
				}	
			}

			lastchar = Keyboard.getEventCharacter();
		} else if(!Keyboard.getEventKeyState()) {
			lastchar = 9999;
		}
	}
	
	public String getContent() {
		return content;
	}
}
