package app;

import java.util.ArrayList;

import events.Event;
import events.SceneChangeEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.stage.Stage;

public abstract class Application extends javafx.application.Application {
	
	public ArrayList<Scene> scenes = new ArrayList<Scene>();
	public int currentScene;
	public Group root;
	public Stage window;
	
	@Override
	public void start(Stage window)
	{
		this.window = window;
		root = new Group();
		window.addEventHandler(javafx.event.Event.ANY, new EventDispatcher(this));
		_init(window);
		AnimationTimer timer = new AnimationTimer() {
			long lastTime;
			@Override
			public void handle(long now) {
				if(lastTime == 0)
					lastTime = now;
				update((now - lastTime) / 16666666.66666666666666f);
				render();
				lastTime = now;
			}
		};
		timer.start();
		window.show();
	}
	
	public abstract void _init(Stage window);
	
	public void render()
	{
		scenes.get(currentScene).render();
	}
	
	public void update(float delta)
	{
		//System.out.println(delta);
		scenes.get(currentScene).update(delta);
	}
	
	public void addScene(Scene scene)
	{
		scenes.add(scene);
	}
	
	public void dispatchEvent(Event event)
	{
		scenes.get(currentScene).dispatch(event);
	}
	
	public void setScene(int id)
	{
		if(id < 0 || id >= scenes.size())
			return;
		window.setScene(scenes.get(id));
		window.centerOnScreen();
		currentScene = id;
		scenes.get(currentScene).dispatch(new SceneChangeEvent(scenes.get(currentScene)));
	}
	
}
