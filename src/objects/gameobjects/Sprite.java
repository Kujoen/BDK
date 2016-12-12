package objects.gameobjects;

import java.awt.Graphics;
import java.util.Set;

import engine.math.Hitbox;
import engine.math.Vector2D;

public abstract class Sprite {

	protected Vector2D position;
	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getMovementVector() {
		return movementVector;
	}

	public void setMovementVector(Vector2D movementVector) {
		this.movementVector = movementVector;
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

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	protected Vector2D movementVector = new Vector2D(0, 0);
	protected double xvel;
	protected double yvel;
	protected ObjectID ID;
	protected Hitbox hitbox;

	public Sprite(Vector2D position, double xvel, double yvel, ObjectID ID) {
		this.position = position;
		this.xvel = xvel;
		this.yvel = yvel;
		this.ID = ID;
	}

	public abstract void update();

	public abstract void render(Graphics g);

}
