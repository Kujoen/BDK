package engine.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.event.MouseInputListener;

import engine.math.Vector2D;

/**
 * Contains all the listeners and changes values if a listener is triggered
 * 
 * @author Soliture
 */

public class Input {

	public static int getKeycode() {
		return keycode;
	}

	public static void setKeycode(int keycode) {
		Input.keycode = keycode;
	}

	public static int getKeycode2() {
		return keycode2;
	}

	public static void setKeycode2(int keycode2) {
		Input.keycode2 = keycode2;
	}

	private static int keycode = 0;
	private static int keycode2 = 0;
	private static int mousex;
	private static int mousey;

	public static MouseListener MouseInputListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			mousex = e.getX();
			mousey = e.getY();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	};

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
			
			switch (e.getKeyCode()) {
			case 87:
		
				break;

			default:
				break;
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

	public static int getMousex() {
		return mousex;
	}

	public static void setMousex(int mousex) {
		Input.mousex = mousex;
	}

	public static int getMousey() {
		return mousey;
	}

	public static void setMousey(int mousey) {
		Input.mousey = mousey;
	}

	public static void update() {
		// DEBUGGING System.out.println(keycode);
		if (keycode == 27 || keycode2 == 27) {
			System.exit(0);
		}

	}

}
