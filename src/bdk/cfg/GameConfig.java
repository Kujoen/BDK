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

	// -------------------------------------------------------------------------------|
	// GAME PATHS
	//
	// Except the config path, all other paths are dependent from the GameConfig
	// -------------------------------------------------------------------------------|

	public static final String CONFIG_PATH = "cfg/game.cfg";
	
	public final String LEVEL_PATH = this.get;

	// -------------------------------------------------------------------------------|
	// GAMEINFO KEYS
	// -------------------------------------------------------------------------------|
	public static final String GAMENAME = "name";
	public static final String GAMECREATOR = "creator";

	// Contains information about the game stored in string keys
	private Map<String, String> gameInfo;

	// Contains a level-charter
	private List<String> levelInfo;

	// Contains a menu-charter
	private List<String> menuInfo;

	// -----------------------------------------------------------------|

	public GameConfig() {
		gameInfo = new HashMap<>();

		// Initialized with default values
		gameInfo.put(GAMENAME, "defaultgame");
		gameInfo.put(GAMECREATOR, "Soliture");

		// Initialize empty lists
		levelInfo = new ArrayList<>();
		menuInfo = new ArrayList<>();
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public Map<String, String> getGameInfo() {
		return gameInfo;
	}

	public List<String> getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(List<String> levelInfo) {
		this.levelInfo = levelInfo;
	}

	public List<String> getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(List<String> menuInfo) {
		this.menuInfo = menuInfo;
	}

	// --------------------------------------------------------------------------------------|

	// GameInfo creation, currently must be run when a new gameinfo is required.
	public static void main(String[] args) {
		try {
			BdkFileManager.saveSerializableObject(new GameConfig(), FILEPATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.WARNING, "Couldn't create game info in: " + FILEPATH, e);
		}
	}

}
