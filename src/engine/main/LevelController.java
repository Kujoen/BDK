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
	 * Grads a line from the levelFile and executes its command
	 */
	public void readNextCommand() throws Exception {
		System.out.println("command : " + commandList.get(commandCounter));

		if (commandList.get(commandCounter).equals("exit")) {
			//System.exit(0);
		}

		commandCounter++;
	}

	public void initialiseCommandCompareList() {
		commandCompareList = new ArrayList<>();
		commandCompareList.add("wait");
		commandCompareList.add("exit");
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
