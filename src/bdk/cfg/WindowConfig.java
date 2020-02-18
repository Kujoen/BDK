package bdk.cfg;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.logging.Level;

import bdk.game.main.Game;
import bdk.util.BDKFileManager;

/**
 * 
 * @author Andreas Farley
 *
 */
public class WindowConfig implements Serializable {
	private static final long serialVersionUID = -4121922165008584013L;

	// ---------------------------------------------------------|

	// Location of the window config
	public static final String FILEPATH = "cfg/window.cfg";

	private boolean isFullScreen = false;

	// ---------------------------------------------------------|

	/**
	 * The window config saves if the user wants fullscreen or the default game size
	 * (1280 x 720)
	 */
	private WindowConfig() {
	}

	/**
	 * Saves the window config to file
	 */
	private void save() {
		try {
			BDKFileManager.saveSerializableObject(this, FILEPATH);
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.WARNING, "Failed saving window config to: " + FILEPATH, e);
		}
	}

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public boolean isFullScreen() {
		return isFullScreen;
	}

	public void setFullScreen(boolean isFullScreen) {
		this.isFullScreen = isFullScreen;
	}

	// ---------------------------------------------------------|

	// GameInfo creation, currently must be run when a new gameinfo is required.
	public static void main(String[] args) {
		new WindowConfig().save();
		Game.getLogger().log(Level.INFO, "Finished saving window config");
	}

}
