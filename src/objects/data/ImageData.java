package objects.data;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

import javax.imageio.ImageIO;

import engine.main.Game;
import engine.main.Window;
import objects.gameobjects.ObjectID;

public class ImageData {
	
	// IMAGES-----------------------------------|
	private static BufferedImage play_scrolling_background;
	private static BufferedImage play_background;
	private static BufferedImage mainmenu_background;
	private static BufferedImage player;
	private static BufferedImage player_projectile;
	//------------------------------------------|
	
	public static void loadimages(){
		try {
			play_background = ImageIO.read(new File("res/sprites/play_background.png"));
			play_scrolling_background = ImageIO.read(new File("res/sprites/play_scrolling_background.png"));
			mainmenu_background = ImageIO.read(new File("res/sprites/mainmenu_background.png"));
			player = ImageIO.read(new File("res/sprites/player.png"));
			player_projectile = ImageIO.read(new File("res/sprites/player_projectile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void scaleimages(){
		play_background = scale(play_background, Window.getACTUALWIDTH(), Window.getACTUALHEIGHT());
		play_scrolling_background = scale(play_scrolling_background, Game.getACTUAL_PLAY_WIDTH(), Game.getACTUAL_PLAY_HEIGHT());
		mainmenu_background = scale(mainmenu_background, Window.getACTUALWIDTH(), Window.getACTUALHEIGHT());
		player = scale(player, SpriteData.getACTUAL_PLAYER_SIZE(), SpriteData.getACTUAL_PLAYER_SIZE());
		player_projectile = scale(player_projectile, SpriteData.getACTUAL_PLAYER_PROJECTILE_SIZE(), SpriteData.getACTUAL_PLAYER_PROJECTILE_SIZE());
	}
	
	private static BufferedImage scale(BufferedImage imagetoscale, int nWidth, int nHeight){
	
		System.out.println(nWidth + "            " + nHeight);
		
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
		return play_background;
	}

	public static void setPlay_background(BufferedImage play_background) {
		ImageData.play_background = play_background;
	}

	public static BufferedImage getMainmenu_background() {
		return mainmenu_background;
	}

	public static void setMainmenu_background(BufferedImage mainmenu_background) {
		ImageData.mainmenu_background = mainmenu_background;
	}

}
