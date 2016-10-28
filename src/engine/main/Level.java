package engine.main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import objects.gameobjects.Player;

public class Level {
	// Pixels per tick
	private static final int SCROLLSPEED = 2;

	private int levelstate = 0;
	private int background1x = 0;
	private int background1y;
	private int background2x = 0;
	private int background2y;
	private boolean isLoaded = false;
	private Image background1;
	private Image background2;
	private Game game;
	private Player player;

	public Level(Game game) {
		this.game = game;
		player = new Player();
	}

	public int getLevelstate() {
		return levelstate;
	}

	public void setLevelstate(int levelstate) {
		this.levelstate = levelstate;
	}

	public void render(Graphics g) {
		switch (levelstate) {
		case 0:
			renderLevelOne(g);
			break;
		default:
			break;
		}
	}

	public void update() {
		switch (levelstate) {
		case 0:
			updateLevelOne();
			break;
		default:
			break;
		}
	}

	private void updateLevelOne() {
		//Update background Position
		updateBackground();
		//Update player Position
		player.update();
		
		//DEBUG
		System.out.println(background1y);
	}

	private void updateBackground() {
		background1y += SCROLLSPEED;
		background2y += SCROLLSPEED;
	}

	private void renderLevelOne(Graphics g) {
		if (!isLoaded) {
			// Load background and all sprites here
			try {
				background1 = ImageIO.read(new File("res/images/level1background.bmp"));
				background1y = 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				background2 = ImageIO.read(new File("res/images/level1background.bmp"));
				background2y = -500;
			} catch (Exception e) {
				e.printStackTrace();
			}
			isLoaded = true;
		}
		// Render the Level here, first background then sprites
		drawScrollingBackground(g, 0);
		drawPlayerSprite(g);
	}

	private void drawPlayerSprite(Graphics g) {
		
	}

	private void drawScrollingBackground(Graphics g, int levelstate) {
		g.drawImage(background1, background1x, background1y, null);
		g.drawImage(background2, background2x, background2y, null);
		
		if(background1y == 500){
			background1y = -500;
		}else if(background2y == 500){
			background2y = -500;
		}
	}

}
