package engine.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import objects.gameobjects.Sprite;

public class Level {
	// Pixels per tick
	private static final int SCROLLSPEED = 2;

	private int levelstate = 0;
	private int background1x = 0;
	private int background1y;
	private int background2x = 0;
	private int background2y;
	private int leveltime = 0;
	private static final int TIMERTICKRATE = 250;
	private boolean isLoaded = false;
	private Image background1;
	private Image background2;
	private Game game;
	private Set<Sprite> spriteset = new HashSet<>();

	public Level(Game game) {
		// Setup level...
		this.game = game;
		// levelTimer.setInitialDelay(2000);
	}

	public void render(Graphics g) {
		// Render Background
		renderBackground(levelstate, g);
		// Render Sprites
		renderSprites(g);
	}

	private void renderBackground(int levelstate, Graphics g) {
		// Load background if needed
		if (!isLoaded) {
			try {
				background1 = ImageIO.read(new File("res/images/level1background.bmp"));
				background1y = 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				background2 = ImageIO.read(new File("res/images/level1background.bmp"));
				background2y = -500;
			} catch (Exception e) {
				e.printStackTrace();
			}
			isLoaded = true;
		}
		// Draw Background
		g.drawImage(background1, background1x, background1y, null);
		g.drawImage(background2, background2x, background2y, null);
		// Check if background has to reset position
		if (background1y == 500) {
			background1y = -500;
		} else if (background2y == 500) {
			background2y = -500;
		}

	}

	private void renderSprites(Graphics g) {
		// spriteset.forEach(Sprite -> render(g));
		for (Sprite s : spriteset) {
			s.render(g);
		}
	}

	public void update() {
		// Check if leveltimer adds new stuff
		LevelTimer.checkLevelTimer(levelstate, leveltime, this);
		// Update Background first
		updateBackground();
		// then update Sprites !!!
		updateSprites();
	}

	private void updateBackground() {
		background1y += SCROLLSPEED;
		background2y += SCROLLSPEED;
	}

	private void updateSprites() {
		for (Sprite s : spriteset) {
			s.update();
		}
	}

	public void startLevelTimer() {
		levelTimer.start();
	}

	public void stopLevelTimer() {
		levelTimer.stop();
	}

	protected Timer levelTimer = new Timer(TIMERTICKRATE, new ActionListener() {
		@Override
		/**
		 * Handle the action event
		 */
		public void actionPerformed(ActionEvent e) {
			leveltime++;
		}
	});

	public int getLevelstate() {
		return levelstate;
	}

	public void setLevelstate(int levelstate) {
		this.levelstate = levelstate;
		levelTimer.restart();
	}

	public Set<Sprite> getSpriteset() {
		return spriteset;
	}

}
