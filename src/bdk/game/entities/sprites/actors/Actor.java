package bdk.game.entities.sprites.actors;

import java.awt.Graphics2D;

import bdk.game.entities.sprites.Sprite;

public class Actor extends Sprite{

	//--Properties
	private String actorType;
	private String actorName;
	private int health;
	private boolean isImageAdded = false;
	//--Finals
	public static final String ACTOR_ENEMY = "enemys";
	public static final String ACTOR_PLAYER = "player";
	public static final String ACTOR_PROJECTILE = "projectiles";
	public static final String[] ACTOR_TYPES = new String[]{ACTOR_ENEMY, ACTOR_PLAYER, ACTOR_PROJECTILE};
	
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
	
	public boolean isImageAdded() {
		return isImageAdded;
	}

	public void setImageAdded(boolean isImageAdded) {
		this.isImageAdded = isImageAdded;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
