package bdk.editor.actor.componentpanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.game.entities.sprites.actors.components.Component;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.game.entities.sprites.actors.components.emitter.Emitter;

public class ComponentPanel extends BdkActorEditorPanel {

	JPanel contentPane;

	public ComponentPanel(BDKActorEditor parent) {
		super(parent);
		this.setLayout(new GridLayout(1, 1));
	}

	private void buildList() {
	}

	/**
	 * We only want to buildList when the actor changed
	 */
	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		if (bdkActorEditor.getCurrentActor() != null) {
			if(event.getPropertyName() == "setCurrentActor") {
				buildList();
			}
		}
	}
}
