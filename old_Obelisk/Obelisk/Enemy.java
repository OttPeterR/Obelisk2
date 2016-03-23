import java.util.*;
import java.awt.*;
import java.lang.Math;

public class Enemy implements AI
	{
		protected double					x, y, xSpeed, ySpeed, health, damage, mass, gravity, fps, friction;
		protected boolean					movingL, movingR, hitTop, hitBottom, hitSide;
		protected int						height, width, id;
		protected String					name, imageString;
		protected Rectangle					rectAll, rectFeetR, rectFeetL, rectUpperR, rectUpperL, rectBottom, rectTop, rectLeft, rectRight, rectFeet, rectUpper;
		protected ArrayList<Entity>			entities;
		protected Graphics2D				g;																														//use this so you draw the player -> make it into an image -> return it with getGraphic
		/* legs */protected Image			stillR, stillL, runR, runL, midAirR, midAirL;
		/* body */protected Image			bodyR, bodyL;
		/* Array */protected Image[]		images;
		/* final */protected Image			finalImage, finalLegs, finalHead, finalBody, finalArms;
		/* for testing-> */protected Image	img;
		protected Dude						dude;

		public Enemy(String inName, int inID, double inX, double inY, double xVelocity, double yVelocity, double inHealth, double inDamage, double inMass, double inGravity, int inHeight, int inWidth, String imageStr, Image inIMG)
			{
				name = inName;
				id = inID;
				x = inX;
				y = inY;
				xSpeed = xVelocity;
				ySpeed = yVelocity;
				health = inHealth;
				damage = inDamage;
				mass = inMass;
				gravity = inGravity;
				fps = 200;
				friction = .1;
				height = inHeight;
				width = inWidth;
				imageString = imageStr;
				img = inIMG;
				movingL = false;
				movingR = false;
				hitTop = false;
				hitBottom = false;
				hitSide = false;
				constructRects();
			}

		public Enemy(String inName, int idCount, int mx, int my, double inFps, Image image, String imageStr, double inMass, double inFriction, double inHealth, double inDamage, double inG)
			{
				name = inName;
				id = idCount;
				fps = inFps;
				x = mx;
				y = my;

				health = inHealth;
				mass = inMass;
				fps = 200;
				friction = inFriction;
				imageString = imageStr;
				img = image;
				movingL = false;
				movingR = false;
				hitTop = false;
				hitBottom = false;
				hitSide = false;
				gravity = inG;
				constructRects();
			}

		public Enemy(String inName, int inID, double inX, double inY, double yVelocity, double inGravity, String imageStr, Image inIMG)
			{
				name = inName;
				id = inID;
				x = inX;
				y = inY;
				xSpeed = 10;
				ySpeed = yVelocity;
				health = 100;
				damage = 5;
				mass = 200;
				gravity = inGravity;
				fps = 200;
				friction = .1;
				height = 175;
				width = 100;
				imageString = imageStr;
				img = inIMG;
				movingL = false;
				movingR = false;
				hitTop = false;
				hitBottom = false;
				hitSide = false;

				constructRects();
			}

		public void constructRects()
			{
				rectAll = new Rectangle((int) x, (int) y, width, height);
				rectTop = new Rectangle((int) x + 15, (int) y, 70, 21);

				rectUpperL = new Rectangle((int) x - 1, (int) y, 1, height - 20);
				rectUpperR = new Rectangle((int) x + 100, (int) y, 1, height - 20);

				rectFeetL = new Rectangle((int) x + 1, (int) y + height - 19, 1, 19);
				rectFeetR = new Rectangle((int) x + width - 1, (int) y + height - 19, 1, 19);

				rectLeft = new Rectangle((int) x, (int) y, 1, height - 1);
				rectRight = new Rectangle((int) x + width - 1, (int) y, 1, height - 1);

				rectUpper = new Rectangle((int) x, (int) y, width, height - 20);
				rectFeet = new Rectangle((int) x + 1, (int) y + height - 19, width - 2, 18);
				rectBottom = new Rectangle((int) x, (int) y + height - 1, width, 2);

			}

		public void setDude(Dude inDude)
			{
				dude = inDude;
			}

		public Dude hit(Dude d)
			{
				double plyrdmg = damage + (Math.random() * 6 - 3); // dmg is within 3 of damage
				boolean crit = (Math.random() * 10 >= 9); // 1 in 10 chance of critical damage
				if (crit)
					plyrdmg *= 2; // critical damage is a (for now) 2x multiplier
				d.setHealth(d.getHealth() - plyrdmg); // make sure to add these methods in Dude**********************************************
				return d;
			}

		public void takeDamage(double impact) // integer parameter will later become a bullet
			{
				double enmydmg = impact + (Math.random() * 6 - 3); // same logic as player hitting zombie, its only fair
				boolean crit = (Math.random() * 10 >= 9);
				if (crit)
					enmydmg *= 2;
				health -= enmydmg;
			}

		public String getSaveString()
			{
				return getType() + " " + name + " " + id + " " + x + " " + y + " " + fps + " " + imageString + " " + mass + " " + friction + " " + health;
			}

		public String getType()
			{
				return "PhysicsEntity";
			}

		public String getImageString()
			{
				return imageString;
			}

		public void construct()
			{
				xSpeed = 0;
				ySpeed = 0;
				gravity = 7;

				int x2 = (int) (x + (img.getWidth(null) * .8)); // alt value .75

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
			
				if (xSpeed < 0)
					{
						
					}
				else
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

		public double getGravity()
			{
				return gravity;
			}

		public void convert(double inx, double iny, int inID)
			{
				x = inx;
				y = iny;
				rectShift((int) inx, (int) iny);
				id = inID;
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
				return id;
			}

		public void setID(int inID)
			{
				id = inID;
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

		public void step(double infps, ArrayList<Entity> e)
			{
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
						ySpeed += gravity / fps;
					}
				x += (xSpeed / infps);
				y += (ySpeed / infps);
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
					}

				rectShift((int) x + 5, (int) y);
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

		public void debug(Entity e) // moves this entity out of e if they are
									// touching
			{
				if (!rectAll.intersects(e.getRectangle()) || e == this)
					return;
				if (rectTop.intersects(e.getRectangle()) && rectBottom.intersects(e.getRectangle()))
					{
						y = e.getY() - rectAll.getHeight();
						ySpeed = 0;
					}
				else if (!rectTop.intersects(e.getRectangle()) && rectBottom.intersects(e.getRectangle()))
					{
						y = e.getY() - rectAll.getHeight() + 1;
						ySpeed = 0;
					}
				else if (rectTop.intersects(e.getRectangle()) && !rectBottom.intersects(e.getRectangle()))
					{
						y = e.getY() + e.getRectangle().getHeight() + 1;
						ySpeed = 0;
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
						x = e.getX() + rectAll.getWidth() + 1;
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

		public void checkCollision(Entity e)
			{
				if (e.getRectangle() == null)
					return;
				if (e instanceof Bullet)
					{
						e.checkCollision(this);
					}
				else if (rectAll.intersects(e.getRectangle()))
					col(e);

			}

		public void frictionWith(Entity e)
			{
				// recude this's speed but not the other one
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
				System.out.println("pronz");
			}

		public void sync(Entity e)
			{

			}

		@Override
		public Rectangle getEditorRectangle()
			{
				return rectAll;
			}

		public void getPlayerLocation(double inx, double iny)
			{

			}

		public String toString()
			{
				return name + " " + id + " " + x + " " + y + " " + fps + " ";
			}

		public double getFPS()
			{
				return fps;
			}

		public double getDamage()
			{
				return damage;
			}

		@Override
		public Enemy getEnemy()
			{

				return null;
			}

	}