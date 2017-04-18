package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import engine.main.Game;
import engine.math.Hitbox;
import engine.math.MovementFunctions;
import engine.math.Vector2D;

public class Projectile extends Sprite {

	// IMAGE------------------------------------------|
	private Image currentimage;
	//------------------------------------------------|
	// INT--------------------------------------------|
	private int animationtickcounter = 0;
	private int animationposcounter = 0;
	//------------------------------------------------|
	// Spielerei--------------------------------------|
	private MovementFunctions mFunction;
	//------------------------------------------------|

	public Projectile(Vector2D position, Vector2D movementvector, int health, ObjectID ID, String spritefilename, boolean isanimated) {
		super(position, movementvector, health, ID, spritefilename, isanimated);
		mFunction = new MovementFunctions(this);
	}

	// UPDATING--------------------------------------------------------------------------|
	@Override
	public void update() {
		animationController();
		moveProjectile();
		checkForRemove();
	}

	/**
	 * moves the projectile
	 */
	private void moveProjectile() {
		position.vecAdd(movementvector);
	}

	/**
	 * check if this projectile is out of bound and should be removed
	 */
	private void checkForRemove() {
		if (this.position.getY() < Game.getACTUAL_PUFFER_HEIGHT()) {
			requestRemoveList.add(this);
		}
	}

	// --------------------------------------------------------------------------|
	// RENDERING-----------------------------------------------------------------|
	@Override
	public void render(Graphics g) {
		g.drawImage(currentimage, (int)position.getX(), (int)position.getY(), null);
	}
	// ---------------------------------------------------------------------------|

	@Override
	public void loadNewSubImage(int sectionid) {
		switch(sectionid){
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		}
		
	}

	@Override
	public void animationController() {
		if(animationtickcounter % 10 != 0){
			switch(animationposcounter % 6){
			case 0:
				currentimage = spritefile.getSubimage(0, 0, 20, 20);
				break;
			case 1:
				currentimage = spritefile.getSubimage(20, 0, 20, 20);
				break;
			case 2:
				currentimage = spritefile.getSubimage(40, 0, 20, 20);
				break;
			case 3:
				currentimage = spritefile.getSubimage(0, 20, 20, 20);
				break;
			case 4:
				currentimage = spritefile.getSubimage(20, 20, 20, 20);
				break;
			case 5: 
				currentimage = spritefile.getSubimage(40, 20, 20, 20);
				break;
			}
			
		}
		
		animationtickcounter++;
		animationposcounter++;
	}

	//GETTERS AND SETTERS
	//----------------------------------------------------------------------------|
	//----------------------------------------------------------------------------|
}
