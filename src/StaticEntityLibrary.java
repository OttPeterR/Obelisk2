import java.awt.Image;

public class StaticEntityLibrary extends Library
{

	public static void loadAll(java.util.ArrayList<Entity> ents)
	{
		ents.add(BigRock());
		ents.add(BridgePiece());
		ents.add(WoodPostShort());
		ents.add(WoodPostLong());
		ents.add(BrickWallWhiteSmall());
		ents.add(BrickWallWhiteSmallGray());
		ents.add(BrickWall1());
		ents.add(BrickWall2());
		ents.add(BrickWall3());
		ents.add(BrickWall4());
		ents.add(BrickWall5());
		ents.add(BrickWall6());
		ents.add(WallMed());
		ents.add(WallMedWindow());

		ents.add(DrankMachine());
		ents.add(DrankMachinePurple());
		ents.add(The11UpMachine());
		ents.add(Pipe());
		ents.add(PipeJoint());
		ents.add(PipeJointFlip());
	}

	public static StaticEntity BrickWall1()
	{
		StaticEntity r =
				new StaticEntity("Brick_Wall_Brown", img("brick_wall.png"), "brick_wall.png", createWF(315.0D, 219.0D), new Vect(0, 0), 24000, .3, 450);

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel3());

		r.hitParticles.add(ParticleLibrary.Gravel3());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel6());

		r.hitParticles.add(ParticleLibrary.Gravel6());
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}
	public static StaticEntity BrickWall2()
	{
		StaticEntity r =
				new StaticEntity("Brick_Wall_Red", img("brick_wall_red.png"), "brick_wall_red.png", createWF(315.0D, 219.0D), new Vect(0, 0), 24000, .3, 450);

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel3());

		r.hitParticles.add(ParticleLibrary.Gravel3());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel6());

		r.hitParticles.add(ParticleLibrary.Gravel6());
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}
	public static StaticEntity BrickWall3()
	{
		StaticEntity r = new StaticEntity("Brick_Wall_Gray_Large", img("brick_wall_gray_large.png"), "brick_wall_gray_large.png", createWF(695, 368), new Vect(0, 0), 24000, .3, 450);

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel3());

		r.hitParticles.add(ParticleLibrary.Gravel3());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel6());

		r.hitParticles.add(ParticleLibrary.Gravel6());
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}
	public static StaticEntity BrickWall4()
	{
		StaticEntity r = new StaticEntity("Brick_Wall_White_Large", img("brick_wall_large_white.png"), "brick_wall_large_white.png", createWF(696, 368), new Vect(0, 0), 24000, .3, 450);

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel3());

		r.hitParticles.add(ParticleLibrary.Gravel3());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel6());

		r.hitParticles.add(ParticleLibrary.Gravel6());
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}
	public static StaticEntity BrickWall5()
	{
		StaticEntity r =
				new StaticEntity("Brick_Wall_Brown_Large", img("brick_wall_large.png"), "brick_wall_large.png", createWF(945, 438), new Vect(0, 0), 24000, .3, 450);

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel3());

		r.hitParticles.add(ParticleLibrary.Gravel3());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel6());

		r.hitParticles.add(ParticleLibrary.Gravel6());
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}
	public static StaticEntity BrickWall6()
	{
		StaticEntity r =
				new StaticEntity("Brick_Wall_Red_Large", img("brick_wall_red_large.png"), "brick_wall_red_large.png", createWF(945, 438), new Vect(0, 0), 24000, .3, 450);

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel1());

		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel2());
		r.hitParticles.add(ParticleLibrary.Gravel3());

		r.hitParticles.add(ParticleLibrary.Gravel3());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel4());
		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel5());

		r.hitParticles.add(ParticleLibrary.Gravel6());

		r.hitParticles.add(ParticleLibrary.Gravel6());
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow1(.5));
		r.hitParticles.add(ParticleLibrary.SparkYellow2(.5));
		return r;
	}

	public static StaticEntity BrickWallWhiteSmall()
	{
		String name = "Brick_Wall_White_Small";
		Image img = img("cinderblock_wall.png");
		String imgstring = "cinderblock_wall.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(174, 00000);
		coords[2] = new Vect(174, 92);
		coords[3] = new Vect(00000, 92);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(00000, 00000);
		double health = 2500;
		double time = -1;
		double penetration = 180;

		double friction = .6;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;

		s.hitParticles.add(ParticleLibrary.Gravel1());

		s.hitParticles.add(ParticleLibrary.Gravel4());

		return s;
	}
	public static StaticEntity BrickWallWhiteSmallGray()
	{
		String name = "Brick_Wall_Gray_Small";
		Image img = img("brick_wall_small_gray.png");
		String imgstring = "brick_wall_small_gray.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(174, 00000);
		coords[2] = new Vect(174, 92);
		coords[3] = new Vect(00000, 92);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(00000, 00000);
		double health = 2500;
		double time = -1;
		double penetration = 180;

		double friction = .6;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;

		s.hitParticles.add(ParticleLibrary.Gravel1());

		s.hitParticles.add(ParticleLibrary.Gravel4());

		return s;
	}
	public static StaticEntity WoodPostLong()
	{
		String name = "Wood_Post";
		Image img = img("longwoodboard_horizontal.png");
		String imgstring = "longwoodboard_horizontal.png";
		Wireframe wf = createWF(500, 35);
		double health = -1;
		double time = -1;
		double penetration = 42;

		double friction = .6;
		double bounce = .3;
		Vect wfOffset = new Vect(0, 0);
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;

		s.hitParticles.add(ParticleLibrary.WoodChipDark1());
		s.hitParticles.add(ParticleLibrary.WoodChipDark2());
		s.hitParticles.add(ParticleLibrary.WoodChipDark3());
		s.hitParticles.add(ParticleLibrary.WoodChipDark4());
		s.particlesToAddOnDeath = 50;
		return s;
	}
	public static StaticEntity WoodPostShort()
	{
		String name = "Wood_Post_Short";
		Image img = img("bridge_end.png");
		String imgstring = "bridge_end.png";
		Wireframe wf = createWF(11, 170);
		double health = 60;
		double time = -1;
		double penetration = 16;

		double friction = .6;
		double bounce = .3;
		Vect wfOffset = new Vect(0, 0);
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;
		s.fullHealth = 0 + health;

		s.hitParticles.add(ParticleLibrary.WoodChipDark1());
		s.hitParticles.add(ParticleLibrary.WoodChipDark2());
		s.hitParticles.add(ParticleLibrary.WoodChipDark3());
		s.hitParticles.add(ParticleLibrary.WoodChipDark4());
		s.particlesToAddOnDeath = 25;
		return s;
	}
	public static StaticEntity BridgePiece()
	{
		String name = "Bridge_Piece";
		Image img = img("bridgestart.png");
		String imgstring = "bridgestart.png";
		Wireframe wf = createWF(231, 170);
		double health = -1;
		double time = -1;
		double penetration = 75;

		double friction = .6;
		double bounce = .3;
		Vect wfOffset = new Vect(0, 0);
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;

		s.hitParticles.add(ParticleLibrary.WoodChipDark1());
		s.hitParticles.add(ParticleLibrary.WoodChipDark2());
		s.hitParticles.add(ParticleLibrary.WoodChipDark3());
		s.hitParticles.add(ParticleLibrary.WoodChipDark4());
		return s;
	}
	public static StaticEntity BigRock()
	{
		String name = "Big_Rock";
		Image img = img("bigrock.png");
		String imgstring = "bigrock.png";
		Vect[] coords = new Vect[7];
		coords[0] = new Vect(156, 4);
		coords[1] = new Vect(213, 3);
		coords[2] = new Vect(258, 35);
		coords[3] = new Vect(240, 242);
		coords[4] = new Vect(10, 245);
		coords[5] = new Vect(0, 205);
		coords[6] = new Vect(60, 72);
		Wireframe wf = new Wireframe(coords, 7);
		Vect wfOffset = new Vect(0, -10);
		double health = -1;
		double time = -1;
		double penetration = 260;

		double friction = .7;
		double bounce = 0;
		StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset, friction, bounce);
		s.timeRemaining = time;
		s.penetration = penetration;
		s.health = health;

		s.hitParticles.add(ParticleLibrary.Gravel1());
		s.hitParticles.add(ParticleLibrary.Gravel4());
		s.hitParticles.add(ParticleLibrary.Gravel5());
		s.hitParticles.add(ParticleLibrary.Gravel3());
		return s;
	}

	public static StaticEntity The11UpMachine()
	{
		String name = "11UpMachine";
		Image img = img("11UpMachine.png");
		String imgstring = "11UpMachine.png";
		Wireframe wf = Library.createWF(100, 200);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y -
		double health = -1;
		double time = -1;
		double penetration = 80;

		double friction = .5;
		double bounce = .2;
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
	public static StaticEntity DrankMachine()
	{
		String name = "DrankMachine";
		Image img = img("DrankMachine.png");
		String imgstring = "DrankMachine.png";
		Wireframe wf = Library.createWF(100, 200);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y -
		double health = -1;
		double time = -1;
		double penetration = 80;

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
	public static StaticEntity DrankMachinePurple()
	{
		String name = "DrankMachinePurple";
		Image img = img("DrankMachinePurple.png");
		String imgstring = "DrankMachinePurple.png";
		Wireframe wf = Library.createWF(100, 200);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y -
		double health = -1;
		double time = -1;
		double penetration = 80;

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
	public static StaticEntity WallMed()
	{
		String name = "WallMed";
		Image img = img("WallMed.png");
		String imgstring = "WallMed.png";
		Wireframe wf = createWF(500, 350);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y -
												// up
		double health = -1;
		double time = -1;
		double penetration = 450;

		double friction = .6;
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
	public static StaticEntity WallMedWindow()
	{
		String name = "WallMedWindow";
		Image img = img("WallMedWindow.png");
		String imgstring = "WallMedWindow.png";
		Wireframe wf = createWF(500, 350);

		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y -
												// up
		double health = -1;
		double time = -1;
		double penetration = 450;

		double friction = .6;
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
	public static StaticEntity Pipe()
	{
		String name = "Pipe";
		Image img = img("pipe.png");
		String imgstring = "pipe.png";
		Wireframe wf = createWF(540, 50);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
		double health = -1;
		double time = -1;
		double penetration = 180;
		double friction = .6;
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
	public static StaticEntity PipeJoint()
	{
		String name = "Pipe_Joint";
		Image img = img("pipe_joint.png");
		String imgstring = "pipe_joint.png";
		Wireframe wf = createWF(49, 60);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
		double health = -1;
		double time = -1;
		double penetration = 180;
		double friction = .6;
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
	public static StaticEntity PipeJointFlip()
	{
		String name = "Pipe_Joint2";
		Image img = img("pipe_joint_flip.png");
		String imgstring = "pipe_joint_flip.png";
		Wireframe wf = createWF(49, 60);
		Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
		double health = -1;
		double time = -1;
		double penetration = 180;
		double friction = .6;
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
	// public static StaticEntity name()
	// {
	// String name = "";
	// Image img = img(".png");
	// String imgstring = ".png";
	// Vect[] coords = new Vect[4];
	// coords[0] = new Vect(00000, 00000);
	// coords[1] = new Vect(00000, 00000);
	// coords[2] = new Vect(00000, 00000);
	// coords[3] = new Vect(00000, 00000);
	// Wireframe wf = new Wireframe(coords, 4);
	// Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
	// double health = -1;
	// double time = -1;
	// double penetration = 0;
	// double friction = 0;
	// double bounce = 0;
	// StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset,
	// friction, bounce);
	// s.timeRemaining = time;
	// s.penetration = penetration;
	// s.health = health;
	// s.fullHealth = 0 + health;
	// return s;
	// }
	// public static StaticEntity name()
	// {
	// String name = "";
	// Image img = img(".png");
	// String imgstring = ".png";
	// Vect[] coords = new Vect[4];
	// coords[0] = new Vect(00000, 00000);
	// coords[1] = new Vect(00000, 00000);
	// coords[2] = new Vect(00000, 00000);
	// coords[3] = new Vect(00000, 00000);
	// Wireframe wf = new Wireframe(coords, 4);
	// Vect wfOffset = new Vect(00000, 00000);// - X + ||| y+down y - up
	// double health = -1;
	// double time = -1;
	// double penetration = 0;
	// double friction = 0;
	// double bounce = 0;
	// StaticEntity s = new StaticEntity(name, img, imgstring, wf, wfOffset,
	// friction, bounce);
	// s.timeRemaining = time;
	// s.penetration = penetration;
	// s.health = health;
	// s.fullHealth=0+health;
	// return s;
	// }
}
