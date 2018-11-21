package bdk.game.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTree;

import bdk.data.WindowInfo;

/**
 * The GameWindow containing everything, contains Methods to control what is
 * currently being displayed
 * 
 * @author Soliture
 *
 */

public class Window extends JFrame {

	// UI------------------------------------------------------------------------------|
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//private static WindowInfo windowInfo;
	// --------------------------------------------------------------------------------|
	// FINALS--------------------------------------------------------------------------|
	private static final int HD_WIDTH = 1280;
	private static final int HD_HEIGHT = 720;
	
	// VISUAL INSTANCES-----------------------------------------------------------------|
	private static Window window;
	private static Game game;
	// ---------------------------------------------------------------------------------|

	public static void main(String[] args) {
		
		//Currently not used since we restrict window to HD windowed mode
		//windowInfo = (WindowInfo)FileUtil.loadSerializedObject(WindowInfo.PATH);
		
		window = new Window(HD_WIDTH, HD_HEIGHT);
	}
	
	public Window(int width, int height){
		//Setup the window and the game
		this.setTitle("BDK engine window");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		game = new Game();

		this.setUndecorated(false);
		this.add(game);
		this.setVisible(true);	
		
		game.startGame();		
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public static Dimension getScreenSize() {
		return screenSize;
	}

	/*
	 * Returns the current Window instance
	 */
	public static Window getWindow() {
		return window;
	}

	public static void setWindow(Window window) {
		Window.window = window;
	}

	/*
	 * Return the game instance being displayed in the Window
	 */
	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Window.game = game;
	}

	/*
	 * Returns the current value for the windows default Width, which is the width of HD -> 1280
	 */
	public static int getDefaultWidth() {
		return HD_WIDTH;
	}

	/*
	 * Returns the current value for the windows default Width, which is the height of HD -> 720
	 */
	public static int getDefaultHeight() {
		return HD_HEIGHT;
	}
}
