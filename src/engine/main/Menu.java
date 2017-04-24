package engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import engine.input.Input;
import objects.data.ImageData;

/**
 * Menu draws the menus based on menustate and updates based on input (clicks)
 * 
 * @author Soliture
 */

public class Menu {

	// INT-----------------------------------|
	private int menuID;
	// --------------------------------------|
	// ENGINEOBJECTS-------------------------|
	private Game game;
	// --------------------------------------|
	// BOOLEAN-------------------------------|
	private boolean isLoaded;
	// --------------------------------------|
	// UI------------------------------------|
	private Rectangle button1;
	private Rectangle button2;
	private Rectangle button3;
	//---------------------------------------|
	// IMAGES--------------------------------|
	private BufferedImage mainmenu_background;
	//---------------------------------------|

	public Menu(Game game) {
		this.game = game;
		this.menuID = game.getDefaultMenuId();
		this.isLoaded = false;
		
		//Setup UI
		button1 = new Rectangle(159, 172, 172, 47);
		button2 = new Rectangle(159, 255, 172, 47);
		button3 = new Rectangle(159, 335, 172, 47);
	}

	// RENDERING--------------------------------------------------------------------|
	/**
	 * Renders the menu based on the menuID
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		switch (menuID) {
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

	/**
	 * Renders the main menu
	 * 
	 * @param g
	 */
	private void renderMainMenu(Graphics g) {
		if (!isLoaded) {
			mainmenu_background = ImageData.getMainmenu_background();
			isLoaded = true;
		}
		
		g.drawImage(mainmenu_background, 0, 0, null);
		
	}

	/**
	 * Renders the options Menu
	 * 
	 * @param g
	 */
	private void renderOptionsMenu(Graphics g) {
		// TODO Auto-generated method stub

	}

	/**
	 * Renders the pause Menu
	 * 
	 * @param g
	 */
	private void renderPauseMenu(Graphics g) {
		// TODO Auto-generated method stub

	}

	// -----------------------------------------------------------------------------|
	// UPDATING---------------------------------------------------------------------|
	/**
	 * Updates the menu based on the menuID
	 */
	public void update() {
		switch (menuID) {
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

	/**
	 * updates the main menu by checking if a button was pressed
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
		// TODO implement credits screen
	}

	private void clickOptionsButton() {
		// TODO implement options screen
	}

	private void clickPlayButton() {
		// Set menu to false and level to true
		game.setMenu(false);
		game.setLevel(true);
		// Set MenuID to 2, because next time menu will be called it will be
		// during the level, wich means it has to load the pause-menu
		this.setMenuID(2);
	}

	private void updateOptionsMenu() {
		// TODO Implement options-menu
	}

	private void updatePauseMenu() {
		// TODO Implement pause-menu
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND
	// SETTERS----------------------------------------------------------------------|
	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public Rectangle getButton1() {
		return button1;
	}

	public void setButton1(Rectangle button1) {
		this.button1 = button1;
	}

	public Rectangle getButton2() {
		return button2;
	}

	public void setButton2(Rectangle button2) {
		this.button2 = button2;
	}

	public Rectangle getButton3() {
		return button3;
	}

	public void setButton3(Rectangle button3) {
		this.button3 = button3;
	}
	// -----------------------------------------------------------------------------|

}
