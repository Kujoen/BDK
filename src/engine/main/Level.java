package engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.text.html.HTMLDocument.Iterator;

import engine.input.Input;
import engine.math.Vector2D;
import objects.gameobjects.ObjectID;
import objects.gameobjects.Player;
import objects.gameobjects.Projectile;
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
	private int tickcount = 0;
	private static final int TIMERTICKRATE = 250;
	private boolean isLoaded = false;
	private Image background1;
	private Image background2;
	private Game game;
	private Level thislevel;
	private Player player = null;
	private ArrayList<Sprite> spritelist = new ArrayList<>();

	public ArrayList<Sprite> getSpritelist() {
		return spritelist;
	}

	public void setSpritelist(ArrayList<Sprite> spritelist) {
		this.spritelist = spritelist;
	}

	public Level(Game game) {
		// Setup level...
		this.game = game;
		this.thislevel = this;
		// levelTimer.setInitialDelay(2000);
	}

	public void render(Graphics g) {
		// Render Background
		renderBackground(levelstate, g);
		// Render Sprites
		renderSprites(g);
		// debug
		renderDebug(g);
	
	}
	
	private void renderDebug(Graphics g){
		g.setColor(Color.RED);
		g.drawString("Spritecount : " + spritelist.size(), 400, 50);
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
				background2y = -Window.GAMEHEIGHT;
			} catch (Exception e) {
				e.printStackTrace();
			}
			isLoaded = true;
		}
		// Draw Background
		g.drawImage(background1, background1x, background1y, null);
		g.drawImage(background2, background2x, background2y, null);
		// Check if background has to reset position
		if (background1y == Window.GAMEHEIGHT) {
			background1y = -Window.GAMEHEIGHT;
		} else if (background2y == Window.GAMEHEIGHT) {
			background2y = -Window.GAMEHEIGHT;
		}

	}

	private void renderSprites(Graphics g) {
		// spriteset.forEach(Sprite -> render(g));
		for (Sprite s : spritelist) {
			s.render(g);
		}
	}

	public void update() {
		// Check if the player adds new stuff
		checkForPlayer();
		// Update Background first
		updateBackground();
		// Update sprite position
		updateSpritePosition();
		// Update sprite count
		updateSpriteCount();
	}

	private void updateSpriteCount() {
		ArrayList<Sprite> removelist = new ArrayList<>();
		for (Sprite s : spritelist) {
			if (s instanceof Projectile && s.getPosition().getY() < 0) {
				removelist.add(s);
			}
		}
		spritelist.removeAll(removelist);
	}

	private void updateBackground() {
		background1y += SCROLLSPEED;
		background2y += SCROLLSPEED;
	}

	private void updateSpritePosition() {
		for (Sprite s : spritelist) {
			s.update();
		}
	}

	private void checkForPlayer() {
		if (player == null) {
			if (!spritelist.isEmpty()) {
				player = (Player) spritelist.get(0);
			}
		}
		if (Input.isSpacebar()) {
			if (tickcount % 5 == 0) {
				spritelist.add(new Projectile(new Vector2D(player.getPosition().getX(), player.getPosition().getY()), 0,
						10, ObjectID.PROJECTILE));
				tickcount++;
			}
		}
		tickcount++;
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
			// Check if leveltimer adds new stuff
			LevelTimer.checkLevelTimer(levelstate, leveltime, thislevel);
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

}
