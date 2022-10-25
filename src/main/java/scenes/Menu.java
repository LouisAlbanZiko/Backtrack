package scenes;

import app.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scenes.menu.Background;

public class Menu extends Scene {

	public Menu(Group group, Stage window) {
		super(group, window);
		addLayer(new Background(Color.BLUE.desaturate().desaturate(), this));
	}
	
}
