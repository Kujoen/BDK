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
	
	public Vector2D addX(double xvalue){
		return new Vector2D(x + xvalue , y);
	}
	
	public Vector2D addY(double yvalue){
		return new Vector2D(x, y + yvalue);
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
