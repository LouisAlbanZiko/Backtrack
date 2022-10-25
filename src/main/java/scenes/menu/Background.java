package scenes.menu;

import app.App;
import app.Layer;
import app.Scene;
import events.Event;
import events.SceneChangeEvent;
import events.window.WindowResizeEvent;
import gui.Action;
import gui.Button;
import gui.DefaultButton;
import gui.TextView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Background extends Layer {
	
	private Color m_Color;
	private Button start, howToPlay, scores;
	private TextView text;

	public Background(Color color, Scene scene) {
		super(scene);
		m_Color = color;
		
		start = new DefaultButton("START", new Action() {
			@Override
			public void actionPerformed(Event event) {
				App.app.setScene(App.game);
			}
		});
		start.setFont(Font.font("Verdana", 24));
		
		
		howToPlay = new DefaultButton("HOW TO PLAY", new Action() {
			@Override
			public void actionPerformed(Event event) {
				App.app.setScene(App.howToPlay);
			}
		});
		howToPlay.setFont(Font.font("Verdana", 24));
		
		scores = new DefaultButton("SCORES", new Action() {
			@Override
			public void actionPerformed(Event event) {
				App.app.setScene(App.scores);
			}
		});
		
		text = new TextView("Backtrack");
		text.setFont(Font.loadFont("file:res/fonts/Pacifico.ttf", 72));
		
	}

	@Override
	public void render() {
		GraphicsContext g = super.getGraphicsContext2D();
		g.setFill(m_Color);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		start.render(g);
		howToPlay.render(g);
		text.render(g);
		scores.render(g);
	}

	@Override
	public void update(float delta) {

		start.setPosition((float)(getWidth() - start.getWidth()) / 2, (float)(getHeight() - start.getHeight()) / 2 - 60);
		howToPlay.setPosition((float)(getWidth() - howToPlay.getWidth()) / 2, start.getY() + start.getHeight());
		scores.setPosition((float)(getWidth() - scores.getWidth()) / 2, howToPlay.getY() + howToPlay.getHeight());
		text.setPosition((float)(getWidth() - text.getWidth()) / 2, (float)(getHeight() - text.getHeight()) / 2 - 200);
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		start.onEvent(event);
		howToPlay.onEvent(event);
		scores.onEvent(event);
		text.onEvent(event);
		return super.onEvent(event);
	}
}
