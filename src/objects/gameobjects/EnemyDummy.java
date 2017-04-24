package objects.gameobjects;

import java.awt.Graphics;
import java.awt.Image;

import engine.math.Vector2D;
/**
 * 
 * @author DonutConsulting
 *
 */
public class EnemyDummy extends Enemy{
	
	
	
	private Image EnemyDummyImage;
	
	public EnemyDummy(Vector2D position, Vector2D movementvector, int health, ObjectID ID, boolean isanimated){
		super(position,movementvector, health, ID,  isanimated);
	}
	
	
	
}
