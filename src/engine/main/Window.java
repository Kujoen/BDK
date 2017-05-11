package engine.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import engine.input.Input;

/**
 * The GameWindow containing everything, contains Methods to control what is
 * currently being displayed
 * 
 * @author Soliture
 *
 */

public class Window extends JFrame {

	// UI-----------------------------------------------------------------------|
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//--------------------------------------------------------------------------|
	// GAMEOBJECTS--------------------------------------------------------------|
	private static Game globalgame;
	//--------------------------------------------------------------------------|
	// INT----------------------------------------------------------------------|
	private static int ACTUALHEIGHT;
	private static int ACTUALWIDTH;
	//--------------------------------------------------------------------------|
	// FINALS-------------------------------------------------------------------|
	private static final double SCREENHEIGHT = screenSize.getHeight();
	private static final double SCREENWIDTH = screenSize.getWidth();
	private static final int DEFAULTHEIGHT = (int) 720;
	private static final int DEFAULTWIDTH = (int) 1280;
	private static final long serialVersionUID = 1L;
	//--------------------------------------------------------------------------|
	
	public Window(String title) {
		
		this.setSize(ACTUALWIDTH, ACTUALHEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLayout(null);
		this.addKeyListener(Input.KeyInputListener);
		
		Game game = new Game(this);
		globalgame = game;
		this.add(game);
		this.setVisible(true);
		game.startGame();
	}
	
	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|

	public static Dimension getScreenSize() {
		return screenSize;
	}

	public static int getACTUALHEIGHT() {
		return ACTUALHEIGHT;
	}

	public static void setACTUALHEIGHT(int aCTUALHEIGHT) {
		ACTUALHEIGHT = aCTUALHEIGHT;
	}

	public static int getACTUALWIDTH() {
		return ACTUALWIDTH;
	}

	public static void setACTUALWIDTH(int aCTUALWIDTH) {
		ACTUALWIDTH = aCTUALWIDTH;
	}

	public static double getScreenheight() {
		return SCREENHEIGHT;
	}

	public static double getScreenwidth() {
		return SCREENWIDTH;
	}

	public static int getDefaultheight() {
		return DEFAULTHEIGHT;
	}

	public static int getDefaultwidth() {
		return DEFAULTWIDTH;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setScreenSize(Dimension screenSize) {
		Window.screenSize = screenSize;
	}

	public static Game getGlobalgame() {
		return globalgame;
	}

	public static void setGlobalgame(Game globalgame) {
		Window.globalgame = globalgame;
	}
	
}
