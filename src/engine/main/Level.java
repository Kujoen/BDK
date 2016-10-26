package engine.main;

import java.awt.Graphics;

public class Level {
	
	private int levelstate = 0;

	public void render(Graphics g) {
		switch (levelstate) {
		case 0:
			renderLevelOne();
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
		// TODO Auto-generated method stub
		
	}

	private void renderLevelOne() {
		// TODO Auto-generated method stub
		
	}

	
}
