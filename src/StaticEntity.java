import java.awt.Color;

public class StaticEntity extends Entity
{
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe wf)
	{
		super(n, im, ims, wf);
		wireframeColor = Color.blue;
		health = -1;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect v)
	{
		super(n, im, ims, wf, v);
		wireframeColor = Color.blue;
		health = -1;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect v, double fric)
	{
		super(n, im, ims, wf, v);
		wireframeColor = Color.blue;
		health = -1;
		friction = fric;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect v, double h, double fric, double p)
	{
		super(n, im, ims, wf, v);
		wireframeColor = Color.blue;
		health = -1;
		health = h;
		fullHealth = 0 + h;
		friction = fric;
		penetration = p;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe wf, Vect v, double fric, double b)
	{
		super(n, im, ims, wf, v);
		wireframeColor = Color.blue;
		health = -1;
		bounce = b;
		friction = fric;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe[] wf, Vect[] wfOff, int nWF)
	{
		super(n, im, ims, wf, wfOff, nWF);
		wireframeColor = Color.blue;
		health = -1;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe wf, double h)
	{
		super(n, im, ims, wf);
		wireframeColor = Color.blue;
		health = -1;
		health = h;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe[] wf, Vect[] wfOff, int nWF, double h, double fric)
	{
		super(n, im, ims, wf, wfOff, nWF);
		wireframeColor = Color.blue;
		health = -1;
		health = h;
		fullHealth = 0 + h;
		friction = fric;
	}
	public StaticEntity(String n, java.awt.Image im, String ims, Wireframe[] wf, Vect[] wfOff, int nWF, double h, double fric, double b)
	{
		super(n, im, ims, wf, wfOff, nWF);
		wireframeColor = Color.blue;
		health = -1;
		health = h;
		fullHealth = 0 + h;
		bounce = b;
		friction = fric;
	}
	public StaticEntity(String n, java.awt.Image[] im, String[] ims, Vect[] imgOff, int numI, Wireframe[] wfs, Vect[] wfOff, int nwf, double h,
			double fric, double b)
	{
		super(n, im, ims, imgOff, numI, wfs, wfOff, nwf);
		wireframeColor = Color.blue;
		health = -1;
		health = h;
		fullHealth = 0 + h;
		bounce = b;
		friction = fric;
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

		Entity r = new StaticEntity(name, img, imgName, imgOffset, numImg, wf, wfo, numWF, fullHealth, friction, bounce);
		for (int i = 0; i < numWF; i++)
		{
			r.wfOffset[i] = new Vect(wfOffset[i].x, wfOffset[i].y);
			r.wfRad[i] = 0 + wfRad[i];
		}
		r.x = 0 + x;
		r.y = 0 + y;
		r.hitParticles = this.hitParticles;
		r.ghost = ghost;
		r.penetration = 0 + this.penetration;
		r.particlesToAddOnDeath = this.particlesToAddOnDeath;
		r.deathParticles = this.deathParticles;
		r.noCollide = noCollide;
		r.damageThreshold = this.damageThreshold;
		r.alpha = this.alpha;
		r.hitSounds = this.hitSounds;
		r.intersectStaticOnly = this.intersectStaticOnly;
		r.intersectPlayers = this.intersectPlayers;
		r.rotate(0);
		r.wireframeColor = this.wireframeColor;
		r.ID = 0 + ID;
		r.loadArray(ents);
		return r;
	}
	public String toString()
	{
		return "StaticEntity " + super.toString();
	}
	public void collide(Entity e)
	{
		if (!(e instanceof StaticEntity))
			e.collide(this);
	}
	public void step()
	{
		if (rotSpeed != 0)
			advance();
	}

}
