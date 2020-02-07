package world.entity;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Animator {
	
	protected ArrayList<Image[]> m_Animations;
	protected float m_Period;
	private int m_CurrentAnimation, m_CurrentFrame;
	private float time;

	public Animator() {
		m_Animations = new ArrayList<Image[]>();
		m_Period = 1;
		m_CurrentAnimation = m_CurrentFrame = 0;
		time = 0;
	}
	
	public void addAnimation(Image[] images)
	{
		m_Animations.add(images);
	}
	
	public void setPeriod(float value)
	{
		m_Period = value;
	}

	public void update(float delta)
	{
		time += delta;
		if(time >= m_Period)
		{
			m_CurrentFrame++;
			time -= m_Period;
		}
		if(m_CurrentFrame >= getCurrentAnimation().length)
			m_CurrentFrame = 0;
	}
	
	public Image getCurrentFrame()
	{
		return m_Animations.get(m_CurrentAnimation)[m_CurrentFrame];
	}
	
	public Image[] getCurrentAnimation()
	{
		return m_Animations.get(m_CurrentAnimation);
	}
	
	public int getAnimationID()
	{
		return m_CurrentAnimation;
	}
	
	public int getFrameID()
	{
		return m_CurrentFrame;
	}
	
	public void setAnimation(int id)
	{
		if(id < 0 || id >= m_Animations.size())
			return;
		m_CurrentAnimation = id;
	}
	
	public void startAnimation(int id)
	{
		if(id < 0 || id >= m_Animations.size())
			return;
		m_CurrentFrame = 0;
		time = 0;
		m_CurrentAnimation = id;
	}
}
