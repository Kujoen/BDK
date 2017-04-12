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
import objects.IO.LevelFileIO;
import objects.gameobjects.ObjectID;
import objects.gameobjects.Player;
import objects.gameobjects.Projectile;
import objects.gameobjects.Sprite;

public class Level {
	// ENGINEOBJECTS-------------------------------------------|
	private Game game;
	private LevelController levelController;
	// --------------------------------------------------------|
	// GAMEOBJECTS---------------------------------------------|
	private Image imgBackgroundFragment1;
	private Image imgBackgroundFragment2;
	private Player player;
	private ArrayList<Sprite> spriteList = new ArrayList<>();
	private ArrayList<Sprite> removeList;
	private ArrayList<Sprite> spawnList;
	// --------------------------------------------------------|
	// INT-----------------------------------------------------|
	private int backgroundFragment1x = 0;
	private int backgroundFragment1y;
	private int backgroundFragment2x = 0;
	private int backgroundFragment2y;
	private int levelFileReaderTickCount = 0;
	// --------------------------------------------------------|
	// BOOLEAN-------------------------------------------------|
	private boolean isLoaded;
	// --------------------------------------------------------|
	// FINALS--------------------------------------------------|
	public static final int BACKGROUND_SCROLLSPEED = 2;
	public static final int PLAYER_VEL = 5;
	public static final int PLAYER_HEALTH = 1;
	public static final int DEFAULT_LEVEL = 0;
	// --------------------------------------------------------|
	// IO------------------------------------------------------|
	private File levelFile;
	// --------------------------------------------------------|

	public Level(Game game) {
		this.game = game;
		this.player = null;
		this.isLoaded = false;

        //Add the player to the spriteList
		this.player = new Player(
				new Vector2D(game.getWindow().GAMEHEIGHT / 2, game.getWindow().getHeight() / 2),
				new Vector2D(10,10), 
				1, 
				ObjectID.PLAYER,
				"player_placeholder",
				true);
		spriteList.add(player);

		// Load the level-file
		this.loadLevel(DEFAULT_LEVEL);
		
		// Add the levelController
		this.levelController = new LevelController(this);
	}

	/**
	 * Loads the level file wich will be used in the updateLevel method. If
	 * Level is currently already running a level, the previous ones data will
	 * be wiped.
	 * 
	 * @param levelID
	 */
	private void loadLevel(int levelID) {
		switch (levelID) {
		case 0:	
			levelFile = new File("res/data/level1.ltw");
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
		// Render all sprites
		for (Sprite s : spriteList) {
			s.render(g);
		}
	}

	// -----------------------------------------------------------------------------|
	// UPDATING---------------------------------------------------------------------|
	/**
	 * Checks if the levelFile/player is spawning sprites, then updates the
	 * background, followed by the sprite positions, and then the sprite count
	 */
	public void update() {
		// Update the background and the positions of already existing sprites
		updateBackground();
		updateSprites();

		// Update sprite lists
		updateLevelFile();
		updateDeleteRequests();
		updateSpawnRequests();
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
	private void updateSprites() {
		// Update sprite positions
		for (Sprite s : spriteList) {
			s.update();
		}
	}

	/**
	 * Reads data from the loaded levelfile. It reads one line every 60 ticks.
	 * If it detects a new sprite creation request, it will read all lines
	 * needed to create the sprite. The levelfile acts as the AI spawner in this
	 * game. We use an external file to make changing what spawns easy. To read
	 * more about how the levelfiles work read the ReadMe.
	 */
	private void updateLevelFile() {
		if (levelFileReaderTickCount % 60 == 0) {
			try {
				levelController.readNextCommand();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			levelFileReaderTickCount++;
		}
		levelFileReaderTickCount++;
	}

	/**
	 * checks if any sprites have been added to the delete list and removes them
	 * from the main list
	 */
	private void updateDeleteRequests() {
		removeList = new ArrayList<>();

		for (Sprite s : spriteList) {
			removeList.addAll(s.getRequestRemoveList());
			s.getRequestRemoveList().clear();
		}

		if (!removeList.isEmpty()) {
			spriteList.removeAll(removeList);
		}
	}

	/**
	 * checks if any sprites have been added to the spawn list and adds them to
	 * the main list
	 * 
	 * @return
	 */
	private void updateSpawnRequests() {
		spawnList = new ArrayList<>();

		for (Sprite s : spriteList) {
			spawnList.addAll(s.getRequestSpawnList());
			s.getRequestSpawnList().clear();
		}

		if (!spawnList.isEmpty()) {
			spriteList.addAll(spawnList);
		}
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

	public ArrayList<Sprite> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(ArrayList<Sprite> spriteList) {
		this.spriteList = spriteList;
	}

	public ArrayList<Sprite> getRemoveList() {
		return removeList;
	}

	public void setRemoveList(ArrayList<Sprite> removeList) {
		this.removeList = removeList;
	}

	public ArrayList<Sprite> getSpawnList() {
		return spawnList;
	}

	public void setSpawnList(ArrayList<Sprite> spawnList) {
		this.spawnList = spawnList;
	}

	public LevelController getLevelController() {
		return levelController;
	}

	public void setLevelController(LevelController levelController) {
		this.levelController = levelController;
	}

	public File getLevelFile() {
		return levelFile;
	}

	public void setLevelFile(File levelFile) {
		this.levelFile = levelFile;
	}

	// -----------------------------------------------------------------------------|
}
