package math;

import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {

	public Shape() {
		
	}
	
	// rendering
	public abstract void draw(GraphicsContext g);
	public abstract void fill(GraphicsContext g);
	
	// physics
	public abstract boolean intersects(Shape shape);
	public abstract boolean contains(Vector2f point);
	public abstract void move(Vector2f movement);
	public abstract float getX();
	public abstract float getY();
	

}
