package bdk.game.entities.sprites.actors;

import java.awt.Graphics2D;
import java.util.ArrayList;

import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.controllers.emitter.Emitter;
import bdk.game.entities.sprites.actors.controllers.initializers.Initializer;
import bdk.game.entities.sprites.actors.controllers.operators.Operator;

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
	private ArrayList<Initializer> initializerList;
	private ArrayList<Operator> operatorList;
	
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
