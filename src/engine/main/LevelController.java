package engine.main;

import java.util.ArrayList;

import objects.IO.LevelFileIO;

public class LevelController {

	// ENGINEOBJECTS---------------------------------------|
	private Level level;
	// ----------------------------------------------------|
	// IO--------------------------------------------------|
	private LevelFileIO ioHandler;
	// ----------------------------------------------------|
	// LIST------------------------------------------------|
	private ArrayList<String> commandList;
	private ArrayList<String> commandCompareList;
	// ----------------------------------------------------|
	// INT-------------------------------------------------|
	private int commandCounter;
	// ----------------------------------------------------|

	/**
	 * The LevelController controls the flow of the level based on the levels
	 * loaded levelfile
	 * 
	 * @param level
	 *            : The level to control
	 */
	public LevelController(Level level) {
		this.level = level;
		this.commandCounter = 0;
		this.commandList = LevelFileIO.getCommandList(this.getLevel().getLevelFile());

		initialiseCommandCompareList();

		try {
			checkCommandList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * reads a line from the levelFile and executes its command
	 */
	public void readNextCommand() throws Exception {
		
		String command = commandList.get(commandCounter);
		
		System.out.println("command : " + command);

		// Exits the programm
		if (command.equals("exit")) {
			System.exit(0);
		}
		// Displays a changeable Leveltitle
		else if(command.contains("display")){
			int startindex = command.indexOf("{");
			System.out.println(startindex);
			String message = command.substring(startindex);
			
			level.getGame().setDisplayLeveltitle(true);
			level.getGame().setLeveltitle(message);
		}
		// Stops display of leveltitle
		else if(command.contains("stopdsplay")){
			level.getGame().setDisplayLeveltitle(false);
		}

		commandCounter++;
	}

	public void initialiseCommandCompareList() {
		
		// TODO: implement a commandCompareList for the checkCommandList
		
	}

	public void checkCommandList() throws Exception {

		// TODO: implement a check of the commandList from the levelfile

	}

	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------|
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public LevelFileIO getIoHandler() {
		return ioHandler;
	}

	public void setIoHandler(LevelFileIO ioHandler) {
		this.ioHandler = ioHandler;
	}
	// ------------------------------------------------------------------------|
}
