import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class AILibrary extends Library
{
	public static void loadAll(ArrayList<Zombie> zombies)
	{
		zombies.add(ZombieNormal());
		zombies.add(ZombieFast());
		zombies.add(ZombieExplosive());
		zombies.add(ZombieStrong());
		zombies.add(ZombieMega());
	}

	public static Zombie ZombieNormal()
	{
		Zombie z = new Zombie();
		z.construct();

		z.width = 38;
		z.height = 190;
		z.wireframes[0] = Library.createWF(z.width, z.height);
		z.wfOffset[0] = new Vect(0, -z.height / 2);
		wireframe(z, z.width, z.height);

		z.name = "Normal_Zombie";

		z.fullHealth = 65;
		z.health = 0 + z.fullHealth;
		z.x = 0;
		z.y = 0;
		// head
		int headRand = (int) (Math.random() * 100);
		if (headRand < 33)
		{
			z.headR = img("ZombieNormal_Head_1R.png");
			z.headL = img("ZombieNormal_Head_1L.png");
			z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Head_1());

		}
		else if (headRand < 67)
		{
			z.headR = img("ZombieNormal_Head_2R.png");
			z.headL = img("ZombieNormal_Head_2L.png");
			z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Head_2());
		}
		else
		{
			z.headR = img("ZombieNormal_Head_3R.png");
			z.headL = img("ZombieNormal_Head_3L.png");
			z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Head_3());
		}
		int randBody = (int) (Math.random() * 100);
		if (randBody < 33)
		{
			z.torsoR = img("ZombieNormal_Body_1R.png");
			z.torsoL = img("ZombieNormal_Body_1L.png");
			z.armR = img("ZombieNormal_Arm_1R.png");
			z.armL = img("ZombieNormal_Arm_1L.png");
			z.deathParticles.add(ZombieParticleLibrary.ZombieBody_Normal_1());
			// z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Arm_1());
			// z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Arm_1());
		}
		else if (randBody < 67)
		{
			z.torsoR = img("ZombieNormal_Body_2R.png");
			z.torsoL = img("ZombieNormal_Body_2L.png");
			z.armR = img("ZombieNormal_Arm_2R.png");
			z.armL = img("ZombieNormal_Arm_2L.png");
			z.deathParticles.add(ZombieParticleLibrary.ZombieBody_Normal_2());
			// z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Arm_2());
			// z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Arm_2());
		}
		else
		{
			z.torsoR = img("ZombieNormal_Body_3R.png");
			z.torsoL = img("ZombieNormal_Body_3L.png");
			z.armR = img("ZombieNormal_Arm_3R.png");
			z.armL = img("ZombieNormal_Arm_3FL.png");
			z.deathParticles.add(ZombieParticleLibrary.ZombieBody_Normal_3());
			// z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Arm_3());
			// z.deathParticles.add(ZombieParticleLibrary.ZombieNormal_Arm_3());
		}
		// legs
		int legRand = (int) (Math.random() * 100);
		if (legRand < 33)
		{
			z.legsStillR = img("ZombieNormal_Legs_Still_1R.png");
			z.legsStillL = img("ZombieNormal_Legs_Still_1L.png");
			z.legsRunningR = img("ZombieNormal_RunningSpriteSheet_1R.png");
			z.legsRunningL = img("ZombieNormal_RunningSpriteSheet_1L.png");
			z.legsAirR = img("ZombieNormal_AirLegs_1R.png");
			z.legsAirL = img("ZombieNormal_AirLegs_1L.png");
		}
		else if (legRand < 67)
		{
			z.legsStillR = img("ZombieNormal_Legs_Still_2R.png");
			z.legsStillL = img("ZombieNormal_Legs_Still_2L.png");
			z.legsRunningR = img("ZombieNormal_RunningSpriteSheet_2R.png");
			z.legsRunningL = img("ZombieNormal_RunningSpriteSheet_2L.png");
			z.legsAirR = img("ZombieNormal_AirLegs_2R.png");
			z.legsAirL = img("ZombieNormal_AirLegs_2L.png");

		}
		else
		{
			z.legsStillR = img("ZombieNormal_Legs_Still_3R.png");
			z.legsStillL = img("ZombieNormal_Legs_Still_3L.png");
			z.legsRunningR = img("ZombieNormal_RunningSpriteSheet_3R.png");
			z.legsRunningL = img("ZombieNormal_RunningSpriteSheet_3L.png");
			z.legsAirR = img("ZombieNormal_AirLegs_3R.png");
			z.legsAirL = img("ZombieNormal_AirLegs_3L.png");

		}

		if (Math.random() < .75)
			z.deathParticles.add(ZombieParticleLibrary.Brain());
		if (Math.random() < .5)
			z.deathParticles.add(ZombieParticleLibrary.Heart());
		if (Math.random() < .4)
			z.deathParticles.add(ZombieParticleLibrary.Spleen());

		z.deathParticles.add(ZombieParticleLibrary.Bone1());
		z.deathParticles.add(ZombieParticleLibrary.Bone2());
		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone1());
		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone2());
		if (Math.random() < .6)
			z.deathParticles.add(ZombieParticleLibrary.Bone3());
		if (Math.random() < .6)
			z.deathParticles.add(ZombieParticleLibrary.Spine());

		z.jumpSpeed = 700;
		z.walkSpeed = 200;
		z.penetration = 20;
		z.healVal = 4;
		z.sprintMultiplier = 2.3;
		z.intersectPlayers = true;
		z.damage = 35;

		z.hitParticles.add(ParticleLibrary.Blood1());
		z.deathParticles.add(ParticleLibrary.Blood1());
		z.particlesToAddOnDeath = 12;

		z.legFrames = 15;
		z.legWidth = 100;
		z.legHeight = 115;

		return z;
	}
	public static Zombie ZombieFast()
	{
		Zombie z = new Zombie();
		z.construct();
		z.width = 30;
		z.height = 100;
		z.wireframes[0] = Library.createWF(z.width, z.height);
		z.wfOffset[0] = new Vect(0, -z.height / 2);
		wireframe(z, z.width, z.height);

		z.name = "Fast_Zombie";

		z.fullHealth = 25;
		z.health = 0 + z.fullHealth;
		z.x = 0;
		z.y = 0;

		int headRand = (int) (100 * Math.random());
		if (headRand < 50)
		{
			z.headR = img("ZombieSmall_Head_1R.png");
			z.headL = img("ZombieSmall_Head_1L.png");
			z.torsoR = img("ZombieSmall_Body_1R.png");
			z.torsoL = img("ZombieSmall_Body_1L.png");
			if (headRand < 25)
			{
				z.legsStillR = img("ZombieSmall_Legs_Still_R1.png");
				z.legsStillL = img("ZombieSmall_Legs_Still_L1.png");
			}
			else
			{
				z.legsStillR = img("ZombieSmall_Legs_Still_R1v2.png");
				z.legsStillL = img("ZombieSmall_Legs_Still_L1v2.png");
			}
			z.deathParticles.add(ZombieParticleLibrary.ZombieSmall_Head_1());
			z.deathParticles.add(ZombieParticleLibrary.ZombieBody_Small_1());
		}
		else
		{
			z.headR = img("ZombieSmall_Head_2R.png");
			z.headL = img("ZombieSmall_Head_2L.png");
			z.torsoR = img("ZombieSmall_Body_2R.png");
			z.torsoL = img("ZombieSmall_Body_2L.png");
			if (headRand < 75)
			{
				z.legsStillR = img("ZombieSmall_Legs_Still_R1.png");
				z.legsStillL = img("ZombieSmall_Legs_Still_L1.png");
			}
			else
			{
				z.legsStillR = img("ZombieSmall_Legs_Still_R2.png");
				z.legsStillL = img("ZombieSmall_Legs_Still_L2.png");
			}
			z.deathParticles.add(ZombieParticleLibrary.ZombieSmall_Head_2());
			z.deathParticles.add(ZombieParticleLibrary.ZombieBody_Small_2());
		}

		z.armR = img("");
		z.armL = img("");

		z.legsRunningR = img("");
		z.legsRunningL = img("");

		if (Math.random() < .75)
			z.deathParticles.add(ZombieParticleLibrary.Brain());
		if (Math.random() < .5)
			z.deathParticles.add(ZombieParticleLibrary.Heart());

		z.deathParticles.add(ZombieParticleLibrary.Bone3());
		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone3());

		z.jumpSpeed = 700;
		z.walkSpeed = 750;
		z.penetration = 10;
		z.healVal = 1;
		z.sprintMultiplier = 3;
		z.intersectPlayers = true;
		z.damage = 12;

		z.hitParticles.add(ParticleLibrary.Blood1());
		z.deathParticles.add(ParticleLibrary.Blood1());
		z.particlesToAddOnDeath = 6;
		return z;
	}
	public static Zombie ZombieStrong()
	{
		Zombie z = new Zombie();
		z.construct();

		z.width = 50;
		z.height = 180;
		z.wireframes[0] = Library.createWF(z.width, z.height);
		z.wfOffset[0] = new Vect(0, -z.height / 2);
		wireframe(z, z.width, z.height);

		z.name = "Strong_Zombie";

		z.fullHealth = 300;
		z.health = 0 + z.fullHealth;
		z.x = 0;
		z.y = 0;

		z.headR = img("");
		z.headL = img("");
		z.torsoR = img("");
		z.torsoL = img("");

		z.armR = img("");
		z.armL = img("");

		z.legsStillR = img("");
		z.legsStillL = img("");
		z.legsRunningR = img("");
		z.legsRunningL = img("");
		if (Math.random() < .75)
			z.deathParticles.add(ZombieParticleLibrary.Brain());
		if (Math.random() < .5)
			z.deathParticles.add(ZombieParticleLibrary.Heart());
		if (Math.random() < .4)
			z.deathParticles.add(ZombieParticleLibrary.Spleen());

		z.deathParticles.add(ZombieParticleLibrary.Bone1());
		z.deathParticles.add(ZombieParticleLibrary.Bone1());
		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone1());
		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone1());
		if (Math.random() < .6)
			z.deathParticles.add(ZombieParticleLibrary.Spine());
		z.jumpSpeed = 700;
		z.walkSpeed = 200;
		z.penetration = 60;
		z.healVal = 10;
		z.sprintMultiplier = 2.3;
		z.intersectPlayers = true;
		z.damage = 60;

		z.hitParticles.add(ParticleLibrary.Blood1());
		z.deathParticles.add(ParticleLibrary.Blood1());
		z.particlesToAddOnDeath = 18;
		return z;
	}
	public static Zombie ZombieMega()
	{
		Zombie z = new Zombie();
		z.construct();

		z.width = 80;
		z.height = 200;
		z.wireframes[0] = Library.createWF(z.width, z.height);
		z.wfOffset[0] = new Vect(0, -z.height / 2);
		wireframe(z, z.width, z.height);

		z.name = "Mega_Zombie";

		z.fullHealth = 1200;
		z.health = 0 + z.fullHealth;
		z.x = 0;
		z.y = 0;

		z.headR = img("");
		z.headL = img("");
		z.torsoR = img("");
		z.torsoL = img("");

		z.armR = img("");
		z.armL = img("");

		z.legsStillR = img("");
		z.legsStillL = img("");
		z.legsRunningR = img("");
		z.legsRunningL = img("");

		if (Math.random() < .75)
			z.deathParticles.add(ZombieParticleLibrary.Brain());
		if (Math.random() < .5)
			z.deathParticles.add(ZombieParticleLibrary.Heart());
		if (Math.random() < .4)
			z.deathParticles.add(ZombieParticleLibrary.Spleen());

		z.deathParticles.add(ZombieParticleLibrary.Bone1());
		z.deathParticles.add(ZombieParticleLibrary.Bone2());
		z.deathParticles.add(ZombieParticleLibrary.Bone2());
		z.deathParticles.add(ZombieParticleLibrary.Bone1());
		z.deathParticles.add(ZombieParticleLibrary.Bone1());

		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone1());
		if (Math.random() < .3)
			z.deathParticles.add(ZombieParticleLibrary.Bone2());
		if (Math.random() < .6)
			z.deathParticles.add(ZombieParticleLibrary.Spine());

		z.jumpSpeed = 700;
		z.walkSpeed = 150;
		z.penetration = 500;
		z.healVal = 20;
		z.sprintMultiplier = 4;
		z.intersectPlayers = true;
		z.damage = 150;

		z.hitParticles.add(ParticleLibrary.Blood1());
		z.deathParticles.add(ParticleLibrary.Blood1());
		z.particlesToAddOnDeath = 25;
		return z;
	}
	public static Zombie ZombieExplosive()
	{
		Zombie z = new Zombie();
		z.construct();

		z.width = 90;
		z.height = 165;
		z.wireframes[0] = Library.createWF(z.width, z.height);
		z.wfOffset[0] = new Vect(0, -z.height / 2);
		wireframe(z, z.width, z.height);

		z.name = "Explosive_Zombie";

		z.fullHealth = 40;
		z.health = 0 + z.fullHealth;
		z.x = 0;
		z.y = 0;
		int randBody = (int) (Math.random() * 100);
		if (randBody < 60)
		{
			z.headR = img("ZombieExplosive_Head_R1.png");
			z.headL = img("ZombieExplosive_Head_L1.png");
			z.torsoR = img("ZombieExplosive_Body_R1.png");
			z.torsoL = img("ZombieExplosive_Body_L1.png");

			z.legsStillR = img("ZombieExplosive_Legs_R1.png");
			z.legsStillL = img("ZombieExplosive_Legs_L1.png");
			z.legsRunningR = img("");
			z.legsRunningL = img("");
		}
		else
		{
			z.headR = img("ZombieExplosive_Head_R2.png");
			z.headL = img("ZombieExplosive_Head_L2.png");
			z.torsoR = img("ZombieExplosive_Body_R2.png");
			z.torsoL = img("ZombieExplosive_Body_L2.png");

			z.legsStillR = img("ZombieExplosive_Legs_R2.png");
			z.legsStillL = img("ZombieExplosive_Legs_L2.png");
			z.legsRunningR = img("");
			z.legsRunningL = img("");

		}
		z.armR = img("");
		z.armL = img("");

		z.jumpSpeed = 700;
		z.walkSpeed = 150;
		z.penetration = 500;
		z.healVal = 20;
		z.sprintMultiplier = 4;
		z.intersectPlayers = true;
		z.damage = 30;

		z.hitParticles.add(ParticleLibrary.Blood1());
		z.deathParticles.add(ParticleLibrary.Blood1());
		z.deathParticles.add(ZombieParticleLibrary.Bone2());
		z.deathParticles.add(ZombieParticleLibrary.Spine());
		z.deathParticles.add(ZombieParticleLibrary.Heart());
		z.deathParticles.add(ZombieParticleLibrary.Spleen());
		z.deathParticles.add(ZombieParticleLibrary.Bone3());
		z.deathParticles.add(ZombieParticleLibrary.Bone3());

		Explosion exp = new Explosion(null, 0, 0, 350, 70000, 500);
		exp.smokeNum = 15;
		exp.smokeLight.add(ParticleLibrary.SmokeMedium1());
		exp.smokeDark.add(ParticleLibrary.SmokeDarkMedium1());
		exp.fire.add(ParticleLibrary.Fire1());
		exp.extraJunk = new ArrayList<Entity>();
		exp.extraJunkCount = 30;
		exp.extraJunk.add(ParticleLibrary.Blood1());
		exp.sound = "explosion3.wav";

		z.deathParticles.add(exp);
		z.particlesToAddOnDeath = 10;
		return z;
	}
	private static void wireframe(Zombie z, int width, int height)
	{
		z.rect = new Rectangle2D.Double(0, 0, width, height);
		z.rect2 = new Rectangle2D.Double(0, 0, width, height);
		z.wireframes[0] = Library.createWF(width, height - 20);// body;
		z.wfOffset[0] = new Vect(0, -(height - 10) / 2);

		Vect[] feetCoords = new Vect[3];
		feetCoords[0] = new Vect(-width, -height / 2);
		feetCoords[1] = new Vect(width, -height / 2);
		feetCoords[2] = new Vect(0, 0);
		z.feet = new Wireframe(feetCoords, 3);
		z.feetOffset = new Vect(0, -5);

		z.feetCheck = Library.createWF(width - .2, height / 2);
		z.feetCheckOffset = new Vect(0, height / 8);

		z.interactArea = Library.createWF(width + 100, height - 40);// attack
																	// area
		z.interactAreaOffset = new Vect(0, -(height - 40) / 2);

		z.head = Library.createWF(25, 30);
		z.headOffset = new Vect(0, -height);
	}
}
