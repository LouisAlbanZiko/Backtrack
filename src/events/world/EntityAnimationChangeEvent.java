package events.world;

import world.entity.Entity;

public class EntityAnimationChangeEvent extends EntityEvent {
	
	private final int id;

	public EntityAnimationChangeEvent(Entity entity, int id) {
		super(entity);
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}

}
