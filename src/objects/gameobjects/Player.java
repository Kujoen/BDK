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
	
	public Player(Vector2D position, Vector2D movementvector, int health, ObjectID ID, boolean isanimated) {
		super(position, movementvector, health, ID, isanimated);
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
			
		}
		
		if (Input.isBackward()) {
			if(position.getY() < (Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.getACTUAL_PLAYER_SIZE())){
				if((position.getY() + yvel) > (Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.getACTUAL_PLAYER_SIZE())){
					position.setY((Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.getACTUAL_PLAYER_SIZE()));
				}else{
					position.addY(yvel);
				}
			}
			
		}

		if (Input.isLeft()) {
			if (position.getX() > Game.getACTUAL_PUFFER_WIDTH()) {
				if((position.getX() - xvel) < Game.getACTUAL_PUFFER_WIDTH()){
					position.setX(Game.getACTUAL_PUFFER_WIDTH());
				}else{
					position.addX(-xvel);
				}
			}
			
		}

		if (Input.isRight()) {
			if(position.getX() < (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - SpriteData.getACTUAL_PLAYER_SIZE())){
				if((position.getX() + xvel) >  (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - SpriteData.getACTUAL_PLAYER_SIZE())){
					position.setX( (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - SpriteData.getACTUAL_PLAYER_SIZE()));
				}else{
					position.addX(xvel);
				}
			}
			
		}
		
		if(!Input.isRight() && !Input.isLeft() && !Input.isForward() && !Input.isBackward()){
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
						new Vector2D(this.position.getX() + SpriteData.getACTUAL_PLAYER_SIZE() / 2 - SpriteData.getACTUAL_PLAYER_PROJECTILE_SIZE() / 2,this.position.getY()), 
						new Vector2D(0,-16), 
						0, 
						ObjectID.PLAYER_PROJECTILE,
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
		g.drawImage(spritefile, (int)position.getX(), (int)position.getY(), null);
	}
	// ---------------------------------------------------------------------------|

	@Override
	public void animationController() {
	
	}
}
