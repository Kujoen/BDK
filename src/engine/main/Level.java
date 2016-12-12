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
	// ENGINEOBJECTS-------------------------------------------|
	private Game game;
	// --------------------------------------------------------|
	// GAMEOBJECTS---------------------------------------------|
	private Image imgBackgroundFragment1;
	private Image imgBackgroundFragment2;
	private Player player;
	private ArrayList<Sprite> spritelist;
	// --------------------------------------------------------|
	// INT-----------------------------------------------------|
	private int backgroundFragment1x = 0;
	private int backgroundFragment1y;
	private int backgroundFragment2x = 0;
	private int backgroundFragment2y;
	private int projectileTickCount = 0;
	private int levelFileReaderTickCount = 0;
	// --------------------------------------------------------|
	// BOOLEAN-------------------------------------------------|
	private boolean isLoaded;
	// --------------------------------------------------------|
	// FINALS--------------------------------------------------|
	private final int BACKGROUND_SCROLLSPEED = 2;
	private final int PLAYER_VEL = 5;
	private final int PLAYER_HEALTH = 1;
	private final int DEFAULT_LEVEL = 0;
	// --------------------------------------------------------|

	public Level(Game game) {
		this.game = game;
		this.player = null;
		this.spritelist = new ArrayList<>();
		this.isLoaded = false;

		// Add the player to the spriteList
		this.player = new Player(new Vector2D(game.getWindow().GAMEHEIGHT / 2, game.getWindow().getHeight() / 2),
				PLAYER_VEL, PLAYER_VEL, PLAYER_HEALTH, ObjectID.PLAYER);
		spritelist.add(player);

		// Load the level-file
		this.loadLevel(DEFAULT_LEVEL);
	}

	/**
	 * Loads the level file wich will be used in the updateLevel method.
	 * If Level is currently already running a level, the previous ones data will be wiped.
	 * 
	 * @param levelID
	 */
	private void loadLevel(int levelID) {
		switch (levelID) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		}
	}

	// RENDERING--------------------------------------------------------------------|
	/**
	 * Renders the background and then the sprites
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		renderBackground(g);
		renderSprites(g);
	}

	/**
	 * Draws the background. Contains logic to loop the background
	 * 
	 * @param g
	 */
	private void renderBackground(Graphics g) {
		// Load background
		if (!isLoaded) {
			try {
				imgBackgroundFragment1 = ImageIO.read(new File("res/images/level1background.bmp"));
				backgroundFragment1y = 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				imgBackgroundFragment2 = ImageIO.read(new File("res/images/level1background.bmp"));
				backgroundFragment2y = -Window.GAMEHEIGHT;
			} catch (Exception e) {
				e.printStackTrace();
			}
			isLoaded = true;
		}
		// Draw Background
		g.drawImage(imgBackgroundFragment1, backgroundFragment1x, backgroundFragment1y, null);
		g.drawImage(imgBackgroundFragment2, backgroundFragment2x, backgroundFragment2y, null);
		// Check if background has to reset position
		if (backgroundFragment1y == Window.GAMEHEIGHT) {
			backgroundFragment1y = -Window.GAMEHEIGHT;
		} else if (backgroundFragment2y == Window.GAMEHEIGHT) {
			backgroundFragment2y = -Window.GAMEHEIGHT;
		}
	}

	/**
	 * Calls the render method in every sprite
	 * 
	 * @param g
	 */
	private void renderSprites(Graphics g) {
		for (Sprite s : spritelist) {
			s.render(g);
		}
	}

	// -----------------------------------------------------------------------------|
	// UPDATING---------------------------------------------------------------------|
	/**
	 * Checks if the levelFile/player is spawning sprites, then updates the background,
	 * followed by the sprite positions, and then the sprite count
	 */
	public void update() {
		// Check if the levelFile(AI) is spawning in Sprites
		updateLevel();

		// Check in Input if the player is spawning Sprites
		checkForPlayer();

		updateBackground();
		updateSpritePosition();
		updateSpriteCount();
	}

	/**
	 * Reads data from the loaded levelfile. It reads one line every 60 ticks.
	 * If it detects a new sprite creation request, it will read all lines
	 * needed to create the sprite. The levelfile acts as the AI spawner in this
	 * game. We use an external file to make changing what spawns easy. To read
	 * more about how the levelfiles work read the ReadMe.
	 */
	private void updateLevel() {
		if(levelFileReaderTickCount % 60 == 0){
			
			
			
			levelFileReaderTickCount++;
		}
		levelFileReaderTickCount++;
	}

	/**
	 * Checks if player is pressing spacebbar, if yes it will spawn a projectile
	 * at the players position
	 */
	private void checkForPlayer() {
		if (player == null) {
			if (!spritelist.isEmpty()) {
				player = (Player) spritelist.get(0);
			}
		}
		if (Input.isSpacebar()) {
			if (projectileTickCount % 5 == 0) {
				spritelist.add(new Projectile(new Vector2D(player.getPosition().getX(), player.getPosition().getY()), 0,
						10, 0, ObjectID.PROJECTILE));
				projectileTickCount++;
			}
		}
		projectileTickCount++;
	}

	/**
	 * moves the background
	 */
	private void updateBackground() {
		backgroundFragment1y += BACKGROUND_SCROLLSPEED;
		backgroundFragment2y += BACKGROUND_SCROLLSPEED;
	}

	/**
	 * calls the update method is all sprites
	 */
	private void updateSpritePosition() {
		for (Sprite s : spritelist) {
			s.update();
		}
	}

	/**
	 * adds out-of-bounds sprites to a delete-list and then removes those
	 * objects from the spritelist
	 */
	private void updateSpriteCount() {
		ArrayList<Sprite> removelist = new ArrayList<>();
		for (Sprite s : spritelist) {
			if (s instanceof Projectile && s.getPosition().getY() < 0) {
				removelist.add(s);
			}
		}
		spritelist.removeAll(removelist);
	}

	// ------------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// ------------------------------------------------------------------------------|
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Sprite> getSpritelist() {
		return spritelist;
	}

	public void setSpritelist(ArrayList<Sprite> spritelist) {
		this.spritelist = spritelist;
	}

	public int getProjectileTickCount() {
		return projectileTickCount;
	}

	public void setProjectileTickCount(int projectileTickCount) {
		this.projectileTickCount = projectileTickCount;
	}

	public int getLevelFileReaderTickCount() {
		return levelFileReaderTickCount;
	}

	public void setLevelFileReaderTickCount(int levelFileReaderTickCount) {
		this.levelFileReaderTickCount = levelFileReaderTickCount;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	// -----------------------------------------------------------------------------|
}
