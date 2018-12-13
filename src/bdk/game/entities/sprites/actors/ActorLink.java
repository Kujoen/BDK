package bdk.game.entities.sprites.actors;

import bdk.game.entities.EntityLink;
import bdk.util.graphics.Vector2D;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ActorLink extends EntityLink {
	private static final long serialVersionUID = -285441568305666272L;
	
	String collectionName;
	String actorType;

	/**
	 * 
	 * ActorLinks save the collection name, since its needed to find the actor
	 * later.
	 * 
	 * @param tickToSpawn
	 * @param origin
	 * @param entityName
	 * @param collectionName
	 */
	public ActorLink(int tickToSpawn, Vector2D origin, String entityName, String collectionName, String entityType) {
		super(tickToSpawn, origin, entityName);
		this.collectionName = collectionName;
	}

	@Override
	protected void cacheEntityToLevel() {
		
	}

	@Override
	public void checkForEntitySpawn() {
		
	}

}
