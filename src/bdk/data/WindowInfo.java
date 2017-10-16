package bdk.data;

import java.io.Serializable;

import bdk.game.main.Window;

/**
 * Serializable HashMap containing info on the current game.
 * @author Kuj
 *
 */
public class WindowInfo implements Serializable {
	
	public static final String PATH = "cfg/windowinfo.cfg";
	private boolean isWindowedMode;
	private int windowType;

	public WindowInfo() {
		isWindowedMode = true;
		windowType = Window.HD;
	}
	
	/**
	 * Overwrites the current gameinfo. Only use for testing purposes.
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtil.saveSerializableObject(new WindowInfo(), PATH);
	}

	public boolean isWindowedMode() {
		return isWindowedMode;
	}

	public void setWindowedMode(boolean isWindowedMode) {
		this.isWindowedMode = isWindowedMode;
	}

	public int getWindowType() {
		return windowType;
	}

	public void setWindowType(int windowType) {
		this.windowType = windowType;
	}
}
