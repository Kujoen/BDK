package bdk.game.entities.sprites.actors;

import java.util.Map;

import bdk.data.FileUtil;
import bdk.data.Vector2D;
import bdk.editor.main.BdkMainWindow;
import bdk.game.component.Level;
import bdk.game.entities.Entity;
import bdk.game.entities.EntityLink;

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

	@Override
	protected void cacheEntityToLevel() {
		// Check if the level already has actorCollection
		if (!level.getActorCollectionCache().containsKey(collectionName)) {
			ActorCollection actorCollectionToCache = (ActorCollection) FileUtil
					.loadSerializedObject(ActorCollection.COLLECTION_PATH + "/" + collectionName + ".ac");
			level.getActorCollectionCache().put(collectionName, actorCollectionToCache);
		}
	}

	/**
	 * The only actor type we could be linking to are enemies, so add the new actor to the actorSpawnBuffer
	 */
	@Override
	protected void checkForEntitySpawn() {
		if(level.getLevelTick() == tickToSpawn) {
			Actor actorToSpawn =  level.getActorCollectionCache().get(collectionName).getActorCache().get(entityName);
			actorToSpawn.initializeForLevel(level);
			
			//TODO: Set x and y correctly based on origin coordinates
			
			level.getSpawnEnemyBuffer().add(actorToSpawn);
		}
		
		// Remove thyself from the entitylink list
		level.getDeleteLinkBuffer().add(this);
	}

}
