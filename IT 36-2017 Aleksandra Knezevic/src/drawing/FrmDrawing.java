package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import stack.DlgRectangle;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JToggleButton tglbtnSelect = new JToggleButton("Select");
	JToggleButton tglbtnModify = new JToggleButton("Modify");
	JToggleButton tglbtnDelete = new JToggleButton("Delete");
	JToggleButton tglbtnPoint = new JToggleButton("Point");
	JToggleButton tglbtnLine = new JToggleButton("Line");
	JToggleButton tglbtnCircle = new JToggleButton("Circle");
	JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	JToggleButton tglbtnDonut = new JToggleButton("Donut");
	
	
	public boolean getTglbtnSelect() {
	    
		return tglbtnSelect.isSelected();
 }
 public boolean getTglbtnPoint() {
	    
		return tglbtnPoint.isSelected();
 }
 public boolean getTglbtnLine() {
	    
		return tglbtnLine.isSelected();
 }
 public boolean getTglbtnRectangle() {
	    
		return tglbtnRectangle.isSelected();
 }
 public boolean getTglbtnDonut() {
	    
		return tglbtnDonut.isSelected();
 }
 public boolean getTglbtnCiclre()
 {
	 return tglbtnCircle.isSelected();
	 
 }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
					frame.setTitle("Knezevic Aleksandra IT36-2017");
					frame.setSize(500, 400);
					frame.setLocation(400, 50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JPanel pnlShapes = new JPanel();
		pnlShapes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Shapes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		PnlDrawing panel = new PnlDrawing(this); 

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
								.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		
		tglbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlOptions.add(tglbtnSelect);
		tglbtnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelected()!= null)
				{
					Shape forModify = panel.getSelected();
					ArrayList<Shape> list=panel.getShapes();
					int index=list.indexOf(forModify);
					if(forModify instanceof Line)
					{
						DlgLine dlgLine = new DlgLine();
						dlgLine.setTxtXStart(Integer.toString(((Line)forModify).getStartPoint().getX()));
						dlgLine.setTxtYStart(Integer.toString(((Line)forModify).getStartPoint().getY()));
						dlgLine.setTxtXEnd(Integer.toString(((Line)forModify).getEndPoint().getX()));
						dlgLine.setTxtYEnd(Integer.toString(((Line)forModify).getEndPoint().getY()));
						dlgLine.setVisible(true);
						
						if(dlgLine.isOk == true)
						{
							((Line)forModify).setStartPoint(new Point(Integer.parseInt(dlgLine.getTxtXStart()), Integer.parseInt(dlgLine.getTxtYStart())));
							((Line)forModify).setStartPoint(new Point(Integer.parseInt(dlgLine.getTxtXEnd()), Integer.parseInt(dlgLine.getTxtYEnd())));
							list.add(index, forModify);
							panel.setShapes(list);
							panel.setSelected(forModify);
							panel.repaint();
							
						}
					}
					
					else if(forModify instanceof Point) {
						DlgPoint dlgPoint = new DlgPoint();
						dlgPoint.setTxtX(Integer.toString(((Point)forModify).getX()));
						dlgPoint.setTxtY(Integer.toString(((Point)forModify).getY()));
						dlgPoint.setColor(((Point)forModify).getColor());
						dlgPoint.setVisible(true);
						
						if(dlgPoint.isOk == true)
						{
							((Point)forModify).setX(Integer.parseInt(dlgPoint.getTxtX()));
							((Point)forModify).setY(Integer.parseInt(dlgPoint.getTxtY()));
							((Point)forModify).setColor(dlgPoint.getColor());
						
					  forModify.setSelected(true);
					  list.add(index, forModify);
					  panel.setShapes(list);
					  panel.setSelected(forModify);
					  panel.repaint();
						}
				
					}
					
					else if(forModify instanceof Rectangle)
					{
						DlgRectangle dlgRectangle = new DlgRectangle();
						dlgRectangle.setTxtX(Integer.toString(((Rectangle)forModify).getUpperLeftPoint().getX()));
						dlgRectangle.setTxtY(Integer.toString(((Rectangle)forModify).getUpperLeftPoint().getY()));
						dlgRectangle.setTxtWidth(Integer.toString(((Rectangle)forModify).getWidth()));
						dlgRectangle.setTxtHeight(Integer.toString(((Rectangle)forModify).getHeight()));
						dlgRectangle.setInsideColor(((Rectangle)forModify).getColorInside());
						dlgRectangle.setEdgeColor(((Rectangle)forModify).getColorEdge());
						dlgRectangle.setVisible(true);
						
						if(dlgRectangle.isOk == true)
						{
							((Rectangle)forModify).getUpperLeftPoint().setX(Integer.parseInt(dlgRectangle.getTxtX()));
		                       ((Rectangle)forModify).getUpperLeftPoint().setY(Integer.parseInt(dlgRectangle.getTxtY()));
		                       ((Rectangle)forModify).setWidth(Integer.parseInt(dlgRectangle.getTxtWidth()));
		                       ((Rectangle)forModify).setHeight(Integer.parseInt(dlgRectangle.getTxtHeight()));
		                       ((Rectangle)forModify).setColorInside(dlgRectangle.getInsideColor());
		                       ((Rectangle)forModify).setColorEdge(dlgRectangle.getEdgeColor());
		                       forModify.setSelected(false);
		     				  list.add(index, forModify);
		     				  panel.setShapes(list);
		     				  panel.setSelected(forModify);
		     				  panel.repaint();
						}
					}
					
					else if(forModify instanceof Donut)
					{
						DlgDonut dlgDonut = new DlgDonut();
						dlgDonut.setTxtX(Integer.toString(((Donut)forModify).getCenter().getX()));
						dlgDonut.setTxtY(Integer.toString(((Donut)forModify).getCenter().getY()));
						dlgDonut.setTxtOuterRadius(Integer.toString(((Donut)forModify).getRadius()));
						dlgDonut.setTxtInnerRadius(Integer.toString(((Donut)forModify).getInnerRadius()));
						dlgDonut.setInsideColor(((Donut)forModify).getiColor());
						dlgDonut.setEdgeColor(((Donut)forModify).geteColor());
						dlgDonut.setVisible(true);
						
						if(dlgDonut.isOk == true)
						{
							((Donut) forModify).getCenter().setX(Integer.parseInt(dlgDonut.getTxtX()));
							((Donut) forModify).getCenter().setY(Integer.parseInt(dlgDonut.getTxtY()));
							((Donut )forModify).setInsideColor(dlgDonut.getInsideColor());
							((Donut) forModify).seteColor(dlgDonut.getEdgeColor());
							((Donut)forModify).setEdgeColor(dlgDonut.getEdgeColor());
							
							try {
								((Donut) forModify).setRadius(Integer.parseInt(dlgDonut.getTxtOuterRadius()));
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(panel, "Radius must be positive", "Error", JOptionPane.ERROR_MESSAGE);
							}
							try {
								((Donut) forModify).setInnerRadius(Integer.parseInt(dlgDonut.getTxtInnerRadius()));
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(panel, "inner radius must be positive", "Error", JOptionPane.ERROR_MESSAGE);

							}
						       forModify.setSelected(false);
			     				  list.set(index, forModify);
			     				  panel.setShapes(list);
			     				  panel.setSelected(forModify);
							panel.repaint();
							
						
						}
					}
					
					else if(forModify instanceof Circle)
					{
						DlgCircle dlgCircle = new DlgCircle();
						dlgCircle.setTxtXOfCenter(Integer.toString(((Circle)forModify).getCenter().getX()));
						dlgCircle.setTxtYOfCenter(Integer.toString(((Circle)forModify).getCenter().getY()));
						dlgCircle.setTxtRadius(Integer.toString(((Circle)forModify).getRadius()));
						dlgCircle.setInsideColor(((Circle)forModify).getInsideColor());
						dlgCircle.setEdgeColor(((Circle)forModify).getEdgeColor());
						dlgCircle.setVisible(true);
						
						if(dlgCircle.isOk == true)
						{
							((Circle) forModify).getCenter().setX(Integer.parseInt(dlgCircle.getTxtXOfCenter()));
							((Circle) forModify).getCenter().setY(Integer.parseInt(dlgCircle.getTxtYOfCenter()));
							try {
								((Circle) forModify).setRadius(Integer.parseInt(dlgCircle.getTxtRadius()));
							} catch (Exception ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
								//stavi joption
							}
							((Circle)forModify).setInsideColor(dlgCircle.getInsideColor());
							
							((Circle)forModify).setEdgeColor(dlgCircle.getEdgeColor());
							 forModify.setSelected(true);
		     				  list.add(index, forModify);
		     				  panel.setShapes(list);
		     				  panel.setSelected(forModify);
		     				  panel.repaint();
						}
					}
				}
				
				else {
					
					JOptionPane.showMessageDialog(panel, "There is no selection", "Eror", JOptionPane.ERROR_MESSAGE);
					}
				
				
			}
		});
		
		
		tglbtnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlOptions.add(tglbtnModify);
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelected()!=null)
				{
					Shape forDelete = panel.getSelected();
					ArrayList<Shape> list=panel.getShapes();
					int index = list.indexOf(forDelete);
					if(JOptionPane.showConfirmDialog(contentPane, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					{
						list.remove(index);
						panel.setSelected(null);
						panel.setShapes(list);
					}
					panel.repaint();
					
				}
				else
				{
					JOptionPane.showMessageDialog(panel, "Need to select", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		tglbtnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlOptions.add(tglbtnDelete);
		
		
		
		tglbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		tglbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		tglbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		tglbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		tglbtnDonut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlShapes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlShapes.add(tglbtnPoint);
		pnlShapes.add(tglbtnLine);
		pnlShapes.add(tglbtnRectangle);
		pnlShapes.add(tglbtnCircle);
		pnlShapes.add(tglbtnDonut);
		
		buttonGroup.add(tglbtnDonut);
		buttonGroup.add(tglbtnRectangle);
		buttonGroup.add(tglbtnLine);
		buttonGroup.add(tglbtnCircle);
		buttonGroup.add(tglbtnPoint);
		buttonGroup.add(tglbtnDelete);
		buttonGroup.add(tglbtnSelect);
		buttonGroup.add(tglbtnModify);
		contentPane.setLayout(gl_contentPane);
		
	}
}
