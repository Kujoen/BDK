package bdk.game.component.level;

import java.awt.Graphics2D;
import java.io.Serializable;

import bdk.game.entities.sprites.tiles.Tile;
import javafx.geometry.Point2D;

/**
 * A GridCell links a tile to the grid and stores display information
 * (Coordinate of the tile as well as options for the sprite like
 * fit-to-width/height)
 *
 * @author Andreas Farley
 */

public class GridCell implements Serializable {
	private static final long serialVersionUID = -6660477549276327693L;

	// -----------------------------------------------------------------------------|

	private Point2D coordinates;
	private boolean isInScrollArea;
	
	private Tile tile;

	// -----------------------------------------------------------------------------|

	public GridCell(Point2D coordinates, boolean isInScrollArea) {
		this.coordinates = coordinates;
		this.isInScrollArea = isInScrollArea;
		this.tile = new Tile();
	}
	
	public void initializeGridCell() {
		
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
