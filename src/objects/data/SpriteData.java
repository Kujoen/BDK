package objects.data;

public class SpriteData {
	// FINALS//----------------------------------------------|
	public static final int DEFAULT_PLAYER_SPEED = 8;
	public static final int DEFAULT_PLAYER_SIZE = 48;
	public static final int DEFAULT_PLAYER_PROJECTILE_SIZE = 20;
	//-------------------------------------------------------|
	// INTS//------------------------------------------------|
	public static int ACTUAL_PLAYER_SPEED;
	public static int ACTUAL_PLAYER_SIZE;
	public static int ACTUAL_PLAYER_PROJECTILE_SIZE;
	//-------------------------------------------------------|
	
	public static void scaleData(double scaling_factor){
		ACTUAL_PLAYER_SPEED =(int) (DEFAULT_PLAYER_SPEED * scaling_factor);
		ACTUAL_PLAYER_SIZE =(int) (DEFAULT_PLAYER_SIZE * scaling_factor);
		ACTUAL_PLAYER_PROJECTILE_SIZE =(int) (DEFAULT_PLAYER_PROJECTILE_SIZE * scaling_factor);
		
		System.out.println("scale : " + scaling_factor + "        " + "speed : " + ACTUAL_PLAYER_SPEED + "         " + "size : "  + ACTUAL_PLAYER_SIZE);
	}

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
}
