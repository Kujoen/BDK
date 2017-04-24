package engine.main;

import java.awt.Color;
import java.awt.Graphics;

public class Debugdrawer {

	public void render(Graphics g, Game game) {
		g.setColor(Color.RED);
		g.drawString("FPS : " + game.getFramesPerSecond(), 0, 10);
		g.drawString("UPS : " + game.getUpdatesPerSecond(), 0, 20);

		// Check for menu specific debug
		if (game.isMenu()) {
			Menu menu = game.getMenu();
			
			g.setColor(Color.RED);
			g.drawRect((Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)), 
										Menu.getACTUAL_BUTTON_HEIGHT() + Menu.getACTUAL_TITLE_HEIGHT(), 
										Menu.getACTUAL_BUTTON_WIDTH(), 
										Menu.getACTUAL_BUTTON_HEIGHT());
			g.drawRect((Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)), 
					(Menu.getACTUAL_BUTTON_HEIGHT() * 3) + Menu.getACTUAL_TITLE_HEIGHT(), 
					Menu.getACTUAL_BUTTON_WIDTH(), 
					Menu.getACTUAL_BUTTON_HEIGHT());
			
			g.drawRect((Menu.getACTUAL_PUFFER_WIDTH() + Menu.getACTUAL_PUFFER_WIDTH() - (Menu.getACTUAL_BUTTON_WIDTH() / 2)), 
					(Menu.getACTUAL_BUTTON_HEIGHT() * 5) + Menu.getACTUAL_TITLE_HEIGHT(), 
					Menu.getACTUAL_BUTTON_WIDTH(), 
					Menu.getACTUAL_BUTTON_HEIGHT());
		}

		// Check for level specific debug
		if (game.isLevel()) {
			g.drawString("Sprite Count : " + (game.getLevel().getSpriteList().size()), 0, 30);
		}
		
		//g.drawRect(Game.getACTUAL_PUFFER_WIDTH(), Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT(), Game.getACTUAL_PLAY_WIDTH(), Game.getACTUAL_PUFFER_HEIGHT());
		
	}
	
}
