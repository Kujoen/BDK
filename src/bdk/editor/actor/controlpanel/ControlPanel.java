package bdk.editor.actor.controlpanel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;

//********************************************************************************

//********************************************************************************

public class ControlPanel extends BdkActorEditorPanel{
	
	S1ActorSelectionPanel actorSelectionPanel;
	S2GeneralPropertiesPanel mainPropertiesPanel;
	S2ImageSelectionPanel imageSelectionPanel;
	S3TypePropertiesPanel advancedPropertiesPanel;
	JPanel s2SubPanel;

	public ControlPanel(BdkActorEditor parent) {
		super(parent);
		
		actorSelectionPanel = new S1ActorSelectionPanel(parent);
		mainPropertiesPanel = new S2GeneralPropertiesPanel(parent);
		imageSelectionPanel = new S2ImageSelectionPanel(parent);
		advancedPropertiesPanel = new S3TypePropertiesPanel(parent);
		
//		Add section2 panels to their subpanel
		s2SubPanel = new JPanel();
		s2SubPanel.setLayout(new GridLayout(1,2));
		s2SubPanel.add(imageSelectionPanel);
		s2SubPanel.add(mainPropertiesPanel);
			
		
		setLayout(new GridLayout(3,1));
		add(actorSelectionPanel);
		add(s2SubPanel);
		add(advancedPropertiesPanel);
		setBorder(BorderFactory.createTitledBorder("Properties"));
	}

	@Override
	public void notifyDataChanged() {
		actorSelectionPanel.notifyDataChanged();
		imageSelectionPanel.notifyDataChanged();
		mainPropertiesPanel.notifyDataChanged();
	}
}
