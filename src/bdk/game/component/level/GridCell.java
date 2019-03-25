package bdk.game.component.level;

import java.awt.Graphics2D;
import java.io.Serializable;

import bdk.game.entities.sprites.tiles.Tile;
import javafx.geometry.Point2D;

/**
 * A GridCell links a tile to the grid and stores display information
 * (Coordinate of the tile as well as options for the sprite like
 * fit-to-width/height) If in scroll area it will not render.
 *
 * @author Andreas Farley
 */

public class GridCell implements Serializable {
	private static final long serialVersionUID = -6660477549276327693L;

	// -----------------------------------------------------------------------------|

	private Tile tile;
	private Point2D coordinates;
	private boolean isStaticCell;


	// -----------------------------------------------------------------------------|

	/**
	 * A GridCell at specific coordinates. If cell is static, cell will always draw to transposed gridcoordinate.
	 * @param coordinates
	 * @param isStaticCell
	 */
	public GridCell(Point2D coordinates, boolean isStaticCell) {
		this.coordinates = coordinates;
		this.isStaticCell = isStaticCell;
		this.tile = new Tile();
	}
	
	/**
	 * This method is executed in parallel
	 * @param level
	 */
	public void initializeGridCell(Level level) {
		tile.initializeTile(level);
	}


	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {
		if(!isStaticCell) {
			
		}
	}

	// -----------------------------------------------------------------------------|
	// RENDERING
	// -----------------------------------------------------------------------------|

	public void render(Graphics2D g) {
				
	}
	
	// -----------------------------------------------------------------------------|
}
