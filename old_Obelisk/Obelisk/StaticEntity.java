import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class StaticEntity implements Entity
	{
		double		x, y, friction, health;
		int			ID;
		Image		img;
		String		name, imgString;
		Rectangle	rect;

		public StaticEntity(String n, int id, double inx, double iny, double fric, double h, Image i, String is)
			{
				x = inx;
				y = iny;
				ID = id;
				img = i;
				friction = fric;
				name = n;
				imgString = is;
				health = h;
				rect = new Rectangle((int) x, (int) y, img.getWidth(null), img.getHeight(null));
			}

		public StaticEntity(String n, double fric, Image i, String is)
			{
				name = n;
				imgString = is;
				img = i;
				friction = fric;
				x = 0;
				y = 0;
				ID = -1;
				health = -.001;
				rect = new Rectangle((int) x, (int) y, img.getWidth(null), img.getHeight(null));
			}

		public void renderInEditor(Graphics2D g)
			{
				g.drawImage(img, (int) x, (int) y, null);
			}

		public void renderInGame(Graphics2D g)
			{
				g.drawImage(img, (int) x, (int) y, null);
			}

		public String getSaveString()
			{
				return "StaticEntity" + " " + name + " " + ID + " " + x + " " + y + " " + friction + " " + health + " " + imgString;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String n)
			{
				name = n;
			}

		public double getGravity()
			{
				return 0;
			}

		public String getType()
			{
				return "StaticEntity";
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
				x = inx;
				y = iny;
				rect.setLocation((int) x, (int) y);
			}

		public void convertMove(double xMove, double yMove)
			{
				x = xMove;
				y = yMove;
				rect.setLocation((int) x, (int) y);
			}

		public double getX()
			{
				return x;
			}

		public void setX(double inx)
			{
				x = inx;
				rect.setLocation((int) x, (int) y);
			}

		public double getY()
			{
				return y;
			}

		public void setY(double iny)
			{
				y = iny;
				rect.setLocation((int) x, (int) y);
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
			{
			}

		public void addXSpeed(double x)
			{
			}

		public void applyXForce(double x)
			{
			}

		public double getYSpeed()
			{
				return 0;
			}

		public void setYSpeed(double y)
			{
			}

		public void addYSpeed(double y)
			{
			}

		public void applyYForce(double y)
			{
			}

		public double getXDim()
			{
				return img.getWidth(null);
			}

		public double getYDim()
			{
				return img.getHeight(null);
			}

		public Rectangle getRectangle()
			{
				return rect;
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
				return friction;
			}

		public void setFriction(double f)
			{
				friction = f;
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
				return img;
			}

		public double getMaxTime()
			{
				return -1;
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

		public void step(double fps, ArrayList<Entity> e)
			{
			}

		public void collide(Entity e)
			{
				if(e.getType().equals("Bullet"))
					e.damage(1);
			}

		public void frictionWith(Entity e)
			{
			}

		public void pause()
			{
			}

		public void unpause()
			{
			}

		public Weapon getWeapon()
			{
				return null;
			}

		public void updateFPS(double newFPS)
			{
			}

		public double getHealth()
			{
				return health;
			}

		public void setHealth(double h)
			{
				health = h;
			}

		public void damage(double h)
			{
				health -= h;
			}

		@Override
		public Rectangle getEditorRectangle()
			{
				// TODO Auto-generated method stub
				return rect;
			}

		@Override
		public void sync(Entity e)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public String getImageString()
			{
				// TODO Auto-generated method stub
				return imgString;
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

		public void debug(Entity e)
			{
				if (!e.getType().equals("StaticEntity"))
					e.debug(this);
			}

		@Override
		public Enemy getEnemy()
			{
				// TODO Auto-generated method stub
				return null;
			}
	}
