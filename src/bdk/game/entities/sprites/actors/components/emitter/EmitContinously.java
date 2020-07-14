package bdk.game.entities.sprites.actors.components.emitter;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;

import bdk.editor.actor.BDKActorEditor;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorSprite;
import bdk.game.main.Game;
import bdk.util.BDKFileManager;
import bdk.util.listeners.BDKDocumentListener;
import bdk.util.ui.BDKFont;
import bdk.util.ui.BDKIcons;
import bdk.util.ui.BDKInputFilter;
import bdk.util.ui.BDKWarningDialog;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;

/**
 * 
 * @author Andreas Farley
 * 
 */
public class EmitContinously extends Emitter {
	private static final long serialVersionUID = 1L;
	private static final String CHANGE_EMISSION_AMOUNT = "CHANGE_EMISSION_AMOUNT";
	private static final String CHANGE_EMISSION_RATE = "CHANGE_EMISSION_RATE";
	private static final String CHANGE_EMISSION_DELAY = "CHANGE_EMISSION_DELAY";
	
	private int emissionAmount;
	private int emissionRate;
	private int emissionDelay;

	public EmitContinously(Actor parentActor) {
		super(parentActor);
		this.emissionAmount = 1;
		this.emissionRate = 60;
		this.emissionDelay = 0;
	}
	
	@Override
	public void emit() {
		// Time to emit ?
		if(componentTick % emissionRate == 0 && (emissionDelay == 0 || componentTick % emissionDelay == 0)) {
			for (int i = 0; i < emissionAmount; i++) {
				ActorSprite newSprite = new ActorSprite(parentActor);
				newSprite.initialize();
				parentActor.getSpriteList().add(newSprite);
			}
		}
		
		componentTick++;
	}
	
	@Override
	public void reset() {
		componentTick = 0;
	}
	
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// Static methods
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	public static boolean rejectForActor(Actor targetActor) {
		Game.getLogger().log(java.util.logging.Level.SEVERE, "Component subclass did not implement the rejectFor static method", new Exception("Not implemented by subclass"));
		BDKWarningDialog.showWarning("Component subclass did not implement the rejectFor static method");
		return true;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// GUI
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	@Override
	public JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor) {
		// Title Row --------------------------------------------------|
			JExpandableRow componentRow = new JExpandableRow(6);
			componentRow.setDataObject(this);
			
			JLabel emitterTitleRowLabel = new JLabel("Emit Continously");
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
			emissionAmountTextField.setText(Integer.toString(this.getEmissionAmount()));
			AbstractDocument emissionAmountDocument = (AbstractDocument) emissionAmountTextField.getDocument();
			emissionAmountDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
			emissionAmountTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {			
					int oldValue = ((EmitOnce) componentRow.getDataObject()).getEmissionAmount();
					int newValue =  Integer.parseInt(emissionAmountTextField.getText());
					
					setEmissionAmount(newValue);
					
					bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_EMISSION_AMOUNT, oldValue, newValue ));
				}
			});
			
			emissionAmountRow.addComponent(new JExpandableRowComponent(emissionAmountLabel, 1, 3));
			emissionAmountRow.addComponent(new JExpandableRowComponent(emissionAmountTextField, 4, 2));
			
			
			// Emission Rate Row ---------------------------------------|
			JExpandableRow emissionRateRow = new JExpandableRow(6);
			
			JLabel emissionRateLabel = new JLabel("Emission Rate");
			emissionRateLabel.setFont(new BDKFont(Font.PLAIN, 20));
			
			JTextField emissionRateTextField = new JTextField();
			emissionRateTextField.setText(Integer.toString(this.getEmissionRate()));
			AbstractDocument emissionRateDocument = (AbstractDocument) emissionRateTextField.getDocument();
			emissionRateDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
			emissionRateTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {			
					int oldValue = ((EmitContinously) componentRow.getDataObject()).getEmissionRate();
					int newValue =  Integer.parseInt(emissionRateTextField.getText());
					
					setEmissionRate(newValue);
					
					bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_EMISSION_RATE, oldValue, newValue ));
				}
			});
			
			emissionRateRow.addComponent(new JExpandableRowComponent(emissionRateLabel, 1, 3));
			emissionRateRow.addComponent(new JExpandableRowComponent(emissionRateTextField, 4, 2));
			
			// Emission Delay Row ---------------------------------------|
			JExpandableRow emissionDelayRow = new JExpandableRow(6);
			
			JLabel emissionDelayLabel = new JLabel("Emission Delay");
			emissionDelayLabel.setFont(new BDKFont(Font.PLAIN, 20));
			
			JTextField emissionDelayTextField = new JTextField();
			emissionDelayTextField.setText(Integer.toString(this.getEmissionDelay()));
			AbstractDocument emissionDelayDocument = (AbstractDocument) emissionDelayTextField.getDocument();
			emissionDelayDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
			emissionDelayTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {			
					int oldValue = ((EmitContinously) componentRow.getDataObject()).getEmissionDelay();
					int newValue =  Integer.parseInt(emissionDelayTextField.getText());
					
					setEmissionDelay(newValue);
					
					bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_EMISSION_DELAY, oldValue, newValue ));
				}
			});
			
			emissionDelayRow.addComponent(new JExpandableRowComponent(emissionDelayLabel, 1, 3));
			emissionDelayRow.addComponent(new JExpandableRowComponent(emissionDelayTextField, 4, 2));
			
			componentRow.addRow(emissionAmountRow);
			componentRow.addRow(emissionRateRow);
			componentRow.addRow(emissionDelayRow);
			
			return componentRow;
	}	

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public int getEmissionPerTick() {
		return emissionAmount;
	}

	public void setEmissionPerTick(int emissionPerTick) {
		this.emissionAmount = emissionPerTick;
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

	public int getEmissionAmount() {
		return emissionAmount;
	}

	public void setEmissionAmount(int emissionAmount) {
		this.emissionAmount = emissionAmount;
	}
}
