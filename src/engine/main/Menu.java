package engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

import engine.input.Input;

/**
 * Menu draws the menus based on menustate and updates based on input (clicks)
 * 
 * @author Soliture
 */

public class Menu {

	// We have three different menus, Mainmenu, Optionsmenu, Pausemenu
	private int menustate = 0;
	private boolean isLoaded = false;
	private Image image;

	// Initialise three Button Spaces
	private Rectangle button1 = new Rectangle(159, 172, 172, 47);
	private Rectangle button2 = new Rectangle(159, 255, 172, 47);
	private Rectangle button3 = new Rectangle(159, 335, 172, 47);

	public void render(Graphics g) {
		switch (menustate) {
		case 0:
			renderMainMenu(g);
			break;
		case 1:
			renderOptionsMenu(g);
			break;
		case 2:
			renderPauseMenu(g);
			break;
		default:
			break;
		}
	}

	public void update() {
		switch (menustate) {
		case 0:
			updateMainMenu();
			break;
		case 1:
			updateOptionsMenu();
			break;
		case 2:
			updatePauseMenu();
			break;
		default:
			break;
		}
	}

	/*
	 * Check for mouse clicks that occured on Menu Buttons, then execute
	 * whatever was pressed
	 */
	private void updateMainMenu() {
		Point mouseCord = new Point(Input.getMousex(), Input.getMousey());
		if (button1.contains(mouseCord)) {
			clickPlayButton();
		} else if (button2.contains(mouseCord)) {
			clickOptionsButton();
		} else if (button3.contains(mouseCord)) {
			clickCreditsButton();
		}
	}

	private void clickCreditsButton() {
		// TODO Auto-generated method stub
		//DEBUGGING
		System.out.println("credits pressed");
	}

	private void clickOptionsButton() {
		// TODO Auto-generated method stub
		// DEBUGING
		System.out.println("options pressed");
	}

	private void clickPlayButton() {
		// DEBUGGING
		System.out.println("play pressed");
	}

	private void updateOptionsMenu() {
		// TODO Auto-generated method stub

	}

	private void updatePauseMenu() {
		// TODO Auto-generated method stub

	}

	private void renderMainMenu(Graphics g) {
		if (!isLoaded) {
			try {
				image = ImageIO.read(new File("res/images/mainmenu.bmp"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			isLoaded = true;
			/*
			 * DEBUGGINGSystem.out.println("loading");
			 */

		}
		g.drawImage(image, 0, 0, null);

		//DEBUGGING
		g.setColor(Color.RED);
		g.drawRect(button1.x, button1.y, button1.width, button1.height);
		g.drawRect(button2.x, button2.y, button2.width, button2.height);
		g.drawRect(button3.x, button3.y, button3.width, button3.height);

	}

	private void renderOptionsMenu(Graphics g) {
		// TODO Auto-generated method stub

	}

	private void renderPauseMenu(Graphics g) {
		// TODO Auto-generated method stub

	}

	public int getMenustate() {
		return menustate;
	}

	public void setMenustate(int menustate) {
		this.menustate = menustate;
	}

}
