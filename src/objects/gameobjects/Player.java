package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import engine.input.Input;
import engine.math.Vector2D;

public class Player extends Sprite {

	public Player(Vector2D position, double xvel, double yvel, ObjectID ID) {
		super(position, xvel, yvel, ID);
	}

	@Override
	public void update() {
		movePlayer();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) position.getX(), (int) position.getY(), 25, 25);
	}

	private void movePlayer() {
		// Update movementvector based on input
		if (Input.isForward()) {
			// Check if already added
			if (movementVector.getY() != -yvel) {
				movementVector.addY(-yvel);
				System.out.println(movementVector.getY());
			}
		} else if (!Input.isForward()) {

		}
		if (Input.isBackward()) {
			// Check if already added
			if (movementVector.getY() != yvel) {
				movementVector.addY(yvel);
				System.out.println(movementVector.getY());
			}
		}
		if (Input.isLeft()) {
			// Check if already added
			if (movementVector.getX() != -xvel) {
				movementVector.addX(-xvel);
			}
		}
		if (Input.isRight()) {
			// Check if already added
			if (movementVector.getX() != xvel) {
				movementVector.addX(xvel);
			}
		}
		if (Input.isShift()) {
			// TODO: Implement projectiles
		}
		position.vecAdd(movementVector);
	}

}
