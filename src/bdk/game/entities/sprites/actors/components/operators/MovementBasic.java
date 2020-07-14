package bdk.game.entities.sprites.actors.components.operators;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;

import bdk.editor.actor.BDKActorEditor;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorSprite;
import bdk.game.entities.sprites.actors.components.Component;
import bdk.game.entities.sprites.actors.components.emitter.EmitContinously;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.game.main.Game;
import bdk.util.BDKFileManager;
import bdk.util.listeners.BDKDocumentListener;
import bdk.util.ui.BDKFont;
import bdk.util.ui.BDKIcons;
import bdk.util.ui.BDKInputFilter;
import bdk.util.ui.BDKWarningDialog;
import javafx.geometry.Point2D;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;

public class MovementBasic extends Operator {
	
	private static final long serialVersionUID = 1L;
	
	public static final String CHANGE_INITIAL_SPEED = "CHANGE_INITIAL_SPEED";
	public static final String CHANGE_ACCELERATION_AMOUNT = "CHANGE_ACCELERATION_AMOUNT";
	public static final String CHANGE_MAXSPEED_AMOUNT = "CHANGE_MAXSPEED_AMOUNT";
	public static final String CHANGE_MOVEMENT_X = "CHANGE_MOVEMENT_X";
	public static final String CHANGE_MOVEMENT_Y = "CHANGE_MOVEMENT_Y";
	
	// Runtime variables
	private double speed = 0;
	private boolean isFirstUpdate = true;
	
	//Editable variables
	private double initialSpeed;
	private double acceleration;
	private double maxSpeed;
	private int movementX;
	private int movementY;
	

	public MovementBasic(Actor parentActor) {
		super(parentActor);
		
		this.initialSpeed = 1;
		this.acceleration = 0;
		this.maxSpeed = 1;
		this.movementX = 0;
		this.movementY = -1;
	}
	
	public MovementBasic(Actor parentActor, ActorSprite actorSprite, double initialSpeed, double acceleration, double maxSpeed, int movementX, int movementY) {
		super(parentActor);
		
		this.actorSprite = actorSprite;
		
		this.initialSpeed = initialSpeed;
		this.acceleration = acceleration;
		this.maxSpeed = maxSpeed;
		this.movementX = movementX;
		this.movementY = movementY;
	}

	@Override
	protected void updateActorSprite() {
		Point2D actorMovementVector = actorSprite.getMovementVector();
		
		if(isFirstUpdate) {
			speed = initialSpeed;
			isFirstUpdate = false;
		
			actorMovementVector = actorMovementVector.add(new Point2D(movementX, movementY));
		}
		
		// Movement Manipulation ------------- |
		actorMovementVector =  actorMovementVector.normalize();
		
		if(speed != 0) {
			actorMovementVector = actorMovementVector.multiply(speed);	
		}
		
		// Add acceleration ------------------ |
		if(speed != maxSpeed && acceleration != 0) {
			speed += acceleration;
			if(speed > maxSpeed) {
				speed = maxSpeed;
			}
		}
		// ----------------------------------- |
		
		actorSprite.setMovementVector(actorMovementVector);
	}
	
	@Override
	public void reset() {
		componentTick = 0;
		speed = 0;
		isFirstUpdate = true;
	}
	
	@Override
	public Operator copyForActorSprite(ActorSprite actorSprite) {
		return new MovementBasic(getParentActor(),actorSprite,  this.initialSpeed, this.acceleration, this.maxSpeed, this.movementX,this.movementY);
	}

	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// Static methods
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	public static boolean rejectForActor(Actor targetActor) {
		return targetActor.getOperatorList().stream().anyMatch(operator -> operator.getClass() == MovementBasic.class);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// GUI
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|

	@Override
	public JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor) {
		// Title Row --------------------------------------------------|
		JExpandableRow componentRow = new JExpandableRow(6);
		componentRow.setDataObject(this);
		
		JLabel operatorTitleRowLabel = new JLabel("Movement Basic");
		operatorTitleRowLabel.setFont(new BDKFont(Font.PLAIN, 24));
		
		JButton operatorDeleteButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_DELETE)));
		operatorDeleteButton.setBorderPainted(false);
		operatorDeleteButton.setFocusPainted(false);
		operatorDeleteButton.setContentAreaFilled(false);
		operatorDeleteButton.addActionListener( (e) -> {
		});
		
		JButton operatorTitleButton = new JButton(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
		operatorTitleButton.setBorderPainted(false);
		operatorTitleButton.setFocusPainted(false);
		operatorTitleButton.setContentAreaFilled(false);
		operatorTitleButton.addActionListener( (e) -> {
			if(componentRow.isExpanded()) {
				operatorTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_MORE)));
				componentRow.setExpanded(false);
			} else {
				operatorTitleButton.setIcon(new ImageIcon(BDKFileManager.loadImage(BDKIcons.ICON_EXPAND_LESS)));
				componentRow.setExpanded(true);
			}	
		});
		
		componentRow.addComponent(new JExpandableRowComponent(operatorTitleRowLabel, 1, 3));
		componentRow.addComponent(new JExpandableRowComponent(operatorDeleteButton, 4, 1));
		componentRow.addComponent(new JExpandableRowComponent(operatorTitleButton, 5, 1));
		
		// Initial Speed Row ---------------------------------------|
		JExpandableRow initialSpeedRow = new JExpandableRow(6);
		
		JLabel initialSpeedLabel = new JLabel("Initial Speed");
		initialSpeedLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField initialSpeedTextField = new JTextField();
		initialSpeedTextField.setText(Double.toString(this.getInitialSpeed()));
		AbstractDocument initialSpeedDocument = (AbstractDocument) initialSpeedTextField.getDocument();
		initialSpeedDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_DOUBLE, -100, 100));
		initialSpeedTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				double oldValue = ((MovementBasic) componentRow.getDataObject()).getInitialSpeed();
				double newValue =  Double.parseDouble(initialSpeedTextField.getText());
				
				setInitialSpeed(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_ACCELERATION_AMOUNT, oldValue, newValue ));
			}
		});
		
		initialSpeedRow.addComponent(new JExpandableRowComponent(initialSpeedLabel, 1, 3));
		initialSpeedRow.addComponent(new JExpandableRowComponent(initialSpeedTextField, 4, 2));
		
		// Acceleration Row ---------------------------------------|
		JExpandableRow accelerationRow = new JExpandableRow(6);
		
		JLabel accelerationAmountLabel = new JLabel("Acceleration amount");
		accelerationAmountLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField accelerationAmountTextField = new JTextField();
		accelerationAmountTextField.setText(Double.toString(this.getAcceleration()));
		AbstractDocument accelerationAmountDocument = (AbstractDocument) accelerationAmountTextField.getDocument();
		accelerationAmountDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_DOUBLE, -100, 100));
		accelerationAmountTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				double oldValue = ((MovementBasic) componentRow.getDataObject()).getAcceleration();
				double newValue =  Double.parseDouble(accelerationAmountTextField.getText());
				
				setAcceleration(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_ACCELERATION_AMOUNT, oldValue, newValue ));
			}
		});
		
		accelerationRow.addComponent(new JExpandableRowComponent(accelerationAmountLabel, 1, 3));
		accelerationRow.addComponent(new JExpandableRowComponent(accelerationAmountTextField, 4, 2));
		
		
		// MaxSpeed Row ---------------------------------------|
		JExpandableRow maxSpeedRow = new JExpandableRow(6);
		
		JLabel maxSpeedLabel = new JLabel("Max Speed");
		maxSpeedLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField maxSpeedTextField = new JTextField();
		maxSpeedTextField.setText(Double.toString(this.getMaxSpeed()));
		AbstractDocument maxSpeedDocument = (AbstractDocument) maxSpeedTextField.getDocument();
		maxSpeedDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_DOUBLE, -100, 100));
		maxSpeedTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				double oldValue = ((MovementBasic) componentRow.getDataObject()).getMaxSpeed();
				double newValue =  Double.parseDouble(maxSpeedTextField.getText());
				
				setMaxSpeed(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_MAXSPEED_AMOUNT, oldValue, newValue ));
			}
		});
		
		maxSpeedRow.addComponent(new JExpandableRowComponent(maxSpeedLabel, 1, 3));
		maxSpeedRow.addComponent(new JExpandableRowComponent(maxSpeedTextField, 4, 2));
		
		// MovementX Row ---------------------------------------|
		JExpandableRow movementXRow = new JExpandableRow(6);
		
		JLabel movementXLabel = new JLabel("Movement X");
		movementXLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField movementXTextField = new JTextField();
		movementXTextField.setText(Integer.toString(this.getMovementX()));
		AbstractDocument movementXDocument = (AbstractDocument) movementXTextField.getDocument();
		movementXDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, -100, 100));
		movementXTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				int oldValue = ((MovementBasic) componentRow.getDataObject()).getMovementX();
				int newValue =  Integer.parseInt(movementXTextField.getText());
				
				setMovementX(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_MOVEMENT_X, oldValue, newValue ));
			}
		});
		
		movementXRow.addComponent(new JExpandableRowComponent(movementXLabel, 1, 3));
		movementXRow.addComponent(new JExpandableRowComponent(movementXTextField, 4, 2));
		
		// MovementY Row ---------------------------------------|
		JExpandableRow movementYRow = new JExpandableRow(6);
		
		JLabel movementYLabel = new JLabel("Movement Y");
		movementYLabel.setFont(new BDKFont(Font.PLAIN, 20));
		
		JTextField movementYTextField = new JTextField();
		movementYTextField.setText(Integer.toString(this.getMovementY()));
		AbstractDocument movementYDocument = (AbstractDocument) movementYTextField.getDocument();
		movementYDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_INT, -100, 100));
		movementYTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {			
				int oldValue = ((MovementBasic) componentRow.getDataObject()).getMovementY();
				int newValue =  Integer.parseInt(movementYTextField.getText());
				
				setMovementY(newValue);
				
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, CHANGE_MOVEMENT_Y, oldValue, newValue ));
			}
		});
		
		movementYRow.addComponent(new JExpandableRowComponent(movementYLabel, 1, 3));
		movementYRow.addComponent(new JExpandableRowComponent(movementYTextField, 4, 2));
		
		
		// ADD ROWS TO PARENT ---------------------------------------|
		
		componentRow.addRow(initialSpeedRow);
		componentRow.addRow(accelerationRow);
		componentRow.addRow(maxSpeedRow);
		componentRow.addRow(movementXRow);
		componentRow.addRow(movementYRow);
		
		
		return componentRow;
	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public double getInitialSpeed() {
		return initialSpeed;
	}

	public void setInitialSpeed(double speed) {
		this.initialSpeed = speed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getMovementX() {
		return movementX;
	}

	public void setMovementX(int movementX) {
		this.movementX = movementX;
	}

	public int getMovementY() {
		return movementY;
	}

	public void setMovementY(int movementY) {
		this.movementY = movementY;
	}
	
}
