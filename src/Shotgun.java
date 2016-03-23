import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Shotgun extends Weapon
{
	double	spread, frags, fragError;

	public Shotgun(String n, Image[] im, String[] ims, Wireframe wf, Vect wfOff, int maxClip, int extra, double mss, Entity b, double numoffrags, double sprd, Vect muzzleOff, Entity shll, Vect shellOff, Entity clp, Vect clipOff, Vect trig, Vect hld, Vect shldr, double rate, double reloadt)
	{
		super(n, "Shotgun", im, ims, wf, wfOff, maxClip, extra, mss, b, muzzleOff, shll, shellOff, clp, clipOff, trig, hld, shldr, rate, reloadt, 0, 0);

		frags = numoffrags;
		spread = sprd;
		automatic = false;
		currentlyAutomatic = false;
		useClip = false;
		fragError = 0;
	}
	public void renderLaser(Graphics2D g, double origX, double origY)
	{

	}

	public boolean shoot()
	{
		boolean r = false;
		if (((currentClip > 0 && canShoot) || (currentClip == -1 && (!currentlyAutomatic || (automatic && canShoot)))) && ((!reloading && useClip) || (!useClip)) && ((!currentlyAutomatic && released) || (currentlyAutomatic)))
		{
			r = true;

			nextShotTime = (60 / rof);

			canShoot = false;
			reloading = false;
			canDropClip = true;
			released = false;

			if (currentClip != -1 && !unlimitedClip)
				currentClip--;
			playSound(shotSound);
			if (!automatic)
				playSound(boltSound);
			int errAmm = (int) ((fragError + 1) * ((Math.random() * 2) - 1));

			if (bullet instanceof Bullet)
			{
				ArrayList<Bullet> bullets = new ArrayList<Bullet>();
				for (int i = 0; i < frags + errAmm; i++)
				{
					Bullet b = (Bullet) bullet.getNew();
					b.translateTo(x + muzzleOffset.x, y + muzzleOffset.y);
					b.fps = 0 + this.fps;
					b.ang = 0 + ang + ((Math.random() - .5) * spread);
					b.loadArray(ents);
					b.rotSpeed = (Math.random() * 50) - 25;
					b.speed *= 1 + ((Math.random() * .1) - .05);
					if (b.ricochet && Math.random() < .2)
						b.ricochet = false;
					b.shoot(fps);
					b.xSpeed += this.xSpeed;
					b.ySpeed += this.ySpeed;
					b.noCollide.add(this);
					b.owner = this.ownerID;
					noCollide.add(b);
					bullets.add(b);
					if (this.ownerID == -1)
					{
						xSpeed = ((xSpeed * mass) - (b.mass * b.xSpeed)) / mass;
						ySpeed = ((ySpeed * mass) - (b.mass * b.ySpeed)) / mass;
					}
				}
				for (int i = bullets.size() - 1; i >= 0; i--)
				{
					bullets.get(i).noCollide.addAll(bullets);
					ents.add(bullets.get(i));
					bullets.remove(i);
				}
			}
			else if (bullet instanceof Explosive)
			{
				ArrayList<Explosive> expFrags = new ArrayList<Explosive>();
				for (int i = 0; i < frags + errAmm; i++)
				{
					Explosive eTemp = (Explosive) bullet.copy();
					eTemp.loadArray(ents);
					eTemp.timeRemaining *= ((Math.random() * .4) + .8);
					eTemp.translateTo(x + muzzleOffset.x, y + muzzleOffset.y);
					eTemp.ang += (Math.random() * accuracy) - (accuracy / 2);
					eTemp.fps = 0 + this.fps;
					eTemp.rotate(ang + ((Math.random() - .5) * spread));
					eTemp.shoot(fps);
					eTemp.xSpeed = ((Math.random() * .05 + .975) * eTemp.speed * Math.cos(eTemp.ang)) + this.xSpeed;
					eTemp.ySpeed = ((Math.random() * .05 + .975) * eTemp.speed * Math.sin(eTemp.ang)) + this.ySpeed;
					eTemp.noCollide.add(this);
					noCollide.add(eTemp);
					expFrags.add(eTemp);
					if (this.ownerID == -1)
					{
						xSpeed = ((xSpeed * mass) - (eTemp.mass * eTemp.xSpeed)) / mass;
						ySpeed = ((ySpeed * mass) - (eTemp.mass * eTemp.ySpeed)) / mass;
					}
				}
				for (int i = 0; i < expFrags.size(); i++)
				{
					expFrags.get(i).noCollide.addAll(expFrags);
					ents.add(expFrags.get(i));
				}

			}
			else if (bullet instanceof AdhesiveEntity)
			{
				ArrayList<AdhesiveEntity> expFrags = new ArrayList<AdhesiveEntity>();
				for (int i = 0; i < frags + errAmm; i++)
				{
					AdhesiveEntity eTemp = (AdhesiveEntity) bullet.copy();
					eTemp.timeRemaining *= ((Math.random() * .4) + .8);
					eTemp.loadArray(ents);
					eTemp.translateTo(x + muzzleOffset.x, y + muzzleOffset.y);

					eTemp.ang += (Math.random() * accuracy) - (accuracy / 2);

					eTemp.fps = 0 + this.fps;
					eTemp.rotate(ang + ((Math.random() - .5) * spread));
					eTemp.shoot(fps);
					eTemp.xSpeed = ((Math.random() * .05 + .975) * eTemp.speed * Math.cos(eTemp.ang)) + this.xSpeed;
					eTemp.ySpeed = ((Math.random() * .05 + .975) * eTemp.speed * Math.sin(eTemp.ang)) + this.ySpeed;
					eTemp.noCollide.add(this);
					noCollide.add(eTemp);
					expFrags.add(eTemp);
					if (this.ownerID == -1)
					{
						xSpeed = ((xSpeed * mass) - (eTemp.mass * eTemp.xSpeed)) / mass;
						ySpeed = ((ySpeed * mass) - (eTemp.mass * eTemp.ySpeed)) / mass;
					}
				}
				for (int i = 0; i < expFrags.size(); i++)
				{
					expFrags.get(i).noCollide.addAll(expFrags);
				}
				ents.addAll(expFrags);

			}
			if (muzzleFlashes != null)
			{
				int rand = (int) (Math.random() * muzzleFlashes.size());
				Entity tempMF = muzzleFlashes.get(rand).getNew();
				tempMF.fps = 0 + this.fps;
				tempMF.rotate(ang);
				tempMF.translateTo(x + muzzleOffset.x + (Math.cos(ang) * 19), y + muzzleOffset.y + (Math.sin(ang) * 19));
				tempMF.noCollide.add(this);
				noCollide.add(tempMF);
				ents.add(tempMF);
			}
			ejectShell();
		}
		else if ((currentClip <= 0 && currentClip != -1) && canShoot && !reloading && extraAmmo > 0)
		{
			reload();
		}
		if ((reloading || currentClip == 0) && released == true)
		{
			playSound("DryFire.wav");
			released = false;
		}
		if (r)
			addMuzzleFlash();

		return r;
	}
	public Shotgun getNew()
	{
		double curAng = 0 + this.ang;
		rotate(-ang);
		Shotgun w;
		if (clip != null)
			w = new Shotgun(name, img, imgName, wireframes[0].getNew(), wfOffset[0], fullClip, extraAmmo, mass, bullet, frags, spread, muzzleOffset, shell, shellOffset, clip.getNew(), clipOffset, trigger, hold, shoulder, rof, reloadTime);
		else
			w = new Shotgun(name, img, imgName, wireframes[0].getNew(), wfOffset[0], fullClip, extraAmmo, mass, bullet, frags, spread, muzzleOffset, shell, shellOffset, null, clipOffset, trigger, hold, shoulder, rof, reloadTime);
		rotate(curAng);
		w.useClip = this.useClip;
		w.automatic = this.automatic;
		w.currentlyAutomatic = this.currentlyAutomatic;
		w.reloadSound = this.reloadSound;
		w.shotSound = this.shotSound;
		w.price = price;
		w.boltSound = this.boltSound;
		w.reloadSound = this.reloadSound;
		w.dryReload = this.dryReload;
		w.plusOne = this.plusOne;
		w.loadArray(ents);
		w.muzzleFlashes = this.muzzleFlashes;
		w.gravity = 0 + gravity;
		return w;
	}
	public Shotgun copy()
	{
		Shotgun r = this.getNew();
		r.loadArray(ents);

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

		r.reloadTimeLeft = 0 + reloadTimeLeft;
		r.nextShotTime = 0 + nextShotTime;

		r.laserColor = this.laserColor;
		r.laserPointerOn = this.laserPointerOn;
		r.laserPointer = this.laserPointer;
		r.wireframeColor = this.wireframeColor;

		return r;
	}
	public String toString()
	{

		// String s = "Shotgun " + name + " " + numImg + " ";
		//
		// for (int i = 0; i < 2; i++)
		// {
		// s += imgName[i] + " ";
		// }
		// for (int i = 0; i < 2; i++)
		// {
		// s += imgOffset[i] + " ";
		// }
		// s += " " + numWF + " ";
		// for (int i = 0; i < wfOffset.length; i++)
		// {
		// s += wfOffset[i] + " ";
		// }
		// for (int i = 0; i < wfAng.length; i++)
		// {
		// s += wfAng[i] + " ";
		// }
		// for (int i = 0; i < wfRad.length; i++)
		// {
		// s += wfRad[i] + " ";
		// }
		//
		// s +=
		// " "
		// + x
		// + " "
		// + y
		// + " "
		// + xSpeed
		// + " "
		// + ySpeed
		// + " "
		// + ang
		// + " "
		// + rotSpeed
		// + " "
		// + speedDampening
		// + " "
		// + mass
		// + " "
		// + friction
		// + " "
		// + bounce
		// + " "
		// + penetration
		// + " "
		// + health
		// + " "
		// + fullHealth
		// + " "
		// + damageThreshold
		// + " "
		// + timeRemaining
		// + " "
		// + fullTimeRemaining
		// + " "
		// + physicalized
		// + " "
		// + ghost
		// + " "
		// + intersectStaticOnly
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
		// + unlimitedClip
		// + " "
		// + spread
		// + " "
		// + frags
		// + " "
		// + fragError;
		// return s;
		return "Shotgun " + name + " ";
	}
}
