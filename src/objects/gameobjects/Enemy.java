package objects.gameobjects;

import java.awt.Graphics;

import engine.math.Vector2D;

public class Enemy extends Sprite {

	public Enemy(Vector2D position, Vector2D movementvector, int health, ObjectID ID, String spritefilename, boolean isanimated) {
		super(position,movementvector, health, ID, spritefilename, isanimated);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void loadNewSubImage(int sectionid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void animationController() {
		// TODO Auto-generated method stub
		
	}

}
