package bdk.editor.actor.controlpanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BDKActorEditorPanel;
import bdk.game.entities.sprites.actors.Actor;
import bdk.util.ui.BDKInputStringDialog;

public class S1ActorSelectionPanel extends BDKActorEditorPanel {

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

	public S1ActorSelectionPanel(BDKActorEditor parent) {
		super(parent);
		init();
	}

	private void init() {
		// BUTTONPANE------------------------------------------------------------------------------------|
		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1, 0));

		actorCollectionName = new JLabel();
		actorCollectionName.setBackground(Color.WHITE);
		actorCollectionName.setForeground(Color.RED);

		buttonCreateActor = new JButton("Create");
		buttonCreateActor.setEnabled(false);
		buttonCreateActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BDKInputStringDialog dialog = new BDKInputStringDialog();
				String result = dialog.showDialog("Actor creation", "Name of new Actor: ");
				if (result != null) {
					Actor newActor = new Actor(result, bdkActorEditor.getCurrentActorCollection().getCollectionName());
					bdkActorEditor.getCurrentActorCollection().addActor(newActor);
					bdkActorEditor.setCurrentActor(newActor);
				}
			}
		});

		buttonDeleteActor = new JButton("Delete");
		buttonDeleteActor.setEnabled(false);
		buttonDeleteActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Filter out -1 because it means nothing is selected
				if (!listToDisplay.isSelectionEmpty()) {
					bdkActorEditor.getCurrentActorCollection().removeActorAt(listToDisplay.getSelectedIndex());
					bdkActorEditor.setCurrentActor(null);
				}
			}
		});

		buttonCopyActor = new JButton("Copy");
		buttonCopyActor.setEnabled(false);
		buttonCopyActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!listToDisplay.isSelectionEmpty()) {
					BDKInputStringDialog dialog = new BDKInputStringDialog();
					String result = dialog.showDialog("copy particle", "Name of the copy : ");
					if (result != null) {
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
		listToDisplay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (listToDisplay.isEnabled()) {
					//Save the selected index, since calling notifyDataChanged makes the list lose focus
					int index = listToDisplay.getSelectedIndex();
					bdkActorEditor.setCurrentActor(bdkActorEditor.getCurrentActorCollection().getActorAt(listToDisplay.getSelectedIndex()));
					listToDisplay.setSelectedIndex(index);
				}
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
		if (bdkActorEditor.getCurrentActorCollection() != null) {
			buttonCreateActor.setEnabled(true);
			actorCollectionName.setText(bdkActorEditor.getCurrentActorCollection().getCollectionName());

			// Check if the actorcollection has any actors
			if (bdkActorEditor.getCurrentActorCollection().getCollectionSize() > 0) {
				listToDisplay.setEnabled(true);
				// If yes enable the delete button
				buttonDeleteActor.setEnabled(true);

				// And add the actors to the list
				actorNames = new Object[bdkActorEditor.getCurrentActorCollection().getCollectionSize()];
				for (int i = 0; i < bdkActorEditor.getCurrentActorCollection().getCollectionSize(); i++) {
					actorNames[i] = bdkActorEditor.getCurrentActorCollection().getActorAt(i).getEntityName();
				}
				listToDisplay.setListData(actorNames);
			} else {

				buttonDeleteActor.setEnabled(false);
				// Hack to stop the list from displaying the deleted object
				listToDisplay.setListData(new Object[0]);
				listToDisplay.setEnabled(false);
			}

			revalidate();
			repaint();
		}
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		displayActorList();
	}
}
