package bdk.game.entities.sprites.actors.components.emitter;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.componentpanel.SelectComponentDialog;
import bdk.game.entities.sprites.BasicSprite;
import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.Actor;
import bdk.util.BDKFileManager;
import bdk.util.listeners.BDKDocumentListener;
import bdk.util.ui.BDKFont;
import bdk.util.ui.BDKIcons;
import bdk.util.ui.BDKInputFilter;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;

/**
 * 
 * @author Andreas Farley An emitter that will only emit once
 */
public class EmitOnce extends Emitter {

	private static final long serialVersionUID = 1L;
	private int emissionAmount;
	private transient boolean isFirstEmission = true;
	
	
	public static final String CHANGE_EMISSION_AMOUNT = "CHANGE_EMISSION_AMOUNT";
	
	
	public EmitOnce(Actor parentActor) {
		super(parentActor);
		this.emissionAmount = 0;
	}
	
	public void emit() {
		if(isFirstEmission) {
			
			
			isFirstEmission = false;
		} else {
			return;
		}
	}
	
	@Override
	public JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor) {	
		// Title Row --------------------------------------------------|
		JExpandableRow componentRow = new JExpandableRow(6);
		componentRow.setDataObject(this);
		
		JLabel emitterTitleRowLabel = new JLabel("Emit Once");
		emitterTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 24));
		
		JButton emitterDeleteButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_DELETE)));
		emitterDeleteButton.setBorderPainted(false);
		emitterDeleteButton.setFocusPainted(false);
		emitterDeleteButton.setContentAreaFilled(false);
		emitterDeleteButton.addActionListener( (e) -> {
		});
		
		JButton emitterTitleButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		emitterTitleButton.setBorderPainted(false);
		emitterTitleButton.setFocusPainted(false);
		emitterTitleButton.setContentAreaFilled(false);
		emitterTitleButton.addActionListener( (e) -> {
			if(componentRow.isExpanded()) {
				emitterTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
				componentRow.setExpanded(false);
			} else {
				emitterTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_LESS)));
				componentRow.setExpanded(true);
			}	
		});
		
		componentRow.addComponent(new JExpandableRowComponent(emitterTitleRowLabel, 1, 3));
		componentRow.addComponent(new JExpandableRowComponent(emitterDeleteButton, 4, 1));
		componentRow.addComponent(new JExpandableRowComponent(emitterTitleButton, 5, 1));
		
		// Emission Amount Row ---------------------------------------|
		JExpandableRow emissionAmountRow = new JExpandableRow(6);
		
		JLabel emissionAmountLabel = new JLabel("Emission amount");
		emissionAmountLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField emissionAmountTextField = new JTextField();
		AbstractDocument emissionAmountDocument = (AbstractDocument) emissionAmountTextField.getDocument();
		emissionAmountDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
		emissionAmountDocument.addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				int oldValue = ((EmitOnce) componentRow.getDataObject()).getEmissionAmount();
				int newValue =  Integer.parseInt(emissionAmountTextField.getText());
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_EMISSION_AMOUNT, oldValue, newValue ));
			}
		});
		
		emissionAmountRow.addComponent(new JExpandableRowComponent(emissionAmountLabel, 1, 1));
		emissionAmountRow.addComponent(new JExpandableRowComponent(emissionAmountTextField, 3, 3));
		
		componentRow.addRow(emissionAmountRow);
		return componentRow;
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
