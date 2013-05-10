package com.chopdawg.josh.orion.tiles;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import com.chopdawg.josh.orion.level.Level;
import com.chopdawg.josh.orion.sprites.GameSprites;

/**
 * 
 * @author SK83RJOSH
 */
public class Solid extends Tile {
	public Body body;

	public Solid(float x, float y, float z) {
		set(x, y, z);
		setSprite(GameSprites.TILESET.getSubImage(0, 0));
		makeSolid();
		setTileType(2);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.STATIC;
		bodyDef.position.set(x * TILE_WIDTH, y * TILE_WIDTH);
		body = Level.world.createBody(bodyDef);
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(TILE_WIDTH / 2, TILE_WIDTH / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 1f;
		body.createFixture(fixtureDef);
	}
}
