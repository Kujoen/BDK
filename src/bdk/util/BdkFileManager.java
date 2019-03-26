package bdk.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import bdk.game.main.Game;

/**
 * FileUtil contains helper methods to load/save serialized objects.
 * 
 * @author Andreas Farley
 *
 */
public class BdkFileManager {

	// ----------------------------------------------------------------------------------|
	
	/**
	 * FileUtil contains helper methods to load/save serialized objects.
	 */
	private BdkFileManager() {
	}
	
	// ----------------------------------------------------------------------------------|

	/**
	 * Attempts to serialize an objecet to the given path.
	 * 
	 * @param o    - The object to serialize
	 * @param path - The FilePath to serialize the object to
	 * @throws FileNotFoundException
	 */
	public static void saveSerializableObject(Object o, String path) throws FileNotFoundException {
		try (FileOutputStream fos = new FileOutputStream(new File(path));
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(o);
		} catch (IOException e) {
			Game.getLogger().log(Level.WARNING,
					"Couldn't save serialized object of class: " + o.getClass().toGenericString(), e);
		}
	}

	/**
	 * Attempts to load a object from a file located at a given path.
	 * 
	 * @param path - The Path to the file
	 * @return The unserialized object. Note that you must cast it to the intended
	 *         class.
	 * @throws FileNotFoundException
	 */
	public static Object loadSerializedObject(String path) throws FileNotFoundException {
		try (FileInputStream fis = new FileInputStream(new File(path));
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			Game.getLogger().log(Level.WARNING, "Couldn't load serialized object", e);
		}

		return null;
	}
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			Game.getLogger().log(Level.WARNING, "Couldn't load texture at: " + path, e);
			return null;
		}
	}
}
