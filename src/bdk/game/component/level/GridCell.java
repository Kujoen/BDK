package bdk.game.component.level;

import java.io.Serializable;

import bdk.game.entities.sprites.tiles.Tile;

/**
 * 
 * @author Andreas Farley
 *
 *         A GridCell links a tile to the grid and stores display information
 *         (Coordinate of the tile aswell as options for the sprite like
 *         fit-to-width/height)
 *
 */

public class GridCell implements Serializable{

	private Grid grid;
	
	private Tile tile;
	private int rowIndex;
	
	public GridCell(Grid grid) {
		this.grid = grid;
	}
}
