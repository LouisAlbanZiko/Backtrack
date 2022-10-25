package events.window;

import events.WindowEvent;

public class WindowCreateEvent extends WindowEvent {

	public WindowCreateEvent(int Width, int Height, String title) {
		super(Width, Height, title);
	}

}
