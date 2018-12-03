package bdk.util.graphics;

/**
 * 
 * @author Andreas
 *
 */
public class Vector2D {

	private double x;
	private double y;
	
	public Vector2D() {
		
	}
	
	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector2D getSumOf(Vector2D vec1, Vector2D vec2){
		return new Vector2D(vec1.getX() + vec1.getX(), vec1.getY() + vec2.getY());
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
