package math;

public class Vector2f {
	
	public float x, y;
	
	public Vector2f(Vector2f other)
	{
		this(other.x, other.y);
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2f()
	{
		this(0, 0);
	}
	
	public Vector2f add(Vector2f other)
	{
		return new Vector2f(x + other.x, y + other.y);
	}
	
	public Vector2f sub(Vector2f other)
	{
		return new Vector2f(x - other.x, y - other.y);
	}
	
	public Vector2f mul(float mul)
	{
		return new Vector2f(x * mul, y * mul);
	}
	
	public Vector2f div(float div)
	{
		return new Vector2f(x / div, y / div);
	}
	
	public Vector2f inverse()
	{
		return new Vector2f(-x, -y);
	}
	
	public float mul(Vector2f other)
	{
		return x * other.x + y * other.y;
	}
	
	public Vector2i toVector2i()
	{
		return new Vector2i(getX(), getY());
	}
	
	public int getX()
	{
		return (int) x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public float getMag()
	{
		return (float) Math.sqrt(x * x + y * y);
	}

}
