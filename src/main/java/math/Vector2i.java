package math;

public class Vector2i {
	
	public int x, y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i()
	{
		this(0, 0);
	}
	
	public Vector2i add(Vector2i other)
	{
		return new Vector2i(x + other.x, y + other.y);
	}
	
	public Vector2i sub(Vector2i other)
	{
		return new Vector2i(x - other.x, y - other.y);
	}
	
	public Vector2i mul(int mul)
	{
		return new Vector2i(x * mul, y * mul);
	}
	
	public Vector2i div(int div)
	{
		return new Vector2i(x / div, y / div);
	}
	
	public Vector2f toVector2f()
	{
		return new Vector2f(x, y);
	}

}
