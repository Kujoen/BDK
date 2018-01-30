package bdk.game.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import bdk.data.FileUtil;
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
	// ---------------------------------------------------------------------------------|
	// FINALS--------------------------------------------------------------------------|
	private static final int HD_WIDTH = 1280;
	private static final int HD_HEIGHT = 720;
	private static WindowInfo windowInfo;
	private static Window window;
	private static Game game;
	// ---------------------------------------------------------------------------------|

	public static void main(String[] args) {
		
		//Currently not used since we restrict window to HD windowed mode
		//windowInfo = (WindowInfo)FileUtil.loadSerializedObject(WindowInfo.PATH);
		
		if(args.length == 0){
			window = new Window(HD_WIDTH, HD_HEIGHT);
		} else {
			for(String x : args){
				switch(x){
				case "preview_leveleditor":
					window = new Window(HD_WIDTH, HD_HEIGHT);
				}
			}
		}
	}
	
	public Window(int width, int height){
		//Setup the window and the canvas
		this.setTitle("BDK engine window");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		game = new Game(Game.PREVIEW_NONE);

		this.setUndecorated(false);
		this.add(game);
		this.pack();
		this.setVisible(true);	
		
		game.startGame();		
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public static Dimension getScreenSize() {
		return screenSize;
	}

	public static Window getWindow() {
		return window;
	}

	public static void setWindow(Window window) {
		Window.window = window;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Window.game = game;
	}

	public static int getHdWidth() {
		return HD_WIDTH;
	}

	public static int getHdHeight() {
		return HD_HEIGHT;
	}
}
