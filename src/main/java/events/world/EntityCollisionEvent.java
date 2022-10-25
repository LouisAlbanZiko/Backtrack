package events.world;

import world.entity.Entity;

public class EntityCollisionEvent extends EntityEvent {
	
	protected boolean x, y;

	public EntityCollisionEvent(Entity entity, boolean x, boolean y) {
		super(entity);
		this.x = x;
		this.y = y;
	}
	
	public boolean isX()
	{
		return x;
	}
	
	public boolean isY()
	{
		return y;
	}

}
