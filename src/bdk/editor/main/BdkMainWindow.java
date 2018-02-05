package bdk.editor.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bdk.data.FileUtil;
import bdk.data.GameInfo;
import bdk.editor.actor.BdkActorEditor;

public class BdkMainWindow extends JFrame {
	
	// \n[\s]* , storing this here
	
	private JTabbedPane mainPanel;
	private BdkActorEditor actorEditor;
	private static String gameName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getGameInfo();
					BdkMainWindow frame = new BdkMainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public BdkMainWindow() {
		mainPanel = new JTabbedPane();
		
		JPanel placeholder1 = new JPanel();
		mainPanel.addTab("Level-Editor", placeholder1);
		mainPanel.setMnemonicAt(0, KeyEvent.VK_F1);
		
		actorEditor = new BdkActorEditor();
		mainPanel.addTab("Actor-Editor", actorEditor);
		mainPanel.setMnemonicAt(1, KeyEvent.VK_F2);
		
		JPanel placeholder2 = new JPanel();
		mainPanel.addTab("-", placeholder2);
		mainPanel.setMnemonicAt(2, KeyEvent.VK_F3);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setContentPane(mainPanel);
		setResizable(true);
		pack();
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1280,720));
		setTitle("BDK");
		setVisible(true);
		
		//Add changelistener after setVisible so the preview doesnt bug
		mainPanel.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(mainPanel.getSelectedIndex()){
				case 0:
					//TODO
					break;
				}
			}
		});
	}
	
	//------------------------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	//------------------------------------------------------------------------------------------------|
	public static void setGameName(String gameName) {
		BdkMainWindow.gameName = gameName;
	}

	public static String getGameName() {
		return gameName;
	}
	public BdkActorEditor getActorEditor() {
		return actorEditor;
	}

	public void setActorEditor(BdkActorEditor actorEditor) {
		this.actorEditor = actorEditor;
	}

	private static void getGameInfo(){
		GameInfo gameinfo = (GameInfo) FileUtil.loadSerializedObject(GameInfo.PATH);
		gameName = gameinfo.getGameInfo().get("NAME");
	}

}
