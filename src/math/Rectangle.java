package math;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape {
	
	protected Vector2f m_Min, m_Max;

	public Rectangle(float width, float height) {
		m_Min = new Vector2f();
		m_Max = new Vector2f(width, height);
	}
	
	public Rectangle(float x, float y, float width, float height) {
		m_Min = new Vector2f(x, y);
		m_Max = new Vector2f(x + width, y + height);
	}
	
	public float getLeft()
	{
		return m_Min.x;
	}
	
	public float getRight()
	{
		return m_Max.x;
	}
	
	public float getTop()
	{
		return m_Min.y;
	}
	
	public float getBot()
	{
		return m_Max.y;
	}
	
	public boolean intersects(Rectangle rect)
	{
		return getLeft() < rect.getRight() && getRight() > rect.getLeft() 
				&& getTop() > rect.getBot() && getBot() < rect.getTop();
	}
	
	public boolean intersects(Rectangle rect, Vector2f offset)
	{
		return getLeft() < rect.getRight() + offset.x && getRight() > rect.getLeft() + offset.x 
				&& getTop() > rect.getBot() + offset.y && getBot() < rect.getTop() + offset.y;
	}

	@Override
	public void draw(GraphicsContext g) {
		g.strokeRect(getLeft(), getTop(), getWidth(), getHeight());
	}

	@Override
	public void fill(GraphicsContext g) {
		g.fillRect(getLeft(), getTop(), getWidth(), getHeight());
	}
	
	@Override
	public boolean intersects(Shape shape) {
		if(shape instanceof Rectangle)
			return intersects((Rectangle) shape);
		return false;
	}

	@Override
	public void move(Vector2f movement) {
		m_Min = m_Min.add(movement);
		m_Max = m_Max.add(movement);
	}

	@Override
	public float getX() {
		return m_Min.x + (m_Max.x - m_Min.x) / 2;
	}

	@Override
	public float getY() {
		return m_Min.y + (m_Max.y - m_Min.y) / 2;
	}
	
	public float getWidth()
	{
		return m_Max.x - m_Min.x;
	}
	
	public float getHeight()
	{
		return m_Max.y - m_Min.y;
	}

	@Override
	public boolean contains(Vector2f point) {
		return m_Min.x < point.x && point.x < m_Max.x &&
				m_Min.y < point.y && point.y < m_Max.y;
	}

}
