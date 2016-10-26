package engine.main;

import java.awt.Graphics;

/**
 * Menu draws the menus based on menustate and updates based on input (clicks)
 * 
 * @author Soliture
 */

public class Menu {
	
	// We have three different menus, Mainmenu, Optionsmenu, Pausemenu
	private int menustate = 0;

	public void render(Graphics g) {
		switch (menustate) {
		case 0:
			renderMainMenu();
			break;
		case 1:
			renderOptionsMenu();
			break;
		case 2:
			renderPauseMenu();
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

	private void updatePauseMenu() {
		// TODO Auto-generated method stub
		
	}

	private void updateOptionsMenu() {
		// TODO Auto-generated method stub
		
	}

	private void updateMainMenu() {
		// TODO Auto-generated method stub
		
	}

	private void renderPauseMenu() {
		// TODO Auto-generated method stub

	}

	private void renderOptionsMenu() {
		// TODO Auto-generated method stub

	}

	private void renderMainMenu() {
		// TODO Auto-generated method stub

	}

	public int getMenustate() {
		return menustate;
	}

	public void setMenustate(int menustate) {
		this.menustate = menustate;
	}

}
