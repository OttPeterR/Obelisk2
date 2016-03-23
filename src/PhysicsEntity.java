import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class PhysicsEntity extends Entity
{

	public PhysicsEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect wfOff, double h, double t, double mss, double fric, double b, double p)
	{
		super(n, im, ims, wf, wfOff);
		physicalized = true;
		penetration = p;
		fullHealth = h;
		health = h;
		fullTimeRemaining = t;
		timeRemaining = t;
		mass = mss;
		friction = fric;
		bounce = b;
		wireframeColor = Color.cyan;
	}
	public PhysicsEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect wfOff, double h, double t, double mss, double fric, double b, boolean nc)
	{
		super(n, im, ims, wf, wfOff);
		physicalized = true;

		fullHealth = h;
		health = h;
		fullTimeRemaining = t;
		timeRemaining = t;
		mass = mss;
		friction = fric;
		bounce = b;
		ghost = nc;
		wireframeColor = Color.cyan;

	}
	public PhysicsEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect wfOff, double h, double t, double mss, double fric, double b, float transp, boolean nc)
	{
		super(n, im, ims, wf, wfOff);
		physicalized = true;

		fullHealth = h;
		health = h;
		fullTimeRemaining = t;
		timeRemaining = t;
		mass = mss;
		friction = fric;
		bounce = b;
		ghost = nc;
		alpha = transp;
		wireframeColor = Color.cyan;

	}
	public PhysicsEntity(String n, java.awt.Image im, String ims, Wireframe[] wfs, Vect[] wfOff, int nWF, double h, double t, double mss, double fric, double b)
	{
		super(n, im, ims, wfs, wfOff, nWF);
		physicalized = true;

		fullHealth = h;
		health = h;
		fullTimeRemaining = t;
		timeRemaining = t;
		mass = mss;
		friction = fric;
		bounce = b;
		wireframeColor = Color.cyan;

	}
	public PhysicsEntity(String n, java.awt.Image[] im, String[] ims, Vect[] imOff, int imN, Wireframe[] wfs, Vect[] wfOff, int nWF, double h, double t, double mss, double fric, double b)
	{
		super(n, im, ims, imOff, imN, wfs, wfOff, nWF);
		physicalized = true;

		fullHealth = h;
		health = h;
		fullTimeRemaining = t;
		timeRemaining = t;
		mass = mss;
		friction = fric;
		bounce = b;
		wireframeColor = Color.cyan;

	}
	public PhysicsEntity(String n, java.awt.Image[] im, String[] ims, Wireframe wf, Vect wfoff, double mss, double fric, double b)
	{// for weapons only
		super(n, im, ims, wf, wfoff);
		mass = mss;
		friction = fric;
		bounce = b;
		timeRemaining = -1;
		fullTimeRemaining = -1;
		health = 1000;
		fullHealth = 1000;
		physicalized = true;
		wireframeColor = Color.cyan;

	}
	public PhysicsEntity(String n, java.awt.Image[] im, String[] ims, Wireframe wf, Vect wfoff, double t, double h, double mss, double fric, double b)
	{// for weapons only
		super(n, im, ims, wf, wfoff);
		mass = mss;
		friction = fric;
		bounce = b;
		timeRemaining = t;
		fullTimeRemaining = 0 + t;
		health = h;
		fullHealth = 0 + h;
		physicalized = true;
		wireframeColor = Color.cyan;

	}
	public Entity getNew()
	{
		Wireframe[] wf = new Wireframe[numWF];
		Vect[] wfo = new Vect[numWF];
		java.awt.Image imgs[] = new java.awt.Image[numImg];
		Vect[] imoff = new Vect[numImg];

		for (int i = 0; i < numWF; i++)
		{
			wf[i] = wireframes[i].getNew();
			wfo[i] = new Vect(0 + wfOffset[i].x, 0 + wfOffset[i].y);
		}
		for (int i = 0; i < numImg; i++)
		{
			imgs[i] = img[i];
			imoff[i] = new Vect(0 + imgOffset[i].x, 0 + imgOffset[i].y);
		}
		PhysicsEntity r = new PhysicsEntity(name, img, imgName, imgOffset, numImg, wf, wfo, numWF, fullHealth, fullTimeRemaining, mass, friction, bounce);
		for (int i = 0; i < numWF; i++)
		{
			r.wfOffset[i] = new Vect(wfOffset[i].x, wfOffset[i].y);
			r.wfRad[i] = 0 + wfRad[i];
		}
		r.x = 0 + x;
		r.y = 0 + y;
		r.physicalized = physicalized;
		r.particlesToAddOnDeath = this.particlesToAddOnDeath;
		r.intersectStaticOnly = this.intersectStaticOnly;
		r.intersectPlayers = this.intersectPlayers;
		r.penetration = 0 + this.penetration;
		r.pickUpable = this.pickUpable;
		r.ghost = this.ghost;
		r.speedDampening = this.speedDampening;
		r.alpha = this.alpha;
		r.gravity = this.gravity;
		r.fade = this.fade;
		r.damageThreshold = this.damageThreshold;
		r.hitParticles = this.hitParticles;
		r.ID = 0 + ID;
		r.deathParticles = this.deathParticles;
		r.wireframeColor = this.wireframeColor;

		return r;
	}
	public boolean intersects(Entity e)
	{
		if (e == null)
			return false;
		if (e instanceof FracLine || e instanceof Bullet || e instanceof Explosion)
			return e.intersects(this);
		else if ((ghost || e.ghost)
				|| (noCollide != null && noCollide.contains(e))
				|| (this instanceof Bullet && e instanceof Bullet)
				|| noCollide.contains(e) ||
				e.noCollide.contains(this)
				|| e.intersectStaticOnly ||
				(intersectStaticOnly && !(e instanceof StaticEntity)))
			return false;

		else
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
	}
	public void collide(Entity e)
	{
		if (e instanceof Biped || (e instanceof Bullet && !(this instanceof Bullet)) || e instanceof Explosion || e instanceof Explosive || e instanceof FracLine)
		{
			e.collide(this);
			return;
		}

		double tempXSpeed = 0 + xSpeed;
		double tempYSpeed = 0 + ySpeed;
		double tempRotSpeed = 0 + rotSpeed;
		col(e);

		double newXSpeed = 0 + xSpeed;
		double newYSpeed = 0 + ySpeed;
		double newRotSpeed = 0 + rotSpeed;

		xSpeed = 0 + tempXSpeed;
		ySpeed = 0 + tempYSpeed;
		rotSpeed = 0 + tempRotSpeed;
		e.col(this);
		xSpeed = 0 + newXSpeed;
		ySpeed = 0 + newYSpeed;
		rotSpeed = 0 + newRotSpeed;
	}
	public void col(Entity e)
	{
		if (!(e instanceof PhysicsEntity) || Math.hypot(xSpeed, ySpeed) > Math.hypot(e.xSpeed, e.ySpeed))
			exitOverlap(e);
		else
			e.exitOverlap(this);

		// yeah bro, thats a quad for loop \/
		ArrayList<Line2D.Double> thisLinesHit = new ArrayList<Line2D.Double>();
		ArrayList<Line2D.Double> eLinesHit = new ArrayList<Line2D.Double>();
		boolean aligned = false;
		double colX = 0;
		double colY = 0;
		// double thisNormal = 0;
		double eNormal = 0;

		for (int k = 0; k < numWF; k++)
		{
			for (int l = 0; l < e.numWF; l++)
			{
				ArrayList<Line2D.Double> thisLines = wireframes[k].getLineWireFrame();
				ArrayList<Line2D.Double> eLines = e.wireframes[l].getLineWireFrame();

				for (int i = 0; i < eLines.size(); i++)
				{
					for (int j = 0; j < thisLines.size(); j++)
					{
						if (!aligned && ((Line2D.Double) thisLines.get(j)).intersectsLine((Line2D) eLines.get(i)))
						{

							thisLinesHit.add((Line2D.Double) thisLines.get(j));

							eLinesHit.add((Line2D.Double) eLines.get(i));

							double thisAng = Math.atan2(thisLines.get(j).getX2() - thisLines.get(j).getX1(), thisLines.get(j).getY2() - thisLines.get(j).getY1());
							double eAng = Math.atan2(eLines.get(i).getX2() - eLines.get(i).getX1(), eLines.get(i).getY2() - eLines.get(i).getY1());
							double diff = Math.abs(thisAng - eAng);

							// thisNormal = thisAng + (Math.PI / 2);
							eNormal = eAng + (Math.PI / 2);

							if (diff < Math.PI * .015 || (diff < Math.PI * 1.015 && diff > Math.PI * .985))
							{// flat collisions

								aligned = true;
								double o1 = 0;
								double o2 = Math.hypot(eLines.get(i).getX1() - eLines.get(i).getX2(), eLines.get(i).getY1() - eLines.get(i).getY2());
								if (eLines.get(i).getX2() < eLines.get(i).getX1())
									o2 = -o2;
								double p1 = Math.hypot(eLines.get(i).getX1() - thisLines.get(j).getX1(), thisLines.get(j).getY1() - eLines.get(i).getY1());
								if (thisLines.get(j).getX1() < eLines.get(i).getX1())
									p1 = -p1;
								double p2 = Math.hypot(eLines.get(i).getX1() - thisLines.get(j).getX2(), thisLines.get(j).getY1() - eLines.get(i).getY2());
								if (thisLines.get(j).getX2() < eLines.get(i).getX1())
									p2 = -p2;
								double pd = Math.hypot(thisLines.get(j).getX1() - thisLines.get(j).getX2(), thisLines.get(j).getY1() - thisLines.get(j).getY2());
								boolean computed = false;

								if (Math.abs(o2) > pd)
								{
									if ((o1 < p1 && o1 < p2 && o2 > p1 && o2 > p2) || (o1 > p1 && o1 > p2 && o2 < p1 && o2 < p2))
									{
										colX = (thisLines.get(j).getX1() + thisLines.get(j).getX2()) / 2;
										colY = (thisLines.get(j).getY1() + thisLines.get(j).getY2()) / 2;
										computed = true;
									}
								}
								else if ((p1 <= o2 && p1 <= o1 && p2 >= o2 && p2 >= o1) || (p1 >= o2 && p1 >= o1 && p2 <= o2 && p2 <= o1))
								{
									colX = (eLines.get(i).getX1() + eLines.get(i).getX2()) / 2;
									colY = (eLines.get(i).getY1() + eLines.get(i).getY2()) / 2;
									computed = true;
								}
								if (!computed)
								{
									if ((o2 < p1 && p1 < o1 && o1 < p2) || (p2 < o1 && o1 < p1 && p1 < o2))
									{
										// p1 o1
										colX = (thisLines.get(j).getX1() + eLines.get(i).getX1()) / 2;
										colY = (thisLines.get(j).getY1() + eLines.get(i).getY1()) / 2;
									}
									else if ((o2 < p2 && p2 < o1 && o1 < p1) || (p1 < o1 && o1 < p2 && p2 < o2))
									{
										// p2 o1
										colX = (thisLines.get(j).getX2() + eLines.get(i).getX1()) / 2;
										colY = (thisLines.get(j).getY2() + eLines.get(i).getY1()) / 2;
									}
									else if ((o1 < p1 && p1 < o2 && o2 < p2) || (p2 < o2 && o2 < p1 && p1 < o1))
									{
										// p1 o2
										colX = (thisLines.get(j).getX1() + eLines.get(i).getX2()) / 2;
										colY = (thisLines.get(j).getY1() + eLines.get(i).getY2()) / 2;
									}
									else if ((o1 < p2 && p2 < o2 && o2 < p1) || (p1 < o2 && o2 < p2 && p2 < o1))
									{
										// p2 o2
										colX = (thisLines.get(j).getX2() + eLines.get(i).getX2()) / 2;
										colY = (thisLines.get(j).getY2() + eLines.get(i).getY2()) / 2;
									}

								}
							}
						}
					}
				}
			}

		}
		if (thisLinesHit.size() > 0 && eLinesHit.size() > 0)
		{

			if (!aligned)
			{
				double eAvgAng = 0;
				for (int i = 0; i < eLinesHit.size(); i++)
				{
					eAvgAng += Math.atan((eLinesHit.get(i).y2 - eLinesHit.get(i).y1) / (eLinesHit.get(i).x2 - eLinesHit.get(i).x1));
				}
				eAvgAng /= -eLinesHit.size();
				eNormal = (eAvgAng) + Math.PI / 2;

				// double thisAvgAng = 0;
				// for (int i = 0; i < thisLinesHit.size(); i++)
				// {
				// thisAvgAng += Math.atan((thisLinesHit.get(i).y2 -
				// thisLinesHit.get(i).y1) / (thisLinesHit.get(i).x2 -
				// thisLinesHit.get(i).x1));
				// }
				// thisAvgAng /= -thisLinesHit.size();
				// thisNormal = thisAvgAng + (Math.PI / 2);

				{// rotation

					double numIntersects = 0;
					for (int i = 0; i < thisLinesHit.size(); i++)
					{
						Line2D.Double thisLine = thisLinesHit.get(i);
						double x1 = thisLine.getX1();
						double y1 = thisLine.getY1();
						double x2 = thisLine.getX2();
						double y2 = thisLine.getY2();

						for (int j = 0; j < eLinesHit.size(); j++)
						{

							Line2D.Double line = eLinesHit.get(j);

							double x3 = line.getX1();
							double y3 = line.getY1();
							double x4 = line.getX2();
							double y4 = line.getY2();

							double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

							if (d != 0)
							{
								numIntersects++;
								colX += ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
								colY += ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
							}
						}
					}
					colX /= numIntersects;
					colY /= numIntersects;
					// end rotation
				}

			}

			if (colX >= Integer.MIN_VALUE && colY >= Integer.MIN_VALUE)
			{
				if (e instanceof StaticEntity)
				{
					Vect thisVel = new Vect(xSpeed, ySpeed);

					double thisVelAngle = Math.atan2(ySpeed, xSpeed);
					double eFaceAngle = eNormal + (Math.PI / 2);

					Vect ThisAlignedVel = new Vect(0, 0);
					ThisAlignedVel.x = thisVel.velocity() * Math.cos(eFaceAngle + thisVelAngle);// parallel
					ThisAlignedVel.y = thisVel.velocity() * Math.sin(eFaceAngle + thisVelAngle);// perpendicular

					ThisAlignedVel.x -= (ThisAlignedVel.x * (1 - ((this.friction + e.friction) / 2))) / fps;
					ThisAlignedVel.y *= (this.bounce + e.bounce) / 2;

					// TODO the below is broken but functional for now
					double thisResultAngle = eFaceAngle - thisVelAngle;

					xSpeed = -ThisAlignedVel.velocity() * Math.cos(thisResultAngle);
					ySpeed = -ThisAlignedVel.velocity() * Math.sin(thisResultAngle);
				}
				else
				{
					double eRadius = Math.hypot(colX - e.x, colY - e.y);
					double eHitAngle = Math.atan(-(colY - e.y) / (colX - e.x));
					if (e.x > colX)
						eHitAngle += Math.PI;
					Vect eVel = new Vect(e.xSpeed, e.ySpeed);
					Vect eRotationalVel = new Vect(eRadius * e.rotSpeed * Math.sin(eHitAngle), eRadius * e.rotSpeed * Math.cos(eHitAngle));
					eVel.x += eRotationalVel.x;
					eVel.y += eRotationalVel.y;

					double thisRadius = Math.hypot(colX - this.x, colY - this.y);
					double thisHitAngle = Math.atan(-(colY - this.y) / (colX - this.x));
					if (this.x > colX)
						thisHitAngle += Math.PI;
					Vect thisVel = new Vect(this.xSpeed, this.ySpeed);
					Vect thisRotationalVel = new Vect(thisRadius * this.rotSpeed * Math.sin(thisHitAngle), thisRadius * this.rotSpeed * Math.cos(thisHitAngle));
					thisVel.x += thisRotationalVel.x;
					thisVel.y += thisRotationalVel.y;

					double masses = this.mass + e.mass;

					Vect eVelFinal = new Vect(0, 0);// this is at the point of
													// collision (rotational
													// velocity is included)
					eVelFinal.x = (eVel.x * ((e.mass - this.mass) / masses)) + (thisVel.x * ((2 * this.mass) / masses));
					eVelFinal.y = (eVel.y * ((e.mass - this.mass) / masses)) + (thisVel.y * ((2 * this.mass) / masses));

					e.xSpeed = eVelFinal.x;
					e.ySpeed = eVelFinal.y;

					Vect thisVelFinal = new Vect(0, 0);// this is at the point
														// of collision
														// (rotational velocity
														// is included)
					thisVelFinal.x = (thisVel.x * ((this.mass - e.mass) / masses)) + (eVel.x * ((2 * e.mass) / masses));
					thisVelFinal.y = (thisVel.y * ((this.mass - e.mass) / masses)) + (eVel.y * ((2 * e.mass) / masses));

					// TODO take out the rotational component from the VelFinals
					// and then apply them
					eRotationalVel.x *= e.mass;
					eRotationalVel.y *= e.mass;

					thisRotationalVel.x *= mass;
					thisRotationalVel.y *= mass;

					computeRotation(eRotationalVel, thisRotationalVel, new Vect(colX, colY), thisRadius);
					this.xSpeed = thisVelFinal.x;
					this.ySpeed = thisVelFinal.y;
				}
			}

		}
	}
	private void computeRotation(Vect eRotVel, Vect myRotVel, Vect col, double rad)
	{
		Vect rForce = new Vect(myRotVel.x - eRotVel.x, myRotVel.y - eRotVel.y);
		double f = rForce.velocity();
		if (rForce.x > 0)
		{
			if (rForce.y > 0)
			{
				if (col.x > x)
					clockWise(f, rad);
				else
					counterClockWise(f, rad);
			}
			else if (rForce.y < 0)
			{
				if (col.x > x)
					counterClockWise(f, rad);
				else
					clockWise(f, rad);
			}
			else
			{
				if (col.y < y)
					clockWise(f, rad);
				else
					counterClockWise(f, rad);
			}
		}
		else if (rForce.x < 0)
		{
			if (rForce.y > 0)
			{
				if (col.x > x)
					clockWise(f, rad);
				else
					counterClockWise(f, rad);
			}
			else if (rForce.y < 0)
			{
				if (col.x > x)
					counterClockWise(f, rad);
				else
					clockWise(f, rad);
			}
			else
			{
				if (col.y > y)
					clockWise(f, rad);
				else
					counterClockWise(f, rad);
			}
		}
		else
		{
			if (rForce.y > 0)
			{
				if (col.x > x)
					clockWise(f, rad);
				else
					counterClockWise(f, rad);
			}
			else
			{
				if (col.x > x)
					counterClockWise(f, rad);
				else
					clockWise(f, rad);
			}
		}
	}
	private void clockWise(double f, double rad)
	{
		f = Math.abs(f);
		rotSpeed += f / ((mass * rad));
	}
	private void counterClockWise(double f, double rad)
	{
		f = -Math.abs(f);
		rotSpeed += f / ((mass * rad));
	}
	public String toString()
	{
		return "PhysicsEntity " + super.toString();
	}

}
