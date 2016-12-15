package engine.math;

import objects.gameobjects.Projectile;

public class MovementFunctions {
	
	// MATHOBJECTS---------------------------------------------|
	private Vector2D originalVector;
	private Vector2D currentPosition;
	// --------------------------------------------------------|
	// GAMEOBJECTS---------------------------------------------|
	private Projectile projectile;
	// --------------------------------------------------------|
	// DOUBLES-------------------------------------------------|
	private double ticks;
	private double spiralTightness;
	private double radiusPerSecond;
	private double tickModifier;
	private double tickCounter;
	// --------------------------------------------------------|
	
	public MovementFunctions(Projectile projectile){
		this.projectile = projectile;
		this.originalVector = projectile.getPosition();
		this.radiusPerSecond = 2.0;
		this.spiralTightness = 0.75;
		this.ticks = 0.0;
		this.tickModifier = 0.04 ;
	}

	public void calcArchimedeanSpiral() {
		
		Vector2D functionVector = new Vector2D(0,0);
		
		double angle = Math.PI * spiralTightness * ticks;
		
		functionVector.setX(Math.cos(angle) * ticks * radiusPerSecond);
		functionVector.setY(Math.sin(angle) * ticks * radiusPerSecond);
		
		projectile.getPosition().vecAdd(functionVector);
		
		ticks += tickModifier;
		tickCounter++;
		
		if(tickCounter == 200){
			tickModifier = 0.005;
			radiusPerSecond = 0.25;
		}
		
		/*double y = -((currentPosition.getX() - originalVector.getX()) / (currentPosition.getY() - originalVector.getY())) * 1.1;	
		Vector2D result = new Vector2D(1,y);
		result.vecScale(-10 / Math.sqrt(y * y + 1));
		result.vecAdd(currentPosition);
		return result;*/
	}

}
