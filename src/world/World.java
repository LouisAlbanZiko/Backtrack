package world;

import java.util.ArrayList;

import app.Layer;
import app.Resources;
import app.Scene;
import events.Event;
import events.world.GravityEvent;
import events.world.KillEntityEvent;
import gui.Timer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import math.Vector2f;
import world.entity.Backtrack;
import world.entity.DoorEntrance;
import world.entity.DoorExit;
import world.entity.Entity;
import world.entity.EntityData;
import world.entity.Key;
import world.entity.Platform;
import world.entity.PlatformData;
import world.entity.player.Player;
import world.entity.player.PlayerIllusion;
import world.tiles.TileMap;

public class World extends Layer {
	
	public int score;
	public int level;
	public float gravity;
	private boolean win;
	
	public Camera camera;
	public TileMap tiles;
	public ArrayList<Entity> entities;
	public ArrayList<Entity> entitiesToKill;

	public Player player;
	public PlayerIllusion illusion;
	public Backtrack backtrack;
	public Key key;
	public DoorExit exit;
	
	public Timer timer;
	
	public World(Scene scene) {
		super(scene);
		gravity = 0.5f;
	}
	
	@Override
	public void render()
	{
		clear();
		GraphicsContext g = getGraphicsContext2D();
		if(tiles != null)
			tiles.render(g, camera.getOffset());
		for(Entity e : entities)
			if(!(e instanceof Player) && !(e instanceof PlayerIllusion))
				e.render(g, camera.getOffset());
		player.render(g, camera.getOffset());
		illusion.render(g, camera.getOffset());
		backtrack.render(g, camera.getOffset());
		if(timer != null)
			timer.render(g);
		if(player.hasKey)
		{
			g.drawImage(Resources.getImage("key"), timer.getRight() + 50, timer.getTop());
		}
	}
	
	@Override
	public void update(float delta)
	{
		onEvent(new GravityEvent(gravity));
		for(Entity e : entities)
			e.update(delta);
		if(entitiesToKill.size() != 0) {
			for(Entity e : entitiesToKill)
				entities.remove(e);
			entitiesToKill.clear();
		}
		camera.update();
		if(timer != null)
			timer.update(delta);
	}
	
	@Override
	public boolean onEvent(Event event)
	{
		if(event instanceof KillEntityEvent)
		{
			entitiesToKill.add(((KillEntityEvent) event).getEntity());
		}
		
		if(backtrack.onEvent(event))
			timer.sub(2);
		for(Entity e : entities)
		{
			e.onEvent(event);
		}
		return super.onEvent(event);
	}
	
	public boolean gameover()
	{
		if(timer.isDone())
			return true;
		if(illusion.getY() > tiles.getHeight() * tiles.getScale())
			return true;
		return false;
	}
	
	public boolean hasCollided(Entity entity, Vector2f offset)
	{
		return hasCollidedTiles(entity, offset) || getCollidedPlaform(entity, offset) != null;
	}
	
	public boolean hasCollidedTiles(Entity entity, Vector2f offset)
	{
		return tiles.hasCollided(entity, offset);
	}
	
	public Platform getCollidedPlaform(Entity entity, Vector2f offset)
	{
		if(offset.x != 0)
			return null;
		for(Entity e : entities)
		{
			if(e instanceof Platform)
			{
				Rectangle hitbox = new Rectangle(e.getX(), e.getY(), e.getWidth(), 1);
				if((hitbox.contains(entity.getX(), entity.getY() + entity.getHeight() + offset.y)
						|| hitbox.contains(entity.getX() + entity.getWidth(), entity.getY() + entity.getHeight() + offset.y))
						&& entity.getY() + entity.getHeight() <= e.getY()) {
					
					return (Platform)e;
				}
			}
		}
		return null;
	}
	
	public void loadLevel(int id)
	{
		this.level = id;
		this.win = false;
		LevelData level = Resources.getLevel(id);
		entities = new ArrayList<Entity>();
		entitiesToKill = new ArrayList<Entity>();
		float scale = 64;
		tiles = new TileMap(level.Width, level.Height, level.ids);
		tiles.setScale(scale);
		timer = new Timer(level.time);
		for(EntityData e : level.entities)
		{
			switch(e.id)
			{
			case 1:
				entities.add(new DoorEntrance(e.x * scale + scale / 8, e.y * scale, this));
				entities.add(player = new Player(e.x * scale + 16, e.y * scale, this));
				entities.add(illusion = new PlayerIllusion(e.x * scale + 16, e.y * scale, this));
				break;
			case 2:
				entities.add(exit = new DoorExit(e.x * scale + scale / 8, e.y * scale, this));
				break;
			case 3:
				entities.add(key = new Key(e.x * scale, e.y * scale, this));
				break;
			case 4:
				PlatformData p = (PlatformData) e;
				entities.add(new Platform(e.x * scale, e.y * scale, this, p.destination.mul(scale)));
				break;
			}
		}
		camera = new Camera(this);
		camera.setOffsetRelativeTo(player);
		backtrack = new Backtrack(player, illusion);
	}
	
	public void nextLevel()
	{
		int time = Resources.getLevel(level).time;
		score += timer.getTime() / time * 120 - backtrack.counter * 60;
		level++;
		if(level >= Resources.levels.size())
			win = true;
		else
			loadLevel(level);
	}
	
	public boolean hasWon()
	{
		return win;
	}

}
