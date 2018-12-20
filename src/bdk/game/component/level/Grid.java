package bdk.game.component.level;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.geometry.Point2D;


/**
 * @author Andreas Farley
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = -5043041988399800929L;
	
	// -----------------------------------------------------------------------------|
	private static final int CELL_SIZE = 16;
	private static final int HORIZONTAL_TILES = 32;
	private static final int VERTICAL_TILES = 18;
	// -----------------------------------------------------------------------------|
	private ArrayList<GridCell> gridCellList;
	// -----------------------------------------------------------------------------|
	private Rectangle scrollArea;
	private ArrayList<GridRow> scrollGridRowList;
	// -----------------------------------------------------------------------------|
	
	public Grid() {
		this.gridCellList = new ArrayList<>();
		this.scrollGridRowList = new ArrayList<>();
		this.scrollArea = new Rectangle(8, 0, 16, 18);
		
		
		for (int i = 0; i < Grid.VERTICAL_TILES; i++ ) {
			for(int k = 0; k < Grid.HORIZONTAL_TILES; k++) {
				Point2D gridCoordinates = new Point2D(k, i);	
				GridCell gridCell = new GridCell(gridCoordinates, scrollArea.contains(k, i));
				
				gridCellList.add(gridCell);
			}
		}
	}
	
	public void initializeGrid() {
		
		gridCellList.stream().parallel().forEach(gridCell -> {
			gridCell.initializeGridCell();
		});
		
	}
	
	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {

	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|
	public void renderStaticGrid(Graphics2D g) {
		
	}
	
	public void renderScrollGrid(Graphics2D g) {

	}
	
	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|
	
}
