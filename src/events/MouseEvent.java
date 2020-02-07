package events;

import javafx.scene.input.MouseButton;

public class MouseEvent extends Event {

	private int m_X, m_Y;
	private MouseButton m_Button;
	
	public MouseEvent(int x, int y) {
		m_X = x;
		m_Y = y;
	}
	
	public MouseEvent(int x, int y, MouseButton button)
	{
		this(x, y);
		m_Button = button;
	}

	public int getX() {
		return m_X;
	}

	public int getY() {
		return m_Y;
	}

	public MouseButton getButton() {
		return m_Button;
	}
	
	

}
