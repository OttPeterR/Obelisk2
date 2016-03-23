import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;

public abstract class Library
{
	static Color	PROColor	= new Color(25, 133, 169);

	// public abstract void loadAll(java.util.ArrayList<Entity> ents);
	// public java.util.ArrayList<Entity> getAll()
	// {
	// java.util.ArrayList<Entity> r = new java.util.ArrayList<Entity>();
	// loadAll(r);
	// return r;
	// }
	public static Image img(String s)
	{
		try
		{
			return ImageIO.read(Library.class.getResourceAsStream("/Graphics/" + s));
		}
		catch (Exception e)
		{
			return null;
		}
	}
	public static Wireframe createWF(double inx, double iny)
	{
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(inx, 0);
		coords[2] = new Vect(inx, iny);
		coords[3] = new Vect(0, iny);
		Wireframe r = new Wireframe(coords, 4);
		return r;
	}
	public static Wireframe createDefaultWF(int points, double radius)
	{
		Vect[] coords = new Vect[points];

		for (int i = 0; i < points; i++)
		{
			double angle = 2 * Math.PI * i / ((double) (points));
			coords[i] = new Vect(Math.cos(angle) * radius, Math.sin(angle) * radius);
		}
		return new Wireframe(coords, points);
	}
}
