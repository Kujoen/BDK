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
	// FINALS--------------------------------|
	private static final int DEFAULT_PUFFER_WIDTH = 320;
	private static final int DEFAULT_TITLE_WIDTH = 640;
	private static final int DEFAULT_TITLE_HEIGHT = 180;
	private static final int DEFAULT_BUTTON_HEIGHT = 80;
	private static final int DEFAULT_BUTTON_WIDTH = 320;
	//---------------------------------------|
	// INT-----------------------------------|
	private static int ACTUAL_PUFFER_WIDTH;
	private static int ACTUAL_TITLE_WIDTH;
	private static int ACTUAL_TITLE_HEIGHT;
	private static int ACTUAL_BUTTON_HEIGHT;
	private static int ACTUAL_BUTTON_WIDTH;
	private int menuID;
	// --------------------------------------|
	// ENGINEOBJECTS-------------------------|
	private Game game;
	// --------------------------------------|
	// UI------------------------------------|
	private Rectangle button_story;
	private Rectangle button_endless;
	private Rectangle button_options;
	//---------------------------------------|
	// SPRITES-------------------------------|
	private static BufferedImage mainmenu_background;
	private static BufferedImage mainmenu_title;
	private static BufferedImage mainmenu_button_story;
	private static BufferedImage mainmenu_button_endless;
	private static BufferedImage mainmenu_button_options;
	//---------------------------------------|
	// BOOLEAN-------------------------------|
	private static boolean shouldReload = true;
	//---------------------------------------|
	
	public Menu(Game game) {
		this.game = game;
		this.menuID = game.getDefaultMenuId();
		
		//Setup UI
		button_story = new Rectangle((Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)), 
										Menu.getACTUAL_BUTTON_HEIGHT() + Menu.getACTUAL_TITLE_HEIGHT(), 
										Menu.getACTUAL_BUTTON_WIDTH(), 
										Menu.getACTUAL_BUTTON_HEIGHT());
		
		button_endless = new Rectangle((Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)), 
				(Menu.getACTUAL_BUTTON_HEIGHT() * 2) + Menu.getACTUAL_TITLE_HEIGHT(), 
				Menu.getACTUAL_BUTTON_WIDTH(), 
				Menu.getACTUAL_BUTTON_HEIGHT());
		
		button_options = new Rectangle((Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)), 
				(Menu.getACTUAL_BUTTON_HEIGHT() * 3) + Menu.getACTUAL_TITLE_HEIGHT(), 
				Menu.getACTUAL_BUTTON_WIDTH(), 
				Menu.getACTUAL_BUTTON_HEIGHT());
		
		this.mainmenu_background = ImageData.getMainmenu_background();
		this.mainmenu_button_story = ImageData.getMainmenu_button_story();
		this.mainmenu_button_endless = ImageData.getMainmenu_button_endless();
		this.mainmenu_button_options = ImageData.getMainmenu_button_options();
		this.mainmenu_title = ImageData.getMainmenu_title();
	}
	
	public static void scaleMenuData(double scaling_factor){
		ACTUAL_PUFFER_WIDTH = (int)(DEFAULT_PUFFER_WIDTH * scaling_factor);
		ACTUAL_TITLE_WIDTH = (int)(DEFAULT_TITLE_WIDTH * scaling_factor);
		ACTUAL_TITLE_HEIGHT = (int)(DEFAULT_TITLE_HEIGHT * scaling_factor);
		ACTUAL_BUTTON_HEIGHT = (int)(DEFAULT_BUTTON_HEIGHT * scaling_factor);
		ACTUAL_BUTTON_WIDTH = (int)(DEFAULT_BUTTON_WIDTH * scaling_factor);
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
		if(shouldReload){
		g.drawImage(mainmenu_background, 0, 0, null);
		g.drawImage(mainmenu_title, Menu.getACTUAL_PUFFER_WIDTH(), 0, null);
		g.drawImage(mainmenu_button_story, 
							(Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)),
							Menu.getACTUAL_BUTTON_HEIGHT() + Menu.getACTUAL_TITLE_HEIGHT(), 
							null);
		g.drawImage(mainmenu_button_endless, 
				(Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)),
				(Menu.getACTUAL_BUTTON_HEIGHT() * 3) + Menu.getACTUAL_TITLE_HEIGHT(), 
				null);
		g.drawImage(mainmenu_button_options, 
				(Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)),
				(Menu.getACTUAL_BUTTON_HEIGHT() * 5) + Menu.getACTUAL_TITLE_HEIGHT(), 
				null);
		
		shouldReload = false;
		}
		
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
		if (button_story.contains(mouseCord)) {
			clickPlayButton();
		} /*else if (button_endless.contains(mouseCord)) {
			clickOptionsButton();
		} else if (button_options.contains(mouseCord)) {
			clickCreditsButton();
		}*/
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
	
	public static int getACTUAL_PUFFER_WIDTH() {
		return ACTUAL_PUFFER_WIDTH;
	}

	public static void setACTUAL_PUFFER_WIDTH(int aCTUAL_PUFFER_WIDTH) {
		ACTUAL_PUFFER_WIDTH = aCTUAL_PUFFER_WIDTH;
	}

	public static int getACTUAL_TITLE_WIDTH() {
		return ACTUAL_TITLE_WIDTH;
	}

	public static void setACTUAL_TITLE_WIDTH(int aCTUAL_TITLE_WIDTH) {
		ACTUAL_TITLE_WIDTH = aCTUAL_TITLE_WIDTH;
	}

	public static int getACTUAL_TITLE_HEIGHT() {
		return ACTUAL_TITLE_HEIGHT;
	}

	public static void setACTUAL_TITLE_HEIGHT(int aCTUAL_TITLE_HEIGHT) {
		ACTUAL_TITLE_HEIGHT = aCTUAL_TITLE_HEIGHT;
	}

	public static int getACTUAL_BUTTON_HEIGHT() {
		return ACTUAL_BUTTON_HEIGHT;
	}

	public static void setACTUAL_BUTTON_HEIGHT(int aCTUAL_BUTTON_HEIGHT) {
		ACTUAL_BUTTON_HEIGHT = aCTUAL_BUTTON_HEIGHT;
	}

	public static int getACTUAL_BUTTON_WIDTH() {
		return ACTUAL_BUTTON_WIDTH;
	}

	public static void setACTUAL_BUTTON_WIDTH(int aCTUAL_BUTTON_WIDTH) {
		ACTUAL_BUTTON_WIDTH = aCTUAL_BUTTON_WIDTH;
	}

	// -----------------------------------------------------------------------------|

	
}
