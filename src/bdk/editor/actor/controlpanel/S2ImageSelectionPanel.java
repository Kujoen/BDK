package bdk.editor.actor.controlpanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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

import bdk.data.BdkImageData;
import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.editor.main.BdkMainWindow;

/**
 * 
 * @author Andreas Farley
 *
 */
public class S2ImageSelectionPanel extends BdkActorEditorPanel {

	// Column Viewport of the scrollpane--------------|
	private JPanel buttonPane;
	private JButton buttonRefresh;
	private JButton buttonIncrease;
	private JButton buttonDecrease;
	// --
	private JScrollPane scrollPanel;
	private JPanel scrollContentPane;
	// --
	private int gridWidth = 4;
	private int imageWidth;
	private int imageCount;
	private JLabel imageLabel;
	// --
	private Map<Icon, String> imageMap;

	public S2ImageSelectionPanel(BdkActorEditor parent) {
		super(parent);

		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setEnabled(false);
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayImagePreview();
			}
		});

		buttonIncrease = new JButton("+");
		buttonIncrease.setEnabled(false);
		buttonIncrease.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (gridWidth < 10) {
					gridWidth++;
				}

				if (gridWidth > 1) {
					buttonDecrease.setEnabled(true);
				}

				if (gridWidth == 10) {
					buttonIncrease.setEnabled(false);
				}

				displayImagePreview();
			}
		});

		buttonDecrease = new JButton("-");
		buttonDecrease.setEnabled(false);
		buttonDecrease.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (gridWidth > 1) {
					gridWidth--;
				}

				if (gridWidth < 10) {
					buttonIncrease.setEnabled(true);
				}

				if (gridWidth == 1) {
					buttonDecrease.setEnabled(false);
				}

				displayImagePreview();
			}
		});

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

	private void displayImagePreview() {
		
		scrollContentPane.removeAll();
		scrollContentPane.setLayout(new GridLayout(0, gridWidth));
		imageWidth = scrollContentPane.getWidth() / gridWidth;
		imageCount = 0;
		imageMap = new HashMap<Icon, String>();

		// Check if currently a actor is selected
		if (!(currentActor == null)) {
			// Check if actortype was set
			if (!(currentActor.getActorType() == null)) {
				// Search the directory of the actortype
				File dir = new File(BdkMainWindow.getGameName() + "/sprites/actors/" + currentActor.getActorType());
				File[] listOfFiles = dir.listFiles();
				
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						if (listOfFiles[i].getName().contains(".png")) {
							try {
								BufferedImage image = ImageIO.read(listOfFiles[i]);
								// Does the image have to be rescaled to fit into a column ?
								if (image.getWidth() < imageWidth || image.getWidth() > imageWidth) {
									image = BdkImageData.scale(image, imageWidth,
											imageWidth * (image.getHeight() / image.getWidth()));
								}

								imageLabel = new JLabel("");
								imageLabel.addMouseListener( new MouseListener(){

									@Override
									public void mouseClicked(MouseEvent arg0) {
										JLabel pressedLabel = (JLabel) arg0.getSource();
										bdkActorEditor.getCurrentActor().setImagePath(imageMap.get(pressedLabel.getIcon()));
										bdkActorEditor.notifyDataChanged();
									}

									@Override
									public void mouseEntered(MouseEvent arg0) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void mouseExited(MouseEvent arg0) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void mousePressed(MouseEvent arg0) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void mouseReleased(MouseEvent arg0) {
										// TODO Auto-generated method stub
										
									}				
								});
								
								//Highlight the image if its the current selected image
								if (listOfFiles[i].getPath().equals(currentActor.getImagePath())) {
									image = BdkImageData.highlight(image, Color.RED);
								}
								
								// Put the image into the imagemap so we can find it later
								imageLabel.setIcon(new ImageIcon(image));
								imageMap.put(imageLabel.getIcon(), listOfFiles[i].getPath());

								// Add the JLabel to the panel
								scrollContentPane.add(imageLabel);
								imageCount++;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}

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
		}
	}

	@Override
	public void notifyDataChanged() {
		currentActorCollection = bdkActorEditor.getCurrentActorCollection();
		currentActor = bdkActorEditor.getCurrentActor();
		displayImagePreview();
	}

}
