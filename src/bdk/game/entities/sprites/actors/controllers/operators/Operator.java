package bdk.game.entities.sprites.actors.controllers.operators;

import bdk.game.entities.sprites.actors.Actor;

public abstract class Operator {

	protected Actor actor;
	protected int operatorTick = 0;
	
	public void update(){
		updateActor();
		operatorTick++;
	}
	
	protected abstract void updateActor();
	
}
