package scenes.score;

import static world.Score.scores;

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
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainLayer extends Layer {
	
	public Button back;
	public TextView text;

	public MainLayer(Scene scene) {
		super(scene);
		back = new DefaultButton("Back", new Action() {
			@Override
			public void actionPerformed(Event event) {
				App.app.setScene(App.menu);
			}
		});
		back.setPosition(10, 10);
		text = new TextView("High Scores");
		text.setFont(Font.loadFont("file:res/fonts/Pacifico.ttf", 50));
		text.setTextColor(Color.BEIGE);
	}

	@Override
	public void render() {
		GraphicsContext g = getGraphicsContext2D();
		
		g.setFill(Color.BLUE.desaturate().desaturate());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setFill(Color.DARKBLUE.darker());
		g.setFont(Font.font("Arial", 40));
		for(int i = 0; i < scores.length; i++)
		{
			String name = scores[i].name;
			if(name.length() > 10)
				name = name.substring(0, 9);
			g.fillText(name, text.getX() - text.getWidth() / 2, text.getY() + text.getHeight() + 30 + i * 50);
			
			String score = "" + scores[i].value;
			double x = text.getX() + 300;
			javafx.scene.text.Text text = new javafx.scene.text.Text(score);
			text.setFont(Font.font("Arial", 40));
			Bounds bounds = text.getLayoutBounds();
			javafx.scene.text.Text text2 = new javafx.scene.text.Text("12345");
			text2.setFont(Font.font("Arial", 40));
			Bounds bounds2 = text2.getLayoutBounds();
			g.fillText(score, x + (bounds2.getWidth() - bounds.getWidth()), this.text.getY() + this.text.getHeight() + 30 + i * 50);
		}
		
		text.render(g);
		back.render(g);
	}

	@Override
	public void update(float delta) {
		
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		back.onEvent(event);
		text.onEvent(event);
		if(event instanceof WindowResizeEvent || event instanceof SceneChangeEvent)
		{
			text.setPosition((float)(getWidth() - text.getWidth()) / 2 - 150, (float)(getHeight() - text.getHeight()) / 2 - 200);
		}
		return super.onEvent(event);
	}

}
