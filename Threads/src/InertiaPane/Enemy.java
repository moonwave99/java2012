import java.awt.*;
import java.awt.geom.*;

class Enemy extends Ellipse2D.Double
{

	public double vx, vy;

	public Enemy(Point2D center, double radius)
	{

		super(
			center.getX() - radius,
			center.getY() - radius,
			2 * radius,
			2 * radius
		);

	}

	public void shrink(double amount)
	{

		width -= amount;
		height -= amount;
		x += amount * 0.5;
		y += amount * 0.5;

	}

	public void grow(double amount)
	{

		width += amount;
		height += amount;
		x -= amount * 0.5;
		y -= amount * 0.5;

	}

	public void draw(Graphics2D g2)
	{

		g2.setColor(Color.YELLOW);
		g2.fill(this);

	}

}