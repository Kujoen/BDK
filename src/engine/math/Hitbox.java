package engine.math;

import objects.gameobjects.Sprite;

public class Hitbox {
	
	Sprite sprite;
	
	//public constructor for now type 1 , 2 : rectangle , circle
	public Hitbox(Sprite sprite , int type){
		this.sprite = sprite;
	}
}
