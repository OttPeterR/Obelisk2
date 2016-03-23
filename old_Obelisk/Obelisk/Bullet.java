import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.sound.sampled.Line;

public class Bullet implements Entity
	{
		ArrayList<Entity>	hit;
		double				x, y, lastX, lastY, xSpeed, ySpeed, gravity, mass, damage, maxTime, curTime, fps;
		int					numHits;

		public Bullet(double inx, double iny, double inxs, double inys, double grav, double m, double inD, int hits, double maxT, double f)
			{
				x = inx;
				y = iny;
				lastX = 0 + x;
				lastY = 0 + y;
				xSpeed = inxs;
				ySpeed = inys;
				gravity = grav;
				mass = m;
				damage = inD;
				numHits = hits;
				curTime = 0;
				maxTime = maxT;
				fps = f;
				hit = new ArrayList<Entity>();
			}

		public void renderInEditor(Graphics2D g)
			{
				Color prev = g.getColor();
				g.setColor(Color.orange);
				g.fillOval((int) x - 3, (int) y - 3, 6, 6);
				g.setColor(Color.gray);
				g.drawLine((int) x, (int) y, (int) (lastX), (int) (lastY));
				g.setColor(prev);
			}

		public void renderInGame(Graphics2D g)
			{

			}

		public String getSaveString()
			{
				return "Bullet " + x + " " + y + " " + xSpeed + " " + ySpeed + " " + gravity + " " + mass + " " + damage + " " + numHits + " " + maxTime + " " + fps;
			}

		public void convert(double inx, double iny, int id)
			{
				x = inx;
				y = iny;

			}

		public void convert(double inx, double iny, double inxs, double inys)
			{
				x = inx;
				y = iny;
				xSpeed = inxs;
				ySpeed = inys;
			}

		public void convertMove(double xMove, double yMove)
			{
				x = xMove;
				y = yMove;
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

				return xSpeed;
			}

		public void setXSpeed(double inx)
			{
				xSpeed = inx;

			}

		public void addXSpeed(double inx)
			{

				xSpeed += inx;
			}

		public void applyXForce(double x)
			{
				xSpeed += (x / mass);
			}

		public double getYSpeed()
			{

				return ySpeed;
			}

		public void setYSpeed(double iny)
			{

				ySpeed = iny;
			}

		public void addYSpeed(double iny)
			{

				ySpeed += iny;
			}

		public void applyYForce(double iny)
			{
				ySpeed += (iny / mass);
			}

		public double getXDim()
			{

				return 2;
			}

		public double getYDim()
			{

				return 2;
			}

		public double getGravity()
			{

				return gravity;
			}

		public Rectangle getRectangle()
			{

				return new Rectangle((int) x - 1, (int) y - 1, 2, 2);
			}

		public Rectangle getEditorRectangle()
			{

				return new Rectangle((int) x - 10, (int) y - 10, 20, 20);
			}

		public String getName()
			{

				return "Bullet";
			}

		public String getType()
			{

				return "Bullet";
			}

		public void setName(String n)
			{

			}

		public double getMass()
			{

				return mass;
			}

		public void setMass(double m)
			{
				mass = m;

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

				return -1;
			}

		public void setID(int id)
			{

			}

		public double getHealth()
			{

				return 0;
			}

		public void setHealth(double h)
			{

			}

		public void damage(double h)
			{
				numHits -= (int) h;
			}

		public Image getImage()
			{

				return null;
			}

		public String getImageString()
			{

				return "";
			}

		public double getMaxTime()
			{

				return maxTime;
			}

		public void setMaxTime(double t)
			{
				maxTime = t;
			}

		public double getCurTime()
			{

				return curTime;
			}

		public void setCurTime(double t)
			{

			}

		public boolean removeMe()
			{
				if (numHits <= 0 || curTime >= maxTime)
					{
						return true;
					}
				else
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

			}
		public double getDamage()
			{
				return damage;
			}

		public void checkCollision(Entity e)
			{
				if (e.getType().equals("Bullet") || e.getType().equals("Particle") || e.getRectangle() == null)
					return;

				Line2D ln = new Line2D.Double(x, y, lastX, lastY);
				Rectangle r = e.getRectangle();

				if (ln.intersects(r))
					{
						hit.add(e);
					}
			}

		public void applySpeed()
			{
				lastX = 0 + x;
				lastY = 0 + y;
				x += (xSpeed / fps);
				y += (ySpeed / fps);
				y += (gravity / fps);
				curTime += 1 / fps;
				for (int i = 0; i<hit.size(); i++)
					{
						hit.get(i).damage(damage);
						collide(hit.get(i));
						numHits--;
						if (numHits == 0)
							i = hit.size();
					}

				while (hit.size() > 0)//resetting the hit array
					{
						hit.remove(0);
					}
			}

	

		public void collide(Entity e)
			{
				double m2 = e.getMass();
				double xs2 = e.getXSpeed();
				double ys2 = e.getYSpeed();

				e.setYSpeed(-1 * (xs2 * (m2 - mass) + 2 * mass * ySpeed) / (m2 + mass));
				e.setXSpeed((ys2 * (m2 - mass) + 2 * mass * xSpeed) / (m2 + mass));

				ySpeed /= 2;
				xSpeed /= 2;
			}

		public void debug(Entity e)
			{

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

		public void sync(Entity e)
			{

			}

		public Enemy getEnemy()
			{
				return null;
			}

	}
