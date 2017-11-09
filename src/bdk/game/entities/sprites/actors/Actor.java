package bdk.game.entities.sprites.actors;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.components.emitter.Emitter;
import bdk.game.entities.sprites.actors.components.initializers.Initializer;
import bdk.game.entities.sprites.actors.components.operators.Operator;

/**
 * 
 * @author Andreas Farley
 *
 */
public class Actor extends Sprite{

	//--Properties
	private String actorType;
	private String actorName;
	private int health;
	//--Finals
	public static final String ACTOR_ENEMY = "enemy";
	public static final String ACTOR_PLAYER = "player";
	public static final String ACTOR_PROJECTILE = "projectile";
	public static final String[] ACTOR_TYPES = new String[]{ACTOR_ENEMY, ACTOR_PLAYER, ACTOR_PROJECTILE};
	//--Controllers
	private Emitter emitter;
	private List<Initializer> initializerList;
	private List<Operator> operatorList;
	private List<Actor> childList;
	
	public Actor(String actorName) {
		super(0, 0, Sprite.TYPE_ACTOR);
		this.actorName = actorName;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
	}

	//---------------------------------------------------------------------|
	//GETTERS & SETTERS
	//---------------------------------------------------------------------|
	
	public Emitter getEmitter() {
		return emitter;
	}

	public void setEmitter(Emitter emitter) {
		this.emitter = emitter;
	}

	public List<Initializer> getInitializerList() {
		return initializerList;
	}

	public void setInitializerList(ArrayList<Initializer> initializerList) {
		this.initializerList = initializerList;
	}

	public List<Operator> getOperatorList() {
		return operatorList;
	}

	public List<Actor> getChildList() {
		return childList;
	}

	public void setChildList(List<Actor> childList) {
		this.childList = childList;
	}

	public void setOperatorList(ArrayList<Operator> operatorList) {
		this.operatorList = operatorList;
	}
	
	public String getActorType() {
		return actorType;
	}

	public void setActorType(String actorType) {
		this.actorType = actorType;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
