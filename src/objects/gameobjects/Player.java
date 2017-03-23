package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import engine.input.Input;
import engine.math.Hitbox;
import engine.math.Vector2D;

public class Player extends Sprite {

	// BOOLEAN----------------------------------------------------|
	private boolean hasShifted = false;
	// -----------------------------------------------------------|
	// INT--------------------------------------------------------|
	private int counterProjectileTick = 0;
	// -----------------------------------------------------------|

	public Player(Vector2D position, double xvel, double yvel, int health, ObjectID ID) {
		super(position, xvel, yvel, health, ID);
	}

	// UPDATING--------------------------------------------------------------------------|
	/**
	 * Updates the player sprite
	 */
	@Override
	public void update() {
		movePlayer();
		spawnPlayerProjectiles();
	}

	/**
	 * moves the player based on input
	 */
	private void movePlayer() {
		if (Input.isForward()) {
			if (position.getY() > 0) {
				position.addY(-yvel);
			}
		}
		if (Input.isBackward()) {
			if (position.getY() < 475) {
				position.addY(yvel);
			}
		}

		if (Input.isLeft()) {
			if (position.getX() > 50) {
				position.addX(-xvel);
			}
		}

		if (Input.isRight()) {
			if (position.getX() < 425) {
				position.addX(xvel);
			}
		}

		if (Input.isShift()) {
			if (!hasShifted) {
				yvel /= 2;
				xvel /= 2;
				hasShifted = true;
			}
		} else if (hasShifted == true) {
			yvel *= 2;
			xvel *= 2;
			hasShifted = false;
		}
	}

	/**
	 * check if the player is holding spacebar, if yes Player Projectiles will
	 * be added to the requestSpawnList
	 */
	public void spawnPlayerProjectiles() {
		if (Input.isSpacebar()) {
			if (counterProjectileTick % 4 == 0) {
				requestSpawnList.add(new Projectile(
						new Vector2D(this.position.getX() + SpriteSize.PLAYER_SIZE / 2 - SpriteSize.SIZE_MEDIUM / 2,
								this.position.getY()),
						Projectile.SPEED_PLAYER_PROJECTILE, Projectile.SPEED_PLAYER_PROJECTILE, 0, ObjectID.PROJECTILE,
						Projectile.MOVEMENT_PLAYER_PROJECTILE));
				counterProjectileTick++;
			}
			counterProjectileTick++;
		}
	}

	// --------------------------------------------------------------------------|
	// RENDERING-----------------------------------------------------------------|
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) position.getX(), (int) position.getY(), SpriteSize.PLAYER_SIZE, SpriteSize.PLAYER_SIZE);
	}
	// ---------------------------------------------------------------------------|
}
