package events.world;

import world.entity.Entity;

public class EntityEvent extends WorldEvent {

	private Entity m_Entity;
	
	public EntityEvent(Entity entity) {
		m_Entity = entity;
	}
	
	public Entity getEntity()
	{
		return m_Entity;
	}

}
