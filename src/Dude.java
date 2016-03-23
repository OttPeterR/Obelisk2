import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Dude extends Biped
{
	Image				upperArmR, upperArmL, lowerArmR, lowerArmL, elbowR, elbowL,
						handTriggerR, handTriggerL, handHoldR, handHoldL;

	ArrayList<Weapon>	weapons;
	int					gunLimit;
	Entity				groundEntity;
	int					grenades;
	boolean				focus					= false;
	boolean				pickUpEntityRequested	= false;
	boolean				unlimitedAmmo			= false, unlimitedClip = false, invulnerable = false;
	boolean				grabBlocked				= false, grabbing = false;
	double				lastGrab				= 0;
	double				weightedWalkSpeed;
	double				slowMotion				= .07;
	boolean				slowMotionActive		= false, unlimitedSlowMotion = false;
	double				slowTimeLeft, timeUntilSlowMotionRecharge,
						timeUntilSlowMotionRechargeFull, slowTimeLeftFull = 2, switchTime;
	boolean				melee;
	double				timeTillNextMelee;
	ArrayList<String>	description;
	Vect				translation				= new Vect(0, 0);
	double				timeOnGround			= 0;
	boolean				nextWeaponRequested		= false;
	boolean				lastWeaponRequested		= false;
	double				timeUntilWeaponSwitch	= 0;
	Wireframe			grab, grabExclude;
	Vect				grabOffset, grabExcludeOffset;
	Color				UIColor					= Entity.PROColorBlaze;
	Color				leftArmColor, rightArmColor;
	String				killerInfo				= "Yourself";
	ArrayList<Entity>	meleeHits;

	public Dude()
	{
		super("", null, "noImage", null, new Vect(0, 0), 100, -1, 180, .2, .2,
				100);
		construct();
		meleeHits = new ArrayList<Entity>();
		numWF = 1;
		description = new ArrayList<String>();
		weapons = new ArrayList<Weapon>();
		weightedWalkSpeed = 0 + walkSpeed;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Rendering\/
	public void render(Graphics2D g, AffineTransform trans, double origX,
			double origY)
	{
		if (timeUntilWeaponSwitch <= 0 && !isDead())
			if (facingRight)
			{
				renderLeftArm(g, trans, origX, origY);
				super.render(g, trans, origX, origY);
				renderCurrentGun(g, trans, origX, origY);
				renderRightArm(g, origX, origY);
			}
			else
			{
				renderRightArm(g, origX, origY);
				renderCurrentGun(g, trans, origX, origY);
				super.render(g, trans, origX, origY);
				renderLeftArm(g, trans, origX, origY);
			}
		else
			super.render(g, trans, origX, origY);
		renderGroundEntity(g, trans, origX, origY);
	}

	public void renderWireframes(Graphics2D g, double origX, double origY)
	{
		super.renderWireframes(g, origX, origY);
		grab.render(g, origX, origY, PROColor);
		grabExclude.render(g, origX, origY, PROColor);
	}

	private void renderRightArm(Graphics2D g, double origX, double origY)
	{
		if (weapons.size() > 0)
		{
			g.setColor(rightArmColor);
			g.setStroke(new BasicStroke(13));

			double tempTriggerX = 0 + weapons.get(0).trigger.x;
			double tempTriggerY = 0 + weapons.get(0).trigger.y;

			double armLength = 18;
			if (!facingRight)
				armLength *= -1;

			double triggerAngle = Math.atan2(
					weapons.get(0).trigger.y - weapons.get(0).shoulder.y,
					weapons.get(0).trigger.x - weapons.get(0).shoulder.x);
			double midX = weapons.get(0).x
					+ (weapons.get(0).trigger.x + weapons.get(0).shoulder.x)
					/ 2;
			double midY = weapons.get(0).y
					+ (weapons.get(0).trigger.y + weapons.get(0).shoulder.y)
					/ 2;
			int elbowX = (int) (midX
					+ (Math.cos(triggerAngle + (Math.PI / 2)) * armLength) - origX);
			int elbowY = (int) (midY
					+ (Math.sin(triggerAngle + (Math.PI / 2)) * armLength) - origY);

			g.fillOval(elbowX - 7, elbowY - 7, 14, 14);
			g.drawLine((int) (x - origX), (int) (y - origY - 138), elbowX,
					elbowY);
			g.drawLine(elbowX, elbowY, (int) (weapons.get(0).x
					+ weapons.get(0).trigger.x - origX),
					(int) (weapons.get(0).y + weapons.get(0).trigger.y - origY));
			g.setStroke(new BasicStroke(1));
			weapons.get(0).trigger.y = tempTriggerY;
			weapons.get(0).trigger.x = tempTriggerX;
		}
	}

	private void renderLeftArm(Graphics2D g, AffineTransform trans,
			double origX, double origY)
	{
		if (weapons.size() > 0)
		{
			g.setColor(leftArmColor);
			g.setStroke(new BasicStroke(13));

			double armLength = 18;
			if (!facingRight)
				armLength *= -1;
			double tempHoldX = 0 + weapons.get(0).hold.x;
			double tempHoldY = 0 + weapons.get(0).hold.y;
			double reloadAngle = 0;
			if (weapons.get(0).reloading)
			{
				double holdRad = Math
						.hypot(weapons.get(0).shoulder.x
								- weapons.get(0).clipOffset.x,
								weapons.get(0).shoulder.y
										- weapons.get(0).clipOffset.y);

				double reloadAmmount = weapons.get(0).reloadTimeLeft
						/ (weapons.get(0).reloadTime * 2);

				if (weapons.get(0).reloadTimeLeft > (weapons.get(0).reloadTime / 2))
					reloadAmmount = .25
							- (weapons.get(0).reloadTimeLeft - (weapons.get(0).reloadTime / 2))
							/ (weapons.get(0).reloadTime * 2);

				double reloadAngleConst = 1.8;
				reloadAngle = (reloadAmmount * Math.PI)
						+ ((1 - reloadAmmount) * -Math.PI / 2) - Math.PI
						/ reloadAngleConst;

				holdRad *= ((.25 - reloadAmmount) / .25);

				if (!facingRight)
				{
					reloadAmmount *= -1;
					reloadAngle = (reloadAmmount * Math.PI)
							+ ((1 - reloadAmmount) * -Math.PI / 2) + Math.PI
							/ reloadAngleConst;
				}

				weapons.get(0).hold.x = weapons.get(0).shoulder.x
						- Math.cos(reloadAngle) * holdRad;
				weapons.get(0).hold.y = weapons.get(0).shoulder.y
						- Math.sin(reloadAngle) * holdRad;
			}

			double holdAngle = Math.atan2(
					weapons.get(0).hold.y - weapons.get(0).shoulder.y,
					weapons.get(0).hold.x - weapons.get(0).shoulder.x);
			double midX = weapons.get(0).x
					+ (weapons.get(0).hold.x + weapons.get(0).shoulder.x) / 2;
			double midY = weapons.get(0).y
					+ (weapons.get(0).hold.y + weapons.get(0).shoulder.y) / 2;
			int elbowX = (int) (midX
					+ (Math.cos(holdAngle + (Math.PI / 2)) * armLength) - origX);
			int elbowY = (int) (midY
					+ (Math.sin(holdAngle + (Math.PI / 2)) * armLength) - origY);

			g.fillOval(elbowX - 7, elbowY - 7, 14, 14);
			g.drawLine((int) (x - origX), (int) (y - origY - 138), elbowX,
					elbowY);
			g.drawLine(elbowX, elbowY, (int) (weapons.get(0).x
					+ weapons.get(0).hold.x - origX), (int) (weapons.get(0).y
					+ weapons.get(0).hold.y - origY));

			if (weapons.get(0).reloading
					&& weapons.get(0).reloadTimeLeft < (weapons.get(0).reloadTime / 2))
			{
				if (weapons.get(0).useClip)
					if (facingRight)
					{
						weapons.get(0).clip.rotate(-weapons.get(0).clip.ang
								+ reloadAngle + Math.PI * .9);
						weapons.get(0).clip.translateTo(weapons.get(0).x
								+ weapons.get(0).hold.x, weapons.get(0).y
								+ weapons.get(0).hold.y);
						weapons.get(0).clip.render(g, trans, origX, origY);
					}
					else
					{
						Image tempImage = weapons.get(0).clip.img[0];
						weapons.get(0).clip.img[0] = weapons.get(0).clip.img[1];
						weapons.get(0).clip.img[1] = tempImage;

						weapons.get(0).clip.rotate(-weapons.get(0).clip.ang
								+ reloadAngle - Math.PI * 1.9);
						weapons.get(0).clip.translateTo(weapons.get(0).x
								+ weapons.get(0).hold.x, weapons.get(0).y
								+ weapons.get(0).hold.y);
						weapons.get(0).clip.render(g, trans, origX, origY);

						Image tempImage2 = weapons.get(0).clip.img[0];
						weapons.get(0).clip.img[0] = weapons.get(0).clip.img[1];
						weapons.get(0).clip.img[1] = tempImage2;
					}
				else if (weapons.get(0).shell != null)
				{
					Entity tempShell = weapons.get(0).shell.getNew();
					if (facingRight)
					{
						tempShell.rotate(reloadAngle + Math.PI * .9);
						tempShell.translateTo(weapons.get(0).x
								+ weapons.get(0).hold.x, weapons.get(0).y
								+ weapons.get(0).hold.y);
						tempShell.render(g, trans, origX, origY);
					}
					else
					{
						tempShell.rotate(reloadAngle - Math.PI * .9);
						tempShell.translateTo(weapons.get(0).x
								+ weapons.get(0).hold.x, weapons.get(0).y
								+ weapons.get(0).hold.y);
						tempShell.render(g, trans, origX, origY);
					}
				}
				else if (weapons.get(0).bullet != null
						&& weapons.get(0).bullet instanceof PhysicsEntity)
				{
					Entity tempBullet = weapons.get(0).bullet.getNew();
					if (facingRight)
					{
						tempBullet.rotate(reloadAngle + Math.PI * .9);
						tempBullet.translateTo(
								weapons.get(0).x + weapons.get(0).hold.x,
								weapons.get(0).y + weapons.get(0).hold.y);
						tempBullet.render(g, trans, origX, origY);
					}
					else
					{
						tempBullet.rotate(reloadAngle - Math.PI * .9);
						tempBullet.translateTo(
								weapons.get(0).x + weapons.get(0).hold.x,
								weapons.get(0).y + weapons.get(0).hold.y);
						tempBullet.render(g, trans, origX, origY);
					}
				}
			}

			g.setStroke(new BasicStroke(1));
			weapons.get(0).hold.x = tempHoldX;
			weapons.get(0).hold.y = tempHoldY;
		}
	}

	private void renderGroundEntity(Graphics2D g, AffineTransform trans,
			double origX, double origY)
	{
		g.setFont(new Font("SansSerif", Font.BOLD, 13));

		if (groundEntity != null)
		{
			if (groundEntity instanceof Weapon)
			{
				int drawX = (int) (groundEntity.x - origX);
				int drawY = (int) (groundEntity.y - origY - 40);
				g.setStroke(new BasicStroke(2));

				g.setColor(Color.black);
				g.drawString(groundEntity.name.replace('_', ' '), drawX + 1,
						drawY + 1);
				g.drawString(((Weapon) groundEntity).currentClip + " / "
						+ ((Weapon) groundEntity).extraAmmo, drawX + 1,
						drawY + 1 + 13);
				g.drawString(groundEntity.name.replace('_', ' '), drawX + 1,
						drawY - 1);
				g.drawString(((Weapon) groundEntity).currentClip + " / "
						+ ((Weapon) groundEntity).extraAmmo, drawX + 1,
						drawY - 1 + 13);
				g.drawString(groundEntity.name.replace('_', ' '), drawX - 1,
						drawY + 1);
				g.drawString(((Weapon) groundEntity).currentClip + " / "
						+ ((Weapon) groundEntity).extraAmmo, drawX - 1,
						drawY + 1 + 13);
				g.drawString(groundEntity.name.replace('_', ' '), drawX - 1,
						drawY - 2);
				g.drawString(((Weapon) groundEntity).currentClip + " / "
						+ ((Weapon) groundEntity).extraAmmo, drawX - 1,
						drawY - 1 + 13);

				g.setColor(UIColor);
				g.drawString(groundEntity.name.replace('_', ' '), drawX, drawY);
				g.drawString(((Weapon) groundEntity).currentClip + " / "
						+ ((Weapon) groundEntity).extraAmmo, drawX, drawY + 13);
			}
			else if (groundEntity instanceof Item)
			{
				((Item) groundEntity).renderInfo(g, origX, origY, this,
						Color.RED);
			}
			g.setStroke(new BasicStroke(1));
		}
	}

	public void renderHUD(Graphics2D g, AffineTransform trans, double origX,
			double origY, double mx, double my, double screenX, double screenY,
			AIManager ai)
	{
		Font prev = g.getFont();
		g.setFont(new Font("SansSerif", Font.BOLD, 15));
		renderHealth(g, screenX, screenY);
		renderSlowMotionBar(g, screenX, screenY);
		renderScore(g, screenX, screenY, ai);
		if (!isDead())
		{
			translation.x = (-(origX - x + (screenX / 2)) * (10 / timeScale))
					/ fps;
			translation.y = (-(origY - y + (screenY * 5 / 8)) * (10 / timeScale))
					/ fps;
			if ((x - origX) - (mx + origX) > 0)
				facingRight = false;
			else
				facingRight = true;
			if (timeTillNextMelee > .3)
			{
				double mel = (timeTillNextMelee - .3 - .11) / .09;
				if (facingRight)
					mx = x - origX * 2 + 5000 * Math.cos(mel);
				else
					mx = x - origX * 2 - 5000 * Math.cos(mel);
				my = y - origY * 2 - 100 + 5000 * Math.sin(mel);
			}
			else
				lookAng = Math.atan2((x - origX) - (mx + origX),
						-((y - 175 - origY) - (my + origY)));

			Font prevFont = g.getFont();
			g.setFont(new Font(prevFont.getName(), Font.BOLD, prevFont
					.getSize()));
			if (timeUntilWeaponSwitch <= 0)
			{
				renderGunHUD(g, trans, origX, origY, mx, my, screenX, screenY);
				if (weapons != null && weapons.size() > 0
						&& weapons.get(0) != null)
				{
					// double newAng = Math.atan2(my - (y - 110 - origY * 2), mx
					// - (x - origX * 2));
					double newAng = Math
							.atan2(my
									- (weapons.get(0).y
											+ weapons.get(0).shoulder.y - origY * 2),
									mx
											- (weapons.get(0).x
													+ weapons.get(0).shoulder.x - origX * 2));
					weapons.get(0).rotate(-weapons.get(0).ang + newAng);
					weapons.get(0).flip(mx + (origX * 2) > x);
				}
			}
			else
				renderWeaponSwitching(g, origX, origY, mx, my);

			g.setFont(prevFont);
		}
		else
		{
			translation.x = 0;
			translation.y = 0;
			// maybe do some kinda death message myah
			g.setColor(Color.red);
		}
		g.setFont(prev);
	}
	private void renderWeaponSwitching(Graphics2D g, double origX,
			double origY, double mx, double my)
	{
		if (weapons.size() > 0)
		{
			if (weapons.size() == 1)
				switchTime = ((weapons.get(0).mass / 30)) * 2;
			int switchAngle = (int) -(Math.PI * 58 * ((switchTime - timeUntilWeaponSwitch) / switchTime));

			if (nextWeaponRequested)
				switchAngle *= -1;
			if (facingRight)
				switchAngle *= -1;

			g.setStroke(new java.awt.BasicStroke(6));
			g.setColor(Color.black);
			g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60,
					(int) switchAngle - 15, 30);
			g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60,
					(int) switchAngle + 180 - 15, 30);

			g.setStroke(new java.awt.BasicStroke(4));
			g.setColor(UIColor);
			g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60,
					(int) switchAngle - 15, 30);
			g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60,
					(int) switchAngle + 180 - 15, 30);

			g.setStroke(new java.awt.BasicStroke(1));
		}
	}

	public void renderCurrentGun(Graphics2D g, AffineTransform trans,
			double origX, double origY)
	{
		if (weapons != null && weapons.size() > 0 && weapons.get(0) != null)
		{
			g.setStroke(new BasicStroke(15));
			g.setColor(Color.black);
			if (timeUntilWeaponSwitch <= 0)
			{
				if (facingRight)
				{
					if (weapons.get(0).reloading)
						weapons.get(0)
								.rotate(-weapons.get(0).ang - Math.PI / 8);
					weapons.get(0).render(g, trans, origX, origY);
					// right arm
					// left hand
				}
				else
				{
					if (weapons.get(0).reloading)
						weapons.get(0).rotate(
								-weapons.get(0).ang + Math.PI * 9 / 8);
					weapons.get(0).render(g, trans, origX, origY);
				}
			}
		}

	}

	private void renderScore(Graphics2D g, double screenX, double screenY,
			AIManager ai)
	{
		Font prevFont = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, 20));

		g.setColor(Color.black);
		g.drawString("Kills: " + ai.zombiesKilled, 21, (int) screenY - 51);
		g.drawString("Kills: " + ai.zombiesKilled, 21, (int) screenY - 49);
		g.drawString("Kills: " + ai.zombiesKilled, 19, (int) screenY - 51);
		g.drawString("Kills: " + ai.zombiesKilled, 19, (int) screenY - 49);
		g.drawString("$" + ai.points, 19, (int) screenY - 19);
		g.drawString("$" + ai.points, 19, (int) screenY - 21);
		g.drawString("$" + ai.points, 21, (int) screenY - 19);
		g.drawString("$" + ai.points, 21, (int) screenY - 21);
		g.drawString("Level: " + ai.level, (int) screenX - 181, 81);
		g.drawString("Level: " + ai.level, (int) screenX - 179, 79);
		g.drawString("Level: " + ai.level, (int) screenX - 181, 81);
		g.drawString("Level: " + ai.level, (int) screenX - 179, 79);

		g.setColor(UIColor);
		g.drawString("Kills: " + ai.zombiesKilled, 20, (int) screenY - 50);
		g.drawString("$" + ai.points, 20, (int) screenY - 20);

		g.drawString("Level: " + ai.level, (int) screenX - 180, 80);
		if (ai.zombiesLeftInRound > 1)
		{
			g.setColor(Color.black);
			g.drawString(ai.getZombiesLeft() + " Zombies Left",
					(int) screenX - 181, 56);
			g.drawString(ai.getZombiesLeft() + " Zombies Left",
					(int) screenX - 181, 54);
			g.drawString(ai.getZombiesLeft() + " Zombies Left",
					(int) screenX - 179, 56);
			g.drawString(ai.getZombiesLeft() + " Zombies Left",
					(int) screenX - 179, 54);
			g.setColor(UIColor);
			g.drawString(ai.getZombiesLeft() + " Zombies Left",
					(int) screenX - 180, 55);
		}
		else if (ai.zombiesLeftInRound == 1) // for grammar
		{
			g.setColor(Color.black);
			g.drawString(ai.getZombiesLeft() + " Zombie Left",
					(int) screenX - 181, 54);
			g.drawString(ai.getZombiesLeft() + " Zombie Left",
					(int) screenX - 181, 56);
			g.drawString(ai.getZombiesLeft() + " Zombie Left",
					(int) screenX - 179, 54);
			g.drawString(ai.getZombiesLeft() + " Zombie Left",
					(int) screenX - 179, 56);
			g.setColor(UIColor);
			g.drawString(ai.getZombiesLeft() + " Zombie Left",
					(int) screenX - 180, 55);
		}
		else
		{
			g.setColor(Color.black);
			g.drawString("Round starting in: " + (int) (ai.setUpTimeLeft()),
					(int) screenX - 209, 54);
			g.drawString("Round starting in: " + (int) (ai.setUpTimeLeft()),
					(int) screenX - 209, 56);
			g.drawString("Round starting in: " + (int) (ai.setUpTimeLeft()),
					(int) screenX - 211, 54);
			g.drawString("Round starting in: " + (int) (ai.setUpTimeLeft()),
					(int) screenX - 211, 56);

			g.setColor(UIColor);
			g.drawString("Round starting in: " + (int) (ai.setUpTimeLeft()),
					(int) screenX - 210, 55);
		}

		g.setFont(prevFont);
	}

	private void renderHealth(Graphics2D g, double screenX, double screenY)
	{

		g.setColor(Color.black);
		g.drawString("Health: " + (int) Math.ceil(100 * health / fullHealth)
				+ " %", (int) (screenX * .72), (int) (screenY * .95) - 3);
		g.drawString("Health: " + (int) Math.ceil(100 * health / fullHealth)
				+ " %", (int) (screenX * .72), (int) (screenY * .95) - 5);
		g.drawString("Health: " + (int) Math.ceil(100 * health / fullHealth)
				+ " %", (int) (screenX * .72) + 2, (int) (screenY * .95) - 3);
		g.drawString("Health: " + (int) Math.ceil(100 * health / fullHealth)
				+ " %", (int) (screenX * .72) + 2, (int) (screenY * .95) - 5);

		g.setColor(new Color(PROColor.getRed(), PROColor.getGreen(), PROColor
				.getBlue(), 75));
		g.fillRect((int) (screenX * .72), (int) (screenY * .95),
				(int) (screenX * .25), (int) (screenY * .03));

		g.setColor(PROColor);
		if (health / fullHealth < .25)
			g.setColor(PROColorBlaze);
		g.drawString("Health: " + (int) Math.ceil(100 * health / fullHealth)
				+ " %", (int) (screenX * .72) + 1, (int) (screenY * .95) - 4);
		g.fillRect((int) (screenX * .72), (int) (screenY * .95),
				(int) ((screenX * .25) * (health / fullHealth)),
				(int) (screenY * .03));
	}

	private void renderSlowMotionBar(Graphics2D g, double screenX,
			double screenY)
	{

		g.setColor(Color.BLACK);
		g.drawString("SlowMotion", (int) (screenX * .72),
				(int) (screenY * .91) - 5);
		g.drawString("SlowMotion", (int) (screenX * .72),
				(int) (screenY * .91) - 3);
		g.drawString("SlowMotion", (int) (screenX * .72) + 2,
				(int) (screenY * .91) - 5);
		g.drawString("SlowMotion", (int) (screenX * .72) + 2,
				(int) (screenY * .91) - 3);

		if (!slowMotionActive)
			g.setColor(new Color(PROColor.getRed(), PROColor.getGreen(),
					PROColor.getBlue(), 75));
		else
			g.setColor(new Color(PROColorBlaze.getRed(), PROColorBlaze
					.getGreen(), PROColorBlaze.getBlue(), 75));
		g.fillRect((int) (screenX * .72), (int) (screenY * .91),
				(int) (screenX * .25), (int) (screenY * .01));

		if (!slowMotionActive)
			g.setColor(PROColor);
		else
			g.setColor(PROColorBlaze);

		g.drawString("SlowMotion", (int) (screenX * .72) + 1,
				(int) (screenY * .91) - 4);
		g.fillRect((int) (screenX * .72), (int) (screenY * .91),
				(int) ((screenX * .25) * (slowTimeLeft / slowTimeLeftFull)),
				(int) (screenY * .01));
	}

	private void renderGunHUD(Graphics2D g, AffineTransform trans,
			double origX, double origY, double mx, double my, double screenX,
			double screenY)
	{
		if (weapons.size() > 0 && weapons.get(0) != null)
		{
			weapons.get(0).renderHUD(g, UIColor, origX, origY, mx, my);

			if (weapons.size() > 1)
			{
				g.setColor(UIColor);
				int height = 20;
				for (int i = 1; i < Math.min(weapons.size(), 13); i++)
				{
					renderSmallGunInfo(
							g,
							trans,
							weapons.get(i),
							30,
							screenY * .035 + height
									+ weapons.get(i).img[0].getHeight(null) / 2);
					height += weapons.get(i).img[0].getHeight(null) + 10;
				}
			}
		}
		else
		{
			g.setColor(UIColor);
			g.fillOval((int) (mx + origX) - 5, (int) (my + origY) - 5, 10, 10);
		}

	}

	private void renderSmallGunInfo(Graphics2D g, AffineTransform trans,
			Weapon w, double inx, double iny)
	{
		g.setColor(UIColor);
		w.rotate(-w.ang);
		w.rotateToward(w.x + 100, w.y + .00000001);
		w.renderAt(g, trans, inx + 6 + w.img[0].getWidth(null) / 2, iny);

		g.setColor(Color.black);
		g.drawString(w.name, (int) (12 + inx + w.img[0].getWidth(null)) - 1,
				(int) iny - 1);
		g.drawString(w.name, (int) (12 + inx + w.img[0].getWidth(null)) - 1,
				(int) iny + 1);
		g.drawString(w.name, (int) (12 + inx + w.img[0].getWidth(null)) + 1,
				(int) iny - 1);
		g.drawString(w.name, (int) (12 + inx + w.img[0].getWidth(null)) + 1,
				(int) iny + 1);
		g.drawString(w.currentClip + " / " + w.extraAmmo,
				(int) (12 + inx + w.img[0].getWidth(null)) - 1, (int) iny + 14);
		g.drawString(w.currentClip + " / " + w.extraAmmo,
				(int) (12 + inx + w.img[0].getWidth(null)) - 1, (int) iny + 16);
		g.drawString(w.currentClip + " / " + w.extraAmmo,
				(int) (12 + inx + w.img[0].getWidth(null)) + 1, (int) iny + 14);
		g.drawString(w.currentClip + " / " + w.extraAmmo,
				(int) (12 + inx + w.img[0].getWidth(null)) + 1, (int) iny + 16);

		g.setColor(UIColor);
		g.drawString(w.name, (int) (12 + inx + w.img[0].getWidth(null)),
				(int) iny);
		g.drawString(w.currentClip + " / " + w.extraAmmo,
				(int) (12 + inx + w.img[0].getWidth(null)), (int) iny + 15);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Rendering/\
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////Step\/
	public void step()
	{
		if (grabBlocked)
			for (int i = entsHit.size() - 1; i > -1; i--)
			{
				if (grab.collide(entsHit.get(i))
						&& !wireframes[0].collide(entsHit.get(i)))
					entsHit.remove(i);
			}
		if (melee && meleeHits.size() > 0)
		{
			timeTillNextMelee = .5;
			for (int i = 0; i < meleeHits.size(); i++)
			{
				if (meleeHits.get(i) instanceof Biped)
				{
					meleeHits.get(i).damage(8 + (int) (Math.random() * 10));
					if (facingRight)
						meleeHits.get(i).xSpeed += 250;
					else
						meleeHits.get(i).xSpeed -= 250;
					meleeHits.get(i).ySpeed -= 100;
				}
			}
		}
		timeTillNextMelee -= 1 / fps;
		meleeHits.clear();
		melee = false;
		if (entsHit.size() > 0)
		{
			int numEnts = 0;
			double avgHitAngle = 0;

			for (int i = entsHit.size() - 1; i >= 0; i--)
			{
				if (grabExclude.collide(entsHit.get(i))
						&& !wireframes[0].collide(entsHit.get(i)))
				{
					grabBlocked = true;
					entsHit.remove(i);
				}
			}
			double walkSlope = Math.PI / 4;
			while (entsHit.size() != 0)
			{
				Entity e = entsHit.get(0);
				double leastSlope = getLeastHitSlope(e);
				if (e instanceof StaticEntity)
				{
					if (!grabBlocked && grab.collide(e))
					{
						hold(e);
						grounded = true;
						lastGrab = System.nanoTime();
					}
					if (!grabbing && wireframes[0].collide(e))
					{
						avgHitAngle += leastSlope;
						numEnts++;
					}
				}
				else
				{
					double tempX = x;
					double tempY = y;
					col(e);
					if (Math.hypot(xSpeed, ySpeed) > 7000
							|| !(x > Integer.MIN_VALUE && y > Integer.MIN_VALUE))
					{
						xSpeed /= 100;
						ySpeed /= 100;
						x = tempX;
						y = tempY;
					}
				}
				if (Math.abs(leastSlope) < walkSlope
						|| Math.PI - Math.abs(leastSlope) < walkSlope)
				{
					if (feet.collide(e) && feetCheck.collide(e))
					{
						if (wireframes[0].collide(e))
							feetPlanted = true;
						grounded = true;
					}
				}
				// 45 degrees max walk slope

				entsHit.remove(0);
			}
			{
				avgHitAngle /= numEnts;
				Vect mySpeed = new Vect(xSpeed, ySpeed);
				double myAng = Math.atan(-ySpeed / xSpeed);
				if (xSpeed < 0)
					myAng += Math.PI;
				Vect alignedSpeed = new Vect(mySpeed.velocity()
						* Math.cos(avgHitAngle), mySpeed.velocity()
						* Math.sin(avgHitAngle));
				// x is perpendicular to surface
				// y is parallel to surface
				if (grounded)
				{
					if (feetPlanted)
						ySpeed = 0;
					if ((!moveR && !moveL)
							|| ((xSpeed > 0 && moveL) || (xSpeed < 0 && moveR)))
					{
						xSpeed -= ((xSpeed * 3) / fps);
					}
				}
				else
				{
					alignedSpeed.x = (alignedSpeed.x * bounce);
					alignedSpeed.y -= (2 * alignedSpeed.y);

					double resultAngle = Math.PI - myAng - 2
							* (avgHitAngle - myAng);

					if (alignedSpeed.x > Integer.MIN_VALUE
							&& alignedSpeed.y > Integer.MIN_VALUE)
					{
						if (focus && grounded)
							alignedSpeed.x = 0;
						xSpeed = alignedSpeed.velocity()
								* Math.cos(resultAngle);
						ySpeed = alignedSpeed.velocity()
								* Math.sin(resultAngle);
					}
				}
			}
		}
		if (moveR || moveL)
			focus = false;
		manageMovement();
		advance();
		translateTo(x, y);
		computeRect();

		if (health < fullHealth)
		{
			if (timeUntilHealthRegen <= 0)
			{
				health += (healVal / fps);
				timeUntilHealthRegen = 0 + healthRegenTime;
			}
			timeUntilHealthRegen -= (1 / fps);
		}
		if (health > fullHealth)
			health = 0 + fullHealth;
		if (!unlimitedSlowMotion)
		{
			if (slowMotionActive)
			{
				slowTimeLeft -= 1 / fps;
				if (slowTimeLeft <= 0)
					slowMotionActive = false;
			}
			else
			{
				timeUntilSlowMotionRecharge -= 1 / fps;
				if (slowTimeLeft < slowTimeLeftFull)
				{
					if (timeUntilSlowMotionRecharge <= 0)
					{
						slowTimeLeft += .1 / fps;
					}
				}
			}
			if (slowTimeLeft > slowTimeLeftFull)
				slowTimeLeft = 0 + slowTimeLeftFull;
		}
		weightedWalkSpeed = 0 + walkSpeed;
		if (weapons != null && weapons.size() > 0)
		{
			for (int i = 0; i < weapons.size(); i++)
			{
				weapons.get(i).xSpeed = 0;
				weapons.get(i).ySpeed = 0;
				weapons.get(i).gravity = 0;
				weapons.get(i).step();
				weapons.get(i).xSpeed = xSpeed;
				weapons.get(i).ySpeed = ySpeed;
				weapons.get(i).gravity = gravity;
				weapons.get(i).fps = fps;
				weapons.get(i).timeScale = this.timeScale;
				weightedWalkSpeed -= weapons.get(i).mass * 200;
			}
			if (weightedWalkSpeed < 0)
				weightedWalkSpeed = 0;
			weapons.get(0).accuracy += (Math
					.abs(Math.hypot(xSpeed, ySpeed) / 1300) / fps);
			if (weapons.get(0).accuracy > weapons.get(0).minAccuracy)
				weapons.get(0).accuracy = weapons.get(0).minAccuracy;

		}
		manageWeaponSwitching();
		weightedWalkSpeed = 0 + walkSpeed;
	}

	public void advance()
	{
		ySpeed += (gravity / fps);
		x += (xSpeed / fps);
		y += (ySpeed / fps);
		speedBrake();
		advanceWeapon();
	}

	private void speedBrake()
	{
		int maxSpeed = 500;
		if (xSpeed > maxSpeed)
			xSpeed = maxSpeed;
		else if (xSpeed < -maxSpeed)
			xSpeed = -maxSpeed;
	}

	private void advanceWeapon()
	{
		if (weapons.size() > 0 && weapons.get(0) != null)
		{
			if (!weapons.get(0).reloading)
				if (focus)
					weapons.get(0).translateTo(x - weapons.get(0).shoulder.x,
							y - 145 - weapons.get(0).shoulder.y);

				else if (facingRight)
					weapons.get(0)
							.translateTo(
									x
											- ((weapons.get(0).shoulderRad - 30) * Math.cos(weapons
													.get(0).shoulderAng
													+ weapons.get(0).ang)),
									y
											- 125
											- ((weapons.get(0).shoulderRad - 30) * Math
													.sin(weapons.get(0).shoulderAng
															+ weapons.get(0).ang)));
				else
					weapons.get(0)
							.translateTo(
									x
											- ((weapons.get(0).shoulderRad - 30) * Math.cos(-weapons
													.get(0).shoulderAng
													+ weapons.get(0).ang)),
									y
											- 125
											- ((weapons.get(0).shoulderRad - 30) * Math
													.sin(-weapons.get(0).shoulderAng
															+ weapons.get(0).ang)));
			else
			{
				if (facingRight)
				{
					weapons.get(0).ang = -Math.PI * 1 / 8;
					weapons.get(0)
							.translateTo(
									x
											- ((weapons.get(0).shoulderRad - 30) * Math.cos(weapons
													.get(0).shoulderAng
													+ weapons.get(0).ang)),
									y
											- 125
											- ((weapons.get(0).shoulderRad - 30) * Math
													.sin(weapons.get(0).shoulderAng
															+ weapons.get(0).ang)));
				}
				else
				{
					weapons.get(0).ang = Math.PI * 9 / 8;
					weapons.get(0)
							.translateTo(
									x
											- ((weapons.get(0).shoulderRad - 30) * Math.cos(-weapons
													.get(0).shoulderAng
													+ weapons.get(0).ang)),
									y
											- 125
											- ((weapons.get(0).shoulderRad - 30) * Math
													.sin(-weapons.get(0).shoulderAng
															+ weapons.get(0).ang)));
				}
			}
		}
	}

	private void manageWeaponSwitching()
	{
		if (timeUntilWeaponSwitch > 0)
		{
			timeUntilWeaponSwitch -= (1 / fps);
			if (focus)
				timeUntilWeaponSwitch -= (.6 / fps);
		}
		if (nextWeaponRequested)
		{
			if (timeUntilWeaponSwitch <= 0)
			{
				nextWeapon();
				nextWeaponRequested = false;
				lastWeaponRequested = false;
			}
		}
		else if (lastWeaponRequested)
		{
			if (timeUntilWeaponSwitch <= 0)
			{
				prevWeapon();
				nextWeaponRequested = false;
				lastWeaponRequested = false;
			}
		}
	}

	private void manageMovement()
	{
		airborne = true;
		if (grounded)
		{
			airborne = false;
			timeOnGround += (1 / fps);
			if (jumpRequested
					&& (timeOnGround > .3 || (grabbing && timeOnGround > .1)))
			{
				ySpeed = (-jumpSpeed);
			}
			if (pickUpEntityRequested && this.timeUntilWeaponSwitch <= 0
					&& !weapons.get(0).reloading)
			{
				pickUpEntity(groundEntity);
				pickUpEntityRequested = false;
			}
			if (moveR && !moveL && !focus)
			{
				if (!sprinting && facingRight)
					moveR(weightedWalkSpeed);
				else if (!facingRight)
					moveR(weightedWalkSpeed / 1.4);
				else if (sprinting && facingRight)
					moveR((weightedWalkSpeed * sprintMultiplier));
			}
			else if (moveL && !moveR && !focus)
			{
				if (!sprinting && !facingRight)
					moveL(weightedWalkSpeed);
				else if (facingRight)
					moveL(weightedWalkSpeed / 1.4);
				else if (sprinting && !facingRight)
					moveL((weightedWalkSpeed * sprintMultiplier));
			}
			if (focus && weapons.size() > 0)
				weapons.get(0).focus();
		}
		else
		{
			timeOnGround = 0;

			if (weapons.size() > 0)
				weapons.get(0).unfocus();
			if (System.nanoTime() - lastGrab > 1E9)// in air
			{
				if (moveR && !moveL && !focus)
					moveR(weightedWalkSpeed / 1.2);
				else if (moveL && !moveR && !focus)
					moveL(weightedWalkSpeed / 1.2);
			}
			else
			// pull up
			{
				if (moveR && !moveL && !focus)
					moveR(weightedWalkSpeed);
				else if (moveL && !moveR && !focus)
					moveL(weightedWalkSpeed);
			}
		}
		if (!focus && weapons.size() > 0)
			weapons.get(0).unfocus();
		jumpRequested = false;
		moveR = false;
		moveL = false;
		grabBlocked = false;
		grabbing = false;
		grounded = false;
		feetPlanted = false;
		groundEntity = null;
		sprinting = false;
		melee = false;
	}

	public void collide(Entity e)
	{
		if (e.intersectPlayers && !e.ghost && !e.intersectStaticOnly
				&& !(e instanceof Weapon) && !(e instanceof Explosion)
				&& !(e instanceof Bullet && ((Bullet) e).owner == ID))
		{
			if (wireframes[0].collide(e) || feet.collide(e) || grab.collide(e))
			{
				entsHit.add(e);
			}
			if (grabExclude.collide(e))
				grabBlocked = true;
		}
		else if (e instanceof Weapon)
		{
			if (interactArea.collide(e)
					&& (groundEntity == null || Math.hypot(x - e.x, y - e.y) < Math
							.hypot(x - groundEntity.x, y - groundEntity.y)))
				groundEntity = e;
		}

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Step/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Inherited\/
	public String toString()
	{
		String s = "";
		s += name;
		return s;
	}

	public Dude getNew()
	{
		Dude d = new Dude();
		d.construct();
		d.name = this.name;

		d.headR = headR;
		d.headL = headL;
		d.torsoR = torsoR;
		d.torsoL = torsoL;
		d.upperArmR = upperArmR;
		d.upperArmL = upperArmL;
		d.lowerArmL = lowerArmL;
		d.elbowR = elbowR;
		d.elbowL = elbowL;
		d.handTriggerR = handTriggerR;
		d.handTriggerL = handTriggerL;
		d.handHoldR = handHoldR;
		d.handHoldL = handHoldL;
		d.handTriggerR = handTriggerR;
		d.handTriggerL = handTriggerL;
		d.legsStillR = legsStillR;
		d.legsStillL = legsStillL;
		d.legsRunningR = legsRunningR;
		d.legsRunningL = legsRunningL;
		d.legsAirR = this.legsAirR;
		d.legsAirL = this.legsAirL;

		d.head = this.head;
		d.headOffset = new Vect(headOffset.x, headOffset.y);
		d.feet = this.feet.getNew();
		d.feetOffset = new Vect(this.feetOffset.x, this.feetOffset.y);
		d.feetCheck = this.feetCheck.getNew();
		d.feetCheckOffset = this.feetCheckOffset;
		d.interactArea = this.interactArea.getNew();
		d.interactAreaOffset = new Vect(this.interactAreaOffset.x,
				this.interactAreaOffset.y);
		d.grab = this.grab.getNew();
		d.grabOffset = this.grabOffset;
		d.grabExclude = this.grabExclude.getNew();
		d.grabExcludeOffset = this.grabExcludeOffset;

		d.rightArmColor = rightArmColor;
		d.leftArmColor = leftArmColor;

		d.gunLimit = gunLimit;
		for (int i = 0; i < weapons.size(); i++)
		{
			d.pickUpWeapon(weapons.get(i).copy());
		}
		d.health = 0 + this.fullHealth;
		d.fullHealth = 0 + this.fullHealth;
		d.healVal = this.healVal;
		d.healthRegenTime = this.healthRegenTime;
		Wireframe[] wf = new Wireframe[numWF];
		Vect[] wfo = new Vect[numWF];
		for (int i = 0; i < numWF; i++)
		{
			wf[i] = wireframes[i].getNew();
			wfo[i] = new Vect(0 + wfOffset[i].x, 0 + wfOffset[i].y);
		}
		d.slowMotionActive = false;
		d.slowTimeLeft = 0 + slowTimeLeftFull;
		d.slowTimeLeftFull = 0 + slowTimeLeftFull;
		d.timeUntilSlowMotionRechargeFull = 0 + this.timeUntilSlowMotionRechargeFull;
		d.walkSpeed = this.walkSpeed;
		d.sprintMultiplier = this.sprintMultiplier;
		d.jumpSpeed = this.jumpSpeed;
		d.wireframes = wf;
		d.slowMotion = this.slowMotion;
		d.wfOffset = wfo;
		d.damageThreshold = this.damageThreshold;
		d.img = img;
		d.imgName = imgName;
		d.description = this.description;
		d.hitParticles = this.hitParticles;
		d.deathParticles = this.deathParticles;
		d.particlesToAddOnDeath = this.particlesToAddOnDeath;
		return d;
	}

	public Entity copy()
	{
		return null;
	}

	public boolean removeMe()
	{
		if (isDead())
		{
			if (weapons.size() > 0)
				dropCurrentWeapon();
			addDeathParticles();
			return true;
		}
		return false;
	}

	public void loadArray(ArrayList<Entity> entz)
	{
		ents = entz;
		if (weapons != null && weapons.size() > 0)
			for (int i = 0; i < weapons.size(); i++)
				weapons.get(i).loadArray(entz);

	}

	public void hold(Entity e)
	{
		if (!grabbing)
			xSpeed = 0;
		grabbing = true;
		feetPlanted = true;
		xSpeed -= (xSpeed * .8) / fps;
		CollisionInfo colInfo = grab.collideInfo(e.wireframes[0]);
		colInfo.mtd.x = 0;
		colInfo.mtd.y = 0;
		for (int j = 0; j < e.numWF; j++)
		{
			if (grab.collide(e.wireframes[j]))
				colInfo = grab.collideInfo(e.wireframes[j]);
		}
		y += colInfo.mtd.y;
		updateWFs();
	}

	public boolean intersects(Entity e)
	{
		boolean r = false;
		try
		{
			if (e instanceof Bullet || e instanceof Explosion
					|| e instanceof FracLine)
			{
				r = e.intersects(this);
				if (e instanceof Explosion && e.intersects(this))
					e.collide(this);
			}
			else if (e instanceof AdhesiveEntity && e.rect2.intersects(rect2))
			{
				e.collide(this);
			}
			else if (!e.ghost
					&& !e.intersectStaticOnly
					&& e.intersectPlayers
					&& ((e.rect != null && rect2.intersects(e.rect)) || (e.rect2 != null && rect2
							.intersects(e.rect2))))
			{
				for (int j = 0; j < e.numWF; j++)
				{
					if (wireframes[0].collide(e.wireframes[j])
							|| head.collide(e.wireframes[j]))
						r = true;
				}
				if (e instanceof Weapon)// or pickup
				{
					for (int j = 0; j < e.numWF; j++)
					{
						if (interactArea.collide(e.wireframes[j]))
							r = true;
					}
				}
			}
			else if (!e.ghost && !e.intersectStaticOnly && feet.collide(e))
			{
				r = true;
			}
			if (ySpeed > 0 && e instanceof StaticEntity && grab.collide(e))
			{
				r = true;
			}
			if (r && e instanceof StaticEntity)
				exitOverlap(e);
			if (((facingRight && e.x > x) || !facingRight && e.x < x)
					&& interactArea.collide(e))
			{
				meleeHits.add(e);
			}
		}
		catch (Exception exce)
		{}
		return r;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Inherited/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Weapons\/
	public void shoot()
	{
		if (weapons != null && weapons.size() > 0 && weapons.get(0) != null
				&& timeUntilWeaponSwitch <= 0)
			if (weapons.get(0).shoot()
					&& !(weapons.get(0).bullet instanceof FracLine))
			{
				if (!(weapons.get(0) instanceof Shotgun))
				{
					if (weapons.get(0).bullet instanceof Bullet)
					{
						xSpeed = ((xSpeed * mass) - (weapons.get(0).bullet.mass
								* ((Bullet) weapons.get(0).bullet).speed * Math
									.cos(weapons.get(0).ang))) / mass;
						ySpeed = ((ySpeed * mass) - (weapons.get(0).bullet.mass
								* ((Bullet) weapons.get(0).bullet).speed * Math
									.sin(weapons.get(0).ang))) / mass;
					}
					else if (weapons.get(0).bullet instanceof Explosive)
					{
						xSpeed = ((xSpeed * mass) - (weapons.get(0).bullet.mass
								* ((Explosive) weapons.get(0).bullet).speed * Math
									.cos(weapons.get(0).ang))) / mass;
						ySpeed = ((ySpeed * mass) - (weapons.get(0).bullet.mass
								* ((Explosive) weapons.get(0).bullet).speed * Math
									.sin(weapons.get(0).ang))) / mass;
					}
					else if (weapons.get(0).bullet instanceof AdhesiveEntity)
					{
						xSpeed = ((xSpeed * mass) - (weapons.get(0).bullet.mass
								* ((AdhesiveEntity) weapons.get(0).bullet).speed * Math
									.cos(weapons.get(0).ang))) / mass;
						ySpeed = ((ySpeed * mass) - (weapons.get(0).bullet.mass
								* ((AdhesiveEntity) weapons.get(0).bullet).speed * Math
									.sin(weapons.get(0).ang))) / mass;
					}
				}
				else if (weapons.get(0) instanceof Shotgun)
				{
					for (int i = 0; i < ((Shotgun) (weapons.get(0))).frags; i++)
					{
						if (weapons.get(0).bullet instanceof Bullet)
						{
							xSpeed = ((xSpeed * mass) - (weapons.get(0).bullet.mass
									* ((Bullet) weapons.get(0).bullet).speed * Math
										.cos(weapons.get(0).ang))) / mass;
							ySpeed = ((ySpeed * mass) - (weapons.get(0).bullet.mass
									* ((Bullet) weapons.get(0).bullet).speed * Math
										.sin(weapons.get(0).ang))) / mass;
						}
						else if (weapons.get(0).bullet instanceof Explosive)
						{
							xSpeed = ((xSpeed * mass) - (weapons.get(0).bullet.mass
									* ((Explosive) weapons.get(0).bullet).speed * Math
										.cos(weapons.get(0).ang))) / mass;
							ySpeed = ((ySpeed * mass) - (weapons.get(0).bullet.mass
									* ((Explosive) weapons.get(0).bullet).speed * Math
										.sin(weapons.get(0).ang))) / mass;
						}
						else if (weapons.get(0).bullet instanceof AdhesiveEntity)
						{
							xSpeed = ((xSpeed * mass) - (weapons.get(0).bullet.mass
									* ((AdhesiveEntity) weapons.get(0).bullet).speed * Math
										.cos(weapons.get(0).ang))) / mass;
							ySpeed = ((ySpeed * mass) - (weapons.get(0).bullet.mass
									* ((AdhesiveEntity) weapons.get(0).bullet).speed * Math
										.sin(weapons.get(0).ang))) / mass;
						}
					}
				}
			}
	}

	public void melee()
	{
		if (this.timeTillNextMelee < 0 && !weapons.get(0).reloading)
			melee = true;
	}

	public void focus()
	{
		if (weapons.size() > 0)
			focus = true;
	}

	public void unfocus()
	{
		focus = false;
	}

	public void reload()
	{
		if (weapons != null && weapons.size() > 0 && weapons.get(0) != null
				&& timeUntilWeaponSwitch <= 0)
			weapons.get(0).reload();
	}

	public void release()
	{
		if (weapons != null && weapons.size() > 0 && weapons.get(0) != null)
			weapons.get(0).release();
	}

	public void nextWeaponRequest()
	{
		if (!nextWeaponRequested && weapons.size() > 1
				&& !weapons.get(0).reloading)
		{
			nextWeaponRequested = true;
			lastWeaponRequested = false;
			timeUntilWeaponSwitch = weapons.get(1).mass / 15;
			switchTime = timeUntilWeaponSwitch;
		}
	}

	private void nextWeapon()
	{
		if (weapons.size() > 1)
		{
			Weapon temp = weapons.get(0);
			double tempAng = temp.ang;
			weapons.remove(0);
			weapons.add(temp);
			weapons.get(0).ang = tempAng;
			weapons.get(0).rotate(0);
			advanceWeapon();
		}
	}

	public void prevWeaponRequest()
	{
		if (!lastWeaponRequested && weapons.size() > 1
				&& !weapons.get(0).reloading)
		{
			lastWeaponRequested = true;
			nextWeaponRequested = false;
			timeUntilWeaponSwitch = weapons.get(weapons.size() - 1).mass / 15;
			switchTime = timeUntilWeaponSwitch;
		}
	}

	private void prevWeapon()
	{
		if (weapons.size() > 1)
		{
			Weapon temp = weapons.get(weapons.size() - 1);
			double tempAng = weapons.get(0).ang;
			weapons.remove(weapons.size() - 1);
			weapons.add(0, temp);
			weapons.get(0).ang = tempAng;
			weapons.get(0).rotate(0);
			advanceWeapon();
		}
	}

	public void pickUpEntity(Entity e)
	{
		if (e != null)
		{
			if (e instanceof Weapon)
				pickUpWeapon((Weapon) e);
			else if (e instanceof Item)
				pickUpItem((Item) e);
			else if (e instanceof UsableEntity)
				use((UsableEntity) e);
		}
	}

	public void use(UsableEntity e)
	{
		e.useBy(this);
	}

	public void pickUpItem(Item i)
	{

	}

	public void pickUpWeapon(Weapon e)
	{
		if (e != null && weaponIndex(e) == -1
				&& (weapons.size() == 0 || (!weapons.get(0).reloading)))
		{
			timeUntilWeaponSwitch = (e.mass / 30);

			if (weapons.size() < gunLimit)
			{
				weapons.add(0, e);
				syncWeps();
			}
			else if (ents != null)
			{
				dropCurrentWeapon();
				weapons.add(0, e);
				syncWeps();
			}
			if (ents != null)
			{
				ents.remove(e);
			}
		}
		else if (weaponIndex(e) != -1)
		{
			takeAmmo(e);
		}
	}

	public void dropCurrentWeapon()
	{
		if (weapons.size() > 1 && !weapons.get(0).reloading)
		{
			weapons.get(0).ownerID = -1;
			weapons.get(0).ID = -1;
			weapons.get(0).loadArray(ents);
			weapons.get(0).unlimitedAmmo = false;
			weapons.get(0).unlimitedClip = false;
			weapons.get(0).intersectPlayers = false;
			weapons.get(0).xSpeed = xSpeed;
			weapons.get(0).ySpeed = ySpeed;
			weapons.get(0).rotate(-weapons.get(0).ang);
			weapons.get(0).translateTo(x, y - 100);
			ents.add(weapons.get(0));
			weapons.remove(0);
		}
	}

	public int weaponIndex(Weapon w)
	{
		if (w != null)
			for (int i = 0; i < weapons.size(); i++)
			{
				if (weapons.get(i) != null
						&& w.name.equals(weapons.get(i).name))
					return i;
			}
		return -1;
	}

	private void takeAmmo(Weapon w)
	{
		weapons.get(weaponIndex(w)).extraAmmo += w.extraAmmo + w.currentClip;
		w.extraAmmo = 0;
		w.currentClip = 0;
	}

	public void pickUpgroundEntity()
	{
		pickUpEntityRequested = true;
	}

	public void syncWeps()
	{
		for (int i = 0; i < weapons.size(); i++)
		{
			weapons.get(i).ownerID = 0 + ID;
		}
	}

	public double getTargetTimeScale()
	{
		if (slowMotionActive)
			return slowMotion;
		else
			return 1;
	}

	public void toggleTimeScale()
	{
		if (slowTimeLeft > 0)
			slowMotionActive = !slowMotionActive;
		if (!slowMotionActive)
			timeUntilSlowMotionRecharge = 0 + timeUntilSlowMotionRechargeFull
					* ((slowTimeLeftFull - slowTimeLeft) / slowTimeLeftFull);
	}

	public void toggleLaser()
	{
		if (weapons != null && weapons.size() > 0 && weapons.get(0) != null)
		{
			weapons.get(0).laserPointer = true;
			weapons.get(0).switchLAM();
		}
	}

	public void toggleFireMode()
	{
		if (weapons != null && weapons.size() > 0 && weapons.get(0) != null)
			weapons.get(0).switchFireMode();
	}

	public void setID(int newID)
	{
		ID = newID;
		for (int i = 0; i < weapons.size(); i++)
		{
			Weapon e = weapons.get(i);
			e.ownerID = 0 + this.ID;
			// e.fps = 0 + this.fps;
			// e.myTime = 0 + this.myTime;
			e.unlimitedAmmo = this.unlimitedAmmo;
			e.unlimitedClip = this.unlimitedClip;
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Weapons/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Movement\/
	public void fall()
	{
		grabBlocked = true;
	}

	public void translateTo(double inx, double iny)
	{
		x = inx;
		y = iny;
		wireframes[0].translateTo(inx + wfOffset[0].x, iny + wfOffset[0].y);
		head.translateTo(inx + headOffset.x, iny + headOffset.y);
		feet.translateTo(inx + feetOffset.x, iny + feetOffset.y);
		feetCheck.translateTo(inx + feetCheckOffset.x, iny + feetCheckOffset.y);
		interactArea.translateTo(inx + interactAreaOffset.x, iny
				+ interactAreaOffset.y);
		grab.translateTo(inx + grabOffset.x, iny + grabOffset.y);
		grabExclude.translateTo(inx + grabExcludeOffset.x, iny
				+ grabExcludeOffset.y);
	}

	public void translate(double inx, double iny)
	{
		x += inx;
		y += iny;
		wireframes[0].translate(inx + wfOffset[0].x, iny + wfOffset[0].y);
		head.translate(inx + headOffset.x, iny + headOffset.y);
		feet.translate(inx + feetOffset.x, iny + feetOffset.y);
		feetCheck.translateTo(inx + feetCheckOffset.x, iny + feetCheckOffset.y);
		interactArea.translate(inx + interactAreaOffset.x, iny
				+ interactAreaOffset.y);
		grab.translate(inx + grabOffset.x, iny + grabOffset.y);
		grabExclude.translate(inx + grabExcludeOffset.x, iny
				+ grabExcludeOffset.y);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Movement/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Cheats\/
	public void invulnerable()
	{
		invulnerable = !invulnerable;
		if (invulnerable)
			health = 0 + fullHealth;
	}

	public void damage(double dam)
	{
		if (!invulnerable)
			super.damage(dam);
	}

	public void unlimitedAmmo()
	{
		unlimitedAmmo = !unlimitedAmmo;
		for (int i = 0; i < weapons.size(); i++)
		{
			weapons.get(i).unlimitedAmmo = this.unlimitedAmmo;
		}
	}

	public void unlimitedClip()
	{
		unlimitedClip = !unlimitedClip;
		for (int i = 0; i < weapons.size(); i++)
		{
			weapons.get(i).unlimitedClip = this.unlimitedClip;
		}
	}

	public void unlimitedSlowMotion()
	{
		unlimitedSlowMotion = !unlimitedSlowMotion;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Cheats/\
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Misc\/
	public void addDeathParticles()
	{
		ArrayList<Entity> newEnts = new ArrayList<Entity>();
		double radius = Math.sqrt((rect.getWidth() * rect.getWidth() + rect
				.getHeight() * rect.getHeight()) / 4);

		if (hitParticles != null && hitParticles.size() > 0)
		{
			for (int i = 0; i < particlesToAddOnDeath; i++)
			{
				double angle = 2 * Math.PI * i
						/ ((double) (particlesToAddOnDeath));
				PhysicsEntity p = (PhysicsEntity) hitParticles.get(
						(int) (Math.random() * hitParticles.size())).getNew();
				p.translateTo(x + Math.cos(angle) * radius * Math.random(), y
						+ Math.sin(angle) * radius * Math.random());
				boolean prevIntersect = p.intersectStaticOnly;
				p.intersectStaticOnly = false;
				while (!p.intersects(this))
					p.translateTo(x + Math.cos(Math.random() * 2 * Math.PI)
							* radius * Math.random(),
							y + Math.sin(Math.random() * 2 * Math.PI) * radius
									* Math.random());
				p.intersectStaticOnly = prevIntersect;
				p.rotate(Math.random() * Math.PI);
				p.xSpeed = 0 + xSpeed;
				p.ySpeed = 0 + ySpeed;
				if (p.intersectStaticOnly && !p.ghost)
				{
					if (p.mass < 5)
						p.rotSpeed = (Math.random() * 140) - 70;
					else
						p.rotSpeed = (Math.random() * 70) - 35;
					p.xSpeed += 400 * Math.cos(Math.random() * Math.PI);
					p.ySpeed += 400 * Math.sin(Math.random() * Math.PI);
				}
				p.xSpeed -= (p.xSpeed * (p.speedDampening / 3));
				p.ySpeed -= (p.ySpeed * (p.speedDampening / 3));

				p.timeRemaining = ((Math.random() * .5) + .5) * p.timeRemaining;
				newEnts.add(p);
			}
		}
		if (deathParticles != null && deathParticles.size() >= 0)
		{
			for (int i = 0; i < deathParticles.size(); i++)
			{
				double angle = 2 * Math.PI * i
						/ ((double) (particlesToAddOnDeath));
				PhysicsEntity p = (PhysicsEntity) deathParticles.get(i)
						.getNew();
				p.translateTo(x + Math.cos(angle) * radius * Math.random(), y
						+ Math.sin(angle) * radius * Math.random());
				boolean prevIntersect = p.intersectStaticOnly;
				p.intersectStaticOnly = false;
				while (!p.intersects(this))
					p.translateTo(x + Math.cos(Math.random() * 2 * Math.PI)
							* radius * Math.random(),
							y + Math.sin(Math.random() * 2 * Math.PI) * radius
									* Math.random());
				p.intersectStaticOnly = prevIntersect;
				p.rotate(Math.random() * Math.PI);
				p.xSpeed = 0 + xSpeed;
				p.ySpeed = 0 + ySpeed;
				p.xSpeed -= (p.xSpeed * p.speedDampening);
				p.ySpeed -= (p.ySpeed * p.speedDampening);

				if (p.intersectStaticOnly && !p.ghost)
				{
					p.rotSpeed = (Math.random() * 20) - 10;
					p.xSpeed += 400 * Math.cos(Math.random() * Math.PI);
					p.ySpeed += 400 * Math.sin(Math.random() * Math.PI);
				}
				p.timeRemaining = ((Math.random() * .5) + .5) * p.timeRemaining;
				newEnts.add(p.copy());
			}
		}
		ents.addAll(newEnts);
	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////Misc/\
}
