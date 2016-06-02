package engine.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;

import engine.input.Input;
import objects.gameobjects.GameObject;
import objects.gameobjects.GameTile;
import objects.gameobjects.ObjectID;

/**
 * Class contains methods regarding the game screen. All game components are
 * drawn on a Canvas wich is displayed on a JFrame
 * 
 * @author Andreas Farley
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final double TICKRATE = 60.0;


	public static ArrayList<GameObject> GridList = new ArrayList<GameObject>();
	public static ArrayList<GameObject> GameTileList = new ArrayList<GameObject>(1200);
	public static LinkedList<GameObject> DynamicList = new LinkedList<GameObject>();

	private boolean isRunning = false;
	private Thread thread;

	public static void main(String[] args) {
		Window window = new Window("LTW", new Game());
	}

	public Game() {
		this.setSize(Window.GAMEWIDTH, Window.GAMEHEIGHT);
	}

	public synchronized void startGame() {
		if (isRunning)
			return;
		loadGraphics();
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	private void loadGraphics() {
		int widthcounter;
		int heightcounter = 0;

		// Add the gametiles into their list
		for (int j = 0; j < Window.GAMEHEIGHT; j += Window.TILEHEIGHT) {
			widthcounter = 0;
			for (int i = 0; i < Window.GAMEWIDTH; i += Window.TILEWIDTH) {

				// first or last row ?
				if (heightcounter == 0 || heightcounter == 29) {
					// first row ?
					if (heightcounter == 0) {
						// add corner frame tile to first/last tile
						if (widthcounter == 0 || widthcounter == 39) {
							if (widthcounter == 0)
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 5));
							else
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 7));
						} else {
							// has rock/grass area been reached ?
							if (widthcounter >= 12 && widthcounter <= 27) {
								// rock ?
								if (widthcounter == 12 || widthcounter == 13 || widthcounter == 26
										|| widthcounter == 27) {
									GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 3));
								}
								// add grass instead
								else {
									// light grass
									if (heightcounter < 3 || heightcounter > 26) {
										GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 1));

									} else if (!(heightcounter < 3 || heightcounter > 26)) {
										// dark grass
										GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 2));
									}
								}
							}
							// not rock/grass, add border metal tile
							else {
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 6));
							}
						}
					}

					// last row ?
					else {
						// add corner frame tile to first/last tile
						if (widthcounter == 0 || widthcounter == 39) {
							if (widthcounter == 0)
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 10));
							else
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 12));
						} else {
							// has rock/grass area been reached ?
							if (widthcounter >= 12 && widthcounter <= 27) {
								// rock ?
								if (widthcounter == 12 || widthcounter == 13 || widthcounter == 26
										|| widthcounter == 27) {
									GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 3));
								}
								// add grass instead
								else {
									// light grass
									if (heightcounter < 3 || heightcounter > 26) {
										GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 1));

									} else if (!(heightcounter < 3 || heightcounter > 26)) {
										// dark grass
										GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 2));
									}
								}
							}
							// not rock/grass, add border metal tile
							else {
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 11));
							}
						}
					}

				}

				// not first or last row
				else {
					// add side border frame tile to first/last tile
					if (widthcounter == 0 || widthcounter == 39) {
						if (widthcounter == 0)
							GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 8));
						else
							GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 9));
					} else {
						// has rock/grass area been reached ?
						if (widthcounter >= 12 && widthcounter <= 27) {
							// rock ?
							if (widthcounter == 12 || widthcounter == 13 || widthcounter == 26 || widthcounter == 27) {
								GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 3));
							}
							// add grass instead
							else {
								// light grass
								if (heightcounter < 3 || heightcounter > 26) {
									GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 1));

								} else if (!(heightcounter < 3 || heightcounter > 26)) {
									// dark grass
									GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 2));
								}
							}
						}
						// not rock/grass, add normal metal tile
						else {
							GameTileList.add(new GameTile(i, j, ObjectID.GAMETILE, 4));
						}
					}
				}
				widthcounter++;
			}

			heightcounter++;
		}
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
		// Update all GameObjects and input
		Input.update();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// Render all GameObjects
		if (!GameTileList.isEmpty()) {
			Iterator<GameObject> iterator = GameTileList.iterator();
			while (iterator.hasNext()) {
				iterator.next().render(g);
			}
		}
		g.dispose();
		bs.show();
	}

}
