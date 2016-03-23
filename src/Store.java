import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Store extends JFrame implements ActionListener
{
	private static final long	serialVersionUID	= 7149119784680903771L;
	private int					resx				= 800, resy = 600;
	private JPanel				picture, weaponType, weapons;
	private Weapon				currentWeapon;
	Dude						dude;
	AIManager					ai;
	ArrayList<Entity>			weaponBank;

	public Store(Dude d, AIManager aizorz)
	{
		weaponBank = new ArrayList<Entity>();
		WeaponLibrary.loadAssaultRifles(weaponBank);
		WeaponLibrary.loadHeavyWeapons(weaponBank);
		WeaponLibrary.loadPistols(weaponBank);
		WeaponLibrary.loadShotguns(weaponBank);
		WeaponLibrary.loadSMGs(weaponBank);
		WeaponLibrary.loadSnipers(weaponBank);
		weaponBank.add(WeaponLibrary.PTM12());
		weaponBank.add(WeaponLibrary.M320());
		weaponBank.add(WeaponLibrary.XBow());
		ai = aizorz;
		ai.store = this;
		this.setSize(resx, resy);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - resx) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - resy) / 2);

		this.setIconImage(Library.img("PROTekIconSmall.png"));
		setTitle("Armory");
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		// dude = d;
		initButtons();
	}
	private void initButtons()
	{
		picture = new JPanel(new GridLayout(3, 4));
		weaponType = new JPanel(new GridLayout(8, 0));
		weapons = new JPanel(new GridLayout(8, 0));
		weapons.setBorder(new EmptyBorder(20, 20, 20, 20));

		JButton pist = new JButton("Pistols");
		pist.setActionCommand("pistol");
		pist.addActionListener(this);
		weaponType.add(pist);

		JButton sg = new JButton("Shotguns");
		sg.setActionCommand("sg");
		sg.addActionListener(this);
		weaponType.add(sg);

		JButton smg = new JButton("SMGs");
		smg.setActionCommand("smg");
		smg.addActionListener(this);
		weaponType.add(smg);

		JButton ar = new JButton("Assault Rifes");
		ar.setActionCommand("ar");
		ar.addActionListener(this);
		weaponType.add(ar);

		JButton hpr = new JButton("High Powered Rifles");
		hpr.setActionCommand("hpr");
		hpr.addActionListener(this);
		weaponType.add(hpr);

		JButton heavy = new JButton("Heavy Weaponry");
		heavy.setActionCommand("heavy");
		heavy.addActionListener(this);
		weaponType.add(heavy);

		JButton exper = new JButton("Experimental Weaponry");
		exper.setActionCommand("exper");
		exper.addActionListener(this);
		weaponType.add(exper);

		weaponType.add(new JLabel());

		JButton buy = new JButton("Buy");
		buy.setBackground(Color.GREEN);
		buy.setActionCommand("buy");
		buy.addActionListener(this);
		weaponType.add(buy);

		// JButton spec = new JButton("Special Weaponry");
		// spec.setActionCommand("spec");
		// spec.addActionListener(this);
		// weaponType.add(spec);

		weaponType.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.setLayout(new GridLayout(2, 2));

		add(weaponType);
		add(weapons);
		add(picture);

	}
	private void addWeaponButton(Weapon w)
	{
		JButton wepButton = new JButton(w.name.replace('_', ' ') + "     $" + w.price);
		wepButton.setActionCommand(w.name);
		wepButton.addActionListener(this);
		weapons.add(wepButton);
	}
	private void loadPistols()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		WeaponLibrary.loadPistols(weps);
		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void loadSMGs()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		WeaponLibrary.loadSMGs(weps);
		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void loadShotguns()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		WeaponLibrary.loadShotguns(weps);
		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void loadHPRs()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		WeaponLibrary.loadSnipers(weps);
		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void loadARs()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		WeaponLibrary.loadAssaultRifles(weps);
		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void addSpec()
	{}
	private void loadHeavy()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		WeaponLibrary.loadHeavyWeapons(weps);
		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void loadExperimental()
	{
		ArrayList<Entity> weps = new ArrayList<Entity>();
		weps.add(WeaponLibrary.M320());
		weps.add(WeaponLibrary.PTM12());
		// weps.add(WeaponLibrary.PTM38());
		weps.add(WeaponLibrary.XBow());

		weapons.removeAll();
		for (int i = 0; i < weps.size(); i++)
		{
			addWeaponButton((Weapon) weps.get(i));
		}
	}
	private void loadWeapon(Weapon w)
	{
		w.translateTo(250, 200);
		Image i = createImage(500, 400);
		Graphics2D g = (Graphics2D) i.getGraphics();
		w.render(g, new AffineTransform(), 0, 0);
		picture.add(new JLabel(new ImageIcon(i)));

		JPanel info = new JPanel(new GridLayout(3, 2));
		info.setBorder(new EmptyBorder(20, 20, 20, 20));
		info.add(new JLabel(w.name.replace("_", " ")));
		info.add(new JLabel("Clip Size: " + w.fullClip));
		info.add(new JLabel("Rate of Fire: " + (int) w.rof));
		try
		{
			if (!(w instanceof Shotgun))
				info.add(new JLabel("Damage: " + (int) ((Bullet) w.bullet).damage));
			else
				info.add(new JLabel("Damage: " + (int) ((Bullet) w.bullet).damage + " x" + (int) ((Shotgun) w).frags));
		}
		catch (Exception expet)
		{}
		info.add(new JLabel("Price: " + w.price
				));
		picture.add(info);
		currentWeapon = w;
	}
	private void buyWeapon()
	{
		// if (ai.points > currentWeapon.price)
		{
			ai.points -= currentWeapon.price;
			Weapon w = (Weapon) currentWeapon.getNew();
			w.ents = dude.ents;
			if (dude.weaponIndex(w) == -1 && dude.weapons.size() == dude.gunLimit)
				dude.dropCurrentWeapon();
			dude.pickUpWeapon(w);
		}
	}
	public void actionPerformed(ActionEvent arg0)
	{
		String com = arg0.getActionCommand();
		picture.removeAll();
		if (com.equals("buy"))
			buyWeapon();
		else if (com.equals("pistol"))
			loadPistols();
		else if (com.equals("sg"))
			loadShotguns();
		else if (com.equals("smg"))
			loadSMGs();
		else if (com.equals("ar"))
			loadARs();
		else if (com.equals("hpr"))
			loadHPRs();
		else if (com.equals("heavy"))
			loadHeavy();
		else if (com.equals("spec"))
			addSpec();
		else if (com.equals("exper"))
			loadExperimental();

		// ///////////////
		else
			for (int i = 0; i < weaponBank.size(); i++)
			{
				if (com.equals(((Weapon) weaponBank.get(i)).name))
					loadWeapon((Weapon) weaponBank.get(i));
			}

		// else if (com.equals("usp"))
		// loadWeapon(WeaponLibrary.USP45());
		// else if (com.equals("magnum"))
		// loadWeapon(WeaponLibrary.Magnum());
		// else if (com.equals("de"))
		// loadWeapon(WeaponLibrary.DesertEagle());
		//
		// else if (com.equals("spas"))
		// loadWeapon(WeaponLibrary.SPAS());
		// else if (com.equals("moss"))
		// loadWeapon(WeaponLibrary.Mossberg());
		//
		// else if (com.equals("sig"))
		// loadWeapon(WeaponLibrary.SIG553());
		// else if (com.equals("kp6"))
		// loadWeapon(WeaponLibrary.KP6());
		// else if (com.equals("scav"))
		// loadWeapon(WeaponLibrary.Scavenger());
		//
		// else if (com.equals("m14"))
		// loadWeapon(WeaponLibrary.M14());
		// else if (com.equals("svd"))
		// loadWeapon(WeaponLibrary.SVD());
		// else if (com.equals("m98"))
		// loadWeapon(WeaponLibrary.M98B());
		//
		// else if (com.equals("ak"))
		// loadWeapon(WeaponLibrary.AK47());
		// else if (com.equals("m16"))
		// loadWeapon(WeaponLibrary.M16());
		// else if (com.equals("aek"))
		// loadWeapon(WeaponLibrary.AEK());
		// else if (com.equals("acr"))
		// loadWeapon(WeaponLibrary.ACR());
		// else if (com.equals("aug"))
		// loadWeapon(WeaponLibrary.AUG());
		// else if (com.equals("lamm"))
		// loadWeapon(WeaponLibrary.Lammergeier());
		// else if (com.equals("ptm4"))
		// loadWeapon(WeaponLibrary.PTM4());
		//
		// else if (com.equals("m249"))
		// loadWeapon(WeaponLibrary.M249());
		// else if (com.equals("mk46"))
		// loadWeapon(WeaponLibrary.Mk46());
		// else if (com.equals("xm214"))
		// loadWeapon(WeaponLibrary.XM214());

		this.setSize(this.getWidth(), this.getHeight() + 1);
		this.setSize(this.getWidth(), this.getHeight() - 1);
	}
}
