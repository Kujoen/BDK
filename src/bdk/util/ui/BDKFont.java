package bdk.util.ui;

import java.awt.Font;

/**
 * 
 * @author Andreas Farley
 *
 */
public class BDKFont extends Font {

	private static final long serialVersionUID = 1L;

	/**
	 * Interface here to change font throughout bdk
	 * 
	 * @param type Use a valid font from the Font class
	 * @param size Size of the font
	 */
	public BDKFont(int type, int size) {
		super("Agency FB", type, size);
	}
}
