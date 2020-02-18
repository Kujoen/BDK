package bdk.util.ui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BDKInputStringDialog{
	
	private String userinput;
	
	public String showDialog(String title, String message){
		userinput = null;
		
		JLabel messageLabel = new JLabel(message);
		JTextField userInputField = new JTextField();
		
		JButton okButton = new JButton("ok");
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				userinput = userInputField.getText();
				SwingUtilities.getWindowAncestor((JButton) arg0.getSource()).dispose();
			}
		});
		
		JButton cancelButton = new JButton("cancel");
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				userinput = null;
				SwingUtilities.getWindowAncestor((JButton) e.getSource()).dispose();
			}
			
		});
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,2, 10, 10));
		content.add(messageLabel);
		content.add(userInputField);
		content.add(okButton);
		content.add(cancelButton);
		
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setTitle(title);
		dialog.setContentPane(content);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
		return userinput;
	}

}
