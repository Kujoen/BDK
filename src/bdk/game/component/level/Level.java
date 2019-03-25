package bdk.game.component.level;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import bdk.game.component.Component;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorLink;
import bdk.game.entities.sprites.tiles.Tile;
import bdk.game.main.Game;

/**
 * The level-component of the game package stores camera settings as well as
 * entity informations
 * 
 * @author Andreas Farley
 */
public class Level extends Component {
	private static final long serialVersionUID = 5563212588932966152L;

	// -------------------------------------------------------------------------------|

	private transient int levelTick;
	private int scrollSpeed;

	// -------------------------------------------------------------------------------|
	// BUFFERS
	// -------------------------------------------------------------------------------|

	private ArrayList<ActorLink> actorLinkList;

	//--Runtime Buffers--
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

	public Level(String name) {
		super(name);

		this.grid = new Grid();
		this.scrollSpeed = 16;
		this.levelTick = 0;
	}
	
	/**
	 * Set the game and call initialize on the grid
	 * @param game
	 */
	public void initializeLevel(Game game) {
		this.game = game;
		this.actorCache = new HashMap();
		this.actorSpriteCache = new HashMap();
		this.tileSpriteCache = new HashMap();
		
		// Initialize 
		grid.initializeGrid(this);
	}
	
	public void loadAndCacheTileSprite(String spritePath) {
		// Only cache it if it isn't a missing tile sprite
		if(spritePath != Tile.MISSING_TILE_PATH) {
			tileSpriteCache.put(spritePath, ImageIO.read(spritePath));
		}
	}
	
	public void loadAndCacheActorSprite(String spritePath) {
		// TODO
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

	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

}