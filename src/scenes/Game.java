package scenes;

import java.awt.event.KeyEvent;

import app.Scene;
import events.Event;
import events.SceneChangeEvent;
import events.key.KeyReleasedEvent;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scenes.game.Background;
import scenes.game.GameOverOverlay;
import scenes.game.PauseOverlay;
import world.World;

public class Game extends Scene {
	
	private enum State {
		running, paused, gameover, win
	}
	
	public World world;
	public PauseOverlay pauseOverlay;
	public GameOverOverlay gameOverOverlay;
	public GameOverOverlay winOverlay;
	
	private State state;

	public Game(Group group, Stage window) {
		super(group, window);
		
		addLayer(new Background(Color.BLUE.desaturate().desaturate(), this));
		
		world = new World(this);
		addLayer(world);
		
		pauseOverlay = new PauseOverlay(this);
		gameOverOverlay = new GameOverOverlay(this, "GAME OVER");
		winOverlay = new GameOverOverlay(this, "WIN");
		
		restart();
	}
	
	public void restart()
	{
		world.loadLevel(0);
		world.score = 0;
		state = State.running;
		while(!m_Overlays.isEmpty()) 
		{
			removeOverlay();
		}
	}
	
	@Override
	public void update(float delta)
	{
		switch(state)
		{
		case running:
			super.update(delta);
			if(world.gameover())
				gameover();
			else if(world.hasWon())
				win();
			break;
		case paused:
			pauseOverlay.update(delta);
			break;
		case gameover:
			gameOverOverlay.update(delta);
			break;
		case win:
			winOverlay.update(delta);
			break;
		}
	}
	
	public void pause()
	{
		addOverlay(pauseOverlay);
		state = State.paused;
	}
	
	public void unpause()
	{
		removeOverlay();
		state = State.running;
	}
	
	public void gameover()
	{
		addOverlay(gameOverOverlay);
		state = State.gameover;
	}
	
	public void win()
	{
		addOverlay(winOverlay);
		state = State.win;
		System.out.println(world.score);
	}
	
	@Override
	public void dispatch(Event event)
	{
		if(event instanceof KeyReleasedEvent)
		{
			KeyReleasedEvent e = (KeyReleasedEvent) event;
			if(e.getKeyCode() == KeyCode.ESCAPE)
			{
				if(state == State.running)
					pause();
				else if(state == State.paused)
					unpause();
			}
		}
		else if(event instanceof SceneChangeEvent)
		{
			restart();
			gameOverOverlay.onEvent(event);
			pauseOverlay.onEvent(event);
			winOverlay.onEvent(event);
		}
		switch(state)
		{
		case running:
			super.dispatch(event);
			break;
		case gameover:
			gameOverOverlay.onEvent(event);
			break;
		case paused:
			pauseOverlay.onEvent(event);
			break;
		case win:
			winOverlay.onEvent(event);
		}
	}
}
