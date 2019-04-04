package bdk.game.component.level;

import java.awt.Graphics2D;
import java.io.Serializable;

import bdk.game.entities.sprites.tiles.Tile;
import javafx.geometry.Point2D;

/**
 * A GridCell links a tile to the grid and stores display information
 * (Coordinate of the tile as well as options for the sprite like
 * fit-to-width/height).
 *
 * @author Andreas Farley
 */

public class GridCell implements Serializable {
	private static final long serialVersionUID = -6660477549276327693L;

	// -----------------------------------------------------------------------------|

	private Tile tile;
	private Point2D coordinates;

	// -----------------------------------------------------------------------------|

	/**
	 * A GridCell at specific coordinates. 
	 * @param coordinates
	 */
	public GridCell(Point2D coordinates) {
		this.coordinates = coordinates;
		this.tile = new Tile();
	}

	/**
	 * This method is executed in parallel. Makes tile load its sprite, scale it
	 * then transposes the gridcells position to real coordinates.
	 * 
	 * @param grid
	 */
	public void initializeGridCell(Grid grid) {
		tile.initializeTileSprite(grid);
		tile.setPosition(grid.transposePositionFromGridToReal(coordinates));
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
		tile.render(g);
	}

	// -----------------------------------------------------------------------------|
}
