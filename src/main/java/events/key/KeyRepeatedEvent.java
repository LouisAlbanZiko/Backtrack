package events.key;

import events.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyRepeatedEvent extends KeyEvent {

	public KeyRepeatedEvent(KeyCode keyCode, int repeatCount) {
		super(keyCode, repeatCount);
	}

}
