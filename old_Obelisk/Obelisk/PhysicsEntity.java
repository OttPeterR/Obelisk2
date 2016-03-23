import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class PhysicsEntity implements Entity
	{

		double	x, y, xSpeed, ySpeed, mass, fps, friction, gravity, health;
		int		ID;
		boolean	still, hitTop, hitSide, hitBottom;;
		Image	img;
		String	name, imgString;
		Rectangle	rectAll, rectTop, rectBottom, rectLeft, rectRight;

		public PhysicsEntity(String n, int id, double inx, double iny, double frames, Image i, String is, double m, double fric, double h)
			{
				name = n;
				ID = id;
				x = inx;
				imgString = is;
				y = iny;
				mass = m;
				fps = frames;
				still = false;
				friction = fric;
				img = i;
				health = h;
				construct();

			}

		public PhysicsEntity(String n, double frames, Image i, String is, double m, double fric, double h)
			{
				name = n;
				ID = -1;
				x = 0;
				y = 0;
				mass = m;
				imgString = is;
				fps = frames;
				still = false;
				friction = fric;
				img = i;
				health = h;
				construct();
			}

		public String getSaveString()
			{
				return "PhysicsEntity" + " " + name + " " + ID + " " + x + " " + y + " " + fps + " " + imgString + " " + mass + " " + friction + " " + health;
			}

		public String getImageString()
			{
				return imgString;
			}

		public void construct()
			{
				xSpeed = 0;
				ySpeed = 0;
				gravity = 10;
				hitTop = false;
				hitSide = false;
				hitBottom = false;

				int x2 = (int) (x + (img.getWidth(null) * .8)); // alt value .75
																// or .7
				int xDistForY = (int) (img.getWidth(null) * .2);

				int y1 = (int) (y + (img.getHeight(null) * .2)); // alt value
																	// .25 or .3
				int y2 = (int) (y + (img.getHeight(null) * .8)); // alt value
																	// .75 or .7
				int yDistForY = (int) (img.getHeight(null) * .6); // value is
																	// the
																	// difference
																	// of the
																	// above
																	// values
				int yDistForX = (int) (img.getHeight(null) * .2);

				int width = img.getWidth(null);
				int height = img.getHeight(null);

				rectAll = new Rectangle((int) x, (int) y, width, height);
				rectTop = new Rectangle((int) x + 5, (int) y, width - 10, yDistForX);
				rectBottom = new Rectangle((int) x + 5, y2, width - 10, yDistForX);
				rectLeft = new Rectangle((int) x, y1, xDistForY, yDistForY);
				rectRight = new Rectangle(x2, y1, xDistForY, yDistForY);

			}

		public void rectShift(int inx, int iny)
			{
				int x2 = (int) (img.getWidth(null) * .8); // alt value .75 or .7

				int y1 = (int) (img.getHeight(null) * .2); // alt value .25 or
															// .3
				int y2 = (int) (img.getHeight(null) * .8); // alt value .75 or
															// .7

				rectAll.setLocation(inx, iny);
				rectTop.setLocation(inx + 5, iny);
				rectBottom.setLocation(inx + 5, iny + y2);
				rectLeft.setLocation(inx, iny + y1);
				rectRight.setLocation(inx + x2, iny + y1);

			}

		public void renderInEditor(Graphics2D g)
			{
				g.drawImage(img, (int) x, (int) y, null);
			}

		public void renderInGame(Graphics2D g)
			{
				g.drawImage(img, (int) x, (int) y, null);
			}
		public void checkCollision(Entity e)
			{
				if (e.getRectangle() != null && rectAll.intersects(e.getRectangle()))
					{
						if (e instanceof Bullet)
							{
								e.checkCollision(this);
								return;
							}
						col(e);
					}
			}

		public void col(Entity e)
			{
				if (e instanceof StaticEntity)
					{
						if (rectTop.intersects(e.getRectangle()))
							{
								hitTop = true;
								ySpeed = 0;
							}
						if (rectBottom.intersects(e.getRectangle()))
							{
								hitBottom = true;
								ySpeed = 0;

							}
						if ((rectLeft.intersects(e.getRectangle()) || rectRight.intersects(e.getRectangle())))
							{
								hitSide = true;
								xSpeed = 0;
							}

					}
				else if (e instanceof Bullet)
					{
						damage(((Bullet) e).getDamage());
					}
				else
					{

						double m2 = e.getMass();
						double xs2 = e.getXSpeed();
						double ys2 = e.getYSpeed();

						e.setYSpeed(-1 * (xs2 * (m2 - mass) + 2 * mass * ySpeed) / (m2 + mass));
						e.setXSpeed((ys2 * (m2 - mass) + 2 * mass * xSpeed) / (m2 + mass));

						xSpeed = ((xSpeed * (mass - m2) + 2 * m2 * xs2) / (mass + m2));
						ySpeed = ((ySpeed * (m2 - mass) + 2 * mass * ys2) / (m2 + mass));

					}
				xSpeed -= (xSpeed * e.getFriction());
				ySpeed -= (ySpeed * e.getFriction());

			}

		public void applySpeed()
			{
				ySpeed += gravity;

				if (hitTop || hitBottom)
					ySpeed = 0;
				if (hitSide)
					xSpeed = 0;

				x += xSpeed / fps;
				y += ySpeed / fps;

				hitTop = false;
				hitSide = false;
				hitBottom = false;

			}

		public void collide(Entity e)
			{
				if (e.getType().equals("Bullet"))
					{
						e.damage(1);
						return;
					}

				double m2 = e.getMass();
				double xs2 = e.getXSpeed();
				double ys2 = e.getYSpeed();

				e.setYSpeed(-1 * (xs2 * (m2 - mass) + 2 * mass * ySpeed) / (m2 + mass));
				e.setXSpeed((ys2 * (m2 - mass) + 2 * mass * xSpeed) / (m2 + mass));

				xSpeed = ((xSpeed * (mass - m2) + 2 * m2 * xs2) / (mass + m2));
				ySpeed = ((ySpeed * (m2 - mass) + 2 * mass * ys2) / (m2 + mass));
			}

		public String getName()
			{
				return name;
			}

		public void setName(String n)
			{
				name = n;
			}

		public String getType()
			{
				return "PhysicsEntity";
			}

		public double getGravity()
			{
				return gravity;
			}

		public void convert(double inx, double iny, int id)
			{
				x = inx;
				y = iny;
				rectShift((int) inx, (int) iny);
				ID = id;
			}

		public void convert(double inx, double iny, double inxs, double inys)
			{
				x = inx;
				y = iny;
				rectShift((int) x, (int) y);
				xSpeed = inxs;
				ySpeed = inys;
			}

		public void convertMove(double xMove, double yMove)
			{
				x = +xMove;
				y = +yMove;
				rectShift((int) x, (int) y);
			}

		public double getX()
			{
				return x;
			}

		public void setX(double inx)
			{
				x = inx;
				rectShift((int) x, (int) y);
			}

		public double getY()
			{
				return y;
			}

		public void setY(double iny)
			{
				y = iny;
				rectShift((int) x, (int) y);
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

		public void applyXForce(double inx)
			{
				xSpeed += inx / mass;
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
				ySpeed += iny / mass;
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
				return rectAll;
			}

		public double getMass()
			{
				return mass;
			}

		public void setMass(double inm)
			{
				mass = inm;
			}

		public double getFriction()
			{
				return friction;
			}

		public void setFriction(double inf)
			{
				friction = inf;
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
				return (health < 0 && health != -.001);
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
				// dont use
			}

		public void frictionWith(Entity e)
			{
				// recude this's speed but not the other one
			}

		public void pause()
			{
				still = true;
			}

		public void unpause()
			{
				still = false;
			}

		public Weapon getWeapon()
			{
				return null;
			}

		public void updateFPS(double newFPS)
			{
				fps = newFPS;
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

		public void sync(Entity e)
			{

			}

		public Rectangle getEditorRectangle()
			{
				return rectAll;
			}

		
		public void debug(Entity e) // moves this entity out of e if they are touching
			{
				if (e.getType().equals("Bullet"))
					return;

				if (e.getRectangle() != null && rectAll.intersects(e.getRectangle()) && e != this)
					{
						if (rectTop.intersects(e.getRectangle()) && rectBottom.intersects(e.getRectangle()))
							{
								y = e.getY() - rectAll.getHeight();
								//ySpeed = 0;
							}
						else if (!rectTop.intersects(e.getRectangle()) && rectBottom.intersects(e.getRectangle()))
							{
								y = e.getY() - rectAll.getHeight() + 1;
								ySpeed = 0;
							}
						else if (rectTop.intersects(e.getRectangle()) && !rectBottom.intersects(e.getRectangle()))
							{
								y = e.getY() + e.getRectangle().getHeight() + 1;
								//ySpeed = 0;
							}
						if (rectLeft.intersects(e.getRectangle()) && rectRight.intersects(e.getRectangle()))
							{
								y = e.getY() - img.getHeight(null);
							}
						else if (rectLeft.intersects(e.getRectangle()) && !rectRight.intersects(e.getRectangle()))
							{
								x = e.getX() + e.getRectangle().getWidth() + 1;
							}
						else if (!rectLeft.intersects(e.getRectangle()) && rectRight.intersects(e.getRectangle()))
							{
								x = e.getX() - rectAll.getWidth() - 1;
							}
					}
			}

		@Override
		public Enemy getEnemy()
			{
				// TODO Auto-generated method stub
				return null;
			}
	}
