import java.awt.Image;
import java.util.ArrayList;

public class SceneEntityLibrary extends Library
{

	public static void loadAll(ArrayList<Entity> ents)
	{
		ents.add(Tree1());
		ents.add(LightBulb1());
		// ents.add(ConfettiCannon());
		ents.add(WallMed1());
		ents.add(WallMedDestructed1());
		ents.add(WallMed2());
		ents.add(WallMedDestructed2());
		ents.add(WallMed3());
		ents.add(WallMedDestructed3());
		ents.add(WallMed4());
		ents.add(WallMedDestructed4());
		ents.add(WallMed5());
		ents.add(WallMedDestructed5());
		ents.add(CracksBig());
		ents.add(CracksMed());
		ents.add(Fence1());
		ents.add(FoodDinoSignLarge());
		ents.add(AirConditioner());
		ents.add(FanSmall4());
		ents.add(FanSmall8());

	}
	public static StaticEntity AirConditioner()
	{
		String name = "Air_Conditioner";
		Image img = img("Air_Conditioner.png");
		String imgstring = "Air_Conditioner.png";
		Wireframe wf = createWF(199, 112);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
		double health = -1;
		double time = -1;
		double penetration = 80;
		double friction = .8;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset,
				friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.hitParticles.add(ParticleLibrary.SparkOrange1(.7));
		s.hitParticles.add(ParticleLibrary.SparkOrange2(.5));
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return s;
	}
	public static StaticEntity FanSmall4()
	{
		String name = "Fan_Small_4";
		Image img = img("Fan_Small_4.png");
		String imgstring = "Fan_Small_4.png";
		Wireframe wf = createDefaultWF(5, 19);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
		double health = -1;
		double time = -1;
		double penetration = 10;
		double friction = .5;
		double bounce = .3;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset,
				friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.hitParticles.add(ParticleLibrary.SparkOrange1(.7));
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity FanSmall8()
	{
		String name = "Fan_Small_8";
		Image img = img("Fan_Small_8.png");
		String imgstring = "Fan_Small_8.png";
		Wireframe wf = createDefaultWF(5, 19);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
		double health = -1;
		double time = -1;
		double penetration = 10;
		double friction = .5;
		double bounce = .3;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset,
				friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.hitParticles.add(ParticleLibrary.SparkOrange1(.7));
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity FoodDinoSignLarge()
	{
		String name = "FoodDinoSignLarge";
		Image img = img("FoodDinoSignLarge.png");
		String imgstring = "FoodDinoSignLarge.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(307, 00000);
		coords[2] = new Vect(212, 606);
		coords[3] = new Vect(100, 606);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = -1;
		double penetration = 0;

		double friction = 0;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.hitParticles.add(ParticleLibrary.SparkYellow2(.9));
		s.hitParticles.add(ParticleLibrary.SparkOrange1(.7));
		s.hitParticles.add(ParticleLibrary.BlackBit1());
		s.hitParticles.add(ParticleLibrary.BlackBit2());
		return s;
	}
	public static StaticEntity Fence1()
	{
		StaticEntity r = new StaticEntity("Fence1", img("Fence1.png"), "Fence1.png", createWF(489, 350), new Vect(0, 0), -1, .3, 450);

		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}
	public static StaticEntity Tree1()
	{
		Vect[] tree1Trunk = new Vect[5];
		tree1Trunk[0] = new Vect(152, 500);
		tree1Trunk[1] = new Vect(155, 400);
		tree1Trunk[2] = new Vect(178, 237);
		tree1Trunk[3] = new Vect(202, 237);
		tree1Trunk[4] = new Vect(201, 500);

		Vect[] tree1Branches = new Vect[8];
		tree1Branches[0] = new Vect(161, 237);
		tree1Branches[1] = new Vect(53, 132);
		tree1Branches[2] = new Vect(35, 64);
		tree1Branches[3] = new Vect(134, 0);
		tree1Branches[4] = new Vect(297, 0);
		tree1Branches[5] = new Vect(350, 90);
		tree1Branches[6] = new Vect(320, 178);
		tree1Branches[7] = new Vect(204, 237);

		Wireframe[] treeWf = new Wireframe[2];
		treeWf[0] = new Wireframe(tree1Trunk, 5);
		treeWf[1] = new Wireframe(tree1Branches, 8);

		Vect[] treeWfVects = new Vect[2];
		treeWfVects[0] = new Vect(-8, 123);// trunk
		treeWfVects[1] = new Vect(7, -132);// branches

		StaticEntity s = new StaticEntity("Tree_1", img("tree1.png"), "tree1", treeWf, treeWfVects, 2);
		s.hitParticles.add(ParticleLibrary.WoodChipDark1());
		s.hitParticles.add(ParticleLibrary.WoodChipDark2());
		s.hitParticles.add(ParticleLibrary.WoodChipDark3());
		s.hitParticles.add(ParticleLibrary.WoodChipDark4());
		s.penetration = 650;
		return s;
	}
	public static Spawner ConfettiCannon()
	{
		String name = "Confetti_Cannon";
		Image img = img("ConfettiCannon.png");
		String imgname = "";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 65);
		coords[1] = new Vect(148, 0);
		coords[2] = new Vect(148, 115);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOff = new Vect(25, 0);
		ArrayList<Entity> ents = new ArrayList<Entity>();
		// ents.add(ParticleLibrary.ConfettiGreen());
		// ents.add(ParticleLibrary.ConfettiRed());
		// ents.add(ParticleLibrary.ConfettiOrange());
		// ents.add(ParticleLibrary.ConfettiYellow());
		// ents.add(ParticleLibrary.ConfettiBlue());
		ents.add(PhysicsEntityLibrary.WoodCrateSmall());
		Vect speedOfSpawned = new Vect(300, 0);
		double rateOfSpawns = 20;
		double angleErr = Math.toRadians(80);
		int numToAdd = 20;
		int spawnsLeft = -30;
		Spawner s = new Spawner(name, img, imgname, wireframe, wfOff, ents, speedOfSpawned, rateOfSpawns, numToAdd, spawnsLeft);
		s.angleError = angleErr;
		s.addPos = new Vect(40, 0);
		return s;
	}
	public static StaticEntity LightBulb1()
	{
		String name = "LightBulb1";
		Image img = img("LightBulb1.png");
		String imgstring = "LightBulb1.png";
		Vect[] coords = new Vect[5];
		coords[0] = new Vect(2, 0);
		coords[1] = new Vect(2, 17);
		coords[2] = new Vect(8, 22);
		coords[3] = new Vect(14, 17);
		coords[4] = new Vect(14, 0);
		Wireframe wf = new Wireframe(coords, 5);
		Vect wfOffset = new Vect(00000, 00000);
		double health = 4;
		double time = -1;
		double penetration = 4;
		double friction = 0;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = health;
		s.hitParticles.add(ParticleLibrary.SparkYellow1(1.5));
		s.hitParticles.add(ParticleLibrary.SparkYellow1(1.5));
		s.hitParticles.add(ParticleLibrary.SparkYellow1(1.5));
		s.hitParticles.add(ParticleLibrary.SparkYellow2(1.5));
		s.deathParticles.add(ParticleLibrary.SmokeSmall1());
		s.deathParticles.add(ParticleLibrary.SmokeSmall1());
		s.deathParticles.add(ParticleLibrary.LightSocketBroken1());
		s.deathParticles.add(ParticleLibrary.GlassPieceSmall1());
		s.deathParticles.add(ParticleLibrary.GlassPieceSmall1());
		s.deathParticles.add(ParticleLibrary.GlassPieceSmall2());
		s.deathParticles.add(ParticleLibrary.GlassPieceSmall2());

		s.particlesToAddOnDeath = 20;
		return s;

	}
	public static StaticEntity WallMedDestructed1()
	{
		String name = "WallMedDestructed1";
		Image img = img("WallMedDestructed1.png");
		String imgstring = "WallMedDestructed1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(60, 0);
		coords[2] = new Vect(60, 339);
		coords[3] = new Vect(0, 339);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(-21, 0);// - X + ||| y+down y -
											// up
		double health = -1;
		double time = -1;
		double penetration = 200;
		double friction = .8;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity WallMedDestructed2()
	{
		String name = "WallMedDestructed2";
		Image img = img("WallMedDestructed2.png");
		String imgstring = "WallMedDestructed2.png";
		Vect[] coords = new Vect[5];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(27, 1);
		coords[2] = new Vect(170, 66);
		coords[3] = new Vect(213, 125);
		coords[4] = new Vect(0, 125);
		Wireframe wf = new Wireframe(coords, 5);
		Vect wfOffset = new Vect(-25, 0);// - X + ||| y+down y -
											// up
		double health = -1;
		double time = -1;
		double penetration = 200;
		double friction = .8;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity WallMedDestructed3()
	{
		String name = "WallMedDestructed3";
		Image img = img("WallMedDestructed3.png");
		String imgstring = "WallMedDestructed3.png";
		Vect[] coords = new Vect[6];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(33, 0);
		coords[2] = new Vect(77, 38);
		coords[3] = new Vect(115, 110);
		coords[4] = new Vect(115, 164);
		coords[5] = new Vect(0, 164);
		Wireframe wf = new Wireframe(coords, 6);
		Vect wfOffset = new Vect(-1.5, -2.5);// - X + ||| y+down y -
		// up
		double health = -1;
		double time = -1;
		double penetration = 200;

		double friction = .8;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity WallMedDestructed4()
	{
		String name = "WallMedDestructed4";
		Image img = img("WallMedDestructed4.png");
		String imgstring = "WallMedDestructed4.png";
		Vect[] coords = new Vect[5];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(37, 0);
		coords[2] = new Vect(161, 73); // left side
		coords[3] = new Vect(161, 155);
		coords[4] = new Vect(0, 155);
		Wireframe wf0 = new Wireframe(coords, 5);
		Vect wfOffset = new Vect(-97, 0);// - X + ||| y+down y -
											// up

		Vect[] coords1 = new Vect[5];
		coords1[0] = new Vect(334, 0);
		coords1[1] = new Vect(285, 8);
		coords1[2] = new Vect(160, 73); // right side
		coords1[3] = new Vect(160, 155);
		coords1[4] = new Vect(334, 155);
		Wireframe wf1 = new Wireframe(coords1, 5);
		Vect wfOffset1 = new Vect(88, 0);// - X + ||| y+down y -
											// up

		Wireframe[] wfs = new Wireframe[2];
		wfs[0] = wf0;
		wfs[1] = wf1;

		Vect[] wfOffsets = new Vect[2];
		wfOffsets[0] = wfOffset;
		wfOffsets[1] = wfOffset1;

		double health = -1;
		double time = -1;
		double penetration = 200;

		double friction = .8;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wfs, wfOffsets, 2, -1, friction);
		s.timeRemaining = time;
		s.bounce = bounce;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity WallMedDestructed5()
	{
		String name = "WallMedDestructed5";
		Image img = img("WallMedDestructed5.png");
		String imgstring = "WallMedDestructed5.png";
		Vect[] coords = new Vect[5];
		coords[0] = new Vect(0, 164);
		coords[1] = new Vect(0, 100);
		coords[2] = new Vect(160, 0);
		coords[3] = new Vect(307, 0);
		coords[4] = new Vect(307, 164);
		Wireframe wf = new Wireframe(coords, 5);
		Vect wfOffset = new Vect(0, 2);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 200;
		double friction = .8;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		return s;
	}
	public static StaticEntity WallMed1()
	{
		String name = "WallMed1";
		Image img = img("WallMed1.png");
		String imgstring = "WallMed1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(100, 0);
		coords[2] = new Vect(100, 339);
		coords[3] = new Vect(0, 339);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 280;

		double friction = .6;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.damageThreshold = 35;
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.deathParticles.add(ParticleLibrary.Gravel1());
		s.deathParticles.add(ParticleLibrary.Gravel4());
		s.particlesToAddOnDeath = 25;
		return s;
	}
	public static StaticEntity WallMed2()
	{
		String name = "WallMed2";
		Image img = img("WallMed2.png");
		String imgstring = "WallMed2.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 0);
		coords[1] = new Vect(213, 0);
		coords[2] = new Vect(213, 125);
		coords[3] = new Vect(0, 125);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 280;

		double friction = .6;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.damageThreshold = 35;
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.deathParticles.add(ParticleLibrary.Gravel1());
		s.deathParticles.add(ParticleLibrary.Gravel4());
		s.particlesToAddOnDeath = 25;
		return s;
	}
	public static StaticEntity WallMed3()
	{
		String name = "WallMed3";
		Image img = img("WallMed3.png");
		String imgstring = "WallMed3.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(115, 0);
		coords[2] = new Vect(115, 164);
		coords[3] = new Vect(0, 164);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 280;

		double friction = .6;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.damageThreshold = 35;
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.deathParticles.add(ParticleLibrary.Gravel1());
		s.deathParticles.add(ParticleLibrary.Gravel4());
		s.particlesToAddOnDeath = 25;
		return s;
	}
	public static StaticEntity WallMed4()
	{
		String name = "WallMed4";
		Image img = img("WallMed4.png");
		String imgstring = "WallMed4.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(334, 0);
		coords[2] = new Vect(334, 155);
		coords[3] = new Vect(0, 155);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 280;

		double friction = .6;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.damageThreshold = 35;
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.deathParticles.add(ParticleLibrary.Gravel1());
		s.deathParticles.add(ParticleLibrary.Gravel4());
		s.particlesToAddOnDeath = 25;
		return s;
	}
	public static StaticEntity WallMed5()
	{
		String name = "WallMed5";
		Image img = img("WallMed5.png");
		String imgstring = "WallMed5.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(307, 0);
		coords[2] = new Vect(307, 164);
		coords[3] = new Vect(0, 164);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 280;

		double friction = .6;
		double bounce = .1;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.damageThreshold = 35;
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.SparkYellow1(.7));
		s.deathParticles.add(ParticleLibrary.Gravel1());
		s.deathParticles.add(ParticleLibrary.Gravel4());
		s.particlesToAddOnDeath = 25;
		return s;
	}
	public static StaticEntity CracksBig()
	{
		String name = "CracksBig";
		Image img = img("CracksBig.png");
		String imgstring = "CracksBig.png";

		Wireframe wf = createDefaultWF(5, 50);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 0;

		double friction = 0;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.ghost = true;
		return s;
	}
	public static StaticEntity CracksMed()
	{
		String name = "CracksMed";
		Image img = img("CracksMed.png");
		String imgstring = "CracksMed.png";

		Wireframe wf = createDefaultWF(5, 25);
		Vect wfOffset = new Vect(0, 0);// - X + ||| y+down y -
										// up
		double health = -1;
		double time = -1;
		double penetration = 0;

		double friction = 0;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;
		s.ghost = true;
		return s;
	}

}
