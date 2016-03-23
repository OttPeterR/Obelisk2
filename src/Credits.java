import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Credits extends JFrame {
	private static final long serialVersionUID = -6224390548062243879L;

	private int width;
	private int height;
	private Image backbuffer;
	private Graphics backG;
	private ArrayList<Entity> ents;
	private ArrayList<Entity> confetti;
	private AffineTransform trans;
	private double confettiTimer, timeOfLastAdd;
	private Color textColor, backColor;
	private Image backImage;
	private int backImageX, backImageY;
	double fps;

	private long frameCap, lastStartTime;

	public Credits() {
		width = 500;
		height = 620;
		timeOfLastAdd = 0;
		backColor = Color.black;
		textColor = Entity.PROColor.brighter().brighter().brighter().brighter()
				.brighter();
		backImage = Library.img("PROTek.png");
		backImageX = width / 2 - (backImage.getWidth(null) / 2);
		backImageY = height / 2 - (backImage.getHeight(null) / 2);
		fps = 1;
		frameCap = 80;
		lastStartTime = System.nanoTime();
		confettiTimer = .03;
	}

	public void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenY = Toolkit.getDefaultToolkit().getScreenSize().height;

		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});

		ents = new ArrayList<Entity>();
		trans = new AffineTransform();

		confetti = new ArrayList<Entity>();
		confetti.add(ParticleLibrary.ConfettiBlue());
		confetti.add(ParticleLibrary.ConfettiOrange());
		confetti.add(ParticleLibrary.ConfettiGreen());
		confetti.add(ParticleLibrary.ConfettiYellow());
		confetti.add(ParticleLibrary.ConfettiRed());

		this.setSize(width, height);
		this.setLocation((screenX - width) / 2, (screenY - height) / 2);
		setTitle("Credits");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(Library.img("PROTekIconSmall.png"));

		backbuffer = createImage(screenX, screenY);
		backG = backbuffer.getGraphics();
		((Graphics2D) backG).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void paint(Graphics g) {

		long elapsedTime = System.nanoTime() - lastStartTime;
		if (elapsedTime != 0) {
			fps = (fps * .9) + (.1 * (1E9 / elapsedTime));

			lastStartTime = System.nanoTime();

			update(g);

			long currentRenderingTime = System.nanoTime() - lastStartTime;
			if (frameCap > 0)
				if (currentRenderingTime < frameCap) {
					try {
						Thread.sleep(0, (int) (frameCap - currentRenderingTime));
					} catch (Exception e) {
					}
				}
		}
		repaint();
	}

	public void update(Graphics g) {
		g.drawImage(backbuffer, 0, 0, this);
		if (backG != null)
			renderAll((Graphics2D) backG);

	}

	public void renderAll(Graphics2D g) {
		g.setColor(backColor);
		g.fillRect(0, 0, width, height);
		g.setComposite(java.awt.AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, .3F));
		g.drawImage(backImage, backImageX, backImageY, null);
		g.setComposite(java.awt.AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1));

		checkRemoves(ents);
		renderEnts(g);

		manageConfetti();
		renderText(g);
	}

	private void manageConfetti() {
		if ((System.nanoTime() - this.timeOfLastAdd) / 1000000000 > this.confettiTimer) {
			Entity e = confetti.get((int) (Math.random() * confetti.size()))
					.getNew();
			e.timeRemaining = 3.8;
			{

				e.translateTo(-100, 820);
				e.ySpeed = -1200 + ((Math.random() - .5) * 300);
				e.xSpeed = 380 + ((Math.random() - .5) * 450);
				e.rotSpeed = (Math.random() - .5) * 100;
				ents.add(e.copy());
			}
			{
				e.translateTo(600, 820);
				e.ySpeed = -1200 + ((Math.random() - .5) * 300);
				e.xSpeed = -380 - ((Math.random() - .5) * 450);
				e.rotSpeed = (Math.random() - .5) * 100;
			}
			ents.add(e);
			timeOfLastAdd = System.nanoTime();
		}
	}

	public void renderEnts(Graphics2D g) {
		for (int i = ents.size() - 1; i >= 0; i--) {
			try {
				if (ents.get(i).ents == null || ents.get(i).ents.size() == 0)
					ents.get(i).ents = this.ents;
				ents.get(i).render(g, trans, 0, 0);
				// for (int j = i; j < ents.size(); j++)
				// {
				// if (ents.get(i).intersects(ents.get(j)))
				// ents.get(i).collide(ents.get(j));
				// }
				ents.get(i).fps = fps;
				ents.get(i).step();
			} catch (Exception e) {
			}
		}
	}

	private void checkRemoves(ArrayList<Entity> arr) {
		for (int i = arr.size() - 1; i >= 0; i--) {
			try {
				if (arr.get(i).removeMe()) {
					arr.set(i, null);
					arr.remove(i);
				}
			} catch (Exception e) {
				arr.remove(i);
			}
		}
	}

	public void renderText(Graphics g) {

		ArrayList<String> creds = new ArrayList<String>();

		g.setColor(textColor);
		g.setFont(new Font("SansSerif", Font.BOLD, 19));

		g.drawString("I proudly present this PROTEK production", 20, 60);

		creds.add("");
		creds.add("");
		creds.add("");
		creds.add("");
		creds.add("Special Thanks to:");
		creds.add("");
		creds.add("Origional Co-Creators:");
		creds.add("   Ben Mosier IV");
		creds.add("   Ushan Abeysekera");
		creds.add("");
		creds.add("Doctor Noob for his gun creator");
		creds.add("   pimpmygun.doctornoob.com");
		creds.add("");
		creds.add("Audio Providers: ");
		creds.add(" Music By: Maximilian Gucker (Guckk)");
		creds.add(" -mp3scull.com");
		creds.add(" Those at SoundBible.com");
		creds.add("   -Mike Koenig");
		creds.add("   -Kibblesbob");
		creds.add("   -RA The Sun God");
		creds.add("   -Justin");
		creds.add("   -snottyboi");

		for (int i = 0; i < creds.size(); i++) {
			g.drawString(creds.get(i), 20, 80 + (i * 25));
		}

	}
}
