package events;

import javafx.scene.input.KeyCode;

public class KeyEvent extends Event {

	private int m_RepeatCount;
	private KeyCode m_KeyCode;
	
	public KeyEvent(KeyCode keyCode, int repeatCount) {
		m_KeyCode = keyCode;
		m_RepeatCount = repeatCount;
	}

	public KeyCode getKeyCode() {
		return m_KeyCode;
	}

	public int getRepeatCount() {
		return m_RepeatCount;
	}
	
	public boolean isKeyPressed()
	{
		return m_RepeatCount != 0;
	}
	
	public boolean isKeyReleased()
	{
		return m_RepeatCount == 0;
	}

}
