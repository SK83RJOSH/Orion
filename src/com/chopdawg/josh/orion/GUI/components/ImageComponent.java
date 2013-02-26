package com.chopdawg.josh.orion.GUI.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.chopdawg.josh.orion.GUI.Component;

/**
 * 
 * @author SK83RJOSH
 */
public class ImageComponent extends Component {
	private Image image;
	
	public ImageComponent(int x, int y, String res) {
		try {
			image = new Image(res);

			setX(x);
			setY(y);
			setWidth(image.getWidth());
			setHeight(image.getHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		g.drawImage(image, getX(), getY());
	}
}
