package gui;

public class Button extends Element {
	
	public Action action;
	
	public Button()
	{
		
	}
	
	public Button(String string)
	{
		text = new Text(string);
		refresh();
	}

	@Override
	public void onHover() {
		
	}
	

	@Override
	public void onPressed() {
		
	}

	@Override
	public void onReleased() {
		if(action != null)
			action.actionPerformed(null);
	}
	
	public void setOnClickEvent(Action action)
	{
		this.action = action;
	}

}
