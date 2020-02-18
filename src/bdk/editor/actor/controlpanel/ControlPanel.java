package bdk.editor.actor.controlpanel;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BDKActorEditorPanel;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ControlPanel extends BDKActorEditorPanel{
	
	S1ActorSelectionPanel actorSelectionPanel;
	S2GeneralPropertiesPanel generalPropertiesPanel;
	S3ImageSelectionPanel imageSelectionPanel;

	public ControlPanel(BDKActorEditor parent) {
		super(parent);
		
		actorSelectionPanel = new S1ActorSelectionPanel(parent);
		generalPropertiesPanel = new S2GeneralPropertiesPanel(parent);
		imageSelectionPanel = new S3ImageSelectionPanel(parent);
			
		this.setLayout(new GridLayout(3,1));
		this.add(actorSelectionPanel);
		this.add(generalPropertiesPanel);
		this.add(imageSelectionPanel);
		
		setBorder(BorderFactory.createTitledBorder("Actors"));
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		actorSelectionPanel.notifyDataChanged(event);
		imageSelectionPanel.notifyDataChanged(event);
		generalPropertiesPanel.notifyDataChanged(event);
	}
}
