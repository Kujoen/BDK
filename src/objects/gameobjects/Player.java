package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import engine.input.Input;
import engine.main.Game;
import engine.math.Hitbox;
import engine.math.Vector2D;
import objects.data.SpriteData;

public class Player extends Sprite {

	// BOOLEAN----------------------------------------------------|
	private boolean hasShifted = false;
	// -----------------------------------------------------------|
	// INT--------------------------------------------------------|
	private int tickcounter = 0;
	private int lastsectionid = 1;
	// -----------------------------------------------------------|
	// IMAGE------------------------------------------------------|
	private Image currentimage;
	// -----------------------------------------------------------|
	
	public Player(Vector2D position, Vector2D movementvector, int health, ObjectID ID, String spritefilename, boolean isanimated) {
		super(position, movementvector, health, ID, spritefilename, isanimated);
		
		currentimage = (Image) spritefile.getSubimage(25, 0, 26, 25);
		
	}

	// UPDATING--------------------------------------------------------------------------|
	/**
	 * Updates the player sprite
	 */
	@Override
	public void update() {
		movePlayer();
		spawnPlayerProjectiles();
	}

	/**
	 * moves the player based on input
	 */
	private void movePlayer() {
		
		if (Input.isForward()) {
			if ((position.getY() > Game.getACTUAL_PUFFER_HEIGHT())) {
				if((position.getY() -yvel) < Game.getACTUAL_PUFFER_HEIGHT()){
					position.setY(Game.getACTUAL_PUFFER_HEIGHT());
				}else{
					position.addY(-yvel);
				}		
			}
			
			loadNewSubImage(3);
		}
		
		if (Input.isBackward()) {
			if(position.getY() < (Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.PLAYER_SIZE)){
				if((position.getY() + yvel) > (Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.PLAYER_SIZE)){
					position.setY((Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.PLAYER_SIZE));
				}else{
					position.addY(yvel);
				}
			}
			
			loadNewSubImage(4);
		}

		if (Input.isLeft()) {
			if (position.getX() > Game.getACTUAL_PUFFER_WIDTH()) {
				if((position.getX() - xvel) < Game.getACTUAL_PUFFER_WIDTH()){
					position.setX(Game.getACTUAL_PUFFER_WIDTH());
				}else{
					position.addX(-xvel);
				}
			}
			
			loadNewSubImage(0);
		}

		if (Input.isRight()) {
			if(position.getX() < (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - 1 - SpriteData.PLAYER_SIZE)){
				if((position.getX() + xvel) >  (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - 1 - SpriteData.PLAYER_SIZE)){
					position.setX( (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - 1 - SpriteData.PLAYER_SIZE));
				}else{
					position.addX(xvel);
				}
			}
			
			loadNewSubImage(2);
		}
		
		if(!Input.isRight() && !Input.isLeft() && !Input.isForward() && !Input.isBackward()){
			loadNewSubImage(1);
		}

		if (Input.isShift()) {
			if (!hasShifted) {
				yvel /= 4;
				xvel /= 4;
				hasShifted = true;
			}
		} else if (hasShifted == true) {
			yvel *= 4;
			xvel *= 4;
			hasShifted = false;
		}
	}

	/**
	 * check if the player is holding spacebar, if yes Player Projectiles will
	 * be added to the requestSpawnList
	 */
	public void spawnPlayerProjectiles() {
		if (Input.isSpacebar()) {
			if (tickcounter % 4 == 0) {
				
				requestSpawnList.add(new Projectile(
						new Vector2D(this.position.getX() + SpriteData.PLAYER_SIZE / 2 - SpriteData.PLAYER_PROJECTILE_SIZE / 2,this.position.getY()), 
						new Vector2D(0,-10), 
						0, 
						ObjectID.PROJECTILE,
						"player_projectilesheet",
						false));
				
				tickcounter++;
			}
			tickcounter++;
		}
	}

	// --------------------------------------------------------------------------|
	// RENDERING-----------------------------------------------------------------|
	@Override
	public void render(Graphics g) {
		g.drawImage(currentimage, (int)position.getX(), (int)position.getY(), null);
	}
	// ---------------------------------------------------------------------------|

	@Override
	public void loadNewSubImage(int sectionid) {
		switch(sectionid){
		case 0:
			if(!(sectionid == lastsectionid)){
				currentimage = (Image) spritefile.getSubimage(0, 0, 26, 25);
				
				lastsectionid = sectionid;
			}
			break;
		case 1:
			if(!(sectionid == lastsectionid)){
				currentimage = (Image) spritefile.getSubimage(25, 0, 26, 25);
				
				lastsectionid = sectionid;
			}
			break;
		case 2:
			if(!(sectionid == lastsectionid)){
				currentimage = (Image) spritefile.getSubimage(50, 0, 26, 25);
				
				lastsectionid = sectionid;
			}
			break;
		case 3:
			if(!(sectionid == lastsectionid)){
				currentimage = (Image) spritefile.getSubimage(0, 24, 26, 25);
				
				lastsectionid = sectionid;
			}
			break;
		case 4:
			if(!(sectionid == lastsectionid)){
				currentimage = (Image) spritefile.getSubimage(25, 24, 26, 25);
				
				lastsectionid = sectionid;
			}
			break;
		}
	}

	@Override
	public void animationController() {
	
	}
}
