package objects.gameobjects;

import java.awt.Graphics;
import java.util.Set;

import engine.math.Hitbox;
import engine.math.Vector2D;

public abstract class Sprite {
	// MATHOBJECTS-----------------------------------------|
	protected Vector2D position;
	// ----------------------------------------------------|
	// DOUBLE----------------------------------------------|
	protected double xvel;
	protected double yvel;
	// ----------------------------------------------------|
	// INT-------------------------------------------------|
	protected double health;
	// ----------------------------------------------------|
	// GAMEOBJECTS-----------------------------------------|
	protected ObjectID ID;
	// ----------------------------------------------------|

	public Sprite(Vector2D position, double xvel, double yvel, int health, ObjectID ID) {
		this.position = position;
		this.xvel = xvel;
		this.yvel = yvel;
		this.health = health;
		this.ID = ID;
	}

	public abstract void update();

	public abstract void render(Graphics g);

	// GETTERS AND SETTERS
	// ------------------------------------------------------------------------------|
	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public double getXvel() {
		return xvel;
	}

	public void setXvel(double xvel) {
		this.xvel = xvel;
	}

	public double getYvel() {
		return yvel;
	}

	public void setYvel(double yvel) {
		this.yvel = yvel;
	}

	public ObjectID getID() {
		return ID;
	}

	public void setID(ObjectID iD) {
		ID = iD;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
	// ------------------------------------------------------------------------------|
}
