package bdk.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import bdk.cfg.GameConfig;
import bdk.game.component.Component;
import bdk.game.component.level.*;
import bdk.input.BdkInputListener;

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

	// ---------------------------------------------------------------------------------|

	private static final Logger LOGGER = Logger.getLogger("BDKGameLogger");

	// ---------------------------------------------------------------------------------|
	private GameConfig gameConfig;
	// ---------------------------------------------------------------------------------|
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	private int frameCounter = 0;
	private int updateCounter = 0;
	private boolean isRunning = false;
	private boolean isPaused = false;
	private static final long serialVersionUID = 1L;
	public static final double TICKRATE = 60.0;
	private transient Thread gameThread;
	// ---------------------------------------------------------------------------------|
	// --Components
	private bdk.game.component.level.Level activeLevel;
	private BDKGameWindow window;
	// ---------------------------------------------------------------------------------|
	private transient BdkInputListener inputListener;
	// ---------------------------------------------------------------------------------|

	/**
	 * The Game object is a runnable canvas. The run thread renders the 'Game' on
	 * the canvas.
	 * 
	 * @param gameDimension The dimension of the game canvas
	 */
	public Game(BDKGameWindow window) {
		this.window = window;
		
		try {
			this.gameConfig = GameConfig.loadGameConfig();
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.SEVERE, "Unable to open game config at: " + GameConfig.CONFIG_PATH, e);
		}

		// We are packing the window around the canvas, therefore set preferred size
		this.setPreferredSize(window.getGameDimension());
		this.setSize(window.getGameDimension());

		// Attach the listeners
		this.inputListener = new BdkInputListener();
		this.addKeyListener(this.inputListener.getKeyInputListener());
		this.addMouseListener(this.inputListener.getMouseInputListener());
	}

	// ------------------------------------------------------------------------------|
	// GENERAL METHODS
	// ------------------------------------------------------------------------------|

	private void initializeGame() {

		// If the level list is empty, add a default empty level
		if (gameConfig.getLevelList().isEmpty()) {
			gameConfig.getLevelList().add(new bdk.game.component.level.Level("default"));
		}

		// Set the first level to active and initialize
		activeLevel = gameConfig.getLevelList().get(0);
		activeLevel.initializeLevel(this);
	}

	// ------------------------------------------------------------------------------|
	// RUN CONTROL METHODS
	// ------------------------------------------------------------------------------|

	/**
	 * Starts a new game thread if there is no other game thread running
	 */
	public void startGame() {
		if (!isRunning) {
			this.initializeGame();

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
		long initialTimeForDebug = System.nanoTime();

		final double nanosecondsPerUpdate = 1000000000 / TICKRATE;
		double delta = 0;
		// ----------------------------------------------------------|
		// Pack the update and rendering operations in two while loops to allow pausing
		while (isRunning) {
			while (!isPaused) {
				long currentTime = System.nanoTime();
				delta += (currentTime - initialTime) / nanosecondsPerUpdate;
				initialTime = currentTime;

				if (delta > 1) {
					update();
					updateCounter++;
					delta--;
				}
				render();
				frameCounter++;

				// For Debug Telemetry, updated every second
				if (currentTime - initialTimeForDebug > 1000000000) {
					framesPerSecond = frameCounter;
					updatesPerSecond = updateCounter;

					frameCounter = 0;
					updateCounter = 0;
					initialTimeForDebug = currentTime;
				}
			}
		}
		// ---------------------------------------------------------------------------|
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {
		// Level Update
		activeLevel.update();
	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		// Clear the screen
		g.setColor(Color.black);
		g.fillRect(0, 0, window.getGameDimension().width, window.getGameDimension().height);

		// Level Render
		activeLevel.render(g);

		// Render Debug Information
		renderGameDebug(g);

		g.dispose();
		bs.show();
	}

	public void renderGameDebug(Graphics2D g) {
		// Draw FPS
		g.setColor(Color.black);
		g.drawString("FPS: " + framesPerSecond, 10, 10);

		// Draw UPS
		g.drawString("UPS: " + updatesPerSecond, 10, 20);

		// Draw Level info
		if (activeLevel != null) {
			g.drawString("Active level: " + activeLevel.getComponentName(), 10, 30);
			g.drawString("TICK: " + activeLevel.getLevelTick(), 10, 40);
			g.drawString("--------CACHED ACTOR SPRITES: ", 10, 50);
			g.drawString("--------CACHED TILES SPRITES: " + activeLevel.getTileSpriteCache().size(), 10, 60);
		}
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

	public GameConfig getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
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

	public BDKGameWindow getWindow() {
		return window;
	}

	public void setWindow(BDKGameWindow window) {
		this.window = window;
	}

	// -----------------------------------------------------------------------------|
	// HARDCODED-GAME
	// -----------------------------------------------------------------------------|

}
