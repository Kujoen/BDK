package bdk.editor.actor.previewpanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorCollection;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas wich is displayed on a JFrame
 * 
 * @author Soliture
 *
 */
public class PreviewGame extends Canvas implements Runnable {
	// ----------------------------------------------------------------------------------------|
	// --Store essential dimensions
	public static final int HD_PLAY_WIDTH = 520;
	public static final int HD_PLAY_HEIGHT = 600;
	// ----------------------------------------------------------------------------------------|
	// Rendering/Updating control
	private int updatesPerSecond = 0;
	private static final double TICKRATE = 60.0;
	private boolean isRunning = false;
	// ----------------------------------------------------------------------------------------|
	private static final long serialVersionUID = 1L;
	private Thread gameThread;
	// ----------------------------------------------------------------------------------------|
	// --Actor-Components to preview
	// We need the actor-collection since the currentActor could have child entities
	private ActorCollection currentActorCollection;
	private Actor currentActor;
	// ----------------------------------------------------------------------------------------|
	
	public PreviewGame() {
		this.setSize(new Dimension(HD_PLAY_WIDTH, HD_PLAY_HEIGHT));
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
			gameThread = new Thread(this);
			gameThread.start();
		}
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
		
		// Values for the main game loop ---------------------------|
		long initialTime = System.nanoTime();
		
		final double nanosecondsPerUpdate = 1000000000 / TICKRATE;
		double delta = 0;
		// ----------------------------------------------------------|

		while (isRunning) {
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
	

	
	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|


	public void update() {
		
	}
	
	/**
	 * Called by BDKEditors
	 */
	public void notifyDataChanged() {
	
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

		// Draw here --------------|
		g.setColor(Color.RED);
		g.fillRect(0,0,HD_PLAY_WIDTH, HD_PLAY_HEIGHT);
		// ------------------------|
		
		g.dispose();
		bs.show();
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

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
