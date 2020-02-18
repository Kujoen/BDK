package bdk.cfg;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;

import bdk.game.main.Game;
import bdk.util.BDKFileManager;

/**
 * 
 * @author Soliture
 * 
 *         Contains critical data the system requires to run. This includes the
 *         game name, which is grabbed to set all default file locations. The
 *         game name MUST be the name of the directory the game is in.
 *         Additionally it holds the level and menu charter, which the game uses
 *         to decide which menu/level to load first/next.
 * 
 */
public class GameConfig implements Serializable {
	private static final long serialVersionUID = -1308671429590296918L;
	
	// -------------------------------------------------------------------------------|
	// GAME INFORMATION
	// -------------------------------------------------------------------------------|
	
	private String gameName;
	private String gameCreator;
	
	private ArrayList<bdk.game.component.level.Level> levelList;

	// -------------------------------------------------------------------------------|
	// GAME PATHS
	//
	// Except the config path, all other paths are dependent from the GameConfig
	// -------------------------------------------------------------------------------|

	public static final String CONFIG_PATH = "cfg/game.cfg";
	
	private String levelPath;
	private String menuPath;

	// -----------------------------------------------------------------|

	public GameConfig() {
		this.gameName = "defaultgame";
		this.gameCreator = "kujoen";
		this.levelList = new ArrayList<>();
	}
	
	public static GameConfig loadGameConfig() throws FileNotFoundException {
		return (GameConfig) BDKFileManager.loadSerializedObject(CONFIG_PATH);
	}
	
	public void saveGameConfig() throws FileNotFoundException {
		BDKFileManager.saveSerializableObject(this, CONFIG_PATH);
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
		
		// Setting the paths to null forces them to update on the next get
		this.levelPath = null;
		this.menuPath = null;
	}

	public String getGameCreator() {
		return gameCreator;
	}

	public void setGameCreator(String gameCreator) {
		this.gameCreator = gameCreator;
	}
	
	
	public String getLevelPath() {
		if(levelPath == null) {
			levelPath = gameName + "/levels";
		}
		
		return levelPath;
	}

	public String getMenuPath() {
		if(menuPath == null) {
			menuPath = gameName + "/menus";
		}
		
		return menuPath;
	}
	
	public ArrayList<bdk.game.component.level.Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(ArrayList<bdk.game.component.level.Level> levelList) {
		this.levelList = levelList;
	}

	// --------------------------------------------------------------------------------------|

	// GameInfo creation, currently must be run when a new gameinfo is required.
	public static void main(String[] args) {
		try {
			BDKFileManager.saveSerializableObject(new GameConfig(), CONFIG_PATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.WARNING, "Couldn't create game info in: " + CONFIG_PATH, e);
		}
	}

}
