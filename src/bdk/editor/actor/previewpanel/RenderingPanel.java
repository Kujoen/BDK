package bdk.editor.actor.previewpanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

import bdk.editor.actor.BDKActorEditor;
import bdk.game.entities.sprites.actors.Actor;
import bdk.util.BDKFileManager;
import bdk.util.graphics.BDKImageEditor;
import javafx.geometry.Point2D;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas which is displayed on a JFrame
 * 
 * @author Soliture
 *
 */
public class RenderingPanel extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	// ----------------------------------------------------------------------------------------|
	// Rendering/Updating control
	private int updatesPerSecond = 0;
	private int framesPerSecond = 0;
	private int updateCounter = 0;
	private int frameCounter = 0;
	private double tickrate = 60.0;
	private boolean isRunning = false;
	// ----------------------------------------------------------------------------------------|
	private Thread gameThread;
	
	private BDKActorEditor bdkActorEditor;
	// ----------------------------------------------------------------------------------------|
	
	public RenderingPanel(BDKActorEditor bdkActorEditor) {
		this.bdkActorEditor = bdkActorEditor;
	}
	
	public void notifyDataChanged(PropertyChangeEvent event) {
		// Ignore events fired by this component
		if(event.getSource() == this) {
			return;
		}
	}
	
	/**
	 * Prepares a actor for preview rendering/updating.
	 * Loads the sprite image, scales it, then places the 
	 * sprite in the center of the preview.
	 */
	private void prepareActor() {
		if(bdkActorEditor.getCurrentActor() != null) {
			Actor targetActor = bdkActorEditor.getCurrentActor();
			BufferedImage targetImage = BDKFileManager.loadImage(targetActor.getSpritePath());
			
			targetActor.reset();
			targetActor.setSpriteImage(BDKImageEditor.scale(targetImage, (int)( targetImage.getWidth() * 2.5), (int) (targetImage.getHeight() * 2.5)));
			targetActor.setPosition(new Point2D((this.getWidth() / 2) -  (targetActor.getSpriteImage().getWidth() / 2), (this.getHeight() / 2) - (targetActor.getSpriteImage().getHeight() / 2) ));
		} else {
			return;
		}
	}
	
	private void clear() {
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.dispose();
	}

	// ------------------------------------------------------------------------------|
	// RUN METHOD
	// ------------------------------------------------------------------------------|
	/**
	 * Starts a new game if there is no other gamethread running
	 */
	public synchronized void startGame() {
		if (!isRunning && bdkActorEditor.getCurrentActor() != null) {
			isRunning = true;
			
			prepareActor();
			
			gameThread = new Thread(this);
			gameThread.start();
		} else {
			return;
		}
	}

	/**
	 * This will stop any rendering/updates
	 */
	public void stopGame() {
		isRunning = false;
		frameCounter = 0;
		updateCounter = 0;
		clear();
	}

	@Override
	public void run() {
		
		// Values for the main game loop ---------------------------|
		long initialTime = System.nanoTime();
		long initialTimeForDebug = System.nanoTime();
		
		final double nanosecondsPerUpdate = 1000000000 / tickrate;
		double delta = 0;
		// ----------------------------------------------------------|

		while (isRunning) {
			long currenttime = System.nanoTime();
			delta += (currenttime - initialTime) / nanosecondsPerUpdate;
			initialTime = currenttime;

			if (delta > 1) {
				update();
				updateCounter++;
				delta--;
			}
			render();
			frameCounter++;
			
			// For Debug Telemetry, updated every second
			if (currenttime - initialTimeForDebug > 1000000000) {
				framesPerSecond = frameCounter;
				updatesPerSecond = updateCounter;

				frameCounter = 0;
				updateCounter = 0;
				initialTimeForDebug = currenttime;
			}
		}
	}
	
	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|

	public void update() {
		bdkActorEditor.getCurrentActor().update();
	}
	
	@Override
	public void paint(Graphics g) {
	}

	// -------------------------------------------------------------------------------|
	// RENDERING
	// -------------------------------------------------------------------------------|
	/**
	 * Renders the the actor preview
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		// CLEAR ------------------|
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		// ------------------------|
		
		// MAIN DRAW --------------|
		bdkActorEditor.getCurrentActor().render(g);
		// ------------------------|		
		
		// DEBUG DRAW -------------|
		g.setColor(Color.RED);
		g.drawString("UPS: " + Integer.toString(updatesPerSecond), 10, 20);
		g.drawString("FPS: " + Integer.toString(framesPerSecond), 10, 40);
		// ------------------------|
		
		g.dispose();
		bs.show();
	}


	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public double getTickrate() {
		return tickrate;
	}

	public void setTickrate(double tickrate) {
		this.tickrate = tickrate;
	}

	// ------------------------------------------------------------------------------|

}
