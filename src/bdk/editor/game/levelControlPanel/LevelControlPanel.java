package bdk.editor.game.levelControlPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import bdk.cfg.GameConfig;
import bdk.editor.game.BDKGameEditor;
import bdk.editor.game.BdkGameEditorPanel;
import bdk.game.component.level.Level;
import bdk.game.main.Game;
import bdk.util.BdkFileManager;
import bdk.util.ui.Icons;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;
import soliture.ui.swingextensions.expandinglist.JExpandableRowComponent;
import soliture.ui.swingextensions.expandinglist.JExpandingListPanel;

public class LevelControlPanel extends BdkGameEditorPanel {

	private JExpandingListPanel activeLevelListPanel;
	private JExpandingListPanel levelFileListPanel;
	
	// We are accessing the same gameConfig that is being used by the
	// gamePropertiesPanel
	private GameConfig gameConfig;
	
	private ArrayList<Level> levelList;
	
	public LevelControlPanel(BDKGameEditor parent) {
		super(parent);
		this.gameConfig = parent.getGameConfig();
		
		// List Building --------------|
		this.activeLevelListPanel = new JExpandingListPanel(10);
		activeLevelListPanel.setBorder(BorderFactory.createTitledBorder("Active Levels"));
		
		this.levelFileListPanel = new JExpandingListPanel(10);
		levelFileListPanel.setBorder(BorderFactory.createTitledBorder("Inactive Levels"));
		
		loadLevels();
		if(!levelList.isEmpty()) {
			for(Level level : levelList) {
				boolean isInActiveList = false;
			
				for(Level activeLevel: gameConfig.getLevelList()) {
					if(activeLevel.getComponentName().equals(level.getComponentName())) {
						isInActiveList = true;
					}
				}
				
				if(!isInActiveList) {
					createLevelFileListRow(level);
				}
			}
		}
		
		for(Level level : gameConfig.getLevelList()) {
			createActiveLevelListRow(level);
		}
		// ----------------------------|

		this.setBorder(BorderFactory.createTitledBorder("Level Control"));
		this.setLayout(new GridLayout(2,1));
		this.add(activeLevelListPanel);
		this.add(levelFileListPanel);
	}
	
	private void createLevelFileListRow(Level level) {
		JExpandableRow levelFileRow = new JExpandableRow(6);
		levelFileRow.setBorder(BorderFactory.createLineBorder(Color.gray));
		levelFileRow.setDataObject(level);
		
		JLabel levelNameLabel = new JLabel(level.getComponentName());
		
		JButton addToActiveLevelsButton = new JButton(new ImageIcon(BdkFileManager.loadImage(Icons.ICON_ADD_CIRCLE)));
		addToActiveLevelsButton.setBorderPainted(false);
		addToActiveLevelsButton.setFocusPainted(false);
		addToActiveLevelsButton.setContentAreaFilled(false);
		addToActiveLevelsButton.addActionListener(event -> {
			levelList.remove(levelFileRow.getDataObject());
			levelFileRow.removeFromParent();
			addNewLevelToGameConfig((Level) levelFileRow.getDataObject());
		});
		
		JButton deleteLevelButton = new JButton(new ImageIcon(BdkFileManager.loadImage(Icons.ICON_DELETE)));
		deleteLevelButton.setBorderPainted(false);
		deleteLevelButton.setFocusPainted(false);
		deleteLevelButton.setContentAreaFilled(false);
		deleteLevelButton.addActionListener(event -> {
			
		});
		
		levelFileRow.addComponent(new JExpandableRowComponent(levelNameLabel, 0, 4));
		levelFileRow.addComponent(new JExpandableRowComponent(addToActiveLevelsButton, 4, 1));
		levelFileRow.addComponent(new JExpandableRowComponent(deleteLevelButton, 5, 1));
		
		levelFileListPanel.addRow(levelFileRow);
	}
	
	private void createActiveLevelListRow(Level level) {
		JExpandableRow activeLevelRow = new JExpandableRow(10);
		activeLevelRow.setBorder(BorderFactory.createLineBorder(Color.gray));
		activeLevelRow.setDataObject(level);
		
		JLabel levelNameLabel = new JLabel(level.getComponentName());
		
		JButton moveUpButton = new JButton(new ImageIcon(BdkFileManager.loadImage(Icons.ICON_EXPAND_MORE)));
		moveUpButton.setBorderPainted(false);
		moveUpButton.setFocusPainted(false);
		moveUpButton.setContentAreaFilled(false);
		moveUpButton.addActionListener(event -> {
			
		});
		
		JButton moveDownButton = new JButton(new ImageIcon(BdkFileManager.loadImage(Icons.ICON_EXPAND_LESS)));
		moveDownButton.setBorderPainted(false);
		moveDownButton.setFocusPainted(false);
		moveDownButton.setContentAreaFilled(false);
		moveDownButton.addActionListener(event -> {
			
		});
		
		JButton openButton = new JButton(new ImageIcon(BdkFileManager.loadImage(Icons.ICON_OPEN)));
		openButton.setBorderPainted(false);
		openButton.setFocusPainted(false);
		openButton.setContentAreaFilled(false);
		openButton.addActionListener(event -> {
			
		});
		
		JButton removeFromActiveLevelsButton = new JButton(new ImageIcon(BdkFileManager.loadImage(Icons.ICON_REMOVE_CIRCLE)));
		removeFromActiveLevelsButton.setBorderPainted(false);
		removeFromActiveLevelsButton.setFocusPainted(false);
		removeFromActiveLevelsButton.setContentAreaFilled(false);
		removeFromActiveLevelsButton.addActionListener(event -> {
			gameConfig.getLevelList().remove(activeLevelRow.getDataObject());
			activeLevelRow.removeFromParent();
			addNewLevelToLevelList((Level) activeLevelRow.getDataObject());
		});
		
		activeLevelRow.addComponent(new JExpandableRowComponent(levelNameLabel, 0, 6));
		activeLevelRow.addComponent(new JExpandableRowComponent(moveUpButton, 6, 1));
		activeLevelRow.addComponent(new JExpandableRowComponent(moveDownButton, 7, 1));
		activeLevelRow.addComponent(new JExpandableRowComponent(openButton, 8, 1));
		activeLevelRow.addComponent(new JExpandableRowComponent(removeFromActiveLevelsButton, 9, 1));
		
		activeLevelListPanel.addRow(activeLevelRow);
	}
	
	private void addNewLevelToLevelList(Level levelToAdd) {
		levelList.add(levelToAdd);
		createLevelFileListRow(levelToAdd);
		
		try {
			gameConfig.saveGameConfig();
		} catch (FileNotFoundException e) {
			Game.getLogger().log(java.util.logging.Level.SEVERE, "Could not save game config");
		}
	}
	
	private void addNewLevelToGameConfig(Level levelToAdd) {
		gameConfig.getLevelList().add(levelToAdd);
		createActiveLevelListRow(levelToAdd);
		
		try {
			gameConfig.saveGameConfig();
		} catch (FileNotFoundException e) {
			Game.getLogger().log(java.util.logging.Level.SEVERE, "Could not save game config");
		}
	}
	
	private void loadLevels() {
		levelList = new ArrayList<Level>();
		
		File dir = new File(gameConfig.getLevelPath());
		File[] listOfFiles = dir.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".lvl")) {
				try {
					levelList.add((Level) BdkFileManager.loadSerializedObject(listOfFiles[i].getPath()));
				} catch (FileNotFoundException e) {
					Game.getLogger().log(java.util.logging.Level.WARNING, "A .lvl file in the level directory couldn't be deserialized");
				} 
			}
		}
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {

	}

}
