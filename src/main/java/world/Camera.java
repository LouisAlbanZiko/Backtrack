package world;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import math.Vector2f;
import world.entity.Entity;

public class Camera {
	
	protected Vector2f m_Position, m_Offset;
	protected Entity center;
	
	protected float m_lvlWidth, m_lvlHeight;
	protected FloatProperty m_Width, m_Height;
	
	public Camera(World world)
	{
		m_Position = new Vector2f();
		m_Offset = new Vector2f();
		m_Width = new SimpleFloatProperty();
		m_Height = new SimpleFloatProperty();
		m_Width.bind(world.widthProperty());
		m_Height.bind(world.heightProperty());
		m_lvlWidth = world.tiles.getWidth() * world.tiles.getScale();
		m_lvlHeight = world.tiles.getHeight() * world.tiles.getScale();
	}
	
	public void update()
	{
		if(center != null) {
			Vector2f size = new Vector2f(m_Width.floatValue(), m_Height.floatValue());
			setOffset(center.getPosition().inverse().add(size.sub(center.getSize()).div(2)));
		}
	}
	
	public void setOffset(Vector2f offset)
	{
		m_Position.x = offset.x;
		m_Position.y = offset.y;
		refresh();
	}
	
	public void setOffsetRelativeTo(Entity entity)
	{
		center = entity;
	}
	
	public Vector2f getOffset()
	{
		return m_Position.add(m_Offset);
	}
	
	public void refresh()
	{
		float width = (m_Width.floatValue() > m_lvlWidth) ? m_Width.floatValue() : m_lvlWidth;
		if(m_Position.x > 0)
			m_Offset.x = -m_Position.x;
		else if(m_Position.x - m_Width.floatValue() + width < 0)
			m_Offset.x = -m_Position.x - width + m_Width.floatValue();
		else m_Offset.x = 0;
		
		float height = (m_Height.floatValue() > m_lvlHeight) ? m_Height.floatValue() : m_lvlHeight;
		if(m_Position.y > 0)
			m_Offset.y = -m_Position.y;
		else if(m_Position.y - m_Height.floatValue() + height < 0)
			m_Offset.y = -m_Position.y - height + m_Height.floatValue();
		else m_Offset.y = 0;
	}
	

}
