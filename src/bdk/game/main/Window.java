package bdk.game.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.logging.Level;

import javax.swing.JFrame;

import bdk.cfg.WindowConfig;
import bdk.input.BdkInputListener;
import bdk.util.BdkFileManager;
import bdk.util.graphics.Vector2D;

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
public class Window extends JFrame {
	private static final long serialVersionUID = 4077895023401843782L;
	
	// ---------------------------------------------------------------------------------|
	private WindowConfig windowConfig;
	// ---------------------------------------------------------------------------------|
	private static Dimension realDimension = new Dimension(512, 288);
	private static Dimension defaultDimension = new Dimension(1280, 720);
	private static Dimension fullScreenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static Vector2D scalingVector = new Vector2D();
	// ---------------------------------------------------------------------------------|
	private static Window window;
	private static Game game;
	// ---------------------------------------------------------------------------------|

	/**
	 * Creates a game-window instance. Reads the required dimension from the window
	 * config and adds a game of that dimension to its Frame and starts the game.
	 * Also input listeners are attached to the window.
	 */
	public Window() {
		// Load the window config
		try {
			this.windowConfig = (WindowConfig) BdkFileManager.loadSerializedObject(WindowConfig.FILEPATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.SEVERE, "Cannot find window-config in path: " + WindowConfig.FILEPATH, e);
		}

		// Create the game with the given dimension
		Dimension gameDimension = (this.windowConfig.isFullScreen()) ? fullScreenDimension : defaultDimension;
		scalingVector.setX(gameDimension.getWidth() / realDimension.getWidth());
		scalingVector.setY(gameDimension.getHeight() / realDimension.getHeight());

		// We don't support windowed mode or resizing
		this.setResizable(false);
		this.setUndecorated(true);

		// Add the game to the frame and pack the frame around the canvas
		game = new Game(gameDimension);
		this.add(game);
		this.pack();
		window = this;

		// Start the show
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		game.startGame();
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public static Dimension getRealDimension() {
		return realDimension;
	}

	public static void setRealDimension(Dimension realDimension) {
		Window.realDimension = realDimension;
	}

	public static Dimension getDefaultDimension() {
		return defaultDimension;
	}

	public static void setDefaultDimension(Dimension defaultDimension) {
		Window.defaultDimension = defaultDimension;
	}

	public static Vector2D getScalingVector() {
		return scalingVector;
	}

	public static void setScalingVector(Vector2D scalingVector) {
		Window.scalingVector = scalingVector;
	}

	public static void setFullScreenDimension(Dimension fullScreenDimension) {
		Window.fullScreenDimension = fullScreenDimension;
	}

	public static Dimension getFullScreenDimension() {
		return fullScreenDimension;
	}

	public static Window getWindow() {
		return window;
	}

	public static void setWindow(Window window) {
		Window.window = window;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Window.game = game;
	}

	// ---------------------------------------------------------------------------------|

	// Main program entry point
	public static void main(String[] args) {
		window = new Window();
	}
}
