package objects.gameobjects;

import java.awt.Graphics;
import java.awt.Image;

import engine.math.Vector2D;
/**
 * 
 * @author DonutConsulting
 *
 */
public class Dummy extends Sprite {
	public Dummy(Vector2D position, Vector2D movementvector, int health, ObjectID ID, boolean isanimated){
		super(position,movementvector, health, ID,  isanimated);
	}

	@Override
	public void update() {
		position.vecAdd(movementvector);
		hitbox.updateHitbox();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(spritefile,(int) position.getX(),(int) position.getY(), null);
	}

	@Override
	public void animationController() {
		// TODO Auto-generated method stub
		
	}
}
