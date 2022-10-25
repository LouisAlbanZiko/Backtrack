package scenes.game;

import app.App;
import app.Layer;
import app.Scene;
import events.Event;
import gui.Action;
import gui.Button;
import gui.DefaultButton;
import gui.TextField;
import gui.TextView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scenes.Game;
import world.Score;

public class GameOverOverlay extends Layer {
	
	public TextView gameOver, enterYourName, score;
	public Button restart, menu;
	public TextField name;

	public GameOverOverlay(Scene scene, String text) {
		super(scene);
		
		gameOver = new TextView(text);
		gameOver.setTextColor(Color.RED);
		gameOver.setFont(Font.font("Arial", 60));
		
		restart = new DefaultButton("Restart", new Action() {
			@Override
			public void actionPerformed(Event event) {
				if(text.equals("WIN"))
					Score.insert(new Score(((Game) scene).world.score, name.getText()));
				((Game) scene).restart();
			}
		});
		
		menu = new DefaultButton("Menu", new Action() {
			@Override
			public void actionPerformed(Event event) {
				if(text.equals("WIN"))
					Score.insert(new Score(((Game) scene).world.score, name.getText()));
				App.app.setScene(App.menu);
			}
		});
		
		if(text.equals("WIN")){
			name = new TextField();
			name.setLimit(20);
			
			enterYourName = new TextView("Enter Your Name : ");
			enterYourName.setTextColor(Color.BLUE);
			enterYourName.setFont(Font.font("Arial", 24));
			
			score = new TextView("Score: " + (((Game)scene).world.score));
			score.setTextColor(Color.BLUE);
			score.setFont(Font.font("Arial", 24));
		}
	}

	@Override
	public void render() {
		clear();
		GraphicsContext g = getGraphicsContext2D();
		gameOver.render(g);
		restart.render(g);
		menu.render(g);
		if(enterYourName != null) {
			enterYourName.render(g);
			name.render(g);
			score.render(g);
		}
	}

	@Override
	public void update(float delta) {
		gameOver.setPosition((float)(getWidth() - gameOver.getWidth()) / 2, (float)(getHeight() - gameOver.getHeight()) / 2 - 140);
		restart.setPosition((float)(getWidth() - restart.getWidth()) / 2, (float)(getHeight() - restart.getHeight()) / 2 - 50);
		menu.setPosition((float)(getWidth() - menu.getWidth()) / 2, (float)(getHeight() - menu.getHeight()) / 2 + 20);
		if(enterYourName != null) {
			enterYourName.setPosition((float)(getWidth() - enterYourName.getWidth()) / 2, (float)(getHeight() - enterYourName.getHeight()) / 2 + 120);
			name.setPosition((float)(getWidth() - name.getWidth()) / 2, (float)(getHeight() - name.getHeight()) / 2 + 180);
			score.setText("Score: " + (((Game)m_ContainerScene).world.score));
			score.setPosition((float)(getWidth() - score.getWidth() - 20), (20));
		}
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		menu.onEvent(event);
		restart.onEvent(event);
		gameOver.onEvent(event);
		if(enterYourName != null) {
			enterYourName.onEvent(event);
			name.onEvent(event);
			score.onEvent(event);
		}
		//if(event instanceof WindowResizeEvent || event instanceof SceneChangeEvent)
		//{
			
		//}
		return super.onEvent(event);
	}
}
