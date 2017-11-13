package bdk.game.entities.sprites.actors.components.emitter;

import bdk.editor.actor.componentpanel.rows.ComponentRow;

/**
 * 
 * @author Andreas Farley An emitter that will only emit once
 */
public class EmitOnce extends Emitter {

	private int emissionAmount;

	public EmitOnce() {
		this.emissionAmount = 0;
	}

	@Override
	public ComponentRow getComponentRow() {
		
		ComponentRow newRow = new ComponentRow("Emit Once");
		return newRow;
		
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
