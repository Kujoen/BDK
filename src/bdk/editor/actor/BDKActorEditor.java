package bdk.editor.actor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import bdk.editor.actor.componentpanel.ComponentPanel;
import bdk.editor.actor.controlpanel.ControlPanel;
import bdk.editor.actor.previewpanel.PreviewPanel;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorCollection;
import bdk.game.main.Game;
import bdk.util.BdkFileManager;
import bdk.util.ui.InputStringDialog;
import bdk.util.ui.WarningDialog;

public class BDKActorEditor extends JPanel {

	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemOpen, menuItemSave, menuItemNew;
	private JFileChooser fileChooser;
	// The 3 content panels------------------------------------------|
	private PreviewPanel previewPanel;
	private ControlPanel controlPanel;
	private ComponentPanel componentPanel;
	// And the panel they are on
	private JPanel centerPanel;
	// --------------------------------------------------------------|
	private ActorCollection currentActorCollection;
	private Actor currentActor;

	public BDKActorEditor() {
		// FileChooser-----------------------------------------------------------------------------|
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Actor Collections", "ac");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(ActorCollection.COLLECTION_PATH));

		// DATAVIEW-------------------------------------------------------------------------------|
		componentPanel = new ComponentPanel(this);
		// PROPERTYVIEW---------------------------------------------------------------------------|
		controlPanel = new ControlPanel(this);
		// GAMEPREVIEW----------------------------------------------------------------------------|
		previewPanel = new PreviewPanel(this);
		// MENUBAR--------------------------------------------------------------------------------|
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener((e) -> {
			int result = fileChooser.showOpenDialog(BDKActorEditor.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				
				try {
					ActorCollection newCollection = (ActorCollection) BdkFileManager.loadSerializedObject(file.getPath());
					
					setCurrentActorCollection(newCollection);
					setCurrentActor(null);
				} catch (FileNotFoundException e1) {
					Game.getLogger().log(java.util.logging.Level.WARNING, "Couldn't load collection", e1);
					WarningDialog.showWarning("Something went wrong when opening the collection");
				}
			}
		});

		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				InputStringDialog inputDialog = new InputStringDialog();
				String resultName = inputDialog.showDialog("Collection Name", "Please enter the collection name : ");

				if (resultName != null) {
					setCurrentActorCollection(new ActorCollection(resultName));
				}
			}

		});

		menuItemSave = new JMenuItem("Save");
		menuItemSave.setEnabled(false);
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Set the suggested name to the name of the collection.
				fileChooser.setSelectedFile(new File(currentActorCollection.getCollectionName() + ".ac"));

				int result = fileChooser.showSaveDialog(BDKActorEditor.this);

				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					// Check if file has correct extension
					if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("ac")) {
						// filename is OK as-is
					} else {
						file = new File(file.toString() + ".ac");

						// Dont think this is needed, remove later ?
						// file = new File(file.getParentFile(),
						// FilenameUtils.getBaseName(file.getName()) + ".ac");
					}

					// Check if the user changed the file name
					String selectedName = FilenameUtils.getBaseName(file.getName());
					if (currentActorCollection.getCollectionName() != selectedName) {
						currentActorCollection.setCollectionName(selectedName);
					}

					try {
						BdkFileManager.saveSerializableObject(currentActorCollection, file.getPath());
					} catch (FileNotFoundException e1) {
						Game.getLogger().log(java.util.logging.Level.WARNING, "Couldn't save collection", e1);
						WarningDialog.showWarning("Something went wrong when saving the collection");
					}
				}
			}
		});

		menuBar.add(menuFile);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemNew);
		menuFile.add(menuItemSave);
		// MENUBAR
		// END-----------------------------------------------------------------------------|
		// ----------------------------------------------------------------------------------------|

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 3));
		centerPanel.add(componentPanel);
		centerPanel.add(controlPanel);
		centerPanel.add(previewPanel);

		setLayout(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
	}

	private void displayMenuButtons() {
		if (currentActorCollection != null) {
			menuItemSave.setEnabled(true);
		} else {
			menuItemSave.setEnabled(false);
		}
	}

	/**
	 * Is called by listeners whenever an actor/actorcollection attribute changes.
	 */
	private void notifyDataChanged(PropertyChangeEvent event) {
		// -Do something
		displayMenuButtons();

		// -Pass the notification on
		controlPanel.notifyDataChanged(event);
		previewPanel.notifyDataChanged(event);
		componentPanel.notifyDataChanged(event);
	}

	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public ActorCollection getCurrentActorCollection() {
		return currentActorCollection;
	}

	/**
	 * Setting the current actor selection will trigger a notifyDataChanged, also a
	 * propertyChangedListener is added if not null
	 * 
	 * @param newActorCollection
	 */
	public void setCurrentActorCollection(ActorCollection newActorCollection) {
		currentActorCollection = newActorCollection;
		// Add a listener
		if (currentActorCollection != null) {
			currentActorCollection.refreshListenerList();
			currentActorCollection.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent event) {
					notifyDataChanged(event);
				}
			});
		}
		notifyDataChanged(new PropertyChangeEvent(newActorCollection, "setCurrentActorCollection", null, null));
	}

	public Actor getCurrentActor() {
		return currentActor;
	}

	/**
	 * Setting the current actor will trigger a notifyDatachanged, also a
	 * propertyChangedListener is added if not null
	 * 
	 * @param newActor
	 */
	public void setCurrentActor(Actor newActor) {
		currentActor = newActor;
		if (currentActor != null) {
			currentActor.refreshListenerList();
			currentActor.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent event) {
					notifyDataChanged(event);
				}
			});
		}
		notifyDataChanged(new PropertyChangeEvent(newActor, "setCurrentActor", null, null));
	}

}
