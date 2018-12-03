package bdk.game.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import bdk.cfg.GameConfig;
import bdk.game.component.Component;
import bdk.input.BdkInputListener;
import bdk.util.BdkFileManager;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas, which is displayed on a JFrame. The game is operating on
 * the dimension 512 x 288. This makes both width and height divisible by 16 and
 * 32. Per default, this is then scaled up to 1280 x 720.
 * 
 * @author Soliture
 *
 */
public class Game extends Canvas implements Runnable {

	private static final Logger LOGGER = Logger.getLogger("BDKGameLogger");

	// 512 x 288
	// ---------------------------------------------------------------------------------|
	private GameConfig gameConfig;
	// ---------------------------------------------------------------------------------|
	// Used for rendering/updating control
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	private boolean isRunning = false;
	private boolean isPaused = false;
	private static final long serialVersionUID = 1L;
	private static final double TICKRATE = 60.0;
	private transient Thread gameThread;
	// ---------------------------------------------------------------------------------|
	// --Components
	private Map<String, Component> componentCache;
	// ---------------------------------------------------------------------------------|
	private transient BdkInputListener inputListener;
	// ---------------------------------------------------------------------------------|

	/**
	 * The Game object is a runnable canvas. The run thread renders the 'Game' on
	 * the canvas.
	 * 
	 * @param gameDimension The dimension of the game canvas
	 */
	public Game(Dimension gameDimension) {
		try {
			this.gameConfig = (GameConfig) BdkFileManager.loadSerializedObject(GameConfig.CONFIG_PATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.SEVERE, "Unable to open game config at: " + GameConfig.CONFIG_PATH, e);
		}

		// We are packing the window around the canvas, therefore set pref. size
		this.setPreferredSize(gameDimension);
		this.setSize(gameDimension);

		// Attach the listeners
		this.inputListener = new BdkInputListener();
		this.addKeyListener(this.inputListener.getKeyInputListener());
		this.addMouseListener(this.inputListener.getMouseInputListener());
	}

	// ------------------------------------------------------------------------------|
	// GENERAL METHODS
	// ------------------------------------------------------------------------------|

	/**
	 * Initializes the levels/menus based on the gameinfo and previewCode. Called by
	 * the run thread after startGame().
	 */
	private void initializeGame() {

	}

	// ------------------------------------------------------------------------------|
	// RUN CONTROL METHODS
	// ------------------------------------------------------------------------------|

	/**
	 * Starts a new game thread if there is no other game thread running
	 */
	public void startGame() {
		if (!isRunning) {
			isRunning = true;
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	/**
	 * Stops the game thread and removes the running flag
	 */
	public void stopGame() {
		this.pauseGame();
		this.isRunning = false;

		try {
			gameThread.join(1000);
		} catch (InterruptedException e) {
			Game.getLogger().log(Level.SEVERE, "Timed out waiting for game thread to stop", e);
		}
	}

	/**
	 * Causes the main game loop to skip
	 */
	public void pauseGame() {
		isPaused = true;
	}

	/**
	 * Resume rendering/updating
	 */
	public void resumeGame() {
		isPaused = false;
	}

	@Override
	public void run() {
		// MAIN GAME LOOP
		// --------------------------------------------------------------------------|

		// Values for the main game loop ---------------------------|
		long initialTime = System.nanoTime();

		final double nanosecondsPerUpdate = 1000000000 / TICKRATE;
		double delta = 0;
		// ----------------------------------------------------------|
		// Pack the update and rendering operations in two while loops to allow pausing
		while (isRunning) {
			while (!isPaused) {
				long currenttime = System.nanoTime();
				delta += (currenttime - initialTime) / nanosecondsPerUpdate;
				initialTime = currenttime;

				if (delta > 1) {
					update();
					delta--;
				}
				render();
			}
		}
		// ----------------------------------------------------------------------------------------|
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	/**
	 * Calls the update method of all gamecomponents, which call the update method
	 * of all their entities. Depending on the current previewcode update may only
	 * be called on request (Therefore public).
	 */
	public void update() {
		// Currently preview/non-preview update the game the same. This could change
		// though.
		updateGame();
	}

	/**
	 * Update hierarchy: StateTranstion -> Components
	 */
	private void updateGame() {

	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|
	/**
	 * Main rendering method. Call count currently not limited.
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.dispose();
		bs.show();
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public int getFramesPerSecond() {
		return framesPerSecond;
	}

	public void setFramesPerSecond(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public int getUpdatesPerSecond() {
		return updatesPerSecond;
	}

	public void setUpdatesPerSecond(int updatesPerSecond) {
		this.updatesPerSecond = updatesPerSecond;
	}

	public static double getTickrate() {
		return TICKRATE;
	}

	public GameConfig getGameInfo() {
		return gameConfig;
	}

	public void setGameInfo(GameConfig gameInfo) {
		this.gameConfig = gameInfo;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public BdkInputListener getInputListener() {
		return inputListener;
	}

	public void setInputListener(BdkInputListener inputListener) {
		this.inputListener = inputListener;
	}

	// -----------------------------------------------------------------------------|
	// HARDCODED-GAME
	// -----------------------------------------------------------------------------|

}
