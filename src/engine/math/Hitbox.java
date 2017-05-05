package engine.math;

import java.awt.Polygon;
import java.awt.Rectangle;

import objects.data.SpriteData;
import objects.gameobjects.Sprite;

public class Hitbox {
	
	private Sprite sprite;
	private Rectangle hitrec;
	
	public Hitbox(Sprite sprite){
		
		this.sprite = sprite;
		this.hitrec = new Rectangle();	
		
		
		switch(sprite.getID()){
		case PLAYER:
			hitrec.setBounds(
					(int)sprite.getPosition().getX(), 
					(int)sprite.getPosition().getY(), 
					SpriteData.getActual_player_sprite_size(), 
					SpriteData.getActual_player_sprite_size()
					);
			break;
		case ENERGYORB:
			hitrec.setBounds(
					(int)sprite.getPosition().getX(), 
					(int)sprite.getPosition().getY(), 
					SpriteData.getActual_energyorb_sprite_size(), 
					SpriteData.getActual_energyorb_sprite_size()
					);
			break;
		case PLAYER_PROJECTILE:
			hitrec.setBounds(
					(int)sprite.getPosition().getX(), 
					(int)sprite.getPosition().getY(), 
					SpriteData.getActual_player_projectile_sprite_size(), 
					SpriteData.getActual_player_projectile_sprite_size() * 4);
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
