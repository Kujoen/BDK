package bdk.game.component.level;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

import bdk.game.entities.sprites.tiles.Tile;
import javafx.geometry.Point2D;

public class GridRow implements Serializable {
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------------------|

	private ArrayList<Tile> tileList;

	private transient Double yPosition;
	private transient Grid grid;

	// -----------------------------------------------------------------------------|

	/**
	 * Creates a new GridRow that is filled with as many gridcells as the scrollarea
	 * is wide
	 * 
	 * @param grid
	 */
	public GridRow(Grid grid) {
		this.tileList = new ArrayList<>();

		for (int i = 0; i < grid.getScrollArea().width; i++) {
			tileList.add(new Tile());
		}
	}

	/**
	 * Initializes textures in all gridCells.
	 * 
	 * @param level
	 */
	public void initializeTileSprites(Grid grid) {
		tileList.parallelStream().forEach(tile -> tile.initializeTileSprite(grid));
	}

	public void initializeGridRow(Grid grid, double yPosition) {
		this.grid = grid;
		this.yPosition = yPosition;

		for(int i = 0; i < grid.getScrollArea().getWidth(); i++) {
			tileList.get(i).setPosition(new Point2D(grid.transposeXFromGridToReal(i + grid.getScrollArea().x) , yPosition));
		}
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {
		yPosition += grid.getUpdateRate();
		
		tileList.parallelStream().forEach(Tile::update);
	}

	// -----------------------------------------------------------------------------|
	// RENDERING
	// -----------------------------------------------------------------------------|

	public void render(Graphics2D g) {
		tileList.parallelStream().forEach(tile -> tile.render(g));
	}

	public Double getyPosition() {
		return yPosition;
	}

	public void setyPosition(Double yPosition) {
		this.yPosition = yPosition;
	}

	// -----------------------------------------------------------------------------|

	// -----------------------------------------------------------------------------|
}
