package events.mouse;

import events.MouseEvent;
import javafx.scene.input.MouseButton;

public class MousePressedEvent extends MouseEvent {

	public MousePressedEvent(int x, int y) {
		super(x, y);
	}

	public MousePressedEvent(int x, int y, MouseButton button) {
		super(x, y, button);
	}

}
