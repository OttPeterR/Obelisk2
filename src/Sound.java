public class Sound extends Entity
{

	public Sound(String n, double inx, double iny)
	{
		super(n, null, "", null);
		name = n;
		x = inx;
		y = iny;
		timeRemaining = 0;

		ghost = true;
	}

	public void render(java.awt.Graphics2D g, double origX, double origY)
	{}
	public Entity getNew()
	{
		return null;
	}
	public void collide(Entity e)
	{}
	public boolean isDead()
	{
		return false;
	}
	public boolean removeMe()
	{
		return false;
	}
	public void step()
	{}
	public Entity copy()
	{
		return null;
	}
	public void computeRect()
	{}
	public void renderAt(java.awt.Graphics2D g, double inx, double iny)
	{}
	public void renderWireframes(java.awt.Graphics2D g, double origX, double origY)
	{}
	public void renderInfo(java.awt.Graphics2D g, double origX, double origY)
	{}
	public void renderRect(java.awt.Graphics2D g, double origX, double origY)
	{}
	public boolean intersects(Entity e)
	{
		return false;
	}
	public void col(Entity e)
	{}
	public void exitOverlap(Entity e)
	{}
	public void advance()
	{}
	public void translate(double inx, double iny)
	{}
	public void translateTo(double inx, double iny)
	{}
	public void updateWFs()
	{}
	public void rotate(double a)// taken in radians
	{}
	public void rotateToward(double inx, double iny)
	{}
	public void rotateWF(double ang)
	{}
	public void damage(double d)
	{}
	public String saveString()
	{
		String s = "[ Sound ";
		s += name + " " + x + " " + y;
		return s + " ]";
	}

}
