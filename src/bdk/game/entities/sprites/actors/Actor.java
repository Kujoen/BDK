package bdk.game.entities.sprites.actors;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.game.entities.sprites.actors.components.emitter.Emitter;
import bdk.game.entities.sprites.actors.components.initializers.Initializer;
import bdk.game.entities.sprites.actors.components.operators.Operator;

/**
 * 
 * @author Andreas Farley
 *
 */
public class Actor extends Sprite {
	
	public static String[] ACTOR_TYPES = new String[] {"enemy", "player", "projectile"};
	
	// ---------------------------------------------------------------------|
	// --Properties
	private String type;
	private String collectionName;
	
	// --Components
	private Emitter emitter;
	private List<Initializer> initializerList;
	private List<Operator> operatorList;
	private List<Actor> childList;

	// ---------------------------------------------------------------------|
	
	public Actor(String entityName, String collectionName) {
		super(entityName);
		
		this.setCollectionName(collectionName);
		this.initializerList = new ArrayList<>();
		this.operatorList = new ArrayList<>();
		this.childList = new ArrayList<>();
	}

	// ---------------------------------------------------------------------|

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	// ---------------------------------------------------------------------|
	// GETTERS & SETTERS
	// ---------------------------------------------------------------------|

	public Emitter getEmitter() {
		return emitter;
	}

	public void setEmitter(Emitter emitter) {
		Emitter oldValue = this.emitter;
		this.emitter = emitter;
		firePropertyChange("setEmitter", oldValue, emitter);
	}

	public List<Initializer> getInitializerList() {
		return initializerList;
	}

	public void setInitializerList(List<Initializer> initializerList) {
		List<Initializer> oldValue = this.initializerList;
		this.initializerList = initializerList;
		firePropertyChange("setInitializerList", oldValue, initializerList);
	}

	public List<Actor> getChildList() {
		return childList;
	}

	public void setChildList(List<Actor> childList) {
		List<Actor> oldValue = this.childList;
		this.childList = childList;
		firePropertyChange("setChildList", oldValue, childList);
	}

	public List<Operator> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(ArrayList<Operator> operatorList) {
		List<Operator> oldValue = this.operatorList;
		this.operatorList = operatorList;
		firePropertyChange("setOperatorList", oldValue, operatorList);
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getActorType() {
		return type;
	}

	public void setActorType(String type) {
		this.type = type;
	}
}
