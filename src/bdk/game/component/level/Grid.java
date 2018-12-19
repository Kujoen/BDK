package bdk.game.component.level;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;


/**
 * @author Andreas Farley
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = -5043041988399800929L;
	
	// -----------------------------------------------------------------------------|
	private static final int SIZE = 16;
	private static final int HORIZONTAL_TILES = 32;
	private static final int VERTICAL_TILES = 18;
	// -----------------------------------------------------------------------------|
	private ArrayList<GridRow> gridRowList;
	// -----------------------------------------------------------------------------|
	private Rectangle scrollArea;
	private ArrayList<GridRow> scrollGridRowList;
	// -----------------------------------------------------------------------------|
	
	public Grid() {
		this.gridRowList = new ArrayList<>();
		this.scrollGridRowList = new ArrayList<>();
		this.scrollArea = new Rectangle(8, 0, 16, 18);
		
		
		for (int i = 0; i < Grid.VERTICAL_TILES; i++ ) {
			for(int k = 0; k < Grid.HORIZONTAL_TILES; k++) {
				
			}
		}
	}
	
	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	/**
	 * Updates entities then the entity life management system.
	 */
	public void update() {

	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|
	public void render(Graphics2D g) {

	}
	
	
	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|
	
}
