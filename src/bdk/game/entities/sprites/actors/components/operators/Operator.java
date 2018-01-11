package bdk.game.entities.sprites.actors.components.operators;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.Component;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Operator extends Component{

	protected int operatorTick = 0;
	
	public Operator(Actor parentActor) {
		super(parentActor);
	}
	
	public void update(){
		updateActor();
		operatorTick++;
	}
	
	protected abstract void updateActor();
	
	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public int getOperatorTick() {
		return operatorTick;
	}

	public void setOperatorTick(int operatorTick) {
		this.operatorTick = operatorTick;
	}

	
}
