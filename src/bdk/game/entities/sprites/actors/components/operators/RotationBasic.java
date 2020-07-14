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
import bdk.util.BDKFileManager;
import bdk.util.listeners.BDKDocumentListener;
import bdk.util.ui.BDKFont;
import bdk.util.ui.BDKIcons;
import bdk.util.ui.BDKInputFilter;
import javafx.geometry.Point2D;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;

public class RotationBasic extends Operator {

	private static final long serialVersionUID = 1L;
	private static final String CHANGE_RATE = "CHANGE_RATE";
	private static final String CHANGE_ANGLE = "CHANGE_ANGLE";
	
	private int rate;
	private int angle;
	
	/**
	 * Default rotation of 10 degrees every 1/6th of a second (1 second 60 degrees)
	 * @param parentActor
	 */
	public RotationBasic(Actor parentActor) {
		super(parentActor);
		this.rate = 10;
		this.angle = 10;
	}
	
	public RotationBasic(Actor parentActor, ActorSprite actorSprite, int rate, int angle) {
		super(parentActor);
		this.actorSprite = actorSprite;
		this.rate = rate;
		this.angle = angle;
	}

	@Override
	protected void updateActorSprite() {
		if(componentTick % rate == 0) {
			Point2D actorMovementVector = actorSprite.getMovementVector();
			actorMovementVector = rotate(actorMovementVector, angle);
			actorSprite.setMovementVector(actorMovementVector);
		}
		
		componentTick++;
	}

	@Override
	public void reset() {
		componentTick = 0;
	}
	
	@Override
	public Operator copyForActorSprite(ActorSprite actorSprite) {
		return new RotationBasic(parentActor, actorSprite, rate, angle);
	}
	
	private Point2D rotate(Point2D vectorToRotate, int angle) { 
		return new Point2D(
				((vectorToRotate.getX() * Math.cos(Math.toRadians(angle))) - (vectorToRotate.getY() * Math.sin(Math.toRadians(angle)))), 	// NEW X
				((vectorToRotate.getX() * Math.sin(Math.toRadians(angle))) + (vectorToRotate.getY() * Math.cos(Math.toRadians(angle)))) 	// NEW Y
		);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// Static methods
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	public static boolean rejectForActor(Actor targetActor) {
		return targetActor.getOperatorList().stream().anyMatch(operator -> operator.getClass() == RotationBasic.class);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// GUI
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	@Override
	public JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor) {
		// Title Row --------------------------------------------------|
		JExpandableRow componentRow = new JExpandableRow(6);
		componentRow.setDataObject(this);
		
		JLabel rotationBasicTitleRowLabel = new JLabel("Rotation Basic");
		rotationBasicTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 24));
		
		JButton rotationBasicDeleteButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_DELETE)));
		rotationBasicDeleteButton.setBorderPainted(false);
		rotationBasicDeleteButton.setFocusPainted(false);
		rotationBasicDeleteButton.setContentAreaFilled(false);
		rotationBasicDeleteButton.addActionListener( (e) -> {
		});
		
		JButton rotationBasicTitleButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		rotationBasicTitleButton.setBorderPainted(false);
		rotationBasicTitleButton.setFocusPainted(false);
		rotationBasicTitleButton.setContentAreaFilled(false);
		rotationBasicTitleButton.addActionListener( (e) -> {
			if(componentRow.isExpanded()) {
				rotationBasicTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
				componentRow.setExpanded(false);
			} else {
				rotationBasicTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_LESS)));
				componentRow.setExpanded(true);
			}	
		});
		
		componentRow.addComponent(new JExpandableRowComponent(rotationBasicTitleRowLabel, 1, 3));
		componentRow.addComponent(new JExpandableRowComponent(rotationBasicDeleteButton, 4, 1));
		componentRow.addComponent(new JExpandableRowComponent(rotationBasicTitleButton, 5, 1));
		
		// Rate Row ---------------------------------------|
		JExpandableRow rateRow = new JExpandableRow(6);
		
		JLabel rateLabel = new JLabel("Rate");
		rateLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField rateTextField = new JTextField();
		rateTextField.setText(Integer.toString(this.getRate()));
		AbstractDocument rateDocument = (AbstractDocument) rateTextField.getDocument();
		rateDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
		rateTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				int oldValue = ((RotationBasic) componentRow.getDataObject()).getRate();
				int newValue =  Integer.parseInt(rateTextField.getText());
				
				setRate(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_RATE, oldValue, newValue ));
			}
		});
		
		rateRow.addComponent(new JExpandableRowComponent(rateLabel, 1, 3));
		rateRow.addComponent(new JExpandableRowComponent(rateTextField, 4, 2));
		
		// Angle Row ---------------------------------------|
		JExpandableRow angleRow = new JExpandableRow(6);
		
		JLabel angleLabel = new JLabel("Angle");
		angleLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField angleTextField = new JTextField();
		angleTextField.setText(Integer.toString(this.getAngle()));
		AbstractDocument angleDocument = (AbstractDocument) angleTextField.getDocument();
		angleDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, 1, 10000));
		angleTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				int oldValue = ((RotationBasic) componentRow.getDataObject()).getAngle();
				int newValue =  Integer.parseInt(angleTextField.getText());
				
				setAngle(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_ANGLE, oldValue, newValue ));
			}
		});
		
		angleRow.addComponent(new JExpandableRowComponent(angleLabel, 1, 3));
		angleRow.addComponent(new JExpandableRowComponent(angleTextField, 4, 2));
		
		// --
		
		componentRow.addRow(rateRow);
		componentRow.addRow(angleRow);
		
		return componentRow;
	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
}