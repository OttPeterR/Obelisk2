import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Particle implements Entity
	{

		double	x, y, xSpeed, ySpeed, gravity, mass, fps, friction, maxTime, curTime;
		int		ID;
		boolean	still, hitTop, hitSide, hitBottom;
		Image	img;
		String	name, imgString;
		Rectangle	rectAll, rectTop, rectBottom, rectLeft, rectRight;

		public Particle(String n, int id, double inx, double iny, double grav, double frames, Image i, String is, double m, double fric, double mT)
			{
				name = n;
				imgString = is;
				ID = id;
				x = inx;
				y = iny;
				xSpeed = 0;
				ySpeed = 0;
				mass = m;
				gravity = grav;
				fps = frames;
				maxTime = mT;
				curTime = 0;
				still = false;
				friction = fric;
				img = i;
				construct();
			}

		public Particle(String n, double frames, Image i, String is, double m, double fric, double grav, double mT)
			{
				name = n;
				imgString = is;
				ID = -1;
				x = 0;
				y = 0;
				xSpeed = 0;
				ySpeed = 0;
				mass = m;
				gravity = grav;
				fps = frames;
				maxTime = mT;
				curTime = 0;
				still = false;
				friction = fric;
				img = i;

				construct();
			}

		public String getSaveString()
			{
				return "Particle" + " " + name + " " + ID + " " + x + " " + y + " " + gravity + " " + fps + " " + imgString + " " + mass + " " + friction + " " + maxTime;
			}

		public void construct()
			{
				hitTop = false;
				hitSide = false;
				hitBottom = false;

				int x1 = (int) (x + (img.getWidth(null) * .2)); // alt value .25
																// or .3
				int x2 = (int) (x + (img.getWidth(null) * .8)); // alt value .75
																// or .7
				int xDistForX = (int) (img.getHeight(null) * .6); // value is
																	// the
																	// difference
																	// of the
																	// above
																	// values
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

				rectAll = new Rectangle((int) x, (int) y, img.getWidth(null), img.getHeight(null));
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
				return "Particle";
			}

		public String getImageString()
			{
				return imgString;
			}

		public double getGravity()
			{
				return gravity;
			}

		public void randomDirection(double xMin, double xMax, double yMax, double yMin, double speed)
			{

			}

		public void convert(double inx, double iny, int id)
			{
				x = inx;
				y = iny;
				rectAll.setLocation((int) x, (int) y);
				ID = id;
			}

		public void convert(double inx, double iny, double inxs, double inys)
			{
				x = inx;
				y = iny;
				rectAll.setLocation((int) x, (int) y);
				xSpeed = inxs;
				ySpeed = inys;
			}

		public void convertMove(double xMove, double yMove)
			{
				x = +xMove;
				y = +yMove;
				rectAll.setLocation((int) x, (int) y);
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
				rectAll.setLocation((int) x, (int) y);
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
				return null;
			}

		public Rectangle getEditorRectangle()
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
				return maxTime;
			}

		public void setMaxTime(double t)
			{
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
				if (curTime > maxTime)
					return true;
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

		public void step(double fps, ArrayList<Entity> e)
			{
				if (still)
					return;

				curTime += (1 / fps);
				double newYT = y;
				double newYB = y;
				double newX = x;

				boolean hitT = false;
				boolean hitB = false;
				boolean hitL = false;
				boolean hitR = false;

				for (int i = 0; i < e.size(); i++)
					{
						if (e.get(i) != this && e.get(i).getRectangle() != null)
							{
								if (e.get(i).getRectangle().intersects(rectAll))
									{
										xSpeed -= (xSpeed * e.get(i).getFriction());
										ySpeed -= (ySpeed * e.get(i).getFriction());
										collide(e.get(i));
									}
								if (e.get(i).getRectangle().intersects(rectTop))
									{
										newYT = e.get(i).getY() + e.get(i).getRectangle().getHeight();
										// if(e.get(i).getMass()==-1)
										hitT = true;
									}
								if (e.get(i).getRectangle().intersects(rectBottom))
									{
										newYB = e.get(i).getY() - getYDim();
										// if(e.get(i).getMass()==-1)
										hitB = true;
									}

								if (e.get(i).getRectangle().intersects(rectLeft))
									{
										newX = e.get(i).getX() + e.get(i).getRectangle().getWidth();
										// if(e.get(i).getMass()==-1)
										hitL = true;
									}
								if (e.get(i).getRectangle().intersects(rectRight))
									{
										newX = e.get(i).getX() - img.getWidth(null);
										// if(e.get(i).getMass()==-1)
										hitR = true;
									}
							}
					}
				if (!hitB)
					{
						ySpeed += gravity;
					}

				if (hitB)
					{
						ySpeed = 0;
						y = newYB;
					}
				if (hitT)
					{
						ySpeed = 0;
						y = newYT;
					}
				if ((hitL || hitR) && !hitB)
					{
						x = newX;
						xSpeed = 0;
					}
				x += (xSpeed / fps);
				y += (ySpeed / fps);
				rectShift((int) x, (int) y);
			}

		public void collide(Entity e)
			{
				if (e.getMass() == -1)
					return;
				double m2 = e.getMass();
				double xs2 = e.getXSpeed();
				double ys2 = e.getYSpeed();

				e.setYSpeed(-1 * (xs2 * (m2 - mass) + 2 * mass * ySpeed) / (m2 + mass));
				e.setXSpeed((ys2 * (m2 - mass) + 2 * mass * xSpeed) / (m2 + mass));

				xSpeed = ((xSpeed * (mass - m2) + 2 * m2 * xs2) / (mass + m2));
				ySpeed = ((ySpeed * (m2 - mass) + 2 * mass * ys2) / (m2 + mass));
			}

		public void frictionWith(Entity e)
			{
				// reduce this's speed but not the other one
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
				return -.001;
			}

		public void setHealth(double h)
			{
			}

		public void damage(double h)
			{
			}

		@Override
		public void sync(Entity e)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void checkCollision(Entity e)
			{
				if (e.getRectangle() == null)
					return;
				if (!rectAll.intersects(e.getRectangle()))
					return;
				col(e);
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

				curTime += 1 / fps;
			}

		public void col(Entity e)
			{

				if (e.getMass() == -1)
					{
						if (rectTop.intersects(e.getRectangle()) && e.getMass() == -1)
							hitTop = true;
						if (rectBottom.intersects(e.getRectangle()) && e.getMass() == -1)
							hitBottom = true;
						if ((rectLeft.intersects(e.getRectangle()) || rectRight.intersects(e.getRectangle())) && e.getMass() == -1)
							hitSide = true;
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
