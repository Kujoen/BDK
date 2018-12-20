package bdk.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import bdk.game.main.Game;

/**
 * Provides utility to make copying objects more easy
 * 
 * @author Andreas Farley
 *
 */
public class BdkCopy {
	// --------------------------------------------------------------|

	private BdkCopy() {
	}

	// --------------------------------------------------------------|

	/**
	 * Returns a deep copy of an object. Uses serialization to do this
	 * 
	 * @param object that is serializable
	 * @return copy of the object
	 */
	public static Object deepCopySerializableObject(Object object) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			objectOutputStream.writeObject(object);

			ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(bais);

			return objectInputStream.readObject();
		} catch (Exception e) {
			Game.getLogger().log(Level.SEVERE, "Failed to generate copy of object", e);
			return null;
		}
	}

	/**
	 * Return a deep copy of a BufferedImage
	 * 
	 * @param bufferedImage to copy
	 * @return a copy of the bufferedImage
	 */
	public static BufferedImage deepCopyBufferedImage(BufferedImage bufferedImage) {
		ColorModel cm = bufferedImage.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bufferedImage.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	// --------------------------------------------------------------|
}
