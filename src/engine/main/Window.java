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
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//--------------------------------------------------------------------------|
	// FINALS-------------------------------------------------------------------|
	public static final double SCREENHEIGHT = screenSize.getHeight();
	public static final double SCREENWIDTH = screenSize.getWidth();
	public static final int GAMEHEIGHT = (int) 500;
	public static final int GAMEWIDTH = (int) 500;
	private static final long serialVersionUID = 1L;
	//--------------------------------------------------------------------------|
	
	public Window(String title) {
		this.setSize(GAMEWIDTH, GAMEHEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLayout(null);
		this.addKeyListener(Input.KeyInputListener);
		
		Game game = new Game(this);
		this.add(game);
		this.setVisible(true);
		game.startGame();
	}
	
}
