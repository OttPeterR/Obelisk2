import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class Entity
{
	static final Color	PROColor		= new Color(17, 93, 118);
	static final Color	PROColorBlaze	= new Color(221, 102, 6);
	static final Color	PROGreen		= new Color(21, 129, 55);
	double				x, y;
	double				xSpeed, ySpeed, gravity;
	boolean				zeroG			= false;
	double				timeScale;
	double				speedDampening;

	double				health, fullHealth, timeRemaining, fullTimeRemaining, damageThreshold;
	boolean				physicalized;

	double				myTime, fps;
	boolean				paused;
	boolean				ghost;
	boolean				intersectStaticOnly;
	boolean				intersectPlayers;
	boolean				pickUpable;

	double				mass, friction, bounce;
	double				penetration;

	double				ang, rotSpeed;

	// boolean threaded = false;

	float				alpha			= 1;
	boolean				fade;
	// AffineTransform trans = new AffineTransform();

	String				name;
	int					ID;
	String[]			imgName;

	Wireframe[]			wireframes;
	int					numWF, numImg;
	double[]			wfAng, wfRad;
	Vect[]				wfOffset, imgOffset;

	Color				wireframeColor;

	Rectangle2D			rect, rect2;

	Image[]				img;

	ArrayList<Entity>	noCollide;
	ArrayList<Entity>	ents;
	ArrayList<Entity>	hitParticles;
	ArrayList<Entity>	deathParticles;
	ArrayList<String>	hitSounds;

	int					particlesToAddOnDeath;

	public Entity(String n, Image im, String ims, Wireframe wf)
	{
		if (im == null)
			return;
		name = n;
		numWF = 1;
		numImg = 1;

		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];
		img = new Image[numImg];
		imgName = new String[numImg];
		imgOffset = new Vect[numImg];

		img[0] = im;
		imgName[0] = ims;
		imgOffset[0] = new Vect(0, 0);

		wireframes[0] = wf;
		wfOffset[0] = new Vect(0, 0);
		wfAng[0] = 0;
		wfRad[0] = 0;

		construct();

	}
	public Entity(String n, Image im, String ims, Wireframe wf, Vect wfOff)
	{
		if (im == null)
			return;
		name = n;
		numWF = 1;
		numImg = 1;

		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];
		img = new Image[numImg];
		imgName = new String[numImg];
		imgOffset = new Vect[numImg];

		img[0] = im;
		imgName[0] = ims;
		imgOffset[0] = new Vect(0, 0);

		wireframes[0] = wf;
		wfOffset[0] = wfOff;

		construct();
	}
	public Entity(String n, Image im, String ims, Wireframe[] wfs, Vect[] wfOff, int nWF)
	{
		if (im == null)
			return;
		name = n;
		numWF = nWF;
		numImg = 1;

		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];
		img = new Image[numImg];
		imgName = new String[numImg];
		imgOffset = new Vect[numImg];

		img[0] = im;
		imgName[0] = ims;
		imgOffset[0] = new Vect(0, 0);

		wireframes = wfs;
		wfOffset = wfOff;

		construct();
	}
	public Entity(String n, Image[] im, String[] ims, Wireframe wf, Vect wfOff)
	{
		if (im == null)
			return;
		name = n;
		numWF = 1;
		numImg = 1;

		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];
		img = new Image[numImg];
		imgName = new String[numImg];
		imgOffset = new Vect[2];

		img = im;
		imgName = ims;
		imgOffset[0] = new Vect(0, 0);
		imgOffset[1] = new Vect(0, 0);

		wireframes[0] = wf;
		wfOffset[0] = wfOff;

		construct();
	}
	public Entity(String n, Image[] im, String[] ims, Vect[] imo, int imN, Wireframe[] wfs, Vect[] wfOff, int nWF)
	{
		if (im == null)
			return;
		name = n;
		numWF = nWF;
		numImg = imN;

		wireframes = new Wireframe[numWF];
		wfOffset = new Vect[numWF];
		wfAng = new double[numWF];
		wfRad = new double[numWF];
		img = new Image[numImg];
		imgName = new String[numImg];
		imgOffset = new Vect[numImg];

		img = im;
		imgName = ims;
		imgOffset = imo;

		wireframes = wfs;
		wfOffset = wfOff;

		construct();
	}
	public void construct()
	{
		ID = -1;

		xSpeed = 0;
		ySpeed = 0;
		speedDampening = 0;

		gravity = 980;
		zeroG = false;

		health = -1;
		fullHealth = -1;
		fullTimeRemaining = -1;
		timeRemaining = -1;

		ang = 0;
		rotSpeed = 0;
		alpha = 1;
		fade = false;
		timeScale = 1;
		paused = true;
		ghost = false;
		intersectPlayers = true;
		intersectStaticOnly = false;
		pickUpable = false;

		ents = new ArrayList<Entity>();
		noCollide = new ArrayList<Entity>();
		hitParticles = new ArrayList<Entity>();
		deathParticles = new ArrayList<Entity>();
		hitSounds = new ArrayList<String>();

		mass = -1;
		friction = .1;
		bounce = .5;
		physicalized = false;

		for (int i = 0; i < numWF; i++)
		{
			if (wfOffset[i] == null)
				wfOffset[i] = new Vect(0, 0);
			wfAng[i] = Math.atan(wfOffset[i].y / (wfOffset[i].x + 1E-12));
			wfRad[i] = wfOffset[i].velocity();
			if (wfOffset[i].x < 0)
				wfAng[i] += Math.PI;
		}
		wireframeColor = Color.green;
		computeRect();
	}
	public boolean equals(Object e)
	{
		if (e instanceof Entity)
			return ID == ((Entity) e).ID;
		return false;
	}
	public abstract Entity getNew();
	public Entity copy()
	{
		Entity r = this.getNew();
		r.img = this.img;
		r.health = 0 + health;
		r.timeRemaining = 0 + timeRemaining;
		r.paused = paused;
		r.ID = 0 + ID;
		r.xSpeed = 0 + xSpeed;
		r.ySpeed = 0 + ySpeed;
		r.gravity = 0 + gravity;
		r.penetration = 0 + penetration;
		r.speedDampening = 0 + speedDampening;
		r.ang = 0 + ang;
		r.rotSpeed = 0 + rotSpeed;
		r.alpha = 0 + alpha;
		r.rotate(0);
		r.pickUpable = this.pickUpable;
		r.particlesToAddOnDeath = particlesToAddOnDeath;
		r.intersectPlayers = intersectPlayers;
		r.translateTo(r.x, r.y);
		r.loadArray(ents);
		return r;
	}
	public String saveString()
	{
		String s = "[ " + name + " " + health + " " + x + " " + y + " " + ang + " " + xSpeed + " " + ySpeed + " " + rotSpeed + " " + gravity + " " + ghost + " " + penetration + " " + damageThreshold;
		return s + " ]";
	}
	public String toString()
	{
		String s = "" + name + " " + numImg + " ";

		s += toString(imgOffset);
		s += toString(wfOffset);
		s += toString(wfAng);
		s += toString(wfRad);
		s += " " + x + " " + y + " " + xSpeed + " " + ySpeed + " " + ang + " " + rotSpeed + " " + speedDampening + " " + mass + " " + friction + " " + bounce + " " + penetration + " " + health + " " + fullHealth + " " + damageThreshold + " " + timeRemaining + " " + fullTimeRemaining + " " + physicalized + " " + ghost + " " + intersectStaticOnly + " " + intersectPlayers + " ";
		if (noCollide.size() == 0)
			s += "noCollideEmpty";
		else
			s += toString(noCollide);
		if (hitParticles.size() == 0)
			s += "hitParticlesEmpty";
		else
			s += toString(hitParticles);
		if (deathParticles.size() == 0)
			s += "deathParticlesEmpty";
		else
			s += toString(deathParticles);

		s += " " + particlesToAddOnDeath + " ";
		s += hitSounds;

		return s;
	}
	public String toString(Object[] o)
	{
		String s = " [ ";
		for (int i = 0; i < o.length; i++)
		{
			s += o[i].toString();
			if (i < o.length - 2)
				s += " , ";
		}
		s += " ]";
		return s;
	}
	public String toString(double[] o)
	{
		String s = " [ ";
		for (int i = 0; i < o.length; i++)
		{
			s += o[i];
			if (i < o.length - 2)
				s += " , ";
		}
		s += " ]";
		return s;
	}
	public String toString(ArrayList<Entity> arr)
	{
		String s = " { ";
		for (int i = 0; i < arr.size(); i++)
		{
			s += arr.get(i).toString();
			if (i < arr.size() - 2)
				s += " , ";
		}
		s += " } ";
		return s;
	}
	public void computeRect()
	{
		double maxX = wireframes[0].coords[0].x;
		double minX = wireframes[0].coords[0].x;
		double minY = wireframes[0].coords[0].y;
		double maxY = wireframes[0].coords[0].y;

		for (int i = 0; i < numWF; i++)
		{
			for (int j = 0; j < wireframes[i].numCoords; j++)
			{
				maxX = Math.max(maxX, wireframes[i].coords[j].x);
				minX = Math.min(minX, wireframes[i].coords[j].x);

				maxY = Math.max(maxY, wireframes[i].coords[j].y);
				minY = Math.min(minY, wireframes[i].coords[j].y);
			}
		}
		if (fps < 1)
		{
			rect = new Rectangle2D.Double(minX, minY, (maxX - minX), (maxY - minY));
			rect2 = (Rectangle2D) rect.clone();
		}
		else
		{
			rect = new Rectangle2D.Double(minX, minY, (maxX - minX), (maxY - minY));
			Rectangle2D.Double temp = new Rectangle2D.Double(minX + (xSpeed / fps), minY + (ySpeed / fps), (maxX - minX), (maxY - minY));
			rect2 = rect.createUnion(temp);
		}
	}
	public void render(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		if (fade && timeRemaining > 0 && fullTimeRemaining > 0 && (timeRemaining / fullTimeRemaining) < .25)
		{
			float newAlpha = (float) (alpha * (timeRemaining / (fullTimeRemaining * .25)));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (newAlpha)));
		}
		else
		{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (alpha)));
		}
		if (timeRemaining == -1 || timeRemaining > .0001)
		{
			for (int i = 0; i < numImg; i++)
			{
				if (img[i] != null)
				{
					if (ang == 0)
					{
						g.drawImage(img[i], (int) ((x - origX + imgOffset[i].x) - img[i].getWidth(null) / 2), (int) ((y - origY + imgOffset[i].y) - img[i].getHeight(null) / 2), null);
					}
					else
					{
						trans.setToTranslation((x - origX + imgOffset[i].x) - img[i].getWidth(null) / 2, (y - origY + imgOffset[i].y) - img[i].getHeight(null) / 2);
						trans.rotate(ang, img[i].getWidth(null) / 2, img[i].getHeight(null) / 2);
						g.drawImage(img[i], trans, null);
					}

				}
				else
				{
					g.setColor(Color.red);
					g.fillRect((int) (x - origX) - 40, (int) (y - origY) - 40, 80, 80);
					g.setColor(Color.BLACK);
					g.drawString("Image", (int) ((x - origX) - 15), (int) ((y - origY) - 20));
					g.drawString("Error", (int) ((x - origX) - 15), (int) ((y - origY) - 7));
				}
			}
		}
		if (fade || alpha != 1)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

	}
	public void renderAt(Graphics2D g, AffineTransform trans, double inx, double iny)
	{
		render(g, trans, x - inx, y - iny);
	}
	public void renderWireframes(Graphics2D g, double origX, double origY)
	{
		g.setColor(wireframeColor);
		renderRect(g, origX, origY);

		for (int i = 0; i < numWF; i++)
		{
			wireframes[i].render(g, origX, origY, wireframeColor);
		}
		g.drawOval((int) (x - origX - 5), (int) (y - origY - 5), 10, 10);
		g.drawLine((int) (x - origX), (int) (y - origY), (int) (x - origX + Math.cos(ang) * 25), (int) (y - origY + Math.sin(ang) * 25));
	}
	public void renderInfo(Graphics2D g, double origX, double origY)
	{
		java.util.ArrayList<String> infos = new java.util.ArrayList<String>();
		infos.add(name);
		infos.add("(" + (double) Math.round((x * 100)) / 100 + ", " + (double) Math.round((y * 100)) / 100 + ")");

		if (health == -1.0D)
			infos.add("Indestructable");
		else
			infos.add("Health: " + health + " / " + fullHealth + " (" + (int) (health / fullHealth * 100.0D) + "%)");
		if (timeRemaining != -1)
			infos.add("Time Left: " + (double) ((int) (timeRemaining * 100)) / 100 + " seconds");
		if (mass <= 0D || !physicalized)
			infos.add("Immovable");
		else
			infos.add("Moveable  Mass: " + mass);
		infos.add("Friction: " + friction);
		infos.add("Degrees: " + Math.ceil((Math.toDegrees(ang))));
		if (alpha != 1F)
			infos.add("Transparency: " + alpha);
		for (int i = 0; i < numWF; i++)
			wireframes[i].render(g, origX, origY, wireframeColor);
		g.setColor(Color.GRAY);
		// renderRect(g, origX, origY);
		renderWireframes(g, origX, origY);
		g.setColor(Color.LIGHT_GRAY);
		g.setColor(Color.blue);
		for (int i = 0; i < infos.size() * 14; i += 14)
		{
			g.drawString((String) infos.get(i / 14), (int) (x - origX) + 16, (int) (y - origY) + i);
		}
		if (xSpeed != 0 && ySpeed != 0)
		{
			double myAngle = Math.atan(ySpeed / xSpeed);
			double speed = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);

			speed /= 2;
			if (xSpeed < 0)
				speed = -speed;

			int startX = (int) (x - origX);
			int startY = (int) (y - origY);

			int endX = (int) (x + speed * Math.cos(myAngle) - origX);
			int endY = (int) (y + speed * Math.sin(myAngle) - origY);

			g.setColor(Color.RED);
			g.drawLine(startX, startY, endX, startY);

			g.setColor(Color.BLUE);
			g.drawLine(startX, startY, startX, endY);

			g.setColor(Color.green);
			g.drawLine(startX, startY, endX, endY);

		}
	}
	public void renderRect(Graphics2D g, double origX, double origY)
	{
		rect.setRect(rect.getX() - origX, rect.getY() - origY, rect.getWidth(), rect.getHeight());
		rect2.setRect(rect2.getX() - origX, rect2.getY() - origY, rect2.getWidth(), rect2.getHeight());

		g.draw(rect);
		g.draw(rect2);

		rect.setRect(rect.getX() + origX, rect.getY() + origY, rect.getWidth(), rect.getHeight());
		rect2.setRect(rect2.getX() + origX, rect2.getY() + origY, rect2.getWidth(), rect2.getHeight());
	}
	public boolean intersects(Entity e)
	{
		boolean r = false;
		try
		{
			if (e instanceof FracLine || e instanceof Explosion || e instanceof Bullet || e instanceof Explosion || e instanceof Biped)
				r = e.intersects(this);
			else if ((ghost || e.ghost) || (noCollide != null && noCollide.contains(e)))
				r = false;
			else if ((e.intersectStaticOnly && this instanceof StaticEntity) || (intersectStaticOnly && e instanceof StaticEntity) || (!intersectStaticOnly && !e.intersectStaticOnly))
			{
				if (!rect2.intersects(e.rect2))
					r = false;
				else
				{
					for (int i = 0; i < numWF; i++)
					{
						for (int j = 0; j < e.numWF; j++)
						{
							if (wireframes[i].collide(e.wireframes[j]))
								r = true;
						}
					}
				}

			}
		}
		catch (Exception ex)
		{}
		return r;
	}
	public abstract void collide(Entity e);
	public void col(Entity e)
	{

	}
	public void exitOverlap(Entity e)
	{
		if (ghost || e.ghost)
			return;
		CollisionInfo colInfo = wireframes[0].collideInfo(e.wireframes[0]);// just
																			// to
																			// set
																			// it
																			// to
																			// something
		colInfo.mtd.x = 0;
		colInfo.mtd.y = 0;
		for (int i = 0; i < wireframes.length; i++)
		{
			for (int j = 0; j < e.wireframes.length; j++)
			{
				if (wireframes[i].collide(e.wireframes[j]))
					colInfo = wireframes[i].collideInfo(e.wireframes[j]);
			}
		}

		x += colInfo.mtd.x;
		y += colInfo.mtd.y;

		x -= colInfo.mtd.x / 1E2;
		y -= colInfo.mtd.y / 1E2;

		updateWFs();
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
		advance();
		translateTo(x, y);
		computeRect();
		myTime = System.nanoTime();
	}
	public void advance()
	{
		if (physicalized)
		{
			if (!zeroG)
				ySpeed += (gravity / fps);

			xSpeed -= ((xSpeed * speedDampening) / fps);
			ySpeed -= ((ySpeed * speedDampening) / fps);

			rotSpeed -= ((rotSpeed * speedDampening) / fps);

			rotSpeed -= (rotSpeed * .85) / fps;

			x += xSpeed / fps;
			y += ySpeed / fps;
			rotate(rotSpeed / fps);
		}
		else
		{
			rotate(rotSpeed / fps);
		}
	}
	public void translate(double inx, double iny)
	{
		if (inx > Integer.MIN_VALUE && iny > Integer.MIN_VALUE)
		{
			x += inx;
			y += iny;
			updateWFs();
		}
	}
	public void translateTo(double inx, double iny)
	{
		if (inx > Integer.MIN_VALUE && iny > Integer.MIN_VALUE)
		{
			x = inx;
			y = iny;
			updateWFs();
		}
	}
	public void updateWFs()
	{
		for (int i = 0; i < numWF; i++)
		{
			wireframes[i].translateTo(x + wfOffset[i].x, y + wfOffset[i].y);
		}
		computeRect();
	}
	public void rotate(double a)// taken in radians
	{
		ang += a;
		while (ang > Math.PI * 2)
			ang -= (2 * Math.PI);
		while (ang < 0)
			ang += (2 * Math.PI);

		rotateWF(a);
		updateWFs();
	}
	public void rotateToward(double inx, double iny)
	{
		rotate(Math.atan2(y - iny, x - inx) + Math.PI - ang);
	}
	public void rotateWF(double ang)
	{
		for (int i = 0; i < numWF; i++)
		{
			wfAng[i] += ang;
			wireframes[i].rotate(ang);
			wfOffset[i].x = wfRad[i] * Math.cos(wfAng[i]);
			wfOffset[i].y = wfRad[i] * Math.sin(wfAng[i]);
		}
	}
	public void damage(double d)
	{
		if (health != -1)
		{
			if (d > damageThreshold)
				health -= (d - damageThreshold);
			if (health == -1)
				health = 0;
		}
	}
	public boolean removeMe()
	{
		if (!isDead())
			return false;
		addDeathParticles();
		return true;
	}

	public boolean isDead()
	{
		if ((health == -1 || health > 0) && (timeRemaining == -1 || timeRemaining > 0))
			return false;
		return true;
	}
	public void addDeathParticles()
	{
		final ArrayList<Entity> newEnts = new ArrayList<Entity>();
		double radius = Math.sqrt((rect.getWidth() * rect.getWidth() + rect.getHeight() * rect.getHeight()) / 4);

		if (hitParticles != null && hitParticles.size() > 0)
		{
			for (int i = 0; i < particlesToAddOnDeath; i++)
			{
				double angle = 2 * Math.PI * i / ((double) (particlesToAddOnDeath));
				PhysicsEntity p = (PhysicsEntity) hitParticles.get((int) (Math.random() * hitParticles.size())).getNew();
				p.translateTo(x + Math.cos(angle) * radius * Math.random(), y + Math.sin(angle) * radius * Math.random());
				boolean prevIntersect = p.intersectStaticOnly;
				p.intersectStaticOnly = false;
				int tries = 0;
				while (!this.intersects(p) && tries < 20)
				{
					p.translateTo(x + Math.cos(Math.random() * 2 * Math.PI) * radius * Math.random(), y + Math.sin(Math.random() * 2 * Math.PI) * radius * Math.random());
					tries++;
				}
				p.intersectStaticOnly = prevIntersect;
				p.rotate(Math.random() * Math.PI);
				p.xSpeed = 0 + xSpeed;
				p.ySpeed = 0 + ySpeed;
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
				p.gravity = gravity;p.fps=fps;
				p.timeRemaining = ((Math.random() * .5) + .7) * p.timeRemaining;
				newEnts.add(p);
			}
		}
		if (deathParticles != null && deathParticles.size() >= 0)
		{
			for (int i = 0; i < deathParticles.size(); i++)
			{
				if (deathParticles.get(i) instanceof PhysicsEntity)
				{
					double angle = 2 * Math.PI * i / ((double) (particlesToAddOnDeath));
					PhysicsEntity p = (PhysicsEntity) deathParticles.get(i).getNew();
					p.translateTo(x + Math.cos(angle) * radius * Math.random(), y + Math.sin(angle) * radius * Math.random());
					boolean prevIntersect = p.intersectStaticOnly;
					p.intersectStaticOnly = false;
					int tries = 0;
					while (!this.intersects(p) && tries < 20)
					{
						p.translateTo(x + Math.cos(Math.random() * 2 * Math.PI) * radius * Math.random(), y + Math.sin(Math.random() * 2 * Math.PI) * radius * Math.random());
						tries++;
					}
					p.intersectStaticOnly = prevIntersect;
					p.rotate(Math.random() * Math.PI);
					p.xSpeed = xSpeed;
					p.ySpeed = ySpeed;
					p.xSpeed -= (p.xSpeed * p.speedDampening);
					p.ySpeed -= (p.ySpeed * p.speedDampening);
					p.gravity = gravity;
					if (p.intersectStaticOnly && !p.ghost)
					{
						p.rotSpeed = (Math.random() * 20) - 10;
						p.xSpeed += 400 * Math.cos(Math.random() * Math.PI);
						p.ySpeed += 400 * Math.sin(Math.random() * Math.PI);
					}
					p.timeRemaining = ((Math.random() * .5) + .5) * p.timeRemaining;p.fps=fps;
					newEnts.add(p.copy());
				}
				else if (deathParticles.get(i) instanceof Explosion)
				{
					Explosion p = ((Explosion) deathParticles.get(i));
					p.translateTo(x, y);
					p.xSpeed = xSpeed;
					p.ySpeed = ySpeed;p.fps=fps;
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
	public void loadArray(java.util.ArrayList<Entity> ents)
	{
		this.ents = ents;
	}
	public void playSound(final String name)
	{
		try
		{
			ents.add(new Sound(name, this.x, this.y));
		}
		catch (Exception e)
		{}
	}
	public double getRenderValue()
	{
		double r = 0;
		for (int i = 0; i < numWF; i++)
		{
			r += wireframes[i].numCoords / 4;
		}
		for (int i = 0; i < numImg; i++)
		{
			r += Math.ceil(Math.hypot(img[i].getWidth(null), img[i].getHeight(null)) / 25);
		}
		if (intersectStaticOnly == false)
			r += 5;
		if (this instanceof PhysicsEntity)
		{
			r += 4;
			if (this instanceof Bullet)
				r += 8;
			else if (this instanceof Weapon)
			{
				if (((Weapon) this).laserPointerOn)
					r += 5;
			}
		}
		else if (this instanceof Sound)
			r += 5;
		else if (this instanceof FracLine)
		{
			r += .1 * Math.hypot(((FracLine) this).start.x - ((FracLine) this).end.x, ((FracLine) this).start.y - ((FracLine) this).end.y);
		}
		else if (this instanceof Explosion)
			r += ((Explosion) this).radius / 35;
		double adjustedAng = Math.min(Math.abs(ang), Math.abs((Math.PI * 2) - ang));

		r += (Math.abs(adjustedAng) * 18);
		r /= 10;
		return r;
	}
}
