package app;

import java.util.Stack;

import events.Event;
import events.window.WindowResizeEvent;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class Scene extends javafx.scene.Scene {
	
	public Stage m_Window;
	public Group m_Parent;
	public Stack<Layer> m_Layers, m_Overlays;
	public float m_Width, m_Height;
	
	public Scene(Group group, Stage window) {
		super(group);
		m_Layers = new Stack<Layer>();
		m_Overlays = new Stack<Layer>();
		m_Parent = group;
		m_Window = window;
		m_Width = (float) window.getWidth();
		m_Height = (float) window.getHeight();
	}
	
	
	public void addLayer(Layer layer)
	{
		layer.widthProperty().bind(m_Window.widthProperty());
		layer.heightProperty().bind(m_Window.heightProperty());
		m_Layers.push(layer);
		m_Parent.getChildren().add(layer);
	}
	
	public void addLayer(Layer layer, int index)
	{
		layer.widthProperty().bind(m_Window.widthProperty());
		layer.heightProperty().bind(m_Window.heightProperty());
		m_Layers.add(index, layer);
		m_Parent.getChildren().add(index, layer);
	}
	
	public void addOverlay(Layer layer)
	{
		layer.widthProperty().bind(m_Window.widthProperty());
		layer.heightProperty().bind(m_Window.heightProperty());
		m_Overlays.push(layer);
		m_Parent.getChildren().add(layer);
	}
	
	public void removeLayer() 
	{
		m_Layers.pop();
		m_Parent.getChildren().remove(m_Parent.getChildren().size() - 1);
	}
	
	public void removeOverlay()
	{
		m_Overlays.pop();
		m_Parent.getChildren().remove(m_Parent.getChildren().size() - 1);
	}
	
	public void dispatch(Event event)
	{
		for(int i = m_Overlays.size() - 1; i > -1; i--)
			if(m_Overlays.get(i).onEvent(event))
				return;
		for(int i = m_Layers.size() - 1; i > -1; i--)
			if(m_Layers.get(i).onEvent(event))
				return;
		if(event instanceof WindowResizeEvent)
		{
			m_Width = ((WindowResizeEvent) event).getWidth();
			m_Height = ((WindowResizeEvent) event).getHeight();
		}
	}
	
	public void render()
	{
		for(int i = 0; i < m_Layers.size(); i++)
			m_Layers.get(i).render();
		for(int i = 0; i < m_Overlays.size(); i++)
			m_Overlays.get(i).render();
	}
	
	public void update(float delta)
	{
		for(int i = 0; i < m_Overlays.size(); i++)
			m_Overlays.get(i).update(delta);
		for(int i = 0; i < m_Layers.size(); i++)
			m_Layers.get(i).update(delta);
	}
	
	public Group getParent()
	{
		return m_Parent;
	}

	public Stage getStage() {
		return m_Window;
	}


}
