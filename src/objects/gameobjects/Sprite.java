package objects.gameobjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import engine.main.Window;
import engine.math.Hitbox;
import engine.math.Vector2D;
import objects.data.ImageData;

public abstract class Sprite {
	// MATHOBJECTS-----------------------------------------|
	protected Vector2D position;
	protected Vector2D movementvector;
	// ----------------------------------------------------|
	// DOUBLE----------------------------------------------|
	protected double xvel;
	protected double yvel;
	// ----------------------------------------------------|
	// INT-------------------------------------------------|
	protected int health;
	//-----------------------------------------------------|
	// SPRITE----------------------------------------------|
	protected BufferedImage spritefile;
	// SPRITESHEET-----------------------------------------|
	protected ArrayList<BufferedImage> spritesheet;
	//-----------------------------------------------------|
	// GAMEOBJECTS-----------------------------------------|
	protected Hitbox hitbox;
	protected ObjectID ID;
	protected ArrayList<Sprite> requestRemoveList;
	protected ArrayList<Sprite> requestSpawnList;
	// ----------------------------------------------------|
	
 /**
  * 
  * @param position
  * 	Where is spawns
  * @param xvel
  * 	How fast it moves
  * @param yvel
  * 	How fast it moves
  * @param health
  * 	How much health
  * @param ID
  * 	Projectile, Enemy or Player.
  * 	
  */
	public Sprite(Vector2D position, Vector2D movementvector, int health, ObjectID ID) {
		this.position = position;
		
		this.movementvector = movementvector;
		this.xvel = movementvector.getX();
		this.yvel = movementvector.getY();
		this.health = health;
		this.ID = ID;
		this.requestRemoveList = new ArrayList<>();
		this.requestSpawnList = new ArrayList<>();
		
		// Get spritefile as single sprite or as spritesheet
		this.spritefile = ImageData.getSpriteForID(ID);
		// Check if have to get spritesheet
		if(this.spritefile == null){
			this.spritesheet = ImageData.getSpriteSheetForID(ID);
		}
		
		this.hitbox = new Hitbox(this);
	}
	
	public void checkHitbox(){
		// Check the hitboxes of non-projectiles
		if(!(this instanceof Projectile)){
			for(Sprite s : Window.getGame().getLevel().getSpriteList()){
				if(!(this instanceof Player)){
					if(s.ID == ObjectID.PLAYER_PROJECTILE){
						if(this.hitbox.getHitrec().contains(s.hitbox.getHitrec())){
							health--;
							requestRemoveList.add(s);
						}
					}
				}
			}
		}
	
	}

	public abstract void update();

	public abstract void render(Graphics g);
	
	public abstract void animationController();

	// GETTERS AND SETTERS
	// ------------------------------------------------------------------------------|
	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public double getXvel() {
		return xvel;
	}

	public void setXvel(double xvel) {
		this.xvel = xvel;
	}

	public double getYvel() {
		return yvel;
	}

	public void setYvel(double yvel) {
		this.yvel = yvel;
	}

	public ObjectID getID() {
		return ID;
	}

	public void setID(ObjectID iD) {
		ID = iD;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public ArrayList<Sprite> getRequestRemoveList() {
		return requestRemoveList;
	}

	public void setRequestRemoveList(ArrayList<Sprite> requestRemoveList) {
		this.requestRemoveList = requestRemoveList;
	}

	public ArrayList<Sprite> getRequestSpawnList() {
		return requestSpawnList;
	}

	public void setRequestSpawnList(ArrayList<Sprite> requestSpawnList) {
		this.requestSpawnList = requestSpawnList;
	}
	
	// ------------------------------------------------------------------------------|
}
