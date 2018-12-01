package bdk.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * InputListener provides access to Listener instances which listen to game related keyboard and mouse events.
 * 
 * @author Soliture
 */

public class BdkInputListener {

	// -----------------------------------------------------------------------------|
	private boolean isForward = false;
	private boolean isBackward = false;
	private boolean isLeft = false;
	private boolean isRight = false;
	private boolean isShift = false;
	private boolean isSpacebar = false;

	private int mouseX;
	private int mouseY;

	private MouseListener mouseInputListener;
	private KeyListener keyInputListener;

	// A KeyListener that listens to forward, backward, left, right, shift, spacebar
	// and escape

	// -----------------------------------------------------------------------------|

	/**
	 * An InputListener which provides an MouseInputListener and a KeyInputListener.
	 * The KeyInputListener listens to W, A, S, D, SHIFT, SPACE, ESCAPE. The
	 * MouseInputListener listens for clicked mouse coordinates.
	 */
	public BdkInputListener() {
		
		// Create the MouseListener
		this.mouseInputListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// LEAVE EMPTY , not used
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// LEAVE EMPTY , not used
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// LEAVE EMPTY , not used
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// LEAVE EMPTY , not used
			}

		};

		this.keyInputListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 87: // W
					isForward = true;
					break;
				case 83: // S
					isBackward = true;
					break;
				case 65: // A
					isLeft = true;
					break;
				case 68: // D
					isRight = true;
					break;
				case 16: // SHIFT
					isShift = true;
					break;
				case 32: // SPACEBAR
					isSpacebar = true;
					break;
				case 27: // ESCAPE
					System.exit(0);
					break;
				default:
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 87: // W
					isForward = false;
					break;
				case 83: // S
					isBackward = false;
					break;
				case 65: // A
					isLeft = false;
					break;
				case 68: // D
					isRight = false;
					break;
				case 16: // SHIFT
					isShift = false;
					break;
				case 32: // SPACEBAR
					isSpacebar = false;
					break;
				default:
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// LEAVE EMPTY , not used
			}

		};
	}
	
	// -------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------|

	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public boolean isBackward() {
		return isBackward;
	}

	public void setBackward(boolean isBackward) {
		this.isBackward = isBackward;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public boolean isShift() {
		return isShift;
	}

	public void setShift(boolean isShift) {
		this.isShift = isShift;
	}

	public boolean isSpacebar() {
		return isSpacebar;
	}

	public void setSpacebar(boolean isSpacebar) {
		this.isSpacebar = isSpacebar;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public MouseListener getMouseInputListener() {
		return mouseInputListener;
	}

	public void setMouseInputListener(MouseListener mouseInputListener) {
		this.mouseInputListener = mouseInputListener;
	}

	public KeyListener getKeyInputListener() {
		return keyInputListener;
	}

	public void setKeyInputListener(KeyListener keyInputListener) {
		this.keyInputListener = keyInputListener;
	}

	// --------------------------------------------------------------------------------------------|

}
