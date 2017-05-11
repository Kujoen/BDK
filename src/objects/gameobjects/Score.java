package objects.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.math.Vector2D;
import objects.data.ImageData;

public class Score extends Sprite {

	// INT---------------------------------------|
	private int score;
	// -------------------------------------------|
	private ArrayList<BufferedImage> imageList;

	// -------------------------------------------|
	public Score(Vector2D position, Vector2D movementvector, int health, ObjectID ID) {
		super(position, movementvector, health, ID);
	}

	@Override
	public void update() {
		int numberOfZeros = 10;
		int currentNumber = 0;
		int scoreNumber = score;

		for (int i = 0; i < 10; i++) {
			if ((scoreNumber / (10 ^ i)) != 0) {
				numberOfZeros = 10 - i;
			}
		}
		for (int i = 1; i <= numberOfZeros; i++) {
			imageList.add(ImageData.getScoreSprite(0));
		}

		int[] tempList = new int[10 - numberOfZeros];

		for (int j = 1; j < 10 - numberOfZeros; j++) {
			currentNumber = scoreNumber % 10;
			tempList[9 - numberOfZeros] = currentNumber;
			scoreNumber /= 10;
		}
		for (int k = 0; k <= 9 - numberOfZeros; k--) {
			imageList.add(ImageData.getScoreSprite(tempList[k]));
		}

	}

	@Override
	public void render(Graphics g) {
		for (BufferedImage i : imageList) {

		}
	}

	@Override
	public void animationController() {

	}

}
