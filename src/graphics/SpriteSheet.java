package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class SpriteSheet {
	
	protected int Width, Height;
	protected Image[] images;

	public SpriteSheet(String path, int width, int height) throws FileNotFoundException {
		Image image = new Image(new FileInputStream(path));
		Width = (int) (image.getWidth() / width);
		Height = (int) (image.getHeight() / height);
		images = new Image[Width * Height];
		for(int y = 0; y < Height; y++)
		{
			for(int x = 0; x < Width; x++)
			{
				images[x + y * Width] = new WritableImage(image.getPixelReader(), x * width, y * height, width, height);
			}
		}
	}
	
	public Image getImage(int x, int y)
	{
		if(x < 0 || x >= Width || y < 0 || x >= Height)
			return null;
		return images[x + y * Width];
	}
	
	public Image getImage(int index)
	{
		if(index > images.length || index < 0)
			return null;
		return images[index];
	}
	
	public Image[] getImages(int row, int length)
	{
		Image[] images = new Image[length];
		for(int i = 0; i < images.length; i++)
			images[i] = getImage(i, row);
		return images;
	}

}
