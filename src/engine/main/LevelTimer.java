package engine.main;

import engine.math.Vector2D;
import objects.gameobjects.ObjectID;
import objects.gameobjects.Player;

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
			level.getSpritelist().add(new Player(new Vector2D(Window.GAMEHEIGHT/2, Window.GAMEWIDTH/2),5, 5, ObjectID.PLAYER));
			break;
		default:
			break;
		}
	}

}
