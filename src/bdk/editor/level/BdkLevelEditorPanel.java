package bdk.editor.level;

import javax.swing.JPanel;

public abstract class BdkLevelEditorPanel extends JPanel{


	protected BdkLevelEditor bdkLevelEditor;

	/**
	 * Write stuff to do when the data has changed here
	 */
	public abstract void notifyDataChanged();
	
	public BdkLevelEditorPanel(BdkLevelEditor parent){
		this.bdkLevelEditor = parent;
	}
}
