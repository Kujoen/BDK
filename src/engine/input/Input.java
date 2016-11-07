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

	private static boolean isForward = false;
	private static boolean isBackward = false;
	private static boolean isLeft = false;
	private static boolean isRight = false;
	private static boolean isShift = false;
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
			switch (e.getKeyCode()) {
			case 87:
				isForward = true;
				break;
			case 83:
				isBackward = true;
				break;
			case 65:
				isLeft = true;
				break;
			case 68:
				isRight = true;
				break;
			case 16:
				isShift = true;
				break;
			case 27:
				System.exit(0);
				break;
			default:
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case 87:
				isForward = false;
				break;
			case 83:
				isBackward = false;
				break;
			case 65:
				isLeft = false;
				break;
			case 68:
				isRight = false;
				break;
			case 16:
				isShift = false;
				break;
			default:
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// LEAVE EMPTY , keytyped not used
		}
	};

	public static void update() {
	
	}

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

	public static boolean isForward() {
		return isForward;
	}

	public static void setForward(boolean isForward) {
		Input.isForward = isForward;
	}

	public static boolean isBackward() {
		return isBackward;
	}

	public static void setBackward(boolean isBackward) {
		Input.isBackward = isBackward;
	}

	public static boolean isLeft() {
		return isLeft;
	}

	public static void setLeft(boolean isLeft) {
		Input.isLeft = isLeft;
	}

	public static boolean isRight() {
		return isRight;
	}

	public static void setRight(boolean isRight) {
		Input.isRight = isRight;
	}

	public static boolean isShift() {
		return isShift;
	}

	public static void setShift(boolean isShift) {
		Input.isShift = isShift;
	}
}
