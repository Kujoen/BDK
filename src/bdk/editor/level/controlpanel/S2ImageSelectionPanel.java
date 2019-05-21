package bdk.editor.level.controlpanel;
import java.awt.Dimension;
import java.awt.GridLayout;

import bdk.editor.level.BDKLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;
import bdk.util.ui.imageselectionpanel.ImageSelectionPanel;

/**
 *	
 * 
 * @author Andreas Farley
 *
 */
public class S2ImageSelectionPanel extends BdkLevelEditorPanel {
	
	private static final long serialVersionUID = -3853592189490691845L;

	// -------------------------------------------------------------------------------|

	private ImageSelectionPanel imageSelectionPanel;
	
	// -------------------------------------------------------------------------------|

	public S2ImageSelectionPanel(BDKLevelEditor parent) {
		super(parent);
		
		this.imageSelectionPanel = new ImageSelectionPanel("/sprites/materials/tiles");
		
		imageSelectionPanel.addImagePropertyChangeListener(e -> bdkLevelEditor.setCurrentSpriteImagePath((String) e.getNewValue()));
		
		this.setLayout(new GridLayout());
		this.add(imageSelectionPanel);
	}

	@Override
	public void notifyDataChanged() {
		imageSelectionPanel.loadSpritesAndFillPanel();
	}

	// --------------------------------------------------------------------------|
	// These overridess make sure the panel doesn't resize when loading the images
	@Override
	public Dimension getPreferredSize() {
		return new Dimension();
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension();
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension();
	}
	// --------------------------------------------------------------------------|
}
