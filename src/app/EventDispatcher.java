package app;

import events.Event;
import events.key.KeyPressedEvent;
import events.key.KeyReleasedEvent;
import events.mouse.MouseMovedEvent;
import events.mouse.MousePressedEvent;
import events.mouse.MouseReleasedEvent;
import events.window.WindowCloseEvent;
import events.window.WindowResizeEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import math.Vector2i;

public class EventDispatcher implements EventHandler<javafx.event.Event> {
	
	public Application app;
	
	private Vector2i windowSize;

	public EventDispatcher(Application app) {
		this.app = app;
		windowSize = new Vector2i((int) app.window.getWidth(), (int) app.window.getHeight());

	    app.window.widthProperty().addListener((observable, oldValue, newValue) ->
		{
			windowSize.x = newValue.intValue();
			System.out.println("Window Resized " + windowSize.x + ", " + windowSize.y);
			app.dispatchEvent(new WindowResizeEvent(windowSize.x, windowSize.y));
		});
	    app.window.heightProperty().addListener((observable, oldValue, newValue) ->
		{
			windowSize.y = newValue.intValue();
			System.out.println("Window Resized " + windowSize.x + ", " + windowSize.y);
			app.dispatchEvent(new WindowResizeEvent(windowSize.x, windowSize.y));
		});
	}

	@Override
	public void handle(javafx.event.Event event) {
		Event newEvent = null;
		if(event instanceof javafx.scene.input.KeyEvent) 
		{
			javafx.scene.input.KeyEvent keyEvent = (javafx.scene.input.KeyEvent) event;
			KeyCode code = keyEvent.getCode();
			if(event.getEventType() == javafx.scene.input.KeyEvent.KEY_PRESSED)
			{
					newEvent = new KeyPressedEvent(code);
			}
			else if(event.getEventType() == javafx.scene.input.KeyEvent.KEY_RELEASED) {
				newEvent = new KeyReleasedEvent(code);
			}
			else if(event.getEventType() == javafx.scene.input.KeyEvent.KEY_TYPED)
				newEvent = new KeyPressedEvent(code);
		}
		else if(event instanceof javafx.scene.input.MouseEvent)
		{
			javafx.scene.input.MouseEvent mouseEvent = (javafx.scene.input.MouseEvent) event;
			int x = (int) mouseEvent.getX(), y = (int) mouseEvent.getY();
			if(mouseEvent.getEventType() == javafx.scene.input.MouseEvent.MOUSE_MOVED)
			{
				newEvent = new MouseMovedEvent(x, y);
			}
			else if(mouseEvent.getEventType() == javafx.scene.input.MouseEvent.MOUSE_PRESSED)
			{
				newEvent = new MousePressedEvent(x, y, mouseEvent.getButton());
			}
			else if(mouseEvent.getEventType() == javafx.scene.input.MouseEvent.MOUSE_RELEASED)
			{
				newEvent = new MouseReleasedEvent(x, y, mouseEvent.getButton());
			}
		}
		else if(event instanceof javafx.stage.WindowEvent)
		{
			javafx.stage.WindowEvent windowEvent = (javafx.stage.WindowEvent) event;
			if(windowEvent.getEventType() == javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST)
			{
				newEvent = new WindowCloseEvent(windowSize.x, windowSize.y);
			}
		}
		if(newEvent != null)
			app.dispatchEvent(newEvent);
	}

}
