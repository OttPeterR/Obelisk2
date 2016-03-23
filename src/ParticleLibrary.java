import java.awt.Image;
import java.util.ArrayList;

public class ParticleLibrary extends Library
{
	public void loadAll(ArrayList<Entity> ents)
	{

	}
	
	public static PhysicsEntity BlackBit1()
	{
		String name = "BlackBit1";
		Image img = img("BlackBit1.png");
		String imgs = "BlackBit1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 14);
		coords[1] = new Vect(16, 0);
		coords[2] = new Vect(15, 15);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 4;
		double time = 4;
		double mass = 1;
		double friction = .2;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity BlackBit2()
	{
		String name = "BlackBit2";
		Image img = img("BlackBit2.png");
		String imgs = "BlackBit2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 14);
		coords[1] = new Vect(16, 0);
		coords[2] = new Vect(15, 15);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 4;
		double time = 4;
		double mass = 1;
		double friction = .2;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity BlackBit3()
	{
		String name = "BlackBit3";
		Image img = img("BlackBit3.png");
		String imgs = "BlackBit3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 14);
		coords[1] = new Vect(16, 0);
		coords[2] = new Vect(15, 15);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 4;
		double time = 4;
		double mass = 1;
		double friction = .2;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Burger()
	{
		String name = "Burger";
		Image img = img("Burger.png");
		String imgs = "Burger.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(14, 0);
		coords[2] = new Vect(14, 14);
		coords[3] = new Vect(0, 14);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 7;
		double mass = .5;
		double friction = .6;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ConfettiBlue()
	{
		String name = "ConfettiBlue";
		Image img = img("BlueSquare.png");
		String imgs = "BlueSquare.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(5, 00000);
		coords[2] = new Vect(2.5, 5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 6;
		double mass = .01;
		double friction = .7;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 450;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		r.speedDampening = .99;
		return r;
	}
	public static PhysicsEntity ConfettiGreen()
	{
		String name = "ConfettiGreen";
		Image img = img("GreenSquare.png");
		String imgs = "GreenSquare.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(5, 00000);
		coords[2] = new Vect(2.5, 5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 6;
		double mass = .01;
		double friction = .7;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 450;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		r.speedDampening = .99;
		return r;
	}
	public static PhysicsEntity ConfettiOrange()
	{
		String name = "ConfettiOrangei";
		Image img = img("OrangeSquare.png");
		String imgs = "OrangeSquare.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(5, 00000);
		coords[2] = new Vect(2.5, 5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 6;
		double mass = .01;
		double friction = .7;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 450;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		r.speedDampening = .99;
		return r;
	}
	public static PhysicsEntity ConfettiRed()
	{
		String name = "ConfettiRed";
		Image img = img("RedSquare.png");
		String imgs = "RedSquare.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(5, 00000);
		coords[2] = new Vect(2.5, 5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 6;
		double mass = .01;
		double friction = .7;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 450;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		r.speedDampening = .99;
		return r;
	}
	public static PhysicsEntity ConfettiYellow()
	{
		String name = "ConfettiYellow";
		Image img = img("YellowSquare.png");
		String imgs = "YellowSquare.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(5, 00000);
		coords[2] = new Vect(2.5, 5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 6;
		double mass = .01;
		double friction = .7;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 450;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		r.speedDampening = .99;
		return r;
	}
	public static PhysicsEntity FishBone()
	{
		String name = "FishBone";
		Image img = img("FishBone.png");
		String imgs = "FishBone.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, -10);
		coords[1] = new Vect(13, -10);
		coords[2] = new Vect(9, 22);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 7;
		double mass = .5;
		double friction = .6;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Fries()
	{
		String name = "Fries";
		Image img = img("Fries.png");
		String imgs = "Fries.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(13, 0);
		coords[2] = new Vect(6, 22);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 7;
		double mass = .5;
		double friction = .6;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity GlassPieceSmall1()
	{
		String name = "GlassPieceSmall1";
		Image img = img("GlassSmall1.png");
		String imgs = "GlassSmall1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(4, 8);
		coords[2] = new Vect(8, 2);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .4;
		double time = 5;
		double mass = 1;
		double friction = .2;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity GlassPieceSmall2()
	{
		String name = "GlassPieceSmall2";
		Image img = img("GlassSmall2.png");
		String imgs = "GlassSmall2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(4, 0);
		coords[1] = new Vect(0, 8);
		coords[2] = new Vect(8, 3);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = .4;
		double time = 5;
		double mass = 1;
		double friction = .2;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Gravel1()
	{
		String name = "Gravel1";
		Image img = img("Gravel1.png");
		String imgs = "Gravel1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(1, 1);
		coords[1] = new Vect(6, 1);
		coords[2] = new Vect(3, 6);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 7;
		double mass = 1;
		double friction = .3;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Gravel2()
	{
		String name = "Gravel2";
		Image img = img("Gravel2.png");
		String imgs = "Gravel2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(1, 1);
		coords[1] = new Vect(6, 1);
		coords[2] = new Vect(3, 6);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 7;
		double mass = 1;
		double friction = .3;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Gravel3()
	{
		String name = "Gravel3";
		Image img = img("Gravel3.png");
		String imgs = "Gravel3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(1, 1);
		coords[1] = new Vect(6, 1);
		coords[2] = new Vect(3, 6);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 7;
		double mass = .5;
		double friction = .3;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Gravel4()
	{
		String name = "Gravel4";
		Image img = img("Gravel4.png");
		String imgs = "Gravel4.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(3, 0);
		coords[2] = new Vect(1.5, 4);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 7;
		double mass = 1;
		double friction = .3;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Gravel5()
	{
		String name = "Gravel5";
		Image img = img("Gravel5.png");
		String imgs = "Gravel5.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(3, 0);
		coords[2] = new Vect(1.5, 4);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 7;
		double mass = .5;
		double friction = .3;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Gravel6()
	{
		String name = "Gravel6";
		Image img = img("Gravel6.png");
		String imgs = "Gravel6.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(3, 0);
		coords[2] = new Vect(1.5, 4);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 7;
		double mass = .5;
		double friction = .3;
		double bounce = .6;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LampShade()
	{
		String name = "LampShade";
		Image img = img("LampShade.png");
		String imgs = "LampShade.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(10, 00000);
		coords[1] = new Vect(29, 00000);
		coords[2] = new Vect(39, 36);
		coords[3] = new Vect(00000, 34);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 3;
		double time = 10;
		double mass = 2;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LampShadeDestructed()
	{
		String name = "LampShadeDestructed";
		Image img = img("LampShadeDestructed.png");
		String imgs = "LampShadeDestructed.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(10, 00000);
		coords[1] = new Vect(29, 00000);
		coords[2] = new Vect(39, 36);
		coords[3] = new Vect(00000, 34);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 3;
		double time = 10;
		double mass = 2;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LampShardBlue1()
	{
		String name = "LampShardBlue1";
		Image img = img("LampShardBlue1.png");
		String imgs = "LampShardBlue1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(13, 8);
		coords[2] = new Vect(0, 14);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 6;
		double mass = 3;
		double friction = .4;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LampShardBlue2()
	{
		String name = "LampShardBlue2";
		Image img = img("LampShardBlue2.png");
		String imgs = "LampShardBlue2.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(15, 00000);
		coords[2] = new Vect(15, 15);
		coords[3] = new Vect(00000, 15);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 6;
		double mass = 4;
		double friction = .4;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LampShardBlue3()
	{
		String name = "LampShardBlue3";
		Image img = img("LampShardBlue3.png");
		String imgs = "LampShardBlue3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(11, 5);
		coords[2] = new Vect(8, 22);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 6;
		double mass = 3;
		double friction = .4;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LampShardBlue4()
	{
		String name = "LampShardBlue4";
		Image img = img("LampShardBlue4.png");
		String imgs = "LampShardBlue4.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(6, 00000);
		coords[1] = new Vect(00000, 9);
		coords[2] = new Vect(6, 22);
		coords[3] = new Vect(18, 9);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 6;
		double mass = 3;
		double friction = .4;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity LightSocketBroken1()
	{
		String name = "LightSocketBroken1";
		Image img = img("LightSocketBroken1.png");
		String imgs = "LightSocketBroken1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(7, 00000);
		coords[2] = new Vect(7, 15);
		coords[3] = new Vect(00000, 15);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 3;
		double time = 7;
		double mass = 1;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity MuzzleFlash1()
	{
		String name = "Muzzle_Flash1";
		Image img = img("MuzzleFlash1.png");
		String imgs = "MuzzleFlash1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 9);
		coords[1] = new Vect(32, 3);
		coords[2] = new Vect(32, 17);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = .03;
		double mass = 1;
		double friction = 0;
		double bounce = 0;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 0;
		r.ghost = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity MuzzleFlash2()
	{
		String name = "Muzzle_Flash2";
		Image img = img("MuzzleFlash2.png");
		String imgs = "MuzzleFlash2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 9);
		coords[1] = new Vect(32, 3);
		coords[2] = new Vect(32, 17);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = .03;
		double mass = 1;
		double friction = 0;
		double bounce = 0;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 0;
		r.ghost = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity MuzzleFlash3()
	{
		String name = "Muzzle_Flash3";
		Image img = img("MuzzleFlash3.png");
		String imgs = "MuzzleFlash3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 9);
		coords[1] = new Vect(32, 3);
		coords[2] = new Vect(32, 17);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = .03;
		double mass = 1;
		double friction = 0;
		double bounce = 0;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 0;
		r.ghost = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity MuzzleFlash4()
	{
		String name = "Muzzle_Flash4";
		Image img = img("MuzzleFlash4.png");
		String imgs = "MuzzleFlash4.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 9);
		coords[1] = new Vect(32, 3);
		coords[2] = new Vect(32, 17);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = .03;
		double mass = 1;
		double friction = 0;
		double bounce = 0;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 0;
		r.ghost = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Napkin()
	{
		String name = "Napkin";
		Image img = img("Napkin.png");
		String imgs = "Napkin.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 12);
		coords[1] = new Vect(17, 0);
		coords[2] = new Vect(17, 12);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 5;
		double mass = 1;
		double friction = .1;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 250;
		r.speedDampening = .8;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity PaperBall()
	{
		String name = "PaperBall";
		Image img = img("PaperBall.png");
		String imgs = "PaperBall.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 8);
		coords[1] = new Vect(13, 2);
		coords[2] = new Vect(13, 18);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 2;
		double time = 5;
		double mass = 1;
		double friction = .1;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 450;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SmokeSmall1()
	{
		String name = "Smoke_Small1";
		Image img = img("Smoke_Small1.png");
		String imgs = "Smoke_Small1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(5, 5);
		coords[1] = new Vect(29, 7);
		coords[2] = new Vect(43, 20);
		coords[3] = new Vect(15, 30);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = .4;
		double mass = 1;
		double friction = 0;
		double bounce = .0;
		float alpha = 1;
		boolean noCollide = true;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .9;
		r.gravity = 30;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SmokeMedium1()
	{
		String name = "Smoke_Medium1";
		Image img = img("Smoke_Medium1.png");
		String imgs = "Smoke_Medium1.png";
		Wireframe wireframe = Library.createDefaultWF(5, 85);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 1.3;
		double mass = 1;
		double friction = 0;
		double bounce = .0;
		float alpha = 1;
		boolean noCollide = true;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .9;
		r.gravity = 30;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SmokeDarkSmall1()
	{
		String name = "Smoke_Dark_Small1";
		Image img = img("Smoke_Dark_Small1.png");
		String imgs = "Smoke_Dark_Small1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(5, 5);
		coords[1] = new Vect(29, 7);
		coords[2] = new Vect(43, 20);
		coords[3] = new Vect(15, 30);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 1.8;
		double mass = 1;
		double friction = 0;
		double bounce = .0;
		float alpha = 1;
		boolean noCollide = true;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .9;
		r.gravity = 30;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SmokeDarkMedium1()
	{
		String name = "Smoke_Dark_Medium1";
		Image img = img("Smoke_Dark_Medium1.png");
		String imgs = "Smoke_Dark_Medium1.png";
		Wireframe wireframe = Library.createDefaultWF(5, 85);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 1.8;
		double mass = 1;
		double friction = 0;
		double bounce = .0;
		float alpha = 1;
		boolean noCollide = true;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .9;
		r.gravity = 30;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SparkYellow1(double time)
	{
		String name = "Spark1";
		Image img = img("Spark1.png");
		String imgs = "Spark1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(1.5, 3);
		coords[2] = new Vect(3, 0);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		// double time = .1;
		double mass = .03;
		double friction = 0;
		double bounce = .7;
		float alpha = (float) .6;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SparkYellow2(double time)
	{
		String name = "Spark2";
		Image img = img("Spark2.png");
		String imgs = "Spark2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(2.5, 5);
		coords[2] = new Vect(5, 0);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		// double time = .1;
		double mass = .03;
		double friction = 0;
		double bounce = .6;
		float alpha = (float) .6;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SparkOrange1(double time)
	{
		String name = "Spark_Orange1";
		Image img = img("Spark_Orange1.png");
		String imgs = "Spark_Orange1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(1.5, 3);
		coords[2] = new Vect(3, 0);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		// double time = .1;
		double mass = .03;
		double friction = 0;
		double bounce = .7;
		float alpha = (float) .6;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SparkOrange2(double time)
	{
		String name = "Spark_Orange2";
		Image img = img("Spark_Orange2.png");
		String imgs = "Spark_Orange2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(2.5, 5);
		coords[2] = new Vect(5, 0);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		// double time = .1;
		double mass = .03;
		double friction = 0;
		double bounce = .6;
		float alpha = (float) .6;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SparkRed1(double time)
	{
		String name = "Spark_Red1";
		Image img = img("Spark_Red1.png");
		String imgs = "Spark_Red1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(1.5, 3);
		coords[2] = new Vect(3, 0);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		// double time = .1;
		double mass = .03;
		double friction = 0;
		double bounce = .7;
		float alpha = (float) .6;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity SparkRed2(double time)
	{
		String name = "Spark_Red2";
		Image img = img("Spark_Red2.png");
		String imgs = "Spark_Red2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(2.5, 5);
		coords[2] = new Vect(5, 0);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		// double time = .1;
		double mass = .03;
		double friction = 0;
		double bounce = .6;
		float alpha = (float) .6;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashBag1()
	{
		String name = "TrashBag1";
		Image img = img("TrashBag1.png");
		String imgs = "TrashBag1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(19, 00000);
		coords[2] = new Vect(19, 34);
		coords[3] = new Vect(00000, 34);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 8;
		double mass = .3;
		double friction = .8;
		double bounce = .01;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .4;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashBag2()
	{
		String name = "TrashBag2";
		Image img = img("TrashBag2.png");
		String imgs = "TrashBag2.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(19, 00000);
		coords[2] = new Vect(19, 34);
		coords[3] = new Vect(00000, 34);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 8;
		double mass = .3;
		double friction = .8;
		double bounce = .01;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .4;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashBag3()
	{
		String name = "TrashBag3";
		Image img = img("TrashBag3.png");
		String imgs = "TrashBag3.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(00000, 00000);
		coords[1] = new Vect(19, 00000);
		coords[2] = new Vect(19, 34);
		coords[3] = new Vect(00000, 34);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = .2;
		double time = 8;
		double mass = .3;
		double friction = .8;
		double bounce = .01;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.speedDampening = .4;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashCanPiece1()
	{
		String name = "TrashCanPiece1";
		Image img = img("TrashCanPiece1.png");
		String imgs = "TrashCanPiece1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(44, 12);
		coords[2] = new Vect(52, 53);
		coords[3] = new Vect(12, 45);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 25;
		double time = 12;
		double mass = 10;
		double friction = .6;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashCanPiece2()
	{
		String name = "TrashCanPiece2";
		Image img = img("TrashCanPiece2.png");
		String imgs = "TrashCanPiece2.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 17);
		coords[1] = new Vect(53, 0);
		coords[2] = new Vect(51, 29);
		coords[3] = new Vect(15, 41);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 25;
		double time = 12;
		double mass = 10;
		double friction = .6;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashCanPiece3()
	{
		String name = "TrashCanPiece3";
		Image img = img("TrashCanPiece3.png");
		String imgs = "TrashCanPiece3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(58, 0);
		coords[2] = new Vect(19, 56);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(-3, -8);
		double health = 25;
		double time = 12;
		double mass = 10;
		double friction = .6;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashCanTop()
	{
		String name = "TrashCanTop";
		Image img = img("TrashCanTop.png");
		String imgs = "TrashCanTop.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(4, 4);
		coords[1] = new Vect(64, 4);
		coords[2] = new Vect(67, 40);
		coords[3] = new Vect(1, 40);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 25;
		double time = 12;
		double mass = 17;
		double friction = .6;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity TrashCup()
	{
		String name = "TrashCup";
		Image img = img("TrashCup.png");
		String imgs = "TrashCup.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(00000, 1);
		coords[1] = new Vect(6, 21);
		coords[2] = new Vect(12, 2);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 1;
		double time = 7;
		double mass = .5;
		double friction = .6;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WaterBottle()
	{
		String name = "WaterBottle";
		Image img = img("WaterBottle.png");
		String imgs = "WaterBottle.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(6, -1);
		coords[1] = new Vect(1, 21);
		coords[2] = new Vect(12, 21);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 2.5);
		double health = 2;
		double time = 6;
		double mass = 1;
		double friction = .1;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodBoardSmall()
	{
		String name = "Wood_Board_Small";
		Image img = img("WoodBoardSmall.png");
		String imgs = "WoodBoardSmall.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(18, 0);
		coords[2] = new Vect(18, 57);
		coords[3] = new Vect(0, 57);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, 0);
		double health = 25;
		double time = 8;
		double mass = 6;
		double friction = .6;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.ghost = false;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChip1()
	{
		String name = "WoodChip1";
		Image img = img("WoodChip1.png");
		String imgs = "WoodChip1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 3);
		coords[1] = new Vect(9, 0);
		coords[2] = new Vect(18, 4);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 3;
		double time = 5;
		double mass = .5;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChip2()
	{
		String name = "WoodChip2";
		Image img = img("WoodChip2.png");
		String imgs = "WoodChip2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(13, 3);
		coords[2] = new Vect(0, 3);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(2, 0);
		double health = 3;
		double time = 5;
		double mass = .5;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChip3()
	{
		String name = "WoodChip3";
		Image img = img("WoodChip3.png");
		String imgs = "WoodChip3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(3, 0);
		coords[2] = new Vect(0, .5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(-2, 0);
		double health = 3;
		double time = 5;
		double mass = .1;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChip4()
	{
		String name = "WoodChip4";
		Image img = img("WoodChip4.png");
		String imgs = "WoodChip4.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(7, 1);
		coords[2] = new Vect(0, 1);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(-2, 0);
		double health = 3;
		double time = 5;
		double mass = .4;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChipDark1()
	{
		String name = "WoodChipDark1";
		Image img = img("WoodChipDark1.png");
		String imgs = "WoodChipDark1.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 3);
		coords[1] = new Vect(9, 0);
		coords[2] = new Vect(18, 4);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(0, 0);
		double health = 3;
		double time = 5;
		double mass = .5;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChipDark2()
	{
		String name = "WoodChipDark2";
		Image img = img("WoodChipDark2.png");
		String imgs = "WoodChipDark2.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(13, 3);
		coords[2] = new Vect(0, 3);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(2, 0);
		double health = 3;
		double time = 5;
		double mass = .5;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChipDark3()
	{
		String name = "WoodChipDark3";
		Image img = img("WoodChipDark3.png");
		String imgs = "WoodChipDark3.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(3, 0);
		coords[2] = new Vect(0, .5);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(-2, 0);
		double health = 3;
		double time = 5;
		double mass = .1;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodChipDark4()
	{
		String name = "WoodChipDark4";
		Image img = img("WoodChipDark4.png");
		String imgs = "WoodChipDark4.png";
		Vect[] coords = new Vect[3];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(7, 1);
		coords[2] = new Vect(0, 1);
		Wireframe wireframe = new Wireframe(coords, 3);
		Vect wfOffset = new Vect(-2, 0);
		double health = 3;
		double time = 5;
		double mass = .4;
		double friction = .3;
		double bounce = .5;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		// r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodCratePiece1()
	{
		String name = "Wood_Crate_Piece1";
		Image img = img("WoodCratePiece1.png");
		String imgs = "WoodCratePiece1.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(49, 0);
		coords[2] = new Vect(35, 62);
		coords[3] = new Vect(0, 63);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(-5, -2);
		double health = 25;
		double time = 8;
		double mass = 12;
		double friction = .6;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.ghost = false;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodCratePiece2()
	{
		String name = "Wood_Crate_Piece2";
		Image img = img("WoodCratePiece2.png");
		String imgs = "WoodCratePiece2.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(51, 0);
		coords[2] = new Vect(51, 49);
		coords[3] = new Vect(0, 23);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(0, -6);
		double health = 25;
		double time = 8;
		double mass = 12;
		double friction = .6;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.ghost = false;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity WoodCratePiece3()
	{
		String name = "Wood_Crate_Piece3";
		Image img = img("WoodCratePiece3.png");
		String imgs = "WoodCratePiece3.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(0, 0);
		coords[1] = new Vect(27, 0);
		coords[2] = new Vect(40, 64);
		coords[3] = new Vect(0, 58);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(-3.5, 0);
		double health = 25;
		double time = 8;
		double mass = 12;
		double friction = .6;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.ghost = false;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Fire1()
	{
		String name = "DeleteMeFire";
		Image img = img("DeleteMeFire.png");
		String imgs = "DeleteMeFire.png";
		Vect[] coords = new Vect[4];
		coords[0] = new Vect(12, 80);
		coords[1] = new Vect(90, 10);
		coords[2] = new Vect(177, 72);
		coords[3] = new Vect(100, 136);
		Wireframe wireframe = new Wireframe(coords, 4);
		Vect wfOffset = new Vect(-3.5, 0);
		double health = 25;
		double time = .06;
		double mass = 12;
		double friction = .6;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe, wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 0;
		r.speedDampening = .9;
		r.ghost = true;
		r.intersectStaticOnly = true;
		r.fade = true;
		return r;
	}
	public static AdhesiveEntity Blood1()
	{
		String name = "Blood1";
		Image img = img("Blood1.png");
		String imgs = "Blood1.png";

		Wireframe wireframe = createDefaultWF(3, 2);
		Vect wfOffset = new Vect(0, 0);
		double time = 6;
		double mass = .1;
		AdhesiveEntity r = new AdhesiveEntity(name, img, imgs, wireframe, wfOffset, time, mass, 0, 0);
		r.numWF = 1;
		r.numImg = 1;
		r.name = name;
		r.wireframes = new Wireframe[r.numWF];
		r.wfOffset = new Vect[r.numWF];
		r.wfAng = new double[r.numWF];
		r.wfRad = new double[r.numWF];
		r.img = new java.awt.Image[r.numImg];
		r.imgName = new String[r.numImg];
		r.imgOffset = new Vect[r.numImg];
		r.img[0] = img("Blood1.png");
		r.imgName[0] = "Blood1.png";

		r.wireframes[0] = createDefaultWF(3, 2);
		r.computeRect();

		r.fade = true;
		r.bounce = 0;
		r.health = 1;
		r.penetration = 0;
		r.ghost = false;
		r.intersectStaticOnly = true;
		r.intersectPlayers = false;
		r.fade = true;
		return r;
	}
	public void loadSparksYellow(ArrayList<Entity> ents)
	{
		ents.add(SparkYellow1(.7));
		ents.add(SparkYellow2(.9));
	}
	public void loadSparksOrange(ArrayList<Entity> ents)
	{
		ents.add(SparkOrange1(.7));
		ents.add(SparkOrange2(.9));
	}
	public void loadSparksRed(ArrayList<Entity> ents)
	{
		ents.add(SparkRed1(.7));
		ents.add(SparkRed2(.9));
	}
	// public static PhysicsEntity ()
	// {
	// String name = "";
	// Image img = img(".png");
	// String imgs = ".png";
	// Vect[] coords = new Vect[5];
	// coords[0] = new Vect(00000,00000);
	// coords[1] = new Vect(00000,00000);
	// coords[2] = new Vect(00000,00000);
	// coords[3] = new Vect(00000,00000);
	// coords[4] = new Vect(00000,00000);
	// Wireframe wireframe = new Wireframe(coords, 5);
	// Vect wfOffset = new Vect(0, 0);
	// double health = -1;
	// double time = -1;
	// double mass = 1;
	// double friction = 0;
	// double bounce = 1;
	// float alpha=(float) 1;
	// boolean noCollide = false;
	// PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
	// wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
	// r.gravity=980;
	// r.intersectStaticOnly=true;
	// r.ghost=false;
	// r.fade=true;
	// return r;
	// }
}
