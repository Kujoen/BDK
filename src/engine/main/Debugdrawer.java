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

			g.drawRect((int) menu.getButton1().getX(), (int) menu.getButton1().getY(),
					(int) menu.getButton1().getWidth(), (int) menu.getButton1().getHeight());
			g.drawRect((int) menu.getButton2().getX(), (int) menu.getButton2().getY(),
					(int) menu.getButton2().getWidth(), (int) menu.getButton2().getHeight());
			g.drawRect((int) menu.getButton3().getX(), (int) menu.getButton3().getY(),
					(int) menu.getButton3().getWidth(), (int) menu.getButton3().getHeight());
			g.drawString("menuID : " + menu.getMenuID(), 0, 30);
		}

		// Check for level specific debug
		if (game.isLevel()) {
			g.drawString("Sprite Count : " + (game.getLevel().getSpriteList().size()), 0, 30);
		}

		g.drawRect(Game.getACTUAL_PUFFER_WIDTH(), Game.getACTUAL_PUFFER_HEIGHT(), 10,10);
	}

}
