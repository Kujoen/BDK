package bdk.editor.actor;

import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorCollection;

/**
 * Used for subpanels of the BdkSpriteEditor
 * @author Kuj
 *
 */
public abstract class BdkActorEditorPanel extends JPanel{
	
	protected BdkActorEditor bdkActorEditor;

	/**
	 * Write stuff to do when the data has changed here
	 */
	public abstract void notifyDataChanged(PropertyChangeEvent event);
	
	public BdkActorEditorPanel(BdkActorEditor parent){
		this.bdkActorEditor = parent;
	}
}
