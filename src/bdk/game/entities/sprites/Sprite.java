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
	
	public static final String CHANGE_SPRITE_IMAGE = "CHANGE_SPRITE_IMAGE";
	public static final String CHANGE_SPRITE_IMAGE_PATH = "CHANGE_SPRITE_IMAGE_PATH";

	public static final String MISSING_SPRITE_PATH = "src/resources/missing_textures/missing_sprite.png";
	public static final String ENEMY_SPRITE_PATH = "/sprites/materials/actors/enemy";
	public static final String PROJECTILE_SPRITE_PATH = "/sprites/materials/actors/projectile";
	public static final String PLAYER_SPRITE_PATH = "/sprites/materials/actors/player";
	

	// ---------------------------------------------------------------------|
	protected transient BufferedImage spriteImage;
	protected String spritePath;
	// ---------------------------------------------------------------------|

	public Sprite(String entityName) {
		super(entityName);
		
		this.spritePath = MISSING_SPRITE_PATH;
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
		firePropertyChange(Sprite.CHANGE_SPRITE_IMAGE, oldValue, spriteImage);
	}

	public String getSpritePath() {
		return spritePath;
	}

	public void setSpritePath(String spritePath) {
		String oldValue = this.spritePath;
		this.spritePath = spritePath;
		firePropertyChange(Sprite.CHANGE_SPRITE_IMAGE_PATH, oldValue, spritePath);
	}

}
