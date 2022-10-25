package world.entity.player;

import app.Resources;
import events.Event;
import events.world.EntityAnimationChangeEvent;
import world.World;
import world.entity.Animator;
import world.entity.Entity;

public class PlayerBase extends Entity {
	
	public static final int IDLE = 0, WALK = 1, JUMP = 2;
	
	public int lastDir;

	public PlayerBase(float x, float y, World world, String key) {
		super(x, y, 32, 64, world);
		lastDir = 0;
		
		setAnimator(new Animator());
		int[] lengths = {4, 8, 1};
		for(int i = 0; i < 6; i++)
			m_Animator.addAnimation(Resources.getSpriteSheet(key).getImages(i, lengths[i / 2]));
		m_Animator.setPeriod(6);
	}
	
	public boolean onEvent(Event event)
	{
		if(event instanceof EntityAnimationChangeEvent)
		{
			EntityAnimationChangeEvent e = (EntityAnimationChangeEvent) event;
			if(e.getEntity() == this) {
				m_Animator.setAnimation(e.getID());
			}
		}
		return super.onEvent(event);
	}
}
