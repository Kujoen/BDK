package bdk.editor.game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import bdk.cfg.GameConfig;
import bdk.editor.game.gamePropertiesPanel.GamePropertiesPanel;
import bdk.editor.game.levelControlPanel.LevelControlPanel;

public class BDKGameEditor extends JPanel {
	// ----------------------------------------------------|
	// The three content panels
	private GamePropertiesPanel gamePropertiesPanel;
	private LevelControlPanel levelControlPanel;
	private JPanel placeholderPanel;
	
	// And the panel they are on
	private JPanel centerPanel;
	// ----------------------------------------------------|
	
	public BDKGameEditor() {
		gamePropertiesPanel = new GamePropertiesPanel(this);
		
		levelControlPanel = new LevelControlPanel(this);
		
		placeholderPanel = new JPanel();
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 3));
		centerPanel.add(gamePropertiesPanel);
		centerPanel.add(levelControlPanel);
		centerPanel.add(placeholderPanel);
		
		setLayout(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
	}
	
	public void notifyDataChanged(PropertyChangeEvent event) {
		gamePropertiesPanel.notifyDataChanged(event);
		levelControlPanel.notifyDataChanged(event);
	}
	
	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|
	
	public GameConfig getGameConfig() {
		return gamePropertiesPanel.getGameConfig();
	}
	
}
