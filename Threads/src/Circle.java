import java.awt.*;
import java.awt.geom.*;

public class Circle extends Ellipse2D.Double
{

	protected double pinX, pinY;

	public double vx, vy;

	protected Color bgColor;

	public Circle(Point2D center, double radius)
	{

		super(center.getX() - radius, center.getY() - radius, 2*radius, 2*radius);

		bgColor = new Color(
			(int)(Math.random()*255),
			(int)(Math.random()*255),
			(int)(Math.random()*255),200
		);

	}

	public double getRadius(){ return width * 0.5; }

	public void draw(Graphics2D g2)
	{

		g2.setColor(bgColor);
		g2.fill(this);

	}

	public void move(Point p)
	{

		this.x = p.x - this.pinX;
		this.y = p.y - this.pinY;

	}

	public void setPinPoint(Point p)
	{

		this.pinX = Math.abs(this.x - p.x);
		this.pinY = Math.abs(this.y - p.y);

	}

	public void releasePinPoint()
	{

		this.pinX = 0;
		this.pinY = 0;

	}

}