package world;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import math.Vector2f;
import world.entity.EntityData;
import world.entity.PlatformData;

public class LevelData {
	
	public int time;
	public int Width, Height;
	public int[] ids;
	public EntityData[] entities;
	
	public LevelData(String path) throws FileNotFoundException
	{
		FileInputStream stream = new FileInputStream(path);
		Scanner scan = new Scanner(stream);
		
		time = scan.nextInt();
		int n = scan.nextInt();
		entities = new EntityData[n];
		for(int i = 0; i < n; i++)
		{
			int id = scan.nextInt();
			if(id == 4)
				entities[i] = new PlatformData();
			else
				entities[i] = new EntityData();
			entities[i].id = id;
			entities[i].x = scan.nextInt();
			entities[i].y = scan.nextInt();
			if(id == 4)
			{
				PlatformData p = (PlatformData) entities[i];
				p.destination = new Vector2f(scan.nextInt(), scan.nextInt());
			}
		}
		
		int w = Width = scan.nextInt();
		int h = Height = scan.nextInt();
		ids = new int[w * h];
		for(int y = 0; y < h; y++)
		{
			for(int x = 0; x < w; x++)
			{
				ids[x + y * w] = scan.nextInt();
			}
		}
		
		scan.close();
	}

}
