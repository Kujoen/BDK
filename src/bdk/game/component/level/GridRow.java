package bdk.game.component.level;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

public class GridRow implements Serializable {

	// -----------------------------------------------------------------------------|

	private ArrayList<GridCell> gridCellList;

	// -----------------------------------------------------------------------------|

	public GridRow() {
		// EMPTY
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {

	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|

	public void render(Graphics2D g) {

	}

	// -----------------------------------------------------------------------------|

	public ArrayList<GridCell> getGridCellList() {
		return gridCellList;
	}

	public void setGridCellList(ArrayList<GridCell> gridCellList) {
		this.gridCellList = gridCellList;
	}

	// -----------------------------------------------------------------------------|
}
