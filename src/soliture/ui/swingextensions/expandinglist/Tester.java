package soliture.ui.swingextensions.expandinglist;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tester {
	
	
	
	public static void main(String[] args) {
	
		JFrame testFrame = new JFrame();
		testFrame.setSize(400, 400);
		testFrame.setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel(new GridLayout(1, 1));
		
		JExpandingListPanel expListPanel = new JExpandingListPanel(10);
		
		JExpandableRow row1 = new JExpandableRow(3);
		JButton row1Button = new JButton();
		
		row1.addComponent(new JExpandableRowComponent(row1Button, 0, 3));
		row1.setExpansionTrigger(row1Button);
		
		JExpandableRow row1_1 = new JExpandableRow(3);
		JButton row1_1Button = new JButton();
		
		row1_1.addComponent(new JExpandableRowComponent(row1_1Button, 1, 3));
		
		JExpandableRow row1_2 = new JExpandableRow(3);
		JButton row1_2Button = new JButton();
		
		row1_2.addComponent(new JExpandableRowComponent(row1_2Button, 1, 3));
		
		row1.addRow(row1_1);
		row1.addRow(row1_2);
		
		expListPanel.addRow(row1);
		
		mainPanel.add(expListPanel);
		testFrame.add(mainPanel);
		testFrame.setVisible(true);
	}

}
