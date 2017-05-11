package controllers;

import engine.main.Level;
import engine.main.Window;
import engine.math.Grid;
import engine.math.Vector2D;
import objects.data.SpriteData;
import objects.gameobjects.EnergyOrb;
import objects.gameobjects.ObjectID;
import objects.gameobjects.Player;

public class LevelController {
	
	//GAME ----------------------------------------------------------|
	private Level level;
	//---------------------------------------------------------------|
	// INT-----------------------------------------------------------|
	private int leveltickcounter = 0;
	//---------------------------------------------------------------|
	
	public LevelController(Level level){
		this.level = level;
	}
	
	public void updateLevel(){
		switch(level.getLevelid()){
			case 0:
				updateLevel1();
				break;
		}
		
		leveltickcounter++;
	}
	
	private void updateLevel1(){
		switch(leveltickcounter){
		case 0:
			Player player = new Player(
					new Vector2D(level.getGame().getWindow().getACTUALWIDTH() / 2, level.getGame().getWindow().getACTUALHEIGHT() / 2),
					new Vector2D(SpriteData.getActual_player_speed(), SpriteData.getActual_player_speed()), 
					1, 
					ObjectID.PLAYER,
					true);
			
			level.getSpriteList().add(player);
			level.setPlayer(player);
				break;
		case 180:
			level.getSpriteList().add(new EnergyOrb(
					new Vector2D(Grid.getXFor(16, ObjectID.ENERGYORB), Grid.getYFor(16, ObjectID.ENERGYORB)),
					new Vector2D(0, 0), 10, ObjectID.ENERGYORB));

			level.getSpriteList().add(new EnergyOrb(
					new Vector2D(Grid.getXFor(48, ObjectID.ENERGYORB), Grid.getYFor(16, ObjectID.ENERGYORB)),
					new Vector2D(0, 0), 10, ObjectID.ENERGYORB));
			break;
		}
	}

}
