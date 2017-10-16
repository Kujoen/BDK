package bdk.data;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import bdk.game.component.Component;

public class BdkImageData {

	// Spritelist contains the images of all components
	private static Map<String, BufferedImage> imageList = new HashMap<String, BufferedImage>();

	public static void addImage(String imagePath) {

		// TODO: ADD CODE THAT SCALES IMAGES BASED ON RESOLUTION

		try {
			imageList.put(imagePath, ImageIO.read(new File(imagePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BufferedImage scale(BufferedImage imagetoscale, int nWidth, int nHeight) {
		BufferedImage scaledImage = null;
		if (imagetoscale != null) {
			scaledImage = new BufferedImage(nWidth, nHeight, imagetoscale.getType());
			Graphics2D graphics2D = scaledImage.createGraphics();
			graphics2D.drawImage(imagetoscale, 0, 0, nWidth, nHeight, null);
			graphics2D.dispose();
		}
		return scaledImage;
	}
	
	//----------------------------------------------------------------------------------|
	//GETTERS & SETTERS
	//----------------------------------------------------------------------------------|
	public static Map<String, BufferedImage> getImageList() {
		return imageList;
	}	
	
}
