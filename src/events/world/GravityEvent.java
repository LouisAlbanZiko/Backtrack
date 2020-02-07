package events.world;

public class GravityEvent extends WorldEvent {

	private float gravity;
	
	public GravityEvent(float gravity) {
		this.gravity = gravity;
	}
	
	public float getGravity()
	{
		return gravity;
	}

}
