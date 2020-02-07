package app;

import javafx.scene.Group;
import javafx.stage.Stage;
import scenes.Game;
import scenes.HowToPlay;
import scenes.Menu;
import scenes.ScoreScene;

public class App extends Application {
	
	public static final int menu = 0, game = 1, howToPlay = 2, scores = 3;
	public static Application app;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void _init(Stage window) {
		new Resources();
		
		app = this;
		
		addScene(new Menu(new Group(), window));
		addScene(new Game(new Group(), window));
		addScene(new HowToPlay(new Group(), window));
		addScene(new ScoreScene(new Group(), window));
		
		setScene(menu);
		window.setTitle("Backtrack");
		window.setWidth(800);
		window.setHeight(600);
	
	}
	
	

}
