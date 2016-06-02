package objects.gameobjects;

import java.awt.Graphics;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float xvel, yvel;
	protected ObjectID ID;

	public GameObject(float x, float y,  ObjectID ID) {
		this.x = x;
		this.y = y;
		this.ID = ID;
	}

	public abstract void update(LinkedList<GameObject> object);

	public abstract void render(Graphics g);

	public ObjectID getID() {
		return ID;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getXvel() {
		return xvel;
	}

	public void setXvel(float xvel) {
		this.xvel = xvel;
	}

	public float getYvel() {
		return yvel;
	}

	public void setYvel(float yvel) {
		this.yvel = yvel;
	}

}
