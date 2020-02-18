package bdk.util.ui.imageselectionpanel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import bdk.util.BDKCopy;
import bdk.util.graphics.BDKImageEditor;

public class ImageSelectionIcon extends ImageIcon {

	private static final long serialVersionUID = 6591928215992652924L;

	// -------------------------------------------------------------------------------|

	private ImageSelectionPanel parentPanel;
	private BufferedImage imageSource;
	private String imagePath;

	// -------------------------------------------------------------------------------|

	public ImageSelectionIcon(ImageSelectionPanel parentPanel, BufferedImage imageSource, String imagePath) {
		this.parentPanel = parentPanel;
		this.imageSource = imageSource;
		this.imagePath = imagePath;
	}

	/**
	 * Scales and highlights the image if needed
	 */
	public void prepareIcon() {
		this.setCopyAndScale();

		ImageSelectionIcon currentIcon = parentPanel.getCurrentSelectedIcon();

		// Check if we need to highlight the icon
		if (currentIcon != null && currentIcon.getImagePath().equals(imagePath)) {
			this.highlight();
			parentPanel.setCurrentSelectedIcon(this);
		}
	}

	/**
	 * Highlights the image by adding a border around it. The active image should
	 * already be scaled
	 */
	public void highlight() {
		this.setImage(BDKImageEditor.highlight((BufferedImage) this.getImage(), Color.red));
	}
	
	public void removeHighlight() {
		this.setCopyAndScale();
	}

	/**
	 * Makes a copy from the image source and scales it according to the parent panels imageWidth
	 */
	private void setCopyAndScale() {
		int imageWidth = parentPanel.getImageWidth();
		
		if (imageSource.getWidth() < imageWidth || imageSource.getWidth() > imageWidth) {
			this.setImage(BDKImageEditor.scale(BDKCopy.deepCopyBufferedImage(imageSource), parentPanel.getImageWidth(),
					parentPanel.getImageWidth() * (imageSource.getHeight() / imageSource.getWidth())));
		}
	}
	
	// GETTERS & SETTERS
	// -------------------------------------------------------------|

	public BufferedImage getImageSource() {
		return imageSource;
	}

	public void setImageSource(BufferedImage imageSource) {
		this.imageSource = imageSource;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	// -------------------------------------------------------------------------------|
}
