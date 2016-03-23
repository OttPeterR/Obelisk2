import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AIManager
{
	int							zombiesKilled, points=7500;
	int							zombiesLeftInRound, level, zombieCap;
	private ArrayList<AINode>	nodes;
	private ArrayList<AINode>	spawnNodes;
	ArrayList<Zombie>			zombies;
	Store						store;
	private ArrayList<Dude>		players;
	private ArrayList<Entity>	ents;
	private double				fps, timeUntilNextManage, levelSetUpTime,
								fullSetUpTime = 25;
	private boolean				setUp;
	Clip						music;
	boolean						bMusic1, bMusic2, bMusic3;
	long							musicTime	= 0;

	// How to work AIManager
	// 1 load ents
	// 2 run gatherAI at start up

	private void gatherAI()
	{
		for (int i = 0; i < ents.size(); i++)
		{
			if (ents.get(i) instanceof Zombie)
			{
				zombies.remove(ents.get(i));
				zombies.add((Zombie) ents.get(i));
			}
		}
	}

	public void startUp(ArrayList<AINode> ns, ArrayList<Entity> playEnts)
	{
		setUp = true;
		level = 0;
		zombies = new ArrayList<Zombie>();
		players = new ArrayList<Dude>();
		nodes = new ArrayList<AINode>();
		spawnNodes = new ArrayList<AINode>();
		ents = playEnts;
		gatherAI();
		this.nodes = ns;
		ents = playEnts;
		for (int i = 0; i < ents.size(); i++)
		{
			if (ents.get(i) instanceof Zombie)
				zombies.add((Zombie) ents.get(i));
			else if (ents.get(i) instanceof Dude)
			{
				players.add((Dude) ents.get(i));
			}
		}
		for (int i = nodes.size() - 1; i >= 0; i--)
		{
			if (nodes.get(i).type == AINode.SPAWNER)
				spawnNodes.add(nodes.get(i));
		}
		try
		{
			music.stop();
		}
		catch (Exception exep)
		{}
		music = loadSound("music.wav");
		music.start();
		((FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-20);
		points = 100000;
	}
	private Clip loadSound(String name)
	{
		try
		{
			URL soundURL = getClass().getResource("/Sound/" + name);
			AudioInputStream in = AudioSystem.getAudioInputStream(soundURL);
			Clip clip = AudioSystem.getClip();
			clip.open(in);
			in.close();
			return clip;
		}
		catch (Exception e)
		{}
		return null;
	}
	public void manage(double frames, final Rectangle2D screenRect)
	{
		fps = frames;
		manageMusic();
		if (timeUntilNextManage <= 0)
		{
			timeUntilNextManage = 3;
			if (zombiesLeftInRound > 0)
				new Thread(new Runnable()
				{
					public void run()
					{
						for (int i = 0; i <= level / 4; i++)
							manageLevelSpawns(screenRect);
					}
				}).start();
			managePlayers();
			instruct();
		}
		if (!setUp)
		{
			if (zombiesLeftInRound == 0)
			{
				setUp = true;
				levelSetUpTime = 0 + fullSetUpTime;
				if (level % 7 == 0)
					levelSetUpTime = 0 + fullSetUpTime * 3;
			}
		}
		else
		{
			zombiesLeftInRound = zombies.size();
			if (zombiesLeftInRound == 0)
			{
				levelSetUpTime -= 1 / fps;
				if (levelSetUpTime <= 0)
				{
					if (store != null)
						store.setVisible(false);
					setUp = false;
					setUpNextLevel();
				}
			}
			else
				setUp = false;
		}
		timeUntilNextManage -= 1 / fps;
	}

	private void instruct()
	{
		for (int i = zombies.size() - 1; i >= 0; i--)
		{
			instruct(zombies.get(i));
			if ((zombies.get(i).lifeTime > 190 && (zombies.get(i).target != null && dist(
					zombies.get(i), zombies.get(i).target) > 500))
					|| (zombies.get(i).target != null && dist(zombies.get(i),
							zombies.get(i).target) > 7000) || (Math.hypot(zombies.get(i).xSpeed, zombies.get(i).ySpeed) > 10000))
			{
				zombies.get(i).damage(10);
				zombies.get(i).timeUntilHealthRegen += 10;
			}
		}
	}
	private double dist(Biped a, Biped b)
	{
		return Math.hypot(a.x - b.x, a.y - b.y);
	}

	public void notifyZombieKilled(Zombie z)
	{
		zombiesKilled++;
		points += z.fullHealth;
		zombies.remove(z);
		zombiesLeftInRound--;
	}

	private void managePlayers()
	{
		if (players.size() == 0)
			for (int i = ents.size() - 1; i >= 0; i--)
				if (ents.get(i) instanceof Dude)
					players.add((Dude) ents.get(i));
		for (int i = players.size() - 1; i >= 0; i--)
			if (players.get(i).isDead())
				players.remove(i);
	}

	private void setUpNextLevel()
	{
		level++;
		for (int i = zombies.size() - 1; i >= 0; i--)
			zombies.get(i).health = 0;
		zombieCap = 25 + (6 * players.size());
		zombiesLeftInRound = (int) ((.2 * level) * zombieCap);
	}

	public int getZombiesLeft()
	{
		return zombiesLeftInRound;
	}

	public boolean isSetUp()
	{
		return setUp;
	}

	private void manageLevelSpawns(Rectangle2D screenRect)
	{
		if (canSpawn())
		{
			int lev = level / 4;
			int rand = (int) (100 * Math.random());
			int randSpawns = (int) (5 * Math.random());
			for (int i = 0; i < randSpawns; i++)
			{
				AINode node = getRandNode(screenRect);

				switch (lev)
				{
					case (0):
						spawnNormal(node);
						if (rand < 30)
							spawnExplosive(node);
						break;
					case (1):
						if (rand < 65)
							spawnNormal(node);
						else if (rand < 80)
							spawnExplosive(node);
						else
							spawnFastSwarm(node);
						break;
					case (2):
						if (rand < 65)
							spawnNormal(node);
						else if (rand < 85)
						{
							spawnFastSwarm(node);
							if (rand < 75)
								spawnExplosive(node);
						}
						else
							spawnStrong(node);
						break;
					case (3):
						if (rand < 60)
						{
							spawnNormal(node);
							spawnExplosive(node);
							spawnNormal(node);
						}
						else if (rand < 80)
						{
							spawnNormal(node);
							spawnStrong(node);
						}
						else
						{
							spawnStrong(node);
							spawnNormal(node);
						}
						break;
					case (4):
						if (rand < 45)
						{
							spawnFastSwarm(node);
							spawnFastSwarm(node);
						}
						else if (rand < 75)
						{
							spawnNormal(node);
							spawnNormal(node);
						}
						else if (rand < 90)
						{
							spawnExplosive(node);
							spawnStrong(node);
						}
						else
							spawnMega(node);
						break;
					case (5):
						if (rand < 50)
						{
							spawnFastSwarm(node);
							spawnStrong(node);
						}
						else if (rand < 70)
						{
							spawnFastSwarm(node);
							spawnNormal(node);
						}
						else
							spawnMega(node);
						break;
					default:
					{
						if (rand < 30)
						{
							spawnNormal(node);
						}
						else if (rand < 45)
						{
							spawnExplosive(node);
							spawnFastSwarm(node);
							spawnFastSwarm(node);
						}
						else if (rand < 70)
						{
							spawnStrong(node);
							spawnFastSwarm(node);
							spawnStrong(node);
						}
						else if (rand < 90)
						{
							spawnMega(node);
							spawnNormal(node);
						}
						else
						{
							spawnFastSwarm(node);
							spawnFastSwarm(node);
							spawnMega(node);
						}
					}
				}
			}
		}
	}

	private AINode getRandNode(Rectangle2D screenRect)
	{
		if (spawnNodes.size() > 0)
		{
			AINode n = spawnNodes
					.get((int) (spawnNodes.size() * Math.random()));
			for (int i = 0; i < 6; i++)
			{

				n = spawnNodes.get((int) (spawnNodes.size() * Math.random()));
				int tries = 0;
				while (n.rect.intersects(screenRect) && tries < 30)
				{
					tries++;
					n = spawnNodes
							.get((int) (spawnNodes.size() * Math.random()));
				}
			}
			return n;
		}
		return null;
	}

	private boolean canSpawn()
	{
		if (nodes.size() == 0)
			return false;
		boolean b = zombies.size() < zombieCap && !setUp;
		int healthVal = 0;
		for (int i = zombies.size() - 1; i >= 0; i--)
		{
			healthVal += zombies.get(i).fullHealth;
		}
		if (healthVal > 1200)
			b = false;
		return b;
	}

	public double setUpTimeLeft()
	{
		return levelSetUpTime;
	}

	private void spawnFastSwarm(AINode n)
	{
		int limit = zombiesLeftInRound - zombies.size();
		int num = 3 + (int) (Math.random() * 3);
		if (num > limit)
			num = limit;
		for (int i = num; i > 0; i--)
		{
			Zombie z = AILibrary.ZombieFast();
			z.translateTo(n.x, n.y);
			zombies.add(z);
			ents.add(z);
		}
	}

	private void spawnNormal(AINode n)
	{
		int limit = zombiesLeftInRound - zombies.size();
		int num = 3 + (int) (Math.random() * 3);
		if (num > limit)
			num = limit;
		for (int i = num; i > 0; i--)
		{
			Zombie z = AILibrary.ZombieNormal();
			z.translateTo(n.x, n.y);
			zombies.add(z);
			ents.add(z);
		}
	}

	private void spawnExplosive(AINode n)
	{
		if (zombiesLeftInRound - zombies.size() > 0)
		{
			Zombie z = AILibrary.ZombieExplosive();
			z.translateTo(n.x, n.y);
			zombies.add(z);
			ents.add(z);
		}
	}

	private void spawnStrong(AINode n)
	{
		int limit = zombiesLeftInRound - zombies.size();
		int num = 1 + (int) (Math.random() * 2.9);
		if (num > limit)
			num = limit;
		for (int i = num; i >= 0; i--)
		{
			Zombie z = AILibrary.ZombieStrong();
			z.translateTo(n.x, n.y);
			zombies.add(z);
			ents.add(z);
		}
	}

	private void spawnMega(AINode n)
	{
		if (zombiesLeftInRound - zombies.size() > 0)
		{
			Zombie z = AILibrary.ZombieMega();
			z.translateTo(n.x, n.y);
			zombies.add(z);
			ents.add(z);
		}
	}

	// private double dist(AINode n, Rectangle2D sr)
	// {
	// return Math.hypot(sr.getCenterX() - n.x, sr.getCenterY() - n.y);
	// }
	private void instruct(Zombie z)
	{
		if (z.target == null)
			z.target = findDude(z);// may return null
		if (z.target == null)
			roam(z);
	}

	private void roam(Zombie z)
	{
		if (onCurrentNode(z, z.travelTo))// if he got to the end of his roam
											// path
			z.travelTo = findRoamNode(z);
	}

	private AINode findRoamNode(Zombie z)
	{
		ArrayList<AINode> cluster = getNodeCluster(z);
		if (cluster.size() == 0)
			return null;
		AINode rtrn = cluster.get(0);
		for (int i = 1; i < cluster.size(); i++)
		{
			if (distBetween(z, cluster.get(i)) > distBetween(z, rtrn))
				rtrn = cluster.get(i);
		}
		return rtrn;
	}

	private ArrayList<AINode> getNodeCluster(Biped b)
	{
		ArrayList<AINode> cluster = new ArrayList<AINode>();

		return cluster;
	}

	private Dude findDude(Zombie z)
	{
		if (players.size() == 0)
			return null;
		Dude rtrn = players.get(0);
		// for (int j = 0; j < players.size(); j++)
		// {// this loop finds if the zombie has a clear line of sight to the
		// // player
		// rtrn = players.get(j);
		// Line2D sight = new Line2D.Double(z.x, z.y - 100, rtrn.x, rtrn.y -
		// 100);
		// boolean inSight = true;
		// for (int i = ents.size() - 1; i > -1; i--)
		// {
		// ArrayList<Line2D.Double> lines =
		// ents.get(i).wireframes[0].getLineWireFrame();
		// for (int l = lines.size() - 1; l > -1; l--)
		// {
		// if (lines.get(l).intersectsLine(sight))
		// inSight = false;
		// }
		// }
		// if (inSight)
		// return rtrn;
		// }
		// for (int j = 0; j < players.size(); j++)
		// {
		// if (Math.hypot(z.x - players.get(j).x, z.y - players.get(j).y) < 450
		// && Math.abs(players.get(j).xSpeed) > 20)
		// return players.get(j);
		//
		// }
		return rtrn;
	}

	public void createLinks()
	{
		resetIDs();
		for (int i = 0; i < nodes.size(); i++)
		{
			nodes.get(i).ID = getNewID();
		}
		for (int i = nodes.size() - 1; i >= 0; i--)
		{
			nodes.get(i).links.clear();
			for (int j = nodes.size() - 1; j >= 0; j--)
			{
				if (nodes.get(i).inRange(nodes.get(j)))
					nodes.get(i).links.add(nodes.get(j));
			}
		}
	}

	private int getNewID()
	{
		int newID = (int) (Math.random() * 999999999);
		while (!origionalID(newID))
		{
			newID = (int) (Math.random() * 999999999);
		}
		return newID;
	}

	private boolean origionalID(int id)
	{
		boolean r = true;
		for (int i = 0; i < nodes.size(); i++)
		{
			if (id == nodes.get(i).ID)
				r = false;
		}
		return r;
	}

	private void resetIDs()
	{
		for (int i = 0; i < nodes.size(); i++)
		{
			nodes.get(i).ID = -1;
		}
	}

	private double distBetween(Biped b, AINode n)
	{
		return Math.hypot(b.x - n.x, b.y - 100 - n.y);
	}

	private boolean onCurrentNode(Biped b, AINode n)
	{
		return b.rect.intersects(n.rect);
	}
	private void manageMusic()
	{
		if (level < 6 || setUp)
		{
			if (!bMusic1)
			{
				bMusic1 = true;
				bMusic2 = false;
				bMusic3 = false;
				music.setMicrosecondPosition(0);

			}
			else if (bMusic1 && music.getMicrosecondPosition() > 72000000)
				music.setMicrosecondPosition(0);
		}
		else if (level < 16)
		{
			if (!bMusic2)
			{
				bMusic2 = true;
				bMusic1 = false;
				music.setMicrosecondPosition(77000000);
			}
			else if (bMusic2 && music.getMicrosecondPosition() > 109000000)
				music.setMicrosecondPosition(91300000);
		}
		else
		{
			if (!bMusic3)
			{
				bMusic3 = true;
				bMusic1 = false;
				music.setMicrosecondPosition(117000000);
			}
			else if (bMusic3 && music.getMicrosecondPosition() > 151000000)
				music.setMicrosecondPosition(130000000);
		}
	}
	public void pauseMusic()
	{
		musicTime = music.getMicrosecondPosition();
		music.stop();
	}
	public void resumeMusic()
	{
		music.start();
		music.setMicrosecondPosition(musicTime);
	}
}
