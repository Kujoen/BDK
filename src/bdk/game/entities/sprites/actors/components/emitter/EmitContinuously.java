package bdk.game.entities.sprites.actors.components.emitter;

import bdk.editor.actor.componentpanel.rows.ComponentRow;

/**
 * 
 * @author Andreas Farley
 * 
 */
public class EmitContinuously extends Emitter {

	private int emissionPerTick;
	private int emissionRate;
	private int emissionDelay;
	private boolean hasEmissionDelay;

	public EmitContinuously() {
		this.emissionPerTick = 0;
		this.emissionRate = 0;
		this.emissionDelay = 0;
		this.hasEmissionDelay = false;
	}

	@Override
	public ComponentRow getComponentRow() {
		// TODO Auto-generated method stub
		return null;
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
