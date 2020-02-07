package world.tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import math.Vector2f;
import world.entity.Entity;

public class Tile {
	
	public int x, y;
	public Image image;
	
	public Tile(int x, int y, Image image)
	{
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public void render(GraphicsContext g, Vector2f offset, float scale)
	{
		g.drawImage(image, x * scale + offset.x, y * scale + offset.y, scale, scale);
	}
	
	public boolean isSolid()
	{
		return image != null;
	}
	
	public boolean hasCollided(Entity entity, Vector2f offset, float scale)
	{
		float left = x * scale;
		float right = (x + 1) * scale;
		float top = y * scale;
		float bot = (y + 1) * scale;
		if(left > entity.getX() + entity.getWidth() + offset.x || entity.getX() + offset.x > right)
			return false;
		if(top > entity.getY() + entity.getHeight() + offset.y || entity.getY() + offset.y > bot)
			return false;
		return true;
			
	}

}
