package bdk.editor.level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import bdk.editor.level.controlpanel.ControlPanel;
import bdk.editor.level.previewpanel.PreviewPanel;
import bdk.editor.main.BDKEditorWindow;
import bdk.game.component.level.Level;
import bdk.game.main.Game;
import bdk.util.BdkFileManager;
import bdk.util.ui.InputStringDialog;
import bdk.util.ui.WarningDialog;

/**
 * 
 * @author Andreas Farley
 *
 */
public class BDKLevelEditor extends JPanel {

	// --------------------------------------------------------------|
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemOpen, menuItemSave, menuItemNew;
	private JLabel menuLabelLevelName;
	private JLabel menuLabelToolName;
	private JLabel menuLabelSpriteName;
	private JFileChooser fileChooser;
	// --------------------------------------------------------------|
	private JPanel centerPanel;
	private PreviewPanel previewPanel;
	private ControlPanel controlPanel;
	// --------------------------------------------------------------|
	private Level currentLevel;
	private String currentSpriteImagePath;
	// --------------------------------------------------------------|
	
	// ------------------------------------------------------------------------------|
	// GENERAL METHODS
	// ------------------------------------------------------------------------------|


	public BDKLevelEditor() {

		// FileChooser-----------------------------------------------------------------------------|
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BDK Levels", "lvl");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(BDKEditorWindow.gameConfig.getLevelPath()));

		// MENUBAR--------------------------------------------------------------------------------|
		menuBar = new JMenuBar();

		menuFile = new JMenu("File");

		menuLabelLevelName = new JLabel("No active level");
		menuLabelLevelName.setBorder(BorderFactory.createTitledBorder(""));
		menuLabelLevelName.setForeground(Color.red);

		menuLabelToolName = new JLabel("No active tool");
		menuLabelToolName.setBorder(BorderFactory.createTitledBorder(""));
		menuLabelToolName.setForeground(Color.red);	
		
		menuLabelSpriteName = new JLabel("No active sprite");
		menuLabelSpriteName.setBorder(BorderFactory.createTitledBorder(""));
		menuLabelSpriteName.setForeground(Color.red);	
		
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(BDKLevelEditor.this);
				if (result == JFileChooser.APPROVE_OPTION) {

					File file = fileChooser.getSelectedFile();

					try {
						Level newLevel = (Level) BdkFileManager.loadSerializedObject(file.getPath());
						setCurrentLevel(newLevel);
						menuLabelLevelName.setText("Level: " + currentLevel.getComponentName());
					} catch (FileNotFoundException e1) {
						Game.getLogger().log(java.util.logging.Level.WARNING, "Couldn't load level", e1);
						WarningDialog.showWarning("Something went wrong when opening the file");
					}
				}
			}
		});

		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				InputStringDialog inputDialog = new InputStringDialog();
				String resultName = inputDialog.showDialog("Level Name", "Please enter the level name : ");

				if (resultName != null) {
					setCurrentLevel(new Level(resultName));
					menuLabelLevelName.setText("Level: " + currentLevel.getComponentName());
				}
				menuItemSave.setEnabled(true);
			}
		});

		menuItemSave = new JMenuItem("Save");
		menuItemSave.setEnabled(false);
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Set the suggested name to the name of the collection.
				fileChooser.setSelectedFile(new File(currentLevel.getComponentName() + ".lvl"));

				int result = fileChooser.showSaveDialog(BDKLevelEditor.this);

				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					// Check if file has correct extension
					if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("lvl")) {
						// filename is OK as-is
					} else {
						file = new File(file.toString() + ".lvl");
					}

					// Check if the user changed the file name
					String selectedName = FilenameUtils.getBaseName(file.getName());
					if (currentLevel.getComponentName() != selectedName) {
						currentLevel.setComponentName(selectedName);
					}

					try {
						BdkFileManager.saveSerializableObject(currentLevel, file.getPath());
					} catch (FileNotFoundException e1) {
						Game.getLogger().log(java.util.logging.Level.WARNING, "Couldn't save level", e1);
						WarningDialog.showWarning("Something went wrong when saving the file");
					}
				}
			}
		});

		menuBar.add(menuFile);
		menuBar.add(menuLabelLevelName);
		menuBar.add(menuLabelToolName);
		menuBar.add(menuLabelSpriteName);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemNew);
		menuFile.add(menuItemSave);
		// MENUBAR END ------------------------------------------------------|
		
		// --------------------------------------------------------------|
		// The level-editor consists of 2 sections, one where images/layers
		// are selected, and three showing the actual level.
		// --------------------------------------------------------------|

		previewPanel = new PreviewPanel(this);
		controlPanel = new ControlPanel(this);
		
		// ------------------------------------------------------------------|

		// Create the layout
		GridBagLayout gbLayout = new GridBagLayout();
		gbLayout.rowWeights = new double[] { 1 };
		gbLayout.columnWeights = new double[] { 1, 3 };

		centerPanel = new JPanel();
		centerPanel.setLayout(gbLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		centerPanel.add(controlPanel, c);

		c.gridx = 1;
		c.gridy = 0;
		centerPanel.add(previewPanel, c);

		setLayout(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
	}

	private void notifyDataChanged() {
		controlPanel.notifyDataChanged();
		previewPanel.notifyDataChanged();
	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
		notifyDataChanged();
	}

	public String getCurrentSpriteImagePath() {
		return currentSpriteImagePath;
	}

	public void setCurrentSpriteImagePath(String currentSpriteImagePath) {
		menuLabelSpriteName.setText("Sprite: " + currentSpriteImagePath);
		this.currentSpriteImagePath = currentSpriteImagePath;
	}

	public JLabel getMenuLabelToolName() {
		return menuLabelToolName;
	}

	public void setMenuLabelToolName(JLabel menuLabelToolName) {
		this.menuLabelToolName = menuLabelToolName;
	}

}
