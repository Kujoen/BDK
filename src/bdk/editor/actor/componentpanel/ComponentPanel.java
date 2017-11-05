package bdk.editor.actor.componentpanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import bdk.editor.actor.BdkActorEditor;
import bdk.editor.actor.BdkActorEditorPanel;
import bdk.editor.actor.componentpanel.rows.TitleRow;
import de.soliture.ui.expandinglist.JExpandingList;

public class ComponentPanel extends BdkActorEditorPanel{
	
	JPanel contentPane;
	JExpandingList expandingList;
	
	//--Main rows
	TitleRow rowEmitter;
	TitleRow rowInitializer;
	TitleRow rowOperator;
	TitleRow rowChildren;

	public ComponentPanel(BdkActorEditor parent) {
		super(parent);	
		
		this.setLayout(new GridLayout(1, 1));

	}

	private void buildList(){
		this.removeAll();
		expandingList = new JExpandingList(40, 6);
		this.add(expandingList);
		
		//--Emitter
		rowEmitter = new TitleRow("Emitter");
		rowEmitter.getAddButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		rowEmitter.getInfoButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//--Initalizer
		rowInitializer = new TitleRow("Initializers");
		
		//--Operator
		rowOperator = new TitleRow("Operators");

		//--Children
		rowChildren = new TitleRow("Children");
		
		expandingList.addRow(rowEmitter.getTitleRow());
		expandingList.addRow(rowInitializer.getTitleRow());
		expandingList.addRow(rowOperator.getTitleRow());
		expandingList.addRow(rowChildren.getTitleRow());
	}

	@Override
	public void notifyDataChanged() {
		this.currentActor = bdkActorEditor.getCurrentActor();
		this.currentActorCollection = bdkActorEditor.getCurrentActorCollection();
		
		if(currentActor != null){
			buildList();
		}
	}
}
