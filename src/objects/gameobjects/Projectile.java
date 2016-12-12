package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import engine.math.Hitbox;
import engine.math.Vector2D;

public class Projectile extends Sprite {

	// FINALS-----------------------------------------|
	public static final double PROJECTILE_SPEED = -10;
	public static final int MOVEMENT_PLAYER_PROJECTILE = 0;
	public static final int MOVEMENT_SPIRAL = 1;
	//------------------------------------------------|
	// GAMEOBJECTS------------------------------------|
	private ProjectileController pController;
	//------------------------------------------------|

	public Projectile(Vector2D position, double xvel, double yvel, int health, ObjectID ID, int movementType) {
		super(position, xvel, yvel, health, ID);
		pController = new ProjectileController(movementType, this);
	}

	// UPDATING--------------------------------------------------------------------------|
	@Override
	public void update() {
		moveProjectile();
		checkForRemove();
	}

	/**
	 * moves the projectile
	 */
	private void moveProjectile() {
		pController.moveProjectile();
	}

	/**
	 * check if this projectile is out of bound and should be removed
	 */
	private void checkForRemove() {
		if (this.position.getY() < 0) {
			requestRemoveList.add(this);
		}
	}

	// --------------------------------------------------------------------------|
	// RENDERING-----------------------------------------------------------------|
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) position.getX(), (int) position.getY(), SpriteSize.SIZE_MEDIUM, SpriteSize.SIZE_MEDIUM);
	}
	// ---------------------------------------------------------------------------|

	//GETTERS AND SETTERS
	//----------------------------------------------------------------------------|
	//----------------------------------------------------------------------------|
}
