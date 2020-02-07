package events.mouse;

import events.MouseEvent;
import javafx.scene.input.MouseButton;

public class MouseReleasedEvent extends MouseEvent {

	public MouseReleasedEvent(int x, int y) {
		super(x, y);
	}

	public MouseReleasedEvent(int x, int y, MouseButton button) {
		super(x, y, button);
	}

}
