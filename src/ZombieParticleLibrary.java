import java.awt.Image;

public class ZombieParticleLibrary extends Library
{

	public static PhysicsEntity ZombieBody_Normal_1()
	{
		String name = "Zombie_Body_Normal_1";
		Image img = img("ZombieNormal_Body_1R.png");
		String imgs = "ZombieNormal_Body_1R.png";

		Wireframe wireframe = createWF(35, 70);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 9;
		double mass = 45;
		double friction = .8;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieBody_Normal_2()
	{
		String name = "Zombie_Body_Normal_2";
		Image img = img("ZombieNormal_Body_2L.png");
		String imgs = "ZombieNormal_Body_2L.png";

		Wireframe wireframe = createWF(35, 70);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 9;
		double mass = 45;
		double friction = .8;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieBody_Normal_3()
	{
		String name = "Zombie_Body_Normal_3";
		Image img = img("ZombieNormal_Body_3L.png");
		String imgs = "ZombieNormal_Body_3L.png";

		Wireframe wireframe = createWF(35, 70);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 9;
		double mass = 45;
		double friction = .8;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieNormal_Arm_1()
	{
		String name = "ZombieNormal_Arm_1R";
		Image img = img("ZombieNormal_Arm_1R.png");
		String imgs = "ZombieNormal_Arm_1R.png";

		Wireframe wireframe = createWF(120, 32);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieNormal_Arm_2()
	{
		String name = "ZombieNormal_Arm_2L";
		Image img = img("ZombieNormal_Arm_2L.png");
		String imgs = "ZombieNormal_Arm_2L.png";

		Wireframe wireframe = createWF(120, 32);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieNormal_Arm_3()
	{
		String name = "ZombieNormal_Arm_3R";
		Image img = img("ZombieNormal_Arm_3R.png");
		String imgs = "ZombieNormal_Arm_3R.png";

		Wireframe wireframe = createWF(120, 32);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieNormal_Head_1()
	{
		String name = "ZombieNormal_Head_1R";
		Image img = img("ZombieNormal_Head_1R.png");
		String imgs = "ZombieNormal_Head_1R.png";

		Wireframe wireframe = createDefaultWF(5, 14);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 8;
		double mass = 12;
		double friction = .5;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.intersectPlayers = true;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieNormal_Head_2()
	{
		String name = "ZombieNormal_Head_2L";
		Image img = img("ZombieNormal_Head_2L.png");
		String imgs = "ZombieNormal_Head_2.png";

		Wireframe wireframe = createDefaultWF(5, 14);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 8;
		double mass = 12;
		double friction = .5;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.intersectPlayers = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieNormal_Head_3()
	{
		String name = "ZombieNormal_Head_3R";
		Image img = img("ZombieNormal_Head_3R.png");
		String imgs = "ZombieNormal_Head_3R.png";

		Wireframe wireframe = createDefaultWF(5, 14);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 8;
		double mass = 12;
		double friction = .5;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.intersectPlayers = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieSmall_Head_1()
	{
		String name = "ZombieSmall_Head_1R";
		Image img = img("ZombieSmall_Head_1R.png");
		String imgs = "ZombieSmall_Head_1R.png";

		Wireframe wireframe = createDefaultWF(5, 14);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 8;
		double mass = 12;
		double friction = .5;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.intersectPlayers = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieSmall_Head_2()
	{
		String name = "ZombieSmall_Head_2L";
		Image img = img("ZombieSmall_Head_2L.png");
		String imgs = "ZombieSmall_Head_2L.png";

		Wireframe wireframe = createDefaultWF(5, 14);
		Vect wfOffset = new Vect(0, 0);
		double health = -1;
		double time = 8;
		double mass = 12;
		double friction = .5;
		double bounce = .4;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.intersectPlayers = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieBody_Small_1()
	{
		String name = "ZombieSmall_Body_1";
		Image img = img("ZombieSmall_Body_1R.png");
		String imgs = "ZombieSmall_Body_1R.png";

		Wireframe wireframe = createWF(30, 60);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 9;
		double mass = 45;
		double friction = .8;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity ZombieBody_Small_2()
	{
		String name = "ZombieSmall_Body_2";
		Image img = img("ZombieSmall_Body_2L.png");
		String imgs = "ZombieSmall_Body_2L.png";

		Wireframe wireframe = createWF(30, 60);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 9;
		double mass = 45;
		double friction = .8;
		double bounce = .1;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Brain()
	{
		String name = "Zombie_Brain_Blood";
		Image img = img("brain.png");
		String imgs = "brain.png";

		Wireframe wireframe = createDefaultWF(4, 10);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Heart()
	{
		String name = "Zombie_Heart_Blood";
		Image img = img("heart.png");
		String imgs = "heart.png";

		Wireframe wireframe = createDefaultWF(4, 10);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Spleen()
	{
		String name = "Zombie_Spleen_Blood";
		Image img = img("spleen.png");
		String imgs = "spleen.png";

		Wireframe wireframe = createDefaultWF(4, 10);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Bone1()
	{
		String name = "Zombie_Bone1";
		Image img = img("bone1.png");
		String imgs = "bone1.png";

		Wireframe wireframe = createWF(50, 15);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Bone2()
	{
		String name = "Zombie_Bone2";
		Image img = img("bone2.png");
		String imgs = "bone2.png";

		Wireframe wireframe = createWF(30, 8);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Bone3()
	{
		String name = "Zombie_Bone3";
		Image img = img("bone3.png");
		String imgs = "bone3.png";

		Wireframe wireframe = createWF(22, 6);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
	public static PhysicsEntity Spine()
	{
		String name = "Zombie_Spine";
		Image img = img("spine.png");
		String imgs = "spine.png";

		Wireframe wireframe = createWF(15, 50);
		Vect wfOffset = new Vect(0, 0);
		double health = 30;
		double time = 7;
		double mass = 15;
		double friction = .4;
		double bounce = .3;
		float alpha = (float) 1;
		boolean noCollide = false;
		PhysicsEntity r = new PhysicsEntity(name, img, imgs, wireframe,
				wfOffset, health, time, mass, friction, bounce, alpha, noCollide);
		r.gravity = 980;
		r.intersectStaticOnly = true;
		r.ghost = false;
		r.fade = true;
		return r;
	}
}
