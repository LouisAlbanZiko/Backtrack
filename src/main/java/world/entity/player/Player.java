package world.entity.player;

import events.Event;
import events.key.KeyPressedEvent;
import events.key.KeyReleasedEvent;
import events.world.EntityAnimationChangeEvent;
import events.world.EntityCollisionEvent;
import events.world.EntityMoveEvent;
import events.world.GravityEvent;
import events.world.KillEntityEvent;
import javafx.scene.input.KeyCode;
import math.Vector2f;
import world.World;
import world.entity.Platform;

public class Player extends PlayerBase {
	
	public static float xSpeed = 5.0f, jumpingAcceleration = 15.0f;
	
	public boolean hasKey;
	
	public Player(float x, float y, World world) {
		super(x, y, world, "player");
		hasKey = false;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		Platform p;
		if((p = m_World.getCollidedPlaform(this, new Vector2f(0, 0.5f))) != null) {
			m_World.onEvent(new EntityMoveEvent(p.getSpeed().mul(delta), this));
		}
		else if(!m_World.hasCollidedTiles(this, new Vector2f(0, 0.5f)))
		{
			setAnimation(JUMP);
		}
		if(m_World.key != null && m_World.key.intersects(this)) {
			hasKey = true; // getkey event to do
			m_World.onEvent(new KillEntityEvent(m_World.key));
			m_World.key = null;
		}
	}

	@Override
	public boolean onEvent(Event event) {
		if(event instanceof KeyPressedEvent)
		{
			KeyPressedEvent keyPressedEvent = (KeyPressedEvent) event;
			if(keyPressedEvent.getKeyCode() == KeyCode.RIGHT)
				m_Dir.x = 1;
			else if(keyPressedEvent.getKeyCode() == KeyCode.LEFT)
				m_Dir.x = -1;
			if(keyPressedEvent.getKeyCode() == KeyCode.RIGHT || keyPressedEvent.getKeyCode() == KeyCode.LEFT) {
				m_Speed.x = m_Dir.x * xSpeed;
				lastDir = m_Dir.getX() == -1 ? 1 : 0;
			}
			
			if(keyPressedEvent.getKeyCode() == KeyCode.UP && m_World.hasCollided(this, new Vector2f(0, 0.5f))) {
				m_Speed.y = -jumpingAcceleration;
				setAnimation(JUMP);
			}
			
			if(keyPressedEvent.getKeyCode() == KeyCode.SPACE)
			{
				if(hasKey && m_World.exit.intersects(this)) {
					m_World.exit.openDoor();
					m_World.nextLevel();
				}
			}
			return true;
		}
		else if(event instanceof KeyReleasedEvent)
		{
			KeyReleasedEvent keyReleasedEvent = (KeyReleasedEvent) event;
			if((keyReleasedEvent.getKeyCode() == KeyCode.RIGHT && m_Dir.x == 1) 
					|| (keyReleasedEvent.getKeyCode() == KeyCode.LEFT && m_Dir.x == -1))
				m_Dir.x = m_Speed.x = 0;
			return true;
		}
		else if(event instanceof EntityCollisionEvent)
		{
			EntityCollisionEvent e = (EntityCollisionEvent) event;
			if(e.getEntity() == this) {
				if(e.isY()) {
					if(m_Speed.y >= 0)
						setAnimation(m_Speed.getX() != 0 ? WALK : IDLE);
					m_Speed.y = m_Acceleration.y = 0;
				}
			}
		}
		else if(event instanceof GravityEvent)
		{
			GravityEvent e = (GravityEvent) event;
			if(!m_World.hasCollided(this, new Vector2f(0, -1.0f)))
				m_Acceleration.y = e.getGravity();
		}
		return super.onEvent(event);
	}
	
	public void setAnimation(int type)
	{
		if(type == WALK)
			m_Animator.setPeriod(3);
		else
			m_Animator.setPeriod(6);
		int id = type * 2 + lastDir;
		if(m_Animator.getAnimationID() != id)
			m_World.onEvent(new EntityAnimationChangeEvent(this, id));
	}

}
