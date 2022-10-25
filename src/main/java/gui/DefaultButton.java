package gui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DefaultButton extends Button {

	public DefaultButton(String string, Action action) {
		super(string);
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
		setOnClickEvent(action);
	}

}
