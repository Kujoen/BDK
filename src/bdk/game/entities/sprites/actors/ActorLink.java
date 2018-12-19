package bdk.game.entities.sprites.actors;

import java.io.Serializable;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ActorLink implements Serializable{
	private static final long serialVersionUID = -285441568305666272L;
	
	// Actor Information --------------------------------------------|
	private String collectionName;
	private String actorName;
	private String actorType;
	// Spawn information --------------------------------------------|
	private int tickToSpawn;
	// --------------------------------------------------------------|
	
	/** 
	 * @param tickToSpawn
	 * @param origin
	 * @param entityName
	 * @param collectionName
	 */
	public ActorLink(int tickToSpawn, String actorName, String collectionName, String actorType) {
		this.tickToSpawn = tickToSpawn;
		
		this.actorName = actorName;
		this.collectionName = collectionName;
		this.actorType = actorType;
	}

	protected void cacheEntityToLevel() {
		
	}

	public void checkForEntitySpawn() {
		
	}
	
	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

}
