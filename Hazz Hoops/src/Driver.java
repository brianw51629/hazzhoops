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

	Gym bckg = new Gym();
	Balanced p1 = new Balanced(50,50,20,50);
	Ball b1 = new Ball();
	Hoop h1 = new Hoop();
	Hoop h2 = new Hoop("HoopRight.png");
	private int points;
	private boolean far = false;
	private boolean close = false;
	private boolean freeze = false;
	Rectangle basket1 = new Rectangle(1450 , 530, 60, 10);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		bckg.paint(g);
		h1.paint(g);
		h2.paint(g);
		p1.paint(g);
		b1.paint(g); 
		//Font gameEndFont = new Font("SansSerif", Font.PLAIN,60);
		//Font restartFont = new Font("SansSerif", Font.PLAIN,25);
		 
		g.setColor(Color.black);
		g.drawRect(1450 , 530, 60, 10 );

			if(b1.hit(basket1)&&far) {
				points+=3;
				System.out.println(points);
				far=false;
				p1.reset();
			}
			if(b1.hit(basket1)&&close) {
				points+=2;
				System.out.println(points);
				close=false;
				p1.reset();
			}
			Font plainFont = new Font("SanSerif", Font.PLAIN, 60);
			g.setFont(plainFont);
			g.setColor(new Color(Color.white.getBlue()));
			g.drawString("Score: "+points, 800, 100);
			if(b1.getReset()) {
				freeze=false;
				b1.resetShot(false);
			}
			
			
			
			
			
		
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
		
		if(freeze==false) {
		
		
		if(arg32.getKeyCode()==65) {
			p1.moveLeft();
			b1.moveLeft(p1.getSpeed());
		}
		if(arg32.getKeyCode()==68) {
			p1.moveRight();
			b1.moveRight(p1.getSpeed());
		}
		}
		if(arg32.getKeyCode()==87) {
			p1.shot();
			p1.moveStop();
			if(p1.getX()<=750) {
				b1.far();
				far = true;
				System.out.println("far");
				freeze=true;
			}
			
			if(p1.getX()>750) {
				if(p1.getX()>1050) {
					b1.close();
					close=true;
					System.out.println("close");
					freeze=true;
				}else {
					b1.normal();
					close=true;
				System.out.println("normal");
				freeze=true;
				}
				
			}
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg32) {
		// TODO Auto-generated method stub
		if(arg32.getKeyCode()==65) {
			p1.moveStop();
			b1.moveStop();
		}
		if(arg32.getKeyCode()==68) {
			p1.moveStop();
			b1.moveStop();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
	
	
}
