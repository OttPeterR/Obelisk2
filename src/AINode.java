import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class AINode
{
	public static final int		MOVEMENT		= 1;
	public static final int		JUMP_RIGHT_UP	= 2;
	public static final int		JUMP_LEFT_UP	= 3;
	public static final int		SPAWNER			= 4;
	public static final int		JUMP_DOWN_RIGHT	= 5;
	public static final int		JUMP_DOWN_LEFT	= 6;
	public static final Color	PROColor		= Entity.PROColor;

	double						x, y;
	ArrayList<AINode>			links, cluster;
	int							ID, type, length;
	Rectangle					rect;

	public AINode(double inx, double iny)
	{
		x = inx;
		y = iny;
		length = 250;
		ID = -1;
		rect = new Rectangle((int) x - 15, (int) y - 15, 30, 30);
		links = new ArrayList<AINode>();
		cluster = new ArrayList<AINode>();
	}
	public void render(Graphics2D g, double origX, double origY)
	{
		Font prevFont = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, 14));
		switch (type)
		{
			case (MOVEMENT):
				g.setColor(Color.blue);
				g.drawString("Movement", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
			case (JUMP_RIGHT_UP):
				g.setColor(Color.green);
				g.drawString("Jump /\\", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
			case (JUMP_LEFT_UP):
				g.setColor(Color.green);
				g.drawString("/\\ Jump", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
			case (SPAWNER):
				g.setColor(Color.red);
				g.drawString("Spawner", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
			case (JUMP_DOWN_RIGHT):
				g.setColor(Color.cyan);
				g.drawString("Jump \\/", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
			case (JUMP_DOWN_LEFT):
				g.setColor(Color.cyan);
				g.drawString("\\/ Jump", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
			default:
				g.setColor(Color.WHITE);
				g.drawString("Unassigned", (int) (x - origX - 7), (int) (y - origY - 7));
				break;
		}
		g.fillRect((int) (x - origX - 5), (int) (y - origY - 5), 10, 10);
		g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(), g.getColor().getBlue(), 80));
		for (int i = 0; i < links.size(); i++)
		{
			g.setStroke(new BasicStroke(3));
			g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(), g.getColor().getBlue(), 160));
			g.drawLine((int) (x - origX), (int) (y - origY), (int) ((links.get(i).x + x) / 2 - origX), (int) ((links.get(i).y + y) / 2 - origY));
			g.setStroke(new BasicStroke(1));
			g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(), g.getColor().getBlue(), 80));
			g.drawLine((int) (links.get(i).x - origX), (int) (links.get(i).y - origY), (int) ((links.get(i).x + x) / 2 - origX), (int) ((links.get(i).y + y) / 2 - origY));
		}
		g.setFont(prevFont);
	}
	public boolean intersects(Biped b)
	{
		for (int i = 0; i < links.size(); i++)
		{
			if (b.rect.intersectsLine(x, y, links.get(i).x, links.get(i).y))
				return true;
		}
		return false;
	}
	public boolean inRange(AINode n)
	{
		switch (type)
		{
			case (MOVEMENT):
				return (Math.hypot(x - n.x, y - n.y)) < 250;
			case (JUMP_LEFT_UP):
				if (n.type == JUMP_DOWN_RIGHT)
					return (y - n.y > 0 && y - n.y < 500 && x - n.x > 0 && x - n.x < 250);
				else
					return (Math.hypot(x - n.x, y - n.y)) < 100;
			case (JUMP_RIGHT_UP):
				if (n.type == JUMP_DOWN_LEFT)
					return (y - n.y > 0 && y - n.y < 500 && x - n.x < 0 && x - n.x > -250);
				else
					return (Math.hypot(x - n.x, y - n.y)) < 100;
			case (JUMP_DOWN_LEFT):
				if (y - n.y < 0 && y - n.y > -500 && x - n.x > 0 && x - n.x < 250)
					return true;
				else
					return (Math.hypot(x - n.x, y - n.y)) < 100;

			case (JUMP_DOWN_RIGHT):
				if (y - n.y < 0 && y - n.y > -500 && x - n.x < 0 && x - n.x > -250)
					return true;
				else
					return (Math.hypot(x - n.x, y - n.y)) < 100;
			case (SPAWNER):
				return (Math.hypot(x - n.x, y - n.y)) < 250;
			default:
				return (Math.hypot(x - n.x, y - n.y)) < 250;
		}
	}
	public String saveString()
	{
		return "[ " + x + " " + y + " " + type + " ]";
	}
}
