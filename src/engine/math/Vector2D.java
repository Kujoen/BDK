package engine.math;

public class Vector2D {
	private double x;
	private double y;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void vecAdd(Vector2D inputVector) {
		x += inputVector.getX();
		y += inputVector.getY();
	}
	
	public void vecScale(double scalingFactor){
		x *= scalingFactor;
		y *= scalingFactor;
	}
	
	public Vector2D getThisScaled(double scalingFactor){
		Vector2D resultVector = new Vector2D(x * scalingFactor, y *scalingFactor);
		return resultVector;
	}
	
	public void addX(double xvalue){
		x += xvalue;
	}
	
	public void addY(double yvalue){
		y += yvalue;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
