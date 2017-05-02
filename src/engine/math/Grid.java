package engine.math;

import engine.main.Game;
import objects.data.SpriteData;
import objects.gameobjects.ObjectID;

public class Grid {

	// FINALS-----------------------------------------------|
	private static final int DEFAULT_GRID_SIZE = 8;
	//------------------------------------------------------|
	// INT--------------------------------------------------|
	private static int actual_grid_size;
	//------------------------------------------------------|
	
	public static void scaleGrid(double scaling_factor){
		actual_grid_size = (int)(DEFAULT_GRID_SIZE * scaling_factor);
	}
	
	public static int getXFor(int x, ObjectID ID){
		switch(ID){
		case ENERGYORB:
			return (int)(Game.getACTUAL_PUFFER_WIDTH() + ( x * actual_grid_size) - (SpriteData.getActual_energyorb_sprite_size() / 2 ));
		}
		return 0;
	}
	
	public static int getYFor(int y, ObjectID ID){
		switch(ID){
		case ENERGYORB:
			return (int)(Game.getACTUAL_PUFFER_WIDTH() + ( y * actual_grid_size) - (SpriteData.getActual_energyorb_sprite_size() / 2));
		}
		return 0;
	}
	
}
