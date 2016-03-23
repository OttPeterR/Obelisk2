import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Biped extends PhysicsEntity
{
	Image				headR, headL, torsoR, torsoL;
	Image				legsStillR, legsStillL, legsRunningR, legsRunningL, legsAirR, legsAirL;
	int					legIndex;
	double				timeUntilNextLeg;
	double				jumpSpeed, walkSpeed, sprintMultiplier;
	boolean				feetPlanted	= false;

	boolean				sprinting, facingRight = false, jumpRequested = false, moveR = false, moveL = false, airborne = false;
	boolean				grounded	= false;
	ArrayList<Entity>	entsHit;
	Wireframe			feet, feetCheck, head, interactArea;
	Vect				feetOffset, feetCheckOffset, headOffset, interactAreaOffset;
	double				lookAng		= 0;

	double				timeUntilHealthRegen, healthRegenTime, healVal = 10;

	public Biped()
	{
		super("", null, "noImage", null, new Vect(0, 0), 100, -1, 180, .2, .2, 100);
		numWF = 1;
	}
	public Biped(String n, Image im, String ims, Wireframe wf, Vect wfOff, double h, double t, double mss, double fric, double b, double p)
	{
		super(n, im, ims, wf, wfOff, h, t, mss, fric, b, p);
		numWF = 1;
	}
	public void construct()
	{
		xSpeed = 0;
		ySpeed = 0;

		gravity = 980;

		ang = 0;
		rotSpeed = 0;
		alpha = 1;
		fade = false;
		noCollide = new java.util.ArrayList<Entity>();
		hitParticles = new java.util.ArrayList<Entity>();
		deathParticles = new java.util.ArrayList<Entity>();

		computeRect();

		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];

		entsHit = new ArrayList<Entity>();
		bounce = .5;
		friction = .0;
		wireframeColor = PROColor;

	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Step\/
	public void translateTo(double inx, double iny)
	{
		x = inx;
		y = iny;
		wireframes[0].translateTo(inx + wfOffset[0].x, iny + wfOffset[0].y);
		head.translateTo(inx + headOffset.x, iny + headOffset.y);
		feet.translateTo(inx + feetOffset.x, iny + feetOffset.y);
		feetCheck.translateTo(inx + feetCheckOffset.x, iny + feetCheckOffset.y);
		interactArea.translateTo(inx + interactAreaOffset.x, iny + interactAreaOffset.y);
		computeRect();
	}
	public void translate(double inx, double iny)
	{
		x += inx;
		y += iny;
		wireframes[0].translate(inx + wfOffset[0].x, iny + wfOffset[0].y);
		head.translate(inx + headOffset.x, iny + headOffset.y);
		feet.translate(inx + feetOffset.x, iny + feetOffset.y);
		feetCheck.translate(inx + feetCheckOffset.x, iny + feetCheckOffset.y);
		interactArea.translate(inx + interactAreaOffset.x, iny + interactAreaOffset.y);
		computeRect();
	}
	public void exitOverlap(Entity e)
	{

		CollisionInfo colInfo = wireframes[0].collideInfo(e.wireframes[0]);
		colInfo.mtd.x = 0;
		colInfo.mtd.y = 0;
		for (int j = 0; j < e.numWF; j++)
		{
			if (wireframes[0].collide(e.wireframes[j]))
				colInfo = wireframes[0].collideInfo(e.wireframes[j]);
		}
		x += colInfo.mtd.x;
		y += colInfo.mtd.y;
		updateWFs();
	}
	public double getAvgHitSlope(Entity e)
	{
		double r = 0;

		ArrayList<Line2D.Double> myLinesHit = new ArrayList<Line2D.Double>();
		ArrayList<Line2D.Double> notMyLinesHit = new ArrayList<Line2D.Double>();

		for (int l = 0; l < e.numWF; l++)
		{
			ArrayList<Line2D.Double> myLines = wireframes[0].getLineWireFrame();
			ArrayList<Line2D.Double> notMyLines = e.wireframes[l].getLineWireFrame();

			for (int i = 0; i < notMyLines.size(); i++)
			{
				for (int j = 0; j < myLines.size(); j++)
				{
					if (((Line2D.Double) myLines.get(j)).intersectsLine((Line2D) notMyLines.get(i)))
					{
						myLinesHit.add((Line2D.Double) myLines.get(j));
						notMyLinesHit.add((Line2D.Double) notMyLines.get(i));

					}
				}
			}
		}

		if (myLinesHit.size() > 0 && notMyLinesHit.size() > 0)
		{

			for (int i = 0; i < notMyLinesHit.size(); i++)
			{
				r += Math.atan((notMyLinesHit.get(i).y2 - notMyLinesHit.get(i).y1) / (notMyLinesHit.get(i).x2 - notMyLinesHit.get(i).x1));
			}
			r /= -notMyLinesHit.size();
		}
		return r;
	}
	public double getLeastHitSlope(Entity e)
	{
		double leastAng = Math.PI / 2;
		for (int l = 0; l < e.numWF; l++)
		{
			ArrayList<Line2D.Double> myLines = wireframes[0].getLineWireFrame();
			myLines.addAll(feet.getLineWireFrame());
			myLines.addAll(feetCheck.getLineWireFrame());
			ArrayList<Line2D.Double> notMyLines = e.wireframes[l].getLineWireFrame();

			for (int i = 0; i < notMyLines.size(); i++)
			{
				for (int j = 0; j < myLines.size(); j++)
				{
					if (((Line2D.Double) myLines.get(j)).intersectsLine((Line2D) notMyLines.get(i)))
					{
						double curAng = Math.atan2(notMyLines.get(i).y2 - notMyLines.get(i).y1, notMyLines.get(i).x2 - notMyLines.get(i).x1);
						if ((Math.abs(curAng) < Math.abs(leastAng)) || (Math.PI - Math.abs(curAng)) > (Math.PI - Math.abs(leastAng)))
							leastAng = curAng;
					}
				}
			}
		}
		return leastAng;
	}
	public void collide(Entity e)
	{
		if (e instanceof Explosive)
			e.collide(this);
		else if ((e.intersectPlayers || e instanceof Weapon) && !e.ghost && !e.intersectStaticOnly && !(e instanceof Bullet && ((Bullet) e).owner == ID) && wireframes[0].collide(e))
			entsHit.add(e);
	}
	public boolean intersects(Entity e)
	{
		boolean r = false;
		try
		{
			if (e instanceof Bullet || e instanceof Explosion || e instanceof FracLine)
			{
				if (e instanceof Bullet)
					r = ((Bullet) e).intersects(this);
				else if (e instanceof Explosion && e.intersects(this))
				{
					r = ((Explosion) e).intersects(this);
					e.collide(this);
				}
				else if (e instanceof FracLine)
					r = ((FracLine) e).intersects(this);
			}
			else if (e instanceof AdhesiveEntity)
			{
				e.collide(this);
			}
			else if (e != null && !e.ghost && !e.intersectStaticOnly && e.intersectPlayers && e.rect != null && rect2.intersects(e.rect))
			{
				for (int j = 0; j < e.numWF; j++)
				{
					if (wireframes[0].collide(e.wireframes[j]) || head.collide(e.wireframes[j]))
						r = true;
				}
			}
			else if (!e.ghost && !e.intersectStaticOnly && feet.collide(e))
			{
				r = true;
			}
			if (r && e instanceof StaticEntity)
				exitOverlap(e);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return r;
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Step/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Movement\/
	protected void moveR(double moveSpeed)
	{
		if (xSpeed < 0 || (xSpeed >= 0 && Math.hypot(xSpeed, ySpeed) < moveSpeed))
			xSpeed += (4 * moveSpeed / fps);
	}
	protected void moveL(double moveSpeed)
	{
		if (xSpeed > 0 || (xSpeed <= 0 && Math.hypot(xSpeed, ySpeed) < moveSpeed))
			xSpeed -= (4 * moveSpeed / fps);
	}
	public void moveRight()
	{
		moveR = true;
	}
	public void moveLeft()
	{
		moveL = true;
	}
	public void jump()
	{
		jumpRequested = true;
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Movement/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Rendering\/
	public void renderRect(Graphics2D g, double origX, double origY)
	{
		g.setColor(wireframeColor);
		rect.setRect(rect.getX() - origX, rect.getY() - origY, rect.getWidth(), rect.getHeight());
		rect2.setRect(rect2.getX() - origX, rect2.getY() - origY, rect2.getWidth(), rect2.getHeight());

		g.draw(rect);
		g.draw(rect2);

		rect.setRect(rect.getX() + origX, rect.getY() + origY, rect.getWidth(), rect.getHeight());
		rect2.setRect(rect2.getX() + origX, rect2.getY() + origY, rect2.getWidth(), rect2.getHeight());
	}
	public void render(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		// //////Legs\/

		if (airborne)
		{
			if (facingRight)
				g.drawImage(legsAirR, (int) (x - origX - 153 / 1.5 + 12), (int) (y - origY - 110), null);
			else
				g.drawImage(legsAirL, (int) (x - origX - 153 / 2 + 12), (int) (y - origY - 110), null);
		}
		else if (Math.abs(xSpeed) > 30)
		{
			timeUntilNextLeg -= (1 / fps);
			if (timeUntilNextLeg <= 0)
			{
				timeUntilNextLeg = Math.abs(15 / xSpeed);

				if (facingRight)
				{
					if (xSpeed < 0)
					{
						legIndex--;
						if (legIndex == -1)
							legIndex = 28;
					}
					else
					{
						legIndex++;
						if (legIndex == 29)
							legIndex = 0;
					}
				}
				else
				{
					if (xSpeed < 0)
					{
						legIndex--;
						if (legIndex == -1)
							legIndex = 28;
					}
					else
					{
						legIndex++;
						if (legIndex == 29)
							legIndex = 0;
					}
				}
			}
			if (facingRight)
				g.drawImage(legsRunningR, (int) (x - origX - 153 / 1.5 + 12), (int) (y - origY - 110), (int) (x - origX - 153 / 1.5 + 12) + 153, (int) (y - origY - 110) + 115, 153 * legIndex, 0, 153 * (legIndex + 1), 116, null);
			else
				g.drawImage(legsRunningL, (int) (x - origX - 153 / 2 + 12), (int) (y - origY - 110), (int) (x - origX - 153 / 2 + 12) + 153, (int) (y - origY - 110) + 115, 153 * legIndex, 0, 153 * (legIndex + 1), 116, null);
		}
		else if (facingRight)
		{
			this.legIndex = 0;
			g.drawImage(legsStillR, (int) (x - origX - legsStillR.getWidth(null) / 2), (int) (y - origY - 110), null);
		}
		else
		{
			this.legIndex = 0;
			g.drawImage(legsStillL, (int) (x - origX - legsStillL.getWidth(null) / 2), (int) (y - origY - 110), null);
		}
		// //////Legs/\
		// //////Body + Arms + Head\/

		if (facingRight)
		{
			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 172);
			trans.rotate((lookAng + Math.PI * 2) / 3 - Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);
		}
		else
		{
			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 172);
			trans.rotate((lookAng + Math.PI) / 3 + 3 * Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);
		}
		if (facingRight)
		{
			g.drawImage(torsoR, (int) (x - origX - torsoR.getWidth(null) / 2), (int) (y - origY - 170), null);

			g.drawImage(headR, trans, null);
		}
		else
		// facing left
		{
			g.drawImage(torsoL, (int) (x - origX - torsoL.getWidth(null) / 2), (int) (y - origY - 170), null);

			g.drawImage(headL, trans, null);
		}
		// //////Body + Arms + Head/\
	}
	public void renderWireframes(Graphics2D g, double origX, double origY)
	{
		g.setStroke(new BasicStroke(1));
		wireframes[0].render(g, origX, origY, wireframeColor);
		feet.render(g, origX, origY, PROColor);
		feetCheck.render(g, origX, origY, PROColor);
		interactArea.render(g, origX, origY, PROColor);
		head.render(g, origX, origY, PROColor);
		g.setColor(Color.green);
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Rendering/\

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Inherited\/
	public void computeRect()
	{
		rect = new Rectangle2D.Double(x + -50, y - 200, 100, 200);
		rect2 = new Rectangle2D.Double(x + -50, y - 200, 100, 200);
	}
	public String toString()
	{
		String s = "";
		s += name;
		return s;
	}
	public Biped getNew()
	{
		Biped d = new Biped();
		d.construct();
		d.name = this.name;

		d.headR = headR;
		d.headL = headL;
		d.torsoR = torsoR;
		d.torsoL = torsoL;
		d.legsStillR = legsStillR;
		d.legsStillL = legsStillL;
		d.legsRunningR = legsRunningR;
		d.legsRunningL = legsRunningL;
		d.legsAirR = this.legsAirR;
		d.legsAirL = this.legsAirL;

		d.head = this.head;
		d.headOffset = new Vect(headOffset.x, headOffset.y);
		d.feet = this.feet.getNew();
		d.feetOffset = new Vect(this.feetOffset.x, this.feetOffset.y);
		d.feetCheck = this.feetCheck.getNew();
		d.feetCheckOffset = this.feetCheckOffset;
		d.interactArea = this.interactArea.getNew();
		d.interactAreaOffset = new Vect(this.interactAreaOffset.x, this.interactAreaOffset.y);

		d.health = 0 + this.fullHealth;
		d.fullHealth = 0 + this.fullHealth;
		d.healVal = this.healVal;
		Wireframe[] wf = new Wireframe[numWF];
		Vect[] wfo = new Vect[numWF];
		for (int i = 0; i < numWF; i++)
		{
			wf[i] = wireframes[i].getNew();
			wfo[i] = new Vect(0 + wfOffset[i].x, 0 + wfOffset[i].y);
		}
		d.walkSpeed = this.walkSpeed;
		d.sprintMultiplier = this.sprintMultiplier;
		d.jumpSpeed = this.jumpSpeed;
		d.wireframes = wf;

		d.wfOffset = wfo;
		d.damageThreshold = this.damageThreshold;
		d.img = img;
		d.imgName = imgName;

		return d;
	}
	public Entity copy()
	{
		return null;
	}
	public void damage(double d)
	{
		super.damage(d);
		timeUntilHealthRegen += (d / 5);
	}
	public boolean removeMe()
	{
		if (health <= 0)
		{
			addDeathParticles();
			return true;
		}
		return false;
	}
	public void addDeathParticles()
	{
		final ArrayList<Entity> newEnts = new ArrayList<Entity>();
		double radius = 50;
		if (hitParticles != null && hitParticles.size() > 0)
		{
			for (int i = 0; i < particlesToAddOnDeath; i++)
			{
				double angle = 2 * Math.PI * i / ((double) (particlesToAddOnDeath));
				PhysicsEntity p = (PhysicsEntity) hitParticles.get((int) (Math.random() * hitParticles.size())).getNew();
				p.translateTo(x + Math.cos(angle) * radius * Math.random(), y - 100 + Math.sin(angle) * radius * Math.random());
				boolean prevIntersect = p.intersectStaticOnly;
				boolean prevIntersectPlayers = p.intersectPlayers;
				p.intersectPlayers = true;
				p.intersectStaticOnly = false;
				int tries = 0;
				while (wireframes[0].collide(p) && tries < 20)
				{
					p.translateTo(x + Math.cos(Math.random() * 2 * Math.PI) * radius * Math.random(), y - 100 + Math.sin(Math.random() * 2 * Math.PI) * radius * Math.random());
					tries++;
				}
				p.intersectStaticOnly = prevIntersect;
				p.intersectPlayers = prevIntersectPlayers;
				p.rotate(Math.random() * Math.PI);
				p.xSpeed = 0 + xSpeed;
				p.ySpeed = ySpeed;
				if (p.intersectStaticOnly && !p.ghost)
				{
					if (p.mass < 5)
						p.rotSpeed = (Math.random() * 140) - 70;
					else
						p.rotSpeed = (Math.random() * 70) - 35;
					p.xSpeed += 400 * Math.cos(Math.random() * Math.PI);
					p.ySpeed += 400 * Math.sin(Math.random() * Math.PI);
				}
				p.xSpeed -= (p.xSpeed * (p.speedDampening / 3));
				p.ySpeed -= (p.ySpeed * (p.speedDampening / 3));
				p.gravity = gravity;
				p.timeRemaining = ((Math.random() * .5) + .7) * p.timeRemaining;
				newEnts.add(p);
			}
		}
		if (deathParticles != null && deathParticles.size() > 0)
		{
			for (int i = 0; i < deathParticles.size(); i++)
			{
				if (deathParticles.get(i) instanceof PhysicsEntity)
				{
					double angle = 2 * Math.PI * (i + .25) / ((double) (particlesToAddOnDeath));
					PhysicsEntity p = (PhysicsEntity) deathParticles.get(i).getNew();
					p.translateTo(x + Math.cos(angle) * radius * Math.random(), y - 100 + Math.sin(angle) * radius * Math.random());
					if (deathParticles.get(i).name.toLowerCase().contains("zombie"))
					{
						if (deathParticles.get(i).name.toLowerCase().contains("head"))
							p.translateTo(head.xCenter, head.yCenter);
						else if (deathParticles.get(i).name.toLowerCase().contains("body"))
							p.translateTo(wireframes[0].xCenter, wireframes[0].yCenter);
						else if (deathParticles.get(i).name.toLowerCase().contains("arm"))
							p.translateTo(wireframes[0].xCenter + ((Math.random() - .5) * 80), wireframes[0].yCenter + ((Math.random() - .5) * 80));
						else if (deathParticles.get(i).name.toLowerCase().contains("organ"))
							p.translateTo(wireframes[0].xCenter + ((Math.random() - .5) * 80), wireframes[0].yCenter + ((Math.random() - .5) * 80));

					}
					else
					{
						boolean prevIntersect = p.intersectStaticOnly;
						boolean prevIntersectPlayers = p.intersectPlayers;
						p.intersectPlayers = true;
						p.intersectStaticOnly = false;
						int tries = 0;
						while (!wireframes[0].collide(p) && tries < 20)
						{
							p.translateTo(x + Math.cos(Math.random() * 2 * Math.PI) * radius * Math.random(), y - 100 + Math.sin(Math.random() * 2 * Math.PI) * radius * Math.random());
							tries++;
						}
						p.intersectStaticOnly = prevIntersect;
						p.intersectPlayers = prevIntersectPlayers;
					}
					p.rotate(Math.random() * Math.PI);
					p.xSpeed = xSpeed;
					p.ySpeed = ySpeed;
					p.xSpeed -= (p.xSpeed * p.speedDampening);
					p.gravity = gravity;
					if (p.intersectStaticOnly && !p.ghost)
					{
						p.rotSpeed = (Math.random() * 20) - 10;
						p.xSpeed += 400 * Math.cos(Math.random() * Math.PI);
						p.ySpeed += 400 * Math.sin(Math.random() * Math.PI);
					}
					p.timeRemaining = ((Math.random() * .5) + .5) * p.timeRemaining;
					newEnts.add(p);
				}
				else if (deathParticles.get(i) instanceof Explosion)
				{
					Explosion p = ((Explosion) deathParticles.get(i));
					p.translateTo(x, y);
					p.xSpeed = xSpeed;
					p.ySpeed = ySpeed;
					newEnts.add(p);
				}
			}
		}
		new Thread(new Runnable()
		{
			public void run()
			{
				ents.addAll(newEnts);
			}
		}).start();
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Inherited/\

}
