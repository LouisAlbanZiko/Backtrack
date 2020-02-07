package events.key;

import events.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyReleasedEvent extends KeyEvent {

	public KeyReleasedEvent(KeyCode keyCode) {
		super(keyCode, 0);
	}

}
