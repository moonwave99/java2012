import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Tratto
{

	protected ArrayList<Point2D> points;

	protected Rectangle2D.Double boundingBox;

	protected Color traitColor;

	protected float stroke;

	protected double tx, ty;
	protected double pinX, pinY;

	public Tratto()
	{

		points = new ArrayList<Point2D>();
		traitColor = Color.BLACK;
		stroke = 1.5f;

	}

	public Tratto(Point initPoint)
	{

		points = new ArrayList<Point2D>();
		traitColor = Color.BLACK;
		stroke = 1.5f;

		tx = initPoint.getX();
		ty = initPoint.getY();
		//addPoint(new Point(0, 0));

	}

	public void addPoint(Point p)
	{

		points.add(new Point2D.Double(p.getX() - tx, p.getY() - ty));

	}

	public void draw(Graphics2D g2)
	{

		g2.setColor(traitColor);
		g2.setStroke(new BasicStroke(stroke));

		for(int i = 0; i < points.size() -1; i++)
		{

			g2.draw(new Line2D.Double(
				points.get(i).getX() + tx,
				points.get(i).getY() + ty,
				points.get(i+1).getX() + tx,
				points.get(i+1).getY() + ty
			));

		}

	}

	public void translate(Point p)
	{



	}

	public void setPinPoint(Point p){

		pinX = p.x;
		pinY = p.y;

	}

	public boolean contains(Point p)
	{

		boundingBox = boundingBox == null ? generateBoundingBox() : boundingBox;

		return boundingBox.contains(p);

	}

	public Rectangle2D.Double getBoundingBox()
	{

		boundingBox = boundingBox == null ? generateBoundingBox() : boundingBox;

		return boundingBox;

	}

	protected Rectangle2D.Double generateBoundingBox()
	{

		double minX = points.get(0).getX();
		double minY = points.get(0).getY();
		double maxX = points.get(0).getX();
		double maxY = points.get(0).getY();

		for(int i = 0; i < points.size() -1; i++)
		{

			minX = points.get(i).getX() < minX ? points.get(i).getX() : minX;
			minY = points.get(i).getY() < minY ? points.get(i).getY() : minY;
			maxX = points.get(i).getX() > maxX ? points.get(i).getX() : maxX;
			maxY = points.get(i).getY() > maxY ? points.get(i).getY() : maxY;

		}

		return new Rectangle2D.Double(
			minX + tx,
			minY + ty,
			maxX - minX,
			maxY - minY
		);

	}

}
