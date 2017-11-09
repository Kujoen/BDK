package bdk.game.entities.sprites.actors.components.operators;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.Component;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Operator extends Component{

	protected Actor actor;
	protected int operatorTick = 0;
	
	public Operator(Actor anActor) {
		this.actor = anActor;
	}
	
	public void update(){
		updateActor();
		operatorTick++;
	}
	
	protected abstract void updateActor();
	
	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|
	
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public int getOperatorTick() {
		return operatorTick;
	}

	public void setOperatorTick(int operatorTick) {
		this.operatorTick = operatorTick;
	}

	
}
