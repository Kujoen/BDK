package engine.main;

import objects.gameobjects.ObjectID;
import objects.gameobjects.Player;
import objects.gameobjects.Sprite;

/**
 * 
 * Using this class to hardcode the levels... dont kill me
 * 
 * @author Soliture
 *
 */

public class LevelTimer {

	public static void checkLevelTimer(int levelstate, int leveltime, Level level) {
		switch (levelstate) {
		case 0:
			updateLevelOne(leveltime, level);
			break;
		default:
			break;
		}
	}

	private static void updateLevelOne(int leveltime, Level level) {
		switch (leveltime) {
		case 1:
			level.getSpriteset().add(new Player(0,0, ObjectID.PLAYER));
			break;
		default:
			break;
		}
	}

}
