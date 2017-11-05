package bdk.editor.actor.controlpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.game.entities.sprites.actors.Actor;

public class S2GeneralPropertiesPanel extends BdkActorEditorPanel {

	JPanel contentPane;
	// --Name
	JLabel nameLabel;
	JTextField nameTextField;
	// --Type
	JLabel typeLabel;
	JComboBox typeComboBox;
	// --Image
	JLabel imageHintLabel;
	JLabel imageNameLabel;

	public S2GeneralPropertiesPanel(BdkActorEditor parent) {
		super(parent);

		nameLabel = new JLabel("Actor name : ");
		nameTextField = new JTextField();
		nameTextField.setEnabled(false);
		nameTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				bdkActorEditor.getCurrentActor().setActorName(currentActor.getActorName());
				bdkActorEditor.notifyDataChanged();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
			}			
		});

		typeLabel = new JLabel("Actor type : ");
		typeComboBox = new JComboBox(Actor.ACTOR_TYPES);
		typeComboBox.setEnabled(false);
		typeComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					bdkActorEditor.getCurrentActor().setActorType(e.getItem().toString());
					bdkActorEditor.notifyDataChanged();
				}
			}
		});

		imageHintLabel = new JLabel("Current image : ");
		imageNameLabel = new JLabel("");
		imageNameLabel.setBackground(Color.GRAY);
		imageNameLabel.setForeground(Color.RED);

		//Adding to the layout
		//------------------------------------------------|
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		c.fill = c.HORIZONTAL;

		// --Name
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(nameTextField, c);

		// --Type
		c.gridx = 0;
		c.gridy = 1;
		contentPane.add(typeLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(typeComboBox, c);

		// --Image
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(imageHintLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		contentPane.add(imageNameLabel, c);
		
		// -----------------------------------------------------------------|

		setLayout(new GridLayout(1, 1));
		setBorder(BorderFactory.createTitledBorder("General Properties"));
		add(contentPane);
	}

	private void displayGeneralProperties() {
		if(currentActorCollection != null){
			//Is there an actor to display information on 
			if(currentActor != null){
				nameTextField.setText(currentActor.getActorName());
				nameTextField.setEnabled(true);
				
				typeComboBox.setSelectedItem(currentActor.getActorType());
				typeComboBox.setEnabled(true);

				
				//Check if actor has an image
				if(!currentActor.getImagePath().isEmpty()){
					imageNameLabel.setText(currentActor.getImagePath().substring(currentActor.getImagePath().lastIndexOf("\\") + 1));
					imageNameLabel.setEnabled(true);
				}else{
					imageNameLabel.setText("No image selected");
					imageNameLabel.setEnabled(true);	
				}
				
			}
			//No actor to display information on 
			else{
				nameTextField.setText("-");
				nameTextField.setEnabled(false);
				
				typeComboBox.setEnabled(false);
				
				imageNameLabel.setName("-");
				imageNameLabel.setEnabled(false);
			}
		}
	}

	@Override
	public void notifyDataChanged() {
		currentActor = bdkActorEditor.getCurrentActor();
		currentActorCollection = bdkActorEditor.getCurrentActorCollection();
		displayGeneralProperties();
	}

}
