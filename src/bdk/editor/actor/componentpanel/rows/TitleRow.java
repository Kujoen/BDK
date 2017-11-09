package bdk.editor.actor.componentpanel.rows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import soliture.ui.swingextensions.expandinglist.ExpandableRow;

public class TitleRow extends ExpandableRow{

	//--JComponents of the titlerow
	JButton expandButton;
	JButton addButton;
	JButton infoButton;
	JLabel rowTitle;
	
	public TitleRow(String title){
		this.expandButton = new JButton();
		this.addButton = new JButton();
		this.infoButton = new JButton();
		this.rowTitle = new JLabel(title);
		
		initialiseRow();
	}
	
	private void initialiseRow(){
		
		rowTitle.setForeground(Color.BLACK);
		rowTitle.setFont(new Font("Terminal" , Font.PLAIN, 16));
		
		addButton.setEnabled(true);
		addButton.setBorder(null);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		
		addButton.setIcon(new ImageIcon(TitleRow.class.getResource("/resources/icons/add_circle.png")));
		
		infoButton.setEnabled(true);
		infoButton.setBorder(null);
		infoButton.setBorderPainted(false);
		infoButton.setContentAreaFilled(false);
		infoButton.setIcon(new ImageIcon(TitleRow.class.getResource("/resources/icons/info_black.png")));
		
		expandButton.setEnabled(false);
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
		
		addComponentToRow(expandButton, 0, 1);
		addComponentToRow(rowTitle, 1, 3);
		addComponentToRow(addButton, 4, 1);
		addComponentToRow(infoButton, 5, 1);		
	}
	
	// --------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------------------------------|

	public JButton getExpandButton() {
		return expandButton;
	}

	public void setExpandButton(JButton expandButton) {
		this.expandButton = expandButton;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getInfoButton() {
		return infoButton;
	}

	public void setInfoButton(JButton infoButton) {
		this.infoButton = infoButton;
	}

	public JLabel getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(JLabel rowTitle) {
		this.rowTitle = rowTitle;
	}
}
