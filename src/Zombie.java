import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Zombie extends Biped
{
	Image	armL, armR;
	int		width, height;
	Dude	target;
	int		damage;
	AINode	travelTo;
	double	lifeTime	= 0;
	double	canHurt		= 0;
	int		legHeight, legFrames, legWidth;

	public Zombie()
	{
		ID = -1;
	}
	public void render(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		if (facingRight)
			lookAng = -Math.PI / 4;
		else
			lookAng = Math.PI / 2;

		if (name.equals("Normal_Zombie"))
			renderNormal(g, trans, origX, origY);
		else if (name.equals("Strong_Zombie"))
			renderStrong(g, trans, origX, origY);
		else if (name.equals("Mega_Zombie"))
			renderMega(g, trans, origX, origY);
		else if (name.equals("Fast_Zombie"))
			renderFast(g, trans, origX, origY);
		else if (name.equals("Explosive_Zombie"))
			renderExplosive(g, trans, origX, origY);
	}
	private void renderNormal(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		if (facingRight)
		{

			g.drawImage(legsStillR, (int) (x - origX - legsStillR.getWidth(null) / 2), (int) (y - origY - legHeight + 6), null);
			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 188);
			trans.rotate((lookAng + Math.PI * 2) / 3 - Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);
			g.drawImage(torsoR, (int) (x - origX - torsoR.getWidth(null) / 2), (int) (y - origY - 180), null);

			g.drawImage(headR, trans, null);
		}
		else
		{

			g.drawImage(legsStillL, (int) (x - origX - legsStillL.getWidth(null) / 2), (int) (y - origY - legHeight + 6), null);

			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 188);
			trans.rotate((lookAng + Math.PI) / 3 + 3 * Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);
			g.drawImage(torsoL, (int) (x - origX - torsoL.getWidth(null) / 2), (int) (y - origY - 180), null);

			g.drawImage(headL, trans, null);
		}
	}
	private void renderStrong(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		g.setColor(Color.red.darker());
		g.fillRect((int) x - (int) origX - width / 2, (int) y - (int) origY - (height - 12), width, height);
		g.setColor(Color.red);
		g.fillRect((int) head.xCenter - 12 - (int) origX, (int) head.yCenter - 12 - (int) origY, 25, 25);
	}
	private void renderFast(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		if (facingRight)
		{

			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 100);
			trans.rotate((lookAng + Math.PI * 2) / 3 - Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);

			g.drawImage(torsoR, (int) (x - origX - torsoR.getWidth(null) / 2), (int) (y - origY - 89), null);
			g.drawImage(headR, trans, null);

			g.drawImage(legsStillR, (int) (x - origX - legsStillR.getWidth(null)
					/ 2), (int) (y - origY - legHeight - 44), null);
		}
		else
		// facing left
		{

			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 100);
			trans.rotate((lookAng + Math.PI) / 3 + 3 * Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);

			g.drawImage(torsoL, (int) (x - origX - torsoL.getWidth(null) / 2), (int) (y - origY - 89), null);
			g.drawImage(headL, trans, null);

			g.drawImage(legsStillL, (int) (x - origX - legsStillL.getWidth(null)
					/ 2), (int) (y - origY - legHeight - 44), null);
		}

		// //////Body + Arms + Head/\
		// renderWireframes(g, origX, origY);
	}
	private void renderExplosive(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		if (facingRight)
		{

			g.drawImage(legsStillR, (int) (x - origX - legsStillR.getWidth(null) / 2), (int) (y - origY - legHeight - 54), null);
			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 163);
			trans.rotate((lookAng + Math.PI * 2) / 3 - Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);
			g.drawImage(torsoR, (int) (x - origX - torsoR.getWidth(null) / 2), (int) (y - origY - 151), null);

			g.drawImage(headR, trans, null);
		}
		else
		{

			g.drawImage(legsStillL, (int) (x - origX - legsStillL.getWidth(null) / 2), (int) (y - origY - legHeight - 54), null);
			trans.setToTranslation(x - origX - headR.getWidth(null) / 2, y - origY - headR.getHeight(null) / 2 - 163);
			trans.rotate((lookAng + Math.PI) / 3 + 3 * Math.PI / 2, headR.getWidth(null) / 2, headR.getHeight(null) / 2);
			g.drawImage(torsoL, (int) (x - origX - torsoL.getWidth(null) / 2), (int) (y - origY - 151), null);

			g.drawImage(headL, trans, null);
		}
	}
	private void renderMega(Graphics2D g, AffineTransform trans, double origX, double origY)
	{
		g.setColor(Color.DARK_GRAY.darker());
		g.fillRect((int) x - (int) origX - width / 2, (int) y - (int) origY - (height - 12), width, height);
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) head.xCenter - 12 - (int) origX, (int) head.yCenter - 12 - (int) origY, 25, 25);
	}
	public Biped copy()
	{
		return new Zombie();
	}
	// ////////////////////////Step\/
	public void step()
	{
		lifeTime += 1 / fps;
		if (entsHit.size() > 0)
		{
			int numEnts = 0;
			double avgHitAngle = 0;
			while (entsHit.size() != 0)
			{
				Entity e = entsHit.get(0);
				if (e instanceof StaticEntity)
				{
					if (wireframes[0].collide(e))
					{
						exitOverlap(e);
						avgHitAngle += getLeastHitSlope(e);
						numEnts++;
					}
				}
				else
					col(e);

				if (feet.collide(e) && feetCheck.collide(e))
					grounded = true;

				entsHit.remove(0);
			}
			{
				avgHitAngle /= numEnts;
				Vect mySpeed = new Vect(xSpeed, ySpeed);
				double myAng = Math.atan(-ySpeed / xSpeed);
				if (xSpeed < 0)
					myAng += Math.PI;
				Vect alignedSpeed = new Vect(mySpeed.velocity() * Math.cos(avgHitAngle), mySpeed.velocity() * Math.sin(avgHitAngle));
				// x is perpendicular to surface
				// y is parallel to surface
				if (grounded)
				{
					ySpeed = 0;
					if ((!moveR && !moveL) || ((xSpeed > 0 && moveL) || (xSpeed < 0 && moveR)))
					{
						xSpeed -= ((xSpeed * 3) / fps);
					}
				}
				else
				{
					alignedSpeed.x = (alignedSpeed.x * bounce);
					alignedSpeed.y -= (2 * alignedSpeed.y);

					double resultAngle = Math.PI - myAng - 2 * (avgHitAngle - myAng);

					if (alignedSpeed.x > Integer.MIN_VALUE && alignedSpeed.y > Integer.MIN_VALUE)
					{
						xSpeed = alignedSpeed.velocity() * Math.cos(resultAngle);
						ySpeed = alignedSpeed.velocity() * Math.sin(resultAngle);
					}
				}
			}
		}
		canHurt -= 1 / fps;
		if (target != null)
		{
			if (wireframes[0].collide(target))
			{
				if (canHurt <= 0)
				{
					canHurt = .5;
					target.damage(1 + (Math.random() * .5 + .5) * damage);
					if (target.isDead())
						target.killerInfo = "" + name;
				}

				if (Math.abs(target.y - y) < 100)
				{
					if (target.x > x)
						target.xSpeed = 500;
					else
						target.xSpeed = -500;
					target.ySpeed = -100;
				}
				else
				{
					if (target.x > x)
						target.xSpeed = 500;
					else
						target.xSpeed = -500;
					target.ySpeed = -300;
					target.y -= 10;
				}
			}
		}
		followInstructions();
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

		jumpRequested = false;
		moveR = false;
		moveL = false;
		grounded = false;
		feetPlanted = false;
		sprinting = false;
	}
	public void advance()
	{
		ySpeed += (gravity / fps);
		speedBrake();
		x += (xSpeed / fps);
		y += (ySpeed / fps);
		if (xSpeed > 0)
			facingRight = true;
		else if (xSpeed < 0)
			facingRight = false;
	}
	protected void speedBrake()
	{
		int maxSpeed = 10000;
		if (xSpeed > maxSpeed)
			xSpeed = maxSpeed;
		else if (xSpeed < -maxSpeed)
			xSpeed = -maxSpeed;
	}
	private void followInstructions()
	{
		if (target != null)
		{
			if (health < fullHealth / 2)
				sprinting = true;

			if (x > target.x)
				moveLeft();
			else
				moveRight();
		}
	}
	private void manageMovement()
	{
		if (jumpRequested)
		{
			ySpeed = (-jumpSpeed);
			xSpeed /= 1.5;
		}
		if (moveR && !moveL)
		{
			if (!sprinting && xSpeed < walkSpeed)
				xSpeed += (walkSpeed / fps);
			else if (xSpeed < walkSpeed * sprintMultiplier)
				xSpeed += (walkSpeed * sprintMultiplier) / fps;

			// speed check
			if (sprinting && xSpeed > walkSpeed * sprintMultiplier)
				xSpeed = walkSpeed * sprintMultiplier;
			else if (xSpeed > walkSpeed)
				xSpeed = walkSpeed;
		}
		else if (moveL && !moveR)
		{
			if (!sprinting && xSpeed < walkSpeed)
				xSpeed -= (walkSpeed / fps);
			else if (xSpeed < walkSpeed * sprintMultiplier)
				xSpeed -= (walkSpeed * sprintMultiplier) / fps;

			// speed check
			if (sprinting && xSpeed < -walkSpeed * sprintMultiplier)
				xSpeed = -walkSpeed * sprintMultiplier;
			else if (xSpeed < -walkSpeed)
				xSpeed = -walkSpeed;
		}
		jumpRequested = false;
		moveR = false;
		moveL = false;
	}
	public void computeRect()
	{
		rect = new Rectangle2D.Double(x + -width / 2, y - height - 25, width, height + 25);
		rect2 = new Rectangle2D.Double(x + -width / 2, y - height - 25, width, height + 25);
	}
	public void damage(double d)
	{
		super.damage(d);
		xSpeed /= Math.log(d / 5 + 1);
	}
	// ////////////////////////Step/\
	// ////////////////////////AI Commands \/

	// ////////////////////////AI Commands /\

}
