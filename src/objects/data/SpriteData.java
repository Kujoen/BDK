package objects.data;

public class SpriteData {
	// FINALS//----------------------------------------------|
	private static final int DEFAULT_PLAYER_SPEED = 8;
	private static final int DEFAULT_PLAYER_SIZE = 32;
	private static final int DEFAULT_PLAYER_PROJECTILE_SIZE = 20;
	private static final int DEFAULT_PLAYER_PROJECTILE_SPEED = -16;
	private static final int DEFAULT_SCROLLING_SPEED = 2;
	private static final int DEFAULT_DUMMY_SIZE = 32;
	//-------------------------------------------------------|
	// INTS//------------------------------------------------|
	private static int ACTUAL_PLAYER_SPEED;
	private static int ACTUAL_PLAYER_SIZE;
	private static int ACTUAL_PLAYER_PROJECTILE_SIZE;
	private static int ACTUAL_PLAYER_PROJECTILE_SPEED;
	private static int ACTUAL_SCROLLING_SPEED;
	private static int ACTUAL_DUMMY_SIZE;
	//-------------------------------------------------------|
	
	public static void scaleData(double scaling_factor){
		ACTUAL_PLAYER_SPEED =(int) (DEFAULT_PLAYER_SPEED * scaling_factor);
		ACTUAL_PLAYER_SIZE =(int) (DEFAULT_PLAYER_SIZE * scaling_factor);
		ACTUAL_PLAYER_PROJECTILE_SIZE =(int) (DEFAULT_PLAYER_PROJECTILE_SIZE * scaling_factor);
		ACTUAL_PLAYER_PROJECTILE_SPEED =(int) (DEFAULT_PLAYER_PROJECTILE_SPEED * scaling_factor);
		ACTUAL_SCROLLING_SPEED = (int) (DEFAULT_SCROLLING_SPEED * scaling_factor);
		ACTUAL_DUMMY_SIZE = (int) (DEFAULT_DUMMY_SIZE * scaling_factor);
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|
	
	public static int getACTUAL_PLAYER_SPEED() {
		return ACTUAL_PLAYER_SPEED;
	}

	public static void setACTUAL_PLAYER_SPEED(int aCTUAL_PLAYER_SPEED) {
		ACTUAL_PLAYER_SPEED = aCTUAL_PLAYER_SPEED;
	}

	public static int getACTUAL_PLAYER_SIZE() {
		return ACTUAL_PLAYER_SIZE;
	}

	public static void setACTUAL_PLAYER_SIZE(int aCTUAL_PLAYER_SIZE) {
		ACTUAL_PLAYER_SIZE = aCTUAL_PLAYER_SIZE;
	}

	public static int getACTUAL_PLAYER_PROJECTILE_SIZE() {
		return ACTUAL_PLAYER_PROJECTILE_SIZE;
	}

	public static void setACTUAL_PLAYER_PROJECTILE_SIZE(int aCTUAL_PROJECTILE_SIZE) {
		ACTUAL_PLAYER_PROJECTILE_SIZE = aCTUAL_PROJECTILE_SIZE;
	}

	public static int getDefaultPlayerSpeed() {
		return DEFAULT_PLAYER_SPEED;
	}

	public static int getDefaultPlayerSize() {
		return DEFAULT_PLAYER_SIZE;
	}

	public static int getDefaultPlayerProjectileSize() {
		return DEFAULT_PLAYER_PROJECTILE_SIZE;
	}

	public static int getACTUAL_SCROLLING_SPEED() {
		return ACTUAL_SCROLLING_SPEED;
	}

	public static void setACTUAL_SCROLLING_SPEED(int aCTUAL_SCROLLING_SPEED) {
		ACTUAL_SCROLLING_SPEED = aCTUAL_SCROLLING_SPEED;
	}

	public static int getDefaultScrollingSpeed() {
		return DEFAULT_SCROLLING_SPEED;
	}

	public static int getACTUAL_PLAYER_PROJECTILE_SPEED() {
		return ACTUAL_PLAYER_PROJECTILE_SPEED;
	}

	public static void setACTUAL_PLAYER_PROJECTILE_SPEED(int aCTUAL_PLAYER_PROJECTILE_SPEED) {
		ACTUAL_PLAYER_PROJECTILE_SPEED = aCTUAL_PLAYER_PROJECTILE_SPEED;
	}

	public static int getACTUAL_DUMMY_SIZE() {
		return ACTUAL_DUMMY_SIZE;
	}

	public static void setACTUAL_DUMMY_SIZE(int aCTUAL_DUMMY_SIZE) {
		ACTUAL_DUMMY_SIZE = aCTUAL_DUMMY_SIZE;
	}

	public static int getDefaultPlayerProjectileSpeed() {
		return DEFAULT_PLAYER_PROJECTILE_SPEED;
	}

	public static int getDefaultDummySize() {
		return DEFAULT_DUMMY_SIZE;
	}
	
	// -----------------------------------------------------------------------------|
}
