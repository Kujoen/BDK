package bdk.game.entities.sprites.actors.components.emitter;

import bdk.game.entities.sprites.actors.Actor;

/**
 * 
 * @author Andreas Farley
 * 
 */
public class EmitContinously extends Emitter {

	private int emissionPerTick;
	private int emissionRate;
	private int emissionDelay;
	private boolean hasEmissionDelay;

	public EmitContinously(Actor parentActor) {
		super(parentActor);
		this.emissionPerTick = 0;
		this.emissionRate = 0;
		this.emissionDelay = 0;
		this.hasEmissionDelay = false;
	}


	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public int getEmissionPerTick() {
		return emissionPerTick;
	}

	public void setEmissionPerTick(int emissionPerTick) {
		this.emissionPerTick = emissionPerTick;
	}

	public int getEmissionRate() {
		return emissionRate;
	}

	public void setEmissionRate(int emissionRate) {
		this.emissionRate = emissionRate;
	}

	public int getEmissionDelay() {
		return emissionDelay;
	}

	public void setEmissionDelay(int emissionDelay) {
		this.emissionDelay = emissionDelay;
	}

	public boolean isHasEmissionDelay() {
		return hasEmissionDelay;
	}

	public void setHasEmissionDelay(boolean hasEmissionDelay) {
		this.hasEmissionDelay = hasEmissionDelay;
	}

}
