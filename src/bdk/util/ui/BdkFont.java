package bdk.util.ui;

import java.awt.Font;

/**
 * 
 * @author Andreas Farley
 *
 */
public class BdkFont extends Font {

	/**
	 * Interface here to change font throughout bdk
	 * 
	 * @param type
	 *            Use a valid font from the Font class
	 * @param size
	 *            Size of the font
	 */
	public BdkFont(int type, int size) {
		super("Terminal", type, size);
	}
}
