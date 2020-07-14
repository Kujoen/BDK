package bdk.game.entities.sprites.actors;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.components.initializers.Initializer;
import bdk.game.entities.sprites.actors.components.operators.Operator;
import bdk.util.BDKCopy;
import javafx.geometry.Point2D;

/**
 * 
 * @author Andreas
 *
 */
public class ActorSprite extends Sprite{

	private static final long serialVersionUID = 1L;
	
	private List<Operator> operatorList;
	private boolean isRemovalRequested;
	private Point2D movementVector;
	
	public ActorSprite(Actor parentActor) {
		super("actor_sprite");
		
		this.setParent(parentActor);
		this.operatorList = new ArrayList<>();
	}
	
	
	// -----------------------------------------------------------------------------|
	// INITIALIZING
	// -----------------------------------------------------------------------------|
	public void initialize() {
		this.setPosition(parent.getPosition());
		this.setSpritePath(((Actor)parent).getSpritePath());
		this.setSpriteImage(BDKCopy.deepCopyBufferedImage(((Actor) parent).getSpriteImage()));
		this.setMovementVector(new Point2D(0,0));
		
		for(Operator operator : ((Actor) parent).getOperatorList()) {
			this.operatorList.add(operator.copyForActorSprite(this));
		}
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
		//Update the sprite operators
		operatorList.stream().forEach(operator -> operator.update());
		
		//Check if self needs to be deleted
		if(isRemovalRequested) {
			((Actor) parent).getSpriteRemoveRequestList().add(this);
		}
		System.out.println(movementVector);
		//Update Sprite position
		position = position.add(movementVector);
	}
	
	// -----------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -----------------------------------------------------------------------------|

	public List<Operator> getOperatorList() {
		return operatorList;
	}


	public void setOperatorList(List<Operator> operatorList) {
		this.operatorList = operatorList;
	}


	public boolean isRemovalRequested() {
		return isRemovalRequested;
	}


	public void setRemovalRequested(boolean isRemovalRequested) {
		this.isRemovalRequested = isRemovalRequested;
	}


	public Point2D getMovementVector() {
		return movementVector;
	}


	public void setMovementVector(Point2D movementVector) {
		this.movementVector = movementVector;
	}
	
}
