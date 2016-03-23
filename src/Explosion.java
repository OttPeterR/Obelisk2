import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Explosion extends Entity
{
	ArrayList<Entity>	hitEnts, smokeLight, smokeDark, fire, hitP, extraJunk;
	double				damage, force, radius;
	boolean				exploded	= false;
	Entity				entHit;
	int					smokeNum	= -1, extraJunkCount;
	String				sound;

	public Explosion(ArrayList<Entity> entz, double inX, double inY, double dam, double forc, double rad)
	{
		super("Explosion", null, "NoImage", null);
		numWF = 2;
		numImg = 0;
		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];
		img = new java.awt.Image[numImg];
		imgName = new String[numImg];
		imgOffset = new Vect[numImg];
		ents = new ArrayList<Entity>();
		hitEnts = new ArrayList<Entity>();
		noCollide = new ArrayList<Entity>();
		hitP = new ArrayList<Entity>();
		loadArray(entz);
		wireframeColor = Color.RED;
		damage = dam;
		force = forc;
		radius = rad;
		sound = "explosion2.wav";
		int points = (int) (rad / 10);
		if (rad < 3)
			rad = 3;

		Vect[] coords = new Vect[points];

		for (int i = 0; i < points; i++)
		{
			double angle = 2 * Math.PI * i / ((double) (points));
			coords[i] = new Vect(Math.cos(angle) * radius, Math.sin(angle) * radius);
		}
		wireframes[0] = new Wireframe(coords, points);

		int points2 = (int) (rad / 10);
		if (rad < 3)
			rad = 3;

		Vect[] coords2 = new Vect[points2];

		for (int i = 0; i < points2; i++)
		{
			double angle = 2 * Math.PI * i / ((double) (points2));
			coords2[i] = new Vect(Math.cos(angle) * radius * 4, Math.sin(angle) * radius * 4);
		}
		wireframes[1] = new Wireframe(coords2, points2);
		rect = new Rectangle2D.Double(x - rad * 4, y - rad * 4, rad * 8, rad * 8);
		rect2 = rect;
		x = inX;
		y = inY;
		translateTo(x, y);
		wireframes[0].translateTo(x, y);
		wireframes[1].translateTo(x, y);
		smokeLight = new ArrayList<Entity>();
		smokeDark = new ArrayList<Entity>();
		fire = new ArrayList<Entity>();

	}
	public void updateWFs()
	{}
	public void step()
	{

		exploded = true;
	}
	// private void bulletDetonate()
	// {
	// new WeaponLibrary();
	// Bullet damB = WeaponLibrary.ShotgunPellet("Explosion_Fragment", damage,
	// (int) force, radius, 10000);
	// new WeaponLibrary();
	// Bullet forceB = WeaponLibrary.ShotgunPellet("Explosion_Shockwave", 0,
	// (int) force, radius * 4, 10000);
	// forceB.mass = 10;
	// for (int i = 0; i < 360; i += 3)
	// {
	// damB.rotateToward(Math.sin(Math.toRadians(i)),
	// Math.cos(Math.toRadians(i)));
	// forceB.rotateToward(Math.sin(Math.toRadians(i)),
	// Math.cos(Math.toRadians(i)));
	// damB.shoot(fps);
	// forceB.shoot(fps);
	// ents.add(damB.copy());
	// ents.add(forceB.copy());
	// }
	// }
	private void detonate()
	{
		Wireframe expRadius = Library.createDefaultWF(10, radius);
		expRadius.translateTo(x, y);
		for (int i = 0; i < ents.size(); i++)
		{
			if (!ents.get(i).ghost && ents.get(i).health > 0 && expRadius.collide(ents.get(i)))
				hitEnts.add(ents.get(i));
		}
		for (int i = 0; i < hitEnts.size(); i++)
		{
			Entity e = hitEnts.get(i);
			if (!noCollide.contains(e))
			{
				double distConst = (radius - Math.hypot((e.x - x), (e.y - y))) / (radius);
				if (e instanceof Zombie)
				{
					distConst = Math.max(distConst, (radius - Math.hypot((e.x - x), ((e.y - (((Zombie) e).height / 2)) - y))) / (radius));
					distConst = Math.max(distConst, (radius - Math.hypot((e.x - x), ((e.y - ((Zombie) e).height) - y))) / (radius));
				}
				// 1 = center of explosion - 0 = edge of explosion
				if (distConst > 0 && distConst < 1.1)
				{
					if (distConst > .5)// inner half
						e.damage(damage * 2 * (distConst - .5));
					if (e.physicalized && e.mass > 0)
					{
						double eAng = Math.atan(-(y - e.y) / (x - e.x));
						double divisor = 1;
						if (e instanceof Biped)
							eAng = Math.atan(-((e.y - 100) - (y)) / (e.x - x));
						else if (e.mass < 20)
							divisor = (10 - Math.sqrt(mass));
						if (x > e.x)// e this
						{
							e.xSpeed -= Math.cos(Math.abs(eAng)) * distConst * (force / e.mass) / divisor;
							e.ySpeed += Math.sin(eAng) * distConst * (force / e.mass) / divisor;
							e.rotSpeed -= (40 * distConst) / e.mass;
						}
						else
						// this e
						{
							e.xSpeed += Math.cos(Math.abs(eAng)) * distConst * (force / e.mass) / divisor;
							e.ySpeed -= Math.sin(eAng) * distConst * (force / e.mass) / divisor;
							e.rotSpeed += (40 * distConst) / e.mass;
						}
					}
				}
			}
		}
	}
	private void addParticles()
	{
		if (smokeNum == -1)
		{
			addParticles(smokeDark, (int) (radius / 25), 15, 260);// outer ring
			addParticles(smokeDark, (int) (radius / 60), 30, 370);// inner ring
			addParticles(fire, (int) (radius / 15), 7, 100);
		}
		else
		{
			addParticles(smokeDark, smokeNum, 1.7, 120);
			addParticles(smokeDark, (int) Math.ceil((double) smokeNum / 3), 6, 370);
			addParticles(fire, (int) Math.ceil((double) smokeNum / 5), 10, 25);
		}

		if (hitP != null && hitP.size() > 0)
			addHitParticles(hitP, 10, 6, force / 3);
		if (extraJunk != null && extraJunk.size() > 0)
			addParticles(extraJunk, extraJunkCount, 6, 100);
	}
	public void addParticles(ArrayList<Entity> al, int freq, double radDenom, double speedDenom)
	{
		if (al.size() > 0)
			for (int i = 0; i < freq; i++)
			{
				double tempAng = (Math.PI * 2) * (i / (radius / 25));

				Entity temp1 = al.get((int) (al.size() * Math.random())).getNew();

				temp1.timeRemaining *= (Math.random() + .8);
				temp1.translateTo(x + (radius / radDenom * ((Math.random() - .5))), y + (radius / radDenom * ((Math.random() - .5))));

				temp1.xSpeed = xSpeed / 5 + (Math.random() * .3 + .7) * (force / temp1.mass) / speedDenom * Math.cos(tempAng + ((Math.random() - .5) * .3));
				temp1.ySpeed = ySpeed / 5 + (Math.random() * .3 + .7) * (force / temp1.mass) / speedDenom * Math.sin(tempAng + ((Math.random() - .5) * .3));
				temp1.rotate(Math.PI * 2 * Math.random());
				temp1.rotSpeed = 15 * (Math.random() - .5);
				temp1.fps = 0 + this.fps;
				temp1.myTime = 0 + this.myTime;
				temp1.ghost = false;
				if (entHit != null)
					temp1.exitOverlap(entHit);
				ents.add(temp1);
			}

	}
	public void addHitParticles(ArrayList<Entity> al, int freq, double radDenom, double speed)
	{
		Entity temp1;
		for (int i = 0; i < freq; i++)
		{
			temp1 = al.get((int) (al.size() * Math.random())).getNew();
			temp1.ghost = false;
			temp1.intersectStaticOnly = true;
			temp1.timeRemaining *= (Math.random() + .8);
			temp1.translateTo(x + (radius / radDenom * ((Math.random() - .5))), y + (radius / radDenom * ((Math.random() - .5))));

			temp1.xSpeed = ((Math.random() * .3 + .7) * speed) * (Math.random() - .5);
			temp1.ySpeed = ((Math.random() * .3 + .7) * speed) * (Math.random() - .5);
			temp1.rotate(Math.PI * 2 * Math.random());
			temp1.rotSpeed = 15 * (Math.random() - .5);
			temp1.fps = 0 + this.fps;
			if (entHit != null)
				temp1.exitOverlap(entHit);
			ents.add(temp1.copy());
		}
	}
	public boolean intersects(Entity e)
	{
		return wireframes[0].collide(e);
	}
	public void translateTo(double inx, double iny)
	{
		wireframes[0].translateTo(inx, iny);
		wireframes[1].translateTo(inx, iny);
		x = inx;
		y = iny;
	}
	public void render(Graphics2D g, double origX, double origY)
	{}
	public boolean isDead()
	{
		return exploded;
	}
	public boolean removeMe()
	{
		if (exploded)
		{
			playSound(sound);
			detonate();
			addParticles();
		}
		return exploded;
	}

	public Entity getNew()
	{
		Explosion e = new Explosion(ents, 0 + x, 0 + y, 0 + damage, 0 + force, 0 + radius);
		e.smokeNum = this.smokeNum;
		e.fire = fire;
		e.extraJunk = this.extraJunk;
		e.extraJunkCount = this.extraJunkCount;
		e.smokeLight = smokeLight;
		e.smokeDark = smokeDark;
		e.sound = sound;
		return e;
	}

	public void collide(Entity e)
	{}
	public void col(Entity e)
	{

	}
	public String saveString()
	{
		String s = "[ Explosion ";
		s += name + " " + x + " " + y + " " + ang + " " + xSpeed + " " + ySpeed + " " + rotSpeed + " " + damage + " " + force + " " + radius + " " + smokeNum;
		return s + " ]";
	}
}
