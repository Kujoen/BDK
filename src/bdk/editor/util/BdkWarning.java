package bdk.editor.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BdkWarning {
	
	public static void showWarning(String warningMessage) {
		
		JLabel messageLabel = new JLabel(warningMessage);
		
		JButton okButton = new JButton("ok");
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.getWindowAncestor((JButton) arg0.getSource()).dispose();
			}
		});
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,1, 10, 10));
		content.add(messageLabel);
		content.add(okButton);
		
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setTitle("Warning");
		dialog.setContentPane(content);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
	}

}
