package bdk.editor.level;

import javax.swing.JPanel;

public abstract class BdkLevelEditorPanel extends JPanel{


	protected BDKLevelEditor bdkLevelEditor;

	/**
	 * Write stuff to do when the data has changed here
	 */
	public abstract void notifyDataChanged();
	
	public BdkLevelEditorPanel(BDKLevelEditor parent){
		this.bdkLevelEditor = parent;
	}
}
