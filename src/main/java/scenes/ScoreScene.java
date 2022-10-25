package scenes;

import app.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import scenes.score.MainLayer;

public class ScoreScene extends Scene {

	public ScoreScene(Group group, Stage window) {
		super(group, window);
		addLayer(new MainLayer(this));
	}

}
