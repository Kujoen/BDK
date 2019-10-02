package bdk.editor.game;

import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

public abstract class BdkGameEditorPanel extends JPanel{

	protected BDKGameEditor bdkGameEditor;

	/**
	 * Write stuff to do when the data has changed here
	 */
	public abstract void notifyDataChanged(PropertyChangeEvent event);
	
	public BdkGameEditorPanel(BDKGameEditor parent){
		this.bdkGameEditor = parent;
	}
	
}
