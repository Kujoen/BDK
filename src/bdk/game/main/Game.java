package bdk.game.main;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.Arrays;
import java.util.Map;

import bdk.data.FileUtil;
import bdk.data.GameInfo;
import bdk.editor.util.BdkException;
import bdk.game.component.Component;
import bdk.game.component.Level;
import bdk.game.component.Menu;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas, which is displayed on a JFrame
 * 
 * @author Soliture
 *
 */
public class Game extends Canvas implements Runnable {

	private static GameInfo gameInfo;
	// ----------------------------------------------------------------------------------------|
	// The default gameState is 0 (aka the main menu)
	private int gameState = 0;
	private boolean hasStateTransitioned = false;
	// ----------------------------------------------------------------------------------------|
	// --Used to store essential dimensions
	public static final int HD_PLAY_WIDTH = 520;
	public static final int HD_PLAY_HEIGHT = 600;
	// ----------------------------------------------------------------------------------------|
	// Used for rendering/updating control
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	private boolean isRunning = false;
	private boolean isPaused = false;
	private static final long serialVersionUID = 1L;
	private static final double TICKRATE = 60.0;
	private Thread gameThread;
	// ----------------------------------------------------------------------------------------|
	// --Components
	private Map<String, Component> componentCache;

	private Level currentLevel;
	private Menu currentMenu;
	// ----------------------------------------------------------------------------------------|

	public Game() {
		this.gameInfo = (GameInfo) FileUtil.loadSerializedObject(GameInfo.FILEPATH);
	}

	// ------------------------------------------------------------------------------|
	// GENERAL METHODS
	// ------------------------------------------------------------------------------|

	/**
	 * Sets the gamestate to the new state. Also saves the previous game state;
	 * 
	 * @param newState
	 */
	private void transition(int newState, boolean isNewStateLevel) {
		previousGameState = gameState;
		gameState = newState;
	}

	/**
	 * Initializes the levels/menus based on the gameinfo and previewCode. Called by
	 * the run thread after startGame().
	 */
	private void initializeGame() {

		// Level loading --------------------------------------------------------|
		// Grab all files in the level directory
		File levelDir = new File(gameInfo.getGameInfo().get("NAME") + "/defaultgame/level/");
		File[] listOfLevelFiles = levelDir.listFiles();
		// Throw out any without .bdkl file type and levels not in the gameinfo
		listOfLevelFiles = (File[]) Arrays.stream(listOfLevelFiles).filter(
				file -> (file.getName().contains(".bdkl") && gameInfo.getGameInfo().containsValue(file.getName())))
				.toArray();
		// Move the levels into the level-list
		Arrays.stream(listOfLevelFiles).forEach(file -> {
			Level levelToAdd = (Level) FileUtil.loadSerializedObject(file.getAbsolutePath());
			componentCache.put(levelToAdd.getComponentName(), levelToAdd);
		});
		// Menu loading----------------------------------------------------------|
		File menuDir = new File(gameInfo.getGameInfo().get("NAME") + "/defaultgame/menu/");
		File[] listOfMenuFiles = menuDir.listFiles();
		// Throw out any without .bdkl file type and menus not in the gameinfo
		listOfMenuFiles = (File[]) Arrays.stream(listOfMenuFiles).filter(
				file -> (file.getName().contains(".bdkm") && gameInfo.getGameInfo().containsValue(file.getName())))
				.toArray();
		// Move the levels into the level-list
		Arrays.stream(listOfMenuFiles).forEach(file -> {
			Menu menuToAdd = (Menu) FileUtil.loadSerializedObject(file.getAbsolutePath());
			componentCache.put(menuToAdd.getComponentName(), menuToAdd);
		});
		// ----------------------------------------------------------------------|
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
		gameThread.interrupt();

		try {
			gameThread.join(1000);

			// After the gameThread has stopped, remove the running flag
			isRunning = false;
		} catch (InterruptedException e) {
			BdkException.throwWithMessage("Timed out waiting for game thread to stop");
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
		checkForStateTransition();
		updateGameComponents();
	}

	private void checkForStateTransition() {
		if (hasStateTransitioned) {
			// ...
		}
	}

	private void updateGameComponents() {

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

	public static GameInfo getGameInfo() {
		return gameInfo;
	}

	public static void setGameInfo(GameInfo gameInfo) {
		Game.gameInfo = gameInfo;
	}

	// -----------------------------------------------------------------------------|
	// HARDCODED-GAME
	// -----------------------------------------------------------------------------|

}
