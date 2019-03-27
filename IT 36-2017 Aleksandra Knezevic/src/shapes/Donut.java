package shapes;
import java.awt.Color;
import java.awt.Graphics;

import drawing.FrmDrawing;
import drawing.PnlDrawing;




public class Donut extends Circle {

	private int innerRadius;
	private Color iColor;
	private Color eColor;
	
	public Donut() {
		
	}
	
	public Donut (Point center, int radius, int innerRadius) throws Exception
	{
		super(center,radius);
		this.setInnerRadius(innerRadius);
	}
	public Donut (Point center, int radius, int innerRadius, Color ic, Color ec) throws Exception
	{
		super(center,radius);
		this.setInnerRadius(innerRadius);
		this.iColor=ic;
		this.eColor=ec;
	}
	public Donut (Point center, int radius, int innerRadius, boolean selected) throws Exception
	{
		this(center, radius, innerRadius);
		setSelected(selected);
		
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		PnlDrawing pnl=new PnlDrawing(new FrmDrawing());
		g.setColor(pnl.getBackground());
		g.fillOval(this.getCenter().getX()-innerRadius, this.getCenter().getY()-innerRadius,
				innerRadius*2, innerRadius*2);
		
		g.setColor(eColor);
		g.drawOval(this.getCenter().getX()-innerRadius, this.getCenter().getY()-innerRadius,
				innerRadius*2, innerRadius*2);
		
		if(isSelected())
		{
		 g.setColor(Color.BLUE);
		 g.drawRect(this.getCenter().getX()-innerRadius-3, this.getCenter().getY()-3, 6, 6);
		 g.drawRect(this.getCenter().getX()+innerRadius-3, this.getCenter().getY()-3, 6, 6);
		 g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-innerRadius-3, 6, 6);
		 g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()+innerRadius-3, 6, 6);
		g.drawRect( getCenter().getX()-3, this.getCenter().getY()-3, 6,6);
		g.setColor(Color.BLACK);
		}
	}
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius()
					&& this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) throws Exception 
	{if ((innerRadius>0) && (innerRadius<getRadius())  )
	{
	this.innerRadius = innerRadius;
	}
	else
	{
		throw new NumberFormatException("Radius has to be a value greater then 0!");	
	}
		
	}
	
	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius;
	}

	public Color getiColor() {
		return iColor;
	}

	public void setiColor(Color iColor) {
		this.iColor = iColor;
	}

	public Color geteColor() {
		return eColor;
	}

	public void seteColor(Color eColor) {
		this.eColor = eColor;
	}
	
	
}
