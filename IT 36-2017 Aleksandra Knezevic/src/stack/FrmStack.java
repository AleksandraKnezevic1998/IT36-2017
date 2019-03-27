package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shapes.Rectangle;
import shapes.Point;


import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Rectangle> dlm = new DefaultListModel <Rectangle>();
	Rectangle r = new Rectangle();
	JList lstRec = new JList();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
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
					.addContainerGap()
					.addComponent(pnlCenter, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(pnlCenter, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		JButton btnAdd = new JButton("Add rectangle");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DlgRectangle dlg = new DlgRectangle();
				dlg.setVisible(true);
				dlg.setTitle("Knezevic Aleksandra IT36-2017");
				if (dlg.isOk == true) {
					int x = Integer.parseInt(dlg.getTxtX());
					int y = Integer.parseInt(dlg.getTxtY());
					int w = Integer.parseInt(dlg.getTxtWidth());
					int h = Integer.parseInt(dlg.getTxtHeight());
					r = new Rectangle (new Point(x,y), w, h);
					dlm.add(0, r);
				}
			}
		});
		
		
		btnAdd.setBackground(new Color(255, 255, 0));
		
		JButton btnRemove = new JButton("Remove rectangle");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!dlm.isEmpty())
				{
				DlgRectangle dlg=new DlgRectangle();
				dlg.setTitle("Knezevic Aleksandra IT36-2017");
				dlg.setTxtX(Integer.toString(r.getUpperLeftPoint().getX()));
				dlg.setTxtY(Integer.toString(r.getUpperLeftPoint().getY()));
				dlg.setTxtWidth(Integer.toString(r.getWidth()));
				dlg.setTxtHeight(Integer.toString(r.getHeight()));
				
				
				dlg.setVisible(true);
				if(dlg.isOk==true)
					{
					dlm.remove(0);
					}

				}
				else 
				{
					JOptionPane.showMessageDialog(contentPane,"List is empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			
		});
		btnRemove.setBackground(new Color(255, 255, 0));
		
		JScrollPane scrlRec = new JScrollPane();
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRemove, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(91)
					.addComponent(scrlRec, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlCenter.createSequentialGroup()
					.addGap(52)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(81))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(30)
					.addComponent(scrlRec, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		
		scrlRec.setViewportView(lstRec);
		lstRec.setModel(dlm);
		pnlCenter.setLayout(gl_pnlCenter);
		contentPane.setLayout(gl_contentPane);
		
		
		
	
		

	}
}
