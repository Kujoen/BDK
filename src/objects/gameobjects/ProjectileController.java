package objects.gameobjects;

import engine.math.MovementFunctions;
import engine.math.Vector2D;

public class ProjectileController {

	// INT--------------------------------------------------|
	private int movementType;
	private int counterFunctionCalls;
	// -----------------------------------------------------|
	// FINALS-----------------------------------------------|
	private final int DEFAULT_MOVEMENT_TYPE = 0;
	// ------------------------------------------------------|
	// MATHOBJECTS------------------------------------------|
	private Vector2D originalVector;
	// ------------------------------------------------------|
	// GAMEOBJECTS------------------------------------------|
	private Projectile projectile;
	// ------------------------------------------------------|

	public ProjectileController(int movementType, Projectile projectile) {
		this.movementType = movementType;
		this.projectile = projectile;
		originalVector = projectile.getPosition();
	}

	/**
	 * Returns a vector2D moved based on the chosen movement type of the
	 * projectile
	 * 
	 * @return
	 */
	public void moveProjectile() {
		switch (movementType) {
		case Projectile.MOVEMENT_PLAYER_PROJECTILE:
			projectile.setPosition(new Vector2D(projectile.getPosition().getX(),
					projectile.getPosition().getY() + projectile.PROJECTILE_SPEED));
			break;
		case Projectile.MOVEMENT_SPIRAL:
			projectile.setPosition(MovementFunctions.calcArchimedeanSpiral(originalVector, counterFunctionCalls));
			counterFunctionCalls++;
			break;
		}
	}

}
