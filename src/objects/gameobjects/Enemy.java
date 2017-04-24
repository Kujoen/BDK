package objects.gameobjects;

import java.awt.Graphics;
import java.awt.Image;

import engine.math.Vector2D;
/**
 * 
 * @author DonutConsulting
 *
 */
public class Enemy extends Sprite {
	
		// IMAGE------------------------------------------------------|
		private Image EnemyImage;
		// -----------------------------------------------------------|

	public Enemy(Vector2D position, Vector2D movementvector, int health, ObjectID ID,  boolean isanimated) {
		super(position,movementvector, health, ID,  isanimated);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {


	}

	@Override
	public void animationController() {
		// TODO Auto-generated method stub
		
	}

}
