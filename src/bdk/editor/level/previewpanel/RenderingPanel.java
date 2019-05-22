package bdk.editor.level.previewpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import bdk.editor.level.BDKLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;
import bdk.game.component.level.Grid;
import bdk.game.component.level.GridCell;
import bdk.game.component.level.Level;
import bdk.game.entities.sprites.tiles.Tile;
import bdk.game.main.Game;
import bdk.util.BdkFileManager;
import bdk.util.graphics.BdkImageEditor;

public class RenderingPanel extends BdkLevelEditorPanel {

	// --------------------------------------------------------------------------|

	// As the level editor preview is always in 1280 x 720, we know the scaling
	// factor is 2.5. So the cellWidth is always 16 * 2.5.
	private Dimension defaultDimension = new Dimension(1280, 720);
	private int previewCellWidth = (int) (Grid.DEFAULT_CELL_SIZE * 2.5);

	// --------------------------------------------------------------------------|

	private Map<Rectangle, GridCell> gridCellMap;

	// --------------------------------------------------------------------------|

	public RenderingPanel(BDKLevelEditor parent) {
		super(parent);

		this.gridCellMap = new HashMap<>();

		this.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				switch (bdkLevelEditor.getCurrentToolName()) {
				case BDKLevelEditor.TOOL_SELECT:
					highlightGridCell(getGridCellInCoordinate(e.getPoint()));
					break;
				case BDKLevelEditor.TOOL_PAINT:
					paintGridCell(getGridCellInCoordinate(e.getPoint()));
					break;
				default:
					break;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	private GridCell getGridCellInCoordinate(Point coordinate) {
		GridCell gridCell = null;

		for (Entry<Rectangle, GridCell> entry : gridCellMap.entrySet()) {
			// Check if its the cell we are looking for
			if (entry.getKey().contains(coordinate)) {
				gridCell = entry.getValue();
			}
		}

		return gridCell;
	}

	// GRID CELL MANIPULATION METHODS -------------------------------------------|
	private void paintGridCell(GridCell gridCell) {

		if (gridCell == null || bdkLevelEditor.getCurrentSpriteImagePath().isEmpty()) {
			return;
		}

		Graphics g = this.getGraphics();

		int cellX = previewCellWidth * gridCell.getGridX();
		int cellY = previewCellWidth * gridCell.getGridY();

		BufferedImage cellTileSprite = BdkImageEditor.scale(
				BdkFileManager.loadImage(bdkLevelEditor.getCurrentSpriteImagePath()), previewCellWidth,
				previewCellWidth);

		if (cellTileSprite == null) {
			Game.getLogger().log(java.util.logging.Level.WARNING,
					"Couldn't load currently selected sprite image. Refresh the image selection panel");
		}

		g.setColor(Color.black);
		g.fillRect(cellX, cellY, previewCellWidth, previewCellWidth);
		g.drawImage(cellTileSprite, cellX, cellY, null);
		g.dispose();

		gridCell.setTile(new Tile(bdkLevelEditor.getCurrentSpriteImagePath()));
	}

	/**
	 * Highlights the gridcell at the given coordinates
	 * 
	 * @param x
	 * @param y
	 */
	private void highlightGridCell(GridCell gridCell) {

	}

	// --------------------------------------------------------------------------|

	@Override
	public void notifyDataChanged() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Clear old
		g.setColor(Color.black);
		g.fillRect(0, 0, defaultDimension.width, defaultDimension.height);

		Level currentLevel = bdkLevelEditor.getCurrentLevel();

		if (currentLevel != null) {

			// Preload default missing image
			BufferedImage missingSpriteImage = BdkFileManager.loadImage(Tile.MISSING_TILE_PATH);

			// STATIC GRID ---------------------------------------------------|

			for (GridCell cell : currentLevel.getGrid().getGridCellList()) {
				// Transpose the coordinates with the predetermined cell width
				int cellX = previewCellWidth * cell.getGridX();
				int cellY = previewCellWidth * cell.getGridY();

				BufferedImage cellTileSprite = BdkImageEditor.scale(
						BdkFileManager.loadImage(cell.getTile().getSpritePath()), previewCellWidth, previewCellWidth);

				if (cellTileSprite == null) {
					cellTileSprite = missingSpriteImage;
				}

				// Create the bounding box for cell identification
				Rectangle boundingBox = new Rectangle(cellX, cellY, previewCellWidth, previewCellWidth);
				gridCellMap.put(boundingBox, cell);

				g.drawImage(cellTileSprite, cellX, cellY, null);
			}

			// SCROLL GRID ---------------------------------------------------|

			// ...

			// ---------------------------------------------------------------|
		}
	}

	// --------------------------------------------------------------------------|
	// These overridess make sure the panel doesn't resize and always stays 1280x720
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(defaultDimension);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(defaultDimension);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(defaultDimension);
	}
	// --------------------------------------------------------------------------|
}
