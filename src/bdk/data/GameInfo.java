package bdk.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Andreas Farley
 * 
 *         Contains critical data the system requires to run. This includes the
 *         game name, which is grabbed to set all default file locations. The
 *         game name MUST be the name of the directory the game is in.
 *         Additionally it holds the level and menu charter, which the game uses
 *         to decide which menu/level to load first/next.
 * 
 */
public class GameInfo implements Serializable {

	// GameInfo related default-strings --------------------------------|
	
	// Location of the gameinfo
	public static final String FILEPATH = "cfg/gameinfo.cfg";
	
	// Name of the game and game directory
	public static final String GAMENAME = "name";
	// Name of the game creator
	public static final String GAMECREATOR = "creator";
	// Name of the path key
	public static final String GAMEPATH = "path";
	// ---------------------------------------------------------|

	// Contains information about the game
	private Map<String, String> gameInfo;

	// Contains a level-charter
	private ArrayList<String> levelInfo;

	// Contains a menu-charter
	private ArrayList<String> menuInfo;

	// ---------------------------------------------------------|

	public GameInfo() {
		gameInfo = new HashMap<String, String>();
		
		// Currently initialized with default values
		gameInfo.put(GAMEPATH, FILEPATH);
		gameInfo.put(GAMENAME, "defaultgame");
		gameInfo.put(GAMECREATOR, "");
	}

	// GameInfo creation, currently must be run when a new gameinfo is required.
	// WARNING : This will clear the level/menu charter.
	public static void main(String[] args) {
		FileUtil.saveSerializableObject(new GameInfo(), FILEPATH);
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	// GameInfo only has a getter since it should be set on initialisiation and
	// never changed afterwards
	public Map<String, String> getGameInfo() {
		return gameInfo;
	}

	public ArrayList<String> getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(ArrayList<String> levelInfo) {
		this.levelInfo = levelInfo;
	}

	public ArrayList<String> getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(ArrayList<String> menuInfo) {
		this.menuInfo = menuInfo;
	}

}
