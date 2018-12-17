package bdk.game.component.level;

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
	
	// -----------------------------------------------------------------------------|
	
	private Point2D coordinates;
	
	// -----------------------------------------------------------------------------|
	public GridCell() {
	
	}
	// -----------------------------------------------------------------------------|
}
