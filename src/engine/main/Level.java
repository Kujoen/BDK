package engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.text.html.HTMLDocument.Iterator;

import engine.input.Input;
import engine.math.Vector2D;
import objects.data.ImageData;
import objects.data.SpriteData;
import objects.gameobjects.Dummy;
import objects.gameobjects.ObjectID;
import objects.gameobjects.Player;
import objects.gameobjects.Projectile;
import objects.gameobjects.Sprite;

public class Level {
	// ENGINEOBJECTS-------------------------------------------|
	private Game game;
	// --------------------------------------------------------|
	// GAMEOBJECTS---------------------------------------------|
	private BufferedImage play_background_section_a;
	private BufferedImage play_background_section_b;
	private BufferedImage play_background_section_c;
	private BufferedImage play_background_section_d;
	private BufferedImage play_scrolling_background1;
	private BufferedImage play_scrolling_background2;
	private Player player;
	private ArrayList<Sprite> spriteList = new ArrayList<>();
	private ArrayList<Sprite> removeList;
	private ArrayList<Sprite> spawnList;
	// --------------------------------------------------------|
	private int scrolling_background1x = Game.getACTUAL_PUFFER_WIDTH();
	private int scrolling_background1y = Game.getACTUAL_PUFFER_HEIGHT();
	private int scrolling_background2x = Game.getACTUAL_PUFFER_WIDTH();
	private int scrolling_background2y = Game.getACTUAL_PUFFER_HEIGHT() - Game.getACTUAL_PLAY_HEIGHT();
	// INT-----------------------------------------------------|
	private int levelid = 0;
	private int tickcounter = 0;
	// --------------------------------------------------------|
	// BOOLEAN-------------------------------------------------|
	private boolean isLoaded;
	// --------------------------------------------------------|

	public Level(Game game) {
		this.game = game;
		this.player = null;
		this.isLoaded = false;

        //Add the player to the spriteList
		this.player = new Player(
				new Vector2D(game.getWindow().getACTUALWIDTH() / 2, game.getWindow().getACTUALHEIGHT() / 2),
				new Vector2D(SpriteData.getACTUAL_PLAYER_SPEED(), SpriteData.getACTUAL_PLAYER_SPEED()), 
				1, 
				ObjectID.PLAYER,
				false);
		spriteList.add(player);

	}

	// RENDERING--------------------------------------------------------------------|
	/**
	 * Renders the background and then the sprites
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		renderScrollingBackground(g);
		renderSprites(g);
		renderBackgroundFragments(g);
	}

	private void renderScrollingBackground(Graphics g){
			g.drawImage(play_scrolling_background1, scrolling_background1x, scrolling_background1y, null);
			g.drawImage(play_scrolling_background2, scrolling_background2x, scrolling_background2y, null);
	}
	/**
	 * Draws the background. Contains logic to loop the background
	 * 
	 * @param g
	 */
	private void renderBackgroundFragments(Graphics g) {
	
		if(!isLoaded){
			play_background_section_a = ImageData.getPlay_background_section_a();
			play_background_section_b = ImageData.getPlay_background_section_b();
			play_background_section_c = ImageData.getPlay_background_section_c();
			play_background_section_d = ImageData.getPlay_background_section_d();
			play_scrolling_background1= ImageData.getPlay_scrolling_background();
			play_scrolling_background2= ImageData.getPlay_scrolling_background();
			isLoaded = true;
		}
		// Then draw the background fragments
		g.drawImage(play_background_section_a, 0, 0, null);
		g.drawImage(play_background_section_b, 0, Game.getACTUAL_PUFFER_HEIGHT(), null);
		g.drawImage(play_background_section_c, Game.getACTUAL_PLAY_WIDTH() + Game.getACTUAL_PUFFER_WIDTH(), Game.getACTUAL_PUFFER_HEIGHT(), null);
		g.drawImage(play_background_section_d, 0, Game.getACTUAL_PUFFER_HEIGHT() + Game.getACTUAL_PLAY_HEIGHT(), null); 
		
	}

	/**
	 * Calls the render method in every sprite
	 * 
	 * @param g
	 */
	private void renderSprites(Graphics g) {
		// Render all sprites
		for (Sprite s : spriteList) {
			s.render(g);
		}
	}

	// -----------------------------------------------------------------------------|
	// UPDATING---------------------------------------------------------------------|
	/**
	 * Checks if the levelFile/player is spawning sprites, then updates the
	 * background, followed by the sprite positions, and then the sprite count
	 */
	public void update() {
		// Update the background and the positions of already existing sprites
		updateScrollingBackground();
		updateSprites();
		updateLevel();
		
		// Update sprite lists
		updateDeleteRequests();
		updateSpawnRequests();
	}

	/**
	 * moves the background
	 */
	private void updateScrollingBackground() {
		scrolling_background1y += SpriteData.getACTUAL_SCROLLING_SPEED();
		scrolling_background2y += SpriteData.getACTUAL_SCROLLING_SPEED();
		
		if(scrolling_background1y == Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT()){
			scrolling_background1y = Game.getACTUAL_PUFFER_HEIGHT() - Game.getACTUAL_PLAY_HEIGHT();
		}
		
		if(scrolling_background2y == Game.getACTUAL_PLAY_HEIGHT() + Game.getACTUAL_PUFFER_HEIGHT()){
			scrolling_background2y = Game.getACTUAL_PUFFER_HEIGHT() - Game.getACTUAL_PLAY_HEIGHT();
		}
	}

	/**
	 * calls the update method is all sprites
	 */
	private void updateSprites() {
		// Update sprite positions
		for (Sprite s : spriteList) {
			s.update();
		}
	}
	
	private void updateLevel(){
		switch(levelid){
		case 0:
			updateLevel1(tickcounter);
			break;
		}
		
		tickcounter++;
	}
	
	private void updateLevel1(int tickcount){
		switch(tickcount){
		case 180:
			spriteList.add(new Dummy(
						new Vector2D(Game.getACTUAL_PUFFER_WIDTH() + (Game.getACTUAL_PLAY_WIDTH() / 2) ,	-SpriteData.getACTUAL_DUMMY_SIZE()), 
						new Vector2D(0,3), 
						10, 
						ObjectID.DUMMY, 
						false));

			break;
		}
	}

	/**
	 * checks if any sprites have been added to the delete list and removes them
	 * from the main list
	 */
	private void updateDeleteRequests() {
		removeList = new ArrayList<>();

		for (Sprite s : spriteList) {
			removeList.addAll(s.getRequestRemoveList());
			s.getRequestRemoveList().clear();
		}

		if (!removeList.isEmpty()) {
			spriteList.removeAll(removeList);
		}
	}

	/**
	 * checks if any sprites have been added to the spawn list and adds them to
	 * the main list
	 * 
	 * @return
	 */
	private void updateSpawnRequests() {
		spawnList = new ArrayList<>();

		for (Sprite s : spriteList) {
			spawnList.addAll(s.getRequestSpawnList());
			s.getRequestSpawnList().clear();
		}

		if (!spawnList.isEmpty()) {
			spriteList.addAll(spawnList);
		}
	}

	// ------------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// ------------------------------------------------------------------------------|
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public ArrayList<Sprite> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(ArrayList<Sprite> spriteList) {
		this.spriteList = spriteList;
	}

	public ArrayList<Sprite> getRemoveList() {
		return removeList;
	}

	public void setRemoveList(ArrayList<Sprite> removeList) {
		this.removeList = removeList;
	}

	public ArrayList<Sprite> getSpawnList() {
		return spawnList;
	}

	public void setSpawnList(ArrayList<Sprite> spawnList) {
		this.spawnList = spawnList;
	}

	// -----------------------------------------------------------------------------|
}
