package controllers;

import engine.main.Level;
import engine.main.Window;

public class LevelController {
	
	//GAME ----------------------------------------------------------|
	private Level level;
	//---------------------------------------------------------------|
	
	public LevelController(Level level){
		this.level = level;
	}
	
	public void updateLevel(){
		switch(Window.getGlobalgame().getTickcounter()){
		
		}
	}

}
