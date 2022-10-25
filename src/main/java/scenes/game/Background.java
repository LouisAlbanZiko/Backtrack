package scenes.game;

import app.Layer;
import app.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Background extends Layer {
	
	private Color m_Color;

	public Background(Color color, Scene scene) {
		super(scene);
		m_Color = color;
	}

	@Override
	public void render() {
		GraphicsContext g = super.getGraphicsContext2D();
		g.setFill(m_Color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void update(float delta) {
		
	}

}
