package events.world;

import math.Vector2f;
import world.entity.Entity;

public class EntityMoveEvent extends EntityEvent {
	
	private Vector2f m_Movement;
	
	public EntityMoveEvent(Vector2f movement, Entity entity) {
		super(entity);
		m_Movement = movement;
	}
	
	public Vector2f getMovement()
	{
		return m_Movement;
	}

}
