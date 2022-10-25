package gui;

import events.Event;
import events.key.KeyPressedEvent;
import events.key.KeyRepeatedEvent;
import events.mouse.MousePressedEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextField extends Element {
	
	protected int limit;
	protected boolean selected;

	public TextField() {
		setLimit(10);
		setText("");
		selected = true;
		
		setFont(Font.font("Arial", 24));
		setBorder(2);
		setPadding(20);
		setPaddingBot(10);
		setPaddingTop(10);
		setBorderColor(Color.DARKRED.darker());
		setTextColor(Color.WHITE);
		setBackgroundColor(Color.color(0.7, 0.1, 0.2), Component.NONE);
		setBackgroundColor(Color.DARKRED.desaturate(), Component.PRESSED);
		setBackgroundColor(Color.color(0.8, 0.2, 0.25), Component.HOVER);
	}

	@Override
	public void onHover() {
		
	}

	@Override
	public void onPressed() {
		selected = true;
	}

	@Override
	public void onReleased() {
		
	}

	@Override
	public boolean onEvent(Event event)
	{
		if(event instanceof KeyPressedEvent)
		{
			KeyPressedEvent e = (KeyPressedEvent) event;
			if(e.getKeyCode() == KeyCode.BACK_SPACE)
			{
				if(selected)
					removeLastChar();
			}
			if(e.getKeyCode().isDigitKey() || e.getKeyCode().isLetterKey())
			{
				if(selected)
					append(e.getKeyCode().getChar().charAt(0));
			}
		}
		else if(event instanceof KeyRepeatedEvent)
		{
			KeyRepeatedEvent e = (KeyRepeatedEvent) event;
			if(e.getKeyCode() == KeyCode.BACK_SPACE && selected)
				removeLastChar();
		}
		else if(event instanceof MousePressedEvent)
		{
			MousePressedEvent e = (MousePressedEvent) event;
			if(contains(e.getX(), e.getY()))
				selected = true;
			//else
			//	selected = false;
		}
		return super.onEvent(event);
	}
	
	public void append(char c)
	{
		if(getText().length() < limit)
			setText(getText() + c);
	}
	
	public void removeLastChar()
	{
		if(getText().length() > 0)
			setText(getText().substring(0, getText().length() - 1));
	}
	
	public void setLimit(int limit)
	{
		if(limit >= 0) {
			this.limit = limit;
		}
	}
	
	public int getLimit()
	{
		return limit;
	}
	
}
