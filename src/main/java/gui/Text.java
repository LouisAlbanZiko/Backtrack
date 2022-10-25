package gui;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import math.Vector2f;

public class Text {
	
	protected Font font;
	protected String text;
	protected Vector2f size;
	
	
	public Text(String text)
	{
		size = new Vector2f();
		setText(text);
	}
	
	public void render(float x, float y, GraphicsContext g)
	{
		g.setFont(font);
		g.fillText(text, x, y);
	}
	
	public void setText(String string)
	{
		this.text = string;
		refresh();
	}
	
	public void setFont(Font font)
	{
		this.font = font;
		refresh();
	}
	
	public void refresh()
	{
		javafx.scene.text.Text text = new javafx.scene.text.Text(this.text);
		text.setFont(font);
		Bounds bounds = text.getLayoutBounds();
		size.x = (float) bounds.getWidth();
		size.y = (float) bounds.getHeight();
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public String getText()
	{
		return text;
	}
	
	public Vector2f getSize()
	{
		return size;
	}
	
	public float getWidth()
	{
		return size.x;
	}
	
	public float getHeight()
	{
		return size.y;
	}

}
