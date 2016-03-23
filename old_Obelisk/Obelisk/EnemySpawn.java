import java.util.*;
import java.awt.*;

public class EnemySpawn implements Entity
	{
		Enemy				en;
		int					enemiesSpawned;
		String				name, imgString;
		Image				img;
		ArrayList<Entity>	eneArray;
		double				x, y;
		double				spawnTime, curTime;

		public EnemySpawn(String n, Image i, String is, Enemy inEnemy)
			{
				name = n;
				x = 0;
				y = 0;
				img = i;
				eneArray = new ArrayList<Entity>();
				imgString = is;
				en = inEnemy;
				enemiesSpawned = 0;
				spawnTime = 4;
				curTime = 0;
			}

		public EnemySpawn(String n, double inx, double iny, Image i, String is, Enemy inEnemy)
			{
				name = n;
				x = inx;
				y = iny;
				img = i;
				eneArray = new ArrayList<Entity>();
				imgString = is;
				en = inEnemy;
				enemiesSpawned = 0;
				spawnTime = 4;
				curTime = 0;
			}

		public boolean shouldSpawn()
			{
				if (eneArray.size() < 5)
					return true;
				else
					return false;
			}

		public void addZamby(double fps, Entity e, ArrayList<Entity> entities)
			{
				if (curTime>=spawnTime)
					{
						eneArray.add(e);
						entities.add(e);
						enemiesSpawned++;
						curTime=0;
					}
				curTime+=1/fps;
			}

		public Enemy spawn()
			{
				enemiesSpawned++;
				en.setX(x);
				en.setY(y);
				return new Enemy(en.getName(), en.getID(), (int) x, (int) y, en.getFPS(), en.getImage(), en.getImageString(), en.getMass(), en.getFriction(), en.getHealth(), (int) en.getDamage(), en.getGravity());
			}

		public void renderInEditor(Graphics2D g)
			{
				g.drawImage(img, (int) x, (int) y, null);
			}

		public Enemy getEnemy()
			{
				Enemy ene = new Enemy(en.getName(), en.getID(), (int) en.getX(), (int) en.getY(), en.getFPS(), en.getImage(), en.getImageString(), en.getMass(), en.getFriction(), en.getHealth(), (int) en.getDamage(), en.getGravity());
				eneArray.add(ene);
				return ene;
			}

		public int getEnemiesSpawned()
			{
				return enemiesSpawned;
			}

		public void renderInGame(Graphics2D g)
			{
				g.drawImage(img, (int) x, (int) y, null);
			}

		public String getSaveString()
			{
				return "EnemySpawn" + " " + name + " " + x + " " + y + " " + imgString;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String n)
			{
				enemiesSpawned++;
			}

		public double getGravity()
			{
				return 0;
			}

		public String getType()
			{
				return "EnemySpawn";
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
				return null;
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
				return -1;
			}

		public void setID(int id)
			{
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
				return enemiesSpawned;
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
				if (e.getType().equals("Bullet"))
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
				return .0001;
			}

		public void setHealth(double h)
			{
			}

		public void damage(double h)
			{
			}

		public Rectangle getEditorRectangle()
			{

				return new Rectangle((int) x, (int) y, img.getWidth(null), img.getHeight(null));
			}

		public void sync(Entity e)
			{

			}

		public String getImageString()
			{

				return imgString;
			}

		public void checkCollision(Entity e)
			{

			}

		public void applySpeed()
			{
				for (int i = 0; i < eneArray.size(); i++)
					{
						if (eneArray.get(i).removeMe())
							{
								eneArray.remove(i);
								i--;
							}
					}
			}

		public void debug(Entity e)
			{

			}
	}
