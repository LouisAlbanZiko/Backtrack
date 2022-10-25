package world.entity;

import events.world.EntityCollisionEvent;
import events.Event;
import events.world.EntityEvent;
import events.world.EntityMoveEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import math.Vector2f;
import world.World;

public abstract class Entity {
	
	public static int nrOfMovements = 10;
	public static Vector2f collisionOffset = new Vector2f(0, 0.5f);
	
	private Image m_Image;
	protected Vector2f m_Position, m_Size;
	protected Vector2f m_Speed, m_Acceleration, m_Dir;
	protected World m_World;
	protected Animator m_Animator;

	public Entity(float x, float y, float w, float h, World world) {
		m_Position = new Vector2f(x, y);
		m_Size = new Vector2f(w, h);
		
		m_Speed = new Vector2f();
		m_Acceleration = new Vector2f();
		m_Dir = new Vector2f();
		
		m_World = world;
	}
	
	public void render(GraphicsContext g, Vector2f offset)
	{
		if(m_Image != null)
			g.drawImage(m_Image, m_Position.x + offset.x, m_Position.y + offset.y, m_Size.x, m_Size.y);
	}
	
	public void update(float delta)
	{
		boolean xMoving = true, yMoving = true;
		Vector2f stepSpeed = m_Speed.div(nrOfMovements).mul(delta);
		Vector2f movement = new Vector2f();
		for(int i = 0; i < nrOfMovements && (xMoving || yMoving); i++)
		{
			if(xMoving) {
				if(!m_World.hasCollided(this, new Vector2f(movement.x + stepSpeed.x, -1.0f)))
				// if no collision x with (position.x + movement.x + stepSpeed.x, position.y + movement.y)
					movement.x += stepSpeed.x;
				else {
					xMoving = false;
					// collision event
					m_World.onEvent(new EntityCollisionEvent(this, true, false));
				}
			}
			if(yMoving) {
				if(!m_World.hasCollidedTiles(this, new Vector2f(0, movement.y + stepSpeed.y - 0.5f))
						&& (m_World.getCollidedPlaform(this, new Vector2f(0, movement.y + stepSpeed.y)) == null))
				// if no collision y with (position.x + movement.x, position.y + movement.y + stepSpeed.y)
					movement.y += stepSpeed.y;
				else {
					yMoving = false;
					// collision event
					m_World.onEvent(new EntityCollisionEvent(this, false, true));
				}
			}
		}
		// send move event to world
		if(!(movement.x == 0 && movement.y == 0)) {
			EntityMoveEvent event = new EntityMoveEvent(movement, this);
			m_World.onEvent(event);
		}
		
		// speed change
		m_Speed = m_Speed.add(m_Acceleration.mul(delta));
		
		if(m_Animator != null) {
			m_Animator.update(delta);
			setImage(m_Animator.getCurrentFrame());
		}
	}
	
	public void backtrack(Vector2f movement)
	{
		m_Position = m_Position.add(movement);
	}
	
	public boolean onEvent(Event event)
	{
		if(event instanceof EntityEvent)
		{
			EntityEvent entityEvent = (EntityEvent) event;
			if(entityEvent.getEntity() == this)
			{
				if(entityEvent instanceof EntityMoveEvent)
				{
					EntityMoveEvent entityMoveEvent = (EntityMoveEvent) entityEvent;
					m_Position = m_Position.add(entityMoveEvent.getMovement());
				}
			}
		}
		return false;
	}
	
	public void setImage(Image image)
	{
		this.m_Image = image;
	}
	
	public void setPosition(float x, float y)
	{
		m_Position.x = x;
		m_Position.y = y;
	}
	
	public void setSize(float w, float h)
	{
		m_Size.x = w;
		m_Size.y = h;
	}
	
	public Vector2f getPosition()
	{
		return m_Position;
	}
	
	public Vector2f getSize()
	{
		return m_Size;
	}

	public float getX()
	{
		return m_Position.x;
	}
	
	public float getY()
	{
		return m_Position.y;
	}
	
	public float getWidth()
	{
		return m_Size.x;
	}
	
	public float getHeight()
	{
		return m_Size.y;
	}
	
	public Vector2f getSpeed()
	{
		return m_Speed;
	}
	
	public boolean contains(Vector2f position)
	{
		return getX() < position.x && position.x < getX() + getWidth() && getY() < position.y && position.y < getY() + getHeight();
	}
	
	public boolean intersects(Entity entity)
	{
		if(getX() > entity.getX() + entity.getWidth() || entity.getX() > getX() + getWidth())
			return false;
		if(getY() > entity.getY() + entity.getHeight() || entity.getY() > getY() + getHeight())
			return false;
		return true;
	}
	
	public void setAnimator(Animator animator)
	{
		m_Animator = animator;
	}
	
}
