package scenes;

import app.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import scenes.howToPlay.Background;

public class HowToPlay extends Scene {

	public HowToPlay(Group group, Stage window) {
		super(group, window);
		addLayer(new Background(this));
	}

}
