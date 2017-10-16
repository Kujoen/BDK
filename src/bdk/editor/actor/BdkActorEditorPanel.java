package bdk.editor.actor;

import javax.swing.JPanel;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorCollection;

/**
 * Used for subpanels of the BdkSpriteEditor
 * @author Kuj
 *
 */
public abstract class BdkActorEditorPanel extends JPanel{
	
	protected ActorCollection currentActorCollection;
	protected Actor currentActor;
	protected BdkActorEditor bdkActorEditor;

	public abstract void notifyDataChanged();
	
	public BdkActorEditorPanel(BdkActorEditor parent){
		this.bdkActorEditor = parent;
	}
}
