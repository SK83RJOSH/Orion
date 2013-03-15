package com.chopdawg.josh.orion.tiles;

import com.chopdawg.josh.orion.sprites.GameSprites;

/**
 * 
 * @author SK83RJOSH
 */
public class Solid extends Tile {
	public Solid(float x, float y, float z) {
		set(x, y, z);
		setSprite(GameSprites.TILESET.getSubImage(0, 0));
		makeSolid();
		setTileType(2);
	}
}