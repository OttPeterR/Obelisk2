import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Loader extends JFrame implements ActionListener, PropertyChangeListener
{
	private static final long	serialVersionUID	= 7506091502820512509L;

	private int					resx				= 450;
	private int					resy				= 100;

	private ArrayList<Entity>	frontEnts, playEnts, backEnts;
	private ArrayList<AINode>	aiNodes;
	private ArrayList<Entity>	allEnts;
	private Vect				spawn;

	private String				fileName			= "";
	private JFormattedTextField	fileNameField		= new JFormattedTextField(fileName);

	public Loader(final ArrayList<Entity> fE, final ArrayList<Entity> pE, final ArrayList<Entity> bE, final ArrayList<AINode> nodes, Vect sp)
	{
		new Thread(
				new Runnable()
				{
					public void run()
					{
						frontEnts = fE;
						playEnts = pE;
						backEnts = bE;
						aiNodes = nodes;
					}
				}).start();

		spawn = sp;
		this.setSize(resx, resy);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - resx) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - resy) / 2);
		this.setIconImage(Library.img("PROTekIconSmall.png"));

		setTitle("Load");
		init();
	}

	public void init()
	{
		JButton loadButton = new JButton("Load");
		loadButton.setActionCommand("load");
		loadButton.addActionListener(this);
		// /////////////////////////////////////////
		JPanel labels = new JPanel(new GridLayout(0, 2));
		labels.setBorder(new EmptyBorder(10, 10, 10, 10));
		labels.add(new JLabel("File Name"));
		labels.add(fileNameField);
		labels.add(loadButton);

		add(labels);
		allEnts = new ArrayList<Entity>();
		StaticEntityLibrary.loadAll(allEnts);
		SceneEntityLibrary.loadAll(allEnts);
		PhysicsEntityLibrary.loadAll(allEnts);
		WeaponLibrary.loadAll(allEnts);
	}
	public void load(String inFileName)
	{
		fileName = inFileName;
		load();
	}
	private void load()
	{
		backEnts.clear();
		playEnts.clear();
		frontEnts.clear();
		aiNodes.clear();
		Scanner scan = null;
		try
		{

			InputStream in = getClass().getResourceAsStream("Saves/" + fileName + ".txt");
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			scan = new Scanner(input);

			scan.next();// skip the file name
			scan.next();// skip first bracket of spawn
			spawn.x = Double.parseDouble(scan.next().replaceAll(",", ""));
			spawn.y = Double.parseDouble(scan.next());
			scan.next();// skip last bracket of spawn

			int arrayToAdd = 1;// 1-back 2-play 3-fronp
			while (scan.hasNext())
			{
				String s = scan.next();
				if (s.equals("["))
					s = scan.next();

				if (s.equals("backEnts"))
					arrayToAdd = 1;
				else if (s.equals("playEnts"))
					arrayToAdd = 2;
				else if (s.equals("frontEnts"))
					arrayToAdd = 3;
				else if (s.equals("AINodes"))
					arrayToAdd = 4;
				else
				{

					switch (arrayToAdd)
					{
						case (1):
						{
							Entity e = null;
							e = loadEntity(scan, s);
							backEnts.add(e);
						}
							break;
						case (2):
						{
							Entity e = null;
							e = loadEntity(scan, s);
							playEnts.add(e);
						}
							break;
						case (3):
						{
							Entity e = null;
							e = loadEntity(scan, s);
							frontEnts.add(e);
						}
							break;
						case (4):
						{
							AINode e = null;
							e = loadNode(scan, s);
							aiNodes.add(e);
						}
							break;
					}
				}
			}
			scan.close();
			syncEnts(frontEnts);
			syncEnts(playEnts);
			syncEnts(backEnts);

			allEnts.clear();
			this.dispose();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private AINode loadNode(Scanner scan, String s)
	{
		double nx = Double.parseDouble(s);
		double ny = Double.parseDouble(scan.next());
		AINode n = new AINode(nx, ny);
		n.type = Integer.parseInt(scan.next());
		scan.next();
		return n;
	}
	private Entity loadEntity(Scanner scan, String s)
	{
		Entity e = get(s);
		e = e.copy();
		boolean wep = false;
		String typeOrHealth = scan.next();
		if (typeOrHealth.equals("Weapon"))
		{
			wep = true;
			Weapon w = (Weapon) get(s);
			w.extraAmmo = Integer.parseInt(scan.next());

			w.currentClip = Integer.parseInt(scan.next());
			scan.next();
			scan.next();
			e = w;
		}
		if (!wep)
			e.health = Double.parseDouble(typeOrHealth);
		else
			e.health = Double.parseDouble(scan.next());
		double x = Double.parseDouble(scan.next());
		double y = Double.parseDouble(scan.next());
		e.translateTo(x, y);

		double ang = Double.parseDouble(scan.next());
		e.rotate(ang);

		e.xSpeed = Double.parseDouble(scan.next());
		e.ySpeed = Double.parseDouble(scan.next());
		e.rotSpeed = Double.parseDouble(scan.next());
		e.gravity = Double.parseDouble(scan.next());
		if (scan.next().equals("true"))
			e.ghost = true;
		else
			e.ghost = false;
		e.penetration = Double.parseDouble(scan.next());
		e.damageThreshold = Double.parseDouble(scan.next());
		if (wep)
			scan.next();
		scan.next();// skip final brace

		return e;

	}
	private Entity get(String name)
	{
		for (int i = allEnts.size() - 1; i >= 0; i--)
		{
			if (allEnts.get(i).name.equals(name))
				return allEnts.get(i).copy();
		}
		return null;
	}
	private void syncEnts(ArrayList<Entity> ents)
	{
		for (int i = ents.size() - 1; i > -1; i--)
		{
			ents.get(i).ents = ents;
		}
	}

	public void propertyChange(PropertyChangeEvent argMeMatey)
	{}
	public void actionPerformed(ActionEvent ev)
	{
		String com = ev.getActionCommand();

		if (com.equals("load"))
		{
			fileName = fileNameField.getText();
			if (!fileName.equals(""))
			{
				load();
				setVisible(false);
			}
		}
	}
}
