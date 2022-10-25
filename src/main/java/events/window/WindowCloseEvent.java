package events.window;

import events.WindowEvent;

public class WindowCloseEvent extends WindowEvent {

	public WindowCloseEvent(int Width, int Height) {
		super(Width, Height);
	}

	public WindowCloseEvent(int Width, int Height, String title) {
		super(Width, Height, title);
	}

}
