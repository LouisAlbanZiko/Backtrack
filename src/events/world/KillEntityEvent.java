package events.world;

import world.entity.Entity;

public class KillEntityEvent extends EntityEvent {
	
	public KillEntityEvent(Entity e) {
		super(e);
	}

}
