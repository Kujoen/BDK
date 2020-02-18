package bdk.editor.game.gamePropertiesPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import bdk.cfg.GameConfig;
import bdk.editor.game.BDKGameEditor;
import bdk.editor.game.BDKGameEditorPanel;
import bdk.game.main.Game;
import bdk.util.ui.BDKInputFilter;

public class GamePropertiesPanel extends BDKGameEditorPanel {

	private JPanel contentPane;
	private GameConfig gameConfig;
	private GridBagConstraints gc;
	private JTextField gameNameTextField;
	private JTextField gameCreatorTextField;

	public GamePropertiesPanel(BDKGameEditor parent) {
		super(parent);

		// UI ---------------------------------------------------------------|
		this.setBorder(BorderFactory.createTitledBorder("Game Properties"));
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());

		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.ipady = 10;
		gc.insets = new Insets(5, 0, 5, 0);
		
		// Add the Labels ------------------|
		gc.weightx = 0.1;
		gc.weighty = 0.0;
		
		gc.gridx = 0;
		gc.gridy = 0;
		contentPane.add(new JLabel("Game Name"), gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		contentPane.add(new JLabel("Game Creator"), gc);
		
		// Add the Input Fields ------------|
		gc.weightx = 1.0;
		gc.weighty = 0.0;
		
		gc.gridx = 1;
		gc.gridy = 0;
		
		gameNameTextField = new JTextField();
		AbstractDocument gameNameDocument = (AbstractDocument) gameNameTextField.getDocument();
		gameNameDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_STRING, 0, 0));
		contentPane.add(gameNameTextField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		
		gameCreatorTextField = new JTextField();
		AbstractDocument gameCreatorDocument = (AbstractDocument) gameCreatorTextField.getDocument();
		gameCreatorDocument.setDocumentFilter(new BDKInputFilter(BDKInputFilter.ALLOW_STRING, 0, 0));
		contentPane.add(gameCreatorTextField, gc);
		
		// Fill excess vertical space ------|
		gc.weightx = 0.0;
		gc.weighty = 1.0;
		
		gc.gridx = 0;
		gc.gridy = 3;
		contentPane.add(new JLabel(), gc);
		
		this.setLayout(new GridLayout());
		this.add(contentPane);
		// DATA -------------------------------------------------------------|
		
		try {
			this.gameConfig = GameConfig.loadGameConfig();
		} catch (FileNotFoundException e) {
			Game.getLogger().log(Level.WARNING, "Couldn't load game config");
		}
		
		
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {

	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|
	
	public GameConfig getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
	}

}
