
package bdk.game.entities.sprites.tiles;

import java.awt.Graphics2D;

import bdk.game.component.level.Level;
import bdk.game.entities.sprites.Sprite;

/**
 * @author Andreas Farley
 */
public class Tile extends Sprite {
	private static final long serialVersionUID = 8425188223140758605L;
	
	// -----------------------------------------------------------------------------|
	
	public static final String MISSING_TILE_PATH = "src/resources/missing_textures/missing_tile.png";
	
	// -----------------------------------------------------------------------------|
	
	public Tile() {
		super("tile");
		
		this.spritePath = MISSING_TILE_PATH;
	}
	
	public void initializeTile(Level level) {
		level.loadAndCacheTileSprite(this);
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {

	}

	// -----------------------------------------------------------------------------|
	// RENDERING
	// -----------------------------------------------------------------------------|

	public void render(Graphics2D g) {
				
	}
	
	// -----------------------------------------------------------------------------|

}
