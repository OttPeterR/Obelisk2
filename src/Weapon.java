import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Weapon extends PhysicsEntity
{
	final static String			TYPE_ASSAULT_RIFLE	= "Assault_Rifle";
	final static String			TYPE_HAND_GUN		= "Hand_Gun";
	final static String			TYPE_SMG			= "SMG";
	final static String			TYPE_LMG			= "LMG";
	final static String			TYPE_SHOTGUN		= "Shotgun";
	final static String			TYPE_SNIPER_RIFLE	= "Sniper_Rifle";
	final static String			TYPE_ENERGY			= "Energy";
	final static String			TYPE_EXPLOSIVE		= "Explosive";

	protected String			type;
	protected boolean			automatic			= true, currentlyAutomatic, released = true, useClip = true, focus = false, dryReloadPlayed = false;
	protected int				fullClip, currentClip, extraAmmo, numExtraClips;
	protected double			accuracy, origAccuracy, accuracyOff, minAccuracy = Math.toRadians(45);
	protected double			aimControl			= Math.toRadians(15), origAimControl = Math.toRadians(15);;
	protected Entity			bullet, shell, clip, shellSmoke = ParticleLibrary.SmokeSmall1();
	protected Vect				muzzleOffset, muzzleOffsetFlip, shellOffset, shellOffsetFlip, clipOffset, clipOffsetFlip, trigger, hold, shoulder;
	protected double			muzzleRad, shellRad, clipRad, triggerRad, holdRad, shoulderRad;
	protected double			muzzleAng, shellAng, clipAng, triggerAng, holdAng, shoulderAng;
	protected double			rof;
	protected boolean			reloading, canShoot, facingRight, canDropClip = true, plusOne = true;
	protected double			reloadTime, reloadTimeLeft, nextShotTime;
	protected ArrayList<Entity>	muzzleFlashes		= new ArrayList<Entity>();
	protected String			shotSound, boltSound, clipSound, reloadSound, dryReload, dryFire = "DryFire.wav";
	protected int				ownerID				= -1;
	protected boolean			laserPointer		= true, laserPointerOn = false;
	protected boolean			unlimitedAmmo		= false, unlimitedClip = false;
	protected Color				laserColor			= PROColor.brighter();
	public int					price;

	public Weapon(String n, String typ, Image[] im, String[] ims, Wireframe wf, Vect wfOff, int maxClip, int extraClips, double mss, Entity b, Vect muzzleOff, Entity shll, Vect shellOff, Entity clp, Vect clipOff, Vect trg, Vect hld, Vect shld, double rate, double reloadt, double acc, double accOff)
	{
		super(n, im, ims, wf, wfOff, mss, .5, .1);
		intersectPlayers = false;
		type = typ;
		fullClip = maxClip;
		currentClip = 0 + maxClip;
		numExtraClips = extraClips;
		extraAmmo = fullClip * numExtraClips;
		accuracy = acc;
		accuracyOff = accOff;
		origAccuracy = 0 + accuracy;

		bullet = b;
		clip = clp;
		shell = shll;

		muzzleOffset = muzzleOff;
		muzzleOffsetFlip = new Vect(-muzzleOff.x, muzzleOffset.y);
		muzzleRad = muzzleOffset.velocity();
		muzzleAng = Math.atan(muzzleOffset.y / muzzleOffset.x);

		hold = hld;
		holdRad = hold.velocity();
		holdAng = Math.atan2(hold.y, hold.x);

		trigger = trg;
		triggerRad = trigger.velocity();
		triggerAng = Math.atan2(trigger.y, trigger.x);

		shoulder = shld;
		shoulderRad = shoulder.velocity();
		shoulderAng = Math.atan2(shoulder.y, shoulder.x);

		clipOffset = clipOff;
		clipOffsetFlip = new Vect(clipOff.x, clipOff.y);
		clipRad = clipOffset.velocity();
		clipAng = Math.atan2(clipOffset.y, clipOffset.x);

		if (shell != null)
		{
			shellOffset = shellOff;
			shellOffsetFlip = new Vect(shellOff.x, shellOff.y);
			shellRad = shellOffset.velocity();
			shellAng = Math.atan2(shellOffset.y, shellOffset.x);
		}
		rof = rate;
		canShoot = true;
		reloading = false;
		reloadTime = reloadt;

		new ParticleLibrary();
		muzzleFlashes.add(ParticleLibrary.MuzzleFlash1());
		new ParticleLibrary();
		muzzleFlashes.add(ParticleLibrary.MuzzleFlash2());
		new ParticleLibrary();
		muzzleFlashes.add(ParticleLibrary.MuzzleFlash3());
		new ParticleLibrary();
		muzzleFlashes.add(ParticleLibrary.MuzzleFlash4());

		new ParticleLibrary();
		hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		new ParticleLibrary();
		hitParticles.add(ParticleLibrary.SparkYellow2(.5));

		rotateToward(x + 1, y);
		translateTo(x, y);
		currentlyAutomatic = true;
		wireframeColor = Color.red;
	}
	public Entity getNew()
	{
		double curAng = 0 + this.ang;
		rotate(-ang);
		Weapon w;
		if (clip != null)
			w = new Weapon(name, type, img, imgName, wireframes[0].getNew(), wfOffset[0], fullClip, numExtraClips, mass, bullet, muzzleOffset, shell, shellOffset, clip.getNew(), clipOffset, trigger, hold, shoulder, rof, reloadTime, origAccuracy, accuracyOff);
		else
			w = new Weapon(name, type, img, imgName, wireframes[0].getNew(), wfOffset[0], fullClip, numExtraClips, mass, bullet, muzzleOffset, shell, shellOffset, null, clipOffset, trigger, hold, shoulder, rof, reloadTime, origAccuracy, accuracyOff);
		rotate(curAng);
		w.useClip = this.useClip;
		w.automatic = this.automatic;
		w.currentlyAutomatic = this.currentlyAutomatic;
		w.updateClip();
		w.wireframeColor = Color.red;
		w.plusOne = this.plusOne;
		w.loadArray(ents);
		w.muzzleFlashes = this.muzzleFlashes;
		w.reloadSound = this.reloadSound;
		w.shotSound = this.shotSound;
		w.boltSound = this.boltSound;
		w.reloadSound = this.reloadSound;
		w.price = price;
		w.dryReload = this.dryReload;
		w.dryFire = this.dryFire;
		w.laserColor = this.laserColor;
		w.minAccuracy = this.minAccuracy;
		w.aimControl = this.aimControl;
		w.hold = new Vect(hold.x, hold.y);
		w.trigger = new Vect(trigger.x, trigger.y);
		w.shoulder = new Vect(shoulder.x, shoulder.y);
		w.gravity = 0 + gravity;
		return w;
	}
	public Weapon copy()
	{
		Weapon r = (Weapon) this.getNew();

		r.health = 0 + health;
		r.timeRemaining = 0 + timeRemaining;
		r.paused = paused;

		r.xSpeed = 0 + xSpeed;
		r.ySpeed = 0 + ySpeed;

		r.rotSpeed = 0 + rotSpeed;
		r.alpha = alpha;

		r.x = 0 + x;
		r.y = 0 + y;
		r.rotate(ang);

		r.canShoot = false;
		if (canShoot)
			r.canShoot = true;
		r.reloading = false;
		if (reloading)
			r.reloading = true;
		r.currentClip = 0 + currentClip;
		r.extraAmmo = 0 + extraAmmo;
		r.loadArray(ents);

		r.reloadTimeLeft = 0 + reloadTimeLeft;
		r.nextShotTime = 0 + nextShotTime;

		r.laserColor = this.laserColor;
		r.laserPointerOn = this.laserPointerOn;
		r.laserPointer = this.laserPointer;
		r.wireframeColor = this.wireframeColor;

		r.intersectPlayers = this.intersectPlayers;
		r.ownerID = this.ownerID;
		return r;
	}
	public String toString()
	{

		// String s =
		// "Weapon "
		// + super.toString()
		// + " "
		// + intersectPlayers
		// + noCollide
		// + " "
		// + hitParticles
		// + " "
		// + deathParticles
		// + " "
		// + particlesToAddOnDeath
		// + " "
		// + hitSounds
		// + " "
		// + type
		// + " "
		// + automatic
		// + " "
		// + currentlyAutomatic
		// + " "
		// + released
		// + " "
		// + useClip
		// + " "
		// + fullClip
		// + " "
		// + currentClip
		// + " "
		// + extraAmmo
		// + " "
		// + accuracy
		// + " "
		// + origAccuracy
		// + " "
		// + accuracyOff
		// + " "
		// + minAccuracy
		// + " "
		// + aimControl
		// + " "
		// + origAimControl
		// + " "
		// + bullet
		// + " "
		// + shell
		// + " "
		// + clip
		// + " "
		// + muzzleOffset
		// + " "
		// + muzzleOffsetFlip
		// + " "
		// + shellOffset
		// + " "
		// + shellOffsetFlip
		// + " "
		// + clipOffset
		// + " "
		// + clipOffsetFlip
		// + " "
		// + muzzleRad
		// + " "
		// + shellRad
		// + " "
		// + clipRad
		// + " "
		// + muzzleAng
		// + " "
		// + shellAng
		// + " "
		// + clipAng
		// + " "
		// + rof
		// + " "
		// + reloading
		// + " "
		// + canShoot
		// + " "
		// + facingRight
		// + " "
		// + canDropClip
		// + " "
		// + plusOne
		// + " "
		// + reloadTime
		// + " "
		// + shellSmoke
		// + " "
		// + muzzleFlashes
		// + " "
		// + shotSound
		// + " "
		// + boltSound
		// + " "
		// + clipSound
		// + " "
		// + reloadSound
		// + " "
		// + dryReload
		// + " "
		// + ownerID
		// + " "
		// + laserPointer
		// + " "
		// + laserPointerOn
		// + " "
		// + unlimitedAmmo
		// + " "
		// + unlimitedClip;
		//
		// return s;
		return "Weapon " + name + " ";

	}
	public void computeRect()
	{
		double maxX = wireframes[0].coords[0].x;
		double minX = wireframes[0].coords[0].x;
		double minY = wireframes[0].coords[0].y;
		double maxY = wireframes[0].coords[0].y;

		for (int i = 0; i < wireframes[0].numCoords; i++)
		{
			maxX = Math.max(maxX, wireframes[0].coords[i].x);
			minX = Math.min(minX, wireframes[0].coords[i].x);

			maxY = Math.max(maxY, wireframes[0].coords[i].y);
			minY = Math.min(minY, wireframes[0].coords[i].y);
		}
		if (clip != null)
		{
			for (int i = 0; i < clip.wireframes[0].numCoords; i++)
			{
				maxX = Math.max(maxX, clip.wireframes[0].coords[i].x);
				minX = Math.min(minX, clip.wireframes[0].coords[i].x);

				maxY = Math.max(maxY, clip.wireframes[0].coords[i].y);
				minY = Math.min(minY, clip.wireframes[0].coords[i].y);
			}
		}
		if (fps < 1)
		{
			rect = new java.awt.geom.Rectangle2D.Double(minX, minY, (maxX - minX), (maxY - minY));
			rect2 = (Rectangle2D) rect.clone();
		}
		rect = new java.awt.geom.Rectangle2D.Double(minX, minY, (maxX - minX), (maxY - minY));
		if (xSpeed > 0 && ySpeed > 0)
			rect2 = new java.awt.geom.Rectangle2D.Double(rect.getX(), rect.getY(), rect.getWidth() + xSpeed / fps, rect.getHeight() + ySpeed / fps);
		else if (xSpeed > 0 && ySpeed <= 0)
			rect2 = new java.awt.geom.Rectangle2D.Double(rect.getX(), rect.getY() + ySpeed / fps, rect.getWidth() + xSpeed / fps, rect.getHeight() - ySpeed / fps);
		else if (xSpeed <= 0 && ySpeed > 0)
			rect2 = new java.awt.geom.Rectangle2D.Double(rect.getX() + xSpeed / fps, rect.getY(), rect.getWidth() - xSpeed / fps, rect.getHeight() + ySpeed / fps);
		else if (xSpeed <= 0 && ySpeed <= 0)
			rect2 = new java.awt.geom.Rectangle2D.Double(rect.getX() + xSpeed / fps, rect.getY() + ySpeed / fps, rect.getWidth() - xSpeed / fps, rect.getHeight() - ySpeed / fps);
		else
			rect2 = new java.awt.geom.Rectangle2D.Double(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

	}
	public void translate(double inx, double iny)
	{
		super.translate(inx, iny);
		updateClip();
	}
	public void translateTo(double inx, double iny)
	{
		super.translateTo(inx, iny);
		updateClip();
	}
	public void updateClip()
	{
		if (clip != null)
		{
			clip.x = x + clipOffset.x;
			clip.y = y + clipOffset.y;
			clip.wireframes[0].translate(0, 0);

			clip.ang = ang;
			clip.rotate(0);
			// clip.wireframes[0].ang = ang;
			// clip.wireframes[0].rotate(clip.ang);
		}
	}
	public void render(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		renderClip(g, trans, origX, origY);

		g.setComposite(java.awt.AlphaComposite.getInstance(3, alpha));

		if (img[1] != null && !facingRight)
		{
			ang += Math.PI;
			trans.setToTranslation((x - origX) - img[1].getWidth(null) / 2, (y - origY) - img[1].getHeight(null) / 2);
			trans.rotate(ang, img[1].getWidth(null) / 2, img[1].getHeight(null) / 2);
			g.drawImage(img[1], trans, null);
			ang += Math.PI;
		}
		else if (img[0] != null && facingRight)
		{
			trans.setToTranslation((x - origX) - img[1].getWidth(null) / 2, (y - origY) - img[1].getHeight(null) / 2);
			trans.rotate(ang, img[0].getWidth(null) / 2, img[0].getHeight(null) / 2);
			g.drawImage(img[0], trans, null);
		}
		else
		{
			g.setColor(java.awt.Color.red);
			g.fillRect((int) (x - origX) - 40, (int) (y - origY) - 40, 80, 80);
			g.setColor(java.awt.Color.BLACK);
			g.drawString("Image", (int) ((x - origX) - 15), (int) ((y - origY) - 20));
			g.drawString("Error", (int) ((x - origX) - 15), (int) ((y - origY) - 7));
		}
	}
	public void renderHUD(java.awt.Graphics2D g, double origX, double origY, double mx, double my)
	{
		Font f = g.getFont();
		this.renderHUD(g, PROColor, origX, origY, mx, my);
		g.setFont(f);
	}
	public void renderHUD(java.awt.Graphics2D g, Color c, double origX, double origY, double mx, double my)
	{
		if (laserPointerOn)
			renderLaser(g, origX, origY);

		if (!reloading)
		{
			g.setStroke(new java.awt.BasicStroke(4));
			g.setColor(Color.black);

			g.drawOval((int) (mx + origX - 1), (int) (my + origY - 1), 2, 2);
			g.drawOval((int) (mx + origX - 10), (int) (my + origY - 10), 20, 20);

			double dist = accuracy * 180;
			if (this instanceof Shotgun)
				dist = Math.toDegrees(((Shotgun) this).spread) * 7;

			g.drawLine((int) (mx + origX), (int) (my + origY + 5 + dist), (int) (mx + origX), (int) (my + origY + 15 + dist));
			g.drawLine((int) (mx + origX), (int) (my + 1 + origY - 5 - dist), (int) (mx + origX), (int) (my + 1 + origY - 15 - dist));
			g.drawLine((int) (mx + origX + 5 + dist), (int) (my + origY), (int) (mx + origX + 15 + dist), (int) (my + origY));
			g.drawLine((int) (mx + origX - 5 - dist), (int) (my + 1 + origY), (int) (mx + origX - 15 - dist), (int) (my + 1 + origY));
			// //////////////////////black outline above
			g.setColor(c);
			g.setStroke(new java.awt.BasicStroke(2));
			g.drawOval((int) (mx + origX - 1), (int) (my + origY - 1), 2, 2);
			g.drawOval((int) (mx + origX - 10), (int) (my + origY - 10), 20, 20);

			g.drawLine((int) (mx + origX), (int) (my + origY + 5 + dist), (int) (mx + origX), (int) (my + origY + 15 + dist));
			g.drawLine((int) (mx + origX), (int) (my + 1 + origY - 5 - dist), (int) (mx + origX), (int) (my + 1 + origY - 15 - dist));
			g.drawLine((int) (mx + origX + 5 + dist), (int) (my + origY), (int) (mx + origX + 15 + dist), (int) (my + origY));
			g.drawLine((int) (mx + origX - 5 - dist), (int) (my + 1 + origY), (int) (mx + origX - 15 - dist), (int) (my + 1 + origY));
			g.setStroke(new java.awt.BasicStroke(1));
			String info = "";
			if (currentClip < (fullClip * .15) && extraAmmo > 0 && currentClip != -1)
			{
				g.setColor(Color.red);
				info = "Reload";
			}
			else if (currentClip == 0 && extraAmmo == 0)
			{
				g.setColor(java.awt.Color.RED);
				info = "Out of Ammo";
			}
			else if (currentClip == -1)
			{
				info = "Unlimited Ammo";
			}

			g.setColor(Color.black);
			g.drawString(currentClip + "    " + extraAmmo + "  " + info, (float) (mx + origX) + 24, (float) (my + origY) - 22);
			g.drawString(currentClip + "    " + extraAmmo + "  " + info, (float) (mx + origX) + 24, (float) (my + origY) - 24);
			g.drawString(currentClip + "    " + extraAmmo + "  " + info, (float) (mx + origX) + 26, (float) (my + origY) - 22);
			g.drawString(currentClip + "    " + extraAmmo + "  " + info, (float) (mx + origX) + 26, (float) (my + origY) - 24);

			g.setColor(c);
			g.drawString(currentClip + "    " + extraAmmo + "  " + info, (float) (mx + origX) + 25, (float) (my + origY) - 23);

		}
		if (reloading)
		{
			g.setColor(Color.black);
			g.drawString(currentClip + "", (float) (mx + origX) + 24, (float) (my + origY) - 22);
			g.drawString(currentClip + "", (float) (mx + origX) + 24, (float) (my + origY) - 24);
			g.drawString(currentClip + "", (float) (mx + origX) + 26, (float) (my + origY) - 22);
			g.drawString(currentClip + "", (float) (mx + origX) + 26, (float) (my + origY) - 24);

			g.setColor(c);
			g.drawString(currentClip + "", (float) (mx + origX) + 25, (float) (my + origY) - 23);

			int reloadAngle = (int) -(Math.PI * 116 * ((double) (reloadTime - reloadTimeLeft) / (double) reloadTime));
			if (plusOne && useClip && currentClip == 0)
				reloadAngle = (int) -(Math.PI * 116 * ((double) ((reloadTime * 1.25) - reloadTimeLeft) / (double) (reloadTime * 1.25)));
			if (useClip)
			{

				int ovX = (int) (mx + origX + 30 * Math.cos(-Math.toRadians(reloadAngle + 90)));
				int ovY = (int) (my + origY + 30 * Math.sin(-Math.toRadians(reloadAngle + 90)));

				g.setColor(Color.black);
				g.setStroke(new java.awt.BasicStroke(6));
				g.fillOval(ovX - 5, ovY - 5, 10, 10);
				g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60, 90, reloadAngle);

				g.setColor(java.awt.Color.BLUE);
				g.setStroke(new java.awt.BasicStroke(4));
				g.fillOval(ovX - 5, ovY - 5, 10, 10);
				g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60, 90, reloadAngle);
			}
			else
			{

				int ovX = (int) (mx + origX + 10 * Math.cos(-Math.toRadians(reloadAngle + 90)));
				int ovY = (int) (my + origY + 10 * Math.sin(-Math.toRadians(reloadAngle + 90)));

				g.setColor(Color.black);
				g.setStroke(new java.awt.BasicStroke(4));
				g.fillOval(ovX - 4, ovY - 4, 8, 8);
				g.drawArc((int) (mx + origX - 10), (int) (my + origY - 10), 20, 20, 90, reloadAngle);

				g.setColor(java.awt.Color.BLUE);
				g.setStroke(new java.awt.BasicStroke(2));
				g.fillOval(ovX - 4, ovY - 4, 8, 8);
				g.drawArc((int) (mx + origX - 10), (int) (my + origY - 10), 20, 20, 90, reloadAngle);

				reloadAngle = (int) (-(int) Math.toDegrees(Math.PI * 2 * (((double) (currentClip) / (double) fullClip))));
				ovX = (int) (mx + origX + 30 * Math.cos(-Math.toRadians(reloadAngle + 90)));
				ovY = (int) (my + origY + 30 * Math.sin(-Math.toRadians(reloadAngle + 90)));

				g.setColor(Color.black);
				g.setStroke(new java.awt.BasicStroke(6));
				g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60, 90, reloadAngle);

				g.setColor(Color.blue);
				g.setStroke(new java.awt.BasicStroke(4));
				g.drawArc((int) (mx + origX - 30), (int) (my + origY - 30), 60, 60, 90, reloadAngle);
			}
		}
		g.setStroke(new BasicStroke(1));

	}
	public void renderLaser(Graphics2D g, double origX, double origY)
	{
		int startX = (int) (x + muzzleOffset.x);
		int startY = (int) (y + muzzleOffset.y);
		int closestX = (int) (x + muzzleOffset.x + (2300 * Math.cos(ang)));
		int closestY = (int) (y + muzzleOffset.y + (2300 * Math.sin(ang)));
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(x + muzzleOffset.x, y + muzzleOffset.y);
		coords[1] = new Vect(x + 1E-9 + muzzleOffset.x, y + muzzleOffset.y);
		coords[2] = new Vect(closestX, closestY);
		Wireframe laserWF = new Wireframe(coords, 3);
		// Crazy a__ mutha grouping below!
		for (int i = 0; i < ents.size(); i++)
		{
			for (int j = 0; j < ents.get(i).numWF; j++)
			{
				if (ents.get(i) != null)
					if (!(ents.get(i) instanceof Bullet || (ents.get(i) instanceof AdhesiveEntity || ents.get(i) instanceof Biped || ents.get(i) instanceof Explosion) || ents.get(i).ghost || ents.get(i).intersectStaticOnly) && laserWF.collide(ents.get(i)))
					{

						ArrayList<Line2D.Double> myLines = laserWF.getLineWireFrame();
						ArrayList<Line2D.Double> notMyLines = ents.get(i).wireframes[j].getLineWireFrame();

						for (int my = 0; my < myLines.size(); my++)
						{
							for (int nMy = 0; nMy < notMyLines.size(); nMy++)
							{
								if (myLines.get(my).intersectsLine(notMyLines.get(nMy)))
								{

									double x1 = myLines.get(my).getX1();
									double y1 = myLines.get(my).getY1();
									double x2 = myLines.get(my).getX2();
									double y2 = myLines.get(my).getY2();

									double x3 = notMyLines.get(nMy).getX1();
									double y3 = notMyLines.get(nMy).getY1();
									double x4 = notMyLines.get(nMy).getX2();
									double y4 = notMyLines.get(nMy).getY2();

									double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
									if (d != 0)
									{
										int newX = (int) (((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d);
										int newY = (int) (((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d);
										if (Math.hypot(startX - newX, startY - newY) < Math.hypot(startX - closestX, startY - closestY))
										{
											closestX = newX;
											closestY = newY;
										}
									}

								}

							}
						}

					}
			}
		}
		startX -= origX;
		startY -= origY;
		closestX -= origX;
		closestY -= origY;

		g.setStroke(new BasicStroke(4));
		g.setColor(new Color(laserColor.getRed(), laserColor.getGreen(), laserColor.getBlue(), (int) ((Math.random() * .5 + .75) * 100)));
		g.drawLine(startX, startY, closestX, closestY);

		g.setStroke(new BasicStroke(1));
		g.setColor(new Color(laserColor.getRed(), laserColor.getGreen(), laserColor.getBlue(), (int) ((Math.random() * .5 + .75) * 200)));
		g.drawLine(startX, startY, closestX, closestY);

		{// laser pointer flare
			int flareLength = (int) (80 * (Math.random() * .10 + .90));
			int yflareLength = (int) (20 * (Math.random() * .15 + .85));
			int rad = 4;

			g.setColor(new Color(laserColor.getRed(), laserColor.getGreen(), laserColor.getBlue(), (int) ((Math.random() * .5 + .75) * 40 * (timeRemaining / fullTimeRemaining))));
			g.fillOval((int) (closestX - rad * 3), (int) (closestY - rad * 3), rad * 6, rad * 6);

			g.setColor(new Color(laserColor.getRed(), laserColor.getGreen(), laserColor.getBlue(), (int) ((Math.random() * .5 + .75) * 120 * (timeRemaining / fullTimeRemaining))));
			g.setStroke(new BasicStroke(5));
			g.drawLine((int) (closestX - flareLength / 4), (int) (closestY), (int) (closestX + flareLength / 4), (int) (closestY));
			g.drawLine((int) (closestX), (int) (closestY - yflareLength / 4), (int) (closestX), (int) (closestY + yflareLength / 4));
			g.setStroke(new BasicStroke(3));
			g.drawLine((int) (closestX - flareLength / 2), (int) (closestY), (int) (closestX + flareLength / 2), (int) (closestY));
			g.drawLine((int) (closestX), (int) (closestY - yflareLength / 2), (int) (closestX), (int) (closestY + yflareLength / 2));

			g.setColor(new Color(laserColor.getRed(), laserColor.getGreen(), laserColor.getBlue(), (int) ((Math.random() * .5 + .75) * 200 * (timeRemaining / fullTimeRemaining))));
			g.fillOval((int) (closestX - rad), (int) (closestY - rad), rad * 2, rad * 2);
			g.setStroke(new BasicStroke(1));
			g.drawLine((int) (closestX - flareLength), (int) (closestY), (int) (closestX + flareLength), (int) (closestY));
			g.drawLine((int) (closestX), (int) (closestY - yflareLength), (int) (closestX), (int) (closestY + yflareLength));

		}

	}
	public void renderClip(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		if (clip != null && canDropClip && !reloading && facingRight)
		{
			{
				if (clip.img[0] != null)
				{
					// clip.trans.setToTranslation(x - origX
					// + clipOffset.x -
					// clip.img[0].getWidth(null) / 2, y -
					// origY + clipOffset.y
					// - clip.img[0].getHeight(null) / 2);
					trans.setToTranslation(clip.x - origX - clip.img[0].getWidth(null) / 2, clip.y - origY - clip.img[0].getHeight(null) / 2);
					trans.rotate(clip.ang, clip.img[0].getWidth(null) / 2, clip.img[0].getHeight(null) / 2);

					g.drawImage(clip.img[0], trans, null);
				}
				else
				{
					g.setColor(java.awt.Color.red);
					g.fillRect((int) (x - origX) - 40, (int) (y - origY) - 40, 80, 80);
					g.setColor(java.awt.Color.BLACK);
					g.drawString("Image", (int) ((x - origX) - 15), (int) ((y - origY) - 20));
					g.drawString("Error", (int) ((x - origX) - 15), (int) ((y - origY) - 7));
				}
			}
		}
		else if (clip != null && canDropClip && !reloading && !facingRight)
		{
			{
				if (clip.img[1] != null)
				{
					trans.setToTranslation(x - origX + clipOffset.x - clip.img[1].getWidth(null) / 2, y - origY + clipOffset.y - clip.img[1].getHeight(null) / 2);
					trans.rotate(clip.ang + Math.PI, clip.img[1].getWidth(null) / 2, clip.img[1].getHeight(null) / 2);

					g.drawImage(clip.img[1], trans, null);
				}
				else
				{
					g.setColor(java.awt.Color.red);
					g.fillRect((int) (x - origX) - 40, (int) (y - origY) - 40, 80, 80);
					g.setColor(java.awt.Color.BLACK);
					g.drawString("Image", (int) ((x - origX) - 15), (int) ((y - origY) - 20));
					g.drawString("Error", (int) ((x - origX) - 15), (int) ((y - origY) - 7));
				}
			}
		}

	}
	public void renderWireframes(Graphics2D g, double origX, double origY)
	{
		computeRect();
		super.renderWireframes(g, origX, origY);
		if (clip != null && canDropClip && !reloading)
			clip.renderWireframes(g, origX, origY);
		g.setColor(Color.blue);
		g.fillRect((int) (x + hold.x - 5 - origX), (int) (y + hold.y - 5 - origY), 10, 10);
		g.setColor(Color.red);
		g.fillRect((int) (x + trigger.x - 5 - origX), (int) (y + trigger.y - 5 - origY), 10, 10);
		g.setColor(Color.green);
		g.fillRect((int) (x + shoulder.x - 5 - origX), (int) (y + shoulder.y - 5 - origY), 10, 10);

	}
	public void renderRect(Graphics2D g, double origX, double origY)
	{
		g.drawRect((int) (rect.getX() - origX), (int) (rect.getY() - origY), (int) (rect.getWidth()), (int) (rect.getHeight()));
	}
	public void rotate(double angle)
	{
		super.rotate(angle);
		if (facingRight)
		{
			hold.x = holdRad * Math.cos(ang + holdAng);
			hold.y = holdRad * Math.sin(ang + holdAng);
			trigger.x = triggerRad * Math.cos(ang + triggerAng);
			trigger.y = triggerRad * Math.sin(ang + triggerAng);
			muzzleOffset.x = muzzleRad * Math.cos(ang + muzzleAng);
			muzzleOffset.y = muzzleRad * Math.sin(ang + muzzleAng);
			shoulder.x = shoulderRad * Math.cos(ang + shoulderAng);
			shoulder.y = shoulderRad * Math.sin(ang + shoulderAng);
			clipOffset.x = clipRad * Math.cos(ang + clipAng);
			clipOffset.y = clipRad * Math.sin(ang + clipAng);
		}
		else
		{
			hold.x = holdRad * Math.cos(ang - holdAng);
			hold.y = holdRad * Math.sin(ang - holdAng);
			trigger.x = triggerRad * Math.cos(ang - triggerAng);
			trigger.y = triggerRad * Math.sin(ang - triggerAng);
			muzzleOffset.x = muzzleRad * Math.cos(ang - muzzleAng);
			muzzleOffset.y = muzzleRad * Math.sin(ang - muzzleAng);
			shoulder.x = shoulderRad * Math.cos(ang - shoulderAng);
			shoulder.y = shoulderRad * Math.sin(ang - shoulderAng);
			clipOffset.x = (clipRad) * Math.cos(ang - clipAng);
			clipOffset.y = (clipRad) * Math.sin(ang - clipAng);
		}
		if (clip != null)
		{
			clip.wireframes[0].ang = this.ang;
			clip.wireframes[0].rotate(angle);
		}
		if (shell != null)
		{
			if (facingRight)
			{
				shellOffset.x = shellRad * Math.cos(ang + shellAng);
				shellOffset.y = shellRad * Math.sin(ang + shellAng);
			}
			else
			{
				shellOffset.x = shellRad * Math.cos(ang - shellAng);
				shellOffset.y = shellRad * Math.sin(ang - shellAng);
			}
		}
		updateClip();

	}
	public void rotateToward(double mx, double my)
	{
		super.rotateToward(mx, my);
		if (ang > Math.PI / 2 && ang < Math.PI * 3 / 2)
		{
			flip(false);// left
		}
		else
		{
			flip(true);// right
		}

	}
	public boolean intersects(Entity e)
	{
		if (super.intersects(e))
			return true;
		else if (clip != null && !reloading && !ghost)
			return clip.intersects(e);
		else
			return false;
	}
	public void flip(boolean b)
	{
		if (facingRight == b)
			return;
		facingRight = b;
		// need to flip Wireframes[]
		if (clip != null)
		{
			Vect temp = clipOffset;
			clipOffset = clipOffsetFlip;
			clipOffsetFlip = temp;
		}
		if (shell != null)
		{
			Vect temp = shellOffset;
			shellOffset = shellOffsetFlip;
			shellOffsetFlip = temp;
		}
	}
	public void step()
	{
		super.step();
		if (reloading)
			reload();
		canShoot = (nextShotTime <= 0);

		if (!focus)
		{
			if (accuracy > origAccuracy)
				if (canShoot)
					accuracy -= (aimControl / fps);
				else
					accuracy -= ((aimControl / 3) / fps);
			while (accuracy < origAccuracy)
				accuracy += 10E-5;
		}
		else
		// focus==true
		{
			if (accuracy > .01)
				if (!canShoot)
					accuracy -= ((aimControl / 2) / fps);
				else
					accuracy -= ((aimControl * 2) / fps);
			while (accuracy < .01)
				accuracy += 10E-5;

		}
		if (accuracy > minAccuracy)
			accuracy = 0 + minAccuracy;
		if (reloading)
		{
			reloadTimeLeft -= 1 / fps;
			if (focus)
				reloadTimeLeft -= .7 / fps;
		}
		nextShotTime -= 1 / fps;
		myTime = System.nanoTime();
	}
	public void advance()
	{
		super.advance();
		if (clip != null)
		{
			clip.rotate(ang - clip.ang);
			clip.translateTo(x + clipOffset.x, y + clipOffset.y);
		}
	}
	public void col(Entity e)
	{
		double xs1 = 0 + xSpeed;
		double ys1 = 0 + ySpeed;
		super.col(e);
		if (Math.hypot(xs1 - xSpeed, ys1 - ySpeed) > 1000 && Math.random() > .5)
			shoot();
	}
	public boolean shoot()
	{
		boolean r = false;
		if (((currentClip > 0 && canShoot) || (currentClip == -1 && (!currentlyAutomatic || (automatic && canShoot)))) && ((!reloading && useClip) || (!useClip)) && ((!currentlyAutomatic && released) || (currentlyAutomatic)))
		{
			r = true;

			nextShotTime = (60 / rof);

			ejectShell();

			canShoot = false;
			reloading = false;
			canDropClip = true;
			released = false;

			if (currentClip != -1 && !unlimitedClip)
				currentClip--;
			playSound(shotSound);
			if (!automatic)
				playSound(boltSound);

			if (bullet instanceof Bullet)
			{
				Bullet b = (Bullet) bullet.getNew();
				b.translateTo(x + muzzleOffset.x, y + muzzleOffset.y);
				b.fps = 0 + this.fps;
				b.ang = 0 + ang;
				b.loadArray(ents);
				if (!(currentClip == -1 && ((automatic && !currentlyAutomatic) || !automatic)))
				{
					b.ang += (Math.random() * accuracy) - (accuracy / 2);

					if (!focus)
						accuracy += accuracyOff;
					else if (focus)
						accuracy += accuracyOff / 1.6;
				}
				b.shoot(fps);
				b.xSpeed += this.xSpeed;
				b.ySpeed += this.ySpeed;
				b.noCollide.add(this);
				b.owner = this.ownerID;
				noCollide.add(b);
				ents.add(b);
				if (this.ownerID == -1)
				{
					xSpeed = ((xSpeed * mass) - (b.mass * b.xSpeed)) / mass;
					ySpeed = ((ySpeed * mass) - (b.mass * b.ySpeed)) / mass;
				}
			}
			else if (bullet instanceof Explosive)
			{
				Explosive eTemp = (Explosive) bullet.getNew();
				eTemp.timeRemaining *= ((Math.random() * .2) + .9);
				eTemp.loadArray(ents);
				eTemp.translateTo(x + muzzleOffset.x, y + muzzleOffset.y);
				if (!(currentClip == -1 && ((automatic && !currentlyAutomatic) || !automatic)))
				{
					eTemp.ang += (Math.random() * accuracy) - (accuracy / 2);

					if (!laserPointerOn && !focus)
						accuracy += accuracyOff;
					else if (focus && laserPointerOn)
						accuracy += accuracyOff / 1.6;
					else if (focus)
						accuracy += accuracyOff / 1.35;
					else
						// laserPointerOn
						accuracy += accuracyOff / 1.15;

				}
				eTemp.fps = 0 + this.fps;
				eTemp.rotate(ang);
				eTemp.shoot(fps);
				eTemp.xSpeed += this.xSpeed;
				eTemp.ySpeed += this.ySpeed;
				eTemp.noCollide.add(this);
				noCollide.add(eTemp);
				ents.add(eTemp);
				if (this.ownerID == -1)
				{
					xSpeed = ((xSpeed * mass) - (eTemp.mass * eTemp.xSpeed)) / mass;
					ySpeed = ((ySpeed * mass) - (eTemp.mass * eTemp.ySpeed)) / mass;
				}
			}
			else if (bullet instanceof AdhesiveEntity)
			{
				AdhesiveEntity eTemp = (AdhesiveEntity) bullet.getNew();
				eTemp.loadArray(ents);
				eTemp.translateTo(x + muzzleOffset.x, y + muzzleOffset.y);
				eTemp.timeRemaining *= ((Math.random() * .2) + .9);
				eTemp.ang += (Math.random() * accuracy) - (accuracy / 2);
				eTemp.fps = 0 + this.fps;
				eTemp.rotate(ang);
				eTemp.shoot(fps);
				eTemp.xSpeed += this.xSpeed;
				eTemp.ySpeed += this.ySpeed;
				eTemp.noCollide.add(this);
				noCollide.add(eTemp);
				ents.add(0, eTemp);
				if (this.ownerID == -1)
				{
					xSpeed = ((xSpeed * mass) - (eTemp.mass * eTemp.xSpeed)) / mass;
					ySpeed = ((ySpeed * mass) - (eTemp.mass * eTemp.ySpeed)) / mass;
				}

			}
			else if (bullet instanceof FracLine)
			{
				FracLine f = new FracLine(bullet.name, new Vect(x + muzzleOffset.x, y + muzzleOffset.y), new Vect(x + (750 * Math.cos(ang)) + (Math.random() - .5) * 300, y + (750 * Math.sin(ang)) + (Math.random() - .5) * 300), bullet.timeRemaining, ((FracLine) bullet).regenTime, ((FracLine) bullet).damage, ((FracLine) bullet).col);
				f.fps = 0 + this.fps;
				noCollide.add(f);
				f.noCollide.add(this);
				ents.add(f);
			}
		}
		else if (currentClip <= 0 && ((!currentlyAutomatic && released) || (currentlyAutomatic && canShoot)) && !reloading && extraAmmo > 0)
		{
			reload();
			released = true;
		}
		if (((reloading && !useClip) || currentClip == 0) && released == true)
		{
			playSound(dryFire);
			released = false;
			if (!reloading)
				reload();
		}
		if (r)
		{
			addMuzzleFlash();
			if (!(currentClip == -1 && ((automatic && !currentlyAutomatic) || !automatic)))
			{

				if (!laserPointerOn && !focus)
					accuracy += accuracyOff;
				else if (focus && laserPointerOn)
					accuracy += accuracyOff / 1.6;
				else if (focus)
					accuracy += accuracyOff / 1.35;
				else
					// laserPointerOn
					accuracy += accuracyOff / 1.15;

			}
		}

		return r;
	}
	public void release()
	{
		released = true;
	}
	public void focus()
	{
		focus = true;
	}
	public void unfocus()
	{
		focus = false;
	}
	public void addMuzzleFlash()
	{
		if (muzzleFlashes.size() > 0)
		{
			int rand = (int) (Math.random() * muzzleFlashes.size());
			Entity tempMF = muzzleFlashes.get(rand).getNew();
			tempMF.rotate(ang);
			tempMF.translateTo(x + muzzleOffset.x + (Math.cos(ang) * 19), y + muzzleOffset.y + (Math.sin(ang) * 19));
			tempMF.xSpeed = 0 + this.xSpeed;
			tempMF.ySpeed = 0 + this.ySpeed;
			tempMF.noCollide.add(this);
			noCollide.add(tempMF);

			if (facingRight)
				ents.add(indexOfOwner() + 1, tempMF);
			else
				ents.add(indexOfOwner(), tempMF);
		}
	}
	public void ejectShell()
	{
		if (shell != null)
		{
			Entity newShell = shell.getNew();
			double speed = 400;
			newShell.fps = 0 + this.fps;
			newShell.myTime = 0 + this.myTime;
			newShell.translateTo(x + shellOffset.x, y + shellOffset.y); // same
																		// as
																		// smoke
																		// location
			newShell.ang = 0 + ang;
			newShell.rotateWF(ang);
			newShell.noCollide.add(this);
			newShell.loadArray(ents);
			noCollide.add(newShell);
			newShell.intersectStaticOnly = true;
			newShell.gravity = gravity;
			// newShell.ghost = true;

			if (facingRight)
			{
				double tempAng = -Math.PI * .75 + ang + (Math.random() * .2 - .1);
				newShell.xSpeed = 0 + xSpeed + ((Math.random() * .3 + .8) * Math.cos(tempAng) * speed);
				newShell.ySpeed = 0 + ySpeed + ((Math.random() * .3 + .8) * Math.sin(tempAng) * speed);
				newShell.rotSpeed = -17 * (.75 + Math.random() * .5);

				if (shellSmoke != null)
				{
					Entity tempS = shellSmoke.getNew();
					tempS.fps = 0 + this.fps;
					tempS.gravity = 100;
					tempS.loadArray(ents);
					tempS.intersectStaticOnly = true;
					tempS.rotate(Math.PI * Math.random());
					tempS.rotSpeed = (Math.random() * -4) + 1;
					tempS.translateTo(x + shellOffset.x, y + shellOffset.y); // same
																				// as
																				// shell
																				// locaiton
					tempS.xSpeed = (Math.cos(tempAng) * speed / 4);
					tempS.ySpeed = (Math.sin(tempAng) * speed / 4);
					tempS.noCollide.add(this);
					noCollide.add(tempS);
					tempS.noCollide.add(clip);

					ents.add(indexOfOwner() + 1, newShell);
					ents.add(indexOfOwner() + 1, tempS);

				}
			}
			else
			{
				double tempAng = Math.PI * .75 + ang + (Math.random() * .2 - .1);
				newShell.xSpeed = 0 + xSpeed + (Math.cos(tempAng) * speed);
				newShell.ySpeed = 0 + ySpeed + (Math.sin(tempAng) * speed);
				newShell.rotSpeed = 17 * (.75 + Math.random() * .5);

				if (shellSmoke != null)
				{
					Entity tempS = shellSmoke.getNew();
					tempS.fps = 0 + this.fps;
					tempS.loadArray(ents);
					tempS.rotate(Math.PI * Math.random());
					tempS.rotSpeed = (Math.random() * 4) - 1;
					tempS.translateTo(x + shellOffset.x, y + shellOffset.y); // same
																				// as
																				// shell
																				// locaiton
					tempS.xSpeed = 0 + xSpeed + (Math.cos(tempAng) * speed / 4);
					tempS.ySpeed = 0 + ySpeed + (Math.sin(tempAng) * speed / 4);
					tempS.noCollide.add(this);
					noCollide.add(tempS);
					tempS.noCollide.add(clip);

					ents.add(indexOfOwner(), newShell);
					ents.add(indexOfOwner(), tempS);
				}
			}
		}
	}
	public int indexOfOwner()
	{
		int r = 0;
		try
		{
			for (int i = 0; i < ents.size(); i++)
			{
				if (ents.get(i).ID == ownerID)
					r = i;
			}
		}
		catch (Exception e)
		{}
		return r;
	}
	public void dropClip()
	{
		if (clip == null)
			return;
		canDropClip = false;
		Entity newClip = clip.copy();
		newClip.numImg = 1;
		newClip.img = new Image[1];
		newClip.imgName = new String[1];
		if (facingRight)
		{
			newClip.img[0] = clip.img[0];
			newClip.imgName[0] = clip.imgName[0];
		}
		else
		{
			newClip.img[0] = clip.img[1];
			newClip.imgName[0] = clip.imgName[1];
		}
		newClip.x = 0 + x;
		newClip.y = 0 + y;
		if (!facingRight)
			newClip.rotate(Math.PI);

		double speed = 150;
		newClip.fps = 0 + this.fps;
		newClip.myTime = 0 + this.myTime;
		newClip.translateTo(x + clipOffset.x, y + clipOffset.y);
		newClip.penetration = 25;

		newClip.intersectStaticOnly = true;
		newClip.noCollide.add(this);
		newClip.loadArray(ents);
		noCollide.add(newClip);

		if (facingRight)
		{
			newClip.rotSpeed = -Math.PI * (Math.random() * .4 + .8);
			newClip.xSpeed = xSpeed + (Math.cos(ang + Math.PI / 2) * speed);
			newClip.ySpeed = ySpeed + (Math.sin(ang + Math.PI / 2) * speed);

			ents.add(indexOfOwner(), newClip);
		}
		else
		{
			newClip.rotSpeed = Math.PI * (Math.random() * .4 + .8);
			newClip.xSpeed = xSpeed - (Math.cos(ang + Math.PI / 2) * speed);
			newClip.ySpeed = ySpeed - (Math.sin(ang + Math.PI / 2) * speed);

			ents.add(indexOfOwner(), newClip);
		}
		// no dude (used for armory)
		// playSound(clipSound);
		if (clip != null)
			playSound("MagEject.wav");
		else
			playSound(reloadSound);
	}
	public void reload()
	{
		if (unlimitedClip)
		{
			currentClip = 0 + fullClip;
		}
		else if (currentClip != -1 && ((plusOne && currentClip < fullClip + 1) || (!plusOne & currentClip < fullClip)))
		{
			if (useClip)
			{
				if (plusOne && currentClip == 0 && reloading && reloadTimeLeft < (reloadTime / 4) && !dryReloadPlayed)
				{
					dryReloadPlayed = true;
					playSound(dryReload);
				}

				if ((!plusOne && currentClip >= fullClip || extraAmmo == 0) || (plusOne && currentClip >= fullClip + 1))
				{
					reloading = false;
					canDropClip = true;
				}
				else if (!reloading)
				{
					dropClip();

					reloadTimeLeft = 0 + reloadTime;
					if (plusOne && currentClip == 0)
						reloadTimeLeft += reloadTime / 4;
					reloading = true;
					playSound(reloadSound);
					return;
				}

				else if (reloading && reloadTimeLeft <= 0)
				{
					release();
					int ammountToAdd = 0;
					if (plusOne && currentClip != 0)
						ammountToAdd = (1 + fullClip) - currentClip;
					else
						ammountToAdd = (fullClip - currentClip);

					if (unlimitedAmmo)
					{
						currentClip += ammountToAdd;
					}
					else if (extraAmmo >= ammountToAdd)
					{
						currentClip += ammountToAdd;
						extraAmmo -= ammountToAdd;
					}
					else
					{
						currentClip += extraAmmo;
						extraAmmo = 0;
					}
					accuracy = minAccuracy * .5;
					reloading = false;
					if (clip != null)
						playSound("MagIn.wav");
					canDropClip = true;
					dryReloadPlayed = false;
				}
			}
			else
			{
				if ((!plusOne && currentClip >= fullClip || extraAmmo == 0) || (plusOne && currentClip >= fullClip + 1))
				{
					reloading = false;
					canDropClip = true;
				}
				else if (!reloading)
				{
					dropClip();
					reloadTimeLeft = 0 + reloadTime;
					if (currentClip != fullClip)
						playSound(reloadSound);
					reloading = true;
					return;
				}
				else if (reloading && (reloadTimeLeft <= 0))
				{
					reloadTimeLeft = 0 + reloadTime;

					if (extraAmmo > 0 && currentClip < fullClip)
					{
						currentClip++;
						extraAmmo--;
						if (currentClip != fullClip)
							playSound(reloadSound);
						else
							playSound(boltSound);
					}

				}

				if (currentClip == fullClip)
					reloading = false;
			}
		}
	}
	public void addAmmo(int ammo)
	{
		extraAmmo += ammo;
	}
	public void updateWFs()
	{
		for (int i = 0; i < numWF; i++)
		{
			wireframes[i].translateTo(x + wfOffset[i].x, y + wfOffset[i].y);
		}
	}
	public void switchFireMode()
	{
		if (automatic && currentlyAutomatic)
		{
			currentlyAutomatic = false;
			playSound("FireModeChange.wav");
		}
		else if (automatic && !currentlyAutomatic)
		{
			currentlyAutomatic = true;
			playSound("FireModeChange.wav");
		}
	}
	public void switchLAM()
	{
		if (laserPointer && laserPointerOn)
			laserPointerOn = false;
		else if (laserPointer && !laserPointerOn)
			laserPointerOn = true;
	}
	public String saveString()
	{
		String s = "[ " + name + " Weapon " + extraAmmo + " " + currentClip + " " + super.saveString();
		return s + " ]";
	}
}
