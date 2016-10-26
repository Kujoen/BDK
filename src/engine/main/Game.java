package engine.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.List;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

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
	public static final double TICKRATE = 60.0;

	private boolean isRunning = false;
	private boolean isMenu = true;

	private Thread thread;

	public Menu menu = new Menu();
	public Level level = new Level();

	public static void main(String[] args) {
		Window window = new Window("LTW", new Game());
	}

	public Game() {
		this.setSize(Window.GAMEWIDTH, Window.GAMEHEIGHT);
		this.addKeyListener(Input.KeyInputListener);
	}

	public synchronized void startGame() {
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
		int updates = 0;
		int frames = 0;
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
				System.out.println(frames + " " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public void update() {
		// Update here
		Input.update();
		
		if (isMenu) {
			menu.update();
		}
		else{
			level.update();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// Render here
		if (isMenu) {
			menu.render(g);
		}
		else{
			level.render(g);
		}

		g.dispose();
		bs.show();
	}

	public boolean isMenu() {
		return isMenu;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

}
