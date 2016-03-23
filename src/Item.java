import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Item extends PhysicsEntity
{
	final static int	HAND_GUN_AMMO		= 2;
	final static int	SHOTGUN_AMMO		= 3;
	final static int	ASSAULT_RIFLE_AMMO	= 4;
	final static int	SMG_AMMO			= 5;
	final static int	SNIPER_RIFLE_AMMO	= 6;
	final static int	LMG_AMMO			= 7;
	final static int	EXPLOSIVE_AMMO		= 8;
	final static int	ENERGY_AMMO			= 9;
	final static int	ANY_AMMO			= 10;
	final static int	GRENADES_2			= 11;
	final static int	GRENADES_5			= 12;
	final static int	LASER_POINTER		= 13;
	final static int	HEALTH_25			= 25;
	final static int	HEALTH_50			= 50;
	final static int	HEALTH_100			= 100;
	final static int	OVER_HEAL_25		= 26;
	final static int	OVER_HEAL_50		= 51;
	final static int	OVER_HEAL_100		= 101;

	int					type, uses = 1, origUses = 1;
	String				useSound			= "";

	public Item(String n, int inType, Image im, String ims, Wireframe wf, Vect wfOff, double t, String sound)
	{
		super(n, im, ims, wf, wfOff, -1, t, 5, .3, .3, true);
		useSound = sound;
	}
	public Item(String n, int inType, Image im, String ims, Wireframe wf, Vect wfOff, double t, String sound, int inUses)
	{
		super(n, im, ims, wf, wfOff, -1, t, 5, .3, .3, true);
		useSound = sound;
		origUses = inUses;
		uses = 0 + origUses;
	}
	public Entity getNew()
	{
		Item r = new Item(name, type, img[0], imgName[0], wireframes[0].getNew(), wfOffset[0], this.fullTimeRemaining, useSound, origUses);
		r.deathParticles = this.deathParticles;
		r.hitParticles = this.hitParticles;
		r.particlesToAddOnDeath = this.particlesToAddOnDeath;
		return r;
	}
	public String toString()
	{
		return super.toString() + type + uses;
	}
	public void activate(Dude d)
	{
		uses--;
		ents.add(new Sound(useSound, x, y));

		if (d.weapons.size() > 0)
		{
			switch (type)
			{
				case (HAND_GUN_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_HAND_GUN)
						d.weapons.get(0).extraAmmo += 5 * (d.weapons.get(0).fullClip);
					break;

				case (ASSAULT_RIFLE_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_ASSAULT_RIFLE)
						d.weapons.get(0).extraAmmo += 3 * (d.weapons.get(0).fullClip);
					break;
				case (SMG_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_SMG)
						d.weapons.get(0).extraAmmo += 5 * (d.weapons.get(0).fullClip);
					break;
				case (SHOTGUN_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_SHOTGUN)
						d.weapons.get(0).extraAmmo += 3 * (d.weapons.get(0).fullClip);
					break;
				case (LMG_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_LMG)
						d.weapons.get(0).extraAmmo += 2 * (d.weapons.get(0).fullClip);
					break;
				case (EXPLOSIVE_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_EXPLOSIVE)
						d.weapons.get(0).extraAmmo += 5 * (d.weapons.get(0).fullClip);
					break;
				case (ENERGY_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_ENERGY)
						d.weapons.get(0).extraAmmo += 3 * (d.weapons.get(0).fullClip);
					break;
				case (SNIPER_RIFLE_AMMO):
					if (d.weapons.get(0).type == Weapon.TYPE_SNIPER_RIFLE)
						d.weapons.get(0).extraAmmo += 3 * (d.weapons.get(0).fullClip);
					break;
				case (ANY_AMMO):
					d.weapons.get(0).extraAmmo = 5 * (d.weapons.get(0).fullClip);
					break;
				case (LASER_POINTER):
					d.weapons.get(0).laserPointer = true;
					d.weapons.get(0).laserPointerOn = true;
					break;
			}
		}
		else
		{
			switch (type)
			{
				case (GRENADES_2):
					d.grenades += 2;
					break;
				case (GRENADES_5):
					d.grenades += 5;
					break;
				case (HEALTH_25):
					d.health += 25;
					if (d.health > d.fullHealth)
						d.health = 0 + d.fullHealth;
					break;
				case (HEALTH_50):
					d.health += 50;
					if (d.health > d.fullHealth)
						d.health = 0 + d.fullHealth;
					break;
				case (HEALTH_100):
					d.health += 100;
					if (d.health > d.fullHealth)
						d.health = 0 + d.fullHealth;
					break;
				case (OVER_HEAL_25):
					d.health += 25;
					break;
				case (OVER_HEAL_50):
					d.health += 50;
					break;
				case (OVER_HEAL_100):
					d.health += 100;
					break;

			}
		}

	}
	public boolean isDead()
	{
		return uses == 0;
	}
	public void renderInfo(Graphics2D g, double origX, double origY, Dude d, Color c)
	{
		g.setColor(c);

		int drawX = (int) (x + (100 - (x - d.x)) - origX);
		if (x < d.x)
			drawX = (int) (x - (140 + (x - d.x)) - origX);
		int drawY = (int) (y - 150 - origY);
		g.setStroke(new BasicStroke(2));
		g.drawLine((int) (x - origX), (int) (y - origY), drawX - 4, drawY + 13);
		g.drawLine(drawX - 4, drawY - 13, drawX - 4, drawY + 13);//
		g.drawLine(drawX - 4, drawY - 13, drawX + 20, drawY - 13);
		g.drawString(name.replace('_', ' '), drawX, drawY);
		if (uses > 1)
			g.drawString("Uses: " + uses, drawX, drawY + 13);

	}
}
