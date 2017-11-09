package bdk.editor.actor.componentpanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import bdk.game.entities.sprites.actors.components.Component;

/**
 * 
 * @author Andreas Farley
 *
 */
public class SelectComponentDialog {

	private static Component selectedComponent;

	private static JPanel contentPanel;
	private static JPanel buttonPanel;
	private static JButton selectButton;
	private static JButton cancelButton;
	private static JScrollPane scrollPane;
	private static JList dataObjectList;

	public static Component showSelectionDialog(Object[] dataObjectArray, String dialogName) {

		selectedComponent = null;
		dataObjectList = new JList(dataObjectArray);
		dataObjectList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		scrollPane = new JScrollPane(dataObjectList);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		selectButton = new JButton("select");
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedComponent = (Component) dataObjectList.getSelectedValue();
				SwingUtilities.getWindowAncestor((JButton) arg0.getSource()).dispose();
			}
		});

		cancelButton = new JButton("cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.getWindowAncestor((JButton) arg0.getSource()).dispose();
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0 , 2 , 25 , 25));
		buttonPanel.add(selectButton);
		buttonPanel.add(cancelButton);

		GridBagConstraints c = new GridBagConstraints();
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = c.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		contentPanel.add(scrollPane, c);
		c.gridy = 3;
		contentPanel.add(buttonPanel, c);


		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setTitle(dialogName);
		dialog.setContentPane(contentPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		return selectedComponent;
	}

}
