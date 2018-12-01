package bdk.game.component.level;

import java.io.Serializable;

import bdk.game.entities.sprites.tiles.Tile;

/**
 * A GridCell links a tile to the grid and stores display information
 * (Coordinate of the tile as well as options for the sprite like
 * fit-to-width/height)
 *
 * @author Andreas Farley
 */

public class GridCell implements Serializable {

	private Grid grid;

	private Tile tile;
	private int rowIndex;

	public GridCell(Grid grid) {
		this.grid = grid;
	}
}
