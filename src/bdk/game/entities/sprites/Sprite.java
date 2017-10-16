package bdk.game.entities.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import bdk.game.entities.Entity;

/**
 * Sprites are entites that are rendered on the play area. There are multiple
 * sprite types wich allow different types of interaction.
 * 
 * @author Kuj
 *
 */
public abstract class Sprite extends Entity {

	protected BufferedImage spriteImage;
	protected String imagePath;
	// --
	protected String spriteType;
	public static final String TYPE_ACTOR = "actor";
	public static final String TYPE_TILE = "tile";
	// --

	/**
	 * Default constructor
	 * 
	 * @param x
	 *            x position
	 * @param y
	 *            y position
	 * 
	 */
	public Sprite(double x, double y, String spriteType) {
		super(x, y);
		this.spriteType = spriteType;
	}

	public abstract void render(Graphics2D g);

	public BufferedImage getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(BufferedImage spriteImage) {
		this.spriteImage = spriteImage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	// ---------------------------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// ---------------------------------------------------------------------------------------------------------------|
}
