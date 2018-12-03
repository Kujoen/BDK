package bdk.game.entities;

import java.io.Serializable;

import bdk.game.component.level.Level;
import bdk.util.graphics.Vector2D;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class EntityLink implements Serializable {
	private static final long serialVersionUID = -1180791765603520973L;

	protected transient Level level;

	protected int tickToSpawn;
	protected Vector2D origin;
	protected String entityName;

	public EntityLink(int tickToSpawn, Vector2D origin, String entityName) {
		this.tickToSpawn = tickToSpawn;
		this.origin = origin;
		this.entityName = entityName;
	}

	public void initializeLink(Level level) {
		this.level = level;

		cacheEntityToLevel();
		checkForEntitySpawn();
	}

	protected abstract void cacheEntityToLevel();

	public abstract void checkForEntitySpawn();

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public int getTickToSpawn() {
		return tickToSpawn;
	}

	public void setTickToSpawn(int tickToSpawn) {
		this.tickToSpawn = tickToSpawn;
	}

	public Vector2D getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2D origin) {
		this.origin = origin;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
