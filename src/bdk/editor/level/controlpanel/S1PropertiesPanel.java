package bdk.editor.level.controlpanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
	
	// ------------------------------------------------------------------|
	
	JPanel toolButtonPanel;
	JToggleButton buttonToolSelect;
	JToggleButton buttonToolPaint;
	ButtonGroup buttonGroup;
	
	JPanel gridPropertiesPanel;
	
	JPanel gridControlPanel;
	
	// ------------------------------------------------------------------|

	public S1PropertiesPanel(BDKLevelEditor parent) {
		super(parent);
		
		// TOOL BUTTONS -----------------------------------------------------|
		
		buttonToolSelect = new JToggleButton("Select");
		buttonToolSelect.addActionListener( e -> bdkLevelEditor.setCurrentToolName(BDKLevelEditor.TOOL_SELECT));
		
		
		buttonToolPaint = new JToggleButton("Paint");
		buttonToolPaint.addActionListener( e -> bdkLevelEditor.setCurrentToolName(BDKLevelEditor.TOOL_PAINT));
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(buttonToolSelect);
		buttonGroup.add(buttonToolPaint);
		
		toolButtonPanel = new JPanel();
		toolButtonPanel.setBorder(BorderFactory.createTitledBorder("Tools"));
		toolButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolButtonPanel.add(buttonToolSelect);
		toolButtonPanel.add(buttonToolPaint);
		
		// GRID PROPERTIES --------------------------------------------------|
		
		gridPropertiesPanel = new JPanel();
		gridPropertiesPanel.setBorder(BorderFactory.createTitledBorder("Grid properties"));
		
		// GRID CONTROLS ----------------------------------------------------|
		
		gridControlPanel = new JPanel();
		gridControlPanel.setBorder(BorderFactory.createTitledBorder("Grid controls"));
		
		// ------------------------------------------------------------------|
		
		setLayout(new GridLayout(3, 1));
		add(toolButtonPanel);
		add(gridPropertiesPanel);
		add(gridControlPanel);
		
		// Default tool is the select tool so click it from the start. That way there is always an active tool.
		buttonToolSelect.doClick();
	}

	@Override
	public void notifyDataChanged() {
		
	}
}