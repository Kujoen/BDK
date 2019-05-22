package bdk.game.component.level;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import bdk.game.main.Game;
import javafx.geometry.Point2D;

/**
 * First row of the Grid, with coordinates:
 * 
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * |0,0||1,0||2,0||3,0||4,0||5,0||6,0||7,0||8,0||9,0||10,0||11,0||12,0||13,0||14,0||15,0||16,0||17,0||18,0||19,0||20,0||21,0||22,0||23,0||24,0||25,0||26,0||27,0||28,0||29,0||30,0||31,0||32,0|
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * @author Andreas Farley
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = -5043041988399800929L;

	// -----------------------------------------------------------------------------|
	public static final int DEFAULT_CELL_SIZE = 16;
	public static final int HORIZONTAL_TILES = 32;
	public static final int VERTICAL_TILES = 18;
	// -----------------------------------------------------------------------------|
	private ArrayList<GridCell> gridCellList;
	// -----------------------------------------------------------------------------|
	private Rectangle scrollArea;
	private ArrayList<GridRow> gridRowList;
	// -----------------------------------------------------------------------------|
	// Runtime variables
	private transient Level level;

	private transient LinkedList<GridRow> activeRowList;

	// Use a iterator to get gridRows in order
	private transient Iterator<GridRow> gridRowIterator;

	private transient Double updateRate;
	private transient boolean isUpdateRateChanged;

	// Calculated as : DefaultCellSize * scalingVector. So this is the actual cell width/height
	private transient Point2D cellDimension;
	// -----------------------------------------------------------------------------|

	public Grid() {
		this.scrollArea = new Rectangle(8, 0, 16, 18);
		this.gridRowList = new ArrayList<>();
		this.gridCellList = new ArrayList<>();

		// Add Static tiles
		for (int i = 0; i < Grid.VERTICAL_TILES; i++) {
			for (int k = 0; k < Grid.HORIZONTAL_TILES; k++) {
				if (!scrollArea.contains(k, i)) {
					GridCell gridCell = new GridCell(k, i);
					gridCellList.add(gridCell);
				}
			}
		}

		// Add gridrows. Create enough rows to fill one page.
		for (int i = 0; i < scrollArea.height; i++) {
			GridRow gridRow = new GridRow(this);
			gridRowList.add(gridRow);
		}
	}

	/**
	 * Initialize grid runtime variables and initialize gridcells
	 * 
	 * @param level
	 */
	public void initializeGrid(Level level) {
		this.level = level;
		this.isUpdateRateChanged = false;
		this.activeRowList = new LinkedList<>();
		this.gridRowIterator = gridRowList.iterator();

		calculateUpdateRate();
		calculateCellSize();

		// Initialize static gridCells
		gridCellList.parallelStream().forEach(gridCell -> gridCell.initializeGridCell(this));
		// Initialize scroll gridCells
		gridRowList.parallelStream().forEach(gridRow -> gridRow.initializeTileSprites(this));

		loadInitialPage();
	}

	private void loadInitialPage() {
		for (int i = scrollArea.height - 1; i >= 0; i--) {
			GridRow gridRow = getNextGridRow();
			gridRow.initializeGridRow(this, transposeYFromGridToReal(i));
			activeRowList.add(gridRow);
		}
	}

	/**
	 * Returns the next grid row from the GridRow list or a default new one if there
	 * are no more rows.s
	 * 
	 * @return
	 */
	private GridRow getNextGridRow() {
		if (gridRowIterator.hasNext()) {
			return gridRowIterator.next();
		} else {
			GridRow newGridRow = new GridRow(this);
			newGridRow.initializeTileSprites(this);
			return newGridRow;
		}
	}

	public Point2D transposePositionFromGridToReal(java.awt.geom.Point2D rawPosition) {
		Point2D realPosition;

		realPosition = new Point2D(rawPosition.getX() * cellDimension.getX(),
				rawPosition.getY() * cellDimension.getY());

		return realPosition;
	}

	public double transposeXFromGridToReal(double x) {
		return x * cellDimension.getX();
	}

	public double transposeYFromGridToReal(double y) {
		return y * cellDimension.getY();
	}

	/**
	 * Calculates how many pixels we need to move a tile per update call, so that in
	 * one second it moves the distanced specified by the scrollspeed. The amount of
	 * update calls is specified by the games tickrate.
	 */
	private void calculateUpdateRate() {
		updateRate = level.getScrollSpeed() / Game.TICKRATE;
	}

	private void calculateCellSize() {
		cellDimension = new Point2D(level.getGame().getWindow().getScalingVector().getX() * DEFAULT_CELL_SIZE,
				level.getGame().getWindow().getScalingVector().getY() * DEFAULT_CELL_SIZE);
	}

	// -------------------------------------------------------------------------------|
	// UPDATING
	// -------------------------------------------------------------------------------|

	public void update() {
		GridRow lastRow = activeRowList.getLast();
		GridRow firstRow = activeRowList.getFirst();

		// Check if we need another row before updating. If we add the new row after
		// updating, a render call could be executed without it which results in blank
		// screen space.
		if (lastRow.getyPosition() + updateRate > 0) {

			// In the next update the row will move below the screen. This means we need to
			// load another row.
			GridRow newRow = getNextGridRow();

			// We initialize the new row to be exactly on top of the previous last row. This
			// means it spawns exactly one tile above the y position of the last row.
			newRow.initializeGridRow(this, lastRow.getyPosition() - cellDimension.getY());

			activeRowList.add(newRow);
		}

		activeRowList.parallelStream().forEach(GridRow::update);

		// After updating all rows check if we can remove the last one, if it's already
		// past the bottom of the screen.
		if (firstRow.getyPosition() > level.getGame().getWindow().getGameDimension().getHeight()) {
			activeRowList.remove();
		}
	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|

	public void renderStaticGrid(Graphics2D g) {
		gridCellList.parallelStream().forEach(gridCell -> gridCell.render(g));
	}

	public void renderScrollGrid(Graphics2D g) {
		activeRowList.parallelStream().forEach(gridRow -> gridRow.render(g));
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Point2D getCellDimension() {
		return cellDimension;
	}

	public void setCellDimension(Point2D cellDimension) {
		this.cellDimension = cellDimension;
	}

	public Rectangle getScrollArea() {
		return scrollArea;
	}

	public void setScrollArea(Rectangle scrollArea) {
		this.scrollArea = scrollArea;
	}

	public Double getUpdateRate() {
		return updateRate;
	}

	public void setUpdateRate(Double updateRate) {
		this.updateRate = updateRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LinkedList<GridRow> getActiveRowList() {
		return activeRowList;
	}

	public void setActiveRowList(LinkedList<GridRow> activeRowList) {
		this.activeRowList = activeRowList;
	}

	public ArrayList<GridCell> getGridCellList() {
		return gridCellList;
	}

	public void setGridCellList(ArrayList<GridCell> gridCellList) {
		this.gridCellList = gridCellList;
	}

	public ArrayList<GridRow> getGridRowList() {
		return gridRowList;
	}

	public void setGridRowList(ArrayList<GridRow> gridRowList) {
		this.gridRowList = gridRowList;
	}

}
