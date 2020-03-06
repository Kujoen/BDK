package bdk.editor.actor.controlpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BDKActorEditorPanel;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorType;
import bdk.util.listeners.BDKDocumentListener;
import bdk.util.ui.imageselectionpanel.ImageSelectionIcon;
import bdk.util.ui.imageselectionpanel.ImageSelectionPanel;

public class S2GeneralPropertiesPanel extends BDKActorEditorPanel {

	JPanel contentPane;
	// --Name
	JLabel nameLabel;
	JTextField nameTextField;
	// --Type
	JLabel typeLabel;
	JComboBox<String> typeComboBox;
	// --Image
	JLabel imageHintLabel;
	JLabel imageNameLabel;

	public S2GeneralPropertiesPanel(BDKActorEditor parent) {
		super(parent);

		// Panel UI Settings
		this.setBorder(BorderFactory.createTitledBorder("General Properties"));
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.ipady = 10;
		gc.insets = new Insets(5, 0, 5, 0);

		// Labels and Listeners

		nameLabel = new JLabel("Actor name : ");
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(null);
		nameTextField.setEnabled(false);
		nameTextField.getDocument().addDocumentListener(new BDKDocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				String newName = nameTextField.getText();
				String oldName = bdkActorEditor.getCurrentActor().getEntityName();
				bdkActorEditor.getCurrentActor().setEntityName(newName);
				bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, BDKActorEditor.CHANGE_ACTOR_NAME, oldName, newName));
			}
		});

		typeLabel = new JLabel("Actor type : ");
		typeComboBox = new JComboBox<>(Actor.ACTOR_TYPES);
		typeComboBox.setEnabled(false);
		typeComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ActorType oldType = bdkActorEditor.getCurrentActor().getActorType();
					ActorType newType = null;
					
					switch((String) e.getItem()) {
					case "enemy":
						newType = ActorType.ENEMY;
						break;
					case "projectile":
						newType = ActorType.PROJECTILE;
						break;
					case "player":
						newType = ActorType.PLAYER;
						break;
					}
					
					bdkActorEditor.getCurrentActor().setActorType(newType);
					bdkActorEditor.notifyDataChanged(new PropertyChangeEvent(this, BDKActorEditor.CHANGE_ACTOR_TYPE, oldType, newType));
				}
			}
		});

		imageHintLabel = new JLabel("Sprite: ");
		imageNameLabel = new JLabel("");
		imageNameLabel.setBackground(Color.GRAY);
		imageNameLabel.setForeground(Color.RED);

		// Adding to the layout ------------------------------------------------|

		// Add the Labels ------------------|
		gc.weightx = 0.1;
		gc.weighty = 0.0;

		gc.gridx = 0;
		gc.gridy = 0;
		contentPane.add(nameLabel, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		contentPane.add(typeLabel, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		contentPane.add(imageHintLabel, gc);

		// Add the Input Fields ------------|
		gc.weightx = 1.0;
		gc.weighty = 0.0;

		gc.gridx = 1;
		gc.gridy = 0;
		contentPane.add(nameTextField, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		contentPane.add(typeComboBox, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		contentPane.add(imageNameLabel, gc);

		// Fill excess vertical space ------|
		gc.weightx = 0.0;
		gc.weighty = 1.0;

		gc.gridx = 0;
		gc.gridy = 3;
		contentPane.add(new JLabel(), gc);

		// -----------------------------------------------------------------|

		this.setLayout(new GridLayout());
		this.add(contentPane);
	}

	private void displayGeneralProperties() {
		if (bdkActorEditor.getCurrentActorCollection() != null) {
			// Is there an actor to display information on
			if (bdkActorEditor.getCurrentActor() != null) {
				nameTextField.setText(bdkActorEditor.getCurrentActor().getEntityName());
				nameTextField.setEnabled(true);

				if (bdkActorEditor.getCurrentActor().getActorType() != null) {
					typeComboBox.setSelectedItem(bdkActorEditor.getCurrentActor().getActorType());
				}

				typeComboBox.setEnabled(true);

				// Check if actor has an image
				if (!bdkActorEditor.getCurrentActor().getSpritePath().isEmpty()) {
					imageNameLabel.setText(bdkActorEditor.getCurrentActor().getSpritePath()
							.substring(bdkActorEditor.getCurrentActor().getSpritePath().lastIndexOf("\\") + 1));
					imageNameLabel.setEnabled(true);
				} else {
					imageNameLabel.setText("No image selected");
					imageNameLabel.setEnabled(true);
				}

			}
			// No actor to display information on
			else {
				nameTextField.setText("-");
				nameTextField.setEnabled(false);

				typeComboBox.setEnabled(false);

				imageNameLabel.setName("-");
				imageNameLabel.setEnabled(false);
			}
		}
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(BDKActorEditor.CHANGE_ACTOR)) {
			displayGeneralProperties();
		}
		
		if (event.getPropertyName().equals(ImageSelectionPanel.CHANGE_IMAGE)) {
			imageNameLabel.setText((String) event.getNewValue());
		}
	}

}
