package bdk.game.component.level;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bdk.util.graphics.Vector2D;

/**
 * Every game component has an associated Grid. The grid saves what tiles should be displayed where
 * 
 * @author Andreas Farley
 *
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = -5043041988399800929L;
	
	private static final int gridSize = 16;
	private static final int horizontalGridCells = 32;
	private static final int verticalGridCells = 18;

	public Grid() {

	}
	
}
