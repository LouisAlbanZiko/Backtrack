package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Element extends Component {
	
	protected Text text;
	protected Color[] backgroundColor, borderColor, textColor;
	private boolean renderBackground, renderBorder, renderText, fixedBounds;
	
	protected float marginLeft, marginRight, marginTop, marginBot;
	protected float paddingLeft, paddingRight, paddingTop, paddingBot;
	protected float borderLeft, borderRight, borderTop, borderBot;

	public Element() {
		backgroundColor = new Color[3];
		borderColor = new Color[3];
		textColor = new Color[3];
		fixedBounds = false;
		renderBackground = true;
		renderBorder = true;
		renderText = true;
	}
	
	@Override
	public void render(GraphicsContext g)
	{
		if(renderBackground) 
		{
			g.setFill(backgroundColor[state]);
			super.fill(g);
		}
		if(renderBorder) 
		{
			g.setFill(borderColor[state]);
			if(borderLeft != 0)
				g.fillRect(m_Min.x, m_Min.y, borderLeft, m_Max.y - m_Min.y);
			if(borderRight != 0)
				g.fillRect(m_Max.x - borderRight, m_Min.y, borderRight, m_Max.y - m_Min.y);
			if(borderTop != 0)
				g.fillRect(m_Min.x, m_Min.y, m_Max.x - m_Min.x, borderTop);
			if(borderBot != 0)
				g.fillRect(m_Min.x, m_Max.y - borderBot, m_Max.x - m_Min.x, borderLeft);
		}
		if(renderText)
		{
			if(text != null)
			{
				g.setFill(textColor[state]);
				text.render(m_Min.x + borderLeft + paddingLeft - 1, m_Max.y - borderBot - paddingBot - 4, g);
			}
		}
	}
	
	@Override
	public void refresh()
	{
		if(fixedBounds)
			return;
		if(text != null) {
			text.refresh();
			setSize(text.getWidth() + paddingRight + paddingLeft + borderRight + borderLeft,
				text.getHeight() + paddingTop + paddingBot + borderTop + borderBot);
		}
	}
	
	public boolean isRenderBackground() {
		return renderBackground;
	}

	public void setRenderBackground(boolean renderBackground) {
		this.renderBackground = renderBackground;
	}

	public boolean isRenderBorder() {
		return renderBorder;
	}

	public void setRenderBorder(boolean renderBorder) {
		this.renderBorder = renderBorder;
	}

	public boolean isRenderText() {
		return renderText;
	}

	public void setRenderText(boolean renderText) {
		this.renderText = renderText;
	}

	public boolean isFixedBounds() {
		return fixedBounds;
	}

	public void setFixedBounds(boolean fixedBounds) {
		this.fixedBounds = fixedBounds;
	}

	public void setFont(Font font)
	{
		this.text.setFont(font);
		refresh();
	}
	
	public void setMargin(float margin)
	{
		marginLeft = marginRight = marginTop = marginBot = margin;
	}
	
	public void setPadding(float padding)
	{
		paddingLeft = paddingRight = paddingTop = paddingBot = padding;
		refresh();
	}
	
	public void setBorder(float border)
	{
		borderLeft = borderRight = borderTop = borderBot = border;
		refresh();
	}

	public float getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(float marginLeft) {
		this.marginLeft = marginLeft;
		refresh();
	}

	public float getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(float marginRight) {
		this.marginRight = marginRight;
		refresh();
	}

	public float getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(float marginTop) {
		this.marginTop = marginTop;
		refresh();
	}

	public float getMarginBot() {
		return marginBot;
	}

	public void setMarginBot(float marginBot) {
		this.marginBot = marginBot;
		refresh();
	}

	public float getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(float paddingLeft) {
		this.paddingLeft = paddingLeft;
		refresh();
	}

	public float getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(float paddingRight) {
		this.paddingRight = paddingRight;
		refresh();
	}

	public float getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(float paddingTop) {
		this.paddingTop = paddingTop;
		refresh();
	}

	public float getPaddingBot() {
		return paddingBot;
	}

	public void setPaddingBot(float paddingBot) {
		this.paddingBot = paddingBot;
		refresh();
	}

	public float getBorderLeft() {
		return borderLeft;
	}

	public void setBorderLeft(float borderLeft) {
		this.borderLeft = borderLeft;
		refresh();
	}

	public float getBorderRight() {
		return borderRight;
	}

	public void setBorderRight(float borderRight) {
		this.borderRight = borderRight;
		refresh();
	}

	public float getBorderTop() {
		return borderTop;
	}

	public void setBorderTop(float borderTop) {
		this.borderTop = borderTop;
		refresh();
	}

	public float getBorderBot() {
		return borderBot;
	}

	public void setBorderBot(float borderBot) {
		this.borderBot = borderBot;
		refresh();
	}
	
	public void setBackgroundColor(Color color)
	{
		for(int i = 0; i < backgroundColor.length; i++)
			backgroundColor[i] = color;
	}
	
	public void setBorderColor(Color color)
	{
		for(int i = 0; i < borderColor.length; i++)
			borderColor[i] = color;
	}
	
	public void setTextColor(Color color)
	{
		for(int i = 0; i < textColor.length; i++)
			textColor[i] = color;
	}
	
	public void setBackgroundColor(Color color, int index)
	{
		backgroundColor[index] = color;
	}
	
	public void setBorderColor(Color color, int index)
	{
		borderColor[index] = color;
	}
	
	public void setTextColor(Color color, int index)
	{
		textColor[index] = color;
	}
	
	public void setText(String string)
	{
		if(this.text == null)
			this.text = new Text(string);
		else
			this.text.setText(string);
		refresh();
	}
	
	public String getText()
	{
		if(text != null)
			return text.getText();
		return "";
	}
	
	

}
