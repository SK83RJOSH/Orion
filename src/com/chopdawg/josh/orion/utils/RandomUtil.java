package com.chopdawg.josh.orion.utils;

/**
 * 
 * @author SK83RJOSH
 */
public class RandomUtil {
	public static boolean randomBoolean() {
		if(randomInt(0, 1)  == 1)
			return true;
			
		return false;
	}
	
	public static int randomInt(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
