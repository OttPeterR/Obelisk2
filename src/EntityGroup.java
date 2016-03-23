import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class EntityGroup
{
	double		x, y;
	Entity[]	ents;
	Vect[]		entOffset;
	double[]	entRad, entAng;
	int			numEnts;

	public EntityGroup(Entity[] es, Vect[] offs, int nEnts)
	{
		numEnts = nEnts;
		ents = new Entity[numEnts];
		entOffset = new Vect[numEnts];
		entRad = new double[numEnts];
		entAng = new double[numEnts];

		ents = es;
		entOffset = offs;

		for (int i = 0; i < numEnts; i++)
		{
			entRad[i] = Math.hypot(entOffset[i].x, entOffset[i].y);
			entAng[i] = Math.atan2(entOffset[i].x, entOffset[i].y);
		}
	}
	public void render(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		for (int i = 0; i < numEnts; i++)
		{
			ents[i].render(g, trans, origX, origY);
		}
	}
	public void translateTo(double inx, double iny)
	{
		for (int i = 0; i < numEnts; i++)
		{
			ents[i].translateTo(inx + entOffset[i].x, iny + entOffset[i].y);
		}
	}
	public void rotate(double theta)
	{
		for (int i = 0; i < numEnts; i++)
		{
			entAng[i] += theta;
			entOffset[i].x = entRad[i] * Math.cos(entAng[i]);
			entOffset[i].y = entRad[i] * Math.sin(entAng[i]);
		}
	}
	public void add(ArrayList<Entity> entz)
	{
		for (int i = 0; i < numEnts; i++)
		{
			entz.add(ents[i].copy());
		}
	}

}
