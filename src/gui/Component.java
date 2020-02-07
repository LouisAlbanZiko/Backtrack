package gui;

import events.Event;
import events.mouse.MouseMovedEvent;
import events.mouse.MousePressedEvent;
import events.mouse.MouseReleasedEvent;
import javafx.scene.canvas.GraphicsContext;
import math.Rectangle;
import math.Vector2f;

public abstract class Component extends Rectangle {
	
	public static final int NONE = 0, HOVER = 1, PRESSED = 2; 
	
	protected int state;
	
	public Component()
	{
		super(0, 0);
	}
	
	public Component(float width, float height)
	{
		super(width, height);
	}
	
	public void setPosition(float x, float y)
	{
		super.m_Max = new Vector2f(x + m_Max.x - m_Min.x, y + m_Max.y - m_Min.y);
		super.m_Min = new Vector2f(x, y);
	}
	
	public void setSize(float width, float height)
	{
		super.m_Max = m_Min.add(new Vector2f(width, height));
	}
	
	public void setSize(Vector2f size)
	{
		super.m_Max = size.add(m_Min);
	}
	
	public void setBounds(float x, float y, float width, float height)
	{
		super.m_Min = new Vector2f(x, y);
		super.m_Max = new Vector2f(x + width, y + height);
	}
	
	public void setBounds(Vector2f topLeft, Vector2f size)
	{
		super.m_Min = topLeft;
		super.m_Max = topLeft.add(size);
	}
	
	public abstract void onHover();
	public abstract void onPressed();
	public abstract void onReleased();
	
	public abstract void refresh();
	
	public abstract void render(GraphicsContext g);
	
	public boolean contains(float x, float y)
	{
		return contains(new Vector2f(x, y));
	}
	
	public boolean onEvent(Event event)
	{
		if(event instanceof MouseMovedEvent)
		{
			MouseMovedEvent mouseMovedEvent = (MouseMovedEvent) event;
			if(contains(mouseMovedEvent.getX(), mouseMovedEvent.getY())) {
				if(state != HOVER) {
					onHover();
					state = HOVER;
				}
			}
			else
				state = NONE;
			return true;
		}
		else if(event instanceof MousePressedEvent)
		{
			MousePressedEvent mousePressedEvent = (MousePressedEvent) event;
			if(contains(mousePressedEvent.getX(), mousePressedEvent.getY())) {
				if(state != PRESSED) {
					onPressed();
					state = PRESSED;
				}
			}
			else
				state = NONE;
			return true;
		}
		else if(event instanceof MouseReleasedEvent)
		{
			MouseReleasedEvent mouseReleasedEvent = (MouseReleasedEvent) event;
			if(contains(mouseReleasedEvent.getX(), mouseReleasedEvent.getY())) {
				state = HOVER;
				onReleased();
			}
			else
				state = NONE;
			return true;
		}
		return false;
	}
	
	public int getState()
	{
		return state;
	}

}
