package bdk.editor.level.previewpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;

import bdk.editor.level.BdkLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;

public class RenderingPanel extends BdkLevelEditorPanel {
	
	private final Dimension d = new Dimension(1280,720);

	public RenderingPanel(BdkLevelEditor parent) {
		super(parent);
		setBorder(BorderFactory.createLineBorder(Color.red));
	}

	@Override
	public void notifyDataChanged() {
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.red);

		int counter = 0;

		while (counter <= 1280) {
			if (counter != 1280) {
				g.drawLine(counter, 0, counter, 720);
				g.drawLine(0, counter, 1280, counter);
			} else {
				g.drawLine(1279, 0, 1279, 719);
				g.drawLine(0, 719, 1279, 719);
			}
			counter += 40;
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(d);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(d);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(d);
	}
}
