package bdk.editor.actor.controlpanel;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ControlPanel extends BdkActorEditorPanel{
	
	S1ActorSelectionPanel actorSelectionPanel;
	S2GeneralPropertiesPanel generalPropertiesPanel;
	S2ImageSelectionPanel imageSelectionPanel;
	S3TypePropertiesPanel advancedPropertiesPanel;
	JPanel s2SubPanel;

	public ControlPanel(BDKActorEditor parent) {
		super(parent);
		
		actorSelectionPanel = new S1ActorSelectionPanel(parent);
		generalPropertiesPanel = new S2GeneralPropertiesPanel(parent);
		imageSelectionPanel = new S2ImageSelectionPanel(parent);
		advancedPropertiesPanel = new S3TypePropertiesPanel(parent);
		
//		Add section2 panels to their subpanel
		s2SubPanel = new JPanel();
		s2SubPanel.setLayout(new GridLayout(1,2));
		s2SubPanel.add(imageSelectionPanel);
		s2SubPanel.add(generalPropertiesPanel);
			
		
		setLayout(new GridLayout(3,1));
		add(actorSelectionPanel);
		add(s2SubPanel);
		add(advancedPropertiesPanel);
		setBorder(BorderFactory.createTitledBorder("Actors"));
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		actorSelectionPanel.notifyDataChanged(event);
		imageSelectionPanel.notifyDataChanged(event);
		generalPropertiesPanel.notifyDataChanged(event);
	}
}
