package bdk.game.entities.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import bdk.game.entities.Entity;

/**
 * Sprites are entites that are rendered on the play area. There are multiple
 * sprite types wich allow different types of interaction.
 * 
 * @author Andreas Farley
 *
 */
public abstract class Sprite extends Entity {

	// --Sprites need to keep the copy of the image they grab from bdkImageData
	// during runtime. This is because the operator/initializer might make
	// modifications to the image exclusively for this sprite. Transient because we
	// alway load the image at runtime to reduce memory usage.
	protected transient BufferedImage spriteImage;

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
		this.imagePath = "";
	}

	public abstract void render(Graphics2D g);

	// ---------------------------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// ---------------------------------------------------------------------------------------------------------------|
	public BufferedImage getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(BufferedImage spriteImage) {
		BufferedImage oldValue = this.spriteImage;
		this.spriteImage = spriteImage;
		firePropertyChange("spriteImage", oldValue, spriteImage);
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		String oldValue = this.imagePath;
		this.imagePath = imagePath;
		firePropertyChange("imagePath", oldValue, imagePath);
	}

}
