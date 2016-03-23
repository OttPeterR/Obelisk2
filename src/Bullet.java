import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Bullet extends PhysicsEntity
{

	public double				damage, speed, maxDistance, trailLength;
	int							numHits, fullNumHits;
	Vect						start, vel;
	Wireframe					longWF;
	boolean						justShot	= false;
	java.util.ArrayList<Entity>	alreadyHit;
	boolean						decay		= false, ricochet = false;
	protected Entity			smoke		= ParticleLibrary.SmokeSmall1();
	ArrayList<Entity>			entsHit;
	ArrayList<Vect>				trail;
	protected int				bulletLength	= 5, bulletThickness = 3;
	boolean						rotateForward	= true;
	int							owner			= -1;

	public Bullet(String n, Image im, String ims, Wireframe wf, Vect wfOff, double t, double mss, double dmg, int numHts, double spd, double dist)
	{
		super(n, im, ims, wf, wfOff, -1, t, mss, 0, 0, 0);
		numHits = numHts;
		fullNumHits = numHts;
		maxDistance = dist;
		damage = dmg;
		speed = spd;
		physicalized = true;
		trailLength = 1500;
		entsHit = new ArrayList<Entity>();
		trail = new ArrayList<Vect>();
		if (wireframes != null)
		{
			Vect[] coords = new Vect[wireframes[0].numCoords * 2];
			for (int i = 0; i < wireframes[0].numCoords; i++)
			{
				coords[i] = wireframes[0].coords[i];
				coords[i + wireframes[0].numCoords] = wireframes[0].coords[i];
			}

			longWF = new Wireframe(coords, wireframes[0].numCoords * 2);

		}
		wireframeColor = Color.orange;
	}

	public void render(java.awt.Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		super.render(g, trans, origX, origY);
		renderTrail(g, origX, origY);
	}
	public void renderTrail(java.awt.Graphics2D g, double origX, double origY)
	{

		if (trail.size() == 0 || Math.hypot((x - trail.get(0).x), (y - trail.get(0).y)) > 2)
			trail.add(0, new Vect(x, y));
		double length = Math.sqrt(((trail.get(0).x - trail.get(trail.size() - 1).x) * (trail.get(0).x - trail.get(trail.size() - 1).x)) + ((trail.get(0).y - trail.get(trail.size() - 1).y) * (trail.get(0).y - trail.get(trail.size() - 1).y)));
		while (length > trailLength * 3)
		{
			trail.remove(trail.size() - 1);
			length = Math.sqrt(((trail.get(0).x - trail.get(trail.size() - 1).x) * (trail.get(0).x - trail.get(trail.size() - 1).x)) + ((trail.get(0).y - trail.get(trail.size() - 1).y) * (trail.get(0).y - trail.get(trail.size() - 1).y)));
		}
		for (int i = 0; i < trail.size() - 1; i += 1)
		{
			int trans = (int) (100 * (double) (trail.size() - i) / (double) trail.size());
			g.setColor(new java.awt.Color(200, 200, 200, (int) trans + 30));

			g.setStroke(new java.awt.BasicStroke((float) (((double) bulletThickness) * ((trail.size() - i) / (double) trail.size()))));
			g.drawLine((int) (trail.get(i).x - origX), (int) (trail.get(i).y - origY), (int) (trail.get(i + 1).x - origX), (int) (trail.get(i + 1).y - origY));
		}
		g.setStroke(new java.awt.BasicStroke(1));

	}
	public void shoot(double frames)
	{
		Vect[] coords = new Vect[wireframes[0].numCoords * 2];
		for (int i = 0; i < wireframes[0].numCoords; i++)
		{
			coords[i] = wireframes[0].coords[i];
			coords[i + wireframes[0].numCoords] = new Vect(wireframes[0].coords[i].x + (xSpeed / (fps)), wireframes[0].coords[i].y + (ySpeed / (fps)));
		}
		longWF = new Wireframe(coords, wireframes[0].numCoords * 2);

		xSpeed = Math.cos(ang) * speed;
		ySpeed = Math.sin(ang) * speed;
		start = new Vect(x, y);
		rotateToward(x + xSpeed, y + ySpeed);
		justShot = true;
		trail.add(new Vect(x, y));
		// step();
	}
	public boolean removeMe()
	{
		return isDead();
	}
	public boolean isDead()
	{
		if (numHits <= 0)
			return true;
		else if (!super.isDead())
		{
			double xDif = start.x - x;
			double yDif = start.y - y;
			return (Math.sqrt(xDif * xDif + yDif * yDif) >= maxDistance);
		}

		else
			return false;
	}
	public void step()
	{
		fps = (1.0E9 / (System.nanoTime() - myTime)) / timeScale;
		if (justShot)
		{
			myTime = System.nanoTime();
			justShot = false;
			return;
		}
		if (entsHit.size() > 0)
		{
			Vect mySpeed = new Vect(xSpeed / fps, ySpeed / fps);
			int numInc = (int) mySpeed.velocity() * 15;
			numInc /= bulletLength;
			if (numInc > 1000)
				numInc = 1000;
			else if (numInc < 1)
				numInc = 1;

			double xInc = mySpeed.x / numInc;
			double yInc = mySpeed.y / numInc;

			double firstX = 0 + x;
			double firstY = 0 + y;

			for (int inc = 0; inc < numInc; inc++)
			{
				for (int i = entsHit.size() - 1; i >= 0; i--)
				{
					if (entsHit.size() <= 0)
						inc = numInc;
					else
					{
						if (!entsHit.get(i).isDead() && checkForHit(entsHit.get(i)))
						{
							addHitParticle(entsHit.get(i), x, y);
							col(entsHit.remove(i));
						}

						if (xInc >= Integer.MIN_VALUE && yInc >= Integer.MIN_VALUE)
							translate(xInc, yInc);
					}
				}
			}
			translateTo(firstX, firstY);
			entsHit.clear();
		}
		if (rotateForward)
			rotateToward(x + xSpeed, y + ySpeed);
		if (!removeMe())
		{
			super.step();
			wireframes[0].rotateToward(x + xSpeed, y + ySpeed);
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
		else
		{
			longWF = new Wireframe(wireframes[0].coords, wireframes[0].numCoords);
			justShot = false;
		}
		myTime = System.nanoTime();
	}
	public boolean checkForHit(Entity e)
	{
		if (!(e instanceof Biped))
			for (int j = 0; j < e.numWF; j++)
			{
				if (wireframes[0].collide(e.wireframes[j]))
				{
					return true;
				}
			}
		else
			return (wireframes[0].collide(((Biped) e).wireframes[0]) || wireframes[0].collide(((Biped) e).head));
		return false;
	}
	public void collide(Entity e)
	{
		if (!e.ghost && !e.intersectStaticOnly && !(e instanceof Bullet) && rect2.intersects(e.rect))
		{
			entsHit.add(e);
		}
	}
	public void col(Entity e)
	{
		if (numHits <= 0)
			return;

		if (e.hitSounds != null && e.hitSounds.size() > 0)
			playSound(e.hitSounds.get((int) (Math.random() * e.hitSounds.size())));

		if (e.physicalized)
		{
			e.xSpeed = ((e.xSpeed * (e.mass - mass) + 2 * mass * xSpeed) / (e.mass + mass));
			e.ySpeed = ((e.ySpeed * (e.mass - mass) + 2 * mass * ySpeed) / (e.mass + mass));
		}
		if (e.ID != owner)
			hurt(e);
		xSpeed /= 1.1;
		ySpeed /= 1.1;

		if (e.penetration == -1)
			numHits = 0;
		if ((ricochet && e.penetration / 2 > numHits))
		{
			noCollide.clear();
			numHits -= e.penetration / 2;
			super.col(e);
			exitOverlap(e);
		}
		else
			numHits -= e.penetration;
		noCollide.add(e);

	}
	private void hurt(Entity e)
	{
		if (e instanceof Biped && ((Biped) e).head.collide(this))
			hurt2(e, damage * 3);
		else
			hurt2(e, damage);

	}
	private void hurt2(Entity e, double d)
	{
		if (!decay)
			e.damage(((double) numHits / (double) fullNumHits) * d);
		else
			e.damage(((double) numHits / (double) fullNumHits) * d * (Math.sqrt(((x - start.x) * (x - start.x)) + ((y - start.y) * (y - start.y))) / maxDistance));
	}
	public Entity getNew()
	{
		Bullet b = new Bullet(name, img[0], imgName[0], wireframes[0].getNew(), wfOffset[0], fullTimeRemaining, mass, damage, fullNumHits, speed, maxDistance);
		b.ricochet = this.ricochet;
		b.trailLength = this.trailLength;
		b.bulletThickness = 0 + this.bulletThickness;
		b.bulletLength = 0 + this.bulletLength;
		b.rotateForward = this.rotateForward;
		b.owner = this.owner;
		b.trail = new ArrayList<Vect>();
		return b;
	}
	public void rotate(double angle)
	{
		ang += angle;
		while (ang > Math.PI * 2)
			ang -= (2 * Math.PI);
		while (ang < 0)
			ang += (2 * Math.PI);

		rotateWF(angle);
		updateWFs();
	}
	public boolean intersects(Entity e)
	{
		boolean r = false;
		if (e instanceof Biped)
		{
			r = (longWF.collide(((Biped) e).wireframes[0]) || longWF.collide(((Biped) e).head));
		}
		else if ((ghost || e.ghost) || (noCollide != null && noCollide.contains(e)) || (e instanceof Bullet) || e instanceof FracLine || e.intersectStaticOnly)
			r = false;
		else if (e != null && e.rect != null && rect2.intersects(e.rect))
		{
			for (int j = 0; j < e.numWF; j++)
			{
				if (longWF.collide(e.wireframes[j]))
				{
					r = true;
				}
			}
		}
		else
			r = false;
		return r;
	}
	public void addSmoke()
	{
		Entity temp = smoke.getNew();
		temp.rotSpeed = Math.random() * 4 - 2;
		temp.translateTo(x, y);
		temp.rotate(Math.random() * Math.PI * 2);
		temp.loadArray(ents);
		ents.add(temp);
	}
	public void addHitParticle(Entity e, double hitX, double hitY)
	{

		if (e.hitParticles != null && e.hitParticles.size() > 0)
		{
			double a = Math.atan(ySpeed / xSpeed);

			double spd = Math.pow(Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed), .65);
			for (int i = 0; i < Math.random() * (mass * 50); i++)
			{
				a += Math.PI;
				Entity eTemp = e.hitParticles.get((int) (Math.random() * e.hitParticles.size())).copy();
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
				eTemp.noCollide.add(this);
				if (Math.random() > .5)
					eTemp.noCollide.add(e);
				eTemp.noCollide.addAll(this.noCollide);
				ents.add(eTemp);
			}

		}
		// Entity temp = sparks.get((int) (Math.random() *
		// sparks.size())).getNew();
		// temp.rotate(Math.random() * Math.PI);
		// temp.translateTo(hitX, hitY);
		//
		// ents.add(temp);
	}
	public void renderWireframes(Graphics2D g, double origX, double origY)
	{
		super.renderWireframes(g, origX, origY);
		longWF.render(g, origX, origY, wireframeColor);
	}
}
