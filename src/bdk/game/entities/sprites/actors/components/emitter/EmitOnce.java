package bdk.game.entities.sprites.actors.components.emitter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.componentpanel.rows.ComponentRow;
import bdk.editor.actor.componentpanel.rows.TextFieldRow;
import bdk.editor.main.BdkMainWindow;
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

	@Override
	public ComponentRow getComponentRow() {
		ComponentRow compRow = new ComponentRow("Emit Once");
		TextFieldRow textFieldRow1 = compRow.getTextFieldRow("Emission Amount");

		textFieldRow1.getTextField().setText(Integer.toString(emissionAmount));
		textFieldRow1.getTextField().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getPropertyName() != "ancestor") {
					
					System.out.println(event.getPropertyName());
					// No other way to update everything from this listener.... maybe find a
					// solution later
					BdkActorEditor bdkActorEditor = ((BdkMainWindow) SwingUtilities
							.getWindowAncestor((JTextField) event.getSource())).getActorEditor();

					EmitOnce currentEmitter = (EmitOnce) bdkActorEditor.getCurrentActor().getEmitter();

					currentEmitter.setEmissionAmount(Math.toIntExact((long) event.getNewValue()));

					// Have to set emitter to force the update
					bdkActorEditor.getCurrentActor().setEmitter(currentEmitter);
				}
			}
		});

		compRow.addRow(textFieldRow1);
		return compRow;
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
