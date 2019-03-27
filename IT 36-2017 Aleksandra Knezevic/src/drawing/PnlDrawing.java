package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import stack.DlgRectangle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PnlDrawing extends JPanel {

	private FrmDrawing frame;
	private ArrayList <Shape> shapes =new ArrayList<Shape>();
	private Shape selected;
	private Point startPoint;
	/**
	 * Create the panel.
	 */
	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisClicked(e);
			}
		});

	}
	
	protected void thisClicked(MouseEvent e) {
		
		if(frame.getTglbtnSelect())
		{
			selected = null;
			Point p = new Point (e.getX(), e.getY());
			Iterator <Shape> iterator = shapes.iterator();
			while (iterator.hasNext())
			{
				Shape shape=iterator.next();
				shape.setSelected(false);
				if (shape.contains(p.getX(), p.getY()))
					{
					setSelected(shape);
					}
			}
		}
		
		if(selected!=null)
			{selected.setSelected(true);}
			
		if(frame.getTglbtnPoint()) {
			DlgPoint dlgPoint = new DlgPoint();
			
			dlgPoint.setTxtX(Integer.toString(e.getX()));
			dlgPoint.setTxtY(Integer.toString(e.getY()));
			dlgPoint.setVisible(true);
			
			
			
			if(dlgPoint.isOk == true)
			{
				
			Color color = dlgPoint.getColor();
			shapes.add(new Point(e.getX(), e.getY(), color));
			}
			
		}
		
		
		if(frame.getTglbtnLine())
		{
			if (startPoint==null)
			{
				startPoint=new Point(e.getX(), e.getY());
				
			}
			else
			{   DlgLine dlgLine =new DlgLine();
			   dlgLine.setTxtXStart(Integer.toString(startPoint.getX()));
			   dlgLine.setTxtYStart(Integer.toString(startPoint.getY()));
			   dlgLine.setTxtXEnd(Integer.toString(e.getX()));
			   dlgLine.setTxtYEnd(Integer.toString(e.getY()));
			  dlgLine.setVisible(true);
			  
			  if(dlgLine.isOk==true)
			  {
				Color color=dlgLine.getColor();  
				Line l=new Line(startPoint, new Point(e.getX(), e.getY()), color);
			   shapes.add(l);
			   startPoint=null;
			  }
			  
			}
			
		}
		
		
		if(frame.getTglbtnRectangle()) {
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.setTxtX(Integer.toString(e.getX()));
			dlgRectangle.setTxtY(Integer.toString(e.getY()));
			dlgRectangle.setVisible(true);
			if(dlgRectangle.isOk==true)
			{
				int x = Integer.parseInt(dlgRectangle.getTxtX());
				int y = Integer.parseInt(dlgRectangle.getTxtY());
				int width = Integer.parseInt(dlgRectangle.getTxtWidth());
				int height = Integer.parseInt(dlgRectangle.getTxtHeight());
				Color colorInside=dlgRectangle.getInsideColor();
				Color colorEdge=dlgRectangle.getEdgeColor();
				shapes.add(new Rectangle(new Point(x,y), width, height, colorInside, colorEdge));
			}
		}
		
		if(frame.getTglbtnCiclre())
		{
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.setTxtXOfCenter(Integer.toString(e.getX()));
			dlgCircle.setTxtYOfCenter(Integer.toString(e.getY()));
			dlgCircle.setVisible(true);
			
			if(dlgCircle.isOk==true)
			{
				int x=Integer.parseInt(dlgCircle.getTxtXOfCenter());
		      	int y=Integer.parseInt(dlgCircle.getTxtYOfCenter());
			     int r=Integer.parseInt(dlgCircle.getTxtRadius());
			    Color colorI=dlgCircle.getInsideColor();
			    Color colorE=dlgCircle.getEdgeColor();
			    
				shapes.add(new Circle( new Point(x,y), r, colorI, colorE));
			}
		}
		
		if(frame.getTglbtnDonut())
		{
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.setTxtX(Integer.toString(e.getX()));
			dlgDonut.setTxtY(Integer.toString(e.getY()));
			dlgDonut.setVisible(true);
			
			try {
			if(dlgDonut.isOk == true)
				
			{
				Donut d = new Donut();
				int x=Integer.parseInt(dlgDonut.getTxtX());
				int y=Integer.parseInt(dlgDonut.getTxtY());
				int or=Integer.parseInt(dlgDonut.getTxtOuterRadius());
				int ir=Integer.parseInt(dlgDonut.getTxtInnerRadius());
				Color icolor=dlgDonut.getInsideColor();
				Color ecolor=dlgDonut.getEdgeColor();
				d.setCenter(new Point(x,y));
				d.setRadius(or);
				d.setInnerRadius(ir);
				d.setInsideColor(icolor);
				d.setEdgeColor(ecolor);
				d.seteColor(ecolor);
				shapes.add(d);
			}
			
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Radius and inner radius must be positive and inner radius must be smaller than outer radius", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(!shapes.isEmpty())
		{
			repaint();
		}
		
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Iterator <Shape> it=shapes.iterator();
		while(it.hasNext())
		{ 
			((Shape)it.next()).draw(g);
		
		
	}

}
	
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public Shape getSelected() {
		return selected;
	}
	public void setSelected(Shape selected) {
		this.selected = selected;
	}
	
	
	

}
