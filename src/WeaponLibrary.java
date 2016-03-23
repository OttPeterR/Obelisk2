import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public class WeaponLibrary extends Library
{
	public static Weapon M16()
	{

		Vect[] frame;
		frame = new Vect[6];
		frame[0] = new Vect(2, 14);
		frame[1] = new Vect(67, 2);
		frame[2] = new Vect(167, 4);
		frame[3] = new Vect(198, 18);
		frame[4] = new Vect(62, 48);
		frame[5] = new Vect(5, 40);

		Vect[] clipFrame = new Vect[5];
		clipFrame[0] = new Vect(3, 3);
		clipFrame[1] = new Vect(15, 3);
		clipFrame[2] = new Vect(18, 34);
		clipFrame[3] = new Vect(8, 38);
		clipFrame[4] = new Vect(3, 23);

		Image[] clipImgs = {img("M16MagazineRight.png"), img("M16MagazineLeft.png")};
		String[] clipImgNames = {"M16MagazineRight.png", "M16MagazineLeft.png"};

		Entity clip = new PhysicsEntity("M16_Magazine", clipImgs, clipImgNames, new Wireframe(clipFrame, 5), new Vect(0, 0), 70, 120, 2, .3, .2);

		java.awt.Image[] imgs = new java.awt.Image[2];
		imgs[0] = img("M16Right.png");
		imgs[1] = img("M16Left.png");

		String[] imgNames = {"M16Right", "M16Left.png"};
		Vect trigger = new Vect(-40, 13);
		Vect hold = new Vect(27, -5);
		Vect shoulder = new Vect(-97, -8);
		Weapon r = new Weapon("M16", "Assault_Rifle", imgs, imgNames, new Wireframe(frame, 6), new Vect(-12, -4), 30, 4, 15, RifleBullet("5.56mm", 56, 24, 8000, 12000), new Vect(101.7, -9.2), RifleShell("5.56mm_Casing"), new Vect(-10, -13), clip, new Vect(-11, 10), trigger, hold, shoulder, 600, 1.8, Math.toRadians(0), Math.toRadians(5.5));
		r.type = Weapon.TYPE_ASSAULT_RIFLE;
		r.shotSound = "M16Shot.wav";
		r.boltSound = "";
		r.price = 17500;
		r.clipSound = "M16MagEject.wav";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.aimControl = Math.toRadians(23);
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon ACR()
	{
		String n = "ACR";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("ACRRight.png"), img("ACRLeft.png")};
		String[] ims = {"ACRRight.png", "ACRLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 14);
		wfCoords[1] = new Vect(50, 0);
		wfCoords[2] = new Vect(57, 0);
		wfCoords[3] = new Vect(165, 18);
		wfCoords[4] = new Vect(45, 44);
		wfCoords[5] = new Vect(0, 37);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-27, -2.5);
		int maxClip = 24;
		int extraClips = 5;
		double mass = 13;
		double rateOfFire = 450;
		double reloadTime = 1.7;
		double accuracy = Math.toRadians(.3);
		double lossOfAccuracy = Math.toRadians(2.6);
		Bullet bullet = RifleBullet("5.56mm", 32, 20, 2500, 11000);
		Vect muzzle = new Vect(86, -3.5);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-17, -5);
		java.awt.Image[] clipImgs = {img("ACRClipRight.png"), img("ACRClipLeft.png")};
		String[] clipImgS = {"ACRClipRight.png", "ACRClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(10, 1);
		clipCoords[2] = new Vect(14, 29);
		clipCoords[3] = new Vect(5, 32);
		Vect trigger = new Vect(-40, 14);
		Vect hold = new Vect(23, -5);
		Vect shoulder = new Vect(-78, -3);
		Vect clipOffset = new Vect(-12, 12);
		Entity clip = new PhysicsEntity("ACR_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "ACRShot.wav";
		r.boltSound = "";
		r.minAccuracy = Math.toRadians(30);
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.shoulder.y = r.muzzleOffset.y;
		r.price = 9000;
		return r;
	}
	public static Weapon AK47()
	{
		String n = "AK47";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("AK47Right.png"), img("AK47Left.png")};
		String[] ims = {"AK47Right.png", "AK47Left.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(1, 9);
		wfCoords[1] = new Vect(42, 1);
		wfCoords[2] = new Vect(146, 1);
		wfCoords[3] = new Vect(161, 9);
		wfCoords[4] = new Vect(47, 33);
		wfCoords[5] = new Vect(1, 25);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-11, -3);
		int maxClip = 30;
		int extraClips = 5;
		double mass = 14;
		double rateOfFire = 600;
		double reloadTime = 1.7;
		double accuracy = Math.toRadians(3.8);
		double lossOfAccuracy = Math.toRadians(2.9);
		Bullet bullet = RifleBullet("5.56mm", 20, 57, 4000, 9000);
		Vect trigger = new Vect(-33, 6);
		Vect hold = new Vect(0, -9);
		Vect shoulder = new Vect(-77, -6);
		Vect muzzle = new Vect(85, -8);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-5, -12);
		java.awt.Image[] clipImgs = {img("AK47ClipRight.png"), img("AK47ClipLeft.png")};
		String[] clipImgS = {"AK47ClipRight.png", "AK47ClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(11, 0);
		clipCoords[2] = new Vect(21, 27);
		clipCoords[3] = new Vect(12, 34);
		clipCoords[4] = new Vect(3, 19);
		Vect clipOffset = new Vect(0, 10);
		Entity clip = new PhysicsEntity("AK47_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "AK47Shot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.minAccuracy = Math.toRadians(38);
		r.reloadSound = "";
		r.price = 7500;
		r.dryReload = "PistolPullBack.wav";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon AEK()
	{
		String n = "AEK";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("AEKRight.png"), img("AEKLeft.png")};
		String[] ims = {"AEKRight.png", "AEKLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(2, 10);
		wfCoords[1] = new Vect(48, 1);
		wfCoords[2] = new Vect(147, 0);
		wfCoords[3] = new Vect(152, 9);
		wfCoords[4] = new Vect(49, 33);
		wfCoords[5] = new Vect(2, 26);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-8, -3);
		int maxClip = 30;
		int extraClips = 4;
		double mass = 11;
		double rateOfFire = 580;
		double reloadTime = 2.3;
		double accuracy = Math.toRadians(1.8);
		double lossOfAccuracy = Math.toRadians(2.3);
		Bullet bullet = RifleBullet("5.56mm", 35, 50, 4500, 12000);
		Vect trigger = new Vect(-29, 7);
		Vect hold = new Vect(24, -7);
		Vect shoulder = new Vect(-73, -7);
		Vect muzzle = new Vect(79.5, -7.7);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-10, -13);
		java.awt.Image[] clipImgs = {img("AEKClipRight.png"), img("AEKClipLeft.png")};
		String[] clipImgS = {"AEKClipRight.png", "AEKClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(1, 1);
		clipCoords[1] = new Vect(12, 1);
		clipCoords[2] = new Vect(20, 27);
		clipCoords[3] = new Vect(10, 33);
		clipCoords[4] = new Vect(3, 18);

		Vect clipOffset = new Vect(5, 10);
		Entity clip = new PhysicsEntity("AEK_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "AEKShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.minAccuracy = Math.toRadians(35);
		r.price = 10500;
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon AUG()

	{
		String n = "AUG";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("AUGRight.png"), img("AUGLeft.png")};
		String[] ims = {"AUGRight.png", "AUGLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 8);
		wfCoords[1] = new Vect(60, 0);
		wfCoords[2] = new Vect(103, 0);
		wfCoords[3] = new Vect(174, 11);
		wfCoords[4] = new Vect(84, 42);
		wfCoords[5] = new Vect(7, 42);

		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-15, -7);
		int maxClip = 30;
		int extraClips = 4;
		double mass = 16;
		double rateOfFire = 370;
		double reloadTime = 2;
		double accuracy = Math.toRadians(4.6);
		double lossOfAccuracy = Math.toRadians(4.5);
		Bullet bullet = RifleBullet("5.56mm", 18, 86, 8000, 10000);
		Vect trigger = new Vect(-15, 3);
		Vect hold = new Vect(19, -13);
		Vect shoulder = new Vect(-83, -13);
		Vect muzzle = new Vect(91, -13.8);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-40, -17);
		java.awt.Image[] clipImgs = {img("AUGClipRight.png"), img("AUGClipLeft.png")};
		String[] clipImgS = {"AUGClipRight.png", "AUGClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(15, 1);
		clipCoords[2] = new Vect(22, 38);
		clipCoords[3] = new Vect(9, 43);
		clipCoords[4] = new Vect(1, 26);
		Vect clipOffset = new Vect(-37, 15);
		Entity clip = new PhysicsEntity("AUG_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "AUGShot.wav";
		r.boltSound = "";
		r.price = 5500;
		r.minAccuracy = Math.toRadians(35);
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "PistolPullBack.wav";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon PTM4()
	{
		String n = "PTM4_Freelancer";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("FreelancerRight.png"), img("FreelancerLeft.png")};
		String[] ims = {"FreelancerRight.png", "FreelancerLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 7);
		wfCoords[1] = new Vect(58, 0);
		wfCoords[2] = new Vect(151, 0);
		wfCoords[3] = new Vect(168, 11);
		wfCoords[4] = new Vect(56, 42);
		wfCoords[5] = new Vect(2, 30);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-10, -4);
		int maxClip = 45;
		int extraClips = 4;
		double mass = 13.5;
		double rateOfFire = 750;
		double reloadTime = 2.5;
		double accuracy = Math.toRadians(2);
		double lossOfAccuracy = Math.toRadians(1.9);
		Bullet bullet = RifleBullet("5.56mm", 24, 45, 2500, 10000);
		Vect trigger = new Vect(-30, 10);
		Vect hold = new Vect(0, -3);
		Vect muzzle = new Vect(87, -9);
		Vect shoulder = new Vect(-80, -9);
		shoulder.y = muzzle.y;
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-6, -7);
		java.awt.Image[] clipImgs = {img("FreelancerClipRight.png"), img("FreelancerClipLeft.png")};
		String[] clipImgS = {"FreelancerClipRight.png", "FreelancerClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(12, 1);
		clipCoords[2] = new Vect(16, 30);
		clipCoords[3] = new Vect(11, 38);
		clipCoords[4] = new Vect(2, 31);
		Vect clipOffset = new Vect(-1, 15);
		Entity clip = new PhysicsEntity("Freelencer_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "AEKShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.price = 15000;
		r.minAccuracy = Math.toRadians(30);
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon Lammergeier()
	{
		String n = "Lammergeier";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("LammergeierRight.png"), img("LammergeierLeft.png")};
		String[] ims = {"LammergeierRight.png", "LammergeierLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 11);
		wfCoords[1] = new Vect(67, 0);
		wfCoords[2] = new Vect(127, 0);
		wfCoords[3] = new Vect(159, 14);
		wfCoords[4] = new Vect(42, 38);
		wfCoords[5] = new Vect(0, 34);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-12.5, -2);
		int maxClip = 32;
		int extraClips = 4;
		double mass = 12;
		double rateOfFire = 1100;
		double reloadTime = 1.7;
		double accuracy = Math.toRadians(3.8);
		double lossOfAccuracy = Math.toRadians(1.3);
		Bullet bullet = RifleBullet("5.56mm", 24, 30, 4000, 9000);
		Vect trigger = new Vect(-39, 8);
		Vect hold = new Vect(15, -9);
		Vect shoulder = new Vect(-78, -5);
		Vect muzzle = new Vect(83.5, -6);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-14, -6);
		java.awt.Image[] clipImgs = {img("LammergeierClipRight.png"), img("LammergeierClipLeft.png")};
		String[] clipImgS = {"LammergeierClipRight.png", "LammergeierClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(1, 1);
		clipCoords[1] = new Vect(11, 2);
		clipCoords[2] = new Vect(15, 28);
		clipCoords[3] = new Vect(5, 31);
		clipCoords[4] = new Vect(1, 18);

		Vect clipOffset = new Vect(-10.5, 10);

		Entity clip = new PhysicsEntity("Lammergeier_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "LammergeierShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.laserColor = Entity.PROColorBlaze;
		r.minAccuracy = Math.toRadians(20);
		r.shoulder.y = r.muzzleOffset.y;
		r.price = 14500;
		return r;
	}
	// SMGs
	public static Weapon SIG553()
	{
		String n = "SIG_552";
		String type = "SMG";
		java.awt.Image[] im = {img("SIG552Right.png"), img("SIG552Left.png")};
		String[] ims = {"SIG552Right.png", "SIG552Left.png"};
		Vect[] wfCoords = new Vect[7];
		wfCoords[0] = new Vect(0, 25);
		wfCoords[1] = new Vect(57, 2);
		wfCoords[2] = new Vect(81, 0);
		wfCoords[3] = new Vect(136, 13);
		wfCoords[4] = new Vect(152, 24);
		wfCoords[5] = new Vect(52, 52);
		wfCoords[6] = new Vect(0, 50);
		Wireframe wf = new Wireframe(wfCoords, 7);
		Vect wfOff = new Vect(-7, -1);
		int maxClip = 30;
		int extraClips = 6;
		double mass = 12;
		double rateOfFire = 750;
		double reloadTime = 1;
		double accuracy = Math.toRadians(7);
		double lossOfAccuracy = Math.toRadians(1.6);
		Bullet bullet = RifleBullet("5.56mm", 12, 12, 8000, 8500);
		bullet.trailLength = 700;
		Vect trigger = new Vect(-27, 17);
		Vect hold = new Vect(36, 3);
		Vect shoulder = new Vect(-78, 0);
		Vect muzzle = new Vect(79, -1.5);
		Entity shell = RifleShell("5.56mm_Casing");
		Vect shellEjection = new Vect(-1, -3);
		java.awt.Image[] clipImgs = {img("SIG552ClipRight.png"), img("SIG552ClipLeft.png")};
		String[] clipImgS = {"SIG552ClipRight.png", "SIG552ClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(12, 1);
		clipCoords[2] = new Vect(17, 27);
		clipCoords[3] = new Vect(5, 32);
		Vect clipOffset = new Vect(7, 20);
		Entity clip = new PhysicsEntity("SIG552_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.minAccuracy = Math.toRadians(30);
		r.shotSound = "SIG552Shot.wav";
		r.boltSound = "";
		r.clipSound = "MagEject.wav";
		r.reloadSound = "";
		r.dryReload = "PistolPullBack.wav";
		r.shoulder.y = r.muzzleOffset.y;
		r.price = 2500;
		return r;
	}
	public static Weapon KP6()
	{
		String n = "KP6";
		String type = "SMG";
		java.awt.Image[] im = {img("KP6Right.png"), img("KP6Left.png")};
		String[] ims = {"KP6Right.png", "KP6Left.png"};
		Vect[] wfCoords = new Vect[7];
		wfCoords[0] = new Vect(0, 18);
		wfCoords[1] = new Vect(51, 1);
		wfCoords[2] = new Vect(145, 0);
		wfCoords[3] = new Vect(172, 10);
		wfCoords[4] = new Vect(172, 16);
		wfCoords[5] = new Vect(64, 37);
		wfCoords[6] = new Vect(1, 38);
		Wireframe wf = new Wireframe(wfCoords, 7);
		Vect wfOff = new Vect(2, 0);
		int maxClip = 43;
		int extraClips = 6;
		double mass = 9;
		double rateOfFire = 1300;
		double reloadTime = .9;
		double accuracy = Math.toRadians(7);
		double lossOfAccuracy = Math.toRadians(1);
		Bullet bullet = PistolBullet("9mm", 8, 6, 8000, 6000);
		Vect trigger = new Vect(-25, 10);
		Vect hold = new Vect(33, -5);
		Vect shoulder = new Vect(-83, -5);
		Vect muzzle = new Vect(91, -5.4);
		Entity shell = PistolShell("9mm");
		Vect shellEjection = new Vect(-15, -10);
		java.awt.Image[] clipImgs = {img("KP6ClipRight.png"), img("KP6ClipLeft.png")};
		String[] clipImgS = {"KP6ClipRight.png", "KP6ClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(8, 0);
		clipCoords[2] = new Vect(26, 49);
		clipCoords[3] = new Vect(19, 54);
		clipCoords[4] = new Vect(5, 29);
		Vect clipOffset = new Vect(13, 20);
		Entity clip = new PhysicsEntity("KP6_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "KP6Shot.wav";
		r.boltSound = "";
		r.clipSound = "KP6MagEject.wav";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.shoulder.y = r.muzzleOffset.y;
		r.muzzleFlashes.clear();
		r.price = 2100;
		return r;
	}
	public static Weapon Scavenger()
	{
		String n = "Scavenger";
		String type = Weapon.TYPE_ASSAULT_RIFLE;
		java.awt.Image[] im = {img("ScavengerRight.png"), img("ScavengerLeft.png")};
		String[] ims = {"ScavengerRight.png", "ScavengerLeft.png"};
		Vect[] wfCoords = new Vect[7];
		wfCoords[0] = new Vect(0, 11);
		wfCoords[1] = new Vect(49, 1);
		wfCoords[2] = new Vect(95, 0);
		wfCoords[3] = new Vect(112, 8);
		wfCoords[4] = new Vect(112, 14);
		wfCoords[5] = new Vect(50, 39);
		wfCoords[6] = new Vect(1, 30);
		Wireframe wf = new Wireframe(wfCoords, 7);
		Vect wfOff = new Vect(2, -3);
		int maxClip = 20;
		int extraClips = 8;
		double mass = 5;
		double rateOfFire = 640;
		double reloadTime = .7;
		double accuracy = Math.toRadians(4);
		double lossOfAccuracy = Math.toRadians(3);
		Bullet bullet = PistolBullet("9mm", 14, 8, 3500, 8000);
		Vect trigger = new Vect(-10, 7.5);
		Vect hold = new Vect(28, -10);
		Vect shoulder = new Vect(-54, -6);
		// ShotgunPellet
		Vect muzzle = new Vect(60, -9);
		Entity shell = PistolShell("9mm_Casing");// shell
		Vect shellEjection = new Vect(12, -12);
		java.awt.Image[] clipImgs = {img("ScavengerClipRight.png"), img("ScavengerClipLeft.png")};
		String[] clipImgS = {"ScavengerClipRight.png", "ScavengerClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(2, 1);
		clipCoords[1] = new Vect(6, 1);
		clipCoords[2] = new Vect(17, 30);
		clipCoords[3] = new Vect(13, 33);
		clipCoords[4] = new Vect(4, 17);
		Vect clipOffset = new Vect(21, 13);
		Entity clip = new PhysicsEntity("Scavenger_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.type = Weapon.TYPE_SMG;
		r.minAccuracy = Math.toRadians(12);
		r.plusOne = true;
		r.automatic = true;
		r.shotSound = "ScavengerShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "M16PullBack.wav";
		r.price = 1500;
		r.shoulder.y = r.muzzleOffset.y;
		r.muzzleFlashes.clear();
		return r;
	}
	// Shotguns
	public static Shotgun Mossberg()
	{
		String n = "Mossberg_500";
		java.awt.Image[] im = {img("Mossberg500Right.png"), img("Mossberg500Left.png")};
		String[] ims = {"Mossberg500Right.png", "Mossberg500Left.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 12);
		wfCoords[1] = new Vect(66, 0);
		wfCoords[2] = new Vect(186, 0);
		wfCoords[3] = new Vect(186, 5);
		wfCoords[4] = new Vect(155, 11);
		wfCoords[5] = new Vect(1, 34);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(4, -7);
		int maxClip = 6;
		int extraClips = 3;
		double mass = 18;
		double rateOfFire = 60;
		double reloadTime = .55;
		double spread = Math.toRadians(4);
		double numFrags = 8;
		Bullet bullet = ShotgunPellet(".12_Guage_Pellet", 38, 162, 2500, 7500);
		Vect trigger = new Vect(-39, -10);
		Vect hold = new Vect(25, -12);
		Vect shoulder = new Vect(-92, 0);
		Vect muzzle = new Vect(96, -14.1);
		Entity shell = ShotgunShell(".12_Guage_Casing"); // shell
		Vect shellEjection = new Vect(-16, -14);
		// clip below
		Vect clipOffset = new Vect(-15, 11);
		Entity clip = null;
		Shotgun r = new Shotgun(n, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, numFrags, spread, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime);
		r.type = Weapon.TYPE_SHOTGUN;
		r.plusOne = false;
		r.automatic = false;
		r.fragError = 2;
		r.plusOne = false;
		r.shotSound = "Mossberg500Shot.wav";
		r.boltSound = "Mossberg500ShotgunPumpSlow.wav";
		r.clipSound = "";
		r.price = 15000;
		r.reloadSound = "ShotgunShellLoad.wav";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Shotgun SPAS()
	{
		String n = "SPAS";
		java.awt.Image[] im = {img("SPASRight.png"), img("SPASLeft.png")};
		String[] ims = {"SPASRight.png", "SPASLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 11);
		wfCoords[1] = new Vect(70, 0);
		wfCoords[2] = new Vect(175, 4);
		wfCoords[3] = new Vect(174, 14);
		wfCoords[4] = new Vect(50, 33);
		wfCoords[5] = new Vect(2, 32);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-8, -2);
		int maxClip = 8;
		int extraClips =4;
		double mass = 13;
		double rateOfFire = 75;
		double reloadTime = .4;
		double numFrags = 6;
		double spread = Math.toRadians(10);
		Bullet bullet = ShotgunPellet(".12_Guage_Pellet", 15, 112, 2000, 7000);
		Vect trigger = new Vect(-36, 2);
		Vect hold = new Vect(15, -8);
		Vect shoulder = new Vect(-89, 0);
		Vect muzzle = new Vect(91, -10);
		Entity shell = ShotgunShell(".12_Guage_Casing");// shell
		Vect shellEjection = new Vect(-12, -10);
		Shotgun r = new Shotgun(n, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, numFrags, spread, muzzle, shell, shellEjection, null, new Vect(-9, -8), trigger, hold, shoulder, rateOfFire, reloadTime);
		r.type = Weapon.TYPE_SHOTGUN;
		r.fragError = 1;
		r.plusOne = false;
		r.automatic = false;
		r.shotSound = "SPASShot.wav";
		r.boltSound = "SPASShotgunPumpFast.wav";
		r.price = 11000;
		r.clipSound = "";
		r.reloadSound = "ShotgunShellLoad.wav";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}

	// Sniper Rifles
	public static Weapon M98B()
	{
		String n = "M98Bravo";
		String type = Weapon.TYPE_SNIPER_RIFLE;
		java.awt.Image[] im = {img("98BRight.png"), img("98BLeft.png")};
		String[] ims = {"98BRight.png", "98BLeft.png"};
		Vect[] wfCoords = new Vect[8];
		wfCoords[0] = new Vect(0, 14);
		wfCoords[1] = new Vect(63, 0);
		wfCoords[2] = new Vect(155, 0);
		wfCoords[3] = new Vect(215, 13);
		wfCoords[4] = new Vect(215, 19);
		wfCoords[5] = new Vect(58, 52);
		wfCoords[6] = new Vect(7, 52);
		wfCoords[7] = new Vect(0, 41);

		Wireframe wf = new Wireframe(wfCoords, 8);
		Vect wfOff = new Vect(-18, -2);
		int maxClip = 5;
		int extraClips = 3;
		double mass = 19;
		double rateOfFire = 50;
		double reloadTime = 2.3;
		double accuracy = 0;
		double lossOfAccuracy = Math.toRadians(35);
		Bullet bullet = SniperBullet(".50_Caliber", 285, 650, 10000, 21000);
		Vect trigger = new Vect(-47, 13);
		Vect hold = new Vect(0, 0);
		Vect shoulder = new Vect(-108, -8);
		Vect muzzle = new Vect(111, -9.6);
		Entity shell = SniperShell(".50_Caliber_Casing");
		Vect shellEjection = new Vect(-10, -13);
		java.awt.Image[] clipImgs = {img("98BClipRight.png"), img("98BClipLeft.png")};
		String[] clipImgS = {"98BClipRight.png", "98BClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(23, 1);
		clipCoords[2] = new Vect(23, 21);
		clipCoords[3] = new Vect(0, 25);
		Vect clipOffset = new Vect(-10, 10);
		Entity clip = new PhysicsEntity("M98B_High_Caliber_Clip", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "M98BShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.price = 9500;
		r.dryReload = "PistolPullBack.wav";
		r.laserColor = Entity.PROColorBlaze;
		r.shoulder.y = r.muzzleOffset.y;
		r.minAccuracy = Math.toRadians(28);
		return r;
	}
	public static Weapon SVD()
	{
		String n = "SVD";
		String type = Weapon.TYPE_SNIPER_RIFLE;
		java.awt.Image[] im = {img("SVDRight.png"), img("SVDLeft.png")};
		String[] ims = {"SVDRight.png", "SVDLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(1, 12);
		wfCoords[1] = new Vect(49, 3);
		wfCoords[2] = new Vect(199, 1);
		wfCoords[3] = new Vect(214, 9);
		wfCoords[4] = new Vect(51, 31);
		wfCoords[5] = new Vect(2, 32);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-20, 0);
		int maxClip = 10;
		int extraClips = 5;
		double mass = 13;
		double rateOfFire = 250;
		double reloadTime = 2.5;
		double accuracy = Math.toRadians(0);
		double lossOfAccuracy = Math.toRadians(16);
		Bullet bullet = SniperBullet("7.62mm", 53, 80, 4000, 13000);
		Vect trigger = new Vect(-58, 3);
		Vect hold = new Vect(-24, 4);
		Vect shoulder = new Vect(-108, -5);
		Vect muzzle = new Vect(110, -6.5);
		Entity shell = RifleShell("7.62mm_Casing");// shell
		Vect shellEjection = new Vect(-20, -10);
		java.awt.Image[] clipImgs = {img("SVDClipRight.png"), img("SVDClipLeft.png")};
		String[] clipImgS = {"SVDClipRight.png", "SVDClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(1, 1);
		clipCoords[1] = new Vect(17, 1);
		clipCoords[2] = new Vect(16, 18);
		clipCoords[3] = new Vect(2, 21);
		Vect clipOffset = new Vect(-21.5, 6);
		Entity clip = new PhysicsEntity("SVD_Clip", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.aimControl = Math.toRadians(23);
		r.plusOne = true;
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "SVDShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.price = 8250;
		r.reloadSound = "";
		r.shoulder.y = r.muzzleOffset.y;
		r.minAccuracy = Math.toRadians(24);
		return r;
	}
	public static Weapon M14()
	{
		String n = "M14_Carbine";
		String type = Weapon.TYPE_SNIPER_RIFLE;
		java.awt.Image[] im = {img("M14Right.png"), img("M14Left.png")};
		String[] ims = {"M14Right.png", "M14Left.png"};
		Vect[] wfCoords = new Vect[5];
		wfCoords[0] = new Vect(0, 13);
		wfCoords[1] = new Vect(65, 0);
		wfCoords[2] = new Vect(198, 1);
		wfCoords[3] = new Vect(213, 6);
		wfCoords[4] = new Vect(1, 37);
		Wireframe wf = new Wireframe(wfCoords, 5);
		Vect wfOff = new Vect(-11, -6);
		int maxClip = 8;
		int extraClips = 5;
		double mass = 13;
		double rateOfFire = 280;
		double reloadTime = .6;
		double accuracy = Math.toRadians(0);
		double lossOfAccuracy = Math.toRadians(11);
		Bullet bullet = RifleBullet("5.56mm", 40, 112, 8000, 17000);
		Vect trigger = new Vect(-46, 4);
		Vect hold = new Vect(-23, -7);
		Vect shoulder = new Vect(-108, -10);
		bullet.bulletLength = 4;
		Vect muzzle = new Vect(109, -12);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-20, -15);
		Vect clipOffset = new Vect(0, 0);
		Entity clip = null;// = new PhysicsEntity("", clipImgs,
							// clipImgS, new Wireframe(clipCoords, 5),
							// new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = false;
		r.useClip = false;
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "M14Shot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.price = 8500;
		r.reloadSound = "M14Reload.wav";
		r.minAccuracy = Math.toRadians(20);
		r.shoulder.y = r.muzzleOffset.y;
		return r;

	}
	// Pistols
	public static Weapon USP45()
	{
		String n = "USP_.45";
		String type = Weapon.TYPE_HAND_GUN;
		java.awt.Image[] im = {img("USP45Right.png"), img("USP45Left.png")};
		String[] ims = {"USP45Right.png", "USP45Left.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 9);
		wfCoords[1] = new Vect(5, 0);
		wfCoords[2] = new Vect(43, 0);
		wfCoords[3] = new Vect(49, 10);
		wfCoords[4] = new Vect(49, 26);
		wfCoords[5] = new Vect(1, 31);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(0, -1);
		int maxClip = 16;
		int extraClips = 4;
		double mass = 2.5;
		double rateOfFire = 350;
		double reloadTime = 1;
		double accuracy = Math.toRadians(0);
		double lossOfAccuracy = Math.toRadians(5);
		// ExplosiveProjectile bullet = Grenade("", 0, 10, 150, 5, 5000,
		// 1000);
		Bullet bullet = PistolBullet(".45_Caliber_Round", 28, 5, 6000, 8500);
		Vect trigger = new Vect(-17, 0);
		Vect hold = new Vect(-19, 13);
		Vect shoulder = new Vect(-80, -9);
		Vect muzzle = new Vect(23, -12);
		Entity shell = PistolShell(".45_Caliber Casing");// shell
		Vect shellEjection = new Vect(-1, -13);
		java.awt.Image[] clipImgs = {img("USP45ClipRight.png"), img("USP45ClipLeft.png")};
		String[] clipImgS = {"USP45ClipRight.png", "USP45ClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(8, 0);
		clipCoords[1] = new Vect(14, 3);
		clipCoords[2] = new Vect(11, 27);
		clipCoords[3] = new Vect(0, 26);
		Vect clipOffset = new Vect(-17, 5);
		Entity clip = new PhysicsEntity("USP_45_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.minAccuracy = Math.toRadians(12);
		r.plusOne = true;
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "USP_45Shot.wav";
		r.boltSound = "";
		r.clipSound = "MagEject.wav";
		r.dryReload = "PistolPullBack.wav";
		r.price = 275;
		r.reloadSound = "";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon Magnum()
	{
		String n = ".44_Magnum";
		String type = Weapon.TYPE_HAND_GUN;
		java.awt.Image[] im = {img("RevolverRight.png"), img("RevolverLeft.png")};
		String[] ims = {"RevolverRight.png", "RevolverLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(18, 0);
		wfCoords[1] = new Vect(79, 0);
		wfCoords[2] = new Vect(79, 10);
		wfCoords[3] = new Vect(9, 33);
		wfCoords[4] = new Vect(1, 33);
		wfCoords[5] = new Vect(1, 22);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-7, -2);
		int maxClip = 6;
		int extraClips = 4;
		double mass = 3;
		double rateOfFire = 340;
		double reloadTime = .30;
		double accuracy = Math.toRadians(0);
		double lossOfAccuracy = Math.toRadians(11);
		Bullet bullet = RifleBullet(".44mm_Round", 60, 70, 6000, 11000);
		Vect trigger = new Vect(-30, 5);
		Vect hold = new Vect(-36, 15);
		Vect shoulder = new Vect(-75, -10);
		bullet.trailLength = 700;
		Vect muzzle = new Vect(43, -13);
		Entity shell = null;// shell
		Vect shellEjection = new Vect(0, 0);

		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(0, 0);
		Vect clipOffset = new Vect(0, 0);

		Entity clip = null;// = new PhysicsEntity("", clipImgs,
							// clipImgS, new Wireframe(clipCoords, 5),
							// new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = false;
		r.useClip = false;
		r.aimControl = Math.toRadians(20);
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "RevolverShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "ShotgunShellLoad.wav";
		r.shoulder.y = r.muzzleOffset.y;
		r.minAccuracy = Math.toRadians(38);
		r.price = 1000;
		return r;
	}
	public static Weapon DesertEagle()
	{
		String n = "Desert_Eagle";
		String type = Weapon.TYPE_HAND_GUN;
		java.awt.Image[] im = {img("DesertEagleRight.png"), img("DesertEagleLeft.png")};
		String[] ims = {"DesertEagleRight.png", "DesertEagleLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 14);
		wfCoords[1] = new Vect(9, 0);
		wfCoords[2] = new Vect(67, 0);
		wfCoords[3] = new Vect(67, 12);
		wfCoords[4] = new Vect(22, 38);
		wfCoords[5] = new Vect(4, 37);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-4, -2);
		int maxClip = 8;
		int extraClips = 4;
		double mass = 4;
		double rateOfFire = 400;
		double reloadTime = 1;
		double accuracy = Math.toRadians(1);
		double lossOfAccuracy = Math.toRadians(8);
		Bullet bullet = RifleBullet(".50_Caliber", 85, 15, 3000, 8500);
		Vect trigger = new Vect(-18, 5);
		Vect hold = new Vect(-23, 16);
		Vect shoulder = new Vect(-75, -9);
		Vect muzzle = new Vect(36, -12);
		Entity shell = PistolShell(".50mm_Casing");// shell
		Vect shellEjection = new Vect(-5, -15);
		java.awt.Image[] clipImgs = {img("DesertEagleClipRight.png"), img("DesertEagleClipLeft.png")};
		String[] clipImgS = {"DesertEagleClipRight.png", "DesertEagleClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(9, 0);
		clipCoords[1] = new Vect(19, 2);
		clipCoords[2] = new Vect(16, 31);
		clipCoords[3] = new Vect(0, 31);
		Vect clipOffset = new Vect(-19, 6);
		Entity clip = new PhysicsEntity("Desert_Eagle_Clip", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = true;
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "DEShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.dryReload = "PistolPullBack.wav";
		r.shoulder.y = r.muzzleOffset.y;
		r.minAccuracy = Math.toRadians(38);
		r.price = 1050;
		return r;
	}
	// Heavy Weapons
	public static Weapon M249()
	{
		String n = "M249";
		String type = Weapon.TYPE_LMG;
		java.awt.Image[] im = {img("M249Right.png"), img("M249Left.png")};
		String[] ims = {"M249Right.png", "M249Left.png"};
		Vect[] wfCoords = new Vect[5];
		wfCoords[0] = new Vect(0, 17);;
		wfCoords[1] = new Vect(95, 0);
		wfCoords[2] = new Vect(180, 19.5);
		wfCoords[3] = new Vect(59, 49);
		wfCoords[4] = new Vect(3, 38);
		Wireframe wf = new Wireframe(wfCoords, 5);
		Vect wfOff = new Vect(-22, 0);
		int maxClip = 100;
		int extraClips = 2;
		double mass = 18;
		double rateOfFire = 950;
		double reloadTime = 3.9;
		double accuracy = Math.toRadians(8);
		double lossOfAccuracy = Math.toRadians(1.9);
		Bullet bullet = RifleBullet("5.56mm", 22, 85, 8000, 7500);
		Vect trigger = new Vect(-31, 12);
		Vect hold = new Vect(12, 5);
		Vect shoulder = new Vect(-88, -3);
		Vect muzzle = new Vect(92, -4.3);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-8, -8);
		java.awt.Image[] clipImgs = {img("M249ClipRight.png"), img("M249ClipLeft.png")};
		String[] clipImgS = {"M249ClipRight.png", "M249ClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(16, 0);
		clipCoords[2] = new Vect(16, 30);
		clipCoords[3] = new Vect(0, 30);
		Vect clipOffset = new Vect(-5, 15);
		Entity clip = new PhysicsEntity("M249_Ammunition_Case", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.minAccuracy = Math.toRadians(23);
		r.plusOne = false;
		r.automatic = true;
		r.shotSound = "M249Shot.wav";
		r.boltSound = "";
		r.price = 14500;
		r.clipSound = "MagEject.wav";
		r.reloadSound = "";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon Mk46()
	{
		String n = "Mk46";
		String type = Weapon.TYPE_LMG;
		java.awt.Image[] im = {img("Mk46Right.png"), img("Mk46Left.png")};
		String[] ims = {"Mk46Right.png", "Mk46Left.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 8);
		wfCoords[1] = new Vect(50, 0);
		wfCoords[2] = new Vect(148, 0);
		wfCoords[3] = new Vect(167, 11.5);
		wfCoords[4] = new Vect(61, 43);
		wfCoords[5] = new Vect(0, 26);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-10, -7);
		int maxClip = 200;
		int extraClips = 1;
		double mass = 20;
		double rateOfFire = 600;
		double reloadTime = 4.3;
		double accuracy = Math.toRadians(6);
		double lossOfAccuracy = Math.toRadians(2);
		Bullet bullet = RifleBullet("5.56mm", 18, 85, 8000, 7500);
		Vect trigger = new Vect(-25, 9);
		Vect hold = new Vect(6, 6);
		Vect shoulder = new Vect(-82, -8);
		Vect muzzle = new Vect(88, -10);
		Entity shell = RifleShell("5.56mm_Casing");// shell
		Vect shellEjection = new Vect(-2.5, -13);
		java.awt.Image[] clipImgs = {img("Mk46ClipRight.png"), img("Mk46ClipLeft.png")};
		String[] clipImgS = {"Mk46ClipRight.png", "Mk46ClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(13, 0);
		clipCoords[2] = new Vect(13, 28);
		clipCoords[3] = new Vect(0, 28);
		Vect clipOffset = new Vect(5, 12);
		Entity clip = new PhysicsEntity("Mk46_High_Capacity_Ammunition_Case", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = false;
		r.automatic = true;
		r.shotSound = "Mk46Shot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.price = 12500;
		r.minAccuracy = Math.toRadians(30);

		r.reloadSound = "";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon XM214()
	{

		String n = "XM214";
		String type = Weapon.TYPE_LMG;
		java.awt.Image[] im = {img("XM214Right.png"), img("XM214Left.png")};
		String[] ims = {"XM214Right.png", "Left.png"};
		Vect[] wfCoords = new Vect[7];
		wfCoords[0] = new Vect(18, 0);
		wfCoords[1] = new Vect(80, 0);
		wfCoords[2] = new Vect(176, 29);
		wfCoords[3] = new Vect(176, 42);
		wfCoords[4] = new Vect(73, 83);
		wfCoords[5] = new Vect(18, 84);
		wfCoords[6] = new Vect(0, 46);
		Wireframe wf = new Wireframe(wfCoords, 7);
		Vect wfOff = new Vect(-9, -.5);
		int maxClip = 350;
		int extraClips = 1;
		double mass = 28;
		double rateOfFire = 1800;
		double reloadTime = 6.4;
		double accuracy = Math.toRadians(10);
		double lossOfAccuracy = Math.toRadians(2.5);
		Bullet bullet = RifleBullet("", 18, 78, 4500, 10000);
		Vect trigger = new Vect(-68, -36);
		Vect hold = new Vect(0, -38);
		Vect shoulder = new Vect(-83, -66);
		Vect muzzle = new Vect(93, -10);
		Entity shell = RifleShell("");// shell
		Vect shellEjection = new Vect(-16, -10);
		java.awt.Image[] clipImgs = {img("XM214ClipRight.png"), img("XM214ClipLeft.png")};
		String[] clipImgS = {"XM214ClipRight.png", "XM214ClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(47, 0);
		clipCoords[2] = new Vect(47, 26);
		clipCoords[3] = new Vect(0, 26);
		Vect clipOffset = new Vect(-42, 23);
		Entity clip = new PhysicsEntity("XM214_High_Capacity_Ammunition_Case", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = false;
		r.automatic = true;
		r.minAccuracy = Math.toRadians(28);
		r.shotSound = "XM214Shot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.price = 15050;
		r.reloadSound = "";

		return r;
	}
	// Explosive Weapons
	public static Weapon M320()
	{

		String n = "M320_Grenade_Launcher";
		String type = Weapon.TYPE_EXPLOSIVE;
		java.awt.Image[] im = {img("M320Right.png"), img("M320Left.png")};
		String[] ims = {"M320Right.png", "M320Left.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 0);
		wfCoords[1] = new Vect(112, 6);
		wfCoords[2] = new Vect(112, 19);
		wfCoords[3] = new Vect(102, 45);
		wfCoords[4] = new Vect(43, 40);
		wfCoords[5] = new Vect(0, 20);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(4, -1);
		int maxClip = 1;
		int extraClips = 19;
		double mass = 9;
		double rateOfFire = 150;
		double reloadTime = 2;
		double accuracy = Math.toRadians(10);
		double lossOfAccuracy = Math.toRadians(5);
		Explosive bullet = Grenade40mm("40mm_Grenade", 195, 70000, 350, 5, 2500, 1800, 1);
		Vect trigger = new Vect(-6, 9);
		Vect hold = new Vect(45, 12);
		Vect shoulder = new Vect(-56, -13);
		bullet.mass = 3;
		Vect muzzle = new Vect(58, -9);
		Entity shell = null;// shell
		Vect shellEjection = new Vect(20, 1);

		Vect clipOffset = new Vect(0, 0);
		Entity clip = null;
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.plusOne = false;
		r.automatic = false;
		r.useClip = false;
		r.currentlyAutomatic = false;
		r.shotSound = "M320Shot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.laserColor = Entity.PROColorBlaze;
		r.shoulder.y = r.muzzleOffset.y;
		r.minAccuracy = Math.toRadians(23);
		r.price = 13000;
		return r;
	}
	public static Shotgun PTM12()
	{
		String n = "PTM12_Jack_Hammer";
		java.awt.Image[] im = {img("JackHammerRight.png"), img("JackHammerLeft.png")};
		String[] ims = {"JackHammerRight.png", "JackHammerLeft.png"};
		Vect[] wfCoords = new Vect[7];
		wfCoords[0] = new Vect(0, 16);
		wfCoords[1] = new Vect(72, 0);
		wfCoords[2] = new Vect(126, 1);
		wfCoords[3] = new Vect(160, 10);
		wfCoords[4] = new Vect(160, 17);
		wfCoords[5] = new Vect(55, 42);
		wfCoords[6] = new Vect(1, 40);
		Wireframe wf = new Wireframe(wfCoords, 7);
		Vect wfOff = new Vect(2, -2);
		int maxClip = 6;
		int extraClips = 1;
		double mass = 16;
		double rateOfFire = 175;
		double reloadTime = 1.9;
		double numFrags = 5;
		double spread = Math.toRadians(20);
		AdhesiveExplosive bullet = ExplosiveFrag("Explosive_Frag", 40, 4000, 180, 2, 2500, 1800, 40000);
		Vect trigger = new Vect(-27, 7);
		Vect hold = new Vect(32, -6);
		Vect shoulder = new Vect(-80, 0);
		bullet.ex.smokeNum = 1;
		Vect muzzle = new Vect(84, -6.5);
		Entity shell = ShotgunShell(".12_Guage_Casing");// shell
		Vect shellEjection = new Vect(1, -11);
		java.awt.Image[] clipImgs = {img("JackHammerClipRight.png"), img("JackHammerClipLeft.png")};
		String[] clipImgS = {"JackHammerClipRight.png", "JackHammerClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(12, 1);
		clipCoords[2] = new Vect(18, 28);
		clipCoords[3] = new Vect(5, 32);
		Vect clipOffset = new Vect(15, 12);
		Entity clip = new PhysicsEntity("Jack_Hammer_Frag_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3, .2);
		Shotgun r = new Shotgun(n, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, numFrags, spread, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime);
		r.type = Weapon.TYPE_EXPLOSIVE;
		// r.fragError = 2;
		r.plusOne = true;
		r.useClip = true;
		r.price = 10500;
		r.automatic = true;
		r.currentlyAutomatic = true;
		r.shotSound = "JackHammerShot.wav";
		r.dryReload = "ShotgunPumpSlow.wav";
		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	// Special Weapons
	public static Weapon PTM38()
	{
		String n = "PTM38_Deus_Digito";
		String type = Weapon.TYPE_ENERGY;
		java.awt.Image[] im = {img("DeusDigitoRight.png"), img("DeusDigitoLeft.png")};
		String[] ims = {"DeusDigitoRight.png", "DeusDigitoLeft.png"};
		Vect[] wfCoords = new Vect[8];
		wfCoords[0] = new Vect(0, 7);
		wfCoords[1] = new Vect(56, 0);
		wfCoords[2] = new Vect(63, 0);
		wfCoords[3] = new Vect(141, 14);
		wfCoords[4] = new Vect(141, 23);
		wfCoords[5] = new Vect(93, 44);
		wfCoords[6] = new Vect(34, 46);
		wfCoords[7] = new Vect(0, 25);
		Wireframe wf = new Wireframe(wfCoords, 8);
		Vect wfOff = new Vect(-4, -1);
		int maxClip = 80;
		int extraClips = 3;
		double mass = 23;
		double rateOfFire = 2400;
		double reloadTime = 3.2;
		double accuracy = 0;
		double lossOfAccuracy = 0;
		Entity bullet = new FracLine("Deus_Digito_Bolt", new Vect(0, 0), new Vect(0, 0), .08, 1, 12, PROColor.brighter());
		Vect trigger = new Vect(-33, 14);
		Vect hold = new Vect(22, 16);
		Vect shoulder = new Vect(-72, -8);
		Vect muzzle = new Vect(40, -4);
		Entity shell = null;// shell
		Vect shellEjection = new Vect(0, 0);
		java.awt.Image[] clipImgs = {img("DeusDigitoClipRight.png"), img("DeusDigitoClipLeft.png")};
		String[] clipImgS = {"DeusDigitoClipRight.png", "DeusDigitoClipLeft.png"};
		Vect[] clipCoords = new Vect[4];
		clipCoords[0] = new Vect(6, 0);
		clipCoords[1] = new Vect(25, 16);
		clipCoords[2] = new Vect(18, 23);
		clipCoords[3] = new Vect(0, 4);

		Vect clipOffset = new Vect(5, 15);
		Entity clip = new PhysicsEntity("Deus_Digito_Charge_Pack", clipImgs, clipImgS, new Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 8, .5, .1);
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.extraAmmo = 3;
		r.plusOne = false;
		r.automatic = true;
		r.shotSound = "Electricity.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.muzzleFlashes.clear();
		r.shoulder.y = r.muzzleOffset.y;
		r.price = 15200;

		return r;
	}
	public static Shotgun Haptic()
	{
		String n = "PTM22_Haptic";
		java.awt.Image[] im = {img("HapticRight.png"), img("HapticLeft.png")};
		String[] ims = {"HapticRight.png", "HapticLeft.png"};
		Vect[] wfCoords = new Vect[6];
		wfCoords[0] = new Vect(0, 13);
		wfCoords[1] = new Vect(71, 0);
		wfCoords[2] = new Vect(120, 13);
		wfCoords[3] = new Vect(120, 21);
		wfCoords[4] = new Vect(36, 46);
		wfCoords[5] = new Vect(0, 35);
		Wireframe wf = new Wireframe(wfCoords, 6);
		Vect wfOff = new Vect(-3, -1);
		int maxClip = 5;
		int extraClips = 6;
		double mass = 23;
		double rateOfFire = 100;
		double reloadTime = 1.5;
		double numFrags = 5;
		double spread = Math.toRadians(15);
		Bullet bullet = Shrapnel("Shrapnel", 27, 6000, 1500);
		Vect trigger = new Vect(-21, 12);
		Vect hold = new Vect(12, 9);
		Vect shoulder = new Vect(-60, 0);
		bullet.trailLength = 50;
		Vect muzzle = new Vect(62, -8);
		Entity shell = ShotgunShell("Haptic_Shrapnel_Casing");
		Vect shellEjection = new Vect(7, -7);
		java.awt.Image[] clipImgs = {img("AUGClipRight.png"), img("AUGClipLeft.png")};
		String[] clipImgS = {"AUGClipRight.png", "AUGClipLeft.png"};
		Vect[] clipCoords = new Vect[5];
		clipCoords[0] = new Vect(0, 0);
		clipCoords[1] = new Vect(15, 1);
		clipCoords[2] = new Vect(22, 38);
		clipCoords[3] = new Vect(9, 43);
		clipCoords[4] = new Vect(1, 26);
		Vect clipOffset = new Vect(13, 20);
		Entity clip = new PhysicsEntity("Haptic_Shrapnel_Magazine", clipImgs, clipImgS, new Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3, .2);
		Shotgun r = new Shotgun(n, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, numFrags, spread, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime);
		r.plusOne = true;
		r.automatic = false;
		r.useClip = true;
		r.shotSound = "HapticShot.wav";
		r.boltSound = "";
		r.clipSound = "MagEject.wav";
		r.reloadSound = "";
		r.dryReload = "ShotgunPumpFast.wav";
		r.fragError = 1;
		r.price = 12500;

		r.shoulder.y = r.muzzleOffset.y;
		return r;
	}
	public static Weapon XBow()
	{
		String n = "X-Bow";
		String type = "X-Bow";
		java.awt.Image[] im = {img("ARMMRight.png"), img("ARMMLeft.png")};
		String[] ims = {"ARMMRight.png", "ARMMLeft.png"};
		Vect[] wfCoords = new Vect[5];
		wfCoords[0] = new Vect(0, 2);
		wfCoords[1] = new Vect(155, 1);
		wfCoords[2] = new Vect(166, 9);
		wfCoords[3] = new Vect(150, 20);
		wfCoords[4] = new Vect(0, 25);
		Wireframe wf = new Wireframe(wfCoords, 5);
		Vect wfOff = new Vect(11, -1);
		int maxClip = 1;
		int extraClips = 34;
		double mass = 5;
		double rateOfFire = 120;
		double reloadTime = 1;
		double accuracy = Math.toRadians(16);
		double lossOfAccuracy = Math.toRadians(5);
		AdhesiveEntity bullet = Arrow(2500, 15);
		Vect trigger = new Vect(-34, 4);
		Vect hold = new Vect(8, -5);
		Vect shoulder = new Vect(-83, 2);
		bullet.mass = 3;
		Vect muzzle = new Vect(50, -6.5);
		Entity shell = null;// shell
		Vect shellEjection = new Vect(20, 1);

		Vect clipOffset = new Vect(0, 0);
		Entity clip = null;
		Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip, extraClips, mass, bullet, muzzle, shell, shellEjection, clip, clipOffset, trigger, hold, shoulder, rateOfFire, reloadTime, accuracy, lossOfAccuracy);
		r.aimControl = Math.toRadians(15);
		r.useClip = false;
		r.plusOne = false;
		r.automatic = false;
		r.currentlyAutomatic = false;
		r.shotSound = "BowShot.wav";
		r.boltSound = "";
		r.clipSound = "";
		r.reloadSound = "";
		r.dryFire = "";
		r.laserColor = new Color(253, 86, 168);
		r.muzzleFlashes.clear();
		r.shoulder.y = r.muzzleOffset.y;
		r.price = 12500;
		return r;

	}

	// public static Weapon Incinerator()
	{

	}

	// Ammo
	public static Bullet RifleBullet(String name, double dmg, int numHits, double range, double speed)
	{
		Bullet b = new Bullet(name, null, "noImage", null, new Vect(-15, 0), 3, 1, dmg, numHits, speed, range);
		b.numWF = 1;
		b.numImg = 1;
		b.name = name;
		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("RifleBullet.png");
		b.bulletLength = 6;
		b.mass = .1;
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(10, 1);
		coords[2] = new Vect(0, 2);
		b.wireframes[0] = new Wireframe(coords, 3);
		b.computeRect();
		b.trailLength = 2000;
		return b;
	}
	public static Bullet SniperBullet(String name, double dmg, int numHits, double range, double speed)
	{
		Bullet b = new Bullet(name, null, "noImage", null, new Vect(-15, 0), 3, 1, dmg, numHits, speed, range);
		b.numWF = 1;
		b.numImg = 1;
		b.name = name;

		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("SniperBullet.png");
		b.imgName[0] = "SniperBullet.png";
		b.mass = .3;
		b.bulletThickness = 5;
		b.trailLength = 3500;
		b.bulletLength = 8;
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(16, 1);
		coords[2] = new Vect(0, 2);
		b.wireframes[0] = new Wireframe(coords, 3);
		b.computeRect();
		return b;
	}
	public static Bullet ShotgunPellet(String name, double dmg, int numHits, double range, double speed)
	{
		Bullet b = new Bullet(name, null, "noImage", null, new Vect(-15, 0), 3, 1, dmg, numHits, speed, range);
		b.numWF = 1;
		b.numImg = 1;
		b.decay = true;
		b.name = name;
		b.mass = .55;
		b.rotateForward = false;
		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("ShotgunPellet.png");
		b.imgName[0] = "ShotgunPellet.png";
		b.trailLength = 350;
		b.bulletLength = 3;
		b.bulletThickness = 1;
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(-.1, -.1);
		coords[1] = new Vect(.1, -.1);
		coords[2] = new Vect(0, .15);
		b.wireframes[0] = new Wireframe(coords, 3);
		b.computeRect();
		return b;
	}
	public static Bullet PistolBullet(String name, double dmg, int numHits, double range, double speed)
	{
		Bullet b = new Bullet(name, null, "noImage", null, new Vect(-15, 0), 3, 1, dmg, numHits, speed, range);
		b.numWF = 1;
		b.numImg = 1;
		b.name = name;

		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("PistolBullet.png");
		b.imgName[0] = "PistolBullet.png";
		b.bulletLength = 4;
		b.bulletThickness = 2;

		b.mass = .05;

		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(2, 1);
		coords[2] = new Vect(0, 0);
		b.wireframes[0] = new Wireframe(coords, 3);
		b.computeRect();
		return b;
	}
	public static Explosive Grenade40mm(String name, double dmg, double force, double radius, double timer, double range, double speed, int forceToBoom)
	{
		Explosive b = new Explosive(name, null, "noImage", null, new Vect(0, 0), timer, 1, speed, radius, dmg, force, forceToBoom, false);
		b.ex.smokeNum = 5;
		b.numWF = 1;
		b.numImg = 1;
		b.name = name;
		b.speed = speed;
		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("40mmGrenade.png");
		b.imgName[0] = "40mmGrenade.png";

		b.ex.extraJunk = new ArrayList<Entity>();
		b.ex.extraJunkCount = 30;
		b.ex.extraJunk.add(ParticleLibrary.Blood1());

		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(16, 3);
		coords[2] = new Vect(0, 6);
		b.wireframes[0] = new Wireframe(coords, 3);
		b.computeRect();
		return b;
	}
	public static AdhesiveExplosive ExplosiveFrag(String name, double dmg, double force, double radius, double timer, double range, double speed, int forceToBoom)
	{
		AdhesiveExplosive b = // 1 is mass
		new AdhesiveExplosive(name, null, "noImage", null, new Vect(0, 0), timer, 1, speed, radius, dmg, force, forceToBoom);
		b.numWF = 1;
		b.numImg = 1;
		b.name = name;
		b.speed = speed;
		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("ExpFrag.png");
		b.imgName[0] = "ExpFrag.png";

		b.wireframes[0] = createDefaultWF(3, 10);
		b.computeRect();
		return b;
	}
	public static Bullet Shrapnel(String name, double dmg, double range, double speed)
	{
		int numhits = 250;
		Bullet b = new Bullet(name, null, "noImage", null, new Vect(-15, 0), 60, 3, dmg, numhits, speed, range);
		b.numWF = 1;
		b.numImg = 1;
		b.name = name;
		b.mass = 1.3;
		b.rotateForward = false;
		b.wireframes = new Wireframe[b.numWF];
		b.wfOffset = new Vect[b.numWF];
		b.wfAng = new double[b.numWF];
		b.wfRad = new double[b.numWF];
		b.img = new java.awt.Image[b.numImg];
		b.imgName = new String[b.numImg];
		b.imgOffset = new Vect[b.numImg];
		b.img[0] = img("Shrapnel.png");
		b.imgName[0] = "Shrapnel.png";
		b.ricochet = true;
		b.trailLength = 100;
		b.bulletLength = 10;
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(2, 2);
		coords[1] = new Vect(11, 3);
		coords[2] = new Vect(9, 11);
		coords[3] = new Vect(1, 8);
		b.wireframes[0] = new Wireframe(coords, 4);
		b.computeRect();
		return b;
	}
	public static AdhesiveEntity Arrow(double spd, double dmg)
	{
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(60, 5);
		coords[1] = new Vect(40, 4);
		coords[2] = new Vect(40, 6);
		Wireframe wf = new Wireframe(coords, 3);
		AdhesiveEntity e = new AdhesiveEntity("Arrow", img("Arrow1.png"), "Arrow1.png", wf, new Vect(0, 0), 25, .5, spd, dmg);
		e.forward = true;
		e.fade = true;
		e.damageMultiplier = 9;
		return e;
	}
	// Shells
	public static PhysicsEntity RifleShell(String n)
	{
		PhysicsEntity s = new PhysicsEntity(n, null, "noImage", null, new Vect(0, 0), 15, 10, .4, .2, .6, 0);
		s.numWF = 1;
		s.name = n;
		s.numImg = 1;
		s.mass = .4;
		s.fade = true;
		s.wireframes = new Wireframe[s.numWF];
		s.wfOffset = new Vect[s.numWF];
		s.wfAng = new double[s.numWF];
		s.wfRad = new double[s.numWF];
		s.img = new java.awt.Image[s.numImg];
		s.imgName = new String[s.numImg];
		s.imgOffset = new Vect[s.numImg];
		s.wfOffset[0] = new Vect(0, 0);
		s.imgOffset[0] = new Vect(0, 0);
		s.img[0] = img("RifleShell.png");
		s.imgName[0] = "RifleShell.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(13, 1);
		coords[2] = new Vect(0, 2);
		s.wireframes[0] = new Wireframe(coords, 3);
		s.computeRect();
		return s;
	}
	public static PhysicsEntity SniperShell(String n)
	{
		PhysicsEntity s = new PhysicsEntity(n, null, "noImage", null, new Vect(0, 0), 15, 10, .4, .2, .4, 0);
		s.numWF = 1;
		s.numImg = 1;
		s.name = n;
		s.mass = .5;
		s.fade = true;

		s.wireframes = new Wireframe[s.numWF];
		s.wfOffset = new Vect[s.numWF];
		s.wfAng = new double[s.numWF];
		s.wfRad = new double[s.numWF];
		s.img = new java.awt.Image[s.numImg];
		s.imgName = new String[s.numImg];
		s.imgOffset = new Vect[s.numImg];
		s.wfOffset[0] = new Vect(0, 0);
		s.imgOffset[0] = new Vect(0, 0);
		s.img[0] = img("SniperShell.png");
		s.imgName[0] = "SniperShell.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(19, 1.5);
		coords[2] = new Vect(0, 3);
		s.wireframes[0] = new Wireframe(coords, 3);
		s.computeRect();
		return s;
	}
	public static PhysicsEntity ShotgunShell(String name)
	{
		PhysicsEntity s = new PhysicsEntity(name, null, "noImage", null, new Vect(0, 0), 15, 10, .4, .2, .4, 0);
		s.numWF = 1;
		s.numImg = 1;
		s.name = name;
		s.mass = .4;
		s.fade = true;

		s.wireframes = new Wireframe[s.numWF];
		s.wfOffset = new Vect[s.numWF];
		s.wfAng = new double[s.numWF];
		s.wfRad = new double[s.numWF];
		s.img = new java.awt.Image[s.numImg];
		s.imgName = new String[s.numImg];
		s.imgOffset = new Vect[s.numImg];
		s.wfOffset[0] = new Vect(0, 0);
		s.imgOffset[0] = new Vect(0, 0);
		s.img[0] = img("ShotgunShell.png");
		s.imgName[0] = "ShotgunShell.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(12, 0);
		coords[2] = new Vect(12, 3);
		coords[3] = new Vect(0, 3);
		s.wireframes[0] = new Wireframe(coords, 4);
		s.computeRect();
		return s;
	}
	public static PhysicsEntity PistolShell(String name)
	{
		PhysicsEntity s = new PhysicsEntity(name, null, "noImage", null, new Vect(0, 0), 15, 10, .4, .2, .4, 0);
		s.numWF = 1;
		s.numImg = 1;
		s.name = name;
		s.mass = .2;
		s.fade = true;

		s.wireframes = new Wireframe[s.numWF];
		s.wfOffset = new Vect[s.numWF];
		s.wfAng = new double[s.numWF];
		s.wfRad = new double[s.numWF];
		s.img = new java.awt.Image[s.numImg];
		s.imgName = new String[s.numImg];
		s.imgOffset = new Vect[s.numImg];
		s.wfOffset[0] = new Vect(0, 0);
		s.imgOffset[0] = new Vect(0, 0);
		s.img[0] = img("PistolShell.png");
		s.imgName[0] = "PistolShell.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(6, 1.5);
		coords[2] = new Vect(0, 3);
		s.wireframes[0] = new Wireframe(coords, 3);
		s.computeRect();
		return s;
	}
	public static PhysicsEntity GrenadeShell()
	{
		return null;
	}
	public static PhysicsEntity GrenadePin()
	{
		PhysicsEntity s = new PhysicsEntity("Grenade_Pin", img("GrenadePin.PNG"), "GrenadePin.PNG", null, new Vect(0, 0), 15, 10, .4, .2, .4, 0);
		s.numWF = 1;
		s.numImg = 1;
		s.mass = .2;
		s.fade = true;

		s.wireframes = new Wireframe[s.numWF];
		s.wfOffset = new Vect[s.numWF];
		s.wfAng = new double[s.numWF];
		s.wfRad = new double[s.numWF];
		s.img = new java.awt.Image[s.numImg];
		s.imgName = new String[s.numImg];
		s.imgOffset = new Vect[s.numImg];
		s.wfOffset[0] = new Vect(0, 0);
		s.imgOffset[0] = new Vect(0, 0);

		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(17, 14);
		coords[2] = new Vect(14, 32);
		s.wireframes[0] = new Wireframe(coords, 3);
		s.computeRect();
		return s;
	}
	// specific load methods
	public static void loadAssaultRifles(java.util.ArrayList<Entity> ents)
	{
		ents.add(M16());
		ents.add(AEK());
		ents.add(ACR());
		ents.add(AK47());
		ents.add(AUG());
		ents.add(Lammergeier());
		ents.add(PTM4());
	}
	public static void loadSMGs(java.util.ArrayList<Entity> ents)
	{
		ents.add(SIG553());
		ents.add(KP6());
		ents.add(Scavenger());
	}
	public static void loadShotguns(java.util.ArrayList<Entity> ents)
	{
		ents.add(Mossberg());
		ents.add(SPAS());
	}
	public static void loadPistols(java.util.ArrayList<Entity> ents)
	{
		ents.add(USP45());
		ents.add(Magnum());
		ents.add(DesertEagle());
	}
	public static void loadSnipers(java.util.ArrayList<Entity> ents)
	{
		ents.add(M14());
		ents.add(SVD());
		ents.add(M98B());
	}
	public static void loadHeavyWeapons(java.util.ArrayList<Entity> ents)
	{
		ents.add(M249());
		ents.add(Mk46());
		ents.add(XM214());
	}
	public static void loadExplosiveWeapons(java.util.ArrayList<Entity> ents)
	{
		ents.add(M320());
		ents.add(PTM12());
	}
	public static void loadSpecialWeapons(java.util.ArrayList<Entity> ents)
	{
		ents.add(PTM38());
		ents.add(Haptic());
		ents.add(XBow());
	}

	public static void loadAll(java.util.ArrayList<Entity> ents)
	{
		loadAssaultRifles(ents);
		loadSMGs(ents);
		loadShotguns(ents);
		loadPistols(ents);
		loadSnipers(ents);
		loadHeavyWeapons(ents);
		loadExplosiveWeapons(ents);
		loadSpecialWeapons(ents);
		ents.add(Grenade40mm("40mm_Grenade", 195, 70000, 350, 5, 2500, 1800, 1));
	}

	// TEMPLATES
	// template for most general weapons except shotguns
	{
		// String n = "";
		// String type = "";
		// java.awt.Image[] im =
		// { img("Right.png"), img("Left.png") };
		// String[] ims =
		// { "Right.png", "Left.png" };
		// Vect[] wfCoords = new Vect[6];
		// wfCoords[0] = new Vect();
		// wfCoords[1] = new Vect();
		// wfCoords[2] = new Vect();
		// wfCoords[3] = new Vect();
		// wfCoords[4] = new Vect();
		// wfCoords[5] = new Vect();
		// Wireframe wf = new Wireframe(wfCoords, 6);
		// Vect wfOff = new Vect(0, 0);
		// int maxClip = 0;
		// int extraClips = 0;
		// double mass = 0;
		// double rateOfFire = 0;
		// double reloadTime = 0;
		// double accuracy = Math.toRadians(0);
		// double lossOfAccuracy = Math.toRadians(0);
		// Bullet bullet = null;//RifleBullet, PistolBullet,
		// ShotgunPellet
		// Vect muzzle = new Vect(0, 0);
		// Entity shell = null;//shell
		// Vect shellEjection = new Vect(0, 0);
		// java.awt.Image[] clipImgs =
		// { img("ClipRight.png"), img("ClipLeft.png") };
		// String[] clipImgS =
		// { "ClipRight.png", "ClipLeft.png" };
		// Vect[] clipCoords = new Vect[4];
		// clipCoords[0] = new Vect();
		// clipCoords[1] = new Vect();
		// clipCoords[2] = new Vect();
		// clipCoords[3] = new Vect();
		// Vect clipOffset = new Vect(0, 0);
		// Entity clip = new PhysicsEntity("", clipImgs, clipImgS, new
		// Wireframe(clipCoords, 4), new Vect(0, 0), 70, 120, 2, .3,
		// .2);
		// Weapon r = new Weapon(n, type, im, ims, wf, wfOff, maxClip,
		// extraClips, mass, bullet, muzzle, shell, shellEjection, clip,
		// clipOffset, rateOfFire, reloadTime, accuracy,
		// lossOfAccuracy);
		// r.plusOne=true;
		// r.automatic=true;
		// r.shotSound="";
		// r.boltSound="";
		// r.clipSound="";
		// r.reloadSound="";
		// r.shoulder.y = r.muzzleOffset.y; return r;

		// template for shotguns

		// String n = "";
		// java.awt.Image[] im =
		// { img("Right.png"), img("Left.png") };
		// String[] ims =
		// { "Right.png", "Left.png" };
		// Vect[] wfCoords = new Vect[6];
		// wfCoords[0] = new Vect();
		// wfCoords[1] = new Vect();
		// wfCoords[2] = new Vect();
		// wfCoords[3] = new Vect();
		// wfCoords[4] = new Vect();
		// wfCoords[5] = new Vect();
		// Wireframe wf = new Wireframe(wfCoords, 6);
		// Vect wfOff = new Vect(0, 0);
		// int maxClip = 0;
		// int extraClips = 0;
		// double mass = 0;
		// double rateOfFire = 0;
		// double reloadTime = 0;
		// double numFrags=0;
		// double spread = Math.toRadians();
		// Bullet bullet = null;//RifleBullet, PistolBullet,
		// ShotgunPellet
		// Vect muzzle = new Vect(0, 0);
		// Entity shell = null;//shell
		// Vect shellEjection = new Vect(0, 0);
		// java.awt.Image[] clipImgs =
		// { img("ClipRight.png"), img("ClipLeft.png") };
		// String[] clipImgS =
		// { "ClipRight.png", "ClipLeft.png" };
		// Vect[] clipCoords = new Vect[5];
		// clipCoords[0]=new Vect(0,0);
		// Vect clipOffset = new Vect(0,0);
		// Entity clip = new PhysicsEntity("", clipImgs, clipImgS, new
		// Wireframe(clipCoords, 5), new Vect(0, 0), 70, 120, 2, .3,
		// .2);
		// Shotgun r = new Shotgun(n, im, ims, wf, wfOff, maxClip,
		// extraClips, mass, bullet, numFrags, spread, muzzle, shell,
		// shellEjection, clip, clipOffset, rateOfFire, reloadTime);
		// r.fragError=0;
		// r.plusOne=false;
		// r.automatic=false;
		// r.useClip=false;
		// r.shotSound="";
		// r.boltSound="";
		// r.clipSound="";
		// r.reloadSound="";
		// r.shoulder.y = r.muzzleOffset.y; return r;
	}
}
