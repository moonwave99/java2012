import java.awt.*;
import java.awt.geom.*;

class Sprite extends Rectangle2D.Double
{

	public double vx, vy;

	public Sprite(Point2D center, double width, double height)
	{

		super(
			center.getX() - width * 0.5,
			center.getY() - height * 0.5,
			width,
			height
		);

	}

	public void draw(Graphics2D g2)
	{

		g2.setColor(Color.RED);
		g2.fill(this);

	}

}