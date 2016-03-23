import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Launcher extends JFrame implements ActionListener
{
	private static final long	serialVersionUID	= -6224390548062243879L;

	private int					glitch1Count		= 0;
	private boolean				threaded			= false;					// Runtime.getRuntime().availableProcessors()
																				// >
																				// 3;
	// String urlG;

	private JPanel				jp;
	String						levelName			= "arena1";

	public static void main(String[] args)
	{
		Launcher l = new Launcher();
		l.setVisible(true);

	}
	public Launcher()
	{
		this.setTitle("PROtek Launcher by Peter Ott");
		this.setIconImage(Library.img("PROTekIconSmall.png"));
		this.setFocusable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenY = Toolkit.getDefaultToolkit().getScreenSize().height;

		addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{}
			public void keyTyped(KeyEvent e)
			{}
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					System.exit(0);
				}
			}
		});

		int width = 307;// 307;
		int height = 96;
		this.setSize(width, height);
		this.setLocation((screenX - width) / 2, (screenY - height) / 2);
		// setTitle("PROLauncher");
		// setResizable(false);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setIconImage(Toolkit.getDefaultToolkit().getImage("Graphics\\PROTekIconSmall.ico"));

		jp = new JPanel();
		jp.setLayout(new FlowLayout(2));

		JButton gameButton = new JButton("Launch Game");// XXX Launch Game
		gameButton.setActionCommand("launchGame");
		gameButton.addActionListener(this);
		jp.add(gameButton);
		JButton editorButton = new JButton("Editor2.0");
		editorButton.setActionCommand("launchEditor");
		editorButton.addActionListener(this);
		jp.add(editorButton);

		JButton armory = new JButton("Armory");
		armory.setActionCommand("armory");
		armory.addActionListener(this);
		// jp.add(armory);

		JButton credits = new JButton("Credits");
		credits.setActionCommand("Credits");
		credits.addActionListener(this);
		jp.add(credits);

		JMenuBar menu = new JMenuBar();

		JMenu misc = new JMenu("Misc.");

		JMenu level = new JMenu("Level");
		JMenuItem buildings = new JMenuItem("Buildings");
		buildings.setActionCommand("build");
		buildings.addActionListener(this);
		JMenuItem flat = new JMenuItem("Flat");
		flat.setActionCommand("flat");
		flat.addActionListener(this);

		JMenuItem help = new JMenu("Help");
		JMenuItem tip1 = new JMenuItem("Win by beating the game");
		JMenuItem tip2 = new JMenuItem("To View the Help Menu click \"Misc.\" then mouse over \"Help\"");
		setJMenuBar(menu);

		menu.add(misc);
		{
			misc.add(help);
			{
				help.add(tip1);
				help.add(tip2);
			}
		}
		menu.add(level);
		level.add(buildings);
		level.add(flat);
		add(jp);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(new Font("Monospaced", Font.BOLD, 14));
		g.setColor(Color.black);
		g.drawString("Esc - Close", 10, this.getHeight() - 10);
	}
	public void actionPerformed(ActionEvent e)
	{
		String name = e.getActionCommand();
		if (name.equals("launchEditor"))
		{
			PROEditor2 e2f = new PROEditor2();
			e2f.setVisible(true);
			e2f.init();
			e2f.setResizable(true);
			e2f.setSize(800, 600);
			e2f.setExtendedState(Frame.MAXIMIZED_BOTH);
			e2f.setThreading(threaded);
			setVisible(false);
		}
		else if (name.equals("launchGame"))
		{
			PROGameDemo pg = new PROGameDemo();
			pg.setVisible(true);
			pg.setLevel(levelName);
			pg.init();
			pg.setResizable(true);
			pg.setSize(800, 600);
			pg.setExtendedState(Frame.MAXIMIZED_BOTH);
			pg.setThreading(threaded);
			pg.setVolume(-10F);
			setVisible(false);
		}
		else if (name.equals("build"))
		{
			levelName = "save1";
		}
		else if (name.equals("flat"))
		{
			levelName = "arena1";
			
		}
		else if (name.equals("Credits"))
		{
			Credits creds = new Credits();
			creds.setVisible(true);
			creds.init();

			creds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else if (name.equals("JFrame@1a15da0"))
		{
			if (glitch1Count == 0)
				System.out.println("What? Did you expect something bad to happen?");
			else if (glitch1Count == 1)
				System.out.println("Well it didn't.");
			else if (glitch1Count == 2)
				System.out.println("...");
			else if (glitch1Count == 3)
				System.out.println("Are you still thinking something will happen?");
			else if (glitch1Count == 4)
				System.out.println("Stop clicking the wierd option.");
			else if (glitch1Count == 7)
				System.out.println("just stop.");
			else if (glitch1Count == 9)
				System.out.println("This isn't getting you anywhere.");
			else if (glitch1Count == 10)
				System.out.println("I'm telling you: NOTHING WILL HAPPEN");
			else if (glitch1Count == 11)
				System.out.println("I'm not talking to you any more.");
			else if (glitch1Count == 15)
				System.out.println("...");
			else if (glitch1Count == 19)
				System.out.println("Sorry, I wasnt paying attention to you");
			else if (glitch1Count == 21)
				System.out.println("Lets play the silent game. I'll win.");
			else if (glitch1Count == 100)
			{
				System.out.println("Okay you finally win. But I have actually won!");
				System.out.println("I just took away time from you that you will never get back!");
			}
			else
				System.out.println("");
			glitch1Count++;
		}
	}
}
