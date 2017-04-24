package objects.data;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

import javax.imageio.ImageIO;

import engine.main.Game;
import engine.main.Menu;
import engine.main.Window;
import objects.gameobjects.ObjectID;

public class ImageData {
	
	// IMAGES-----------------------------------|
	private static BufferedImage play_scrolling_background;
	private static BufferedImage play_background_section_a;
	private static BufferedImage play_background_section_b;
	private static BufferedImage play_background_section_c;
	private static BufferedImage play_background_section_d;
	private static BufferedImage mainmenu_background;
	private static BufferedImage player;
	private static BufferedImage player_projectile;
	private static BufferedImage mainmenu_title;
	private static BufferedImage mainmenu_button_story;
	private static BufferedImage mainmenu_button_endless;
	private static BufferedImage mainmenu_button_options;
	//------------------------------------------|
	
	public static void loadimages(){
		try {
			play_background_section_a = ImageIO.read(new File("res/sprites/play_background_section_a.png"));
			play_background_section_b = ImageIO.read(new File("res/sprites/play_background_section_b.png"));
			play_background_section_c = ImageIO.read(new File("res/sprites/play_background_section_c.png"));
			play_background_section_d = ImageIO.read(new File("res/sprites/play_background_section_d.png"));
			play_scrolling_background = ImageIO.read(new File("res/sprites/play_scrolling_background.png"));
			player = ImageIO.read(new File("res/sprites/player.png"));
			player_projectile = ImageIO.read(new File("res/sprites/player_projectile.png"));
			mainmenu_title = ImageIO.read(new File("res/sprites/mainmenu_title.png"));
			mainmenu_button_story = ImageIO.read(new File("res/sprites/mainmenu_button_story.png"));
			mainmenu_button_endless = ImageIO.read(new File("res/sprites/mainmenu_button_endless.png"));
			mainmenu_button_options = ImageIO.read(new File("res/sprites/mainmenu_button_options.png"));
			mainmenu_background = ImageIO.read(new File("res/sprites/mainmenu_background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void scaleimages(){
		play_background_section_a = scale(play_background_section_a, Window.getACTUALWIDTH(), Game.getACTUAL_PUFFER_HEIGHT());
		play_background_section_b = scale(play_background_section_b, Game.getACTUAL_PUFFER_WIDTH(), Game.getACTUAL_PLAY_HEIGHT());
		play_background_section_c = scale(play_background_section_c, Game.getACTUAL_PUFFER_WIDTH(), Game.getACTUAL_PLAY_HEIGHT());
		play_background_section_d = scale(play_background_section_d,  Window.getACTUALWIDTH(), Game.getACTUAL_PUFFER_HEIGHT());
		play_scrolling_background = scale(play_scrolling_background, Game.getACTUAL_PLAY_WIDTH(), Game.getACTUAL_PLAY_HEIGHT());
		player = scale(player, SpriteData.getACTUAL_PLAYER_SIZE(), SpriteData.getACTUAL_PLAYER_SIZE());
		player_projectile = scale(player_projectile, SpriteData.getACTUAL_PLAYER_PROJECTILE_SIZE(), SpriteData.getACTUAL_PLAYER_PROJECTILE_SIZE());
		mainmenu_title = scale(mainmenu_title, Menu.getACTUAL_TITLE_WIDTH(), Menu.getACTUAL_TITLE_HEIGHT());
		mainmenu_background = scale(mainmenu_background, Window.getACTUALWIDTH(), Window.getACTUALHEIGHT());
		mainmenu_button_story = scale(mainmenu_button_story, Menu.getACTUAL_BUTTON_WIDTH(), Menu.getACTUAL_BUTTON_HEIGHT());
		mainmenu_button_endless = scale(mainmenu_button_endless, Menu.getACTUAL_BUTTON_WIDTH(), Menu.getACTUAL_BUTTON_HEIGHT());
		mainmenu_button_options = scale(mainmenu_button_options, Menu.getACTUAL_BUTTON_WIDTH(), Menu.getACTUAL_BUTTON_HEIGHT());
	}
	
	private static BufferedImage scale(BufferedImage imagetoscale, int nWidth, int nHeight){
		 BufferedImage scaledImage = null;
	        if (imagetoscale != null) {
	            scaledImage = new BufferedImage(nWidth, nHeight, imagetoscale.getType());
	            Graphics2D graphics2D = scaledImage.createGraphics();
	            graphics2D.drawImage(imagetoscale, 0, 0, nWidth, nHeight, null);
	            graphics2D.dispose();
	        }
	        return scaledImage;
	}
	
	public static BufferedImage getSpriteForID(ObjectID ID){
		switch(ID){
		case PLAYER:
			return player;
		case PLAYER_PROJECTILE:
			return player_projectile;
		}
		return null;
	}
	
	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public static BufferedImage getPlay_scrolling_background() {
		return play_scrolling_background;
	}

	public static void setPlay_scrolling_background(BufferedImage play_scrolling_background) {
		ImageData.play_scrolling_background = play_scrolling_background;
	}

	public static BufferedImage getPlay_background() {
		return play_background_section_a;
	}

	public static void setPlay_background(BufferedImage play_background) {
		ImageData.play_background_section_a = play_background;
	}

	public static BufferedImage getMainmenu_background() {
		return mainmenu_background;
	}

	public static void setMainmenu_background(BufferedImage mainmenu_background) {
		ImageData.mainmenu_background = mainmenu_background;
	}

	public static BufferedImage getMainmenu_title() {
		return mainmenu_title;
	}

	public static void setMainmenu_title(BufferedImage mainmenu_title) {
		ImageData.mainmenu_title = mainmenu_title;
	}

	public static BufferedImage getMainmenu_button_story() {
		return mainmenu_button_story;
	}

	public static void setMainmenu_button_story(BufferedImage mainmenu_button_story) {
		ImageData.mainmenu_button_story = mainmenu_button_story;
	}

	public static BufferedImage getMainmenu_button_endless() {
		return mainmenu_button_endless;
	}

	public static void setMainmenu_button_endless(BufferedImage mainmenu_button_endless) {
		ImageData.mainmenu_button_endless = mainmenu_button_endless;
	}

	public static BufferedImage getMainmenu_button_options() {
		return mainmenu_button_options;
	}

	public static void setMainmenu_button_options(BufferedImage mainmenu_button_options) {
		ImageData.mainmenu_button_options = mainmenu_button_options;
	}

	public static BufferedImage getPlay_background_section_a() {
		return play_background_section_a;
	}

	public static void setPlay_background_section_a(BufferedImage play_background_section_a) {
		ImageData.play_background_section_a = play_background_section_a;
	}

	public static BufferedImage getPlay_background_section_b() {
		return play_background_section_b;
	}

	public static void setPlay_background_section_b(BufferedImage play_background_section_b) {
		ImageData.play_background_section_b = play_background_section_b;
	}

	public static BufferedImage getPlay_background_section_c() {
		return play_background_section_c;
	}

	public static void setPlay_background_section_c(BufferedImage play_background_section_c) {
		ImageData.play_background_section_c = play_background_section_c;
	}

	public static BufferedImage getPlay_background_section_d() {
		return play_background_section_d;
	}

	public static void setPlay_background_section_d(BufferedImage play_background_section_d) {
		ImageData.play_background_section_d = play_background_section_d;
	}

}
