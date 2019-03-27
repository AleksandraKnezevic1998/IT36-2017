package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shapes.Point;
import shapes.Rectangle;
import stack.DlgRectangle;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Rectangle> dlm=new DefaultListModel<Rectangle>();
	private ArrayList <Rectangle> rectangles = new ArrayList<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
					frame.setTitle("Knezevic Aleksandra IT36-2017");
					frame.setSize(500, 300);
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
	public FrmSort() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(100, 149, 237));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(pnlCenter, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(pnlCenter, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JButton btnAddRectangles = new JButton("Add Rectangle");
		btnAddRectangles.setBackground(new Color(255, 255, 0));
		btnAddRectangles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DlgRectangle dijalog = new DlgRectangle();
				dijalog.setVisible(true);
				if (dijalog.isOk==true)
				{
					
					dlm.addElement(new Rectangle(new Point(Integer.parseInt(dijalog.getTxtX()), Integer.parseInt( dijalog.getTxtY())),
							Integer.parseInt(dijalog.getTxtHeight()), Integer.parseInt(dijalog.getTxtWidth())));
				      rectangles.add(new Rectangle(new Point(Integer.parseInt(dijalog.getTxtX()), Integer.parseInt( dijalog.getTxtY())),
								Integer.parseInt(dijalog.getTxtHeight()), Integer.parseInt(dijalog.getTxtWidth())));
				}
			}
		});
		
		JButton btnSortRectangles = new JButton("Sort Rectangles");
		btnSortRectangles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!dlm.isEmpty())
				{
					Collections.sort(rectangles, new Comparator<Rectangle>()
							{
						public int compare(Rectangle r1, Rectangle r2)
						{
							return r1.compareTo(r2);
						}
						
							});
					dlm.clear();
					for(int i=0; i<rectangles.size(); i++)
					{
						dlm.addElement(rectangles.get(i));
					}
							
				}
				else 
				{
					JOptionPane.showMessageDialog(contentPane, "List is empty", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSortRectangles.setBackground(new Color(255, 255, 0));
		
		JList lstSortRec = new JList();
		lstSortRec.setModel(dlm);
		
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(47)
					.addComponent(btnAddRectangles)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(btnSortRectangles)
					.addGap(65))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(34)
					.addComponent(lstSortRec, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSortRectangles)
						.addComponent(btnAddRectangles))
					.addGap(18)
					.addComponent(lstSortRec, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addGap(29))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		contentPane.setLayout(gl_contentPane);
	}
}
