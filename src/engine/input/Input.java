package engine.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Contains all the listeners and changes values if a listener is triggered
 * 
 * @author Soliture
 */

public class Input {

	private static int keycode = 0;
	private static int keycode2 = 0;

	public static KeyListener KeyInputListener = new KeyListener() {

		// TODO Cleanup method
		@Override
		public void keyPressed(KeyEvent e) {
			if (!(e.getKeyCode() == 0)) {
				if ((keycode == 0) || (keycode2 == 0)) {
					if (keycode == 0) {
						keycode = e.getKeyCode();
					}
					if (!(keycode == 0)) {
						if (!(keycode == e.getKeyCode())) {
							keycode2 = e.getKeyCode();
						}
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (!(e.getKeyCode() == 0)) {
				if (e.getKeyCode() == keycode) {
					keycode = 0;
				}
				if (e.getKeyCode() == keycode2) {
					keycode2 = 0;
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// LEAVE EMPTY , keytyped not used
		}
	};

	public static void update() {
		//DEBUGGING 		System.out.println(keycode);
		if (keycode == 27 || keycode2 == 27) {
			System.exit(0);
		}
	}

}
