package events.mouse;

import events.MouseEvent;
import javafx.scene.input.MouseButton;

public class MouseMovedEvent extends MouseEvent {

	public MouseMovedEvent(int x, int y) {
		super(x, y);
	}

	public MouseMovedEvent(int x, int y, MouseButton buttonId) {
		super(x, y, buttonId);
	}

}
