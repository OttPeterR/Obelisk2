import java.awt.Image;

public class PhysicsEntityLibrary extends Library
{

	public static void loadAll(java.util.ArrayList<Entity> ents)
	{
		ents.add(WoodCrateSmall());
		ents.add(WoodTable());
		ents.add(TrashCan());
		ents.add(BeachBall());
		ents.add(ExplosiveBarrel());
		ents.add(LampBlue());
		ents.add(NapkinDispencer());
		ents.add(Van());
	}
	public static PhysicsEntity TrashCan()
	{
		Vect[] trashcanCoords = new Vect[4];
		trashcanCoords[0] = new Vect(3.0, 3.0);
		trashcanCoords[1] = new Vect(64.0, 3.0);
		trashcanCoords[2] = new Vect(59.0, 117.0);
		trashcanCoords[3] = new Vect(8.0, 116.0);
		PhysicsEntity r = new PhysicsEntity("Trash_Can", img("trashcan.png"), "trashcan.png", new Wireframe(trashcanCoords, 4), new Vect(0, -2), 185, -1, 80, .3, .6, 40);
		r.deathParticles.add(ParticleLibrary.TrashCanPiece1());
		r.deathParticles.add(ParticleLibrary.TrashCanPiece2());
		r.deathParticles.add(ParticleLibrary.TrashCanPiece3());
		r.deathParticles.add(ParticleLibrary.TrashCanPiece1());
		r.deathParticles.add(ParticleLibrary.TrashCanPiece2());
		r.deathParticles.add(ParticleLibrary.TrashCanPiece3());
		r.deathParticles.add(ParticleLibrary.TrashCanTop());
		r.deathParticles.add(ParticleLibrary.WaterBottle());
		r.deathParticles.add(ParticleLibrary.Burger());
		r.deathParticles.add(ParticleLibrary.PaperBall());
		r.deathParticles.add(ParticleLibrary.WaterBottle());
		r.deathParticles.add(ParticleLibrary.TrashBag1());
		r.deathParticles.add(ParticleLibrary.TrashCup());
		r.deathParticles.add(ParticleLibrary.TrashBag3());
		r.deathParticles.add(ParticleLibrary.Napkin());
		r.deathParticles.add(ParticleLibrary.Napkin());
		r.deathParticles.add(ParticleLibrary.WaterBottle());
		r.deathParticles.add(ParticleLibrary.PaperBall());
		r.deathParticles.add(ParticleLibrary.Napkin());
		r.deathParticles.add(ParticleLibrary.PaperBall());
		r.deathParticles.add(ParticleLibrary.TrashCup());
		r.deathParticles.add(ParticleLibrary.TrashCup());
		r.deathParticles.add(ParticleLibrary.WaterBottle());
		r.deathParticles.add(ParticleLibrary.TrashBag2());
		r.deathParticles.add(ParticleLibrary.Fries());
		r.deathParticles.add(ParticleLibrary.Fries());
		r.deathParticles.add(ParticleLibrary.WaterBottle());
		r.deathParticles.add(ParticleLibrary.Burger());
		r.deathParticles.add(ParticleLibrary.PaperBall());

		r.deathParticles.add(ParticleLibrary.Napkin());
		r.deathParticles.add(ParticleLibrary.FishBone());
		r.particlesToAddOnDeath = 25;

		r.hitParticles.add(ParticleLibrary.Napkin());
		r.hitParticles.add(ParticleLibrary.Napkin());
		r.hitParticles.add(ParticleLibrary.PaperBall());
		r.hitParticles.add(ParticleLibrary.PaperBall());
		r.hitParticles.add(ParticleLibrary.BlackBit1());
		r.hitParticles.add(ParticleLibrary.BlackBit1());
		r.hitParticles.add(ParticleLibrary.BlackBit1());
		r.hitParticles.add(ParticleLibrary.BlackBit2());
		r.hitParticles.add(ParticleLibrary.BlackBit2());
		r.hitParticles.add(ParticleLibrary.BlackBit2());
		r.hitParticles.add(ParticleLibrary.BlackBit3());
		r.hitParticles.add(ParticleLibrary.BlackBit3());
		r.hitParticles.add(ParticleLibrary.BlackBit3());
		r.intersectPlayers = false;
		return r;
	}
	public static PhysicsEntity BeachBall()
	{
		PhysicsEntity b = new PhysicsEntity("Beach_Ball", img("BeachBall.png"), "BeachBall.png", createDefaultWF(13, 38.5), new Vect(.5, 0), 3, -1, 5, .09, .99, 2);
		b.particlesToAddOnDeath = 20;
		b.deathParticles.add(ParticleLibrary.ConfettiRed());
		b.deathParticles.add(ParticleLibrary.ConfettiOrange());
		b.deathParticles.add(ParticleLibrary.ConfettiYellow());
		b.deathParticles.add(ParticleLibrary.ConfettiBlue());
		b.deathParticles.add(ParticleLibrary.ConfettiGreen());

		b.hitParticles.add(ParticleLibrary.ConfettiRed());
		b.hitParticles.add(ParticleLibrary.ConfettiOrange());
		b.hitParticles.add(ParticleLibrary.ConfettiYellow());
		b.hitParticles.add(ParticleLibrary.ConfettiBlue());
		b.hitParticles.add(ParticleLibrary.ConfettiGreen());
		b.intersectPlayers=false;
		return b;
	}
	public static PhysicsEntity WoodCrateSmall()
	{
		PhysicsEntity r = new PhysicsEntity("Wood_Crate_Small", img("WoodCrateSmall.png"), "WoodCrateSmall.png", createWF(100, 100), new Vect(0, 0), 100, -1, 45, .4, .5, 30);
		r.hitParticles.add(ParticleLibrary.WoodChip1());
		// r.hitParticles.add(ParticleLibrary.WoodChip2());
		r.hitParticles.add(ParticleLibrary.WoodChip3());
		r.hitParticles.add(ParticleLibrary.WoodChip4());

		r.deathParticles.add(ParticleLibrary.WoodCratePiece1());
		r.deathParticles.add(ParticleLibrary.WoodCratePiece2());
		r.deathParticles.add(ParticleLibrary.WoodCratePiece3());
		r.deathParticles.add(ParticleLibrary.WoodBoardSmall());
		r.deathParticles.add(ParticleLibrary.WoodBoardSmall());
		r.intersectPlayers=false;
		r.particlesToAddOnDeath = 20;
		return r;
	}
	public static PhysicsEntity Van()
	{
		String name = "Van";
		Image img = img("hippievan.png");
		String imgstring = "hippievan.png";
		Vect[] coords = new Vect[8];
		coords[0] = new Vect(2, 160);
		coords[1] = new Vect(15, 78);
		coords[2] = new Vect(68, 1);
		coords[3] = new Vect(418, 6);
		coords[4] = new Vect(451, 78);
		coords[5] = new Vect(458, 169);
		coords[6] = new Vect(340, 206);
		coords[7] = new Vect(69, 199);
		Wireframe wf = new Wireframe(coords, 8);
		Vect wfOff = new Vect(0, 0);
		double health = 8000;
		double time = -1;
		double mass = 3400;
		double friciton = .7;
		double bounce = .1;
		double penetration = 780;

		PhysicsEntity p = new PhysicsEntity(name, img, imgstring, wf, wfOff, health, time, mass, friciton, bounce, penetration);
		p.hitParticles.add(ParticleLibrary.SparkYellow1(.8));
		p.hitParticles.add(ParticleLibrary.SparkYellow2(.8));
		p.hitParticles.add(ParticleLibrary.SparkOrange1(.8));
		p.hitParticles.add(ParticleLibrary.SparkOrange2(.8));
		p.hitParticles.add(ParticleLibrary.BlackBit1());
		p.hitParticles.add(ParticleLibrary.BlackBit1());
		p.hitParticles.add(ParticleLibrary.BlackBit1());
		// p.hitParticles.add(ParticleLibrary.Burger());
		p.particlesToAddOnDeath = 200;
		return p;
	}
	public static PhysicsEntity LampBlue()
	{
		String name = "LampBlue";
		Image img = img("LampBlue.png");
		String imgstring = "LampBlue.png";
		Vect[] coords = new Vect[6];
		coords[0] = new Vect(10, 0000);
		coords[1] = new Vect(30, 0000);
		coords[2] = new Vect(38, 35);
		coords[3] = new Vect(29, 79);
		coords[4] = new Vect(10, 79);
		coords[5] = new Vect(0, 35);
		Wireframe wf = new Wireframe(coords, 6);
		Vect wfOff = new Vect(-1, -2);
		double health = 8;
		double time = -1;
		double mass = 6;
		double friciton = .6;
		double bounce = .3;
		double penetration = 15;

		PhysicsEntity p = new PhysicsEntity(name, img, imgstring, wf, wfOff, health, time, mass, friciton, bounce, penetration);
		p.intersectPlayers = false;
		p.deathParticles.add(ParticleLibrary.LampShadeDestructed());
		p.deathParticles.add(ParticleLibrary.LampShardBlue1());
		p.deathParticles.add(ParticleLibrary.LampShardBlue2());
		p.deathParticles.add(ParticleLibrary.LampShardBlue3());
		p.deathParticles.add(ParticleLibrary.LampShardBlue4());
		p.deathParticles.add(ParticleLibrary.LightSocketBroken1());
		p.intersectPlayers=false;
		p.particlesToAddOnDeath = 1;
		return p;
	}
	public static PhysicsEntity NapkinDispencer()
	{
		String name = "NapkinDispencer";
		Image img = img("NapkinDispencer.png");
		String imgstring = "NapkinDispencer.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0000, 0000);
		coords[1] = new Vect(20, 0000);
		coords[2] = new Vect(20, 35);
		coords[3] = new Vect(0000, 35);
		Wireframe wf = new Wireframe(coords, 4);
		Vect wfOff = new Vect(0, 0);
		double health = 8;
		double time = -1;
		double mass = 6;
		double friciton = .6;
		double bounce = .4;
		double penetration = 10;

		PhysicsEntity p = new PhysicsEntity(name, img, imgstring, wf, wfOff, health, time, mass, friciton, bounce, penetration);
		p.deathParticles.add(ParticleLibrary.Napkin());
		p.deathParticles.add(ParticleLibrary.Napkin());
		p.deathParticles.add(ParticleLibrary.Napkin());
		p.deathParticles.add(ParticleLibrary.Napkin());
		p.particlesToAddOnDeath = 15;
		p.intersectPlayers = false;
p.intersectPlayers=false;
		p.hitParticles.add(ParticleLibrary.Napkin());
		p.hitParticles.add(ParticleLibrary.SparkYellow1(.6));
		return p;
	}
	public static Entity ExplosiveBarrel()
	{
		String name = "ExplosiveBarrel";
		Image img = img("ExplosiveBarrel.png");
		String imgstring = "ExplosiveBarrel.png";
		Wireframe wf = createWF(100, 136);
		Vect wfOff = new Vect(0, 0);
		double health = 80;
		double time = -1;
		double mass = 300;
		double friciton = .7;
		double bounce = 0;
		double penetration = 80;

		PhysicsEntity p = new PhysicsEntity(name, img, imgstring, wf, wfOff,
				health, time, mass, friciton, bounce, penetration);
		Explosion exp = new Explosion(null, 0, 0, 350, 70000, 1000);
		exp.smokeNum = 15;
		exp.smokeLight.add(ParticleLibrary.SmokeMedium1());
		exp.smokeDark.add(ParticleLibrary.SmokeDarkMedium1());
		exp.fire.add(ParticleLibrary.Fire1());
		exp.sound = "explosion3.wav";
		p.deathParticles.add(exp);

		p.particlesToAddOnDeath = 10;
		p.hitParticles.add(ParticleLibrary.SparkYellow1(1.7));
		p.hitParticles.add(ParticleLibrary.SparkYellow2(2.1));
		p.hitParticles.add(ParticleLibrary.SparkOrange1(1.7));
		p.hitParticles.add(ParticleLibrary.SparkOrange2(2));
		p.hitParticles.add(ParticleLibrary.SparkRed1(1.7));
		p.intersectPlayers=false;
		return p;
	}
	public static PhysicsEntity WoodTable()
	{
		String name = "WoodTable";
		Image img = img("WoodTable.png");
		String imgstring = "WoodTable.png";
		Vect[] leg1Coords = new Vect[3];
		leg1Coords[0] = new Vect(4, 9);
		leg1Coords[1] = new Vect(11, 9);
		leg1Coords[2] = new Vect(5, 84);
		Wireframe leg1WF = new Wireframe(leg1Coords, 3);

		Vect[] leg2Coords = new Vect[3];
		leg2Coords[0] = new Vect(138, 9);
		leg2Coords[1] = new Vect(145, 9);
		leg2Coords[2] = new Vect(144, 84);
		Wireframe leg2WF = new Wireframe(leg2Coords, 3);

		Wireframe tableBody = createWF(146, 10);

		Wireframe[] frames = new Wireframe[3];
		frames[0] = tableBody;
		frames[1] = leg1WF;
		frames[2] = leg2WF;

		Vect[] wfOffsets = new Vect[3];
		wfOffsets[0] = new Vect(0, -37);// body
		wfOffsets[1] = new Vect(-68, -7);// leftLeg
		wfOffsets[2] = new Vect(66, -7);// rightLeg

		double health = 60;
		double time = -1;
		double mass = 25;
		double friciton = .6;
		double bounce = .1;
		double penetration = 30;

		PhysicsEntity p = new PhysicsEntity(name, img, imgstring, frames, wfOffsets, 3,
				health, time, mass, friciton, bounce);
		p.penetration = penetration;
		// p.deathParticles.add(ParticleLibrary);
		p.particlesToAddOnDeath = 20;
		p.hitParticles.add(ParticleLibrary.WoodChipDark1());
		p.hitParticles.add(ParticleLibrary.WoodChipDark2());
		p.hitParticles.add(ParticleLibrary.WoodChipDark3());
		p.hitParticles.add(ParticleLibrary.WoodChipDark4());
		p.intersectPlayers=false;
		return p;
	}
	// public static PhysicsEntity namehere()
	// {
	// String name = "";
	// Image img = img(".png");
	// String imgstring = ".png";
	// Vect[] coords = new Vect[6];
	// coords[0] = new Vect(0000, 0000);
	// coords[1] = new Vect(0000, 0000);
	// coords[2] = new Vect(0000, 0000);
	// coords[3] = new Vect(0000, 0000);
	// coords[4] = new Vect(0000, 0000);
	// coords[5] = new Vect(0000, 0000);
	// Wireframe wf = new Wireframe(coords, 6);
	// Vect wfOff = new Vect(0, 0);
	// double health = -1;
	// double time = -1;
	// double mass = 0;
	// double friciton = 0;
	// double bounce = 1;
	// double penetration = 0;
	//
	// PhysicsEntity p = new PhysicsEntity(name, img, imgstring, wf, wfOff,
	// health, time, mass, friciton, bounce, penetration);
	// p.deathParticles.add(ParticleLibrary);
	// p.particlesToAddOnDeath=0;
	// p.hitParticles.add(ParticleLibrary);
	// return p;
	// }
}
