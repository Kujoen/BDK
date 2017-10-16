package bdk.editor.actor.controlpanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.editor.util.InputStringDialog;
import bdk.game.entities.sprites.actors.Actor;

public class S1ActorSelectionPanel extends BdkActorEditorPanel {

	// Column Viewport of the scrollpane--------------|
	private JPanel buttonPane;
	private JLabel actorCollectionName;
	private JButton buttonCreateActor;
	private JButton buttonDeleteActor;
	private JButton buttonCopyActor;
	// ------------------------------------------------|
	private JScrollPane scrollPane;
	private JList listToDisplay;
	private Object[] actorNames;

	public S1ActorSelectionPanel(BdkActorEditor parent) {
		super(parent);
		init();
	}

	private void init() {
		// BUTTONPANE------------------------------------------------------------------------------------|
		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1, 0));

		actorCollectionName = new JLabel();
		actorCollectionName.setBackground(Color.GRAY);
		actorCollectionName.setForeground(Color.RED);

		buttonCreateActor = new JButton("Create");
		buttonCreateActor.setEnabled(false);
		buttonCreateActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputStringDialog dialog = new InputStringDialog();
				String result = dialog.showDialog("Actor creation", "Name of new Actor: ");
				if (!(result == null)) {
					Actor newActor = new Actor(result);
					bdkActorEditor.getCurrentActorCollection().addActor(newActor);
					bdkActorEditor.setCurrentActor(newActor);
					bdkActorEditor.notifyDataChanged();
				}
			}
		});

		buttonDeleteActor = new JButton("Delete");
		buttonDeleteActor.setEnabled(false);
		buttonDeleteActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Filter out -1 because it means nothing is selected
				if(!listToDisplay.isSelectionEmpty()){
					System.out.println("deleted");
					System.out.println(bdkActorEditor.getCurrentActorCollection().getActorAmount());
					bdkActorEditor.getCurrentActorCollection().removeActorAt(listToDisplay.getSelectedIndex());
					System.out.println(bdkActorEditor.getCurrentActorCollection().getActorAmount());
					bdkActorEditor.notifyDataChanged();
				}
			}
		});

		buttonCopyActor = new JButton("Copy");
		buttonCopyActor.setEnabled(false);
		buttonCopyActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!listToDisplay.isSelectionEmpty()) {
					InputStringDialog dialog = new InputStringDialog();
					String result = dialog.showDialog("copy particle", "Name of the copy : ");
					if (!(result == null)) {
						// TODO: cloning
					}
				}
			}

		});

		buttonPane.add(actorCollectionName);
		buttonPane.add(buttonCreateActor);
		buttonPane.add(buttonDeleteActor);
		buttonPane.add(buttonCopyActor);
		// -------------------------------------------------------------------------------------------|
		listToDisplay = new JList();
		listToDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listToDisplay.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//Gotta save the index before changing data because JList loses focus for whatever reason
				int index = listToDisplay.getSelectedIndex();
				bdkActorEditor.setCurrentActor(currentActorCollection.getActorAt(listToDisplay.getSelectedIndex()));
				bdkActorEditor.notifyDataChanged();
				listToDisplay.setSelectedIndex(index);
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setColumnHeaderView(buttonPane);
		scrollPane.setViewportView(listToDisplay);

		setLayout(new GridLayout(1, 1));
		add(scrollPane);
	}

	private void displayActorList() {
		if (currentActorCollection != null) {
			buttonCreateActor.setEnabled(true);
			actorCollectionName.setText(currentActorCollection.getCollectionName());

			// Check if the actorcollection has any actors
			if (!(currentActorCollection.getActorAmount() == 0)) {
				// If yes enable the delete button
				buttonDeleteActor.setEnabled(true);

				// And add the actors to the list
				actorNames = new Object[currentActorCollection.getActorAmount()];
				for (int i = 0; i < currentActorCollection.getActorAmount(); i++) {
					actorNames[i] = currentActorCollection.getActorAt(i).getActorName();
				}
				listToDisplay.setListData(actorNames);
			}

			revalidate();
			repaint();
		}
	}

	@Override
	public void notifyDataChanged() {
		currentActorCollection = bdkActorEditor.getCurrentActorCollection();
		currentActor = bdkActorEditor.getCurrentActor();
		displayActorList();
	}
}
