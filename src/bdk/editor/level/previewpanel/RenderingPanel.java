package bdk.editor.level.previewpanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import bdk.editor.level.BDKLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;
import bdk.game.component.level.Grid;
import bdk.game.component.level.GridCell;
import bdk.game.component.level.GridRow;
import bdk.game.component.level.Level;
import bdk.game.entities.sprites.tiles.Tile;
import bdk.game.main.Game;
import bdk.util.BDKFileManager;
import bdk.util.graphics.BDKImageEditor;
import javafx.geometry.Point2D;

public class RenderingPanel extends BdkLevelEditorPanel {

	// --------------------------------------------------------------------------|

	// As the level editor preview is always in 1280 x 720, we know the scaling
	// factor is 2.5. So the cellWidth is always 16 * 2.5.
	private Dimension defaultDimension = new Dimension(1280, 720);
	private int previewCellWidth = (int) (Grid.DEFAULT_CELL_SIZE * 2.5);

	// ----------------------------------|

	private BufferedImage renderingPanelBuffer;
	private boolean redraw;
	private JPanel indicatorPanel;
	private Map<Rectangle, GridCell> gridCellMap;
	private Map<Rectangle, Tile> gridRowTileMap;
	private boolean isMousePressed;
	private boolean isMouseDragged;
	private Point mousePressedPoint;
	private Point mouseReleasedPoint;
	private Point mouseDraggedPoint;
	private int gridRowStartIndex;

	// --------------------------------------------------------------------------|

	public RenderingPanel(BDKLevelEditor parent) {
		super(parent);

		this.gridRowStartIndex = 0;
		this.mousePressedPoint = null;
		this.mouseReleasedPoint = null;
		this.mouseDraggedPoint = null;
		this.gridCellMap = new HashMap<>();
		this.gridRowTileMap = new HashMap<>();
		this.indicatorPanel = createIndicatorPanel();
		this.isMouseDragged = false;
		this.isMousePressed = false;
		this.renderingPanelBuffer = new BufferedImage(defaultDimension.width, defaultDimension.height,
				BufferedImage.TYPE_INT_ARGB_PRE);
		this.redraw = true;
		this.addMouseListeners();
		this.add(indicatorPanel);
	}

	// GRID CELL MANIPULATION METHODS -------------------------------------------|
	private void paintGridCell(GridCell gridCell) {

		if (gridCell == null || bdkLevelEditor.getCurrentSpriteImagePath().isEmpty()) {
			return;
		}

		// Draw onto the buffer
		Graphics g = renderingPanelBuffer.getGraphics();

		int cellX = previewCellWidth * gridCell.getGridX();
		int cellY = previewCellWidth * gridCell.getGridY();

		BufferedImage cellTileSprite = BDKImageEditor.scale(
				BDKFileManager.loadImage(bdkLevelEditor.getCurrentSpriteImagePath()), previewCellWidth,
				previewCellWidth);

		if (cellTileSprite == null) {
			Game.getLogger().log(java.util.logging.Level.WARNING,
					"Couldn't load currently selected sprite image. Refresh the image selection panel");
			return;
		}

		g.setColor(Color.black);
		g.fillRect(cellX, cellY, previewCellWidth, previewCellWidth);
		g.drawImage(cellTileSprite, cellX, cellY, null);
		g.dispose();

		gridCell.setTile(new Tile(bdkLevelEditor.getCurrentSpriteImagePath()));
	}

	private void paintTile(Tile tile) {
		if (tile == null || bdkLevelEditor.getCurrentSpriteImagePath().isEmpty()) {
			return;
		}

		// Draw onto the buffer
		Graphics g = renderingPanelBuffer.getGraphics();

		int tileX = (int) tile.getPosition().getX();
		int tileY = (int) tile.getPosition().getY();

		BufferedImage cellTileSprite = BDKImageEditor.scale(
				BDKFileManager.loadImage(bdkLevelEditor.getCurrentSpriteImagePath()), previewCellWidth,
				previewCellWidth);

		if (cellTileSprite == null) {
			Game.getLogger().log(java.util.logging.Level.WARNING,
					"Couldn't load currently selected sprite image. Refresh the image selection panel");
			return;
		}

		g.setColor(Color.black);
		g.fillRect(tileX, tileY, previewCellWidth, previewCellWidth);
		g.drawImage(cellTileSprite, tileX, tileY, null);
		g.dispose();

		tile.setSpritePath(bdkLevelEditor.getCurrentSpriteImagePath());
	}

	/**
	 * Returns the gridCell, in which the coordinate lies
	 * 
	 * @param coordinate
	 * @return
	 */
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

	/**
	 * Returns the Tile, in which the coordinate lies
	 * 
	 * @param coordinate
	 * @return
	 */
	private Tile getTileInCoordinate(Point coordinate) {
		Tile tile = null;

		for (Entry<Rectangle, Tile> entry : gridRowTileMap.entrySet()) {
			// Check if its the cell we are looking for
			if (entry.getKey().contains(coordinate)) {
				tile = entry.getValue();
			}
		}

		return tile;
	}

	/**
	 * Returns the rectangle, in which the coordinate lies
	 * 
	 * @param coordinate
	 * @return
	 */
	private Rectangle getRectangleInCoordinate(Point coordinate) {
		Rectangle rec = null;

		for (Entry<Rectangle, GridCell> entry : gridCellMap.entrySet()) {
			// Check if its the cell we are looking for
			if (entry.getKey().contains(coordinate)) {
				rec = entry.getKey();
			}
		}

		if (rec == null) {
			for (Entry<Rectangle, Tile> entry : gridRowTileMap.entrySet()) {
				// Check if its the cell we are looking for
				if (entry.getKey().contains(coordinate)) {
					rec = entry.getKey();
				}
			}
		}

		return rec;
	}

	private ArrayList<Rectangle> getRectanglesInArea(Rectangle area) {
		ArrayList<Rectangle> rectangleList = new ArrayList<>();

		for (Entry<Rectangle, GridCell> entry : gridCellMap.entrySet()) {
			Rectangle targetRectangle = entry.getKey();

			// Check if rectangles are overlapping
			if ((targetRectangle.x < area.x + area.width) && (targetRectangle.x + targetRectangle.width > area.x)
					&& (targetRectangle.y < area.y + area.height)
					&& (targetRectangle.y + targetRectangle.height) > area.y) {
				rectangleList.add(targetRectangle);
			}
		}

		for (Entry<Rectangle, Tile> entry : gridRowTileMap.entrySet()) {
			Rectangle targetRectangle = entry.getKey();

			// Check if rectangles are overlapping
			if ((targetRectangle.x < area.x + area.width) && (targetRectangle.x + targetRectangle.width > area.x)
					&& (targetRectangle.y < area.y + area.height)
					&& (targetRectangle.y + targetRectangle.height) > area.y) {
				rectangleList.add(targetRectangle);
			}
		}

		return rectangleList;
	}

	private ArrayList<GridCell> getGridCellsInArea(Rectangle area) {
		ArrayList<GridCell> gridCellList = new ArrayList<>();

		for (Entry<Rectangle, GridCell> entry : gridCellMap.entrySet()) {
			Rectangle targetRectangle = entry.getKey();

			// Check if rectangles are overlapping
			if ((targetRectangle.x < area.x + area.width) && (targetRectangle.x + targetRectangle.width > area.x)
					&& (targetRectangle.y < area.y + area.height)
					&& (targetRectangle.y + targetRectangle.height) > area.y) {
				gridCellList.add(entry.getValue());
			}
		}

		return gridCellList;
	}

	private ArrayList<Tile> getTilesInArea(Rectangle area) {
		ArrayList<Tile> tileList = new ArrayList<>();

		for (Entry<Rectangle, Tile> entry : gridRowTileMap.entrySet()) {
			Rectangle targetRectangle = entry.getKey();

			// Check if rectangles are overlapping
			if ((targetRectangle.x < area.x + area.width) && (targetRectangle.x + targetRectangle.width > area.x)
					&& (targetRectangle.y < area.y + area.height)
					&& (targetRectangle.y + targetRectangle.height) > area.y) {
				tileList.add(entry.getValue());
			}
		}

		return tileList;
	}

	// INDICATOR PANEL --------------------------------------------------------|

	private JPanel createIndicatorPanel() {
		@SuppressWarnings("serial")
		JPanel newIndicatorPanel = new JPanel() {
			// --------------------------------------|
			// These overrides make sure the panel doesn't resize and always stays 1280x720
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

			// --------------------------------------|
			// Override the paintComponent so resizing doesn't redraw the indicatorPanel
			@Override
			protected void paintComponent(Graphics g) {
				// we need g2d for stroke thickness
				Graphics2D g2D = (Graphics2D) g;

				// make sure its overlapping
				indicatorPanel.setLocation(new Point(0, 0));

				// Clear panel
				g2D.setColor(new Color(0, 0, 0, 0));
				g2D.fillRect(0, 0, defaultDimension.width, defaultDimension.height);

				// Set indicator colour (RED)
				g2D.setColor(Color.red);
				g2D.setStroke(new BasicStroke(2));

				// Check if we have to paint selection indicators
				if (isMousePressed && !isMouseDragged) {
					Rectangle targetRectangle = getRectangleInCoordinate(mousePressedPoint);

					if (targetRectangle != null) {
						g2D.drawRect(targetRectangle.x, targetRectangle.y, targetRectangle.width,
								targetRectangle.height);
					}
				} else if (isMouseDragged) {
					// First check the case that we are still inside of the same rectangle
					Rectangle sourceRectangle = getRectangleInCoordinate(mousePressedPoint);
					Rectangle destinationRectangle = getRectangleInCoordinate(mouseDraggedPoint);

					if (sourceRectangle == null || destinationRectangle == null) {
						return;
					}

					if (sourceRectangle == destinationRectangle) {
						g2D.drawRect(sourceRectangle.x, sourceRectangle.y, sourceRectangle.width,
								sourceRectangle.height);
					} else {
						// Draw every selected rectangle
						for (Rectangle rec : getRectanglesInArea(getSelectionRectangle())) {
							g2D.drawRect(rec.x, rec.y, rec.width, rec.height);
						}
					}

				}
			}
		};

		newIndicatorPanel.setOpaque(false);
		return newIndicatorPanel;
	}

	/**
	 * Returns a rectangle that represents the area the user clicked and dragged
	 * over
	 * 
	 * @return
	 */
	public Rectangle getSelectionRectangle() {
		Rectangle coverRectangle = new Rectangle(mousePressedPoint);
		coverRectangle.add(mouseDraggedPoint);

		return coverRectangle;
	}

	// GRID RENDER PANEL --------------------------------------------------|

	/**
	 * We have a problem, since we don't cache any sprites, repainting the component
	 * does a bunch of I/O all the time. This slows everything down. Instead, draw
	 * any operations onto a BufferedImage. Then draw this BufferedImage onto the
	 * JPanel. The BufferedImage is only redrawn from actual disk sprites when a
	 * redraw is requested.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// Clear screen
		g.setColor(Color.black);
		g.fillRect(0, 0, defaultDimension.width, defaultDimension.height);

		Level currentLevel = bdkLevelEditor.getCurrentLevel();

		if (currentLevel != null) {
			// No redraw requested. Just draw the contents of the BufferedImage
			if (!redraw) {
				g.drawImage(renderingPanelBuffer, 0, 0, null);
			} else {
				Graphics bufferGraphics = renderingPanelBuffer.getGraphics();
				loadAndDrawLevel(bufferGraphics, currentLevel);
				bufferGraphics.dispose();

				g.drawImage(renderingPanelBuffer, 0, 0, null);
				redraw = false;
			}
		}

	}

	private void loadAndDrawLevel(Graphics g, Level level) {
		// Since the gridRows initialize method uses this vector, initialize it here
		level.getGrid().setCellDimension(new Point2D(previewCellWidth, previewCellWidth));

		// Clear screen
		g.setColor(Color.black);
		g.fillRect(0, 0, defaultDimension.width, defaultDimension.height);

		// Preload default missing image
		BufferedImage missingSpriteImage = BDKFileManager.loadImage(Tile.MISSING_TILE_PATH);

		// STATIC GRID ---------------------------------------------------|

		for (GridCell cell : level.getGrid().getGridCellList()) {
			// Transpose the coordinates with the predetermined cell width
			int cellX = previewCellWidth * cell.getGridX();
			int cellY = previewCellWidth * cell.getGridY();

			BufferedImage cellTileSprite = BDKImageEditor.scale(
					BDKFileManager.loadImage(cell.getTile().getSpritePath()), previewCellWidth, previewCellWidth);

			if (cellTileSprite == null) {
				cellTileSprite = missingSpriteImage;
			}

			// Create the bounding box for cell identification
			Rectangle boundingBox = new Rectangle(cellX, cellY, previewCellWidth, previewCellWidth);
			gridCellMap.put(boundingBox, cell);
			g.drawImage(cellTileSprite, cellX, cellY, null);
		}

		// SCROLL GRID ---------------------------------------------------|

		// You can only increase the gridRowStartIndex, if the requested rows actually
		// exist. So we don't have to check if the row exists here.
		int gridRowOffset = (int) level.getGrid().getScrollArea().getHeight();

		// Add gridrows. k is the gridY position.
		int k = gridRowOffset - 1;
		for (int i = 0; i < gridRowOffset; i++) {
			GridRow gridRow = level.getGrid().getGridRowList().get(i + gridRowStartIndex);
			gridRow.initializeGridRow(level.getGrid(), (double) k * previewCellWidth);

			for (Tile gridRowTile : gridRow.getTileList()) {
				BufferedImage tileSprite = BDKImageEditor.scale(BDKFileManager.loadImage(gridRowTile.getSpritePath()),
						previewCellWidth, previewCellWidth);

				if (tileSprite == null) {
					tileSprite = missingSpriteImage;
				}

				// Create the bounding box for cell identification
				Rectangle boundingBox = new Rectangle((int) gridRowTile.getPosition().getX(),
						(int) gridRowTile.getPosition().getY(), previewCellWidth, previewCellWidth);
				gridRowTileMap.put(boundingBox, gridRowTile);
				g.drawImage(tileSprite, (int) gridRowTile.getPosition().getX(), (int) gridRowTile.getPosition().getY(),
						null);
			}

			k--;
		}

		// ---------------------------------------------------------------|

	}

	private void addMouseListeners() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				isMousePressed = true;
				mousePressedPoint = e.getPoint();
				indicatorPanel.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedPoint = e.getPoint();
				triggerMouseEvent();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// not used
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// not used
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// not used
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// not used, maybe highlight cells when hovering over them ?
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				isMouseDragged = true;
				mouseDraggedPoint = e.getPoint();
				indicatorPanel.repaint();
			}
		});

		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (bdkLevelEditor.getCurrentLevel() != null) {
					int gridRowOffset = (int) bdkLevelEditor.getCurrentLevel().getGrid().getScrollArea().getHeight();
					int scrollAmount = e.getWheelRotation();
					int newIndex = gridRowStartIndex + scrollAmount;

					// Is the scroll valid ?
					if (newIndex >= 0 && newIndex + gridRowOffset - 1 < bdkLevelEditor.getCurrentLevel().getGrid()
							.getGridRowList().size()) {
							gridRowStartIndex = newIndex;
							redraw = true;
							repaint();
					}
				}
			}
		});
		
		// needed so the keyListener works
		this.grabFocus();
	}

	private void triggerMouseEvent() {
		if (!isMouseDragged) {
			switch (bdkLevelEditor.getCurrentToolName()) {
			case BDKLevelEditor.TOOL_SELECT:
				break;
			case BDKLevelEditor.TOOL_PAINT:
				// Draw call both, even though only one will actually call
				paintGridCell(getGridCellInCoordinate(mousePressedPoint));
				paintTile(getTileInCoordinate(mousePressedPoint));
				break;
			default:
				break;
			}
		} else {
			switch (bdkLevelEditor.getCurrentToolName()) {
			case BDKLevelEditor.TOOL_SELECT:
				break;
			case BDKLevelEditor.TOOL_PAINT:
				getGridCellsInArea(getSelectionRectangle()).parallelStream().forEach(this::paintGridCell);
				getTilesInArea(getSelectionRectangle()).parallelStream().forEach(this::paintTile);
				break;
			default:
				break;
			}
		}

		// reset booleans
		isMouseDragged = false;
		isMousePressed = false;

		indicatorPanel.repaint();
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		gridRowStartIndex = 0;
		redraw = true;
		repaint();
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
