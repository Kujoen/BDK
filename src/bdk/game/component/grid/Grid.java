package bdk.game.component.grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bdk.data.Vector2D;

/**
 * Used by game components to coordinate where entities spawn and stores tile
 * data. A Level-Grid has 5 sections, north, south, east, west and center. These
 * sections can be modified at will for every level. Only the center section can
 * scroll. A Menu-Grid has only one sections, center.Each grid-cell has a
 * setting for layer.
 * 
 * @author Andreas Farley
 *
 */
public class Grid implements Serializable {

	// Finals
	// --------------------------------------------------------|
	// Restricted grid size is 40, so no dynamic blocks or anything... maybe a
	// feature for the future.
	private static final int gridSize = 40;
	private static final int horizontalGridCells = 1280 / 40;
	private static final int verticalGridCells = 720 / 40;
	// --------------------------------------------------------|
	private transient int currentRowIndex;
	// The width of the gridrows is saved in the grid, since all rows must be the
	// same length.
	private int rowWidth = 1;
	// Layed on gridcells, stacked gridRows will display the scrollable area. These
	// are added to the dynamic tile buffer of the level.
	private List<GridRow> gridRowList;
	// The Grid will contain h x v amount of grid-cells. These are added to the
	// static tile buffer of the level.
	private List<GridCell> gridCellList;
	// --------------------------------------------------------|

	public Grid() {

	}

	public void initializeGrid() {
		this.currentRowIndex = 0;
	}

}
