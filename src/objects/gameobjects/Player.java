package objects.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

	private int playerx;
	private int playery;
	private int playerhealth;
	private static final int PLAYERSPEED = 5;
	private static final Rectangle HITBOX = new Rectangle(0,0,20,20);

	public Player(){
	}
	
	public void update() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
	}
}
