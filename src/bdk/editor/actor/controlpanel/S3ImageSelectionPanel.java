package bdk.editor.actor.controlpanel;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BDKActorEditorPanel;
import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorType;
import bdk.util.ui.imageselectionpanel.ImageSelectionPanel;

/**
 * 
 * @author Andreas Farley
 *
 */
public class S3ImageSelectionPanel extends BDKActorEditorPanel {

	private static final long serialVersionUID = -1111941703993788655L;

	// -------------------------------------------------------------------------------|

	private ImageSelectionPanel imageSelectionPanel;

	// -------------------------------------------------------------------------------|

	public S3ImageSelectionPanel(BDKActorEditor parent) {
		super(parent);

		this.imageSelectionPanel = new ImageSelectionPanel("");

		imageSelectionPanel.addImagePropertyChangeListener(e -> {
			if(bdkActorEditor.getCurrentActor() != null) {
				bdkActorEditor.getCurrentActor().setSpritePath((String) e.getNewValue());
				bdkActorEditor.notifyDataChanged(e);
			}
		});

		this.setLayout(new GridLayout());
		this.add(imageSelectionPanel);
	}

	private void setSpriteSourceForType(ActorType type) {
		switch (type) {
		case ENEMY:
			imageSelectionPanel.setSpriteSource(Sprite.ENEMY_SPRITE_PATH);
			break;
		case PROJECTILE:
			imageSelectionPanel.setSpriteSource(Sprite.PROJECTILE_SPRITE_PATH);
			break;
		case PLAYER:
			imageSelectionPanel.setSpriteSource(Sprite.PLAYER_SPRITE_PATH);
			break;
		}
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		// Do nothing if you fired the event
		if (event.getSource() == imageSelectionPanel) {
			return;
		}

		// If the actor type has changed, we need to change the sprite selection path
		if (event.getPropertyName().equals(BDKActorEditor.CHANGE_ACTOR_TYPE)) {
			setSpriteSourceForType((ActorType) event.getNewValue());
		}

		if (event.getPropertyName().equals(BDKActorEditor.CHANGE_ACTOR)) {
			if(event.getNewValue() != null) {
				setSpriteSourceForType(((Actor) event.getNewValue()).getActorType());	
			}
		}
	}
}
