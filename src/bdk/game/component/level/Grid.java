package bdk.game.component.level;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

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
	private static final int CELL_SIZE = 16;
	private static final int HORIZONTAL_TILES = 32;
	private static final int VERTICAL_TILES = 18;
	// -----------------------------------------------------------------------------|
	private ArrayList<GridCell> gridCellList;
	// -----------------------------------------------------------------------------|
	private Rectangle scrollArea;
	private ArrayList<GridRow> scrollGridRowList;
	// -----------------------------------------------------------------------------|
	// Level initialized at runtime
	private transient Level level;
	// -----------------------------------------------------------------------------|
	
	public Grid() {
		this.scrollArea = new Rectangle(8, 0, 16, 18);
		this.scrollGridRowList = new ArrayList<>();
		this.gridCellList = new ArrayList<>();

		// Create a default static grid
		for (int i = 0; i < Grid.VERTICAL_TILES; i++ ) {
			for(int k = 0; k < Grid.HORIZONTAL_TILES; k++) {
				Point2D gridCoordinates = new Point2D(k, i);
				
				if (!scrollArea.contains(k, i)) {
					GridCell gridCell = new GridCell(gridCoordinates, true);
					gridCellList.add(gridCell);
				}
			}
		}
	}
	
	public void initializeGrid(Level level) {
		this.level = level;
		
		// Initialize static grid
		gridCellList.stream().parallel().forEach(gridCell -> gridCell.initializeGridCell(level));
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
