import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class PROGameDemo extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener
{
	private static final long	serialVersionUID	= 7890677435172846874L;
	public boolean				threaded			= false;
	private boolean				performance			= false;
	private Store				store;
	private long				lastStartTime		= System.nanoTime();
	private long				frameCap			= (long) (1E9 / 900);
	private ArrayList<Entity>	frontEnts, playEnts, backEnts;
	private float				volume;
	private ArrayList<AINode>	AINodes;
	private int					mx, my;
	private Wireframe			mouseWF;
	private boolean				M1, M3, ctrl, shift, keySpace, keyA, keyS, keyD, keyR, keyV, keyW;
	private boolean				slowMotionToggle	= false;

	private boolean				loading				= true, pause = false, reloading = false;
	private int					loadNum				= 1;
	private ArrayList<String>	loadingStatus;

	private String				levelName;

	private int					screenX, renderOffsetX = 0 * 4;
	// renderOffsetX = 5
	private int					screenY, renderOffsetY = 0 * 23;
	// renderOffsetY = 30
	private double				origX;
	private double				origY;

	private double				fps;
	private double				endRenderTime;
	private double				timeScale			= 1, targetTimeScale = 1;
	private boolean				blood				= false;
	private double				loadPercent			= 0;

	private Image				backbuffer;
	private Graphics			backG;
	AffineTransform				trans				= new AffineTransform();

	private Rectangle2D.Double	screenRect;

	private static final Color	PROColor			= Entity.PROColor;

	private Dude				dude;
	private Vect				spawnPoint			= new Vect(0, 0);
	private AIManager			aiManager;

	private Cursor				origCursor;
	private ArrayList<Clip>		sounds;
	private ArrayList<String>	soundNames;
	private double				gcTimer;

	public void setThreading(boolean b)
	{
		threaded = b;
	}
	public void init()
	{
		setTitle("Obelisk 2 by Peter Ott");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		loadingStatus = new ArrayList<String>();
		setResizable(true);

		this.setIconImage(Library.img("PROTekIconSmall.png"));

		backbuffer = createImage(Toolkit.getDefaultToolkit().getScreenSize().width + 8, Toolkit.getDefaultToolkit().getScreenSize().height);
		backG = backbuffer.getGraphics();
		backG.translate(renderOffsetX, renderOffsetY);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		setFocusable(true);
		this.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				screenX = getWidth();
				screenY = getHeight();
				setResolution(screenX, screenY);
			}
		});
	}
	public void setBlood(boolean b)
	{
		blood = b;
	}
	public void setLevel(String l)
	{
		levelName = l;
	}
	public void setResolution(int resX, int resY)
	{
		screenX = resX;
		screenY = resY;
		setSize(screenX, screenY);
		backbuffer = createImage(screenX, screenY);
		backG = backbuffer.getGraphics();
		screenRect = new Rectangle2D.Double(0, 0, screenX, screenY);
	}
	private void updateLoading(double percent, String status)
	{
		if (percent > 10)
			loadingStatus.add(0, percent + "% " + status);
		else
			loadingStatus.add(0, " " + percent + "% " + status);
		loadNum++;
		loadPercent = percent;
	}
	private void renderLoading(Graphics g)
	{
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.gray);
		backG.fillRect(0, 0, screenX + 50, screenY + 50);
		g.setColor(PROColor);

		g.setFont(new Font("MonoSpaced", Font.BOLD, 40));
		g.drawString("PROtekENGINE 2", (int) (screenX * .6), (int) (screenY * .88));
		g.setFont(new Font("MonoSpaced", Font.BOLD, 14));
		g.setColor(PROColor);
		// if (readyForNextLoad)
		if (!reloading)
			switch (loadNum)
			{
				case (1):
					updateLoading(01.2, "Rendering Elements");
					break;
				case (2):
					screenX = Toolkit.getDefaultToolkit().getScreenSize().width + 17;
					screenY = Toolkit.getDefaultToolkit().getScreenSize().height - 28;
					screenRect = new Rectangle2D.Double(0, 0, screenX, screenY);
					updateLoading(04.3, "Basic System Variables");
					break;
				case (3):

					origX = 0;
					origY = 0;
					ctrl = false;
					shift = false;
					fps = 100;
					timeScale = 1;
					updateLoading(8.6, "Controls");
					break;
				case (4):
					// mouse
					Vect[] mcoords = new Vect[3];
					mcoords[0] = new Vect(mx, my - 1);
					mcoords[1] = new Vect(mx + 1, my + 1);
					mcoords[2] = new Vect(mx - 1, my + 1);
					mouseWF = new Wireframe(mcoords, 3);

					origCursor = this.getCursor();
					Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(this.getX(), this.getY()), "");
					this.setCursor(c);

					updateLoading(13.1, "Settings");
					break;
				case (5):
					updateLoading(19.2, "Player Character");
					break;
				case (6):
					dude = DudeLibrary.Russ();
					updateLoading(26.1, "Creating Necessary Resources");
					break;
				case (7):
					frontEnts = new ArrayList<Entity>();
					playEnts = new ArrayList<Entity>();
					backEnts = new ArrayList<Entity>();
					sounds = new ArrayList<Clip>();
					soundNames = new ArrayList<String>();
					updateLoading(35.9, "Artificial Intelegence");
					break;
				case (8):
					AINodes = new ArrayList<AINode>();
					aiManager = new AIManager();
					updateLoading(38.0, "Loading Level");
					break;
				case (9):
					// load here
					store = new Store(dude, aiManager);
					Loader ldr = new Loader(frontEnts, playEnts, backEnts, AINodes, spawnPoint);
					ldr.load(levelName);
					updateLoading(87.3, "Testing Level");
					break;
				case (10):
					dude.translateTo(spawnPoint.x, spawnPoint.y);
					playEnts.add(dude);
					dude.loadArray(playEnts);
					aiManager.startUp(AINodes, playEnts);
					updateLoading(100, "Complete");
					break;
				default:
					loading = false;
					break;
			}
		else
			switch (loadNum)
			{
				case (1):
					Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(this.getX(), this.getY()), "");
					this.setCursor(c);
					updateLoading(12.4, "Resetting Player Character");
					break;
				case (2):
					dude = DudeLibrary.Russ();
					keyA = false;
					keyD = false;
					keyS = false;
					keySpace = false;
					ctrl = false;
					shift = false;
					updateLoading(25.1, "Reloading Level");
					break;
				case (3):
					frontEnts.clear();
					backEnts.clear();
					playEnts.clear();
					Loader ldr = new Loader(frontEnts, playEnts, backEnts, AINodes, spawnPoint);
					ldr.load(levelName);
					updateLoading(70.3, "Recomputing AI");
					break;
				case (4):
					aiManager = null;
					aiManager = new AIManager();
					updateLoading(82.8, "Testing Level");
					break;
				case (5):
					dude.translateTo(spawnPoint.x, spawnPoint.y);
					playEnts.add(dude);
					dude.loadArray(playEnts);
					aiManager = new AIManager();
					aiManager.startUp(AINodes, playEnts);
					store = new Store(dude, aiManager);
					System.gc();
					updateLoading(100, "Complete");
					break;
				default:
					pause = false;
					loading = false;
					break;
			}
		g.setColor(Color.black);
		g.drawString("Loading...", (int) (screenX * .025), screenY - 120);
		for (int i = loadingStatus.size() - 1; i >= 0; i--)
		{
			g.drawString(loadingStatus.get(i), (int) (screenX * .045) + 60, screenY - 120 - (i * 15));
		}
		g.fillRect((int) (screenX * .025), (int) (screenY * .9), (int) (screenX * .95), 20);
		g.setColor(PROColor);
		g.drawString(loadPercent + "", (int) ((screenX * .95) * ((double) loadPercent / 100)) + 45, (int) (screenY * .895));
		g.fillRect((int) (screenX * .025), (int) (screenY * .9), (int) ((screenX * .95) * ((double) loadPercent / 100)), 20);
	}
	// //////////////////////////////////////////////////////////Loading/\
	// //////////////////////////////////////////////////////////Rendering\/
	public void paint(Graphics g)
	{
		// super.paintComponents(g);
		Graphics2D gfx = (Graphics2D) g;
		if (!loading)
		{
			gcTimer -= 1 / fps;
			if (gcTimer <= 0)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						System.gc();
					}
				}).start();
				gcTimer = 10;
			}
			double elapsedTime = System.nanoTime() - lastStartTime;
			while (elapsedTime < frameCap)
			{
				elapsedTime = System.nanoTime() - lastStartTime;
			}
			fps = (fps * .9) + (.1 * (1E9 / elapsedTime)) / timeScale;
			lastStartTime = System.nanoTime();
			update(gfx);
		}
		else
			updateLoading(gfx);
		gfx.finalize();
		repaint();
	}
	public void updateLoading(Graphics g)
	{
		g.drawImage(backbuffer, 0, 0, null);
		if (backG != null)
			renderLoading(backG);
	}
	public void update(Graphics2D g)
	{
		((Graphics2D) backG).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderAll((Graphics2D) backG);

		if (this.targetTimeScale > .9)
			g.setComposite(java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8F));
		else
		{
			float compVal = (float) (.40 + (.3 * timeScale));
			g.setComposite(java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, compVal));
		} // motion blur

		g.drawImage(backbuffer, 0, 0, null);

	}
	private void renderAll(Graphics2D g)
	{
		if (!pause && !dude.isDead())
		{
			if (threaded)
				simulateTHREADED();
			else
				simulate();

			manageSound();
			manageTimeChange();

			scroll(dude.translation.x, dude.translation.y);

			g.setBackground(Color.black);
			g.clearRect(0, 0, screenX, screenY);

			renderArray(g, backEnts);
			renderArray(g, playEnts);
			renderArray(g, frontEnts);
			keyboardForDude();

			g.setColor(Color.RED);
			dude.renderHUD(g, trans, origX, origY, mx, my, screenX, screenY, aiManager);

			targetTimeScale = dude.getTargetTimeScale();
		}
		else if (dude.isDead())
		{
			pause = true;
			this.setCursor(origCursor);
			renderDeadHUD(g);
		}
		else if (pause)
		{
			mouseWF.translateTo(mx, my);
			renderPauseMenu(g);
		}
		g.setComposite(java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	private void renderDeadHUD(Graphics2D g)
	{
		g.drawImage(backbuffer, 0, 0, screenX, screenY, 0, 0, screenX, screenY, null);
		g.setColor(new Color(90, 0, 0, 15));
		g.fillRect(0, 0, screenX, screenY);

		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Game Over", 100, 200);
		g.setFont(new Font("Arial", Font.BOLD, 25));

		g.drawString("Level: " + aiManager.level, 120, 230);
		g.drawString("$" + aiManager.points, 120, 260);
		g.drawString("Killed by: " + dude.killerInfo.replace('_', ' '), 120, 290);

		g.drawString("Press R to restart", 120, screenY - 100);

	}
	private void renderPauseMenu(Graphics2D g)
	{
		if (timeScale != 1)
			dude.toggleTimeScale();
		keySpace = false;
		g.drawImage(backbuffer, 0, 0, screenX, screenY, 0, 0, screenX, screenY, null);
		g.setColor(new Color(0, 0, 0, 15));
		g.fillRect(0, 0, screenX, screenY);

		g.setColor(PROColor);
		g.setFont(new java.awt.Font("Arial", 1, 70));
		g.drawString("Esc - Resume", screenX / 8 - 70, screenY - 100);
		g.setFont(new java.awt.Font("Arial", 1, 20));
		g.drawString("Ctrl + R - Restart", screenX / 8 - 70, screenY - 50);

		g.drawString("Kills: " + aiManager.zombiesKilled, screenX / 7, screenY - 200);
		g.drawString("$" + aiManager.points, screenX / 7, screenY - 240);

		g.setFont(new java.awt.Font("Arial", 1, 30));
		if (threaded)
			g.drawString("Threading: Enabled", screenX - 350, screenY / 4);
		else
			g.drawString("Threading: Disabled", screenX - 350, screenY / 4);
		if (blood)
			g.drawString("Blood: Enabled", screenX - 350, screenY / 4 + 80);
		else
			g.drawString("Blood: Disabled", screenX - 350, screenY / 4 + 80);
		if (performance)
			g.drawString("Simple: Enabled", screenX - 350, screenY / 4 + 160);
		else
			g.drawString("Simple: Disabled", screenX - 350, screenY / 4 + 160);

		g.setFont(new java.awt.Font("Arial", 1, 20));
		g.drawString("Toggle: T", screenX - 230, screenY / 4 + 30);
		g.drawString("Toggle: B", screenX - 230, screenY / 4 + 110);
		g.drawString("Toggle: P", screenX - 230, screenY / 4 + 190);

		g.drawString("Movement: A, S, D", screenX / 8, 78);
		g.drawString("Jump: W", screenX / 8, 100);

		g.drawString("Shoot: Left Click", screenX / 8, 122);
		g.drawString("Aim: Right Click", screenX / 8, 144);
		g.drawString("Melee: F", screenX / 8, 188);
		g.drawString("Switch Weapons: Scroll", screenX / 8, 210);

		g.drawString("Interact: E", screenX / 8, 232);

		g.drawString("Slow-Motion: Space", screenX / 8, 254);
		g.drawString("Laser Pointer: L", screenX / 8, 298);
		g.drawString("Drop Weapon: J", screenX / 8, 320);

		g.drawString("Open Store during level switch: S", screenX / 8, 400);
	}
	private void renderArray(Graphics2D g, ArrayList<Entity> arr)
	{
		for (int i = 0; i < arr.size(); i++)
		{
			if (i < arr.size() && arr.get(i) != null)
				try
				{
					if (arr.get(i) instanceof Bullet || (arr.get(i).rect != null
							&& arr.get(i).rect.intersects(screenRect)))
						arr.get(i).render(g, trans, origX, origY);
				}
				catch (Exception e)
				{
					System.out.println(i + " " + arr.size());
					System.out.println(arr.get(i).name);
					e.printStackTrace();
				}
		}
	}
	private void scroll(double xScroll, double yScroll)
	{
		xScroll = (int) xScroll;
		yScroll = (int) yScroll;

		origX += xScroll;
		origY += yScroll;

		mx -= xScroll;
		my -= yScroll;
		screenRect.setFrame(origX, origY, screenRect.getWidth(), screenRect.getHeight());
	}
	private void manageTimeChange()
	{
		if (timeScale > targetTimeScale)// slowing down
		{
			timeScale -= (25 / fps);
			if (timeScale < targetTimeScale)
				timeScale = 0 + targetTimeScale;
		}
		else if (timeScale < targetTimeScale)// speeding up
		{
			timeScale += (14 / fps);
			if (timeScale > targetTimeScale)
				timeScale = 0 + targetTimeScale;
		}
	}
	// //////////////////////////////////////////////////////////Rendering/\
	// //////////////////////////////////////////////////////////Sound \/
	public void setVolume(float inVol)
	{
		volume = inVol;
	}
	private void manageSound()
	{
		for (int i = playEnts.size() - 1; i >= 0; i--)
		{
			if (playEnts.get(i) instanceof Sound)
			{
				processSound((Sound) playEnts.remove(i));
			}
		}
	}
	private void processSound(Sound s)
	{
		if (!s.name.equals("") && !haveAvailableSound(s.name, s.x, s.y))
			addNewSound(s);
	}
	private void addNewSound(Sound s)
	{
		try
		{
			URL soundURL = getClass().getResource("/Sound/" + s.name);
			AudioInputStream in = AudioSystem.getAudioInputStream(soundURL);
			Clip clip = AudioSystem.getClip();
			clip.open(in);
			in.close();
			sounds.add(clip);
			soundNames.add(s.name);
			setClip(clip, s.x, s.y);
		}
		catch (Exception e)
		{}
	}
	private boolean haveAvailableSound(String clipName, double inx, double iny)
	{
		for (int i = sounds.size() - 1; i >= 0; i--)
		{
			if (soundNames.get(i).equals(clipName))
				if (sounds.get(i).getFramePosition() == sounds.get(i).getFrameLength())
				{
					setClip(sounds.get(i), inx, iny);
					return true;
				}
		}
		return false;
	}
	private void setClip(Clip c, double inx, double iny)
	{
		c.setFramePosition(0);

		float vol = (float) (volume - (40 * (Math.hypot(dude.x - inx, dude.y - iny) / 5000)));
		if (vol > 6)
			vol = 6;
		else if (vol < -80)
			vol = -80;
		((FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN)).setValue(vol);
		float bal = (float) (inx - dude.x) / 1000;
		if (bal > 1)
			bal = 1;
		else if (bal < -1)
			bal = -1;
		((FloatControl) c.getControl(FloatControl.Type.BALANCE)).setValue(bal);

		c.start();
	}
	// //////////////////////////////////////////////////////////Sound /\
	// //////////////////////////////////////////////////////////Simulation\/
	private void simulate()
	{// just a collection of methods
		aiManager.manage(fps, screenRect);
		processArray(backEnts);
		processArray(playEnts);
		processArray(frontEnts);
	}
	private void simulateTHREADED()
	{
		ArrayList<Thread> threads = new ArrayList<Thread>();
		threads.add(new Thread(new Runnable()
		{
			public void run()
			{
				aiManager.manage(fps, screenRect);
			}
		}));
		threads.add(new Thread(new Runnable()
		{
			public void run()
			{
				processArrayTHREADED(playEnts);
			}
		}));
		threads.add(new Thread(new Runnable()
		{
			public void run()
			{
				processArrayTHREADED(backEnts);
			}
		}));
		threads.add(new Thread(new Runnable()
		{
			public void run()
			{
				processArrayTHREADED(frontEnts);
			}
		}));
		threads.get(0).start();
		threads.get(1).start();
		threads.get(2).start();
		threads.get(3).start();

		while (threads.get(0).isAlive() || threads.get(1).isAlive() || threads.get(2).isAlive() || threads.get(3).isAlive())
		{
			try
			{
				Thread.sleep(0, 1);
			}
			catch (InterruptedException e)
			{}
		}

	}
	private void processArrayTHREADED(ArrayList<Entity> arr)
	{
		syncArray(arr);
		stepArrayTHREADED(arr);
		checkCollisionsArrayTHREADED(arr);
		checkRemoves(arr);
	}
	private void processArray(ArrayList<Entity> arr)
	{// yet again another collection of methods
		syncArray(arr);
		checkCollisionsArray(arr, 0, arr.size());
		stepArray(arr, 0, arr.size());
		checkRemoves(arr);
	}
	private void checkCollisionsArrayTHREADED(final ArrayList<Entity> arr)
	{
		ArrayList<Thread> threads = new ArrayList<Thread>();
		int length = arr.size();
		int increm = arr.size() / Runtime.getRuntime().availableProcessors();// 35;
		if (increm == 0)
			increm = 1;
		final int increment = increm;
		while (length >= increment)
		{
			final int leng = length;
			threads.add(new Thread(new Runnable()
			{
				public void run()
				{
					checkCollisionsArray(arr, leng - increment, leng);
				}
			}));
			length -= increment;
		}
		if (length < increment && length > 0)
		{
			final int leng = length;
			threads.add(new Thread(new Runnable()
			{
				public void run()
				{
					checkCollisionsArray(arr, 0, leng);
				}
			}));
		}
		for (int i = 0; i < threads.size(); i++)
		{
			threads.get(i).setName("checkCollisionsArrayTHREADED");
			threads.get(i).start();
		}
		boolean allFinished = false;
		while (!allFinished)
		{
			allFinished = true;
			for (int i = threads.size() - 1; i >= 0; i--)
			{
				if (threads.get(i).isAlive())
					allFinished = false;
				else
					threads.remove(i);
			}
		}
	}
	private void checkCollisionsArray(ArrayList<Entity> arr, int start, int end)
	{
		for (int i = start; i < end; i++)
		{
			for (int j = arr.size() - 1; j > i; j--)
			{
				if (arr.get(i) instanceof Bullet || arr.get(i) instanceof AdhesiveEntity && arr.get(i).intersects(arr.get(j)))
					arr.get(i).collide(arr.get(j));
				else if (arr.get(j) instanceof Bullet || arr.get(j) instanceof AdhesiveEntity && arr.get(j).intersects(arr.get(i)))
					arr.get(j).collide(arr.get(i));
				else if (arr.get(i) instanceof Dude && arr.get(i).intersects(arr.get(j)))
					arr.get(i).collide(arr.get(j));
				else if (arr.get(j) instanceof Dude && arr.get(j).intersects(arr.get(i)))
					arr.get(j).collide(arr.get(i));
				else if (arr.get(i) instanceof Biped && arr.get(i).intersects(arr.get(j)))
					arr.get(i).collide(arr.get(j));
				else if (arr.get(j) instanceof Biped && arr.get(j).intersects(arr.get(i)))
					arr.get(j).collide(arr.get(i));
				else if (arr.get(i).intersects(arr.get(j)))
					arr.get(j).collide(arr.get(i));
			}

		}
	}
	public void stepArrayTHREADED(final ArrayList<Entity> arr)
	{
		ArrayList<Thread> threads = new ArrayList<Thread>();
		int length = arr.size();
		int increm = arr.size() / Runtime.getRuntime().availableProcessors();// 35;
		if (increm == 0)
			increm = 1;
		final int increment = increm;
		while (length >= increment)
		{
			final int leng = length;
			threads.add(new Thread(new Runnable()
			{
				public void run()
				{
					stepArray(arr, leng - increment, leng);
				}
			}));
			length -= increment;
		}
		if (length < increment && length > 0)
		{
			final int leng = length;
			threads.add(new Thread(new Runnable()
			{
				public void run()
				{
					stepArray(arr, 0, leng);
				}
			}));
		}
		for (int i = 0; i < threads.size(); i++)
		{
			threads.get(i).setName("stepArrayTHREADED");
			threads.get(i).start();
		}
		boolean allFinished = false;
		while (!allFinished)
		{
			allFinished = true;
			for (int i = threads.size() - 1; i >= 0; i--)
			{
				if (threads.get(i).isAlive())
					allFinished = false;
				else
					threads.remove(i);
			}
		}
	}
	public void stepArrayTHREADED2(final ArrayList<Entity> arr)
	{
		final ThreadManager tm = new ThreadManager();
		int length = arr.size();
		final int increment = arr.size() / Runtime.getRuntime().availableProcessors();// 35;
		while (length >= increment)
		{
			final int leng = length;
			new Thread(new Runnable()
			{
				public void run()
				{
					tm.addThread();
					stepArray(arr, leng - increment, leng);
					tm.removeThread();
					synchronized (tm)
					{
						tm.notify();
					}
				}
			}).start();
			length -= increment;
		}
		if (length < increment && length > 0)
		{
			final int leng = length;
			new Thread(new Runnable()
			{
				public void run()
				{
					tm.addThread();
					stepArray(arr, 0, leng);
					tm.removeThread();
					synchronized (tm)
					{
						tm.notify();
					}
				}
			}).start();
		}
		while (tm.getNumThreads() > 0)
		{
			synchronized (tm)
			{
				try
				{
					tm.wait();
				}
				catch (InterruptedException e)
				{}
			}
		}
	}
	private void stepArray(ArrayList<Entity> ents, int start, int end)
	{
		for (int i = start; i < end; i++)
		{
			if (i < ents.size() && ents.get(i) != null)
			{
				if (ents.get(i).ents == null)
					ents.get(i).ents = ents;
				ents.get(i).fps = (this.fps);
				ents.get(i).step();
			}
		}
	}
	private void checkRemoves(ArrayList<Entity> arr)
	{
		for (int i = arr.size() - 1; i > -1; i--)
		{
			try
			{
				if (arr.get(i).removeMe()
						|| (!blood && (arr.get(i).name.toLowerCase().contains("blood"))) ||
						(performance && !(arr.get(i) instanceof Biped) && !(arr.get(i) instanceof Bullet)
								&& !(arr.get(i) instanceof Explosive) && !(arr.get(i) instanceof AdhesiveEntity)
								&& (arr.get(i).name.toLowerCase().contains("zombie"))))
				{
					if (arr.get(i) instanceof Zombie)
						aiManager.notifyZombieKilled((Zombie) arr.get(i));
					arr.set(i, null);
					arr.remove(i);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
				try{
					arr.set(i, null);
					arr.remove(i);
				}
				catch(Exception exept){}
				
				
				
			}
		}
	}
	private void syncArray(ArrayList<Entity> arr)
	{
		for (int i = arr.size() - 1; i > -1; i--)
		{
			if (arr.get(i) != null)
			{
				arr.get(i).timeScale = timeScale;
				if (arr.get(i).ID == -1)
				{
					arr.get(i).ID = getNewID();
					arr.get(i).myTime = 0 + endRenderTime;
					arr.get(i).fps = -1;
				}
			}
		}
	}
	private int getNewID()
	{
		int newID = 0;
		while (containsID(newID))
			newID++;
		return newID;

	}
	private boolean containsID(int id)
	{
		for (int i = 0; i < playEnts.size(); i++)
		{
			if (playEnts.get(i) != null && playEnts.get(i).ID == id)
				return true;
		}
		for (int i = 0; i < frontEnts.size(); i++)
		{
			if (frontEnts.get(i) != null && frontEnts.get(i).ID == id)
				return true;
		}
		for (int i = 0; i < backEnts.size(); i++)
		{
			if (backEnts.get(i) != null && backEnts.get(i).ID == id)
				return true;
		}
		return false;
	}
	private void pauseToggle()
	{
		pause = !pause;
		if (pause)
		{
			this.setCursor(origCursor);
			aiManager.pauseMusic();
		}
		else
		{
			aiManager.resumeMusic();
			System.gc();
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(this.getX(), this.getY()), "");
			this.setCursor(c);
		}
	}
	private void restartGame()
	{
		loadingStatus.clear();
		loadNum = 1;
		loading = true;
		reloading = true;
	}
	// //////////////////////////////////////////////////////////Simulation/\
	// //////////////////////////////////////////////////////////Input\/
	public void keyPressed(KeyEvent e)
	{
		if (loading)
			return;
		int code = e.getKeyCode();
		manageKeys(code, true);
		if (code == KeyEvent.VK_ESCAPE)
			pauseToggle();
		if (!pause)
		{
			switch (code)
			{
				case KeyEvent.VK_F:
					dude.melee();
					break;
				case KeyEvent.VK_E:
					dude.pickUpgroundEntity();
					break;
				case KeyEvent.VK_L:
					dude.toggleLaser();
					break;
				case KeyEvent.VK_J:
					dude.dropCurrentWeapon();
					break;
				case (KeyEvent.VK_1):
					dude.nextWeaponRequest();
					break;
				case (KeyEvent.VK_2):
					dude.prevWeaponRequest();
					break;
				case (KeyEvent.VK_S):
					if (aiManager.isSetUp())
					{
						store.dude = dude;
						store.setVisible(true);
					}
					break;
			}
		}
		else
		// paused
		{
			switch (code)
			{
				case KeyEvent.VK_T:
					threaded = !threaded;
					break;
				case KeyEvent.VK_P:
					performance = !performance;
					if (performance)
					{
						threaded = false;
						blood = false;
					}
					break;
				case KeyEvent.VK_B:
					blood = !blood;
					break;
			}

		}

	}
	public void keyReleased(KeyEvent e)
	{
		if (loading)
			return;
		int code = e.getKeyCode();
		manageKeys(code, false);
		if (dude.isDead() && code == KeyEvent.VK_R)
			restartGame();
		if (pause)
		{
			if (code == KeyEvent.VK_R && ctrl)
			{
				ctrl = false;
				restartGame();
			}
		}
	}
	public void keyTyped(KeyEvent e)
	{
		// dont use
	}
	private void manageKeys(int k, boolean b)
	{
		switch (k)
		{
			case KeyEvent.VK_W:
				keyW = b;
				break;
			case KeyEvent.VK_A:
				keyA = b;
				break;
			case KeyEvent.VK_S:
				keyS = b;
				break;
			case KeyEvent.VK_D:
				keyD = b;
				break;
			case KeyEvent.VK_SPACE:
				if (!slowMotionToggle && !pause)
					if (keySpace != b)
					{
						if ((targetTimeScale == 1 && b) || (targetTimeScale != 1 && !b))
							dude.toggleTimeScale();
					}
				keySpace = b;
				break;
			case KeyEvent.VK_SHIFT:
				shift = b;
				break;
			case KeyEvent.VK_CONTROL:
				ctrl = b;
				break;
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_P:
				break;
			case KeyEvent.VK_V:
				keyV = b;
				break;
			case KeyEvent.VK_Z:
				break;
			case KeyEvent.VK_X:
				break;
			case KeyEvent.VK_C:
				break;
			case KeyEvent.VK_Q:
				break;
			case KeyEvent.VK_E:
				break;
			case KeyEvent.VK_R:
				keyR = b;
				break;
			case KeyEvent.VK_T:
				break;
			case KeyEvent.VK_H:
				break;
			case KeyEvent.VK_B:
				break;
			case KeyEvent.VK_ESCAPE:
				break;
			case KeyEvent.VK_0:
				break;
			case KeyEvent.VK_1:
				break;
			case KeyEvent.VK_2:
				break;
			case KeyEvent.VK_3:
				break;
			case KeyEvent.VK_4:
				break;
			case KeyEvent.VK_5:
				break;
			case KeyEvent.VK_6:
				break;
			case KeyEvent.VK_7:
				break;
			case KeyEvent.VK_8:
				break;
			case KeyEvent.VK_9:
		}
	}
	private void keyboardForDude()
	{
		if (dude == null)
			return;

		dude.sprinting = shift;
		if (keyA && !keyD)
			dude.moveLeft();
		else if (keyD && !keyA)
			dude.moveRight();
		if (keyW)
			dude.jump();
		if (keyR)
			dude.reload();
		if (keyS)
		{
			dude.fall();
		}
		if (keyV)
		{
			// dude.ySpeed = 0;
			// dude.y -= 200 / fps;
		}
		// /////Mouse
		if (M1)
			dude.shoot();
		else
			dude.release();
		if (M3)
			dude.focus();
		else
			dude.unfocus();

	}
	public void mouseEntered(MouseEvent e)
	{

	}
	public void mouseExited(MouseEvent e)
	{

	}
	public void mousePressed(MouseEvent e)
	{
		if (loading)
			return;
		if (e.getButton() == MouseEvent.BUTTON1)
			M1 = true;
		else if (e.getButton() == MouseEvent.BUTTON2)
		{
			dude.toggleFireMode();
		}
		else if (e.getButton() == MouseEvent.BUTTON3)
			M3 = true;

	}
	public void mouseReleased(MouseEvent e)
	{
		if (loading)
			return;
		if (e.getButton() == MouseEvent.BUTTON1)
			M1 = false;
		else if (e.getButton() == MouseEvent.BUTTON2)
		{}
		else if (e.getButton() == MouseEvent.BUTTON3)
			M3 = false;
	}
	public void mouseClicked(MouseEvent e)
	{

	}
	public void mouseMoved(MouseEvent e)
	{
		mx = (int) ((e.getX() - origX)) - renderOffsetX;
		my = (int) ((e.getY() - origY)) - renderOffsetY;
	}
	public void mouseDragged(MouseEvent e)
	{
		mx = (int) ((e.getX() - origX)) - renderOffsetX;
		my = (int) ((e.getY() - origY)) - renderOffsetY;
	}
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (loading)
			return;

		if (e.getWheelRotation() < 0)
			dude.nextWeaponRequest();
		else if (e.getWheelRotation() > 0)
			dude.prevWeaponRequest();

	}

}
