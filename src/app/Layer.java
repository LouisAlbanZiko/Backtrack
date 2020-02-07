package app;

import events.Event;
import events.EventListener;
import javafx.scene.canvas.Canvas;

public abstract class Layer extends Canvas implements EventListener {
	
	protected Scene m_ContainerScene;
	
	public Layer(Scene scene)
	{
		super();
		m_ContainerScene = scene;
	}
	
	public abstract void render();
	public abstract void update(float delta);
	
	public void clear()
	{
		getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public boolean isResizable()
	{
		return true;
	}
	
	@Override
    public double prefWidth(double height) {
      return getWidth();
    }
 
    @Override
    public double prefHeight(double width) {
      return getHeight();
    }
    
    @Override
	public boolean onEvent(Event event)
	{
    	return false;
	}

}