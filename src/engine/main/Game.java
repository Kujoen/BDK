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

	private static final long serialVersionUID = 1L;
	private static int ups;
	private static int fps;
	public static final double TICKRATE = 60.0;

	private boolean isRunning = false;
	private boolean isMenu = true;
	private boolean isDebug = true;
	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	private boolean isMouseListening = false;

	private Thread thread;

	public Menu menu;
	public Level level;

	public static void main(String[] args) {
		Window window = new Window("LTW", new Game());
	}

	public Game() {
		this.setSize(Window.GAMEWIDTH, Window.GAMEHEIGHT);
		this.addKeyListener(Input.KeyInputListener);
	}

	public synchronized void startGame() {
		// Setup things game needs here
		menu = new Menu(this);
		level = new Level(this);
		// Start the thread
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
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
				// System.out.println(frames + " " + updates);
				fps = frames;
				ups = updates;
				frames = 0;
				updates = 0;

			}
		}
	}

	public void update() {
		// Update here
		Input.update();

		if (isMenu) {
			if (!isMouseListening) {
				isMouseListening = true;
				this.addMouseListener(Input.MouseInputListener);
			}
			menu.update();
		} else {
			if (isMouseListening) {
				// RESET MOUSE COORDINATES TO PREVENT INSTANT MENU ACTIVATION
				Input.setMousex(0);
				Input.setMousey(0);
				this.removeMouseListener(Input.MouseInputListener);
				isMouseListening = false;
			}
			level.update();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// Render here
		if (isMenu) {
			menu.render(g);
		} else {
			level.render(g);
		}

		// DEBUG RENDERING
		if (isDebug) {
			g.setColor(Color.RED);
			g.drawString("FPS : " + fps + " UPS : " + ups, 390, 10);
			g.drawString("Menustate : " + menu.getMenustate(), 420, 20);
			g.drawString("Levelstate : " + level.getLevelstate(), 420, 30);
			g.drawString(Input.getMousex() + " " + Input.getMousey(), 450, 40);
		}

		g.dispose();
		bs.show();
	}

	public boolean isMenu() {
		return isMenu;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
		if (isMenu) {
			level.stopLevelTimer();
		} else if (!isMenu) {
			level.startLevelTimer();
		}
	}

}
