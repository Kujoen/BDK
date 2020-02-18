package bdk.game.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.logging.Level;

import javax.swing.JFrame;

import bdk.cfg.WindowConfig;
import bdk.util.BDKFileManager;
import javafx.geometry.Point2D;

/**
 * The Window holding the game instance. BDK has three essential dimensions. The
 * 'RealDimension' is the size you are to reference when designing
 * sprites/levels. To allow making sprites and levels easier, the
 * 'RealDimension' is only 512x288. The Window then opens to a default 1280x720
 * dimension (standard HD). All sprites are then scaled from the realDimension
 * to the defaultDimension. Since fullscreen is supported, the
 * fullScreenDimension also holds the entire screen size.
 * 
 * @author Soliture
 *
 */
public class BDKGameWindow extends JFrame {
	
	// Main program entry point
	public static void main(String[] args) {
		BDKGameWindow window = new BDKGameWindow();
	}
	
	private static final long serialVersionUID = 4077895023401843782L;
	
	// ---------------------------------------------------------------------------------|
	private WindowConfig windowConfig;
	// ---------------------------------------------------------------------------------|
	private Dimension realDimension = new Dimension(512, 288);
	private Dimension defaultDimension = new Dimension(1280, 720);
	private Dimension fullScreenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Evaluated at runtime
	private Dimension gameDimension;
	private Point2D scalingVector;
	// ---------------------------------------------------------------------------------|
	private Game game;
	// ---------------------------------------------------------------------------------|

	/**
	 * Creates a game-window instance. Reads the required dimension from the window
	 * config and adds a game of that dimension to its Frame and starts the game.
	 * Also input listeners are attached to the window.
	 */
	public BDKGameWindow() {
		// Load the window config
		try {
			this.windowConfig = (WindowConfig) BDKFileManager.loadSerializedObject(WindowConfig.FILEPATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.SEVERE, "Cannot find window-config in path: " + WindowConfig.FILEPATH, e);
		}

		// Create the game with the given dimension
		this.gameDimension = (this.windowConfig.isFullScreen()) ? fullScreenDimension : defaultDimension;
		this.scalingVector = new Point2D(gameDimension.getWidth() / realDimension.getWidth(), gameDimension.getHeight() / realDimension.getHeight());

		// We don't support windowed mode or resizing
		this.setResizable(false);
		this.setUndecorated(true);

		// Add the game to the frame and pack the frame around the canvas
		game = new Game(this);
		this.add(game);
		this.pack();

		// Start the show
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		game.startGame();
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|
	
	public Dimension getGameDimension() {
		return gameDimension;
	}

	public void setGameDimension(Dimension gameDimension) {
		this.gameDimension = gameDimension;
	}

	public Point2D getScalingVector() {
		return scalingVector;
	}

	public void setScalingVector(Point2D scalingVector) {
		this.scalingVector = scalingVector;
	}

	// ---------------------------------------------------------------------------------|
}