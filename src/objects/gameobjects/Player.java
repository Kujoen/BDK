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
	private int animationtickcounter = 0;
	private int tickcounter = 0;
	private int lastsectionid = 1;
	// -----------------------------------------------------------|
	// SPRITE-----------------------------------------------------|
	private BufferedImage currentimage;
	
	public Player(Vector2D position, Vector2D movementvector, int health, ObjectID ID, boolean isanimated) {
		super(position, movementvector, health, ID);
	}

	// UPDATING--------------------------------------------------------------------------|
	/**
	 * Updates the player sprite
	 */
	@Override
	public void update() {		
		movePlayer();
		animationController();
		spawnPlayerProjectiles();
		updateHitbox();
		checkHitbox();
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
			if(position.getY() < (Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.getActual_player_sprite_size())){
				if((position.getY() + yvel) > (Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.getActual_player_sprite_size())){
					position.setY((Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT() - SpriteData.getActual_player_sprite_size()));
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
			if(position.getX() < (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - SpriteData.getActual_player_sprite_size())){
				if((position.getX() + xvel) >  (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - SpriteData.getActual_player_sprite_size())){
					position.setX( (Game.getACTUAL_PUFFER_WIDTH() + Game.getACTUAL_PLAY_WIDTH() - SpriteData.getActual_player_sprite_size()));
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
	
	@Override
	public void animationController() {
		switch(animationtickcounter % 80){
			case 0:
				spritefile = spritesheet.get(0);
				break;
			case 10:
				spritefile = spritesheet.get(1);
				break;
			case 20:
				spritefile = spritesheet.get(2);
				break;
			case 30:
				spritefile = spritesheet.get(3);
				break;
			case 40:
				spritefile = spritesheet.get(4);
				break;
			case 50:
				spritefile = spritesheet.get(5);
				break;

			case 60:
				spritefile = spritesheet.get(6);
				break;

			case 70:
				spritefile = spritesheet.get(7);
				break;
		}
		
		animationtickcounter++;
	}

	/**
	 * check if the player is holding spacebar, if yes Player Projectiles will
	 * be added to the requestSpawnList
	 */
	public void spawnPlayerProjectiles() {
		if (Input.isSpacebar()) {
			if (tickcounter % 4 == 0) {
				
				requestSpawnList.add(new Projectile(
						new Vector2D(this.position.getX() + (SpriteData.getActual_player_sprite_size() / 2) - (SpriteData.getActual_player_projectile_sprite_size() / 2),this.position.getY()), 
						new Vector2D(0, SpriteData.getActual_player_projectile_speed()), 
						0, 
						ObjectID.PLAYER_PROJECTILE));
				
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


}
