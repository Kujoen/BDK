package bdk.editor.level.previewpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import bdk.editor.level.BDKLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;
import bdk.game.component.level.Grid;
import bdk.game.component.level.GridCell;
import bdk.game.component.level.Level;
import bdk.game.entities.sprites.tiles.Tile;
import bdk.util.BdkFileManager;
import bdk.util.graphics.BdkImageEditor;
import javafx.geometry.Point2D;

public class RenderingPanel extends BdkLevelEditorPanel {

	private Dimension realDimension = new Dimension(512, 288);
	private Dimension defaultDimension = new Dimension(1280, 720);
	private double scalingFactor = 2.5;

	// --------------------------------------------------------------------------|

	public RenderingPanel(BDKLevelEditor parent) {
		super(parent);
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

			// Set the scaling vector so we can transpose cell coordinates
			currentLevel.getGrid().setCellDimension(
					new Point2D(Grid.DEFAULT_CELL_SIZE * scalingFactor, Grid.DEFAULT_CELL_SIZE * scalingFactor));

			// Draw the static grid
			for (GridCell cell : currentLevel.getGrid().getGridCellList()) {
				BufferedImage cellTileSprite = BdkImageEditor.scale(
						BdkFileManager.loadImage(cell.getTile().getSpritePath()),
						(int) currentLevel.getGrid().getCellDimension().getX(),
						(int) currentLevel.getGrid().getCellDimension().getY());

				if (cellTileSprite == null) {
					cellTileSprite = missingSpriteImage;
				}

				g.drawImage(cellTileSprite, (int) currentLevel.getGrid().transposeXFromGridToReal(cell.getGridX()),
						(int) currentLevel.getGrid().transposeYFromGridToReal(cell.getGridY()), null);
			}
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
