package bdk.editor.actor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import bdk.data.FileUtil;
import bdk.editor.actor.componentpanel.ComponentPanel;
import bdk.editor.actor.controlpanel.ControlPanel;
import bdk.editor.actor.previewpanel.PreviewPanel;
import bdk.editor.main.BdkMainWindow;
import bdk.editor.util.InputStringDialog;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorCollection;

public class BdkActorEditor extends JPanel {

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

	public BdkActorEditor() {
		// FileChooser-----------------------------------------------------------------------------|
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Actor Collections", "ac");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(BdkMainWindow.getGameName() + "/sprites/actors/collections"));
		
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
		menuItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(BdkActorEditor.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					ActorCollection newCollection = (ActorCollection) FileUtil.loadSerializedObject(file.getPath());
					currentActorCollection = newCollection;
					currentActor = null;
					notifyDataChanged();
				}
			}
		});

		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Currently do not allow custom naming, collection will be named after the file
//				InputStringDialog dialog = new InputStringDialog();
//				String result = dialog.showDialog("New actor collection", "Actor collection name : ");
//				if (!(result == null)) {
//					currentActorCollection = new ActorCollection(result);
//					notifyDataChanged();
//				}
				
				currentActorCollection = new ActorCollection("");
				notifyDataChanged();
				
				
			}
		});

		menuItemSave = new JMenuItem("Save");
		menuItemSave.setEnabled(false);
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showSaveDialog(BdkActorEditor.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					//Check if file has correct extension
					if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("ac")) {
					    // filename is OK as-is
					} else {
					    file = new File(file.toString() + ".ac");  // append .xml if "foo.jpg.xml" is OK
					    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".ac"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
					}
					
					//THE NAME OF THE ACTOR COLLECTION IN THE FILE !!!
					currentActorCollection.setCollectionName(file.getName());
					
					FileUtil.saveSerializableObject(currentActorCollection, file.getPath());
				}
			}
		});

		menuBar.add(menuFile);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemNew);
		menuFile.add(menuItemSave);
		// ---------------------------------------------------------------------------------------|

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 3));
		centerPanel.add(componentPanel);
		centerPanel.add(controlPanel);
		centerPanel.add(previewPanel);

		setLayout(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
		
		//Testing notifications
				
	}
	
	private void displayMenuButtons(){
		if(currentActorCollection != null){
			menuItemSave.setEnabled(true);
		}else{
			menuItemSave.setEnabled(false);
		}
	}

	public void notifyDataChanged() {
		displayMenuButtons();
		
		controlPanel.notifyDataChanged();
		previewPanel.notifyDataChanged();
		componentPanel.notifyDataChanged();
	}
	
	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public ActorCollection getCurrentActorCollection() {
		return currentActorCollection;
	}

	public void setCurrentActorCollection(ActorCollection newActorCollection) {
		this.currentActorCollection = newActorCollection;
	}

	public Actor getCurrentActor() {
		return currentActor;
	}

	public void setCurrentActor(Actor newActor) {
		this.currentActor = newActor;
	}

}
