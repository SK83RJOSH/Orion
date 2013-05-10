package com.chopdawg.josh.orion.entities;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;

import com.chopdawg.josh.orion.level.Level;
import com.chopdawg.josh.orion.sprites.GameSprites;

/**
 * 
 * @author SK83RJOSH
 */
public class Player extends Entity {
	public Body body;

	public Player(int x, int y) {
		set(x, y, 0);
		setSpriteSheet(GameSprites.PLAYER);
		
		System.out.println(y);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(x, y);
		body = Level.world.createBody(bodyDef);
		
		PolygonShape dynamicBox = new PolygonShape(); //Head
		dynamicBox.setAsBox(7f, 5.5f, new Vec2(-0.5f, -5.5f), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 0.1f;
		body.createFixture(fixtureDef);
		
		dynamicBox = new PolygonShape(); //Body
		dynamicBox.setAsBox(8f, 5.5f, new Vec2(0, 5f), 0);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 0.1f;
		body.createFixture(fixtureDef);
		
		dynamicBox = new PolygonShape(); //Left Arm
		dynamicBox.setAsBox(3.5f, 5f, new Vec2(-11.5f, 5.5f), 0);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 0.1f;
		body.createFixture(fixtureDef);
		
		dynamicBox = new PolygonShape(); //Right Arm
		dynamicBox.setAsBox(3.5f, 5f, new Vec2(11.5f, 5.5f), 0);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 0.1f;
		body.createFixture(fixtureDef);
		
		dynamicBox = new PolygonShape(); //Roller
		dynamicBox.setAsBox(10f, 3f, new Vec2(0, 13.5f), 0);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 0.1f;
		body.createFixture(fixtureDef);
	}
	
	boolean jumped, doubleJumped;
	int countdown;
	int cooldown = 20;
	float lastY = getY();
	
	public void update(GameContainer container) {
		super.update(container);
				
		if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if(!jumped || countdown == 0 && !doubleJumped) {				
				if(!jumped) {
					body.applyLinearImpulse(new Vec2(0, -150), new Vec2(body.getPosition().x, body.getPosition().y));
					jumped = true;
					countdown = cooldown;
				} else if(!doubleJumped) {
					body.applyLinearImpulse(new Vec2(0, -100), new Vec2(body.getPosition().x, body.getPosition().y));
					doubleJumped = true;				
				}				
			}
		}
		
		if(Math.round(body.getLinearVelocity().y) == 0 && lastY == getY()) {
			jumped = false;
			doubleJumped = false;
		}
		
		countdown--;
		countdown = (countdown > 0 ? countdown : 0);
		
		lastY = getY();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A) && body.getLinearVelocity().x > -70) {
			body.applyForceToCenter(new Vec2(-100, 0));
			setAnimation(1);
		} else if(Keyboard.isKeyDown(Keyboard.KEY_D) && body.getLinearVelocity().x < 70) {
			body.applyForceToCenter(new Vec2(100, 0));
			setAnimation(0);
		} else if(!(Keyboard.isKeyDown(Keyboard.KEY_D)) && !(Keyboard.isKeyDown(Keyboard.KEY_A))) {
			if(getAnimation() != 2 && getAnimation() != 3)
				setAnimation(getAnimation() + 2);
		}
		
		Vec2 position = body.getPosition();
		set(position.x, position.y, 0);
		rotation = (int) (body.getAngle() * (180 / Math.PI));
	}
}
