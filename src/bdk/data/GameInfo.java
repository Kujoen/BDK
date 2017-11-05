package bdk.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Serializable HashMap containing info on the current game.
 * @author Kuj
 *
 */
public class GameInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PATH = "cfg/gameinfo.cfg";

	private Map<String, String> gameInfo;

	/**
	 * Creats a new HashMap and puts in default values. Only use for testing purposes.
	 */
	public GameInfo() {
		gameInfo = new HashMap<String, String>();
		gameInfo.put("NAME", "defaultgame");
		gameInfo.put("CREATOR", "Soliture");
	}
	
	/**
	 * Overwrites the current gameinfo. Only use for testing purposes.
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtil.saveSerializableObject(new GameInfo(), PATH);
	}

	public Map<String, String> getGameInfo() {
		return gameInfo;
	}
}
