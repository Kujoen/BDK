package engine.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JOptionPane;

import engine.input.Input;
import objects.data.ImageData;
import objects.data.SpriteData;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas wich is displayed on a JFrame
 * 
 * @author Soliture
 *
 */
public class Game extends Canvas implements Runnable {
	// ENGINEOBJECTS-----------------------------------|
	private Window window;
	private Level level;
	private Menu menu;
	private Debugdrawer debug;
	// ------------------------------------------------|
	// BOOLEANS----------------------------------------|
	private boolean isRunning;
	private boolean isDebug;
	private boolean isMenu;
	private boolean isLevel;
	private boolean isMouseListening;
	private boolean isPaused;
	private boolean isDisplayLeveltitle;
	// STRINGS-----------------------------------------|
	private String leveltitle;
	// ------------------------------------------------|
	// FINALS------------------------------------------|
	private static final long serialVersionUID = 1L;
	private static final double TICKRATE = 60.0;
	private static final int DEFAULT_LEVEL_ID = 0;
	private static final int DEFAULT_MENU_ID = 0;
	private static final int DEFAULT_PLAY_WIDTH = 520;
	private static final int DEFAULT_PLAY_HEIGHT = 576;
	private static final int DEFAULT_PUFFER_HEIGHT = 72;
	private static final int DEFAULT_PUFFER_WIDTH = 380;
	// INTS--------------------------------------------|
	private static int ACTUAL_PLAY_WIDTH;
	private static int ACTUAL_PLAY_HEIGHT;
	private static int ACTUAL_PUFFER_HEIGHT;
	private static int ACTUAL_PUFFER_WIDTH;
	private static int missingPixels = 0;
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	// OTHER-------------------------------------------|
	private Thread thread;
	// ------------------------------------------------|

	public static void main(String[] args) {
		
		//loadimages
		ImageData.loadimages();
		
		// Ask if fullscreen or default size
		JOptionPane optionpane = new JOptionPane();
		
		int reply = optionpane.showConfirmDialog(null, "Would you like to run Clonehou in fullscreen ?" , "Window Settings", JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
			
			Window.setACTUALHEIGHT((int)Window.getScreenheight());
			Window.setACTUALWIDTH((int)Window.getScreenwidth());
			
			double height_scaling_factor = Window.getScreenheight() / Window.getDefaultheight();
			
			ACTUAL_PLAY_HEIGHT = (int)(DEFAULT_PLAY_HEIGHT * height_scaling_factor);
			ACTUAL_PUFFER_HEIGHT = (int)(DEFAULT_PUFFER_HEIGHT * height_scaling_factor);
			
			double width_scaling_factor = Window.getScreenwidth() / Window.getDefaultwidth();
			
			ACTUAL_PLAY_WIDTH = (int)(DEFAULT_PLAY_WIDTH * width_scaling_factor);
			ACTUAL_PUFFER_WIDTH = (int)(DEFAULT_PUFFER_WIDTH * width_scaling_factor);

			Menu.scaleMenuData(height_scaling_factor);
			SpriteData.scaleData(height_scaling_factor);
			ImageData.scaleimages();
			
		}else{
			Window.setACTUALHEIGHT(Window.getDefaultheight());
			Window.setACTUALWIDTH(Window.getDefaultwidth());
			ACTUAL_PLAY_WIDTH = DEFAULT_PLAY_WIDTH;
			ACTUAL_PLAY_HEIGHT = DEFAULT_PLAY_HEIGHT;
			ACTUAL_PUFFER_HEIGHT = DEFAULT_PUFFER_HEIGHT;
			ACTUAL_PUFFER_WIDTH = DEFAULT_PUFFER_WIDTH;
			
			Menu.scaleMenuData(1.0);
			SpriteData.scaleData(1.0);
		}
		
		missingPixels = (int)(Window.getScreenheight() - (ACTUAL_PUFFER_HEIGHT + ACTUAL_PLAY_HEIGHT + ACTUAL_PUFFER_HEIGHT));
		
		Window window = new Window("LTW");
	}

	/**
	 * This contructor sets booleans and initialises gameobjects.
	 * @param window
	 */
	public Game(Window window) {
		this.setSize(Window.getACTUALWIDTH(), Window.getACTUALHEIGHT());
		this.addKeyListener(Input.KeyInputListener);
		this.window = window;

		// Set booleans
		isRunning = false;
		isDebug = true;
		isMenu = true;
		isLevel = false;
		isPaused = false;

		// Set gameobjects
		menu = new Menu(this);
		level = new Level(this);
		debug = new Debugdrawer();
		
	}
	
	// GAME/FLOW CONTROLLERS-------------------------------------------------------------|
	/**
	 * Starts a new game if there is no other gamethread running
	 */
	public synchronized void startGame() {
		// Start the thread
		if (!isRunning) {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}

	}
	
	/**
	 * Called by the levelController when the end of the level is detected. Shows a level completed screen and then starts the next
	 * level after a brief delay.
	 */
	public void startNextLevel(){
		
	}

	@Override
	public void run() {
		long previoustime = System.nanoTime();
		double nanosecond = 1000000000 / TICKRATE;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while (isRunning) {
			long currenttime = System.nanoTime();
			delta += (currenttime - previoustime) / nanosecond;
			previoustime = currenttime;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				framesPerSecond = frames;
				updatesPerSecond = updates;
				frames = 0;
				updates = 0;
			}
		}
	}
	//  -----------------------------------------------------------------------------|
	// UPDATING----------------------------------------------------------------------|
	/**
	 * calls the update method of Input and the selected gameobject
	 */
	public void update() {
		// Update Input
		Input.update();

		// Check if updates are paused
		if (!isPaused) {
			// Check if menu should be updated
			if (isMenu) {
				// Check if there is a MouseListener
				if (!isMouseListening) {
					isMouseListening = true;
					this.addMouseListener(Input.MouseInputListener);
				}
				menu.update();
			}

			// Check if level should be updated
			if (isLevel) {
				// Check if there is a MouseListener
				if (isMouseListening) {
					// Reset mouse coordinates to prevent menu activation and
					// remove
					// listener
					Input.setMousex(0);
					Input.setMousey(0);
					this.removeMouseListener(Input.MouseInputListener);
					isMouseListening = false;
				}
				level.update();
			}
		}
	}
	
	// -------------------------------------------------------------------------------|
	// RENDERING----------------------------------------------------------------------|
	/**
	 * Renders the current active gameobject
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		if (!isPaused) {
			//Check if menu/level
			if (isMenu) {
				menu.render(g);
			} else if (isLevel) {
				level.render(g);
				drawUI(g);
			}
		}

		// Check if debugging is enabled
		if (isDebug) {
			debug.render(g, this);
		}

		g.dispose();
		bs.show();
	}
	
	private void drawUI(Graphics g) {
		
		if(isDisplayLeveltitle){
			drawLevelTitle(g);
		}
		
		
	}
	
	private void drawLevelTitle(Graphics g) {
		
	
	}
	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public boolean isMenu() {
		return isMenu;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	public boolean isLevel() {
		return isLevel;
	}

	public void setLevel(boolean isLevel) {
		this.isLevel = isLevel;
	}

	public boolean isMouseListening() {
		return isMouseListening;
	}

	public void setMouseListening(boolean isMouseListening) {
		this.isMouseListening = isMouseListening;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public int getFramesPerSecond() {
		return framesPerSecond;
	}

	public void setFramesPerSecond(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public int getUpdatesPerSecond() {
		return updatesPerSecond;
	}

	public void setUpdatesPerSecond(int updatesPerSecond) {
		this.updatesPerSecond = updatesPerSecond;
	}

	public static double getTickrate() {
		return TICKRATE;
	}

	public static int getDefaultLevelId() {
		return DEFAULT_LEVEL_ID;
	}

	public static int getDefaultMenuId() {
		return DEFAULT_MENU_ID;
	}

	public boolean isLeveltitle() {
		return isDisplayLeveltitle;
	}

	public void setDisplayLeveltitle(boolean isLevelTitle) {
		this.isDisplayLeveltitle = isLevelTitle;
	}

	public String getLeveltitle() {
		return leveltitle;
	}

	public void setLeveltitle(String leveltitle) {
		this.leveltitle = leveltitle;
	}

	public static int getACTUAL_PLAY_WIDTH() {
		return ACTUAL_PLAY_WIDTH;
	}

	public static void setACTUAL_PLAY_WIDTH(int aCTUAL_PLAY_WIDTH) {
		ACTUAL_PLAY_WIDTH = aCTUAL_PLAY_WIDTH;
	}

	public static int getACTUAL_PLAY_HEIGHT() {
		return ACTUAL_PLAY_HEIGHT;
	}

	public static void setACTUAL_PLAY_HEIGHT(int aCTUAL_PLAY_HEIGHT) {
		ACTUAL_PLAY_HEIGHT = aCTUAL_PLAY_HEIGHT;
	}

	public static int getACTUAL_PUFFER_HEIGHT() {
		return ACTUAL_PUFFER_HEIGHT;
	}

	public static void setACTUAL_PUFFER_HEIGHT(int aCTUAL_PUFFER_HEIGHT) {
		ACTUAL_PUFFER_HEIGHT = aCTUAL_PUFFER_HEIGHT;
	}

	public static int getACTUAL_PUFFER_WIDTH() {
		return ACTUAL_PUFFER_WIDTH;
	}

	public static void setACTUAL_PUFFER_WIDTH(int aCTUAL_PUFFER_WIDTH) {
		ACTUAL_PUFFER_WIDTH = aCTUAL_PUFFER_WIDTH;
	}

	public static int getMissingPixels() {
		return missingPixels;
	}

	public static void setMissingPixels(int missingPixels) {
		Game.missingPixels = missingPixels;
	}

	// ------------------------------------------------------------------------------|

}
