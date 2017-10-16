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
	public static final int HD = 0, HD_PLUS = 1, HD_FULL = 2;
	public static final int PREVIEW_NONE = 0, PREVIEW_OE = 1;
	private static final int HD_WIDTH = 1280;
	private static final int HD_HEIGHT = 720;
	private static final int HD_PLUS_WIDTH = 1600;
	private static final int HD_PLUS_HEIGHT = 900;
	private static final int HD_FULL_WIDTH = 1920;
	private static final int HD_FULL_HEIGHT = 1080;
	private static final double FULLSCREEN_HEIGHT = screenSize.getHeight();
	private static final double FULLSCREEN_WIDTH = screenSize.getWidth();
	private static int previewState;
	private static WindowInfo windowInfo;
	private static Window window;
	private static Game game;
	// ---------------------------------------------------------------------------------|

	public static void main(String[] args) {
		windowInfo = (WindowInfo)FileUtil.loadSerializedObject(WindowInfo.PATH);
		
		if(args.length == 0){
			previewState = PREVIEW_NONE;
			
			switch(windowInfo.getWindowType()){
				case HD:
					window = new Window(HD_WIDTH, HD_HEIGHT);
					break;
				case HD_PLUS:
					window = new Window(HD_PLUS_WIDTH, HD_PLUS_HEIGHT);
					break;
				case HD_FULL:
					window = new Window(HD_FULL_WIDTH, HD_FULL_HEIGHT);
			}
		} else {
			for(String x : args){
				switch(x){
				case "preview_objecteditor":
					previewState = PREVIEW_OE;
					window = new Window(Game.HD_PLAY_WIDTH, Game.HD_PLAY_HEIGHT);
					break;
				}
			}
		}
	}
	
	public Window(int width, int height){
		//Setup the window and the canvas
		this.setTitle("BDK engine window");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		if(windowInfo.isWindowedMode()){
			this.setUndecorated(false);
			this.pack();
			this.setVisible(true);	
		} else{
			this.setUndecorated(true);
			this.setSize(width, height);
			this.add(game);
			this.setVisible(true);
		}
		
		game.startGame();
		
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public static Dimension getScreenSize() {
		return screenSize;
	}

	public static double getFullscreenHeight() {
		return FULLSCREEN_HEIGHT;
	}

	public static double getFullscreenWidth() {
		return FULLSCREEN_WIDTH;
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

	public static int getHdPlusWidth() {
		return HD_PLUS_WIDTH;
	}

	public static int getHdPlusHeight() {
		return HD_PLUS_HEIGHT;
	}

	public static int getHdFullWidth() {
		return HD_FULL_WIDTH;
	}

	public static int getHdFullHeight() {
		return HD_FULL_HEIGHT;
	}
}
