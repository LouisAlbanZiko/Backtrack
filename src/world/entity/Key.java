package world.entity;

import app.Resources;
import world.World;

public class Key extends Entity {

	public Key(float x, float y, World world) {
		super(x, y, 32, 32, world);
		setImage(Resources.getImage("key"));
	}

}
