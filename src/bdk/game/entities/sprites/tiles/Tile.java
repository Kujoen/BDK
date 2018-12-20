
package bdk.game.entities.sprites.tiles;

import java.awt.Graphics2D;

import bdk.game.entities.sprites.Sprite;
import bdk.util.graphics.BdkImageEditor;

/**
 * @author Andreas Farley
 */
public class Tile extends Sprite {
	private static final long serialVersionUID = 8425188223140758605L;
	
	private static final String MISSING_TILE_PATH = "src/resources/missing_textures/missing_tile.png";
	
	// -----------------------------------------------------------------------------|
	
	public Tile() {
		super("tile");
		
		this.spritePath = MISSING_TILE_PATH;
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
