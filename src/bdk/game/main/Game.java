package bdk.game.main;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.logging.Level;

import bdk.data.GameInfo;
import bdk.editor.actor.BdkActorEditor;
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
	public static final int HD_PLAY_WIDTH = 520;
	public static final int HD_PLAY_HEIGHT = 600;
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	private boolean isRunning = false;
	private static final long serialVersionUID = 1L;
	private static final double TICKRATE = 60.0;
	private Thread thread;
	//--Components
	private ArrayList<Level> levelList;
	private ArrayList<Menu>	menuList;
	
	//--------------------------------------------------|
	
	/**
	 * Constructor used by the BdkParticleEditor to use Game to render a actorsystem preview
	 */
	public Game(){
		
	}
	
	/**
	 * Refreshes the data (Used by the editor)
	 */
	public void notifyDataChanged(){
		
	}
	
	//------------------------------------------------------------------------------|
	// RUN METHOD
	//------------------------------------------------------------------------------|
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

	@Override
	public void run() {
		long previoustime = System.nanoTime();
		double nanosecond = 1000000000 / TICKRATE;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
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
	 * Calls the update method of all gamecomponents, which call the updatemethod of all their entitys.
	 * Depending on the current gamesetting update may only be called on request.
	 */
	public void update() {

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
