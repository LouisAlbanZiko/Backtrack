package events.key;

import events.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyPressedEvent extends KeyEvent {

	public KeyPressedEvent(KeyCode keyCode) {
		super(keyCode, 1);
	}

}
