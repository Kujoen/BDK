package bdk.editor.level;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import bdk.game.component.level.Level;
import bdk.util.BdkFileManager;
import bdk.util.ui.InputStringDialog;
import bdk.util.ui.WarningDialog;

/**
 * 
 * @author Andreas Farley
 *
 */
public class BdkLevelEditor extends JPanel {

	// --------------------------------------------------------------|
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemOpen, menuItemSave, menuItemNew;
	private JFileChooser fileChooser;
	// --------------------------------------------------------------|
	private JPanel centerPanel;
	private PreviewPanel previewPanel;
	private ControlPanel controlPanel;
	// --------------------------------------------------------------|
	private Level currentLevel;
	// --------------------------------------------------------------|

	public BdkLevelEditor() {
		// FileChooser-----------------------------------------------------------------------------|
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BDK Levels", "lvl");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(Level.LEVEL_PATH));

		// --------------------------------------------------------------|
		// The level-editor consists of 2 sections, one where images/layers are
		// selected, and three showing the actuall level.
		// --------------------------------------------------------------|

		previewPanel = new PreviewPanel(this);
		controlPanel = new ControlPanel(this);

		// MENUBAR--------------------------------------------------------------------------------|
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(BdkLevelEditor.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					Level newLevel = (Level) BdkFileManager.loadSerializedObject(file.getPath());
					if (newLevel != null) {
						setCurrentLevel(newLevel);
					} else {
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
				}
			}

		});

		menuItemSave = new JMenuItem("Save");
		menuItemSave.setEnabled(false);
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Set the suggested name to the name of the collection.
				fileChooser.setSelectedFile(new File(currentLevel.getComponentName() + ".lvl"));

				int result = fileChooser.showSaveDialog(BdkLevelEditor.this);

				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					// Check if file has correct extension
					if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("lvl")) {
						// filename is OK as-is
					} else {
						file = new File(file.toString() + ".lvl");

						// Dont think this is needed, remove later ?
						// file = new File(file.getParentFile(),
						// FilenameUtils.getBaseName(file.getName()) + ".ac");
					}

					// Check if the user changed the file name
					String selectedName = FilenameUtils.getBaseName(file.getName());
					if (currentLevel.getComponentName() != selectedName) {
						currentLevel.setComponentName(selectedName);
					}

					BdkFileManager.saveSerializableObject(currentLevel, file.getPath());
				}
			}
		});

		menuBar.add(menuFile);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemNew);
		menuFile.add(menuItemSave);
		// MENUBAR END ------------------------------------------------------|
		// ------------------------------------------------------------------|

		// Create the layout
		GridBagLayout gbLayout = new GridBagLayout();
		gbLayout.rowWeights = new double[] { 1 };
		gbLayout.columnWeights = new double[] { 1, 3 };

		centerPanel = new JPanel();
		centerPanel.setLayout(gbLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = c.BOTH;
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

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

}
