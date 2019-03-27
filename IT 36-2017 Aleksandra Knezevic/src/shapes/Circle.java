package shapes;

import java.awt.Color;
import java.awt.Graphics;




public class Circle extends Shape {

	private Point center;
	private int radius;
	private Color insideColor;
	private Color edgeColor;
	
	
	
	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, Color insideColor, Color edgeColor) {
		this(center, radius);
		this.insideColor = insideColor;
		this.edgeColor = edgeColor;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}

	public void select(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
		g.drawRect(this.getCenter().getX() + getRadius() - 3, this.getCenter().getY()-3, 6, 6);
		g.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY()-3, 6, 6);
		g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getRadius() -3, 6, 6);
		g.drawRect(this.getCenter().getX()  - 3, this.getCenter().getY() - getRadius() -3, 6, 6);
		g.setColor(Color.BLACK);
	}
	@Override
	public void draw(Graphics g) {
		if (insideColor!=null )
		{
		g.setColor(insideColor);
		g.fillOval(this.getCenter().getX()-radius, this.getCenter().getY()-radius, radius*2, radius*2);
		}
		g.setColor(edgeColor);
		g.drawOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		g.setColor(Color.BLACK);
		if (isSelected()) {
			select(g);
			
		}
	}
	
	public void fill(Graphics g) {
		g.setColor(insideColor);
		g.fillOval(center.getX() - radius + 1, center.getY() - radius + 1, 2 * radius - 2, 2 * radius - 2);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius ;
	}
	
	public boolean contains(Point p) {
		if (p.distance(getCenter().getX(), getCenter().getY()) <= radius) {
			return true;
		} else {
			return false;
		} 
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double area() {
		return radius*radius*Math.PI;
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) throws Exception {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
	}
	
	public String toString() {
		return "Center=" + center + ", radius=" + radius; 
	}


	
}