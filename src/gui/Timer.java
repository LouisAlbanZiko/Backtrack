package gui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Timer extends TextView {
	
	public float m_Time;

	public Timer(float time) {
		super("");
		setTime(time * 60);
		setTextColor(Color.color(0.9, 0.1, 0.15));
		setFont(Font.font("Arial", 36));
		setPosition(10, 10);
		
		setPadding(10);
		setPaddingTop(5);
		
		setRenderBorder(true);
		setBorder(4);
		setBorderColor(Color.BLUE.saturate());
	}
	
	public void update(float delta)
	{
		setTime(m_Time - delta);
	}
	
	public void setTime(float time)
	{
		m_Time = time;
		time /= 60;
		int timeInt = ((int) time);
		int mantis = ((int)((time - timeInt) * 100));
		String text = "" + (timeInt > 9 ? timeInt : "0" + timeInt) + ":" + (mantis > 9 ? mantis : "0" + mantis);
		setText(text);
	}
	
	public float getTime()
	{
		return m_Time;
	}
	
	public boolean isDone()
	{
		return m_Time <= 0;
	}
	
	public void sub(float value)
	{
		setTime(m_Time + value * 60);
	}

}
