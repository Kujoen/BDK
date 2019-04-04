package bdk.editor.actor.componentpanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.game.entities.sprites.actors.components.Component;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.game.entities.sprites.actors.components.emitter.Emitter;

public class ComponentPanel extends BdkActorEditorPanel {

	Boolean hasBeenBuilt = false;

	JPanel contentPane;
	//JExpandingList expandingList;

//	// --Main rows
//	TitleRow rowEmitter;
//	TitleRow rowInitializer;
//	TitleRow rowOperator;
//	TitleRow rowChildren;

	public ComponentPanel(BdkActorEditor parent) {
		super(parent);
		this.setLayout(new GridLayout(1, 1));
	}

	private void buildList() {

//		// If the list has already been built theres no reason to rebuild the entire
//		// thing...
//		if (hasBeenBuilt) {
//			//TODO: Dont rebuild the entire list once its already been built
//		}
//
//		this.removeAll();
//		//expandingList = new JExpandingList(40, 12);
//		//this.add(expandingList);
//
//		// EMITTER************************************************************|
//		rowEmitter = new TitleRow("Emitter");
//
//		// -Add Button
//		rowEmitter.getAddButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				Emitter selectedEmitter = (Emitter) SelectComponentDialog.showSelectionDialog(
//						Component.getSelectableEmittersFor(bdkActorEditor.getCurrentActor()).toArray(),
//						"Select an Emitter");
//				if (selectedEmitter != null) {
//					bdkActorEditor.getCurrentActor().setEmitter(selectedEmitter);
//				}
//			}
//		});
//		// -Info Button
//		rowEmitter.getInfoButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO: Implement an info-window for all titlerows
//			}
//		});
//
//		// -Subrows
//		if (bdkActorEditor.getCurrentActor().getEmitter() != null) {
//
//			//rowEmitter.addRow(bdkActorEditor.getCurrentActor().getEmitter().getComponentRow());
//			// Enable the expand button
//			rowEmitter.getExpandButton().setEnabled(true);
//		}
//
//		// INITIALIZERS************************************************************|
//		rowInitializer = new TitleRow("Initializers");
//
//		// OPERATORS***************************************************************|
//		rowOperator = new TitleRow("Operators");
//
//		// CHILDREN****************************************************************|
//		rowChildren = new TitleRow("Children");

//		expandingList.addRow(rowEmitter);
//		expandingList.addRow(rowInitializer);
//		expandingList.addRow(rowOperator);
//		expandingList.addRow(rowChildren);
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
