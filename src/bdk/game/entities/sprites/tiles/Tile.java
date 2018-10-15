package bdk.game.entities.sprites.tiles;

import java.awt.Graphics2D;

import bdk.data.BdkImageData;
import bdk.game.entities.sprites.Sprite;

/**
 * Tiles are static sprites which build the foundation of the level. They can have block/kill collision.
 * Unlike dynamic sprites, tiles have purely rectangle based hitboxes.
 * @author Andreas Farley
 */
public class Tile extends Sprite{
	
	public Tile(double x, double y, String imagePath) {
		super(x, y, Sprite.TYPE_TILE);
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void update() {
		
	}

}
