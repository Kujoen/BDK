package bdk.game.entities.sprites.actors.components.operators;

import java.awt.Font;
import java.beans.PropertyChangeEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;

import bdk.editor.actor.BDKActorEditor;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorSprite;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.util.BDKFileManager;
import bdk.util.listeners.BDKDocumentListener;
import bdk.util.ui.BDKFont;
import bdk.util.ui.BDKIcons;
import bdk.util.ui.BDKInputFilter;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;

public class LifespanDecay extends Operator {
	private static final long serialVersionUID = 1L;
	private static final String CHANGE_DURATION = "CHANGE_DURATION";

	//Duration in seconds until the lifespan decays
	private int duration;
	
	/**
	 * Default lifespan decay of 2 seconds
	 * @param parentActor
	 */
	public LifespanDecay(Actor parentActor) {
		super(parentActor);
		this.duration = 120;
	}
	
	public LifespanDecay(Actor parentActor, ActorSprite actorSprite, int duration) {
		super(parentActor);
		this.actorSprite = actorSprite;
		this.duration = duration;
	}

	@Override
	protected void updateActorSprite() {
		if(componentTick >= duration) {
			actorSprite.setRemovalRequested(true);
		}
	}

	@Override
	public void reset() {
		componentTick = 0;
	}
	
	@Override
	public Operator copyForActorSprite(ActorSprite actorSprite) {
		return new LifespanDecay(parentActor, actorSprite, duration);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// Static methods
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	public static boolean rejectForActor(Actor targetActor) {
		return targetActor.getOperatorList().stream().anyMatch(operator -> operator.getClass() == LifespanDecay.class);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// GUI
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	@Override
	public JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor) {
		// Title Row --------------------------------------------------|
		JExpandableRow componentRow = new JExpandableRow(6);
		componentRow.setDataObject(this);
		
		JLabel lifespanDecayTitleRowLabel = new JLabel("Lifespan Decay");
		lifespanDecayTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 24));
		
		JButton lifespanDecayDeleteButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_DELETE)));
		lifespanDecayDeleteButton.setBorderPainted(false);
		lifespanDecayDeleteButton.setFocusPainted(false);
		lifespanDecayDeleteButton.setContentAreaFilled(false);
		lifespanDecayDeleteButton.addActionListener( (e) -> {
		});
		
		JButton lifespanDecayTitleButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		lifespanDecayTitleButton.setBorderPainted(false);
		lifespanDecayTitleButton.setFocusPainted(false);
		lifespanDecayTitleButton.setContentAreaFilled(false);
		lifespanDecayTitleButton.addActionListener( (e) -> {
			if(componentRow.isExpanded()) {
				lifespanDecayTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
				componentRow.setExpanded(false);
			} else {
				lifespanDecayTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_LESS)));
				componentRow.setExpanded(true);
			}	
		});
		
		componentRow.addComponent(new JExpandableRowComponent(lifespanDecayTitleRowLabel, 1, 3));
		componentRow.addComponent(new JExpandableRowComponent(lifespanDecayDeleteButton, 4, 1));
		componentRow.addComponent(new JExpandableRowComponent(lifespanDecayTitleButton, 5, 1));
		
		// Duration Row ---------------------------------------|
		JExpandableRow durationRow = new JExpandableRow(6);
		
		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField durationTextField = new JTextField();
		durationTextField.setText(Integer.toString(this.getDuration()));
		AbstractDocument durationDocument = (AbstractDocument) durationTextField.getDocument();
		durationDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
		durationTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				int oldValue = ((LifespanDecay) componentRow.getDataObject()).getDuration();
				int newValue =  Integer.parseInt(durationTextField.getText());
				
				setDuration(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_DURATION, oldValue, newValue ));
			}
		});
		
		durationRow.addComponent(new JExpandableRowComponent(durationLabel, 1, 3));
		durationRow.addComponent(new JExpandableRowComponent(durationTextField, 4, 2));
		
		componentRow.addRow(durationRow);
		return componentRow;
	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
