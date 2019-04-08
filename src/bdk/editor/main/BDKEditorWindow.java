package bdk.editor.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import bdk.cfg.GameConfig;
import bdk.cfg.WindowConfig;
import bdk.editor.actor.BdkActorEditor;
import bdk.editor.level.BdkLevelEditor;
import bdk.game.main.Game;
import bdk.util.BdkFileManager;

public class BDKEditorWindow extends JFrame {

	// \n[\s]* , storing this here
	private JTabbedPane mainPanel;
	private BdkActorEditor actorEditor;
	private BdkLevelEditor levelEditor;

	public static GameConfig gameConfig;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Game.getLogger().log(Level.WARNING, "Couldn't set look and feel", e);
		}

		// Load the required config files
		initializeConfigs();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BDKEditorWindow frame = new BDKEditorWindow();
				} catch (Exception e) {
					Game.getLogger().log(Level.SEVERE, "Error creating window instance", e);
				}
			}
		});
	}
	
	public static void initializeConfigs() {
		try {
			gameConfig = GameConfig.loadGameConfig();
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.SEVERE, "Unable to open game config at: " + GameConfig.CONFIG_PATH, e);
		}
	}

	/**
	 * Create the frame.
	 */
	public BDKEditorWindow() {
		mainPanel = new JTabbedPane();

		levelEditor = new BdkLevelEditor();
		mainPanel.addTab("Level-Editor", levelEditor);

		actorEditor = new BdkActorEditor();
		mainPanel.addTab("Actor-Editor", actorEditor);

		JPanel placeholder2 = new JPanel();
		mainPanel.addTab("-", placeholder2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setContentPane(mainPanel);
		setResizable(true);
		pack();
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1280, 720));
		setTitle("BDK");
		setVisible(true);
	}

	// ------------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// ------------------------------------------------------------------------------------------------|

	// DO NOT DELETE THESE GETTER/SETTERS ------------------------------|
	public BdkActorEditor getActorEditor() {
		return actorEditor;
	}

	public void setActorEditor(BdkActorEditor actorEditor) {
		this.actorEditor = actorEditor;
	}
	// -----------------------------------------------------------------|
}
