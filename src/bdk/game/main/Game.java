package bdk.game.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.Arrays;
import java.util.Map;

import bdk.data.FileUtil;
import bdk.data.GameInfo;
import bdk.game.component.Component;
import bdk.game.component.Level;
import bdk.game.component.Menu;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas wich is displayed on a JFrame
 * 
 * @author Soliture
 *
 */
public class Game extends Canvas implements Runnable {

	private static GameInfo gameInfo;
	// ----------------------------------------------------------------------------------------|
	// --States/Codes wich modify/control the game
	public static final int PREVIEW_NONE = 0, PREVIEW_LEVELEDITOR = 1, PREVIEW_ACTOREDITOR = 2;
	private int previewCode;
	// The default gameState is 0 (aka the main menu)
	private int gameState = 0;
	private int previousGameState = 0;
	private boolean isLevelState = false;
	private boolean hasStateTransitioned = false;
	private boolean drawLoadingScreen = false;
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
	private Thread thread;
	// ----------------------------------------------------------------------------------------|
	// --Components
	private Map<String, Component> componentCache;

	private Level currentLevel;
	private Menu currentMenu;
	// ----------------------------------------------------------------------------------------|

	public Game(int previewCode) {
		this.gameInfo = (GameInfo) FileUtil.loadSerializedObject(GameInfo.FILEPATH);
		this.previewCode = previewCode;

		switch (previewCode) {
		case PREVIEW_NONE:
			this.setPreferredSize(new Dimension(Window.getHdWidth(), Window.getHdHeight()));
			this.setSize(this.getPreferredSize());
			break;
		case PREVIEW_LEVELEDITOR:
			break;
		case PREVIEW_ACTOREDITOR:
			this.setPreferredSize(new Dimension(HD_PLAY_WIDTH, HD_PLAY_HEIGHT));
			this.setSize(this.getPreferredSize());
			break;
		}
	}

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
	 * Called by BDKEditors
	 */
	public void notifyDataChanged() {
	
	}

	// ------------------------------------------------------------------------------|
	// RUN METHOD
	// ------------------------------------------------------------------------------|
	/**
	 * Starts a new game if there is no other gamethread running
	 */
	public synchronized void startGame() {
		if (!isRunning) {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Stops the game if there is a gamethread runnning. CURRENTLY NON FUNCTIONAL
	 */
	public void stopGame() {
		// TODO: Read about java multithreading
	}

	/**
	 * This will stop any rendering/updates from happening until resumed
	 */
	public void pauseGame() {
		isRunning = false;
	}

	/**
	 * Resume rendering/updating
	 */
	public void resumeGame() {
		isRunning = true;
	}

	@Override
	public void run() {
		long previoustime = System.nanoTime();
		double nanosecond = 1000000000 / TICKRATE;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;

		initializeGame();

		while (isRunning) {
			long currenttime = System.nanoTime();
			delta += (currenttime - previoustime) / nanosecond;
			previoustime = currenttime;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				framesPerSecond = frames;
				updatesPerSecond = updates;
				frames = 0;
				updates = 0;
			}
		}
	}
	
	/**
	 * Initializes the levels/menus based on the gameinfo and previewCode. Called by
	 * the run thread after startGame().
	 */
	private void initializeGame() {

		// switch (previewCode) {
		// case PREVIEW_NONE:
		// // Level loading --------------------------------------------------------|
		// // Grab all files in the level directory
		// File levelDir = new File(gameInfo.getGameInfo().get("NAME") +
		// "/defaultgame/level/");
		// File[] listOfLevelFiles = levelDir.listFiles();
		// // Throw out any without .bdkl file type and levels not in the gameinfo
		// listOfLevelFiles = (File[]) Arrays.stream(listOfLevelFiles).filter(
		// file -> (file.getName().contains(".bdkl") &&
		// gameInfo.getGameInfo().containsValue(file.getName())))
		// .toArray();
		// // Move the levels into the level-list
		// Arrays.stream(listOfLevelFiles).forEach(file -> {
		// Level levelToAdd = (Level)
		// FileUtil.loadSerializedObject(file.getAbsolutePath());
		// componentCache.put(levelToAdd.getComponentName(), levelToAdd);
		// });
		// // Menu loading----------------------------------------------------------|
		// File menuDir = new File(gameInfo.getGameInfo().get("NAME") +
		// "/defaultgame/menu/");
		// File[] listOfMenuFiles = menuDir.listFiles();
		// // Throw out any without .bdkl file type and menus not in the gameinfo
		// listOfMenuFiles = (File[]) Arrays.stream(listOfMenuFiles).filter(
		// file -> (file.getName().contains(".bdkm") &&
		// gameInfo.getGameInfo().containsValue(file.getName())))
		// .toArray();
		// // Move the levels into the level-list
		// Arrays.stream(listOfMenuFiles).forEach(file -> {
		// Menu menuToAdd = (Menu)
		// FileUtil.loadSerializedObject(file.getAbsolutePath());
		// componentCache.put(menuToAdd.getComponentName(), menuToAdd);
		// });
		// // ----------------------------------------------------------------------|
		// // Makes sure the main menu is loaded
		// transition(0, false);
		// break;
		// case PREVIEW_LEVELEDITOR:
		// break;
		// case PREVIEW_ACTOREDITOR:
		// break;
		// }
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
	 * Renders the current active gameobject
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

	// ------------------------------------------------------------------------------|

}
