import java.awt.Image;
import java.util.ArrayList;

public class Spawner extends Entity
{
	ArrayList<Entity>	spawns;
	double				rateOfSpawns;	// per minute
	int					numToAdd, spawnsLeft;
	Vect				speedOfSpawned;
	double				timeUntilNextSpawn;
	double				angleError	= 0;
	Vect				addPos;

	public Spawner(String n, Image im, String ims, Wireframe wf, Vect wfOff, Entity e, double ros, int n2a, int sl)
	{
		super(n, im, ims, wf, wfOff);
		spawns = new ArrayList<Entity>();
		spawns.add(e);
		speedOfSpawned = new Vect(0, 0);
		addPos = new Vect(0, 0);
		rateOfSpawns = ros;
		numToAdd = n2a;
		spawnsLeft = sl;
		computeRect();
	}
	public Spawner(String n, Image im, String ims, Wireframe wf, Vect wfOff, ArrayList<Entity> e, double ros, int n2a, int sl)
	{
		super(n, im, ims, wf, wfOff);
		spawns = new ArrayList<Entity>();
		spawns.addAll(e);
		speedOfSpawned = new Vect(0, 0);
		addPos = new Vect(0, 0);
		rateOfSpawns = ros;
		numToAdd = n2a;
		spawnsLeft = sl;
		computeRect();
	}
	public Spawner(String n, Image im, String ims, Wireframe wf, Vect wfOff, ArrayList<Entity> e, Vect sos, double ros, int n2a, int sl)
	{
		super(n, im, ims, wf, wfOff);
		spawns = new ArrayList<Entity>();
		spawns.addAll(e);
		speedOfSpawned = sos;
		rateOfSpawns = ros;
		numToAdd = n2a;
		spawnsLeft = sl;
		addPos = new Vect(0, 0);
		computeRect();
	}
	public Entity getNew()
	{
		Spawner s = new Spawner(name, img[0], imgName[0], wireframes[0].getNew(), wfOffset[0], spawns, speedOfSpawned, rateOfSpawns, numToAdd, spawnsLeft);
		s.timeRemaining = this.timeRemaining;
		s.fullHealth = this.fullHealth;
		s.health = 0 + this.fullHealth;
		s.ghost = this.ghost;
		s.intersectStaticOnly = this.intersectStaticOnly;
		s.ghost = this.ghost;
		s.deathParticles = this.deathParticles;
		s.hitParticles = this.hitParticles;
		s.deathParticles = this.deathParticles;
		s.angleError = this.angleError;
		return s;
	}
	public Entity copy()
	{
		return getNew();
	}
	public boolean removeMe()
	{
		if (spawnsLeft <= 0 && spawnsLeft != -1)
			return false;
		else
			return super.removeMe();
	}
	public void step()
	{
		if (paused)
		{
			paused = false;
			return;
		}
		else if (timeRemaining != -1)
		{
			timeRemaining -= 1 / fps;
			if (timeRemaining == -1)
				timeRemaining = 0;
		}
		if (timeUntilNextSpawn <= 0 && ents != null)
		{
			for (int i = 0; i < numToAdd; i++)
			{
				spawn();
			}
			timeUntilNextSpawn = rateOfSpawns;
		}
		else
			timeUntilNextSpawn -= 1 / fps;
	}
	public void spawn()
	{
		Entity e = spawns.get((int) (Math.random() * spawns.size())).getNew();
		e.translateTo(0 + x + Math.cos(ang) * addPos.velocity(), 0 + y + Math.sin(ang) * addPos.velocity());
		if (speedOfSpawned.x != 0 && speedOfSpawned.y != 0)
		{

			double a = 0 + ang + (angleError * (Math.random() - .5));
			e.xSpeed = (speedOfSpawned.velocity() * Math.cos(a));// *
																	// (Math.random()
																	// * .5 +
																	// .5);
			e.ySpeed = (speedOfSpawned.velocity() * Math.sin(a));// *
																	// (Math.random()
																	// * .5 +
																	// .5);
		}
		ents.add(0, e);
		spawnsLeft--;
	}
	public void collide(Entity e)
	{}
	public String saveString()
	{
		String s = "[ Spawner ";

		return s + " ]";
	}
}
