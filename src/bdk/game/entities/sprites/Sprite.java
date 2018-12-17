package bdk.game.entities.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import bdk.game.entities.Entity;

/**
 * Sprites are entities that are additionally rendered on the gamecanvas.
 * 
 * @author Andreas Farley
 *
 */
public abstract class Sprite extends Entity {
	private static final long serialVersionUID = 2586067322532032810L;

	// ---------------------------------------------------------------------|
	protected transient BufferedImage spriteImage;
	protected String spritePath;
	// ---------------------------------------------------------------------|

	public Sprite() {
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
		return spritePath;
	}

	public void setImagePath(String spritePath) {
		String oldValue = this.spritePath;
		this.spritePath = spritePath;
		firePropertyChange("imagePath", oldValue, spritePath);
	}

}
