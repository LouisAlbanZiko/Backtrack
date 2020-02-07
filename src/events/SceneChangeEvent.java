package events;

import app.Scene;

public class SceneChangeEvent extends Event {

	private Scene m_Scene;
	
	public SceneChangeEvent(Scene scene) {
		m_Scene = scene;
	}
	
	public Scene getScene()
	{
		return m_Scene;
	}

}
