package world.entity;

import app.Resources;
import events.Event;
import math.Vector2f;
import world.World;

public class Platform extends Entity {
	
	public static final float xSpeed = 2.0f;

	public Vector2f start, destination;
	
	public Platform(float x, float y, World world, Vector2f destination) {
		super(x, y, 64, 8, world);
		setImage(Resources.getImage("platform"));
		this.start = new Vector2f(m_Position);
		this.destination = destination;
		m_Speed.x = xSpeed;
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		if(m_Speed.x > 0) 
		{
			if(m_Position.x > destination.x)
				m_Speed.x = -xSpeed;
		}
		else if(m_Speed.x < 0)
		{
			if(m_Position.x < start.x)
				m_Speed.x = xSpeed;
		}
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		return super.onEvent(event);
	}
	
	@Override
	public void backtrack(Vector2f movement)
	{
		super.backtrack(movement);
		start.add(movement);
		destination.add(movement);
	}

}
