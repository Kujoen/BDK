package bdk.cfg;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import bdk.game.main.Game;
import bdk.util.BdkFileManager;

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
	// GAMEINFO INFORMATION
	// -------------------------------------------------------------------------------|
	
	private String gameName = "defaultgame";
	private String gameCreator = "Soliture";
	
	private ArrayList levelList;

	// -------------------------------------------------------------------------------|
	// GAME PATHS
	//
	// Except the config path, all other paths are dependent from the GameConfig
	// -------------------------------------------------------------------------------|

	public static final String CONFIG_PATH = "cfg/game.cfg";
	
	private String levelPath;
	private String menuPath;

	// -----------------------------------------------------------------|

	private GameConfig() {
	}
	
	public static GameConfig loadGameConfig() throws FileNotFoundException {
		return (GameConfig) BdkFileManager.loadSerializedObject(CONFIG_PATH);
	}
	
	public void saveGameConfig() throws FileNotFoundException {
		BdkFileManager.saveSerializableObject(this, CONFIG_PATH);
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|
	
	public String getGameName() {
		return gameName;
	}

	/**
	 * Setting a new game name updates all other paths
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
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
	
	public ArrayList getLevelList() {
		return levelList;
	}

	public void setLevelList(ArrayList levelList) {
		this.levelList = levelList;
	}

	// --------------------------------------------------------------------------------------|

	// GameInfo creation, currently must be run when a new gameinfo is required.
	public static void main(String[] args) {
		try {
			BdkFileManager.saveSerializableObject(new GameConfig(), CONFIG_PATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.WARNING, "Couldn't create game info in: " + CONFIG_PATH, e);
		}
	}

}
