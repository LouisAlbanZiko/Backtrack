package app;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import graphics.SpriteSheet;
import javafx.scene.image.Image;
import world.LevelData;

public class Resources {
	
	public static HashMap<String, SpriteSheet> sheets;
	public static HashMap<String, Image> images;
	public static ArrayList<Image> tiles;
	public static ArrayList<LevelData> levels;

	public Resources() {
		sheets = new HashMap<String, SpriteSheet>();
		images = new HashMap<String, Image>();
		tiles = new ArrayList<Image>();
		levels = new ArrayList<LevelData>();
		
		tiles.add(new Image("file:res/images/tile.png"));
		
		try {
			for(int i = 0; i < 8; i++)
				levels.add(new LevelData("res/levels/lvl" + (i + 1) + ".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		images.put("clock", new Image("file:res/images/Clock.png"));
		images.put("platform", new Image("file:res/images/platform.png"));
		images.put("key", new Image("file:res/images/key.png"));
		images.put("movement", new Image("file:res/images/movement.png"));
		images.put("backtrack", new Image("file:res/images/Q.png"));
		images.put("space", new Image("file:res/images/space.png"));
		
		try {
			sheets.put("player", new SpriteSheet("res/images/player.png", 32, 64));
			sheets.put("player illusion", new SpriteSheet("res/images/playerillusion.png", 32, 64));
			
			sheets.put("door", new SpriteSheet("res/images/door.png", 48, 64));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static SpriteSheet getSpriteSheet(String key)
	{
		return sheets.get(key);
	}
	
	public static Image getImage(String key)
	{
		return images.get(key);
	}
	
	public static Image getTileImage(int index)
	{
		if(index < 0)
			return null;
		return tiles.get(index);
	}
	
	public static LevelData getLevel(int index)
	{
		if(index < 0)
			return null;
		return levels.get(index);
	}

}
