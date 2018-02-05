package bdk.game.component.grid;

import java.util.List;

/**
 * Used by game components to coordinate where entities spawn and stores tile
 * data. A Level-Grid has 5 sections, north, south, east, west and center. These
 * sections can be modified at will for every level. Only the center section can
 * scroll. A Menu-Grid has only one sections, center.Each grid-cell has a
 * setting for layer and level.
 * 
 * @author Andreas Farley
 *
 */
public class Grid {
	
	private List<GridCell> centerCellList;
	private List<GridCell> northCellList;
	private List<GridCell> southCellList;
	private List<GridCell> eastCellList;
	private List<GridCell> westCellList;
	
}
