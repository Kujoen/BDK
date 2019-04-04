
package bdk.game.entities.sprites.tiles;

import java.awt.Graphics2D;

import bdk.game.component.level.Grid;
import bdk.game.entities.sprites.Sprite;
import bdk.util.BdkCopy;
import bdk.util.graphics.BdkImageEditor;

/**
 * @author Andreas Farley
 */
public class Tile extends Sprite {
	private static final long serialVersionUID = 8425188223140758605L;
	public static final String MISSING_TILE_PATH = "src/resources/missing_textures/missing_tile.png";

	// -----------------------------------------------------------------------------|

	private transient Grid grid;

	// -----------------------------------------------------------------------------|

	public Tile() {
		super("tile");
		this.spritePath = MISSING_TILE_PATH;
	}

	/**
	 * 
	 * Requests a load/cache of this tiles spriteImage. Then gets a copy of the
	 * spriteImage from the cache.
	 * 
	 * @param level
	 */
	public void initializeTileSprite(Grid grid)  {
		this.grid = grid;
		
		grid.getLevel().loadAndCacheTileSprite(this);
		
		spriteImage = BdkCopy.deepCopyBufferedImage(grid.getLevel().getTileSpriteCache().get(this.spritePath));
		spriteImage = BdkImageEditor.scale(spriteImage, (int) grid.getCellDimension().getX(), (int) grid.getCellDimension().getY());
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {
		this.position = position.add(0, grid.getUpdateRate());
	}

	// -----------------------------------------------------------------------------|
	// RENDERING
	// -----------------------------------------------------------------------------|

	public void render(Graphics2D g) {
		g.drawImage(spriteImage,(int) position.getX(), (int) position.getY(), null);
	}

	// -----------------------------------------------------------------------------|
}