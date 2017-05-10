package objects.data;

public class SpriteData {
	// FINALS//----------------------------------------------|
	private static final int DEFAULT_PLAYER_SPEED = 8;
	private static final int DEFAULT_PLAYER_SPRITE_SIZE = 64;
	private static final int DEFAULT_PLAYER_PROJECTILE_SPRITE_SIZE = 8;
	private static final int DEFAULT_PLAYER_PROJECTILE_SPEED = -16;
	private static final int DEFAULT_BACKGROUND_SCROLLING_SPEED = 2;
	private static final int DEFAULT_ENERGYORB_SPRITE_SIZE = 256;
	private static final int DEFAULT_ENERGYORB_HITBOX_SIZE = 64;
	private static final int DEFAULT_ENERGYORB_PROJECTILE_SPRITE_SIZE = 16;

	private static final int DEFAULT_SCORE_SPRITE_HEIGTH = 20;
	private static final int DEFAULT_SCORE_SPRITE_WIDTH = 20;
	private static final int DEFAULT_SCORETEXT_SPRITE_HEIGTH = 20;
	private static final int DEFAULT_SCORETEXT_SPRITE_WIDTH = 20;
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
	
	private static int actual_score_sprite_heigth;
	private static int actual_score_sprite_width;
	private static int actual_scoretext_sprite_heigth;
	private static int actual_scoretext_sprite_width;
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
		actual_score_sprite_heigth = (int)(DEFAULT_SCORE_SPRITE_HEIGTH * scaling_factor);
		actual_score_sprite_width = (int)(DEFAULT_SCORE_SPRITE_WIDTH * scaling_factor);
		actual_scoretext_sprite_heigth = (int)(DEFAULT_SCORETEXT_SPRITE_HEIGTH * scaling_factor);
		actual_scoretext_sprite_width = (int)(DEFAULT_SCORETEXT_SPRITE_WIDTH * scaling_factor);

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

	/**
	 * @return the actual_score_sprite_heigth
	 */
	public static int getActual_score_sprite_heigth() {
		return actual_score_sprite_heigth;
	}

	/**
	 * @param actual_score_sprite_heigth the actual_score_sprite_heigth to set
	 */
	public static void setActual_score_sprite_heigth(int actual_score_sprite_heigth) {
		SpriteData.actual_score_sprite_heigth = actual_score_sprite_heigth;
	}

	/**
	 * @return the actual_score_sprite_width
	 */
	public static int getActual_score_sprite_width() {
		return actual_score_sprite_width;
	}

	/**
	 * @param actual_score_sprite_width the actual_score_sprite_width to set
	 */
	public static void setActual_score_sprite_width(int actual_score_sprite_width) {
		SpriteData.actual_score_sprite_width = actual_score_sprite_width;
	}

	/**
	 * @return the actual_scoretext_sprite_heigth
	 */
	public static int getActual_scoretext_sprite_heigth() {
		return actual_scoretext_sprite_heigth;
	}

	/**
	 * @param actual_scoretext_sprite_heigth the actual_scoretext_sprite_heigth to set
	 */
	public static void setActual_scoretext_sprite_heigth(int actual_scoretext_sprite_heigth) {
		SpriteData.actual_scoretext_sprite_heigth = actual_scoretext_sprite_heigth;
	}

	/**
	 * @return the actual_scoretext_sprite_width
	 */
	public static int getActual_scoretext_sprite_width() {
		return actual_scoretext_sprite_width;
	}

	/**
	 * @param actual_scoretext_sprite_width the actual_scoretext_sprite_width to set
	 */
	public static void setActual_scoretext_sprite_width(int actual_scoretext_sprite_width) {
		SpriteData.actual_scoretext_sprite_width = actual_scoretext_sprite_width;
	}
	
	// -----------------------------------------------------------------------------|
}
