package scenes.game;

import app.App;
import app.Layer;
import app.Scene;
import events.Event;
import gui.Action;
import gui.Button;
import gui.DefaultButton;
import javafx.scene.canvas.GraphicsContext;
import scenes.Game;

public class PauseOverlay extends Layer {
	
	public Button unpause, menu;

	public PauseOverlay(Scene scene) {
		super(scene);
		unpause = new DefaultButton("Unpause", new Action() {
			@Override
			public void actionPerformed(Event event) {
				((Game) scene).unpause();
			}
		});
		menu = new DefaultButton("Menu", new Action() {
			@Override
			public void actionPerformed(Event event) {
				App.app.setScene(App.menu);
			}
		});
	}

	@Override
	public void render() {
		clear();
		GraphicsContext g = getGraphicsContext2D();
		unpause.render(g);
		menu.render(g);
	}

	@Override
	public void update(float delta) {
		unpause.setPosition((float) ((getWidth() - unpause.getWidth()) / 2), (float) ((getHeight() - unpause.getHeight()) / 2) - 70);
		menu.setPosition((float) ((getWidth() - menu.getWidth()) / 2), (float) ((getHeight() - menu.getHeight()) / 2));
	}
	
	public boolean onEvent(Event event)
	{
		unpause.onEvent(event);
		menu.onEvent(event);
		return super.onEvent(event);
	}

}
