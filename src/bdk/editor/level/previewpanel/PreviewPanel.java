package bdk.editor.level.previewpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bdk.editor.level.BdkLevelEditor;
import bdk.editor.level.BdkLevelEditorPanel;


/**
 * 
 * @author Andreas Farley
 *
 */
public class PreviewPanel extends BdkLevelEditorPanel{
	
	// This Scrollpane holds the rendering pane 
	private JScrollPane scrollPane;
	private JPanel scrollPaneView;
	// This panel will draw the grid content
	private RenderingPanel renderingPane;

	public PreviewPanel(BdkLevelEditor parent) {
		super(parent);
		this.setBorder(BorderFactory.createTitledBorder("Level Preview"));
		
		renderingPane = new RenderingPanel(parent);
		
		scrollPaneView = new JPanel(new FlowLayout());
		scrollPaneView.add(renderingPane);
		
		scrollPane = new JScrollPane(scrollPaneView);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	
		
		this.setLayout(new GridLayout(1,1));
		this.add(scrollPane);
	}

	@Override
	public void notifyDataChanged() {
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension();
	}

}
