package objects.gameobjects;

import engine.math.MovementFunctions;
import engine.math.Vector2D;

public class ProjectileController {

	// INT--------------------------------------------------|
	private int movementType;
	// -----------------------------------------------------|
	// FINALS-----------------------------------------------|
	private final int DEFAULT_MOVEMENT_TYPE = 0;
	// -----------------------------------------------------|
	// MATHOBJECTS------------------------------------------|
	private MovementFunctions mFunction;
	private Vector2D movementDirection;
	// -----------------------------------------------------|
	// GAMEOBJECTS------------------------------------------|
	private Projectile projectile;
	// -----------------------------------------------------|

	public ProjectileController(int movementType, Projectile projectile) {
		this.movementType = movementType;
		this.projectile = projectile;
		this.mFunction = new MovementFunctions(projectile);
	}
	
	public ProjectileController(int movementType, Projectile projectile, Vector2D movementVector){
		this.movementType = movementType;
		this.projectile = projectile;
		this.mFunction = new MovementFunctions(projectile);
		this.movementDirection = movementVector;
	}

	/**
	 * moves projectile based on the chosen movement type of the
	 * projectile
	 * 
	 * @return
	 */
	public void moveProjectile() {
		switch (movementType) {
		case Projectile.MOVEMENT_PLAYER_PROJECTILE:
			projectile.setPosition(new Vector2D(projectile.getPosition().getX(),
					projectile.getPosition().getY() + projectile.SPEED_PLAYER_PROJECTILE));
			break;
		case Projectile.MOVEMENT_SPIRAL:
			mFunction.calcArchimedeanSpiral();
			break;
		case Projectile.MOVEMENT_VECTOR:
			mFunction.calcMovementVector(movementDirection);
			break;
		}
	}

}
