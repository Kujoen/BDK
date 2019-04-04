package bdk.game.entities.sprites.actors.components.emitter;

import bdk.game.entities.sprites.actors.Actor;

/**
 * 
 * @author Andreas Farley An emitter that will only emit once
 */
public class EmitOnce extends Emitter {

	private static final long serialVersionUID = 1L;
	private int emissionAmount;

	public EmitOnce(Actor parentActor) {
		super(parentActor);
		this.emissionAmount = 0;
	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public int getEmissionAmount() {
		return emissionAmount;
	}

	public void setEmissionAmount(int emissionAmount) {
		this.emissionAmount = emissionAmount;
	}
}
