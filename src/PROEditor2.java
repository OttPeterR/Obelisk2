import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
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
import java.util.ArrayList;

import javax.swing.JFrame;

public class PROEditor2 extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener
{

	private static final long	serialVersionUID	= 5233144957422731679L;

	private boolean				threaded			= false;
	private long				lastStartTime		= System.nanoTime();
	private long				frameCap			= (long) (1E9 / 900);
	private double				localbudget			= 0;
	private ArrayList<Entity>	frontEnts, playEnts, backEnts, staticEnts, physEnts, sceneEnts, weps;
	private ArrayList<Zombie>	zombies;
	private ArrayList<Entity>	frontEnts2, playEnts2, backEnts2;
	private ArrayList<AINode>	AINodes;
	private int					aiNodeType, zombiePos;
	private boolean				nodeMode;
	private int					currentStaticEntsPos, currentPhysEntsPos, currentSceneEntsPos, currentWepEntsPos;
	private int					entArrayNumber;
	private boolean				frontEntsVisible, playEntsVisible, backEntsVisible;
	private Entity				currentEntity;
	private Vect				currentEntityOffset;
	private double				gcTimer;
	private int					toolNumber;
	// # Name Left Right Middle Scroll Wheel
	// 1 Creator Place Delete Next Array Next/Last CurrentEnt
	// 2 Edits Pick-Up Copy Change Array View Rotate
	// 3

	private int					mx, my;
	private boolean				M1, M3, ctrl, shift, esc, keySpace, keyW, keyS, keyA, keyD, keyB, keyG, keyR, key1, key2, key3, key4, key5, key6, key7, key8, key9, key0;

	private boolean				loading				= true, renderWireframes = false;
	private int					loadNum				= 1;
	private ArrayList<String>	loadingStatus;

	private int					screenX, renderOffsetX = 0 * 4;
	// renderOffsetX = 5
	private int					screenY, renderOffsetY = 0 * 23;
	// renderOffsetY = 30
	private double				origX;
	private double				origY;

	private double				scrollSpeed;
	private double				shiftMultiplier;

	private double				fps;
	private double				endRenderTime;
	private double				timeScale			= 1, targetTimeScale = 1;

	private int					localEnts;
	private int					totalEnts;
	private double				loadPercent			= 0;

	private boolean				inGame;
	private boolean				physics;
	// private URL url = getClass().getResource("Editor2.class");

	private Image				backbuffer;
	private Graphics			backG;
	AffineTransform				trans				= new AffineTransform();

	private Rectangle2D.Double	screenRect;
	private Wireframe			mouseWF;

	private static final Color	PROColor			= Entity.PROColor;

	private Dude				dude, origDude;
	private Vect				spawnPoint			= new Vect(0, 0);
	private AIManager			aiManager;

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Start
	// Up / Loading \/
	public void setThreading(boolean b)
	{
		threaded = b;
	}
	public void init()
	{
		setTitle("PROtek Editor v2.03 by Peter Ott");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		loadingStatus = new ArrayList<String>();
		setResizable(true);

		screenX = Toolkit.getDefaultToolkit().getScreenSize().width + 17;
		screenY = Toolkit.getDefaultToolkit().getScreenSize().height - 28;

		this.setIconImage(Library.img("PROTekIconSmall.png"));

		backbuffer = createImage(Toolkit.getDefaultToolkit().getScreenSize().width + 8, Toolkit.getDefaultToolkit().getScreenSize().height);
		backG = backbuffer.getGraphics();
		backG.translate(renderOffsetX, renderOffsetY);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		this.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				screenX = getWidth();
				screenY = getHeight();
				setResolution(screenX, screenY);
			}
		});
		setFocusable(true);
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
	public void start()
	{
		this.requestFocusInWindow();
	}
	private void updateLoading(double percent, String status)
	{
		loadingStatus.add(0, percent + "% " + status);
		loadNum++;
		loadPercent = percent;
	}
	private void renderLoading(Graphics g)
	{
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.gray);
		backG.fillRect(0, 0, screenX + 50, screenY + 50);
		g.setColor(PROColor);
		Font prevFont = g.getFont();
		g.setFont(new Font("MonoSpaced", Font.BOLD, 40));
		g.drawString("PROtekENGINE 2", (int) (screenX * .6), (int) (screenY * .88));
		g.setFont(prevFont);
		g.setColor(PROColor);
		g.fillOval((int) (mx + origX) - 5, (int) (my + origY) - 5, 10, 10);
		// if (readyForNextLoad)

		Thread physLoad = new Thread();
		Thread staticLoad = new Thread();
		Thread sceneLoad = new Thread();
		Thread weaponLoad = new Thread();

		switch (loadNum)
		{
			case (1):
				updateLoading(01.2, "Rendering Elements");
				break;
			case (2):
				setSize(screenX, screenY);
				screenRect = new Rectangle2D.Double(0, 0, screenX, screenY);
				frontEntsVisible = true;
				playEntsVisible = true;
				backEntsVisible = true;
				updateLoading(04.3, "Basic System Variables");
				break;
			case (3):
				System.setProperty("sun.java2d.opengl", "True");
				scrollSpeed = 2000.0;
				shiftMultiplier = 3.0;
				origX = 0;
				origY = 0;
				ctrl = false;
				shift = false;
				toolNumber = 1;
				inGame = false;
				physics = false;
				fps = 1;
				timeScale = 1;
				aiNodeType = 1;
				nodeMode = true;
				zombiePos = 0;
				updateLoading(8.6, "Controls");
				break;
			case (4):
				// mouse
				Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(this.getX(), this.getY()), "");
				this.setCursor(c);

				Vect[] mcoords = new Vect[3];
				mcoords[0] = new Vect(mx, my - 1);
				mcoords[1] = new Vect(mx + 1, my + 1);
				mcoords[2] = new Vect(mx - 1, my + 1);
				mouseWF = new Wireframe(mcoords, 3);
				currentEntityOffset = new Vect(0, 0);

				updateLoading(13.1, "Settings");
				break;
			case (5):
				updateLoading(19.2, "Player Character");
				break;
			case (6):
				new DudeLibrary();
				origDude = DudeLibrary.Russ();
				dude = origDude.getNew();
				updateLoading(26.1, "Loading AI");
				break;
			case (7):
				AINodes = new ArrayList<AINode>();

				zombies = new ArrayList<Zombie>();
				AILibrary.loadAll(zombies);

				aiManager = new AIManager();
				updateLoading(35.9, "Creating Arrays");
				break;
			case (8):
				staticEnts = new ArrayList<Entity>();
				physEnts = new ArrayList<Entity>();
				sceneEnts = new ArrayList<Entity>();
				weps = new ArrayList<Entity>();

				frontEnts = new ArrayList<Entity>();
				playEnts = new ArrayList<Entity>();
				backEnts = new ArrayList<Entity>();

				frontEnts2 = new ArrayList<Entity>();
				playEnts2 = new ArrayList<Entity>();
				backEnts2 = new ArrayList<Entity>();

				AINodes = new ArrayList<AINode>();

				currentStaticEntsPos = 0;
				currentPhysEntsPos = 0;
				currentSceneEntsPos = 0;

				entArrayNumber = 1;
				localEnts = 0;
				totalEnts = 0;

				updateLoading(38.0, "Populating Physics Entity Library");
				break;
			case (9):
				physLoad = new Thread(new Runnable()
				{

					public void run()
					{
						PhysicsEntityLibrary.loadAll(physEnts);
					}
				});
				physLoad.start();
				updateLoading(57.3, "Populating Static Entity Library");
				break;
			case (10):
				staticLoad = new Thread(new Runnable()
				{

					public void run()
					{
						StaticEntityLibrary.loadAll(staticEnts);
					}
				});
				staticLoad.start();
				updateLoading(68.6, "Populating Scene Entity Library");
				break;
			case (11):
				sceneLoad = new Thread(new Runnable()
				{

					public void run()
					{
						SceneEntityLibrary.loadAll(sceneEnts);
					}
				});
				sceneLoad.start();
				updateLoading(74.3, "Populating Weapons Library");
				break;
			case (12):
				weaponLoad = new Thread(new Runnable()
				{

					public void run()
					{
						WeaponLibrary.loadAll(weps);
					}
				});
				weaponLoad.start();
				updateLoading(85.5, "Checking Entities");
				break;
			case (13):
				while (weaponLoad.isAlive() || sceneLoad.isAlive() || staticLoad.isAlive() || physLoad.isAlive())
				{
					try
					{
						Thread.sleep(0, 1);
					}
					catch (InterruptedException e1)
					{}
				}
				Thread t1 = new Thread(new Runnable()
				{

					public void run()
					{
						testArray(physEnts);
					}
				});
				Thread t2 = new Thread(new Runnable()
				{

					public void run()
					{
						testArray(sceneEnts);
					}
				});
				Thread t3 = new Thread(new Runnable()
				{

					public void run()
					{
						testArray(staticEnts);
					}
				});
				t1.start();
				t2.start();
				t3.start();

				while (t1.isAlive() || t2.isAlive() || t3.isAlive())
				{
					try
					{
						Thread.sleep(0, 1);
					}
					catch (InterruptedException e)
					{}
				}
				// testArray(weps);
				updateLoading(100, "Complete");
				break;
			default:
				loading = false;
				break;

		}

		g.setColor(Color.black);
		g.drawString("Loading...", (int) (screenX * .025), screenY - 120);
		for (int i = loadingStatus.size() - 1; i >= 0; i--)
		{
			g.drawString(loadingStatus.get(i), (int) (screenX * .025) + 60, screenY - 120 - (i * 15));
		}
		g.fillRect((int) (screenX * .025), (int) (screenY * .9), (int) (screenX * .95), 20);
		g.setColor(PROColor);
		g.drawString(loadPercent + "", (int) ((screenX * .95) * ((double) loadPercent / 100)) + 45, (int) (screenY * .895));
		g.fillRect((int) (screenX * .025), (int) (screenY * .9), (int) ((screenX * .95) * ((double) loadPercent / 100)), 20);
	}
	private void testArray(ArrayList<Entity> ents)
	{
		for (int i = 0; i < ents.size(); i++)
		{
			for (int j = 0; j < ents.get(i).numImg; j++)
			{
				Image tempImg = createImage(1, 1);
				Graphics tempG = tempImg.getGraphics();
				ents.get(i).render((Graphics2D) tempG, trans, 0, 0);
			}
		}
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Start
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Rendering\/
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
		if (physics)
		{
			if (threaded)
				simulateTHREADED();
			else
				simulate();
			manageTimeChange();
		}
		if (inGame)
		{
			scroll(dude.translation.x, dude.translation.y);
		}

		g.setBackground(Color.black);
		g.clearRect(0, 0, screenX, screenY);

		// g.setColor(Color.black);
		// g.fillRect(0, 0, screenX + 50, screenY + 50);

		localEnts = 0;
		totalEnts = frontEnts.size() + playEnts.size() + backEnts.size();
		localbudget = 0;

		if (backEntsVisible)
			renderArray(g, backEnts);
		if (playEntsVisible)
			renderArray(g, playEnts);
		if (frontEntsVisible)
			renderArray(g, frontEnts);

		if (!inGame)// && readyForFrame)
		{
			keyboardForEditor();

			renderSpawnPoint(g);
			renderAIPoints(g);
			if (!physics)
			{
				renderMouse(g);
				renderData(g);
			}

		}
		else
		{
			keyboardForDude();

			g.setColor(Color.RED);
			g.drawString("Esc - Exit", (int) screenX - 65, 14);

			dude.renderHUD(g, trans, origX, origY, mx, my, screenX, screenY, aiManager);
			targetTimeScale = dude.getTargetTimeScale();
			renderPerformance(g);

		}

		g.setComposite(java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	private void renderData(Graphics2D g)
	{
		List infos = new List();

		infos.add("(" + (int) origX + ", " + (int) origY + ")");
		infos.add("Mouse: (" + (mx + origX * 2) + ", " + (my + origY * 2) + ")");
		infos.add("");
		infos.add("FPS: " + (int) fps);
		infos.add("");
		infos.add("On-Screen Entities: " + localEnts);
		infos.add("Total Entities: " + totalEnts);

		for (int i = 0; i < frontEnts.size(); i++)
		{
			if (frontEnts.get(i).rect.intersects(screenRect))

				localbudget += frontEnts.get(i).getRenderValue();
		}
		for (int i = 0; i < playEnts.size(); i++)
		{
			if (playEnts.get(i).rect.intersects(screenRect))

				localbudget += playEnts.get(i).getRenderValue();
		}
		for (int i = 0; i < backEnts.size(); i++)
		{
			if (backEnts.get(i).rect.intersects(screenRect))

				localbudget += backEnts.get(i).getRenderValue();
		}
		int localAINodes = 0;
		for (int i = 0; i < AINodes.size(); i++)
		{
			if (AINodes.get(i).rect.intersects(screenRect))
				localAINodes++;
		}
		infos.add("");
		infos.add("Local DrawVal: " + ((double) Math.round(localbudget * 10)) / 10);
		infos.add("Total DrawVal: " + ((double) Math.round(getCurrentBudget() * 10)) / 10);
		infos.add("");
		infos.add("Local AINodes: " + localAINodes);
		infos.add("Total AINodes: " + AINodes.size());
		infos.add("");
		double mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		infos.add("Memory Usage: " + (double) ((int) (mem / 100000)) / 10 + " MB");// java.lang.management.ManagementFactory.getMemoryMXBean());
		infos.add("");

		infos.add("Tool Set__________________________________");
		infos.add("");
		if (true)
			infos.add("#   Name        Left | Right | Middle | Scroll Wheel");
		if (toolNumber == 1)
			infos.add("1   Creator     Place | Delete | Next Array | Next/Last CurrentEnt");
		else
			infos.add("1   Creator");
		if (toolNumber == 2)
			infos.add("2   Edits         Pick-Up | Copy | Change Array View | Rotate");
		else
			infos.add("2   Edits");
		if (toolNumber == 3)
			infos.add("3   Physics    Drag Speed | Enable Physics | Reset Speed");
		else
			infos.add("3   Physics");

		if (toolNumber == 4)
			infos.add("4   AI              Place | Remove | ----- | Change Type");
		else
			infos.add("4   AI");

		infos.add("");
		if (physics)
			infos.add("Physics Enabled");
		else
			infos.add("P - Physics Suspended");
		g.setColor(PROColor);
		g.setFont(new java.awt.Font("Arial", 1, 15));
		for (int i = 0; i < infos.getItemCount() * 15; i += 15)
		{
			g.drawString((String) infos.getItem(i / 15), 5 + 4, 17 + 24 + i);
		}
		g.setFont(new java.awt.Font("Arial", 0, 12));
	}
	private void renderPerformance(Graphics2D g)
	{
		if (getCurrentBudget() > 2000 || fps < 23)
			g.setColor(Color.red);
		else if (getCurrentBudget() > 1400 || fps < 35)
			g.setColor(Color.orange.darker());
		else if (getCurrentBudget() > 1000 || fps < 55)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.green);
		g.drawString("RV: " + (int) localbudget, (int) screenX - 100, 40);
		g.drawString("FPS: " + (int) (fps * timeScale), (int) screenX - 100, 55);
		g.drawString("TimeScale: " + timeScale, (int) screenX - 100, 85);

	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Rendering/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////GameAndEditor\/
	private void goInGame()
	{
		if (physics)
		{
			togglePhysics();
			togglePhysics();// to refresh the backups
		}
		else
			togglePhysics();

		initDude();
		// dude.unlimitedAmmo();
		// dude.unlimitedClip();
		// dude.invulnerable();
		// dude.unlimitedSlowMotion();

		playEnts.add(dude);
		if (shift)
			dude.translateTo(spawnPoint.x, spawnPoint.y);

		toolNumber = 0;

		inGame = true;
		frontEntsVisible = true;
		playEntsVisible = true;
		backEntsVisible = true;
	}
	private void initDude()
	{

		dude = origDude.getNew();

		dude.setID(getNewID());
		for (int i = 0; i < dude.weapons.size(); i++)
			dude.weapons.get(i).ID = getNewID();

		dude.loadArray(playEnts);

		dude.translateTo(mx + origX * 2, my + origY * 2);

		dude.xSpeed = 0;
		dude.ySpeed = 0;

	}
	private void initAI()
	{
		aiManager.startUp(this.AINodes, playEnts);
	}
	private void goInEditor()
	{
		restoreBackUp();
		targetTimeScale = 1;
		timeScale = 1;
		toolNumber = 1;
		physics = false;
		inGame = false;
	}
	private void createBackUp()
	{
		frontEnts2.clear();// = new ArrayList<Entity>();
		playEnts2.clear();// = new ArrayList<Entity>();
		backEnts2.clear();// = new ArrayList<Entity>();

		transferArray(frontEnts, frontEnts2);
		transferArray(playEnts, playEnts2);
		transferArray(backEnts, backEnts2);
	}
	private void restoreBackUp()
	{
		frontEnts.clear();// = new ArrayList<Entity>();
		playEnts.clear();// = new ArrayList<Entity>();
		backEnts.clear();// = new ArrayList<Entity>();

		transferArray(frontEnts2, frontEnts);
		transferArray(playEnts2, playEnts);
		transferArray(backEnts2, backEnts);
	}
	private void transferArray(ArrayList<Entity> a, ArrayList<Entity> b)
	{
		for (int i = 0; i < a.size(); i++)
		{
			if (!(a.get(i) instanceof Biped))
				b.add(a.get(i).copy());
		}
	}
	private void togglePhysics()
	{
		if (!physics)
		{
			createBackUp();
			enablePhysics();
			initAI();
		}
		else
		{
			restoreBackUp();
			physics = false;
		}
	}
	private void enablePhysics()
	{
		resetFPS(backEnts);
		resetFPS(playEnts);
		resetFPS(frontEnts);
		physics = true;
		for (int i = 0; i < playEnts.size(); i++)
		{
			playEnts.get(i).myTime = 0 + this.endRenderTime;
		}
	}
	private void resetFPS(ArrayList<Entity> ents)
	{
		for (int i = 0; i < ents.size(); i++)
		{
			ents.get(i).myTime = System.nanoTime();
			ents.get(i).paused = true;
		}
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
	private double getCurrentBudget()
	{
		return budgetFromArray(frontEnts) + budgetFromArray(playEnts) + budgetFromArray(backEnts);
	}
	private double budgetFromArray(ArrayList<Entity> ents)
	{
		double total = 0;
		for (int i = 0; i < ents.size(); i++)
			if (ents.get(i) != null)
				total += ents.get(i).getRenderValue();
		return total;
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
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////GameAndEditor/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Tools\/
	private void nextCurrentArray()
	{
		if (entArrayNumber == 4)
			entArrayNumber = 1;
		else
			entArrayNumber++;
		nextCurrentEntity();
		lastCurrentEntity();
	}
	private void nextCurrentEntity()
	{
		if (entArrayNumber == 1 && staticEnts.size() > 0)
		{
			currentStaticEntsPos++;
			if (currentStaticEntsPos == staticEnts.size())
				currentStaticEntsPos = 0;
			currentEntity = staticEnts.get(currentStaticEntsPos).getNew();
		}
		else if (entArrayNumber == 2 && physEnts.size() > 0)
		{
			currentPhysEntsPos++;
			if (currentPhysEntsPos == physEnts.size())
				currentPhysEntsPos = 0;
			currentEntity = physEnts.get(currentPhysEntsPos).getNew();
		}
		else if (entArrayNumber == 3 && sceneEnts.size() > 0)
		{
			currentSceneEntsPos++;
			if (currentSceneEntsPos == sceneEnts.size())
				currentSceneEntsPos = 0;
			currentEntity = sceneEnts.get(currentSceneEntsPos).getNew();
		}
		else if (entArrayNumber == 4 && weps.size() > 0)
		{
			currentWepEntsPos++;
			if (currentWepEntsPos == weps.size())
				currentWepEntsPos = 0;
			currentEntity = weps.get(currentWepEntsPos).getNew();
		}
	}
	private void lastCurrentEntity()
	{
		if (entArrayNumber == 1)
		{
			currentStaticEntsPos--;
			if (currentStaticEntsPos == -1)
				currentStaticEntsPos = staticEnts.size() - 1;
			currentEntity = staticEnts.get(currentStaticEntsPos).getNew();
		}
		else if (entArrayNumber == 2)
		{
			currentPhysEntsPos--;
			if (currentPhysEntsPos == -1)
				currentPhysEntsPos = physEnts.size() - 1;
			currentEntity = physEnts.get(currentPhysEntsPos).getNew();
		}
		else if (entArrayNumber == 3)
		{
			currentSceneEntsPos--;
			if (currentSceneEntsPos == -1)
				currentSceneEntsPos = sceneEnts.size() - 1;
			currentEntity = sceneEnts.get(currentSceneEntsPos).getNew();
		}
		else if (entArrayNumber == 4)
		{
			currentWepEntsPos--;
			if (currentWepEntsPos == -1)
				currentWepEntsPos = weps.size() - 1;
			currentEntity = weps.get(currentWepEntsPos).getNew();
		}
	}
	private void changeTool(int tool)
	{
		if (tool == toolNumber)
			return;
		toolNumber = tool;
		currentEntity = null;
		if (toolNumber == 1)
		{
			if (entArrayNumber == 1 && staticEnts.size() > 0)
				currentEntity = staticEnts.get(currentStaticEntsPos).getNew();
			else if (entArrayNumber == 2 && physEnts.size() > 0)
				currentEntity = physEnts.get(currentPhysEntsPos).getNew();
			else if (sceneEnts.size() > 0)
				currentEntity = sceneEnts.get(currentSceneEntsPos).getNew();
			currentEntityOffset = new Vect(0, 0);
		}
		else if (toolNumber == 2)
		{
			currentEntity = null;
		}
		else if (toolNumber == 3)
		{
			currentEntity = null;
		}
		else if (toolNumber == 4)
		{
			currentEntity = null;
		}
	}
	private void placeEntity()
	{
		if (currentEntity != null)
		{
			Entity newEnt = currentEntity.copy();
			newEnt.translateTo(mx + (2 * origX), my + (2 * origY));
			newEnt.ID = getNewID();
			if (toolNumber == 2)
				newEnt.translate(currentEntityOffset.x, currentEntityOffset.y);
			if (playEntsVisible)
			{
				newEnt.loadArray(playEnts);
				playEnts.add(newEnt);
			}
			else if (frontEntsVisible)
			{
				newEnt.loadArray(frontEnts);
				frontEnts.add(newEnt);
			}
			else if (backEntsVisible)
			{
				newEnt.loadArray(backEnts);
				backEnts.add(newEnt);
			}
			else
			{
				newEnt.loadArray(playEnts);
				playEnts.add(newEnt);
			}
		}
	}
	private void placeAINode()
	{
		if (nodeMode)
		{
			AINode newNode = new AINode(mx + (2 * origX), my + (2 * origY));
			newNode.type = 0 + aiNodeType;
			AINodes.add(newNode);
		}
		else
		{
			Biped zombo = zombies.get(zombiePos).getNew();
			zombo.translateTo(mx + (2 * origX), my + (2 * origY));
			playEnts.add(zombo);
		}
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Tools/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Array\/
	private void renderArray(Graphics2D g, ArrayList<Entity> arr)
	{
		for (int i = 0; i < arr.size(); i++)
		{
			if (arr.get(i) != null)
				try
				{
					if (arr.get(i) instanceof Bullet || (arr.get(i).rect != null && arr.get(i).rect.intersects(screenRect)))
					{
						arr.get(i).render(g, trans, origX, origY);
						if (renderWireframes)
						{
							arr.get(i).renderWireframes(g, origX, origY);
							arr.get(i).renderRect(g, origX, origY);
						}
						localEnts += 1;
					}
				}
				catch (Exception e)
				{}
		}
	}
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
				if (arr.get(i).removeMe())
				{
					if (arr.get(i) instanceof Zombie)
						aiManager.notifyZombieKilled((Zombie) arr.get(i));
					arr.set(i, null);
					arr.remove(i);
				}
			}
			catch (Exception e)
			{
				arr.remove(i);
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
	private void renderAIPoints(Graphics2D g)
	{
		for (int i = 0; i < this.AINodes.size(); i++)
		{
			if (AINodes.get(i).rect.intersects(this.screenRect))
				AINodes.get(i).render(g, origX, origY);
		}
	}
	private void renderSpawnPoint(Graphics2D g)
	{

		g.setColor(new Color(PROColor.getRed(), PROColor.getGreen(), PROColor.getBlue(), 100));
		g.fillRect((int) (spawnPoint.x - 55 - origX), (int) (spawnPoint.y - 200 - origY), 110, 200);
		g.setColor(PROColor);
		g.drawString("Player Spawn - Shift + G to move", (int) (spawnPoint.x - 55 - origX), (int) (spawnPoint.y - 200 - origY));
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Array/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////ScreenControl\/
	private void scroll(double xScroll, double yScroll)
	{
		xScroll = (int) xScroll;
		yScroll = (int) yScroll;

		origX += xScroll;
		origY += yScroll;

		mx -= xScroll;
		my -= yScroll;
		updateMouseWF();
		if (currentEntity != null && toolNumber != 3)
			currentEntity.translateTo(mx + origX * 2 + currentEntityOffset.x, my + origY * 2 + currentEntityOffset.y);
		screenRect.setFrame(origX, origY, screenRect.getWidth(), screenRect.getHeight());
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////ScreenControl/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Mouse\/
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
		{}
		else if (e.getButton() == MouseEvent.BUTTON3)
			M3 = true;

		if (!inGame)
		{
			if (e.getButton() == MouseEvent.BUTTON1)// Left
			{
				if (toolNumber == 1)
				{
					placeEntity();
				}
				else if (toolNumber == 2 && !physics)
				{
					if (currentEntity == null)
					{
						currentEntity = getEntityOnMouse();
						removeEntityOnMouse();
						if (currentEntity != null)
						{
							currentEntityOffset = new Vect(currentEntity.x - mx - origX * 2, currentEntity.y - my - origY * 2); // works
							currentEntity.paused = true;
						}
					}
				}
				else if (toolNumber == 3)
				{
					if (getEntityOnMouse() != null)
					{
						currentEntity = getEntityOnMouse();
						currentEntityOffset = new Vect(currentEntity.x - origX * 2, currentEntity.y - origY * 2);
					}
					else
						currentEntity = null;
				}
				else if (toolNumber == 4)
				{
					placeAINode();
				}
			}
			else if (e.getButton() == MouseEvent.BUTTON3)// right click
			{
				if (toolNumber == 1)
				{
					removeEntityOnMouse();
				}
				else if (toolNumber == 2 && !physics)
				{
					if (getEntityOnMouse() != null)
					{
						currentEntity = getEntityOnMouse().copy();
						currentEntityOffset = new Vect(currentEntity.x - mx - origX * 2, currentEntity.y - my - origY * 2);// works
					}
				}
				else if (toolNumber == 3 && getEntityOnMouse() != null)
				{
					getEntityOnMouse().xSpeed = 0;
					getEntityOnMouse().ySpeed = 0;
				}
				else if (toolNumber == 4)
				{
					removeAINodeOnMouse();
				}
			}
			else if (e.getButton() == MouseEvent.BUTTON2)
			{
				if (toolNumber == 3)
				{
					togglePhysicalized();
				}
			}
		}
		else
		{
			if (e.getButton() == MouseEvent.BUTTON2)// wheel button
				dude.toggleFireMode();
		}

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
		if (!inGame)
		{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				if (toolNumber == 2 && !physics && currentEntity != null)
				{
					placeEntity();
					currentEntity = null;
				}
				if (toolNumber == 3 && currentEntity != null)
				{
					if (!shift)
					{
						currentEntity.xSpeed = -(currentEntityOffset.x - mx);
						currentEntity.ySpeed = -(currentEntityOffset.y - my);
					}
					else
					{
						currentEntity.xSpeed = -(currentEntityOffset.x - mx) * shiftMultiplier;
						currentEntity.ySpeed = -(currentEntityOffset.y - my) * shiftMultiplier;
					}
				}
			}
			else if (e.getButton() == MouseEvent.BUTTON2)
			{
				if (toolNumber == 1)
					nextCurrentArray();
				else if (toolNumber == 4)
					nodeMode = !nodeMode;
			}
			else if (e.getButton() == MouseEvent.BUTTON3)
			{

			}
		}
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
		if (!inGame)
		{
			if (toolNumber == 1)
			{
				if (e.getWheelRotation() > 0)
					nextCurrentEntity();
				else
					lastCurrentEntity();
			}
			else if (toolNumber == 2)
			{
				if (e.getWheelRotation() > 0)
					for (int i = 0; i <= e.getWheelRotation(); i++)
					{
						if (!shift)
							rotateEntityOnMouse(Math.toRadians(5));
						else
							rotateEntityOnMouse(Math.toRadians(.5));
					}
				else if (e.getWheelRotation() < 0)
					for (int i = 0; i >= e.getWheelRotation(); i--)
					{
						if (!shift)
							rotateEntityOnMouse(Math.toRadians(-5));
						else
							rotateEntityOnMouse(Math.toRadians(-.5));
					}
			}
			else if (toolNumber == 4)
			{
				if (nodeMode)
				{
					if (e.getWheelRotation() > 0)
						aiNodeType++;
					else if (e.getWheelRotation() < 0)
						aiNodeType--;

					if (aiNodeType == 0)
						aiNodeType = 6;
					else if (aiNodeType == 7)
						aiNodeType = 1;
				}
				else
				{
					if (e.getWheelRotation() > 0)
						zombiePos++;
					else if (e.getWheelRotation() < 0)
						zombiePos--;

					if (zombiePos == -1)
						zombiePos = zombies.size() - 1;
					else if (zombiePos == zombies.size())
						zombiePos = 0;
				}
			}
		}
		else
		{
			if (e.getWheelRotation() < 0)
				dude.nextWeaponRequest();
			else if (e.getWheelRotation() > 0)
				dude.prevWeaponRequest();
		}
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Mouse/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////MouseRelatedMethods\/
	private void renderCurrentEntity(Graphics2D g)
	{
		if (toolNumber == 1 || toolNumber == 2)
		{
			if (currentEntity != null)
			{
				currentEntity.translateTo(mx + origX * 2 + currentEntityOffset.x, my + origY * 2 + currentEntityOffset.y);
				currentEntity.computeRect();
				currentEntity.render(g, trans, origX, origY);
				currentEntity.renderWireframes(g, origX, origY);
			}
		}
		else if (toolNumber == 4)
		{
			if (nodeMode)
			{
				switch (aiNodeType)
				{
					case (AINode.MOVEMENT):
						g.setColor(Color.blue);
						g.drawString("Movement", (int) (mx + origX - 7), (int) (my + origY - 7));
						break;
					case (AINode.JUMP_RIGHT_UP):
						g.setColor(Color.green);
						g.drawString("Jump /\\", (int) (mx + origX - 7), (int) (my + origY - 7));
						break;
					case (AINode.JUMP_LEFT_UP):
						g.setColor(Color.green);
						g.drawString("/\\ Jump", (int) (mx + origX - 7), (int) (my + origY - 7));
						break;
					case (AINode.JUMP_DOWN_LEFT):
						g.setColor(Color.cyan);
						g.drawString("\\/ Jump", (int) (mx + origX - 7), (int) (my + origY - 7));
						break;
					case (AINode.JUMP_DOWN_RIGHT):
						g.setColor(Color.cyan);
						g.drawString("Jump \\/", (int) (mx + origX - 7), (int) (my + origY - 7));
						break;
					case (AINode.SPAWNER):
						g.setColor(Color.red);
						g.drawString("Spawner", (int) (mx + origX - 7), (int) (my + origY - 7));
						break;
				}
			}
			else
			{
				zombies.get(zombiePos).renderAt(g, trans, mx + origX, my + origY);
			}
		}
	}
	private void renderMouse(Graphics2D g)
	{
		updateMouseWF();
		if (toolNumber == 1)
		{
			if ((drawMouseOverInfo(g)) && (currentEntity != null))
			{// dimming
				// effect
				// when over
				// another
				// entity
				float tempAlpha = currentEntity.alpha;
				currentEntity.alpha = 0.5F;
				renderCurrentEntity(g);
				currentEntity.alpha = tempAlpha;
				g.setComposite(java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			}
			else
			{
				renderCurrentEntity(g);
			}
		}
		else if (toolNumber == 2)
		{
			drawMouseOverInfo(g);
			if (currentEntity != null)
			{
				renderCurrentEntity(g);
			}
		}
		else if (toolNumber == 3)
		{

			if (M1 && currentEntity != null)
			{
				g.setColor(java.awt.Color.gray);
				g.drawLine((int) (currentEntity.x - origX), (int) (currentEntity.y - origY), (int) (mx + origX), (int) (my + origY));

				currentEntity.renderInfo((Graphics2D) g, origX, origY);
			}
			drawMouseOverInfo(g);
		}
		else if (toolNumber == 4)
		{
			renderCurrentEntity(g);
		}
		g.setColor(PROColor);
		g.fillOval((int) (mx + origX) - 5, (int) (my + origY) - 5, 11, 11);
	}
	private void updateMouseWF()
	{
		mouseWF.translateTo(mx + origX * 2, my + origY * 2);
	}
	private boolean drawMouseOverInfo(Graphics2D g)
	{
		Entity mouseOn = getEntityOnMouse();
		if (mouseOn != null && toolNumber != 3)
		{
			mouseOn.renderInfo(g, origX, origY);
			return true;
		}
		else if (toolNumber == 3 && currentEntity != null)
		{
			currentEntity.renderInfo(g, origX, origY);
			return true;
		}
		return false;
	}
	private Entity findEntityInArray(ArrayList<Entity> arr)
	{
		Entity r = null;
		for (int i = 0; i < arr.size(); i++)
		{
			for (int j = 0; j < arr.get(i).wireframes.length; j++)
			{
				if (arr.get(i).wireframes[j].collide(mouseWF))
				{
					r = (Entity) arr.get(i);
				}
			}
		}
		return r;
	}
	private Entity getEntityOnMouse()
	{
		Entity r = null;
		if ((backEntsVisible) && (findEntityInArray(backEnts) != null))
			r = findEntityInArray(backEnts);
		if ((playEntsVisible) && (findEntityInArray(playEnts) != null))
			r = findEntityInArray(playEnts);
		if ((frontEntsVisible) && (findEntityInArray(frontEnts) != null))
			r = findEntityInArray(frontEnts);
		return r;
	}
	private void removeEntityOnMouse()
	{
		String arrayName = "";
		int removeIndex = -1;
		Entity toBeRemoved = null;
		if (backEntsVisible)
		{
			for (int i = 0; i < backEnts.size(); i++)
			{
				for (int j = 0; j < backEnts.get(i).wireframes.length; j++)
				{

					if (backEnts.get(i).wireframes[j].collide(mouseWF))
					{
						toBeRemoved = (Entity) backEnts.get(i);
						arrayName = "backEnts";
						removeIndex = i;
					}
				}
			}
		}

		if (playEntsVisible)
		{
			for (int i = 0; i < playEnts.size(); i++)
			{
				for (int j = 0; j < playEnts.get(i).wireframes.length; j++)
				{
					if (playEnts.get(i).wireframes[j].collide(mouseWF))
					{
						toBeRemoved = (Entity) playEnts.get(i);
						arrayName = "playEnts";
						removeIndex = i;
					}
				}
			}
		}
		if (frontEntsVisible)
		{
			for (int i = 0; i < frontEnts.size(); i++)
			{
				for (int j = 0; j < frontEnts.get(i).wireframes.length; j++)
				{
					if (frontEnts.get(i).wireframes[j].collide(mouseWF))
					{
						toBeRemoved = (Entity) frontEnts.get(i);
						arrayName = "frontEnts";
						removeIndex = i;
					}
				}
			}
		}
		if (toBeRemoved != null)
		{
			if (arrayName.equals("frontEnts"))
			{
				frontEnts.remove(removeIndex);
			}
			else if (arrayName.equals("playEnts"))
			{
				playEnts.remove(removeIndex);
			}
			else if (arrayName.equals("backEnts"))
			{
				backEnts.remove(removeIndex);
			}
		}
	}
	private void removeAINodeOnMouse()
	{
		for (int i = AINodes.size() - 1; i >= 0; i--)
		{
			if (AINodes.get(i).rect.contains(mx + origX * 2, my + origY * 2))
				AINodes.remove(i);
		}
	}
	private void rotateEntityOnMouse(double rot)
	{
		if (currentEntity != null)
		{
			currentEntity.rotate(rot);
			currentEntity.updateWFs();
		}
		else if (getEntityOnMouse() != null)
		{
			getEntityOnMouse().rotate(rot);
			try
			{
				getEntityOnMouse().updateWFs();
			}
			catch (Exception e)
			{}
		}
	}
	private void togglePhysicalized()
	{
		if (getEntityOnMouse() != null)
		{
			if (getEntityOnMouse().physicalized)
				getEntityOnMouse().physicalized = false;
			else
				getEntityOnMouse().physicalized = true;
		}
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////MouseRelatedMethods/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Keyboard\/
	public void keyPressed(KeyEvent e)
	{
		if (loading)
			return;
		int code = e.getKeyCode();
		manageKeys(code, true);
		if (!inGame)
		{
			switch (code)
			{
				case KeyEvent.VK_SPACE:
					placeEntity();
					break;
				case KeyEvent.VK_R:
					break;
				case KeyEvent.VK_E:
					if (getEntityOnMouse() != null)
					{
						EntityEditor ed = new EntityEditor(getEntityOnMouse(), frontEnts, playEnts, backEnts);
						ed.setVisible(true);
						ed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					break;
				case KeyEvent.VK_S:
					if (ctrl && !shift)
					{
						keyW = false;
						keyS = false;
						keyA = false;
						keyD = false;
						ctrl = false;
						Saver saver = new Saver(frontEnts, playEnts, backEnts, AINodes, spawnPoint);
						saver.setVisible(true);
						saver.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					break;
				case KeyEvent.VK_L:
					if (ctrl && !shift)
					{
						Loader loader = new Loader(frontEnts, playEnts, backEnts, AINodes, spawnPoint);
						loader.setVisible(true);
						loader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					else
					{
						if (frontEntsVisible && backEntsVisible && playEntsVisible)
						{
							backEntsVisible = false;
							playEntsVisible = false;
						}
						else if (frontEntsVisible)
						{
							frontEntsVisible = false;
							playEntsVisible = true;
						}
						else if (playEntsVisible)
						{
							playEntsVisible = false;
							backEntsVisible = true;
						}
						else
						{
							frontEntsVisible = true;
							playEntsVisible = true;
							backEntsVisible = true;
						}
					}
					break;
				case KeyEvent.VK_X:
					renderWireframes = !renderWireframes;

					break;
				case KeyEvent.VK_O:
					nextCurrentArray();
					break;
				case KeyEvent.VK_LEFT:
					if (!shift && (toolNumber == 1 || toolNumber == 2) && currentEntity != null)
						mx -= currentEntity.img[0].getWidth(null);
					else if (shift && (toolNumber == 1 || toolNumber == 2))
						mx -= 1;
					else
						mx -= 10;

					break;
				case KeyEvent.VK_RIGHT:
					if (!shift && (toolNumber == 1 || toolNumber == 2) && currentEntity != null)
						mx += currentEntity.img[0].getWidth(null);
					else if (shift && (toolNumber == 1 || toolNumber == 2))
						mx += 1;
					else
						mx += 10;

					break;
				case KeyEvent.VK_UP:
					if (!shift && (toolNumber == 1 || toolNumber == 2) && currentEntity != null)
						my -= currentEntity.img[0].getHeight(null);
					else if (shift && (toolNumber == 1 || toolNumber == 2))
						my -= 1;
					else
						my -= 10;

					break;
				case KeyEvent.VK_DOWN:
					if (!shift && (toolNumber == 1 || toolNumber == 2) && currentEntity != null)
						my += currentEntity.img[0].getHeight(null);
					else if (shift && (toolNumber == 1 || toolNumber == 2))
						my += 1;
					else
						my += 10;
					break;
				case KeyEvent.VK_F:
					if (getEntityOnMouse() != null)
					{
						Entity temp = getEntityOnMouse().copy();
						removeEntityOnMouse();
						frontEnts.add(temp);
					}
					break;
				case KeyEvent.VK_B:
					if (getEntityOnMouse() != null)
					{
						Entity temp = getEntityOnMouse().copy();
						removeEntityOnMouse();
						backEnts.add(temp);
					}
					break;
				case KeyEvent.VK_P:
					if (getEntityOnMouse() != null)
					{
						Entity temp = getEntityOnMouse().copy();
						removeEntityOnMouse();
						playEnts.add(temp);
					}
					break;
				case KeyEvent.VK_A:
					if (ctrl)
					{
						aiManager.startUp(AINodes, playEnts);
						aiManager.createLinks();
					}
					break;
			}
		}
		// in game
		else
		// in game
		{
			switch (code)
			{
				case KeyEvent.VK_Q:
					if (!ctrl)
						dude.toggleTimeScale();
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
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if (loading)
			return;
		int code = e.getKeyCode();
		manageKeys(code, false);

		if (!inGame)
		{
			if (code == KeyEvent.VK_P)
				togglePhysics();
		}
		else
		{

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
			case KeyEvent.VK_A:
				keyA = b;
				break;
			case KeyEvent.VK_S:
				keyS = b;
				break;
			case KeyEvent.VK_D:
				keyD = b;
				break;
			case KeyEvent.VK_W:
				keyW = b;
				break;
			case KeyEvent.VK_SPACE:
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
			case KeyEvent.VK_G:
				keyG = b;
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
				keyB = b;
				break;
			case KeyEvent.VK_ESCAPE:
				esc = b;
				break;
			case KeyEvent.VK_0:
				key0 = b;
				break;
			case KeyEvent.VK_1:
				key1 = b;
				break;
			case KeyEvent.VK_2:
				key2 = b;
				break;
			case KeyEvent.VK_3:
				key3 = b;
				break;
			case KeyEvent.VK_4:
				key4 = b;
				break;
			case KeyEvent.VK_5:
				key5 = b;
				break;
			case KeyEvent.VK_6:
				key6 = b;
				break;
			case KeyEvent.VK_7:
				key7 = b;
				break;
			case KeyEvent.VK_8:
				key8 = b;
				break;
			case KeyEvent.VK_9:
				key9 = b;
		}
	}
	private void keyboardForEditor()
	{
		if (!shift && !ctrl) // neither
		{
			if (key1)
				changeTool(1);
			else if (key2)
				changeTool(2);
			else if (key3)
				changeTool(3);
			else if (key4)
				changeTool(4);
			else if (key5)
				changeTool(5);
			else if (key6)
				changeTool(6);
			else if (key7)
				changeTool(7);
			else if (key8)
				changeTool(8);
			else if (key9)
				changeTool(9);
			else if (key0)
				changeTool(0);
			if (keyA && !keyD)
				scroll(-scrollSpeed / fps, 0);
			else if (keyD && !keyA)
				scroll(scrollSpeed / fps, 0);
			if (keyW && !keyS)
				scroll(0, -scrollSpeed / fps);
			else if (keyS && !keyW)
				scroll(0, scrollSpeed / fps);
		}
		else if (!ctrl && shift)
		{
			if (keyA && !keyD)
				scroll(-scrollSpeed * shiftMultiplier / fps, 0);
			else if (keyD)
				scroll(scrollSpeed * shiftMultiplier / fps, 0);
			if (keyW && !keyS)
				scroll(0, -scrollSpeed * shiftMultiplier / fps);
			else if (keyS)
				scroll(0, scrollSpeed * shiftMultiplier / fps);
			else if (keyG)
			{
				spawnPoint.x = mx + origX * 2;
				spawnPoint.y = my + origY * 2;
			}
		}
		else if (ctrl && !shift) // control
		{
			if (keyG)
				goInGame();
			if (keyB)
				createBackUp();
			if (keyR)
				restoreBackUp();

		}
		else if (ctrl && shift)
		{
			if (keyG)
				goInGame();
		}

	}
	private void keyboardForDude()
	{
		if (esc)
		{
			goInEditor();
			return;
		}
		if (dude == null)
			return;

		dude.sprinting = shift;
		if (keyA && !keyD)
			dude.moveLeft();
		else if (keyD && !keyA)
			dude.moveRight();
		if (keySpace)
			dude.jump();
		if (keyR)
			dude.reload();
		if (keyS)
			dude.fall();

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
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Keyboard/\

}
