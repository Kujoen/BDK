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

	// --------------------------------------------------------------------------|
	// RENDERING-----------------------------------------------------------------|
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) position.getX(), (int) position.getY(), 25, 25);
	}
	//---------------------------------------------------------------------------|
}
