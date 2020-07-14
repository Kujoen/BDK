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
	
	public static String CHANGE_ACTOR_EMITTER = "CHANGE_ACTOR_EMITTER";
	public static String CHANGE_ACTOR_TYPE = "CHANGE_ACTOR_TYPE";
	public static String CHANGE_ACTOR_COLLECTION = "CHANGE_ACTOR_COLLECTION";
	public static String CHANGE_ACTOR_INITIALIZER = "CHANGE_ACTOR_INITIALIZER";
	public static String CHANGE_ACTOR_OPERATOR = "CHANGE_ACTOR_OPERATOR";
	public static String CHANGE_ACTOR_CHILD = "CHANGE_ACTOR_CHILD";
	
	// ---------------------------------------------------------------------|
	// --Properties
	private ActorType type;
	private String collectionName;
	
	// --Components
	private Emitter emitter;
	private List<Initializer> initializerList;
	private List<Operator> operatorList;
	private List<Actor> childList;
	
	// --Sprites
	private transient List<ActorSprite> spriteList;
	private transient List<ActorSprite> spriteRemoveRequestList;

	// ---------------------------------------------------------------------|
	
	public Actor(String entityName, String collectionName) {
		super(entityName);
		
		this.setCollectionName(collectionName);
		this.initializerList = new ArrayList<>();
		this.operatorList = new ArrayList<>();
		this.childList = new ArrayList<>();
		this.spriteList = new ArrayList<>();
		this.spriteRemoveRequestList = new ArrayList<>();
		
		this.type = ActorType.ENEMY;
	}
	
	public void reset() {
		// Reset self
		spriteList = new ArrayList<>();
		this.spriteRemoveRequestList = new ArrayList<>();
		
		// Reset components
		if(emitter != null) {
			emitter.reset();	
		}
		
		initializerList.stream().forEach(initializer -> initializer.reset());
		operatorList.stream().forEach(operator -> operator.reset());
		childList.stream().forEach(child -> child.reset());
	}

	// RENDER & UPDATE -----------------------------------------------------|

	@Override
	public void update() {
		if(emitter != null) {
			emitter.emit();
		}
		
		spriteList.parallelStream().forEach(actorSprite -> actorSprite.update());
		
		// Remove dead ActorSprites
		if(!spriteRemoveRequestList.isEmpty()) {
			spriteList.removeAll(spriteRemoveRequestList);
			spriteRemoveRequestList.clear();
		}
	}

	@Override
	public void render(Graphics2D g) {
		spriteList.stream().forEach(actorSprite -> {
			actorSprite.render(g);
		});
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
		firePropertyChange(CHANGE_ACTOR_EMITTER, oldValue, emitter);
	}

	public List<Initializer> getInitializerList() {
		return initializerList;
	}
	
	public void addInitializer(Initializer newInitializer) {
		this.getInitializerList().add(newInitializer);
		firePropertyChange(CHANGE_ACTOR_INITIALIZER, null, newInitializer);
	}
	
	public void removeInitializer(Initializer initializerToRemove) {
		this.getInitializerList().remove(initializerToRemove);
		firePropertyChange(CHANGE_ACTOR_INITIALIZER, initializerToRemove, null);
	}

	public List<Actor> getChildList() {
		return childList;
	}
	
	public void addChild(Actor newChild) {
		this.getChildList().add(newChild);
		firePropertyChange(CHANGE_ACTOR_CHILD, null, newChild);
	}
	
	public void removeChild(Actor childToRemove) {
		this.getChildList().remove(childToRemove);
		firePropertyChange(CHANGE_ACTOR_CHILD, childToRemove, null);
	}

	public List<Operator> getOperatorList() {
		return operatorList;
	}
	
	public void addOperator(Operator newOperator) {
		this.getOperatorList().add(newOperator);
		firePropertyChange(CHANGE_ACTOR_OPERATOR, null, newOperator);
	}
	
	public void removeOperator(Operator operatorToRemove ) {
		this.getOperatorList().remove(operatorToRemove);
		firePropertyChange(CHANGE_ACTOR_OPERATOR, operatorToRemove, null);
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		String oldValue = this.getCollectionName();
		this.collectionName = collectionName;
		firePropertyChange(CHANGE_ACTOR_COLLECTION, oldValue, collectionName);
	}

	public ActorType getActorType() {
		return type;
	}

	public void setActorType(ActorType type) {
		ActorType oldValue = this.getActorType();
		this.type = type;
		firePropertyChange(CHANGE_ACTOR_TYPE, oldValue, type);
	}

	public List<ActorSprite> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(List<ActorSprite> spriteList) {
		this.spriteList = spriteList;
	}

	public List<ActorSprite> getSpriteRemoveRequestList() {
		return spriteRemoveRequestList;
	}

	public void setSpriteRemoveRequestList(List<ActorSprite> spriteRemoveRequestList) {
		this.spriteRemoveRequestList = spriteRemoveRequestList;
	}

	public void setInitializerList(List<Initializer> initializerList) {
		this.initializerList = initializerList;
	}

	public void setOperatorList(List<Operator> operatorList) {
		this.operatorList = operatorList;
	}

	public void setChildList(List<Actor> childList) {
		this.childList = childList;
	}
}
