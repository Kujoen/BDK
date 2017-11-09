package bdk.editor.actor.componentpanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.editor.actor.componentpanel.rows.TitleRow;
import bdk.game.entities.sprites.actors.components.Component;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.game.entities.sprites.actors.components.emitter.Emitter;
import soliture.ui.swingextensions.expandinglist.JExpandingList;

public class ComponentPanel extends BdkActorEditorPanel {

	JPanel contentPane;
	JExpandingList expandingList;

	// --Main rows
	TitleRow rowEmitter;
	TitleRow rowInitializer;
	TitleRow rowOperator;
	TitleRow rowChildren;

	public ComponentPanel(BdkActorEditor parent) {
		super(parent);

		this.setLayout(new GridLayout(1, 1));

	}

	private void buildList() {
		this.removeAll();
		expandingList = new JExpandingList(40, 6);
		this.add(expandingList);

		// EMITTER*****************************************************************|
		rowEmitter = new TitleRow("Emitter");
		rowEmitter.getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Emitter selectedEmitter =(Emitter) SelectComponentDialog.showSelectionDialog(
						Component.getSelectableEmittersFor(currentActor).toArray(), "Select an Emitter");
				if(selectedEmitter != null) {
					
					//TODO: Implement the new updating system, no longe call notifyDataChanged(); from here
//					bdkActorEditor.getCurrentActor().setEmitter(selectedEmitter);
//					bdkActorEditor.notifyDataChanged();
					
				}
			}
		});
		rowEmitter.getInfoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Implement an info-window for all titlerows
			}
		});
		
		//-Add subrows of the titlerow
		if(currentActor.getEmitter() != null) {
			//There can be only one emitter so ne need for loops here
			if(currentActor.getEmitter() instanceof EmitOnce) {
				
			}
		}

		// INITIALIZERS************************************************************|
		rowInitializer = new TitleRow("Initializers");

		// OPERATORS***************************************************************|
		rowOperator = new TitleRow("Operators");

		// CHILDREN****************************************************************|
		rowChildren = new TitleRow("Children");

		expandingList.addRow(rowEmitter);
		expandingList.addRow(rowInitializer);
		expandingList.addRow(rowOperator);
		expandingList.addRow(rowChildren);
	}

	@Override
	public void notifyDataChanged() {
		this.currentActor = bdkActorEditor.getCurrentActor();
		this.currentActorCollection = bdkActorEditor.getCurrentActorCollection();

		if (currentActor != null) {
			buildList();
		}
	}
}
