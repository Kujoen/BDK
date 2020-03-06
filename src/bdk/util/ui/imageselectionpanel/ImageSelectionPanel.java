package bdk.util.ui.imageselectionpanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bdk.editor.main.BDKEditorWindow;
import bdk.game.main.Game;
import bdk.util.listeners.BDKMouseListener;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ImageSelectionPanel extends JPanel {

	private static final long serialVersionUID = -3853592189490691845L;
	
	public static final String CHANGE_IMAGE = "CHANGE_IMAGE";

	// -------------------------------------------------------------------------------|

	// Column Viewport of the scrollpane--------------|
	private JPanel buttonPane;
	private JButton buttonRefresh;
	private JButton buttonIncrease;
	private JButton buttonDecrease;

	// -----------------------------------------------|

	private JScrollPane scrollPanel;
	private JPanel scrollContentPane;

	// -----------------------------------------------|

	private String spriteSource;

	private int gridWidth = 4;
	private int imageWidth;
	private int imageCount;

	// -----------------------------------------------|

	private JLabel currentSelectedLabel;
	private ImageSelectionIcon currentSelectedIcon;
	private transient Map<BufferedImage, String> imageMap;
	private transient ArrayList<BufferedImage> imageList;

	// -------------------------------------------------------------------------------|

	public ImageSelectionPanel(String spriteSource) {
		this.spriteSource = spriteSource;
		this.setBorder(BorderFactory.createTitledBorder("Sprites"));

		// REFRESH ---------------------------------------|

		// A complete refresh, can be slow
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setEnabled(false);
		buttonRefresh.addActionListener(e -> loadSpritesAndFillPanel());

		// SHOW MORE -------------------------------------|

		// The increase button can decrease image size up to 10 per row
		buttonIncrease = new JButton("-");
		buttonIncrease.setEnabled(false);
		buttonIncrease.addActionListener(e -> {
			if (gridWidth < 10) {
				gridWidth++;
			}

			if (gridWidth > 1) {
				buttonDecrease.setEnabled(true);
			}

			if (gridWidth == 10) {
				buttonIncrease.setEnabled(false);
			}

			fillPanelWithIcons();
		});

		// SHOW LESS -------------------------------------|

		// The increase button can increase image size up to 1 per row
		buttonDecrease = new JButton("+");
		buttonDecrease.setEnabled(false);
		buttonDecrease.addActionListener(e -> {
			if (gridWidth > 1) {
				gridWidth--;
			}

			if (gridWidth < 10) {
				buttonIncrease.setEnabled(true);
			}

			if (gridWidth == 1) {
				buttonDecrease.setEnabled(false);
			}

			fillPanelWithIcons();
		});

		// -----------------------------------------------|

		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1, 3));
		buttonPane.add(buttonRefresh);
		buttonPane.add(buttonIncrease);
		buttonPane.add(buttonDecrease);

		scrollContentPane = new JPanel();

		scrollPanel = new JScrollPane(scrollContentPane);
		scrollPanel.setVerticalScrollBarPolicy(scrollPanel.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setHorizontalScrollBarPolicy(scrollPanel.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setColumnHeaderView(buttonPane);

		setLayout(new GridLayout(1, 1));
		add(scrollPanel);
	}

	public void loadSpritesAndFillPanel() {
		loadSprites();
		fillPanelWithIcons();
	}

	// -------------------------------------------------------------------------------|

	/**
	 * Fills the spriteList with all sprites found at
	 * {GAMENAME}/sprites/materials/tiles
	 */
	private void loadSprites() {
		imageMap = new HashMap<>();
		imageList = new ArrayList<>();

		File dir = new File(BDKEditorWindow.gameConfig.getGameName() + spriteSource);
		File[] listOfFiles = dir.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".png")) {
				try {
					BufferedImage image = ImageIO.read(listOfFiles[i]);
					imageMap.put(image, listOfFiles[i].getPath());
					imageList.add(image);
				} catch (IOException e) {
					Game.getLogger().log(Level.INFO, "Read file couldn't be loaded into an bufferedImage");
				}
			}
		}

		imageCount = imageMap.size();
	}

	/**
	 * Fills the panel with imageIcons. The images are pulled from the spriteList
	 */
	private void fillPanelWithIcons() {
		scrollContentPane.removeAll();
		scrollContentPane.setLayout(new GridLayout(0, gridWidth));
		imageWidth = scrollContentPane.getWidth() / gridWidth;

		JLabel imageLabel;

		// First add all the images to the scrollpane. We iterate over the imageList so we always get the same order
		for (BufferedImage image : imageList) {
			ImageSelectionIcon imageSelectionIcon = new ImageSelectionIcon(this, image, imageMap.get(image));
			imageSelectionIcon.prepareIcon();

			imageLabel = new JLabel("");
			imageLabel.addMouseListener(new BDKMouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel pressedLabel = (JLabel) e.getSource();
					ImageSelectionIcon pressedIcon = (ImageSelectionIcon) pressedLabel.getIcon();

					if (currentSelectedIcon != pressedIcon) {

						if (currentSelectedIcon != null) {
							currentSelectedIcon.removeHighlight();
							currentSelectedLabel.repaint();
						}

						pressedIcon.highlight();
						pressedLabel.repaint();
						
						fireImagePropertyChange(CHANGE_IMAGE, null, pressedIcon.getImagePath());

						currentSelectedIcon = pressedIcon;
						currentSelectedLabel = pressedLabel;
					}
				}
			});

			imageLabel.setIcon(imageSelectionIcon);
			// Since we called prepareIcon, the new icon could already be the selected one.
			// Therefore we need to set the label as selected aswell.
			if (currentSelectedIcon == imageSelectionIcon) {
				currentSelectedLabel = imageLabel;
			}

			scrollContentPane.add(imageLabel);
			imageCount++;
		}

		// Now it could be that we don't have enough images.
		// Check if fakelabels need to be added
		if (imageCount < gridWidth) {
			int counter = 0;
			while (counter < (gridWidth * 3 - imageCount)) {
				imageLabel = new JLabel("");
				scrollContentPane.add(imageLabel);
				counter++;
			}
		}

		buttonIncrease.setEnabled(true);
		buttonDecrease.setEnabled(true);
		buttonRefresh.setEnabled(true);

		scrollContentPane.revalidate();
		scrollContentPane.repaint();
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

	// Listeners to update parent panels when image selection changes
	protected transient List<PropertyChangeListener> imageListeners = new ArrayList<>();

	public void refreshListenerList() {
		imageListeners = new ArrayList<>();
	}

	protected void fireImagePropertyChange(String property, Object oldValue, Object newValue) {
		for (PropertyChangeListener l : imageListeners) {
			l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	public void addImagePropertyChangeListener(PropertyChangeListener listener) {
		imageListeners.add(listener);
	}

	// GETTERS & SETTERS
	// -------------------------------------------------------------|

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public ImageSelectionIcon getCurrentSelectedIcon() {
		return currentSelectedIcon;
	}

	public void setCurrentSelectedIcon(ImageSelectionIcon currentSelectedIcon) {
		this.currentSelectedIcon = currentSelectedIcon;
	}

	public String getSpriteSource() {
		return spriteSource;
	}

	/**
	 * Set a new source for sprites. Automatically reloads the panel.
	 * @param spriteSource
	 */
	public void setSpriteSource(String spriteSource) {
		this.spriteSource = spriteSource;
		loadSpritesAndFillPanel();
	}

	// -------------------------------------------------------------------------------|
}
