package world.entity;

import java.awt.event.KeyEvent;

import events.Event;
import events.key.KeyPressedEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import math.Vector2f;
import world.entity.player.Player;
import world.entity.player.PlayerIllusion;

public class Backtrack {
	
	public static final float RADIUS = 128;
	
	public Player m_Player;
	public PlayerIllusion m_Illusion;
	public Color radiusColor;
	public float counter;
	
	public float r, g, b, rChange = 0.001f, gChange = 0.002f, bChange = 0.005f;

	public Backtrack(Player player, PlayerIllusion illusion) {
		m_Player = player;
		m_Illusion = illusion;
		r = (float) Math.random();
		g = (float) Math.random();
		b = (float) Math.random();
		counter = 0;
	}
	
	public void render(GraphicsContext g, Vector2f offset)
	{
		if(this.r >= 1 - 4 * Math.abs(rChange) || this.r <= 4 * Math.abs(rChange))
			rChange = -rChange;
		if(this.g >= 1 - 4 * Math.abs(gChange) || this.g <= 4 * Math.abs(gChange))
			gChange = -gChange;
		if(this.b >= 1 - 4 * Math.abs(bChange) || this.b <= 4 * Math.abs(bChange))
			bChange = -bChange;
		
		this.r += rChange;
		this.g += gChange;
		this.b += bChange;
		
		if(this.r > 1)
			this.r = 1;
		else if(this.r < 0)
			this.r = 0;
		if(this.g > 1)
			this.g = 1;
		else if(this.g < 0)
			this.g = 0;
		if(this.b > 1)
			this.b = 1;
		else if(this.b < 0)
			this.b = 0;
		
		radiusColor = Color.color(this.r, this.g, this.b, 0.5f);
		g.setStroke(radiusColor);
		g.strokeOval(m_Player.getX() + m_Player.getWidth() / 2 - RADIUS + offset.x, m_Player.getY() + m_Player.getHeight() / 2 - RADIUS + offset.y, RADIUS * 2, RADIUS * 2);
	}
	
	public boolean onEvent(Event event)
	{
		if(event instanceof KeyPressedEvent)
		{
			KeyPressedEvent e = (KeyPressedEvent) event;
			if(e.getKeyCode() == KeyCode.Q && m_Illusion.enabled)
			{
				for(Entity entity : m_Player.m_World.entities)
				{
					if((entity instanceof Platform || entity instanceof Key)
							&& entity.getPosition().sub(m_Player.getPosition()).getMag() <= RADIUS) {
						entity.backtrack(m_Illusion.getPosition().sub(m_Player.getPosition()));
					}
					counter++;
				}
				m_Player.m_Position = new Vector2f(m_Illusion.m_Position);
				m_Illusion.disable();
				return true;
			}
		}
		return false;
	}

}
