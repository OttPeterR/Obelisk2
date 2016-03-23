import java.awt.Image;
import java.util.ArrayList;

public class AdhesiveExplosive extends AdhesiveEntity
{
	double		radius, exDamage, force;
	Weapon		detonater;
	int			forceToExplode;
	Explosion	ex;

	public AdhesiveExplosive(String n, Image im, String ims, Wireframe wf, Vect wfOff, double t, double mss, double spd, double rad, double exp, double frce, int forceToBlow)
	{
		super(n, im, ims, wf, wfOff, t, mss, spd, 0);
		possibleHits = new ArrayList<Entity>();
		if (rad > 0)
		{
			exDamage = exp;
			radius = rad;
			speed = spd;
			forceToExplode = forceToBlow;
			force = frce;
			ex = new Explosion(ents, x, y, exDamage, force, radius);
		}
		else
			ex = null;
	}
	public void col(Entity e)
	{
		double origYS = 0 + ySpeed;
		double origXS = 0 + xSpeed;
		if (!stuck)
		{
			lastTouchedEntity = e;
			stuck = true;
			noCollide.clear();
			stuckRad = Math.hypot(x - e.x, e.y - y);
			if (x > e.x)
				stuckAng = Math.atan((e.y - y) / (e.x - x)) - lastTouchedEntity.ang;
			else
				stuckAng = Math.PI - Math.atan((y - e.y) / (e.x - x)) - lastTouchedEntity.ang;
			noCollide.add(e);
		}
		if (forceToExplode > 0 && Math.hypot(origXS, origYS) > forceToExplode)
			health = 0;
	}
	public Entity getNew()
	{
		AdhesiveExplosive e = new AdhesiveExplosive(name, img[0], imgName[0], wireframes[0].getNew(), wfOffset[0], fullTimeRemaining, mass, speed, radius, exDamage, force, forceToExplode);
		if (ex != null)
			e.ex = (Explosion) this.ex.getNew();
		e.hitParticles = this.hitParticles;
		e.deathParticles = this.deathParticles;
		e.particlesToAddOnDeath = this.particlesToAddOnDeath;
		e.fade = this.fade;
		e.forward = this.forward;
		e.pickUpable = this.pickUpable;
		return e;
	}
	public Entity copy()
	{
		return getNew();
	}
	public boolean removeMe()
	{
		if ((health == -1 || health > 0) && (timeRemaining == -1 || timeRemaining > 0))
			return false;
		if (ex != null)
		{
			if (lastTouchedEntity != null)
				ex.hitParticles = lastTouchedEntity.hitParticles;

			ex.translateTo(x, y);
			//ex.loadArray(ents);
			ex.noCollide = this.noCollide;
			if (lastTouchedEntity != null)
			{
				ex.hitP = lastTouchedEntity.hitParticles;

			}
			ex.noCollide.remove(lastTouchedEntity);
			ex.fps = 0 + this.fps;
			ents.add(ex);
		}

		return true;
	}

	public void addHitParticle(Entity e, double hitX, double hitY)
	{}
}
