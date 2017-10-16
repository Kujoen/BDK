package bdk.data;

public class Animation {

	private int animationLength;
	private int animationFrameRate;
	
	public Animation(int animationLength, int animationFrameRate){
		this.animationLength = animationLength;
		this.animationFrameRate = animationFrameRate;
	}

	public int getAnimationLength() {
		return animationLength;
	}

	public void setAnimationLength(int animationLength) {
		this.animationLength = animationLength;
	}

	public int getAnimationFrameRate() {
		return animationFrameRate;
	}

	public void setAnimationFrameRate(int animationFrameRate) {
		this.animationFrameRate = animationFrameRate;
	}
}
