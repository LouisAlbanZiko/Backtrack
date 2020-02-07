package world.entity;

import app.Resources;
import world.World;

public class DoorExit extends Entity {

	public DoorExit(float x, float y, World world) {
		super(x, y, 48, 64, world);
		setImage(Resources.getSpriteSheet("door").getImage(0));
	}
	
	public void openDoor()
	{
		setImage(Resources.getSpriteSheet("door").getImage(1));
	}

}
