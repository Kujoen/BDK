package bdk.editor.actor.controlpanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bdk.editor.actor.BDKActorEditor;
import bdk.editor.actor.BDKActorEditorPanel;
import bdk.editor.level.BDKLevelEditor;
import bdk.editor.main.BDKEditorWindow;
import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.ActorType;
import bdk.util.graphics.BDKImageEditor;
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
		
		imageSelectionPanel.addImagePropertyChangeListener(e -> bdkActorEditor.notifyDataChanged(e));
		
		this.setLayout(new GridLayout());
		this.add(imageSelectionPanel);
	}

	@Override
	public void notifyDataChanged(PropertyChangeEvent event) {
		// Do nothing if you fired the event
		if(event.getSource() == imageSelectionPanel) {
			return;
		}
		
		// If the actor type has changed, we need to change the sprite selection path
		if(event.getPropertyName().equals(BDKActorEditor.CHANGE_ACTOR_TYPE)) {
			switch((ActorType) event.getNewValue()) {
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
	}

}
