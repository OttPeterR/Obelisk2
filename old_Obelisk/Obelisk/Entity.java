import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public interface Entity
{

	// game internals
	void renderInEditor(Graphics2D g);

	void renderInGame(Graphics2D g);

	String getSaveString();

	// Entity convert(double inx, double iny, int id);
	void convert(double inx, double iny, int id);

	// Entity convert(double inx, double iny, double inxs, double inys);
	void convert(double inx, double iny, double inxs, double inys);

	// Entity convertMove(double xMove, double yMove);
	void convertMove(double xMove, double yMove);

	// X, Y variables
	double getX();

	void setX(double inx);

	double getY();

	void setY(double iny);

	void setLocation(double inx, double iny);

	double getXSpeed();

	void setXSpeed(double inx);

	void addXSpeed(double x);

	void applyXForce(double x);

	double getYSpeed();

	void setYSpeed(double iny);

	void addYSpeed(double iny);

	void applyYForce(double iny);

	double getXDim();

	double getYDim();

	double getGravity();

	Rectangle getRectangle();

	Rectangle getEditorRectangle();
	// other variables
	String getName();

	String getType();

	void setName(String n);

	double getMass();

	void setMass(double m);

	double getFriction();

	void setFriction(double f);

	int getID();

	void setID(int id);

	double getHealth();

	void setHealth(double h);

	void damage(double h);

	Image getImage();
	
	String getImageString();

	double getMaxTime();

	void setMaxTime(double t);

	double getCurTime();

	void setCurTime(double t);

	boolean removeMe();

	double getRotation();

	void setRotation(double r);

	void addRotation(double r);

	void updateFPS(double newFPS);

	// simulation
	void step(double fps, ArrayList<Entity> e);

	void collide(Entity e);
	
	void checkCollision(Entity e);
	
	public void applySpeed();
	
	public void debug(Entity e);

	void frictionWith(Entity e);

	void pause();

	void unpause();

	// gun parameters
	void sync(Entity e);
	
	Enemy getEnemy();
}
