import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Flare extends PhysicsEntity
	{
		Color			col;
		int				red, green, blue;
		int				trailLength, trailPresicion, rad;
		double			flareLengthX, flareLengthY, timeUntilRemove;
		ArrayList<Vect>	trail;
		BasicStroke		oneStroke, slimStroke, thickStroke;

		public Flare(String n, Color clr, int tl, double h, double t, double mss, double fric, double b)
			{
				super(n, null, "noImage", null, null, h, t, mss, fric, b, false);
				intersectPlayers = false;
				intersectStaticOnly = true;
				timeRemaining = t;
				fullTimeRemaining = 0 + t;
				trailLength = tl;
				trailPresicion = 15;
				trail = new ArrayList<Vect>();
				col = clr;
				red = clr.getRed();
				green = clr.getGreen();
				blue = clr.getBlue();
				flareLengthX = 80;
				flareLengthY = 30;
				rad = 3;
				oneStroke = new BasicStroke(1);
				slimStroke = new BasicStroke(3);
				thickStroke = new BasicStroke(5);
				numWF = 1;
				wireframes = new Wireframe[1];
				wfOffset = new Vect[1];
				wfAng = new double[1];
				wfRad = new double[1];
				wireframes[0] = Library.createDefaultWF(3, rad);
				super.construct();
			}
		public void step()
			{
				fps = (1.0E9 / (System.nanoTime() - myTime)) / timeScale;
				if (paused)
					{
						paused = false;
						myTime = System.nanoTime();
						return;
					}

				timeRemaining -= 1 / fps;
				if (timeRemaining < 0)
					timeRemaining = 0;

				if (Math.hypot((x - trail.get(trail.size() - 1).x), (y - trail.get(trail.size() - 1).y)) > 2)
					trail.add(0, new Vect(x, y));

				if (timeUntilRemove <= 0 && trail.size() >= 2)
					{
						trail.remove(trail.size() - 1);
						timeUntilRemove = .01;
					}
				timeUntilRemove -= 1 / fps;

				advance();
				translateTo(x, y);
				computeRect();
				myTime = System.nanoTime();
			}
		public void render(Graphics2D g, double origX, double origY)
			{
				// this.renderTrail(g, origX, origY);
				this.renderFlare(g, origX, origY);
			}
		public void renderTrail(Graphics2D g, double origX, double origY)
			{
				for (int i = 0; i < trail.size() - 1; i += 1)
					{
						int trans = (int) (30 * (double) (trail.size() - i) / (double) trail.size());
						g.setColor(new java.awt.Color(col.getRed(), col.getGreen(), col.getBlue(), (int) (70 + Math.pow(trans, .3))));

						g.setStroke(new java.awt.BasicStroke((long) Math.pow(trans, .4)));
						g.drawLine((int) (trail.get(i).x - origX), (int) (trail.get(i).y - origY), (int) (trail.get(i + 1).x - origX),
								(int) (trail.get(i + 1).y - origY));
					}
				g.setStroke(new java.awt.BasicStroke(1));
			}
		public void renderFlare(Graphics2D g, double origX, double origY)
			{
				int tempFlareLengthX = (int) (flareLengthX * (Math.random() * .10 + .90));
				int tempFlareLengthY = (int) (flareLengthY * (Math.random() * .10 + .90));
				g.setColor(new Color(red, green, blue, (int) ((Math.random() * .5 + .75) * 60 * (timeRemaining / fullTimeRemaining))));
				g.fillOval((int) (x - rad * 3 - origX), (int) (y - rad * 3 - origY), rad * 6, rad * 6);

				g.setColor(new Color(red, green, blue, (int) ((Math.random() * .5 + .75) * 90 * (timeRemaining / fullTimeRemaining))));
				g.setStroke(thickStroke);
				g.drawLine((int) (x - tempFlareLengthX / 4 - origX), (int) (y - origY), (int) (x + tempFlareLengthX / 4 - origX), (int) (y - origY));
				g.drawLine((int) (x - origX), (int) (y - tempFlareLengthY / 4 - origY), (int) (x - origX), (int) (y + tempFlareLengthY / 4 - origY));
				g.setStroke(slimStroke);
				g.drawLine((int) (x - tempFlareLengthX / 2 - origX), (int) (y - origY), (int) (x + tempFlareLengthX / 2 - origX), (int) (y - origY));
				g.drawLine((int) (x - origX), (int) (y - tempFlareLengthY / 2 - origY), (int) (x - origX), (int) (y + tempFlareLengthY / 2 - origY));

				g.setColor(new Color(red, green, blue, (int) ((Math.random() * .5 + .75) * 200 * (timeRemaining / fullTimeRemaining))));
				g.fillOval((int) (x - rad - origX), (int) (y - rad - origY), rad * 2, rad * 2);
				g.setStroke(oneStroke);
				g.drawLine((int) (x - tempFlareLengthX - origX), (int) (y - origY), (int) (x + tempFlareLengthX - origX), (int) (y - origY));
				g.drawLine((int) (x - origX), (int) (y - tempFlareLengthY - origY), (int) (x - origX), (int) (y + tempFlareLengthY - origY));
			}
		public void translateTo(double inx, double iny)
			{
				if (inx > Integer.MIN_VALUE && iny > Integer.MIN_VALUE)
					{
						x = inx;
						y = iny;
					}
			}
		public void translate(double inx, double iny)
			{
				if (inx > Integer.MIN_VALUE && iny > Integer.MIN_VALUE)
					{
						x += inx;
						y += iny;
					}
			}
		public void ignite(double inx, double iny, double xS, double yS)
			{
				translateTo(inx, iny);
				trail.add(0, new Vect(x, y));
				xSpeed = xS;
				ySpeed = yS;
			}
		public Flare copy()
			{
				Flare newF = new Flare(name, col, trailLength, health, fullTimeRemaining, mass, friction, bounce);
				newF.fullTimeRemaining = 0 + this.fullTimeRemaining;
				newF.timeRemaining = 0 + this.fullTimeRemaining;

				newF.trailLength = 0 + this.trailLength;
				newF.trailPresicion = 0 + this.trailPresicion;
				newF.flareLengthX = 0 + this.flareLengthX;
				newF.flareLengthY = 0 + this.flareLengthY;
				newF.intersectStaticOnly = this.intersectStaticOnly;
				newF.intersectPlayers = this.intersectPlayers;
				return newF;
			}
		public boolean removeMe()
			{
				if (timeRemaining <= 0 || health <= 0)
					return true;
				return false;
			}
	}
