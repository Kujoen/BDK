package objects.data;

public class SpriteData {
	// FINALS//----------------------------------------------|
	private static final int DEFAULT_PLAYER_SPEED = 8;
	private static final int DEFAULT_PLAYER_SPRITE_SIZE = 32;
	private static final int DEFAULT_PLAYER_PROJECTILE_SPRITE_SIZE = 8;
	private static final int DEFAULT_PLAYER_PROJECTILE_SPEED = -16;
	private static final int DEFAULT_BACKGROUND_SCROLLING_SPEED = 2;
	private static final int DEFAULT_ENERGYORB_SPRITE_SIZE = 256;
	private static final int DEFAULT_ENERGYORB_HITBOX_SIZE = 64;
	private static final int DEFAULT_ENERGYORB_PROJECTILE_SPRITE_SIZE = 16;
	//-------------------------------------------------------|
	// INTS//------------------------------------------------|
	private static int actual_player_speed;
	private static int actual_player_sprite_size;
	private static int actual_player_projectile_sprite_size;
	private static int actual_player_projectile_speed;
	private static int actual_background_scrolling_speed;
	private static int actual_energyorb_sprite_size;
	private static int actual_energyorb_hitbox_size;
	private static int actual_energyorb_projectile_sprite_size;
	//-------------------------------------------------------|
	
	public static void scaleData(double scaling_factor){
		actual_player_speed =(int) (DEFAULT_PLAYER_SPEED * scaling_factor);
		actual_player_sprite_size =(int) (DEFAULT_PLAYER_SPRITE_SIZE * scaling_factor);
		actual_player_projectile_sprite_size =(int) (DEFAULT_PLAYER_PROJECTILE_SPRITE_SIZE * scaling_factor);
		actual_player_projectile_speed =(int) (DEFAULT_PLAYER_PROJECTILE_SPEED * scaling_factor);
		actual_background_scrolling_speed = (int) (DEFAULT_BACKGROUND_SCROLLING_SPEED * scaling_factor);
		actual_energyorb_sprite_size = (int) (DEFAULT_ENERGYORB_SPRITE_SIZE * scaling_factor);
		actual_energyorb_hitbox_size = (int) (DEFAULT_ENERGYORB_HITBOX_SIZE * scaling_factor);
		actual_energyorb_projectile_sprite_size = (int)(DEFAULT_ENERGYORB_PROJECTILE_SPRITE_SIZE * scaling_factor);
	}

	// -----------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// -----------------------------------------------------------------------------|
	public static int getActual_player_speed() {
		return actual_player_speed;
	}

	public static void setActual_player_speed(int actual_player_speed) {
		SpriteData.actual_player_speed = actual_player_speed;
	}

	public static int getActual_player_sprite_size() {
		return actual_player_sprite_size;
	}

	public static void setActual_player_sprite_size(int actual_player_sprite_size) {
		SpriteData.actual_player_sprite_size = actual_player_sprite_size;
	}

	public static int getActual_player_projectile_sprite_size() {
		return actual_player_projectile_sprite_size;
	}

	public static void setActual_player_projectile_sprite_size(int actual_player_projectile_sprite_size) {
		SpriteData.actual_player_projectile_sprite_size = actual_player_projectile_sprite_size;
	}

	public static int getActual_player_projectile_speed() {
		return actual_player_projectile_speed;
	}

	public static void setActual_player_projectile_speed(int actual_player_projectile_speed) {
		SpriteData.actual_player_projectile_speed = actual_player_projectile_speed;
	}

	public static int getActual_background_scrolling_speed() {
		return actual_background_scrolling_speed;
	}

	public static void setActual_background_scrolling_speed(int actual_background_scrolling_speed) {
		SpriteData.actual_background_scrolling_speed = actual_background_scrolling_speed;
	}

	public static int getActual_energyorb_sprite_size() {
		return actual_energyorb_sprite_size;
	}

	public static void setActual_energyorb_sprite_size(int actual_energyorb_sprite_size) {
		SpriteData.actual_energyorb_sprite_size = actual_energyorb_sprite_size;
	}

	public static int getActual_energyorb_projectile_sprite_size() {
		return actual_energyorb_projectile_sprite_size;
	}

	public static void setActual_energyorb_projectile_sprite_size(int actual_energyorb_projectile_sprite_size) {
		SpriteData.actual_energyorb_projectile_sprite_size = actual_energyorb_projectile_sprite_size;
	}

	public static int getActual_energyorb_hitbox_size() {
		return actual_energyorb_hitbox_size;
	}

	public static void setActual_energyorb_hitbox_size(int actual_energyorb_hitbox_size) {
		SpriteData.actual_energyorb_hitbox_size = actual_energyorb_hitbox_size;
	}
	
	// -----------------------------------------------------------------------------|
}
