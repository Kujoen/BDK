package bdk.game.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import bdk.game.component.Level;

import bdk.data.GameInfo;
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

	// --States/Codes wich modify/control the game
	public static final int PREVIEW_NONE = 0, PREVIEW_LEVELEDITOR = 1, PREVIEW_ACTOREDITOR = 2;
	private int previewCode;
	// The default gameState is 0 (aka the main menu, wich always has to have the code 0)
	private int gameState = 0;

	
	// --Used to store essential dimensions
	public static final int HD_PLAY_WIDTH = 520;
	public static final int HD_PLAY_HEIGHT = 600;
	
	// --All used for rendering/updating control
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	private boolean isRunning = false;
	private boolean isPaused = false;
	private static final long serialVersionUID = 1L;
	private static final double TICKRATE = 60.0;
	private Thread thread;
	
	// --Components
	private ArrayList<Level> levelList;
	private ArrayList<Menu> menuList;

	// --------------------------------------------------|

	/**
	 * The goto-instructor
	 */
	public Game(int previewCode) {

		this.previewCode = previewCode;

		switch (previewCode) {
		case PREVIEW_NONE:
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
	 * Refreshes the data (Used when in previewmode)
	 */
	public void notifyDataChanged() {
		//TODO: parse the actor
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

	/**
	 * Initializes the levels/menus based on the gameinfo and previewCode
	 */
	private void initializeGame() {

		switch (previewCode) {
		case PREVIEW_ACTOREDITOR:
			levelList = (ArrayList<Level>) Level.getLevelListForActorPreview();
			break;
		}

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

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	/**
	 * Calls the update method of all gamecomponents, which call the updatemethod of
	 * all their entitys. Depending on the current gamesetting update may only be
	 * called on request.
	 */
	public void update() {

		if (previewCode == PREVIEW_ACTOREDITOR) {

		}

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

	// ------------------------------------------------------------------------------|

}
