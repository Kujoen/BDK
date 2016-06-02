package engine.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.border.Border;

import engine.input.Input;

/**
 * The window displaying everything, contains Methods to control what is
 * currently being displayed
 * 
 * @author Andreas
 *
 */

public class Window extends JFrame {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static final double SCREENHEIGHT = screenSize.getHeight();
	public static final double SCREENWIDTH = screenSize.getWidth();
	public static final int GAMEHEIGHT = (int) SCREENHEIGHT - (int) SCREENHEIGHT % 30;
	public static final int TILEHEIGHT = GAMEHEIGHT / 30;
	public static final int GAMEWIDTH = (int) SCREENWIDTH;
	public static final int TILEWIDTH = GAMEWIDTH / 40;

	private int windowstate = 0;

	private static final long serialVersionUID = 1L;

	// Default konstruktor creates the mainwindow
	public Window(String title, Game game) {

		System.out.println(SCREENWIDTH);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.addKeyListener(Input.KeyInputListener);
		this.add(game);
		this.setVisible(true);
		game.startGame();
	}

	public int getWindowstate() {
		return windowstate;
	}

	public void setWindowstate(int windowstate) {
		this.windowstate = windowstate;
	}
}
