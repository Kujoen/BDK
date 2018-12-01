package bdk.game.component.level;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bdk.editor.main.BdkMainWindow;
import bdk.game.component.Component;
import bdk.game.entities.Entity;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorCollection;
import bdk.game.entities.sprites.actors.ActorLink;

/**
 * The level-component of the game package stores camera settings as well as
 * entity informations
 * 
 * @author Andreas Farley
 */
public class Level extends Component {
	
	// -------------------------------------------------------------------------------|
	
	private transient int levelTick;
	private int scrollSpeed;
	
	// -------------------------------------------------------------------------------|
	// BUFFERS
	// -------------------------------------------------------------------------------|
	
	// -------------------------------------------------------------------------------|
	// LEVEL INFORMATION
	// -------------------------------------------------------------------------------|
	private Grid grid;
	
	
	// -----------------------------------------------------------------------------------|
	// Runtime level information


	// For now scrollSpeed is static, perhaps it should be modifiable in the future.


	// Entity information storage
	// --------------------------------------------------------|

	// --The grid contains all tiles.


	// --List containing links to actors.
	private List<ActorLink> actorLinkList;

	// --Caches for the actors. We are caching each actor individually. We do not
	// need a tile cache since the tiles are saved directly in the grid.
	private transient Map<String, Actor> actorCache;

	// Entity runtime buffers
	// -------------------------------------------------------------|

	// --For performance/control reasons we spit every entity type into a seperate
	// buffer. Transient since we save the links, not the buffers themselves.
	private transient List<Entity> enemyBuffer;
	private transient List<Entity> projectileBuffer;

	// --One tilebuffer for each area (Can increase performance, for example only
	// render non-center-tiles when they are updated, reduces rendering cost). Only
	// dynamic tiles should be deleted/spawned, thats why we only have one
	// spawn/delete buffer for tiles (try to implement it that way).
	private transient List<Entity> staticTileBuffer;
	private transient List<Entity> dynamicTileBuffer;

	// --Lists for controlling spawning/deleting entities (Lifemanagement)
	private transient List<Entity> spawnEnemyBuffer;
	private transient List<Entity> deleteEnemyBuffer;

	private transient List<Entity> spawnProjectileBuffer;
	private transient List<Entity> deleteProjectileBuffer;

	private transient List<Entity> spawnTileBuffer;
	private transient List<Entity> deleteTileBuffer;

	// --Links can also be deleted, since after they add their entity they should no
	// longer be called
	private transient List<ActorLink> deleteLinkBuffer;

	// -----------------------------------------------------------------------------------|

	public Level(String name) {
		super(name);

		this.grid = new Grid();
		this.actorLinkList = new ArrayList<ActorLink>();
	}

	/**
	 * Returns a list containing one level with no actors and empty tiles that runs
	 * endlessly
	 * 
	 * @return
	 */
	public static List<Level> getLevelListForActorPreview() {
		// TODO: Implement
		return null;
	}

	/**
	 * This Method first goes through all EntityLinks. First it grabs all the
	 * imagepaths and passes them to BdkImageData, which loads each image into a
	 * buffer that throws away duplicates. Therefore 10 identical entities don't
	 * make us load the same image 10 times. Then it does the same for every
	 * grid-cell in the grid. Then it proceeds to add all actors/tiles with
	 * tickToSpawn 0 to the buffers.
	 */
	public void intialize() {

		this.levelTick = 0;

		// --Initialize cache
		this.actorCache = new HashMap<String, Actor>();

		// --Initialize the buffers
		this.enemyBuffer = new ArrayList<Entity>();
		this.projectileBuffer = new ArrayList<Entity>();

		this.dynamicTileBuffer = new ArrayList<Entity>();
		this.staticTileBuffer = new ArrayList<Entity>();

		this.spawnEnemyBuffer = new ArrayList<Entity>();
		this.deleteEnemyBuffer = new ArrayList<Entity>();

		this.spawnProjectileBuffer = new ArrayList<Entity>();
		this.deleteProjectileBuffer = new ArrayList<Entity>();

		this.spawnTileBuffer = new ArrayList<Entity>();
		this.deleteTileBuffer = new ArrayList<Entity>();

		this.deleteLinkBuffer = new ArrayList<ActorLink>();

		// --Cache all the actors
		actorLinkList.stream().forEach(acLink -> acLink.initializeLink(this));

	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	/**
	 * Updates entities then the entity life management system.
	 */
	@Override
	public void update() {
		updateLevel();
		updateEntities();
		updateLifeManagement();

		// Increase the levelTick
		levelTick++;
	}

	/**
	 * Checks if there are new tiles/actors to spawn. For this it iterates through
	 * the entitylinklist and updates the center section of the grid. The center
	 * section can either return null, indicating its not time to spawn more tiles
	 * yet, or it returns a list of tiles.
	 */
	private void updateLevel() {
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
	 * Updates all actors
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
	}

	private void renderTiles(Graphics2D g) {

	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public List<ActorLink> getActorLinkList() {
		return actorLinkList;
	}

	public void setActorLinkList(List<ActorLink> actorLinkList) {
		this.actorLinkList = actorLinkList;
	}

	public List<Entity> getEnemyBuffer() {
		return enemyBuffer;
	}

	public void setEnemyBuffer(List<Entity> enemyBuffer) {
		this.enemyBuffer = enemyBuffer;
	}

	public List<Entity> getProjectileBuffer() {
		return projectileBuffer;
	}

	public void setProjectileBuffer(List<Entity> projectileBuffer) {
		this.projectileBuffer = projectileBuffer;
	}

	public List<Entity> getStaticTileBuffer() {
		return staticTileBuffer;
	}

	public void setStaticTileBuffer(List<Entity> staticTileBuffer) {
		this.staticTileBuffer = staticTileBuffer;
	}

	public List<Entity> getDynamicTileBuffer() {
		return dynamicTileBuffer;
	}

	public void setDynamicTileBuffer(List<Entity> dynamicTileBuffer) {
		this.dynamicTileBuffer = dynamicTileBuffer;
	}

	public List<Entity> getSpawnEnemyBuffer() {
		return spawnEnemyBuffer;
	}

	public void setSpawnEnemyBuffer(List<Entity> spawnEnemyBuffer) {
		this.spawnEnemyBuffer = spawnEnemyBuffer;
	}

	public List<Entity> getDeleteEnemyBuffer() {
		return deleteEnemyBuffer;
	}

	public void setDeleteEnemyBuffer(List<Entity> deleteEnemyBuffer) {
		this.deleteEnemyBuffer = deleteEnemyBuffer;
	}

	public List<Entity> getSpawnProjectileBuffer() {
		return spawnProjectileBuffer;
	}

	public void setSpawnProjectileBuffer(List<Entity> spawnProjectileBuffer) {
		this.spawnProjectileBuffer = spawnProjectileBuffer;
	}

	public List<Entity> getDeleteProjectileBuffer() {
		return deleteProjectileBuffer;
	}

	public void setDeleteProjectileBuffer(List<Entity> deleteProjectileBuffer) {
		this.deleteProjectileBuffer = deleteProjectileBuffer;
	}

	public List<Entity> getSpawnTileBuffer() {
		return spawnTileBuffer;
	}

	public void setSpawnTileBuffer(List<Entity> spawnTileBuffer) {
		this.spawnTileBuffer = spawnTileBuffer;
	}

	public List<Entity> getDeleteTileBuffer() {
		return deleteTileBuffer;
	}

	public void setDeleteTileBuffer(List<Entity> deleteTileBuffer) {
		this.deleteTileBuffer = deleteTileBuffer;
	}

	public List<ActorLink> getDeleteLinkBuffer() {
		return deleteLinkBuffer;
	}

	public void setDeleteLinkBuffer(List<ActorLink> deleteLinkBuffer) {
		this.deleteLinkBuffer = deleteLinkBuffer;
	}

	public int getLevelTick() {
		return levelTick;
	}

	public void setLevelTick(int levelTick) {
		this.levelTick = levelTick;
	}

	public Map<String, Actor> getActorCache() {
		return actorCache;
	}

	public void setActorCollectionCache(Map<String, Actor> actorCache) {
		this.actorCache = actorCache;
	}
}
