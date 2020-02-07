package events;

public class WindowEvent extends Event {
	
	private int m_Width, m_Height;
	private String m_Title;

	public WindowEvent(int Width, int Height) {
		m_Width = Width;
		m_Height = Height;
	}
	
	public WindowEvent(int Width, int Height, String title)
	{
		this(Width, Height);
		m_Title = title;
	}

	public int getWidth() {
		return m_Width;
	}

	public int getHeight() {
		return m_Height;
	}
	
	public String getTitle() {
		return m_Title;
	}

}
