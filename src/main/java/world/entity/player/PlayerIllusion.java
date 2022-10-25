package world.entity.player;

import java.util.LinkedList;
import java.util.Queue;

import events.Event;
import events.world.EntityAnimationChangeEvent;
import events.world.EntityEvent;
import events.world.EntityMoveEvent;
import events.world.IllusionEnableEvent;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2f;
import world.World;

public class PlayerIllusion extends PlayerBase {
	
	public boolean enabled;
	public float time;
	public Queue<Float> eventTimes;
	public Queue<EntityEvent> events;

	public PlayerIllusion(float x, float y, World world) {
		super(x, y, world, "player illusion");
		time = 0;
		eventTimes = new LinkedList<Float>();
		events = new LinkedList<EntityEvent>();
		disable();
	}
	
	public void update(float delta)
	{
		time += delta;
		while(events.size() != 0 && eventTimes.peek() <= time)
		{
			eventTimes.remove();
			m_World.onEvent(events.poll());
		}
		super.update(delta);
	}
	
	public void render(GraphicsContext g, Vector2f offset)
	{
		if(enabled)
			super.render(g, offset);
	}
	
	public void enable()
	{
		enabled = true;
	}
	
	public void disable()
	{
		enabled = false;
		events.clear();
		eventTimes.clear();
		events.add(new IllusionEnableEvent(this));
		eventTimes.add(time + 120);
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		if(event instanceof EntityMoveEvent)
		{
			EntityMoveEvent e = (EntityMoveEvent) event;
			if(e.getEntity() == m_World.player) {
				EntityMoveEvent newEvent = new EntityMoveEvent(e.getMovement(), this);
				recordEvent(newEvent);
				return true;
			}
		}
		else if(event instanceof EntityAnimationChangeEvent)
		{
			EntityAnimationChangeEvent e = (EntityAnimationChangeEvent) event;
			if(e.getEntity() == m_World.player) {
				recordEvent(new EntityAnimationChangeEvent(this, e.getID()));
				return true;
			}
		}
		else if(event instanceof IllusionEnableEvent)
		{
			enable();
		}
		return super.onEvent(event);
	}
	
	public void recordEvent(EntityEvent event)
	{
		events.add(event);
		eventTimes.add(time + 120);
	}

}
