package bdk.editor.level.controlpanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import bdk.editor.level.BdkLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;

/**
 * 
 * @author Andreas Farley
 *
 */
public class S1PropertiesPanel extends BdkLevelEditorPanel{
	
	JPanel toolButtonPanel;
	JButton buttonToolPaint;

	public S1PropertiesPanel(BdkLevelEditor parent) {
		super(parent);
		
		buttonToolPaint = new JButton("Select");
		
		buttonToolPaint = new JButton("Fill");
		
		
		toolButtonPanel = new JPanel();
		toolButtonPanel.setLayout(new FlowLayout());
		
		setLayout(new GridLayout(3, 1));
		
	}

	@Override
	public void notifyDataChanged() {
		
	}

}
