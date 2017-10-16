package bdk.game.entities.sprites.tiles;

import java.awt.Graphics2D;

import bdk.data.BdkImageData;
import bdk.game.entities.sprites.Sprite;

/**
 * Tiles are static sprites which build the foundation of the level. They can have block/kill collision.
 * Unlike dynamic sprites, tiles have purely rectangle based hitboxes.
 * @author Kuj
 */
public class Tile extends Sprite{
	
	public Tile(double x, double y, String imagePath) {
		super(x, y, Sprite.TYPE_TILE);
	}

	@Override
	public void render(Graphics2D g) {
		//First get the image from BdkImageData
		if(spriteImage == null){
			spriteImage = BdkImageData.getImageList().get(imagePath);
		}
		
		g.drawImage(spriteImage, (int) x, (int) y, null);
	}

	/**
	 * Tiles are only updated when the camera is update
	 */
	@Override
	public void update() {
		//TODO: Add updating to tiles based on camera
	}

}
