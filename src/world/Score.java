package world;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Score {
	
	private static final String path = "res/scores.txt";
	public static Score[] scores = load();
	
	public static void insert(Score score)
	{
		if(score.name.equals(""))
			score.name = "No name";
		for(int i = 0; i < scores.length; i++)
		{
			if(score.value > scores[i].value)
			{
				for(int j = scores.length - 1; j > i; j--)
				{
					scores[j] = scores[j - 1];
				}
				scores[i] = score;
				break;
			}
		}
		write(scores);
	}
	
	public static boolean isHighScore(Score score)
	{
		for(int i = 0; i < scores.length; i++)
		{
			if(score.value > scores[i].value)
				return true;
		}
		return false;
	}
	
	public static Score[] load()
	{
		Score[] scores = new Score[5];
		try {
			FileInputStream stream = new FileInputStream(path);
			for(int i = 0; i < scores.length; i++)
				scores[i] = new Score(readInt(stream), readString(stream));
			stream.close();
		}
		catch (IOException e) {
			for(int i = 0; i < scores.length; i++)
				scores[i] = new Score(0, "Empty");
		}
		return scores;
	}
	
	public static void write(Score[] scores)
	{
		try {
			FileOutputStream stream = new FileOutputStream(path);
			for(int i = 0; i < scores.length; i++)
			{
				writeInt(stream, scores[i].value);
				writeString(stream, scores[i].name);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String readString(FileInputStream input) throws IOException
	{
		int length = input.read();
		StringBuilder s = new StringBuilder(length);
		for(int i = 0; i < length; i++)
		{
			s.append((char) input.read());
		}
		return s.toString();
	}
	
	private static void writeString(FileOutputStream output, String string) throws IOException
	{
		output.write(string.length());
		for(int i = 0; i < string.length(); i++)
		{
			output.write(string.charAt(i));
		}
	}
	
	private static int readInt(FileInputStream input) throws IOException
	{
		int value = 0;
		for(int i = 0; i < 4; i++)
		{
			value += input.read() * Math.pow(0xff, i);
		}
		return value;
	}
	
	private static void writeInt(FileOutputStream output, int value) throws IOException
	{
		for(int i = 0; i < 4; i++) {
			output.write(value % 0xff);
			value /= 0xff;
		}
	}
	
//////////////////////////////////////////////////
	
	public int value;
	public String name;
	
	public Score(int v, String n)
	{
		value = v;
		name = n;
	}

}
