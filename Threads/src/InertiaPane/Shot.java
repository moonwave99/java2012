import java.awt.*;
import java.awt.geom.*;

class Shot extends Ellipse2D.Double
{

	public Shot(Point2D center, double radius)
	{

		super(
			center.getX() - radius,
			center.getY() - radius,
			2 * radius,
			2 * radius
		);

	}

	public void draw(Graphics2D g2)
	{

		g2.setColor(Color.GREEN);
		g2.fill(this);

	}

}