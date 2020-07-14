package bdk.game.entities.sprites.actors.components.operators;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorSprite;
import bdk.game.entities.sprites.actors.components.Component;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Operator extends Component{
	private static final long serialVersionUID = -2248102734337074687L;
	
	public Operator(Actor parentActor) {
		super(parentActor);
	}
	
	public void update(){
		updateActorSprite();
		componentTick++;
	}
	
	protected abstract void updateActorSprite();
	public abstract Operator copyForActorSprite(ActorSprite actorSprite);
	
	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

}
