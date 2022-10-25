package world.entity;

import app.Resources;
import world.World;

public class DoorEntrance extends Entity {

	public DoorEntrance(float x, float y, World world) {
		super(x, y, 48, 64, world);
		setImage(Resources.getSpriteSheet("door").getImage(1));
	}

}
