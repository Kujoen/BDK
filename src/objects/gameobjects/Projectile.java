package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import engine.math.Hitbox;
import engine.math.Vector2D;

public class Projectile extends Sprite{

	public Projectile(Vector2D position, double xvel, double yvel, ObjectID ID) {
		super(position, xvel, yvel, ID);
		this.hitbox = new Hitbox(this , 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		position.addY(-yvel);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) position.getX(), (int) position.getY(), 10, 10);
	}
}
