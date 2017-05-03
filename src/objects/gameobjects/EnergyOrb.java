package objects.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.math.Grid;
import engine.math.Vector2D;
import objects.data.SpriteData;

public class EnergyOrb extends Sprite {

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

	public EnergyOrb(Vector2D position, Vector2D movementvector, int health, ObjectID ID) {
		super(position, movementvector, health, ID);
	}

	@Override
	public void update() {
		spawnProjectiles();
		animationController();

		tickcounter++;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(currentimage, (int) position.getX(), (int) position.getY(), null);
	}

	@Override
	public void animationController() {
		// State 0 is spawn state
		if (animationstate == 0) {
			switch (tickcounter) {
			case 0:
				currentimage = spritesheet.get(6);
				break;
			case 10:
				currentimage = spritesheet.get(7);
				break;
			case 20:
				currentimage = spritesheet.get(8);
				break;
			case 30:
				currentimage = spritesheet.get(9);
				break;
			case 40:
				currentimage = spritesheet.get(10);
				break;
			case 50:
				currentimage = spritesheet.get(11);
				break;
			case 60:
				currentimage = spritesheet.get(12);
				break;
			case 70:
				currentimage = spritesheet.get(13);
				break;
			case 80:
				currentimage = spritesheet.get(14);
				break;
			case 90:
				currentimage = spritesheet.get(15);
				break;
			case 100:
				currentimage = spritesheet.get(16);
				animationstate = 1;
				break;
			}
		}
		// State 1 is default state
		else if (animationstate == 1) {
			if (tickcounter % 10 == 0) {
				currentimage = spritesheet.get(imageindex);

				imageindex += isreverse ? -1 : 1;

				if (imageindex > 5) {
					imageindex = 5;
					isreverse = true;
				} else if (imageindex < 0) {
					imageindex = 0;
					isreverse = false;
				}
			}
		}
		// State 2 is death state
		else {

		}
	}

	private void spawnProjectiles() {
		if (animationstate == 1) {
			if (tickcounter % 10 == 0) {
				for (double currentangle = 0.0; currentangle < 2 * Math.PI; currentangle += (Math.PI / 16)) {
					requestSpawnList.add(new Projectile(
							new Vector2D(
									position.getX() + (SpriteData.getActual_energyorb_sprite_size() / 2)
											- (SpriteData.getActual_energyorb_projectile_sprite_size() / 2),
									position.getY() + (SpriteData.getActual_energyorb_sprite_size() / 2)
											- (SpriteData.getActual_energyorb_projectile_sprite_size() / 2)),
							new Vector2D(Math.sin(currentangle) * SpriteData.getActual_player_projectile_speed() / 4,
									Math.cos(currentangle) * SpriteData.getActual_player_projectile_speed() / 4),
							0, ObjectID.ENERGYORB_PROJECTILE));

				}
			}
		}
	}

}
