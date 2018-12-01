package bdk.game.entities;

import java.io.Serializable;

import bdk.game.component.level.Level;
import bdk.util.graphics.Vector2D;

public abstract class EntityLink implements Serializable {

	protected transient Level level;

	protected int tickToSpawn;
	protected Vector2D origin;
	protected String entityName;

	/**
	 * The EntityLink is used to link an entity to a level. The name of the entity
	 * are saved in this class, aswell as when it should spawn and on what
	 * origin(tile). At level start all entites are precached in a list. When its
	 * time to spawn, the EntityLink will grab a copy of the entity its linked to
	 * the level and add it to the levels entitybuffer. An EntityLink only holds
	 * information for a single entity spawn.
	 * 
	 */
	public EntityLink(int tickToSpawn, Vector2D origin, String entityName) {
		this.tickToSpawn = tickToSpawn;
		this.origin = origin;
		this.entityName = entityName;
	}

	/**
	 * Sets this links level and caches this links entity to it. Also checks if that
	 * entity should already be added to the spawn buffer (aka tickToSpawn == 0).
	 * 
	 * @param level
	 */
	public void initializeLink(Level level) {
		this.level = level;

		cacheEntityToLevel();
		checkForEntitySpawn();
	}

	/**
	 * Goes into the level and checks if the entity cache already contains the
	 * entity this is linked to. If so no entity is loaded. Otherwise add it to the
	 * cache
	 * 
	 * @param level
	 */
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
