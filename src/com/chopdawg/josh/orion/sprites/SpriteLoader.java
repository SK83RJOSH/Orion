package com.chopdawg.josh.orion.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * 
 * @author SK83RJOSH
 */
public abstract class SpriteLoader {
	public static Image loadImage(String res) {
		try {
			return new Image(res);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static SpriteSheet loadSheet(String res, int width, int height) {
		try {
			if(((~width) & 1) != 1 && ((~height) & 1) != 1)
				System.out.println("Loading '" + res + "', be warned sprites are not a power of two. (" + width + "," + height + ")");
				
			return new SpriteSheet(res, width, height);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
