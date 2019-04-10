package bdk.editor.level.controlpanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import bdk.editor.level.BDKLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;

/**
 * 
 * @author Andreas Farley
 *
 */
public class S1PropertiesPanel extends BdkLevelEditorPanel{
	
	JPanel toolButtonPanel;
	JToggleButton buttonToolSelect;
	JToggleButton buttonToolPaint;
	ButtonGroup buttonGroup;

	public S1PropertiesPanel(BDKLevelEditor parent) {
		super(parent);
		
		buttonToolSelect = new JToggleButton("Select");
		buttonToolSelect.doClick();
		
		buttonToolPaint = new JToggleButton("Paint");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(buttonToolSelect);
		buttonGroup.add(buttonToolPaint);
		
		toolButtonPanel = new JPanel();
		toolButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolButtonPanel.add(buttonToolSelect);
		toolButtonPanel.add(buttonToolPaint);
		
		setLayout(new GridLayout(3, 1));
		add(toolButtonPanel);
	}

	@Override
	public void notifyDataChanged() {
		
	}
}