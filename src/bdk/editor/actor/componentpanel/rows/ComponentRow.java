package bdk.editor.actor.componentpanel.rows;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import bdk.editor.util.BdkFont;
import soliture.ui.swingextensions.expandinglist.ExpandableRow;

public class ComponentRow extends ExpandableRow {
	
	JLabel titleLabel;
	
	public ComponentRow(String title) {
		titleLabel = new JLabel(title);
		titleLabel.setFont(new BdkFont(Font.BOLD , 12));
		addComponentToRow(titleLabel,  2, 3);
	}
	

}
