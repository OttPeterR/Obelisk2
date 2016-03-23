import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ParticleSpawner implements Entity
{
	Particle	p;
	double		x, y, count, addTime;	// addTime is how many seconds before
										// adding another particle
	double		maxX, minX, maxY, minY, speed;
	String		name;
	int			ID;
	boolean		pause;
	Rectangle	rect;

	public ParticleSpawner(String n, int id, double inx, double iny, double t, Particle par, double xmax, double xmin, double ymax, double ymin, double sp)
	{
		p = par;
		x = inx;
		y = iny;
		name = n;
		ID = id;
		count = 0;
		addTime = t;
		pause = false; // change me back nigga!

		maxX = xmax;
		minX = xmin;
		maxY = ymax;
		minY = ymin;
		speed = sp;

		rect = new Rectangle((int) x, (int) y, 40, 40);
	}

	public ParticleSpawner(String n, double t, Particle par, double xmax, double xmin, double ymax, double ymin, double sp)
	{
		p = par;
		x = 0;
		y = 0;
		name = n;
		ID = -1;
		count = 0;
		addTime = t;
		pause = false;

		maxX = xmax;
		minX = xmin;
		maxY = ymax;
		minY = ymin;
		speed = sp;

		rect = new Rectangle((int) x, (int) y, 40, 40);
	}
	public String getSaveString()
	{
		return "ParticleSpawner"+" "+name+" "+ID+" "+x+" "+y+" "+addTime+" "+p.getSaveString()+" "+maxX+" "+minX+" "+maxY+" "+minY+" "+speed;
	}
	public String getImageString()
	{
		return "";
	}
	public void sync(Entity e)
	{
		if (!(e.getType().equals("ParticleSpawner")))
			return;
		ParticleSpawner that = (ParticleSpawner) e;

		x = that.x;
		y = that.y;
		name = that.name;
		ID = that.ID;
		addTime = that.addTime;
		maxX = that.maxX;
		maxY = that.maxY;
		minX = that.minX;
		minY = that.minY;
		speed = that.speed;
		rect = that.rect;
		p = that.p;

	}

	public void renderInEditor(Graphics2D g)
	{
		Color previous = g.getColor();
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 40, 40);
		g.setColor(previous);
	}

	public void renderInGame(Graphics2D g)
	{

	}

	

	public void convert(double inx, double iny, int id)
	{
		x = inx;
		y = iny;
		rect.setLocation((int) x, (int) y);
		ID = id;

	}

	public void convert(double inx, double iny, double inxs, double inys)
	{

	}

	public void convertMove(double xMove, double yMove)
	{

	}

	public Particle getParticle()
	{
		return p;
	}

	public double getX()
	{

		return x;
	}

	public void setX(double inx)
	{
		x = inx;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double iny)
	{
		y = iny;
	}

	public void setLocation(double inx, double iny)
	{
		x = inx;
		y = iny;
	}

	public double getXSpeed()
	{
		return 0;
	}

	public void setXSpeed(double x)
	{}

	public void addXSpeed(double x)
	{}

	public void applyXForce(double x)
	{}

	public double getYSpeed()
	{
		return 0;
	}

	public void setYSpeed(double y)
	{}

	public void addYSpeed(double y)
	{

	}

	public void applyYForce(double y)
	{

	}

	public double getXDim()
	{

		return 10;
	}

	public double getYDim()
	{

		return 10;
	}

	public Rectangle getRectangle()
	{
		return null;
	}

	public String getName()
	{

		return name;
	}

	public String getType()
	{

		return "ParticleSpawner";
	}

	public void setName(String n)
	{

		name = n;
	}

	public double getMass()
	{

		return -1;
	}

	public void setMass(double m)
	{

	}

	public double getFriction()
	{

		return 0;
	}

	public void setFriction(double f)
	{

	}

	public int getID()
	{

		return ID;
	}

	public void setID(int id)
	{
		ID = id;

	}

	public Image getImage()
	{

		return p.getImage();
	}

	public double getMaxTime()
	{

		return addTime;
	}

	public void setMaxTime(double t)
	{

	}

	public double getCurTime()
	{

		return -1;
	}

	public void setCurTime(double t)
	{

	}

	public boolean removeMe()
	{

		return false;
	}

	public double getRotation()
	{

		return 0;
	}

	public void setRotation(double r)
	{

	}

	public void addRotation(double r)
	{

	}

	public void updateFPS(double newFPS)
	{

	}

	public void step(double fps, ArrayList<Entity> e)
	{
		if (pause)
			return;
		count += 1 / fps;
		if (count > addTime)
			count = 0;
		if (count == addTime)
		{
			Particle add = p;
			add.setLocation(x, y);
			add.randomDirection(maxX, minX, maxY, minY, speed);
			e.add(add);
		}

	}

	public void collide(Entity e)
	{

	}

	public void frictionWith(Entity e)
	{

	}

	public void pause()
	{
		pause = true;

	}

	public void unpause()
	{
		pause = false;

	}

	public Weapon getWeapon()
	{
		return null;
	}

	public double getGravity()
	{
		return 0;
	}

	public double getHealth()
	{
		return -.001;
	}

	public void setHealth(double h)
	{}

	public void damage(double h)
	{}

	@Override
	public Rectangle getEditorRectangle()
	{
		// TODO Auto-generated method stub
		return rect;
	}

	@Override
	public void checkCollision(Entity e)
		{
			// TODO Auto-generated method stub
			
		}

	@Override
	public void applySpeed()
		{
			// TODO Auto-generated method stub
			
		}

	@Override
	public void debug(Entity e)
		{
			// TODO Auto-generated method stub
			
		}

	@Override
	public Enemy getEnemy()
		{
			// TODO Auto-generated method stub
			return null;
		}

}
