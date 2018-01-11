package bdk.editor.actor.previewpanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.game.main.Game;

/**
 * 
 * @author Andreas Farley
 *
 */
public class PreviewPanel extends BdkActorEditorPanel {
	// -------------------------------------|
	private Game previewGame;

	private JPanel previewGamePanel;
	private JPanel previewControlPanel;
	// --
	private JButton pausePreviewButton;
	// --
	private JButton stopPreviewButton;
	// --
	private JButton startPreviewButton;
	// --
	private JLabel atMouseLabel;
	private JCheckBox atMouseCheckBox;
	// --
	private JLabel setTickrateLabel;
	private JTextField setTickrateTextField;
	// -------------------------------------|

	public PreviewPanel(BdkActorEditor parent) {
		super(parent);
		// CONROLPANEL--------------------------------------------------------------------------|
		startPreviewButton = new JButton("start");
		startPreviewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		stopPreviewButton = new JButton("stop");
		stopPreviewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		pausePreviewButton = new JButton("pause");
		pausePreviewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		atMouseLabel = new JLabel("Set origin at mouse click");
		atMouseCheckBox = new JCheckBox();
		atMouseCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (atMouseCheckBox.isSelected()) {
					// TODO
				} else {
					// TODO
				}
			}
		});

		setTickrateLabel = new JLabel("Set tickrate");
		setTickrateTextField = new JTextField("60.0");
		setTickrateTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		previewControlPanel = new JPanel();
		previewControlPanel.setLayout(new GridLayout(3, 3, 10, 10));
		previewControlPanel.add(startPreviewButton);
		previewControlPanel.add(stopPreviewButton);
		previewControlPanel.add(pausePreviewButton);
		previewControlPanel.add(atMouseLabel);
		previewControlPanel.add(atMouseCheckBox);
		previewControlPanel.add(new JLabel());
		previewControlPanel.add(setTickrateLabel);
		previewControlPanel.add(setTickrateTextField);
		// -------------------------------------------------------------------------------------|

		// PREVIEWPANEL-------------------------------------------------------------------------|
		// PreviewGame requires a preferred size, but the rest will still scale
		// with the editor
		previewGame = new Game();
		previewGamePanel = new JPanel();
		previewGamePanel.setLayout(new GridLayout(1, 1));
		previewGamePanel.add(previewGame);
		previewGamePanel.setBorder(BorderFactory.createTitledBorder(""));
		// -------------------------------------------------------------------------------------|

		// setLayout(new GridLayout(2,1));
		// add(previewGamePanel);
		// add(previewControlPanel);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;

		JLabel fakeLabel;
		int counter = 0;
		while (counter < 6) {
			fakeLabel = new JLabel("");
			c.gridx = 0;
			c.gridy = counter;

			add(fakeLabel, c);
			counter++;
		}

		// Add control panel
		c.gridx = 0;
		c.gridy = 5;
		c.fill = c.HORIZONTAL;
		add(previewControlPanel, c);

		// Add preview panel
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 5;
		c.fill = c.BOTH;
		add(previewGamePanel, c);

		setBorder(BorderFactory.createTitledBorder("Preview"));
	}

	public void startPreview() {
		previewGame.startGame();
	}

	@Override
	public void notifyDataChanged() {

	}

	// TESTER---------------------------------------------------------------|
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 900);
		frame.setLayout(new GridLayout(1, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PreviewPanel panel = new PreviewPanel(new BdkActorEditor());

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		panel.startPreview();
	}
	// ---------------------------------------------------------------------|

}
