package bdk.game.entities.sprites.actors;

import java.awt.Graphics2D;

import bdk.game.entities.sprites.Sprite;
import bdk.util.BDKCopy;
import javafx.geometry.Point2D;

/**
 * 
 * @author Andreas
 *
 */
public class ActorSprite extends Sprite{

	private static final long serialVersionUID = 1L;
	
	Point2D update;
	
	public ActorSprite(Actor parentActor) {
		super("actor_sprite");
		
		this.setParent(parentActor);
	}
	
	
	// -----------------------------------------------------------------------------|
	// INITIALIZING
	// -----------------------------------------------------------------------------|
	public void initialize() {
		this.setPosition(parent.getPosition());
		this.setSpritePath(((Actor)parent).getSpritePath());
		this.setSpriteImage(BDKCopy.deepCopyBufferedImage(((Actor) parent).getSpriteImage()));
	}
	
	// -----------------------------------------------------------------------------|
	// RENDERING
	// -----------------------------------------------------------------------------|
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(this.spriteImage, (int)this.position.getX(), (int)this.position.getY(), null);
	}

	// -----------------------------------------------------------------------------|
	// UPDATING
	// -----------------------------------------------------------------------------|
	
	@Override
	public void update() {
		this.position.add(update);
	}
	
	// -----------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -----------------------------------------------------------------------------|

}
