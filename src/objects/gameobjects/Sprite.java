package objects.gameobjects;

import java.awt.Graphics;

import engine.math.Vector2D;

public abstract class Sprite {

	protected Vector2D position;
	protected Vector2D movementVector = new Vector2D(0,0);
	protected double xvel;
	protected double yvel;
	protected ObjectID ID;

	public Sprite(Vector2D position, double xvel, double yvel, ObjectID ID) {
		this.position = position;
		this.xvel = xvel;
		this.yvel = yvel;
		this.ID = ID;
	}

	public abstract void update();

	public abstract void render(Graphics g);
}
