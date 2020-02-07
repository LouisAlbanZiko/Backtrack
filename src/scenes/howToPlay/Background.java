package scenes.howToPlay;

import app.App;
import app.Layer;
import app.Resources;
import app.Scene;
import events.Event;
import gui.Action;
import gui.Button;
import gui.DefaultButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Background extends Layer {
	
	public Image movement, backtrack, space;
	public Button back;

	public Background(Scene scene) {
		super(scene);
		movement = Resources.getImage("movement");
		backtrack = Resources.getImage("backtrack");
		space = Resources.getImage("space");
		
		back = new DefaultButton("Back", new Action() {
			@Override
			public void actionPerformed(Event event) {
				App.app.setScene(App.menu);
			}
		});
		back.setPosition(10, 10);
	}

	@Override
	public void render() {
		GraphicsContext g = getGraphicsContext2D();
		
		g.setFill(Color.BLUE.desaturate().desaturate());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setFill(Color.BLACK);
		g.setFont(Font.getDefault());
		
		double x = (getWidth() - movement.getWidth()) / 2 - 200, y = getHeight() / 2;
		g.drawImage(movement, x, y, movement.getWidth() / 2, movement.getHeight() / 2);
		g.fillText("Movement", x + 16, y + 100);
		
		x = (getWidth() - backtrack.getWidth()) / 2 - 50; y = getHeight() / 2;
		g.drawImage(backtrack, x, y, backtrack.getWidth() / 2, backtrack.getHeight() / 2);
		g.fillText("Backtrack", x - 16, y + 64);
		
		x = (getWidth() - space.getWidth()) / 2 + 200; y = getHeight() / 2;
		g.drawImage(space, x, y, space.getWidth() / 2, space.getHeight() / 2);
		g.fillText("Exit Door", x + 32, y + 64);
		
		String[] explanation = {
								"The goal of the game is to pickup the key and reach the exit within the time limit.",
								"Hint1: The blue outline that follows you is your position 2 seconds before.",
								"You can jump to it (backtrack) at any time by pressing Q.",
								"Hint2: You can take other objects with you when backtracking.",
								"Hint3: Your momentum is kept when backtracking."
								};
		x = getWidth() / 2 - 300; y = getHeight() / 2 - 200;
		for(int i = 0; i < explanation.length; i++)
			g.fillText(explanation[i], x, y + i * 20);
		
		back.render(g);
	}

	@Override
	public void update(float delta) {
		
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		back.onEvent(event);
		return super.onEvent(event);
	}

}
