package bdk.data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

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
	
	public static BufferedImage highlight(BufferedImage imageToHighlight , Color color) {
		BufferedImage highlightedImage = null;
		
		if (imageToHighlight != null){
			highlightedImage = new BufferedImage(imageToHighlight.getWidth() , imageToHighlight.getHeight() , imageToHighlight.getType());
			Graphics2D graphics2D = highlightedImage.createGraphics();
			graphics2D.drawImage(imageToHighlight, 0 , 0 , imageToHighlight.getWidth(), imageToHighlight.getHeight(), null);
			//Draw the highlights
			graphics2D.setColor(color);
			graphics2D.drawRect(0, 0, imageToHighlight.getWidth() - 1, imageToHighlight.getHeight() - 1);
			graphics2D.dispose();
		}
		
		return highlightedImage; 
	}
	
	//----------------------------------------------------------------------------------|
	//GETTERS & SETTERS
	//----------------------------------------------------------------------------------|
	public static Map<String, BufferedImage> getImageList() {
		return imageList;
	}	
	
}
