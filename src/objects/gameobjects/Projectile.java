package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import engine.math.Hitbox;
import engine.math.Vector2D;

public class Projectile extends Sprite{

	public Projectile(Vector2D position, double xvel, double yvel, int health , ObjectID ID) {
		super(position, xvel, yvel, health, ID);
	}
	
	// UPDATING--------------------------------------------------------------------------|
	@Override
	public void update() {
		position.addY(-yvel);
	}
	// --------------------------------------------------------------------------|
	// RENDERING-----------------------------------------------------------------|
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) position.getX(), (int) position.getY(), 10, 10);
	}
	//---------------------------------------------------------------------------|
}
