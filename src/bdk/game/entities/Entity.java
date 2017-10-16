package bdk.game.entities;

import java.io.Serializable;

/**
 * An entity is an instance of an entity-object which is on the level and being updated.
 * Currently just using rendered Entities.
 * @author Kuj
 *
 */
public abstract class Entity implements Serializable{

	protected double x, y;
	
	public abstract void update();
	
	public Entity(double x, double y){
		this.x = x;
		this.y = y;
	}
}
