import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FracLine extends Entity
	{
		ArrayList<Line2D.Double>	lines	= new ArrayList<Line2D.Double>();
		Color						col;
		Vect						start, end;
		double						maxDeform, numDivisions, regenTime, currentRegenTime, damage, splitEnds = 1.5;
		Dude						owner;

		public FracLine(String n, Vect s, Vect e, double t, double reg, double dmg, Color colr)
			{
				super("", null, "", null, null, 0);
				timeRemaining = t;
				fullTimeRemaining = 0 + t;
				regenTime = 0 + reg;
				currentRegenTime = 0 + reg;
				start = s;
				end = e;
				col = colr;
				damage = dmg;
				timeRemaining = t;
				fullTimeRemaining = 0 + t;
				x = start.x;
				y = start.y;
				noCollide = new ArrayList<Entity>();
				lines.add(new Line2D.Double(s.x, s.y, e.x, e.y));
				computeVariables();
				generate();
			}
		public void computeVariables()
			{
				double dis = Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
				numDivisions = Math.pow(dis, .23);// .1 is crap --- .4 never
													// happens
				maxDeform = Math.pow(dis, .76);
			}
		public void computeRect()
			{
				double maxX = lines.get(0).x1;
				double minX = lines.get(0).x1;
				double minY = lines.get(0).y1;
				double maxY = lines.get(0).y1;

				for (int i = 0; i < lines.size(); i++)
					{
						maxX = Math.max(maxX, lines.get(i).x1);
						minX = Math.min(minX, lines.get(i).x1);

						maxY = Math.max(maxY, lines.get(i).y1);
						minY = Math.min(minY, lines.get(i).y1);
					}
				rect = new java.awt.geom.Rectangle2D.Double(minX, minY, (maxX - minX), (maxY - minY));
			}
		public void generate()// normal lightning
			{
				double md = 0 + maxDeform;// I use this so getNew() is not
											// affected ... and its easier to
											// type
				Point2D.Double mid = new Point2D.Double();

				for (double j = numDivisions; j > 0; j--)
					{
						int origSize = lines.size();

						for (int i = 0; i < origSize; i++)
							{
								mid = new Point2D.Double();
								mid.x = (lines.get(0).x1 + lines.get(0).x2) / 2;
								mid.y = (lines.get(0).y1 + lines.get(0).y2) / 2;

								double dx = lines.get(0).x2 - lines.get(0).x1;
								double dy = lines.get(0).y2 - lines.get(0).y1;
								double theta = Math.atan(-dx / dy);

								double dist = md - (Math.random() * (md * 2));
								double sign = 1;
								if (Math.random() < .5)
									sign = -1;
								mid.x += Math.cos(theta) * sign * dist;
								mid.y += Math.sin(theta) * sign * dist;

								Line2D.Double l2 = new Line2D.Double(lines.get(0).getP1(), mid);
								lines.add(l2);
								lines.add(new Line2D.Double(mid, lines.get(0).getP2()));

								if ((j - (numDivisions / splitEnds)) / numDivisions > Math.random()) // making
																										// split
																										// ends
									{
										// dx = 0 + mid.x - l2.x1;
										// dy = 0 + mid.y - l2.y1;

										dx = end.x - start.x;
										dy = end.y - start.y;

										theta = Math.atan(dy / dx);
										if (start.x < end.x)
											theta += Math.PI;
										theta += (Math.random() * 1.4) - .7;

										dx = 0 + mid.x - l2.x1;
										dy = 0 + mid.y - l2.y1;

										double splitEndDist = Math.sqrt(((dx) * (dx)) + ((dy) * (dy)));

										Point splitEnd = new Point();
										splitEnd.x += mid.x - (int) (Math.cos(theta) * splitEndDist);
										splitEnd.y += mid.y - (int) (Math.sin(theta) * splitEndDist);
										lines.add(new Line2D.Double(mid, splitEnd));
									}
								lines.remove(0);
							}
						md /= 1.9;
					}
				computeRect();
			}
		public void regenerate()
			{
				lines = new ArrayList<Line2D.Double>();
				lines.add(new Line2D.Double(start.x, start.y, end.x, end.y));
				computeVariables();
				generate();
			}
		public void step()
			{
				super.step();
				if (regenTime == -1)
					return;
				if (fps > 1)
					currentRegenTime -= (1 / fps);
				if (currentRegenTime <= 0)
					{
						currentRegenTime += regenTime;
						regenerate();
					}
			}
		public boolean removeMe()
			{
				if (timeRemaining <= 0 && timeRemaining != -1)
					return true;
				else
					return false;
			}
		public void render(Graphics2D g, AffineTransform trans, double origX, double origY)
			{
				g.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue(), 200));
				g.setStroke(new java.awt.BasicStroke(5));
				for (int i = 0; i < lines.size(); i++)
					{
						g.drawLine((int) (((Line2D.Double) lines.get(i)).getX1() - origX), (int) (((Line2D.Double) lines.get(i)).getY1() - origY), (int) (((Line2D.Double) lines.get(i)).getX2() - origX), (int) (((Line2D.Double) lines.get(i)).getY2() - origY));
					}
				g.setColor(new Color(255, 255, 255, 150));
				g.setStroke(new java.awt.BasicStroke(1));
				for (int i = 0; i < lines.size(); i++)
					{
						g.drawLine((int) (((Line2D.Double) lines.get(i)).getX1() - origX), (int) (((Line2D.Double) lines.get(i)).getY1() - origY), (int) (((Line2D.Double) lines.get(i)).getX2() - origX), (int) (((Line2D.Double) lines.get(i)).getY2() - origY));
					}

			}
		public void renderWireframes(Graphics2D g, double origX, double origY)
			{
				g.setColor(col.brighter().brighter());
				g.setStroke(new java.awt.BasicStroke(1));
				for (int i = 0; i < lines.size(); i++)
					{
						g.drawLine((int) (((Line2D.Double) lines.get(i)).getX1() - origX), (int) (((Line2D.Double) lines.get(i)).getY1() - origY), (int) (((Line2D.Double) lines.get(i)).getX2() - origX), (int) (((Line2D.Double) lines.get(i)).getY2() - origY));
					}
			}
		public void translate(double inx, double iny)
			{
				start.x += inx;
				start.y += iny;
				end.x += inx;
				end.y += iny;

				for (int i = 0; i < lines.size(); i++)
					{
						lines.get(i).x1 += inx;
						lines.get(i).y1 += iny;
						lines.get(i).x2 += inx;
						lines.get(i).y2 += iny;
					}
			}
		public void translateTo(double inx, double iny)
			{
				end.x = (end.x - start.x + inx);
				end.y = (end.y - start.y + iny);

				for (int i = 0; i < lines.size(); i++)
					{
						lines.get(i).x1 = (lines.get(i).x1 - start.x + inx);
						lines.get(i).y1 = (lines.get(i).y1 - start.y + iny);
						lines.get(i).x2 = (lines.get(i).x2 - start.x + inx);
						lines.get(i).y2 = (lines.get(i).y2 - start.y + iny);
					}

				start.x = inx;
				start.y = iny;

			}
		public Entity getNew()
			{
				FracLine f = new FracLine(name, start, end, fullTimeRemaining, regenTime, damage, col);
				return f;
			}
		public void collide(Entity e)
			{
				e.noCollide.add(this);
				e.damage(damage);
			}

		public boolean intersects(Entity e)
			{
				if (ghost || e.ghost)
					return false;
				boolean touch = false;
				if (rect.intersects(e.rect))
					{
						for (int h = 0; h < e.numWF; h++)
							{
								for (int k = 0; k < e.wireframes[h].numCoords; k++)
									{
										ArrayList<Line2D.Double> otherLines = e.wireframes[h].getLineWireFrame();
										int lim = lines.size();
										for (int i = 0; i < lim; i++)
											{
												for (int j = 0; j < otherLines.size(); j++)
													{
														if (((Line2D.Double) lines.get(i)).intersectsLine((java.awt.geom.Line2D) otherLines.get(j)))
															{

																touch = true;
															}
													}
											}
									}
							}
					}
				return touch;
			}
	}
