package engine.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import engine.input.Input;

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
	// ------------------------------------------------|
	// FINALS------------------------------------------|
	private static final long serialVersionUID = 1L;
	private static final double TICKRATE = 60.0;
	private static final int DEFAULT_LEVEL_ID = 0;
	private static final int DEFAULT_MENU_ID = 0;
	// INTS--------------------------------------------|
	private int framesPerSecond = 0;
	private int updatesPerSecond = 0;
	// OTHER-------------------------------------------|
	private Thread thread;
	// ------------------------------------------------|

	public static void main(String[] args) {
		Window window = new Window("LTW");
	}

	/**
	 * This contructor sets booleans and initialises gameobjects.
	 * @param window
	 */
	public Game(Window window) {
		this.setSize(Window.GAMEWIDTH, Window.GAMEHEIGHT);
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

	/**
	 * Renders the current active gameobject
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		if (!isPaused) {
			//Check wich gameobject is being drawn
			if (isMenu) {
				menu.render(g);
			} else if (isLevel) {
				level.render(g);
			}
		}

		// Check if debugging is enabled
		if (isDebug) {
			debug.render(g, this);
		}

		g.dispose();
		bs.show();
	}

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

	// ------------------------------------------------------------------------------|

}
