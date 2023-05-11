import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


import javax.swing.JLabel;
public class Driver extends JPanel implements ActionListener, MouseListener, KeyListener  {

	Background bckg = new Background();
	public void paint(Graphics g) {
		super.paintComponent(g);
		bckg.paint(g);
		
		
		//Font gameEndFont = new Font("SansSerif", Font.PLAIN,60);
		//Font restartFont = new Font("SansSerif", Font.PLAIN,25);
		 
	}
	
	public Driver() {
		
		JFrame f = new JFrame("Hazz Hoops");
		f.setSize(new Dimension(1800, 1000));
		f.setBackground(new Color(0, 0, 0));
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		f.setVisible(true); 
		/*BufferedImage cursorImg;
		try {
					cursorImg =  ImageIO.read(new File("crosshair img.png"));
					Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
						    cursorImg, new Point(0, 0), "blank cursor");
					f.getContentPane().setCursor(blankCursor);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	*/ 
		

		
		}
	
	
	
	
	
	
	public static void main(String[] arg) {
		Driver f = new Driver();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
			
				
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg32) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg32) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
	
	
}
