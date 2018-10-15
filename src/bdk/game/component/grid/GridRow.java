package bdk.game.component.grid;

import java.io.Serializable;
import java.util.List;

public class GridRow implements Serializable{
	
	private Grid grid;
	
	// Contains all gridcells in this row
	private List<GridCell> cellList;
	private int rowLength;
	
	public GridRow(Grid grid) {
		this.grid = grid;
	}

}
