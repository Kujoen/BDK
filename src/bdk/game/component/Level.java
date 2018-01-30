package bdk.game.component;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import bdk.game.entities.Entity;
import bdk.game.entities.EntityLink;

/**
 * 
 * @author Andreas Farley
 *
 *         The level-component of the game package stores camera settings as
 *         well as entity informations
 *
 */
public class Level extends Component {

	// --For performance/control reasons we spit every entity type into a seperate
	// buffer. Transient since we save the links, not the entities themselves.
	private transient List<Entity> enemyBuffer;
	private transient List<Entity> projectileBuffer;
	private transient List<Entity> tileBuffer;

	// --List containing links to entities.
	private List<EntityLink> actorLinkList;
	private List<EntityLink> tileLinkList;

	// --Lists for controlling spawning/deleting entities (Lifemanagement)
	private transient List<Entity> spawnEnemyBuffer;
	private transient List<Entity> deleteEnemyBuffer;

	private transient List<Entity> spawnProjectileBuffer;
	private transient List<Entity> deleteProjectileBuffer;

	private transient List<Entity> spawnTileBuffer;
	private transient List<Entity> deleteTileBuffer;

	public Level() {

	}

	/**
	 * Returns a list containing one level with no actors and empty tiles that runs
	 * endlessly
	 * 
	 * @return
	 */
	public static List<Level> getLevelListForActorPreview() {

		List<Level> levelListForPreview = new ArrayList<>();

		return null;

	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	/**
	 * Updates entities then the entity life management system.
	 */
	@Override
	public void update() {
		updateEntities();
		updateLifeManagement();
	}

	/**
	 * Updates all entities. ORDER OF UPDATING: Level->Tiles->Projectiles->Actors
	 */
	private void updateEntities() {
		updateTiles();
		updateProjectiles();
		updateActors();
	}

	private void updateTiles() {

	}

	/**
	 * Updates all projectiles in the projectileBuffer
	 */
	private void updateProjectiles() {

	}

	/**
	 * Updates all actor
	 */
	private void updateActors() {

	}

	/**
	 * Updates delete/spawn requests. ORDER OF UPDATING: Delete-Spawn. ORDER OF
	 * ENTITIES: Tiles->Projectiles->Actors
	 */
	private void updateLifeManagement() {

	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|
	@Override
	public void render(Graphics2D g) {
		renderTiles(g);
		renderProjectiles(g);
		renderActors(g);
	}

	private void renderTiles(Graphics2D g) {

	}

	private void renderProjectiles(Graphics2D g) {

	}

	private void renderActors(Graphics2D g) {

	}

}
