package bdk.editor.level.controlpanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bdk.editor.level.BdkLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ControlPanel extends BdkLevelEditorPanel {

	// holds the image/prop panel
	private JPanel contentPane;
	private S1ImageSelectionPanel imageSelectPanel;
	private S2PropertiesPanel propertiesPanel;

	private GridBagConstraints gc;

	public ControlPanel(BdkLevelEditor parent) {
		super(parent);
		this.setBorder(BorderFactory.createTitledBorder("Images & Options"));

		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());

		imageSelectPanel = new S1ImageSelectionPanel(parent);
		propertiesPanel = new S2PropertiesPanel(parent);

		gc = new GridBagConstraints();
		gc.fill = gc.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		// Hack to fill all rows

		gc.gridx = 0;
		gc.gridy = 0;
		contentPane.add(new JLabel(), gc);

		gc.gridx = 0;
		gc.gridy = 1;
		contentPane.add(new JLabel(), gc);

		gc.gridx = 0;
		gc.gridy = 2;
		contentPane.add(new JLabel(), gc);

		// Add the panels

		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridheight = 2;
		contentPane.add(imageSelectPanel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridheight = 1;
		contentPane.add(propertiesPanel, gc);
		
		this.setLayout(new GridLayout());
		this.add(contentPane);
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(0,0);
		
	}

	@Override
	public void notifyDataChanged() {

	}

}
