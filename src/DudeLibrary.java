import java.awt.Color;
import java.util.ArrayList;

public class DudeLibrary extends Library
{

	public void loadAll(ArrayList<Entity> ents)
	{}

	public static Dude Russ()
	{
		Dude dude = new Dude();
		dude.construct();
		wireframe(dude);
		dude.name = "Russ_Mosley";
		dude.description.add("The_OG");
		dude.description.add("");
		dude.description.add("Normal_stats");
		dude.fullHealth = 150;
		dude.health = 0 + dude.fullHealth;
		dude.x = 0;
		dude.y = 0;

		dude.headR = img("RussHeadR.png");
		dude.headL = img("RussHeadL.png");

		dude.torsoR = img("RussBodyR.png");
		dude.torsoL = img("RussBodyL.png");

		dude.upperArmR = img("");
		dude.upperArmL = img("");
		dude.lowerArmR = img("");
		dude.lowerArmL = img("");
		dude.elbowR = img("");
		dude.elbowL = img("");
		dude.handTriggerR = img("");
		dude.handTriggerL = img("");
		dude.handHoldR = img("");
		dude.handHoldL = img("");

		dude.legsStillR = img("RussStillR.png");
		dude.legsStillL = img("RussStillL.png");

		dude.legsAirR = img("RussAirLegsRight.png");
		dude.legsAirL = img("RussAirLegsLeft.png");

		dude.legsRunningR = img("RussRunningRightSpriteSheet.png");
		dude.legsRunningL = img("RussRunningLeftSpriteSheet.png");

		dude.jumpSpeed = 700;
		dude.walkSpeed = 400;
		dude.sprintMultiplier = 1.7;

		dude.healVal = 5;
		dude.slowMotion = .15;
		dude.slowTimeLeftFull = 2.3;
		dude.timeUntilSlowMotionRechargeFull = 5;
		dude.gunLimit = 2;

		dude.rightArmColor = Color.DARK_GRAY.darker().darker();
		dude.leftArmColor = Color.DARK_GRAY.darker().darker();

		dude.pickUpWeapon(WeaponLibrary.USP45());

		dude.hitParticles.add(ParticleLibrary.Blood1());
		dude.particlesToAddOnDeath = 20;

		return dude;
	}
	public static Dude Steelway()
	{
		Dude dude = new Dude();
		dude.construct();
		wireframe(dude);

		dude.name = "Sgt._Steelway";
		dude.description.add("The_strongest_option.");
		dude.description.add("");
		dude.description.add("Increased_Health");
		dude.description.add("Increased_Health_Regeneration");
		dude.description.add("Holds_3_Weapons");
		dude.description.add("Weaker_Slow-Motion");
		dude.description.add("Decreased_agility");
		dude.fullHealth = 225;
		dude.health = 0 + dude.fullHealth;
		dude.x = 0;
		dude.y = 0;

		dude.headR = img("SteelwayHeadR.png");
		dude.headL = img("SteelwayHeadL.png");

		dude.torsoR = img("SteelwayBodyR.png");
		dude.torsoL = img("SteelwayBodyL.png");

		dude.upperArmR = img("");
		dude.upperArmL = img("");
		dude.lowerArmR = img("");
		dude.lowerArmL = img("");
		dude.elbowR = img("");
		dude.elbowL = img("");
		dude.handTriggerR = img("");
		dude.handTriggerL = img("");
		dude.handHoldR = img("");
		dude.handHoldL = img("");

		dude.legsStillR = img("SteelwayStillR.png");
		dude.legsStillL = img("SteelwayStillL.png");

		dude.legsAirR = img("SteelwayAirLegsRight.png");
		dude.legsAirL = img("SteelwayAirLegsLeft.png");

		dude.legsRunningR = img("SteelwayRunningRightSpriteSheet.png");
		dude.legsRunningL = img("SteelwayRunningLeftSpriteSheet.png");

		dude.jumpSpeed = 600;
		dude.walkSpeed = 48000;
		dude.sprintMultiplier = 1.6;

		dude.leftArmColor = Color.GREEN.darker().darker().darker().darker().darker().darker().darker();
		dude.rightArmColor = Color.GREEN.darker().darker().darker().darker().darker().darker().darker();

		dude.healVal = 7;
		dude.gunLimit = 3;

		dude.pickUpWeapon(WeaponLibrary.DesertEagle());

		dude.slowMotion = .4;
		dude.slowTimeLeftFull = 1.8;
		dude.timeUntilSlowMotionRechargeFull = 5;

		return dude;
	}
	public static Dude Slick()
	{
		Dude dude = new Dude();
		dude.construct();
		wireframe(dude);
		dude.name = "Slick";
		dude.description.add("Accuracy_is_key.");
		dude.description.add("");
		dude.description.add("Slowest_Slow-Motion");
		dude.description.add("Increased_agility");
		dude.description.add("Slightly_lower_health");

		dude.fullHealth = 120;
		dude.health = 0 + dude.fullHealth;
		dude.x = 0;
		dude.y = 0;

		dude.headR = img("RussHeadR.png");
		dude.headL = img("RussHeadL.png");

		dude.torsoR = img("RussBodyR.png");
		dude.torsoL = img("RussBodyL.png");

		dude.upperArmR = img("");
		dude.upperArmL = img("");
		dude.lowerArmR = img("");
		dude.lowerArmL = img("");
		dude.elbowR = img("");
		dude.elbowL = img("");
		dude.handTriggerR = img("");
		dude.handTriggerL = img("");
		dude.handHoldR = img("");
		dude.handHoldL = img("");

		dude.legsStillR = img("RussStillR.png");
		dude.legsStillL = img("RussStillL.png");

		dude.legsAirR = img("RussAirLegsRight.png");
		dude.legsAirL = img("RussAirLegsLeft.png");

		dude.legsRunningR = img("RussRunningRightSpriteSheet.png");
		dude.legsRunningL = img("RussRunningLeftSpriteSheet.png");

		dude.jumpSpeed = 850;
		dude.walkSpeed = 60;
		dude.sprintMultiplier = 1.6;

		dude.healVal = 4;
		dude.gunLimit = 2;

		dude.rightArmColor = Color.RED.darker().darker().darker();
		dude.leftArmColor = Color.DARK_GRAY.darker().darker();

		dude.pickUpWeapon(WeaponLibrary.Scavenger());

		dude.slowMotion = .08;
		dude.slowTimeLeftFull = 3.5;
		dude.timeUntilSlowMotionRechargeFull = 5;
		return dude;
	}
	private static void wireframe(Dude d)
	{
		d.wireframes[0] = Library.createWF(20, 186);// body
		d.wfOffset[0] = new Vect(0, -93);

		Vect[] feetCoords = new Vect[3];
		feetCoords[0] = new Vect(-90, -60);
		feetCoords[1] = new Vect(90, -60);
		feetCoords[2] = new Vect(0, 0);
		d.feet = new Wireframe(feetCoords, 3);
		d.feetOffset = new Vect(0, -5);

		d.feetCheck = Library.createWF(37.6, 120);
		d.feetCheckOffset = new Vect(0, 30);

		d.interactArea = Library.createWF(200, 150);// pickup
		d.interactAreaOffset = new Vect(0, -75);

		d.head = Library.createWF(25, 25);
		d.headOffset = new Vect(0, -172);

		d.grab = Library.createWF(80, 25);
		d.grabOffset = new Vect(0, -145);

		d.grabExclude = Library.createWF(82, 5);
		d.grabExcludeOffset = new Vect(0, -150);
	}
}
