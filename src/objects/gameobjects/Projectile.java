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
	
	// INT--------------------------------------------|
	private int animationtickcounter = 0;
	private int animationposcounter = 0;
	//------------------------------------------------|
	// Spielerei--------------------------------------|
	private MovementFunctions mFunction;
	//------------------------------------------------|

	public Projectile(Vector2D position, Vector2D movementvector, int health, ObjectID ID, boolean isanimated) {
		super(position, movementvector, health, ID,  isanimated);
		mFunction = new MovementFunctions(this);
	}

	// UPDATING--------------------------------------------------------------------------|
	@Override
	public void update() {
		
		if(isanimated){
			animationController();	
		}
		
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
		g.drawImage(spritefile, (int)position.getX(), (int)position.getY(), null);
	}
	// ---------------------------------------------------------------------------|

	@Override
	public void animationController() {
		
	}

	//GETTERS AND SETTERS
	//----------------------------------------------------------------------------|
	//----------------------------------------------------------------------------|
}
