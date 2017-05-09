package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.math.Vector2D;
import objects.data.SpriteData;

public class EnergyTurret extends Sprite {

	// INT----------------------------------|
	private int imageindex = 0;
	private int tickcounter = 0;
	private int animationstate = 0;
	// --------------------------------------|
	// SPRITE-------------------------------|
	private BufferedImage currentimage;
	// --------------------------------------|
	// BOOLEAN------------------------------|
	private boolean isreverse = false;
	// --------------------------------------|

	public EnergyTurret(Vector2D position, Vector2D movementvector, int health, ObjectID ID) {
		super(position, movementvector, health, ID);
	}

	@Override
	public void update() {

		if (health <= 0) {
			requestRemoveList.add(this);
		}

		spawnProjectiles();
		animationController();
		checkHitbox();

		tickcounter++;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(currentimage, (int) position.getX(), (int) position.getY(), null);

		g.setColor(Color.RED);
		g.drawRect((int) hitbox.getHitrec().getX(), (int) hitbox.getHitrec().getY(),
				(int) hitbox.getHitrec().getWidth(), (int) hitbox.getHitrec().getHeight());
	}

	@Override
	public void animationController() {
		int currentStateNumber = 0;
		double angleToPlayer = 0.0;
		currentStateNumber = (int) (angleToPlayer / (Math.PI / 24));
		switch (currentStateNumber) {
		case 0:
			currentimage = spritesheet.get(6);
			break;
		case 1:
			currentimage = spritesheet.get(7);
			break;
		case 2:
			currentimage = spritesheet.get(8);
			break;
		case 3:
			currentimage = spritesheet.get(9);
			break;
		case 4:
			currentimage = spritesheet.get(10);
			break;
		case 5:
			currentimage = spritesheet.get(11);
			break;
		case 6:
			currentimage = spritesheet.get(12);
			break;
		case 7:
			currentimage = spritesheet.get(13);
			break;
		case 8:
			currentimage = spritesheet.get(14);
			break;
		case 9:
			currentimage = spritesheet.get(15);
			break;
		case 10:
			currentimage = spritesheet.get(6);
			break;
		case 11:
			currentimage = spritesheet.get(7);
			break;
		case 12:
			currentimage = spritesheet.get(8);
			break;
		case 13:
			currentimage = spritesheet.get(9);
			break;
		case 14:
			currentimage = spritesheet.get(10);
			break;
		case 15:
			currentimage = spritesheet.get(11);
			break;
		case 16:
			currentimage = spritesheet.get(12);
			break;
		case 17:
			currentimage = spritesheet.get(13);
			break;
		case 18:
			currentimage = spritesheet.get(14);
			break;
		case 19:
			currentimage = spritesheet.get(15);
			break;
		case 20:
			currentimage = spritesheet.get(16);
			break;
		case 21:
			currentimage = spritesheet.get(13);
			break;
		case 22:
			currentimage = spritesheet.get(14);
			break;
		case 23:
			currentimage = spritesheet.get(15);
			break;
		}
	}

	private void spawnProjectiles() {

	}
}