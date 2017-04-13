package objects.data;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.main.Window;

public class ImageData {
	
	// IMAGES-----------------------------------|
	private static BufferedImage scrolling_background;
	private static BufferedImage general_background;
	//------------------------------------------|
	
	public static void loadimages(){
		try {
			general_background = ImageIO.read(new File("res/images/background_placeholder.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void scaleimages(double height_scaling_factor, double width_scaling_factor){
		general_background = scale(general_background, Window.getACTUALWIDTH(), Window.getACTUALHEIGHT());
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
	
	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|
	
	public static BufferedImage getScrolling_background() {
		return scrolling_background;
	}

	public static void setScrolling_background(BufferedImage scrolling_background) {
		ImageData.scrolling_background = scrolling_background;
	}

	public static BufferedImage getGeneral_background() {
		return general_background;
	}

	public static void setGeneral_background(BufferedImage general_background) {
		ImageData.general_background = general_background;
	}

}
