import java.awt.Image;

public class UsableEntity extends Entity
{

	public UsableEntity(String n, Image im, String ims, Wireframe wf)
	{
		super(n, im, ims, wf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entity getNew()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collide(Entity e)
	{
		// TODO Auto-generated method stub

	}
	public void useBy(Biped b)
	{

	}

}
