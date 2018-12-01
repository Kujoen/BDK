package bdk.game.entities.sprites.actors;

import java.util.Map;

import bdk.editor.main.BdkMainWindow;
import bdk.game.component.level.Level;
import bdk.game.entities.Entity;
import bdk.game.entities.EntityLink;
import bdk.util.BdkFileManager;
import bdk.util.graphics.Vector2D;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ActorLink extends EntityLink {

	String collectionName;
	String actorType;

	/**
	 * 
	 * The ActorLink saves the collection name, since its needed to find the actor
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

	/**
	 * Adds the actor to the levels actorCache. The key has the format
	 * collectionName$$entityName
	 */
	@Override
	protected void cacheEntityToLevel() {
		// Check if the level already has this actor
		if (level.getActorCache().containsKey(collectionName + "$$" + entityName)) {
			// Level already has this actor cached
		} else {
			// Load the actor into the actorCache
			ActorCollection actorCollectionToCache = (ActorCollection) BdkFileManager
					.loadSerializedObject(ActorCollection.COLLECTION_PATH + "/" + collectionName + ".ac");

			level.getActorCache().put(collectionName + "$$" + entityName, actorCollectionToCache.getActor(entityName));
		}
	}

	/**
	 * The only actor type we could be linking to are enemies, so add the new actor
	 * to the actorSpawnBuffer
	 */
	@Override
	public void checkForEntitySpawn() {
		// Is it time to spawn the entity ?
		if (level.getLevelTick() == tickToSpawn) {
			// TODO: Create a new instance of the actor by grabbing the cached actor from
			// the levels actorCache and copying it. Initialize the new actor with
			// initializeForLevel() and then put it in the request spawn buffer.
		}
	}

}
