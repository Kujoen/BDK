package engine.math;

public class MovementFunctions {

	public static Vector2D calcArchimedeanSpiral(Vector2D originalVector, int i) {
		Vector2D returnVector = new Vector2D(0, 0);
		
		double angle = 0.1 * i;

		returnVector.setX(originalVector.getX() + (1 + 1 * angle) * Math.cos(angle));
		returnVector.setY(originalVector.getY() + (1 + 1 * angle) * Math.sin(angle));
		return returnVector;
	}

}
