package bdk.game.component.level;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import bdk.game.component.Component;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorLink;

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
	private transient HashMap<String, Actor> actorCache;

	// -------------------------------------------------------------------------------|
	// LEVEL INFORMATION
	// -------------------------------------------------------------------------------|
	private Grid grid;
	private transient Actor player;
	// -------------------------------------------------------------------------------|

	public Level(String name) {
		super(name);

		this.grid = new Grid();
		this.scrollSpeed = 16;
		this.levelTick = 0;
	}
	
	public void initializeLevel() {
		
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
	// -> ScrollGridLayer ADDITIONAL LAYERS
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
