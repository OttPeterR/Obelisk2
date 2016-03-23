import java.awt.Image;
import java.util.ArrayList;

public class Explosive extends PhysicsEntity
{
	double				radius, exDamage, force, speed, stuckRad, stuckAng;
	boolean				sticky, stuck = false;
	Weapon				detonater;
	int					forceToExplode;

	Explosion			ex;
	Entity				lastTouchedEntity	= null;
	ArrayList<Entity>	possibleHits;
	Wireframe			longWF;

	public Explosive(String n, Image im, String ims, Wireframe wf, Vect wfOff, double t, double mss, double spd, double rad, double exp, double frce, int forceToBlow, boolean stickee)
	{
		super(n, im, ims, wf, wfOff, 5, t, mss, 0, .5, false);
		possibleHits = new ArrayList<Entity>();
		if (rad > 0)
		{
			exDamage = exp;
			radius = rad;
			speed = spd;
			forceToExplode = forceToBlow;
			force = frce;
			sticky = stickee;
			ex = new Explosion(ents, x, y, exDamage, force, radius);
			ex.smokeLight.add(ParticleLibrary.SmokeMedium1());
			ex.smokeDark.add(ParticleLibrary.SmokeDarkMedium1());
			ex.fire.add(ParticleLibrary.Fire1());
		}
		else
			ex = null;

	}
	public void step()
	{
		if (!stuck)
		{
			superstep();
		}
		else
		{
			if (timeRemaining != -1)
			{
				timeRemaining -= 1 / fps;
				if (timeRemaining == -1)
					timeRemaining = 0;
			}
			translateTo(lastTouchedEntity.x + (stuckRad * Math.cos(stuckAng + lastTouchedEntity.ang)), lastTouchedEntity.y + (stuckRad * Math.sin(stuckAng + lastTouchedEntity.ang)));
			xSpeed = lastTouchedEntity.xSpeed;
			ySpeed = lastTouchedEntity.ySpeed;

			computeRect();

			if (lastTouchedEntity.isDead())
			{
				stuck = false;
				lastTouchedEntity = null;
			}

		}

	}
	public void superstep()
	{
		if (paused)
		{
			paused = false;
			myTime = System.nanoTime();
			return;
		}
		if (possibleHits.size() > 0)
		{
			int numInc = (int) Math.hypot(xSpeed / fps, ySpeed / fps);
			if (numInc > 1000)
				numInc = 1000;
			else if (numInc < 1)
				numInc = 1;
			else
				numInc *= 4;

			double xInc = (xSpeed / fps) / numInc;
			double yInc = (ySpeed / fps) / numInc;

			double firstX = 0 + x;
			double firstY = 0 + y;

			for (int inc = 0; inc < numInc; inc++)
			{
				for (int i = possibleHits.size() - 1; i >= 0; i--)
				{
					if (possibleHits.size() <= 0 || stuck)
						inc = numInc;
					else
					{
						if (!possibleHits.get(i).isDead() && checkForHit(possibleHits.get(i)))
						{
							addHitParticle(possibleHits.get(i), x, y);
							col(possibleHits.remove(i));
						}
						translate(xInc, yInc);

					}
				}
			}
			translateTo(firstX, firstY);
			possibleHits.clear();
		}
		if (timeRemaining != -1)
		{
			timeRemaining -= 1 / fps;
			if (timeRemaining == -1)
				timeRemaining = 0;
		}
		if (fps > 0)
		{
			Vect[] coords = new Vect[wireframes[0].numCoords * 2];
			for (int i = 0; i < wireframes[0].numCoords; i++)
			{
				coords[i] = wireframes[0].coords[i];
				coords[i + wireframes[0].numCoords] = new Vect(wireframes[0].coords[i].x + (xSpeed / (fps)), wireframes[0].coords[i].y + (ySpeed / (fps)));
			}
			longWF = new Wireframe(coords, wireframes[0].numCoords * 2);
		}
		advance();
		translateTo(x, y);
		computeRect();
	}
	public void shoot(double frames)
	{
		xSpeed = Math.cos(ang) * speed;
		ySpeed = Math.sin(ang) * speed;

		Vect[] coords = new Vect[wireframes[0].numCoords * 2];
		for (int i = 0; i < wireframes[0].numCoords; i++)
		{
			coords[i] = wireframes[0].coords[i];
			coords[i + wireframes[0].numCoords] = new Vect(wireframes[0].coords[i].x + (xSpeed / (fps)), wireframes[0].coords[i].y + (ySpeed / (fps)));
		}
		longWF = new Wireframe(coords, wireframes[0].numCoords * 2);

		rotateToward(x + xSpeed, y + ySpeed);
		step();
	}
	public boolean checkForHit(Entity e)
	{
		for (int i = 0; i < numWF; i++)
		{
			for (int j = 0; j < e.numWF; j++)
			{
				if (wireframes[i].collide(e.wireframes[j]))
				{
					return true;
				}
			}
		}
		return false;
	}
	public void collide(Entity e)
	{
		if (!e.ghost && !(e instanceof Explosion) && !(e instanceof Bullet) && !(e instanceof FracLine) && rect2.intersects(e.rect) && !noCollide.contains(e))
			possibleHits.add(e);
	}
	public void col(Entity e)
	{
		double origYS = 0 + ySpeed;
		double origXS = 0 + xSpeed;

		if (!sticky)
		{
			super.col(e);
			noCollide.clear();
		}
		else if (!stuck)
		{
			lastTouchedEntity = e;
			stuck = true;
			noCollide.clear();
			stuckRad = Math.hypot(x - e.x, e.y - y);
			if (x > e.x)
				stuckAng = Math.atan((e.y - y) / (e.x - x)) - lastTouchedEntity.ang;
			else
				stuckAng = Math.PI - Math.atan((y - e.y) / (e.x - x)) - lastTouchedEntity.ang;
			noCollide.add(e);
		}
		if ((e instanceof Biped && !sticky) || forceToExplode > 0 && Math.hypot(origXS, origYS) > forceToExplode)
		{
			health = 0;
		}
	}
	public Entity getNew()
	{
		Explosive e = new Explosive(name, img[0], imgName[0], wireframes[0].getNew(), wfOffset[0], fullTimeRemaining, mass, speed, radius, exDamage, force, forceToExplode, sticky);
		if (ex != null)
			e.ex = (Explosion) this.ex.getNew();
		e.pickUpable = this.pickUpable;

		return e;
	}
	public Entity copy()
	{
		return getNew();
	}
	public boolean removeMe()
	{
		if ((health == -1 || health > 0) && (timeRemaining == -1 || timeRemaining > 0))
			return false;
		if (ex != null)
		{
			if (lastTouchedEntity != null)
				ex.hitParticles = lastTouchedEntity.hitParticles;

			ex.translateTo(x, y);
			ex.loadArray(ents);
			ex.noCollide = this.noCollide;
			if (lastTouchedEntity != null)
				ex.hitP = lastTouchedEntity.hitParticles;
			ex.noCollide.remove(lastTouchedEntity);
			ex.fps = 0 + this.fps;
			ents.add(ex);
		}
		return true;
	}

	public void addHitParticle(Entity e, double hitX, double hitY)
	{}
}
