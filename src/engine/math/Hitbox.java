package engine.math;

import java.awt.Polygon;
import java.awt.Rectangle;

import objects.data.SpriteData;
import objects.gameobjects.Sprite;

public class Hitbox {
	
	private Sprite sprite;
	private Rectangle hitrec;
	private boolean isplayer;
	
	public Hitbox(Sprite sprite){
		this.sprite = sprite;
		
		switch(sprite.getID()){
		case PLAYER:
			isplayer = true;
			hitrec = new Rectangle();	
			hitrec.setBounds(
					(int)sprite.getPosition().getX(), 
					(int)sprite.getPosition().getY(), 
					SpriteData.getACTUAL_PLAYER_SIZE(), 
					SpriteData.getACTUAL_PLAYER_SIZE()
					);
			
			break;
			
		case DUMMY:
			hitrec = new Rectangle();
			hitrec.setBounds(
					(int)sprite.getPosition().getX(), 
					(int)sprite.getPosition().getY(), 
					SpriteData.getACTUAL_DUMMY_SIZE(),
					SpriteData.getACTUAL_DUMMY_SIZE()
					);
			break;
		}
		
	}
	
	public void updateHitbox(){
		hitrec.setLocation((int)sprite.getPosition().getX(), (int)sprite.getPosition().getY());
	}

	public Rectangle getHitrec() {
		return hitrec;
	}

	public void setHitrec(Rectangle hitrec) {
		this.hitrec = hitrec;
	}
	
}
