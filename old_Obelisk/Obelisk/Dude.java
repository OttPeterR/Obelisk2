import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Dude
	{
		private double				x, y, xSpeed, ySpeed, health, jump, fps, mass, gravity;
		private int					maxWeps, stepSize, jumpForce, playerHeight, playerWidth, mx, my;
		private Rectangle			rectAll, rectTop, rectLeft, rectRight, rectFeet;
		private ArrayList<Weapon>	weps;
		private boolean				fall, facingRight, jumping;
		private Weapon				currentWep;
		private ArrayList<Entity>	entities;
		private Graphics2D			g;
		Image						hitBox;
		/* legs */private Image		stillR, stillL, runR, runL, midAirR, midAirL;
		/* body */private Image		bodyR, bodyL;
		/* arms */private Image		armsRifleR, armsRifleL, armsPistolR, armsPistolL;
		/* head */private Image		headR, headL;
		/* Array */private Image[]	images;
		/* final */private Image	finalImage, finalLegs, finalHead, finalBody, finalArms;

		public Dude(Image[] tD, double frames, double inx, double iny, double inxs, double inys, ArrayList<Entity> e) // test
																														// constructor
			{
				x = inx;
				y = iny;
				xSpeed = inxs;
				ySpeed = inys;
				images = tD;
				entities = e;
				fps = frames;
				loadImages(tD);

				construct();
			}

		public Dude(Image[] tD, double frames, double inx, double iny, double inxs, double inys, boolean f, boolean j, ArrayList<Entity> e, double heal) // test
		// constructor
			{
				x = inx;
				y = iny;
				xSpeed = inxs;
				ySpeed = inys;
				images = tD;
				entities = e;
				fps = frames;
				fall = f;
				jumping = j;
				loadImages(tD);

				construct();
				health = heal;
			}

		public Dude(Image[] tD, double frames, double inx, double iny, double inxs, double inys, boolean f, boolean j, ArrayList<Entity> e, double hlth, Weapon w1, Weapon w2) // test
																																												// constructor
			{
				x = inx;
				y = iny;
				xSpeed = inxs;
				ySpeed = inys;
				images = tD;
				entities = e;
				fps = frames;
				jumping = j;
				fall = f;
				weps.add(w1);
				weps.add(w2);
				health = hlth;
				loadImages(tD);

				construct();
			}

		public void construct()
			{
				playerHeight = 200; //was 175
				playerWidth = 100;

				gravity = 5000;
				mass = 500;
				health = 50;

				mx = 0;
				my = 0;

				stepSize = 700;
				jumpForce = -2000;

				rectAll = new Rectangle((int) x, (int) y, playerWidth, playerHeight);
				rectTop = new Rectangle((int) x, (int) y, playerWidth, 20);
				rectFeet = new Rectangle((int) x, (int) y + 116 + 25, playerWidth, 55);
				rectLeft = new Rectangle((int) x, (int) y + 21, playerWidth / 2, 100 + 25);
				rectRight = new Rectangle((int) x + 51, (int) y + 21, playerWidth / 2, 100 + 25);
			}

		private void rectUpdate()
			{
				rectAll.setLocation((int) x, (int) y);
				rectTop.setLocation((int) x, (int) y);
				rectFeet.setLocation((int) x, (int) y + 116 + 25);
				rectLeft.setLocation((int) x, (int) y + 21);
				rectRight.setLocation((int) x + 51, (int) y + 21);
			}

		public void loadImages(Image[] imgs)
			{
				images = imgs;
				bodyR = imgs[0];
				bodyL = imgs[1];
				stillR = imgs[2];
				stillL = imgs[3];
				runR = imgs[4];
				runL = imgs[5];
				midAirR = imgs[6];
				midAirL = imgs[7];
				armsRifleR = imgs[8];
				armsRifleL = imgs[9];
				armsPistolR = imgs[10];
				armsPistolL = imgs[11];
				headR = imgs[12];
				headL = imgs[13];
				hitBox = imgs[14];
			}

		public void render(Graphics2D gfx)
			{
				if (health < 20)
					{
						health += 1 / fps;
						stepSize=250;
						jumpForce=-700;
					}
				else if (health < 50)
					{
						health += 3 / fps;
						stepSize=360;
						jumpForce=-1400;
					}
				else if (health < 95)
					{
						health += 7 / fps;
						stepSize=700;
						jumpForce=-2000;
					}
				else if(health<100)
					{
						health+=1/fps;
						stepSize=700;
						jumpForce=-2000;
					}
				if (xSpeed >= 30 || xSpeed <= -30)
					{
						if (xSpeed > 0)//running right
							{
								gfx.drawImage(runR, (int) x - 40, (int) y + 80, null);
								gfx.drawImage(bodyR, (int) x + 30, (int) y + 15, null);
							}
						else
							//running left
							{
								gfx.drawImage(runL, (int) x, (int) y + 80, null);
								gfx.drawImage(bodyL, (int) x + 40, (int) y + 15, null);
							}
					}
				else
					{
						if (facingRight)//still right
							{
								gfx.drawImage(stillR, (int) x + 35, (int) y + 80, null);
								gfx.drawImage(bodyR, (int) x + 30, (int) y + 15, null);
							}
						else
							//still left
							{
								gfx.drawImage(stillL, (int) x + 35, (int) y + 80, null);
								gfx.drawImage(bodyL, (int) x + 40, (int) y + 15, null);
							}
					}
				// //////////////////////////////
				if (mx > x + 50)
					{
						gfx.drawImage(armsRifleR, (int) x + 20, (int) y + 25, null);
						gfx.drawImage(headR, (int) x + 40, (int) y, null);
						// finalArms=armsRifleR;
						// finalHead=headR;
					}
				else
					{
						gfx.drawImage(armsRifleL, (int) x - 40, (int) y + 25, null);
						gfx.drawImage(headL, (int) x + 40, (int) y, null);
						// finalArms=armsRifleL;
						// finalHead=headL;
					}
				// gfx.drawImage(finalHead, (int)x+40, (int)y, null);
				// gfx.drawImage(finalArms, (int)x, (int)y+30, null);
				// gfx.drawImage(finalLegs, (int)x, (int)y+70, null);
			}

		public double getPlayerHeight()
			{
				return playerHeight;
			}

		public double getPlayerWidth()
			{
				return playerWidth;
			}

		public double getHealth()
			{
				return health;
			}

		public void setHealth(double h)
			{
				health = h;
			}

		public void hurt(double h)
			{
				health -= h;
			}

		public double getX()
			{
				return x;
			}

		public double getY()
			{
				return y;
			}

		public double getxSpeed()
			{
				return xSpeed;
			}

		public void setySpeed(double iny)
			{
				ySpeed = iny;
			}

		public void setxSpeed(double inx)
			{
				xSpeed = inx;
			}

		public void addxSpeed(double inx)
			{
				xSpeed += inx;
			}

		public void addySpeed(double iny)
			{
				ySpeed = +iny;
			}

		public double getySpeed()
			{
				return ySpeed;
			}

		public void setY(double iny)
			{
				y = iny;
			}

		public void setX(double inx)
			{
				x = inx;
			}

		public boolean isJumping()
			{
				return jumping;
			}

		public Rectangle getRectangle()
			{
				return rectAll;
			}

		public void drawHUD(Graphics2D g) // HUD
			{

			}

		public Dude convert(double inx, double iny, double inxs, double inys, boolean f, ArrayList<Entity> e, double heal)
			{
				return new Dude(images, fps, inx, iny, inxs, inys, fall, jumping, e, heal);
			}

		public void updateEntities(ArrayList<Entity> ent)
			{
				entities = ent;
			}

		public void updateDirection(double inmx, double inmy)
			{
				mx = (int) inmx;
				my = (int) inmy;

				if (mx > (x + 50))
					facingRight = true;
				else
					facingRight = false;
				// also do gun rotation here
			}

		public void moveRight() // movement
			{
				if (xSpeed < stepSize)
					xSpeed += stepSize / 10;
			}

		public void sprintRight()
			{
				if (xSpeed < 2 * stepSize)
					xSpeed += 2 * stepSize / 10;
			}

		public void moveLeft() // movement
			{
				if (xSpeed > -stepSize)
					xSpeed -= stepSize / 10;
			}

		public void sprintLeft()
			{
				if (xSpeed > -2 * stepSize)
					xSpeed -= 2 * stepSize / 10;
			}

		public void jump()
			{
				jumping = true;
			}

		public void checkCollisions()
			{
				fall = true;

				double bottomFric = 0;

				boolean hitT = false;
				boolean hitB = false;
				boolean hitS = false;

				for (int i = 0; i < entities.size(); i++)
					{

						if (entities.get(i).getRectangle() != null && !(entities.get(i) instanceof Bullet))
							{
								if (entities.get(i).getRectangle().intersects(rectAll))// collides with player
									{
										Rectangle r = entities.get(i).getRectangle();
										collide(entities.get(i));//for PhysicsEntities only

										if (r.intersects(rectFeet) && !(r.intersects(rectRight) || r.intersects(rectLeft)))// step up onto an object
											{
												if (y > entities.get(i).getY() - 175)
													{
														y = entities.get(i).getY() - 175;
														rectUpdate();
													}
												double newFric = entities.get(i).getFriction();

												if (newFric > bottomFric)
													bottomFric = newFric;
												if (ySpeed > 0)
													ySpeed = 0;
												hitB = true;
											}
										else if (r.intersects(rectTop) && !(r.intersects(rectRight) || r.intersects(rectLeft)))
											{
												y = entities.get(i).getY() + entities.get(i).getRectangle().getHeight();
												rectUpdate();

												if (ySpeed < 0)
													ySpeed = 0;

											}
										if (r.intersects(rectLeft) && r.intersects(rectRight))
											{
												y = entities.get(i).getY() + entities.get(i).getRectangle().getHeight();
												rectUpdate();

												hitS = true;

											}
										else if (r.intersects(rectLeft))
											{
												x = entities.get(i).getX() + r.getWidth() + 1;
												rectUpdate();

												if (xSpeed < 0)
													xSpeed = 0;
												hitS = true;
											}
										else if (r.intersects(rectRight))
											{
												x = entities.get(i).getX() - 101;
												rectUpdate();

												if (xSpeed > 0)
													xSpeed = 0;
												hitS = true;
											}

										//debug(entities.get(i));
									}
							}
					}
				if (hitB)
					{
						ySpeed = 0;
						fall = false;
						xSpeed -= xSpeed * bottomFric;
						if ((xSpeed > -.01 && xSpeed < 0) || (xSpeed > 0 && xSpeed < .01))
							xSpeed = 0;
					}
				else if (!hitB)
					{
						fall = true;
						ySpeed += gravity / fps;
					}

				if (jumping && hitB && !fall && !hitT && !hitS)
					{
						ySpeed = jumpForce;
						fall = true;
						jumping = false;
					}
				jumping = false;

				// some how this fixes jumping twice per space
				// press

				x += xSpeed / fps;
				y += ySpeed / fps;

			}

		public void collide(Entity e)
			{
				if (e instanceof Bullet || e instanceof StaticEntity)
					return;

				// m1=mass
				// xs1=xSpeed
				// ys1=ySpeed
				double m2 = e.getMass();
				double xs2 = e.getXSpeed();
				double ys2 = e.getYSpeed();

				// e.setXSpeed((xs2 * (m2 - mass) + 2 * mass * xSpeed) / (m2 +
				// mass));
				// e.setYSpeed((ys2 * (m2 - mass) + 2 * mass * ySpeed) / (m2 +
				// mass));

				e.setXSpeed(xSpeed);
				e.setYSpeed(ySpeed);

				xSpeed = ((xSpeed * (mass - m2) + 2 * m2 * xs2) / (mass + m2));
				ySpeed = (-1 * (ySpeed * (m2 - mass) + 2 * mass * ys2) / (m2 + mass));

				if (ySpeed == 0 && e.getRectangle().intersects(rectTop))
					{
						e.setYSpeed(-20);
					}
			}

		public void debug(Entity e)
			{
				if (e instanceof Bullet)
					return;

				if (e instanceof StaticEntity)
					{
						if (!rectAll.intersects(e.getRectangle()) || e == this)
							return;

						if (rectTop.intersects(e.getRectangle()) && rectFeet.intersects(e.getRectangle()))
							{
								y = e.getY() - 200;
								rectUpdate();
							}
						else if (!rectTop.intersects(e.getRectangle()) && rectFeet.intersects(e.getRectangle()))
							{
								y = e.getY() - 201;
								rectUpdate();
							}
						else if (rectTop.intersects(e.getRectangle()) && !rectFeet.intersects(e.getRectangle()))
							{
								y = e.getY() + e.getRectangle().getHeight() + 1;
								rectUpdate();
							}
						if (rectLeft.intersects(e.getRectangle()) && rectRight.intersects(e.getRectangle()) && !rectTop.intersects(e.getRectangle()))
							{
								y = e.getY() - (e.getRectangle().getHeight() + 1);
								rectUpdate();
							}
						else if (rectLeft.intersects(e.getRectangle()) && !rectRight.intersects(e.getRectangle()))
							{
								x = e.getX() + e.getRectangle().getWidth();
								rectUpdate();
							}
						else if (!rectLeft.intersects(e.getRectangle()) && rectRight.intersects(e.getRectangle()))
							{
								x = e.getX() - rectAll.getWidth();
								rectUpdate();
							}

						return;
					}
				PhysicsEntity s = new PhysicsEntity("", fps, hitBox, "PlayerHitBox.gif", 250, 0, -.001);
				e.debug(s);

			}

		public boolean isFalling()
			{
				return fall;
			}

		public void shoot(int inx, int iny)
			{
				if (currentWep == null)
					return;
				if (currentWep.canShoot())
					entities.add(currentWep.shoot());
				else
					currentWep.reload();
			}

		public void aim(int inx, int iny)
			{
				// rotate and aim the gun toward
			}

		public void switchWeps()
			{

			}

		public void pickUpWep(Weapon w)
			{
				weps.add(w);
				// remember to combine ammo first
			}

		public void combineAmmo(Weapon w)
			{
				for (int i = 0; i < weps.size(); i++)
					{
						if (w.getName().equals(weps.get(i).getName()))
							weps.get(i).addAmmo(w);
					}
			}

	}
