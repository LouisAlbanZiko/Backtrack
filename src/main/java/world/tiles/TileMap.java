package world.tiles;

import app.Resources;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2f;
import world.entity.Entity;

public class TileMap {
	
	protected float m_Scale;
	protected int m_Width, m_Height;
	protected Tile[] tiles;
	
	public TileMap(int width, int height, int ids[])
	{
		m_Width = width;
		m_Height = height;
		tiles = new Tile[m_Width * m_Height];
		for(int y = 0; y < m_Height; y++)
		{
			for(int x = 0; x < m_Width; x++)
			{
				tiles[x + y * m_Width] = new Tile(x, y, Resources.getTileImage(ids[x + y * m_Width]));
			}
		}
	}
	
	public boolean hasCollided(Entity entity, Vector2f offset)
	{
		for(int y = 0; y < m_Height; y++)
		{
			for(int x = 0; x < m_Width; x++)
			{
				Tile t = tiles[x + y * m_Width];
				if(t != null && t.hasCollided(entity, offset, m_Scale) && t.isSolid())
					return true;
			}
		}
		return false;
	}
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || x >= m_Width || y < 0 || y >= m_Height)
			return null;
		return tiles[x + y * m_Width];
	}
	
	public void render(GraphicsContext g, Vector2f offset)
	{
		for(Tile t : tiles)
			t.render(g, offset, m_Scale);
	}
	
	public void setScale(float scale)
	{
		m_Scale = scale;
	}
	
	public int getWidth()
	{
		return m_Width;
	}
	
	public int getHeight()
	{
		return m_Height;
	}
	
	public float getScale()
	{
		return m_Scale;
	}

}
