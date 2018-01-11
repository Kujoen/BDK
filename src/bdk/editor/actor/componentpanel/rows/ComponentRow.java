package bdk.editor.actor.componentpanel.rows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import bdk.editor.util.BdkFont;
import bdk.game.entities.sprites.actors.Actor;
import soliture.ui.swingextensions.expandinglist.ExpandableRow;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ComponentRow extends ExpandableRow {
	
	JLabel titleLabel;
	JButton expandButton;
	JButton deleteButton;
	
	public ComponentRow(String title) {
		titleLabel = new JLabel(title);
		titleLabel.setFont(new BdkFont(Font.BOLD , 14));
		
		expandButton = new JButton("");
		expandButton.setEnabled(true);
		expandButton.setIcon(new ImageIcon(TitleRow.class.getResource("/resources/icons/expand_more.png")));
		expandButton.setBorder(null);
		expandButton.setBorderPainted(false);
		expandButton.setContentAreaFilled(false);
		expandButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				expandRow();
				if(isExpanded()){
					expandButton.setIcon(new ImageIcon(TitleRow.class.getResource("/resources/icons/expand_less.png")));
				} else {
					expandButton.setIcon(new ImageIcon(TitleRow.class.getResource("/resources/icons/expand_more.png")));
				}
			}
		});
		
		deleteButton = new JButton("");
		deleteButton.setEnabled(true);
		deleteButton.setIcon(new ImageIcon(TitleRow.class.getResource("/resources/icons/delete_forever.png")));
		deleteButton.setBorder(null);
		deleteButton.setBorderPainted(false);
		deleteButton.setContentAreaFilled(false);
		
		addComponentToRow(titleLabel,  1, 2);
		addComponentToRow(expandButton, 3, 1);
		addComponentToRow(deleteButton, 4, 1);
	}
	
	public TextFieldRow getTextFieldRow(String textFieldLabelText) {
		TextFieldRow newRow = new TextFieldRow(textFieldLabelText);
		return newRow;
	}
}
