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
public abstract class BDKActorEditorPanel extends JPanel{
	
	protected BDKActorEditor bdkActorEditor;

	/**
	 * Write stuff to do when the data has changed here
	 */
	public abstract void notifyDataChanged(PropertyChangeEvent event);
	
	public BDKActorEditorPanel(BDKActorEditor parent){
		this.bdkActorEditor = parent;
	}
}
