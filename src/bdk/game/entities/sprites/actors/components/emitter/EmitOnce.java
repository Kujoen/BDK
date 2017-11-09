package bdk.game.entities.sprites.actors.components.emitter;

import soliture.ui.swingextensions.expandinglist.ExpandableRow;

/**
 * 
 * @author Andreas Farley
 *	An emitter that will only emit once
 */
public class EmitOnce extends Emitter {

	private int emissionAmount;

	public EmitOnce() {
		this.emissionAmount = 0;
	}
	
	@Override
	public ExpandableRow getDataRow() {
		// TODO Auto-generated method stub
		return null;
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
