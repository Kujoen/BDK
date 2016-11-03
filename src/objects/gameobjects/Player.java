package objects.gameobjects;

import java.awt.Graphics;

import engine.input.Input;

public class Player extends Sprite {

	public Player(float x, float y, ObjectID ID) {
		super(x, y, ID);
		// starting position
		this.setX(244);
		this.setY(474);

		this.setXvel(1);
		this.setYvel(1);
	}

	@Override
	public void update() {
		// Check for movement
		// shift
		if (Input.getKeycode() == 16 || Input.getKeycode2() == 16) {

		}
		// w
		if (Input.getKeycode() == 87 || Input.getKeycode2() == 87) {

		}
		// a
		if (Input.getKeycode() == 65 || Input.getKeycode2() == 65) {

		}
		// s
		if (Input.getKeycode() == 83 || Input.getKeycode2() == 83) {

		}
		// d
		if (Input.getKeycode() == 68 || Input.getKeycode2() == 68) {

		}
	}

	@Override
	public void render(Graphics g) {

	}

}
