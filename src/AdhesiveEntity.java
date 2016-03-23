import java.awt.Image;
import java.util.ArrayList;

public class AdhesiveEntity extends PhysicsEntity
{
	double				speed, stuckRad, stuckAng, damage, angAtHit, damageMultiplier = 1;
	boolean				stuck				= false, forward = false;
	Entity				lastTouchedEntity	= null;
	ArrayList<Entity>	possibleHits;
	Wireframe			longWF;
	boolean				bipedFacingRight	= false;

	public AdhesiveEntity(String n, Image im, String ims, Wireframe wf, Vect wfOff, double t, double mss, double spd, double dmg)
	{
		super(n, im, ims, wf, wfOff, 5, t, mss, 0, .5, false);
		possibleHits = new ArrayList<Entity>();
		speed = spd;
		damage = dmg;
	}
	public Entity getNew()
	{
		AdhesiveEntity e = new AdhesiveEntity(name, img[0], imgName[0], wireframes[0].getNew(), wfOffset[0], fullTimeRemaining, mass, speed, damage);
		e.hitParticles = this.hitParticles;
		e.deathParticles = this.deathParticles;
		e.particlesToAddOnDeath = this.particlesToAddOnDeath;
		e.fade = this.fade;
		e.forward = this.forward;
		e.pickUpable = this.pickUpable;
		e.damageMultiplier = this.damageMultiplier;
		return e;
	}
	public Entity copy()
	{
		return getNew();
	}
	public void step()
	{
		if (!stuck)
			superstep();
		if (stuck)
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
			ang = 0 + lastTouchedEntity.ang + angAtHit;
			if (lastTouchedEntity instanceof Biped)
			{
				if (((Biped) lastTouchedEntity).facingRight != bipedFacingRight)
				{
					stuckAng = -stuckAng - Math.PI;
					bipedFacingRight = (((Biped) lastTouchedEntity).facingRight);
					rotate(Math.PI);
				}
			}
			computeRect();

			if (lastTouchedEntity.isDead())
			{
				stuck = false;
				lastTouchedEntity = null;
				timeRemaining = .5;
			}
			advance();
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
					if (possibleHits.size() <= 0)
						inc = numInc;
					else
					{
						if (possibleHits.get(i) != null && !possibleHits.get(i).isDead() && checkForHit(possibleHits.get(i)))
						{
							col(possibleHits.remove(i));
						}

						if (xInc >= Integer.MIN_VALUE && yInc >= Integer.MIN_VALUE)
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
		if (forward && !stuck)
		{
			// double origAng = 0 + ang;
			// if (ySpeed != 0)
			// {
			rotateToward(x + xSpeed, y + ySpeed);
			// rotate((ang + origAng) / 2 - ang);
			// }

		}
		if (fps > 0)
		{
			Vect[] coords = new Vect[wireframes[0].numCoords * 2];
			for (int i = 0; i < wireframes[0].numCoords; i++)
			{
				coords[i] = wireframes[0].coords[i];
				coords[i + wireframes[0].numCoords] = new Vect(wireframes[0].coords[i].x + (xSpeed * 1.3 / (fps)), wireframes[0].coords[i].y + (ySpeed * 1.3 / (fps)));
			}
			longWF = new Wireframe(coords, wireframes[0].numCoords * 2);
		}
		advance();
		translateTo(x, y);
		computeRect();
		myTime = System.nanoTime();
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
		for (int j = 0; j < e.numWF; j++)
		{
			if (wireframes[0].collide(e.wireframes[j]))
			{
				return true;
			}
		}
		return false;
	}
	public void collide(Entity e)
	{
		try
		{
			if (e instanceof Biped)
			{
				if (longWF.collide(e.wireframes[0]) || longWF.collide(((Biped) e).head))
				{
					possibleHits.add(e);
				}
			}
			else
			{
				if (!e.ghost && !(e instanceof Explosion) && !(e instanceof Bullet) && !(e instanceof Weapon) && !(e instanceof FracLine) && rect2.intersects(e.rect) && !noCollide.contains(e))
					possibleHits.add(e);
			}
		}
		catch (Exception exc)
		{}
	}
	public void col(Entity e)
	{
		if (!e.ghost && !(e instanceof AdhesiveEntity) && !(e instanceof Explosion) && !(e instanceof Bullet) && !(e instanceof Weapon) && !(e instanceof FracLine) && !noCollide.contains(e))
			if (!stuck)
			{
				if (damage > 0)
				{
					if (!(e instanceof Biped))
						e.damage(damage);
					else
						e.damage(damageMultiplier * damage);
					damage /= 2;
					addHitParticle(e, x, y);
				}
				if (e.isDead())
					return;

				lastTouchedEntity = e;
				stuck = true;
				if (lastTouchedEntity instanceof Biped)
					bipedFacingRight = ((Biped) lastTouchedEntity).facingRight;
				angAtHit = 0 + ang - e.ang;
				ghost = true;
				stuckRad = Math.hypot(x - e.x, e.y - y);
				if (x > e.x)
					stuckAng = Math.atan((e.y - y) / (e.x - x)) - lastTouchedEntity.ang;
				else
					stuckAng = Math.PI - Math.atan((y - e.y) / (e.x - x)) - lastTouchedEntity.ang;
				noCollide.add(e);
			}

	}
	public void addHitParticle(Entity e, double hitX, double hitY)
	{

		if (e.hitParticles != null && e.hitParticles.size() > 0)
		{
			double a = Math.atan(ySpeed / xSpeed);
			double spd = Math.pow(Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed), .65);
			for (int i = 0; i < (Math.random() * .4 + .6) * (mass * 2); i++)
			{
				a += Math.PI;
				Entity eTemp = e.hitParticles.get((int) (Math.random() * e.hitParticles.size())).getNew();
				eTemp.rotate(Math.random() * Math.PI);
				eTemp.timeRemaining = (Math.random() + .5) * eTemp.timeRemaining;
				eTemp.translateTo(hitX, hitY);
				eTemp.loadArray(ents);
				eTemp.rotSpeed = (Math.random() * 70) - 35;
				eTemp.xSpeed = spd * 2 * Math.cos(a + ((Math.random() * .8) - .4)) * ((Math.random() * .5) + .5);
				eTemp.ySpeed = -spd * 2 * Math.sin(a + ((Math.random() * .8) - .4)) * ((Math.random() * .5) + .5);
				if (xSpeed > 0)
					eTemp.xSpeed = -eTemp.xSpeed;
				else
					eTemp.ySpeed = -eTemp.ySpeed;
				eTemp.exitOverlap(e);

				ents.add(eTemp);
			}

		}
	}
}
