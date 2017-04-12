package objects.gameobjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import engine.math.Hitbox;
import engine.math.Vector2D;

public abstract class Sprite {
	// MATHOBJECTS-----------------------------------------|
	protected Vector2D position;
	protected Vector2D movementvector;
	// BOOLEAN---------------------------------------------|
	protected boolean isanimated;
	// ----------------------------------------------------|
	// DOUBLE----------------------------------------------|
	protected double xvel;
	protected double yvel;
	// ----------------------------------------------------|
	// INT-------------------------------------------------|
	protected int health;
	// ----------------------------------------------------|
	// STRING----------------------------------------------|
	protected String spritefilename;
	//-----------------------------------------------------|
	// IMAGE/SPRITESHEET-----------------------------------|
	protected BufferedImage spritefile;
	//-----------------------------------------------------|
	// GAMEOBJECTS-----------------------------------------|
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
	public Sprite(Vector2D position, Vector2D movementvector, int health, ObjectID ID, String spritefilename, boolean isanimated) {
		this.position = position;
		
		this.movementvector = movementvector;
		this.xvel = movementvector.getX();
		this.yvel = movementvector.getY();
		
		this.health = health;
		this.ID = ID;
		
		//Load the sprite image
		this.spritefilename = spritefilename;
		try {
			spritefile = ImageIO.read(new File("res/images/" + spritefilename + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.isanimated = isanimated;
		
		this.requestRemoveList = new ArrayList<>();
		this.requestSpawnList = new ArrayList<>();
	}

	public abstract void update();

	public abstract void render(Graphics g);
	
	public abstract void loadNewSubImage(int sectionid);
	
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
