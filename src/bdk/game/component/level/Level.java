package bdk.game.component.level;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import bdk.game.component.Component;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorLink;
import bdk.game.entities.sprites.tiles.Tile;
import bdk.game.main.Game;
import bdk.util.BDKFileManager;

/**
 * The level-component of the game package stores camera settings as well as
 * entity informations
 * 
 * @author Andreas Farley
 */
public class Level extends Component{
	private static final long serialVersionUID = 5563212588932966152L;

	// -------------------------------------------------------------------------------|

	private transient int levelTick;
	private int scrollSpeed;

	// -------------------------------------------------------------------------------|
	// BUFFERS
	// -------------------------------------------------------------------------------|

	private ArrayList<ActorLink> actorLinkList;

	// --Runtime Buffers--
	private transient HashMap<String, Actor> actorCache;

	private transient HashMap<String, BufferedImage> actorSpriteCache;
	private transient HashMap<String, BufferedImage> tileSpriteCache;

	// -------------------------------------------------------------------------------|
	// LEVEL INFORMATION
	// -------------------------------------------------------------------------------|
	private Grid grid;
	private transient Actor player;

	// Game initialized at runtime
	private transient Game game;
	// -------------------------------------------------------------------------------|

	/**
	 * Level with default scroll speed 16. 
	 * @param name
	 */
	public Level(String name) {
		super(name);

		this.grid = new Grid();
		this.scrollSpeed = 120;
		this.levelTick = 0;
	}

	/**
	 * Set the game and call initialize on the grid
	 * 
	 * @param game
	 */
	public void initializeLevel(Game game) {
		this.game = game;
		this.actorCache = new HashMap<>();
		this.actorSpriteCache = new HashMap<>();
		this.tileSpriteCache = new HashMap<>();
		
		// Load missing-sprite textures
		BufferedImage missingTileSprite = BDKFileManager.loadImage(Tile.MISSING_TILE_PATH);

		tileSpriteCache.put(Tile.MISSING_TILE_PATH, missingTileSprite);
		
		// Initialize
		grid.initializeGrid(this);
	}

	/**
	 * Tiles sprite will be only loaded if its not the missing tile sprite and not already in the cache. 
	 * If the image can't load the sprites image then it is set to the missing tile sprite.
	 * @param tile
	 */
	public void loadAndCacheTileSprite(Tile tile) {
		String spritePath = tile.getSpritePath();
		
		if((tile.getSpritePath() != Tile.MISSING_TILE_PATH) && !tileSpriteCache.containsKey(spritePath)) {
			BufferedImage spriteImage = BDKFileManager.loadImage(spritePath);
			
			// Set to missing sprite if image not found. This is not saved, it's only for this runtime.
			if(spriteImage == null) {
				tile.setSpritePath(Tile.MISSING_TILE_PATH);
			} else {
				tileSpriteCache.put(spritePath, spriteImage);
			}	
		}
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	/**
	 * Updates entities then the entity life management system.
	 */
	@Override
	public void update() {
		// Increase the levelTick
		levelTick++;
		
		grid.update();
	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	//
	// RENDER ORDER:
	// -> ScrollGridLayer BASE LAYER
	// -> Enemies
	// -> Projectiles
	// -> Player
	// -> ScrollGridLayer ADDITIONAL LAYERS ( TO BE ADDED )
	// -> StaticTiles
	// -> Widgets
	// -------------------------------------------------------------------------------|

	@Override
	public void render(Graphics2D g) {
		grid.renderStaticGrid(g);
		grid.renderScrollGrid(g);
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|
	
	public int getLevelTick() {
		return levelTick;
	}

	public void setLevelTick(int levelTick) {
		this.levelTick = levelTick;
	}

	public int getScrollSpeed() {
		return scrollSpeed;
	}

	/**
	 * Max scroll speed = cell size * tickrate
	 * @param scrollSpeed
	 */
	public void setScrollSpeed(int scrollSpeed) {
		this.scrollSpeed = scrollSpeed;
	}

	public ArrayList<ActorLink> getActorLinkList() {
		return actorLinkList;
	}

	public void setActorLinkList(ArrayList<ActorLink> actorLinkList) {
		this.actorLinkList = actorLinkList;
	}

	public HashMap<String, Actor> getActorCache() {
		return actorCache;
	}

	public void setActorCache(HashMap<String, Actor> actorCache) {
		this.actorCache = actorCache;
	}

	public HashMap<String, BufferedImage> getActorSpriteCache() {
		return actorSpriteCache;
	}

	public void setActorSpriteCache(HashMap<String, BufferedImage> actorSpriteCache) {
		this.actorSpriteCache = actorSpriteCache;
	}

	public HashMap<String, BufferedImage> getTileSpriteCache() {
		return tileSpriteCache;
	}

	public void setTileSpriteCache(HashMap<String, BufferedImage> tileSpriteCache) {
		this.tileSpriteCache = tileSpriteCache;
	}

	public Actor getPlayer() {
		return player;
	}

	public void setPlayer(Actor player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}


}