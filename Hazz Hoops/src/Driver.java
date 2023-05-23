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

public class Driver extends JPanel implements ActionListener, MouseListener, KeyListener {

	Gym bckg = new Gym();
	OutsideCourt obckg = new OutsideCourt();
	Balanced p1 = new Balanced(50, 50, 20, 50, 1);
	Balanced p2 = new Balanced(50, 50, 20, 50, 2);
	Ball b1 = new Ball();
	Hoop h1 = new Hoop();
	Hoop h2 = new Hoop("HoopRight.png");
	MenuScreen ms = new MenuScreen();
	Scoreboard s1 = new Scoreboard();
	private int points1;
	private int points2;
	private int rand = (int) (Math.random() * 2 + 1);
	private boolean far = false;
	private boolean close = false;
	private boolean freeze = false;
	Rectangle basket1 = new Rectangle(1450, 530, 60, 10);
	Rectangle basket2 = new Rectangle(300, 530, 60, 10);
	boolean PlayerSelect = false;
	boolean HighScore = false;
	Select ps = new Select();
	boolean p1Select = false;
	boolean p2Select = false;
	boolean p1ball = false;
	boolean p2ball = false;
	boolean gbox1 = false;
	boolean gbox2 = false;
	boolean gbox3 = false;
	boolean gameStart = false;
	int picks = 0;
	double timer = 100.0;

	public void paint(Graphics g) {
		super.paintComponent(g);

		ms.paint(g);

		if (PlayerSelect) {
			// randomly selects which gym to choose

			ps.paint(g);
			g.setColor(Color.green);
			if (gbox1) {
				g.fillRect(265, 760, 210, 140);
			}
			if (gbox2) {
				g.fillRect(795, 760, 210, 140);
			}
			if (gbox3) {
				g.fillRect(1340, 760, 210, 140);
			}

			if (picks > 1) {
				gameStart = true;
			}
		}

		if (gameStart) {
			if (rand == 1) {
				bckg.paint(g);
			} else if (rand == 2) {
				obckg.paint(g);
			}
			s1.paint(g);
			b1.paint(g);
			h1.paint(g);
			h2.paint(g);
			p1.paint(g);
			p2.paint(g);

			Font plainFont = new Font("SanSerif", Font.PLAIN, 60);
			g.setFont(plainFont);
			g.setColor(Color.white);
			if (gameStart) {
				g.drawString("Time: " + ((int) timer / 10), 750, 100);
				if (timer >= 0) {
					timer -= 0.5;
				}
			}
			g.drawString("" + points1, 765, 225);
			g.drawString("" + points2, 945, 225);
		}

		if (HighScore) {
			g.drawRect(0, 0, 1000, 2000);
		}

		// Font gameEndFont = new Font("SansSerif", Font.PLAIN,60);
		// Font restartFont = new Font("SansSerif", Font.PLAIN,25);

		g.setColor(Color.black);
		// g.drawRect(300 , 530, 60, 10 );

		if (b1.hit(basket1) && far) {
			points1 += 3;
			// System.out.println(points1);
			far = false;
			p1.reset(1);
			p2.reset(2);
			
			
			
			
			
		}
		if (b1.hit(basket1) && close) {
			points1 += 2;
			// System.out.println(points1);
			close = false;
			p1.reset(1);
			p2.reset(2);
		}
		if (b1.hit(basket2) && far) {
			points2 += 3;
			// System.out.println(points2);
			far = false;
			p1.reset(1);
			p2.reset(2);
		}
		if (b1.hit(basket2) && close) {
			points2 += 2;
			// System.out.println(points2);
			close = false;
			p1.reset(1);
			p2.reset(2);
		}
		if (b1.getReset()) {
			freeze = false;
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
		/*
		 * BufferedImage cursorImg; try { cursorImg = ImageIO.read(new
		 * File("crosshair img.png")); Cursor blankCursor =
		 * Toolkit.getDefaultToolkit().createCustomCursor( cursorImg, new Point(0, 0),
		 * "blank cursor"); f.getContentPane().setCursor(blankCursor);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

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

		// if High Score button is pressed
		if (ms.highscore(arg0) && PlayerSelect == false) {
			HighScore = true;
		}
		// if Menu Screen button is pressed
		if (ms.play(arg0) && HighScore == false) {
			PlayerSelect = true;
		}

		if (ps.changeScreen(arg0)) {
			if (ps.isB1()) {
				gbox1 = true;
				picks++;
			}
		}

		if (ps.changeScreen(arg0)) {
			if (ps.isB2()) {
				gbox2 = true;
				picks++;
			}
		}

		if (ps.changeScreen(arg0)) {
			if (ps.isB3()) {
				gbox3 = true;
				picks++;
			}
		}

		if (ps.changeScreen(arg0)) {
			if (p1Select == true) {

				p2Select = true;
			}
			p1Select = true;

		}

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
		// System.out.println(arg32.getKeyCode());
		if (freeze == false) {

			if (arg32.getKeyCode() == 65) {
				p1.moveLeft();
				b1.moveLeft(p1.getSpeed());
			}
			if (arg32.getKeyCode() == 68) {
				p1.moveRight();
				b1.moveRight(p1.getSpeed());
			}

			if (arg32.getKeyCode() == 87) {
				p1.moveStop();
				b1.moveStop();
				//b1.setVY(0);
				p1.jump();

				freeze = true;
				//far code
				if (p1.getX() <= 750) {
					//b1.rightfar();
					
					
					b1.Thrown();
					far = true;
				}
				
				//close code
				if (p1.getX() > 750) {
					if (p1.getX() > 1050) {
						b1.rightclose();
						close = true;
					} else {
						b1.rightnormal();
						close = true;
					}
				}
			
			
			}
		}
		if (freeze == false) {

			if (arg32.getKeyCode() == 37) {
				p2.moveLeft();
				b1.moveLeft(p2.getSpeed());
			}
			if (arg32.getKeyCode() == 39) {
				p2.moveRight();
				b1.moveRight(p2.getSpeed());
			}

			if (arg32.getKeyCode() == 38) {
				p2.moveStop();
				b1.moveStop();
				b1.setVY(0);
				p2.jump();

				freeze = true;
				if (p2.getX() > 1050) {
					b1.leftfar();
					far = true;
					System.out.println("far");

				}

				if (p2.getX() < 1050) {
					if (p2.getX() < 250) {
						b1.leftclose();
						close = true;
					} else {
						b1.leftnormal();
						close = true;
					}

				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg32) {
		// TODO Auto-generated method stub
		if (arg32.getKeyCode() == 65) {
			if (freeze == false) {
				p1.moveStop();
				b1.moveStop();
			}

		}
		if (arg32.getKeyCode() == 68) {
			if (freeze == false) {
				p1.moveStop();
				b1.moveStop();
			}
		}
		if (arg32.getKeyCode() == 37) {
			if (freeze == false) {
				p2.moveStop();
				b1.moveStop();
			}

		}
		if (arg32.getKeyCode() == 39) {
			if (freeze == false) {
				p2.moveStop();
				b1.moveStop();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
