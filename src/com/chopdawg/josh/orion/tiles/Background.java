package com.chopdawg.josh.orion.tiles;

import com.chopdawg.josh.orion.sprites.GameSprites;

public class Background extends Tile {
	public Background(float x, float y, float z) {
		set(x, y, z);
		setSprite(GameSprites.TILESET.getSubImage(0, 4));
	}
}
