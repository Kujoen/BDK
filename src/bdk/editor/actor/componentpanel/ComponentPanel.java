package bdk.editor.actor.componentpanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BDKActorEditorPanel;
import bdk.game.entities.sprites.actors.components.Component;
import bdk.game.entities.sprites.actors.components.emitter.Emitter;
import bdk.game.entities.sprites.actors.components.operators.Operator;
import bdk.util.BDKFileManager;
import bdk.util.ui.BDKFont;
import bdk.util.ui.BDKIcons;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;
import soliture.ui.swingextensions.expandinglist.JExpandingListPanel;

public class ComponentPanel extends BDKActorEditorPanel {

	JPanel contentPane;
	JExpandingListPanel expListPanel;

	public ComponentPanel(BDKActorEditor parent) {
		super(parent);
		this.setLayout(new GridLayout(1, 1));
		this.expListPanel = new JExpandingListPanel(15);
		this.add(expListPanel);
		setBorder(BorderFactory.createTitledBorder("Components"));
	}
	
	/**
	 * Adds the component rows.
	 */
	private void addComponentRows() {
		JExpandableRow emitterTitleRow = new JExpandableRow(6);
		addEmitterRows(emitterTitleRow);

		JExpandableRow initializerTitleRow = new JExpandableRow(6);
		addInitializerRows(initializerTitleRow);
		
		JExpandableRow operatorTitleRow = new JExpandableRow(6);
		addOperatorRows(operatorTitleRow);
		
		JExpandableRow childTitleRow = new JExpandableRow(6);
		addChildRows(childTitleRow);
		
		expListPanel.addRow(emitterTitleRow);
		expListPanel.addRow(initializerTitleRow);
		expListPanel.addRow(operatorTitleRow);
		expListPanel.addRow(childTitleRow);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// EMITTER
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	
	private void addEmitterRows(JExpandableRow emitterTitleRow) {
		// TITLE ROW ---------------------------------------------|
		JLabel emitterTitleRowLabel = new JLabel("Emitter");
		emitterTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 30));
		
		JButton addEmitterButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_ADD_CIRCLE)));
		addEmitterButton.setBorderPainted(false);
		addEmitterButton.setFocusPainted(false);
		addEmitterButton.setContentAreaFilled(false);
		addEmitterButton.addActionListener( (e) -> {
			if(bdkActorEditor.getCurrentActor() != null) {
				Emitter newEmitter = (Emitter) SelectComponentDialog.showSelectionDialog(Component.getSelectableEmittersFor(bdkActorEditor.getCurrentActor()).toArray(), "Select an Emitter");
				
				if(newEmitter != null) {
					bdkActorEditor.getCurrentActor().setEmitter(newEmitter);
					emitterTitleRow.addRow(newEmitter.getComponentRow(bdkActorEditor));
					emitterTitleRow.setExpanded(true);
				}
			}
		});
		
		JButton expandEmitterButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		expandEmitterButton.setBorderPainted(false);
		expandEmitterButton.setFocusPainted(false);
		expandEmitterButton.setContentAreaFilled(false);
		expandEmitterButton.addActionListener( (e) -> {
			if(bdkActorEditor.getCurrentActor() != null) {
				if(emitterTitleRow.isExpanded()) {
					expandEmitterButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
					emitterTitleRow.setExpanded(false);
				} else {
					expandEmitterButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_LESS)));
					emitterTitleRow.setExpanded(true);
				}
			}
		});
		
		emitterTitleRow.addComponent(new JExpandableRowComponent(emitterTitleRowLabel, 0, 4));
		emitterTitleRow.addComponent(new JExpandableRowComponent(addEmitterButton, 4, 1));
		emitterTitleRow.addComponent(new JExpandableRowComponent(expandEmitterButton, 5, 1));
		
		// ACTOR SPECIFIC EMITTER ROWS -------------------------|
		if(bdkActorEditor.getCurrentActor() != null && bdkActorEditor.getCurrentActor().getEmitter() != null) {
			emitterTitleRow.addRow(bdkActorEditor.getCurrentActor().getEmitter().getComponentRow(bdkActorEditor));
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// INITIALIZERS
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	
	private void addInitializerRows(JExpandableRow initializerTitleRow) {
		// TITLE ROW ---------------------------------------------|
		JLabel initializerTitleRowLabel = new JLabel("Initializers");
		initializerTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 30));
		
		JButton addInitializerButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_ADD_CIRCLE)));
		addInitializerButton.setBorderPainted(false);
		addInitializerButton.setFocusPainted(false);
		addInitializerButton.setContentAreaFilled(false);
		addInitializerButton.addActionListener( (e) -> {
			
		});
		
		JButton expandInitializerButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		expandInitializerButton.setBorderPainted(false);
		expandInitializerButton.setFocusPainted(false);
		expandInitializerButton.setContentAreaFilled(false);
		expandInitializerButton.addActionListener( (e) -> {
			
		});
		
		initializerTitleRow.addComponent(new JExpandableRowComponent(initializerTitleRowLabel, 0, 4));
		initializerTitleRow.addComponent(new JExpandableRowComponent(addInitializerButton, 4, 1));
		initializerTitleRow.addComponent(new JExpandableRowComponent(expandInitializerButton, 5, 1));
		
		// ACTOR SPECIFIC INITIALIZER ROWS -----------------------|
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// OPERATORS
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	
	private void addOperatorRows(JExpandableRow operatorTitleRow) {
		// TITLE ROW ---------------------------------------------|
		JLabel operatorTitleRowLabel = new JLabel("Operators");
		operatorTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 30));
		
		JButton addOperatorButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_ADD_CIRCLE)));
		addOperatorButton.setBorderPainted(false);
		addOperatorButton.setFocusPainted(false);
		addOperatorButton.setContentAreaFilled(false);
		addOperatorButton.addActionListener( (e) -> {
			if(bdkActorEditor.getCurrentActor() != null) {
				Operator newOperator = (Operator) SelectComponentDialog.showSelectionDialog(Component.getSelectableOperatorsFor(bdkActorEditor.getCurrentActor()).toArray(), "Select an Emitter");
				
				if(newOperator  != null) {
					bdkActorEditor.getCurrentActor().addOperator(newOperator);
					operatorTitleRow.addRow(newOperator.getComponentRow(bdkActorEditor));
					operatorTitleRow.setExpanded(true);
				}
			}
		});
		
		JButton expandOperatorButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		expandOperatorButton.setBorderPainted(false);
		expandOperatorButton.setFocusPainted(false);
		expandOperatorButton.setContentAreaFilled(false);
		expandOperatorButton.addActionListener( (e) -> {
			if(operatorTitleRow.isExpanded()) {
				expandOperatorButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
				operatorTitleRow.setExpanded(false);
			} else {
				expandOperatorButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_LESS)));
				operatorTitleRow.setExpanded(true);
			}
		});
		
		operatorTitleRow.addComponent(new JExpandableRowComponent(operatorTitleRowLabel, 0, 4));
		operatorTitleRow.addComponent(new JExpandableRowComponent(addOperatorButton, 4, 1));
		operatorTitleRow.addComponent(new JExpandableRowComponent(expandOperatorButton, 5, 1));
		
		// ACTOR SPECIFIC OPERATOR ROWS ---------------------------|
		if(bdkActorEditor.getCurrentActor() != null && bdkActorEditor.getCurrentActor().getOperatorList().isEmpty() == false) {
			bdkActorEditor.getCurrentActor().getOperatorList().stream().forEach( operator -> operatorTitleRow.addRow(operator.getComponentRow(bdkActorEditor)));
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// CHILD
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	
	private void addChildRows(JExpandableRow childTitleRow) {
		// TITLE ROW ---------------------------------------------|
		JLabel childTitleRowLabel = new JLabel("Children");
		childTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 30));
		
		JButton addChildButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_ADD_CIRCLE)));
		addChildButton.setBorderPainted(false);
		addChildButton.setFocusPainted(false);
		addChildButton.setContentAreaFilled(false);
		addChildButton.addActionListener( (e) -> {
			
		});
		
		JButton expandChildButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		expandChildButton.setBorderPainted(false);
		expandChildButton.setFocusPainted(false);
		expandChildButton.setContentAreaFilled(false);
		expandChildButton.addActionListener( (e) -> {
			
		});
		
		childTitleRow.addComponent(new JExpandableRowComponent(childTitleRowLabel, 0, 4));
		childTitleRow.addComponent(new JExpandableRowComponent(addChildButton, 4, 1));
		childTitleRow.addComponent(new JExpandableRowComponent(expandChildButton, 5, 1));
		
		// ACTOR SPECIFIC CHILD ROWS -----------------------------|
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// NOTIFY METHOD
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	
	/**
	 * We only want to buildList when the actor changed
	 */
	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {		
		if(event.getPropertyName().equals(BDKActorEditor.CHANGE_ACTOR) && event.getNewValue() != null && event.getNewValue() != event.getOldValue()) {
			expListPanel.removeRows();
			addComponentRows();
		}
	}
}
