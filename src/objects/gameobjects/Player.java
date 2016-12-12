package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import engine.input.Input;
import engine.math.Hitbox;
import engine.math.Vector2D;

public class Player extends Sprite {

	boolean hasShifted = false;

	public Player(Vector2D position, double xvel, double yvel, ObjectID ID) {
		super(position, xvel, yvel, ID);
		this.hitbox = new Hitbox(this, 2);
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
		// TODO: use the movement vector
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
}
