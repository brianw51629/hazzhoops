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
	Character Player1 = new Character();
	Character Player2 = new Character();
	Ball b1 = new Ball();
	Hoop h1 = new Hoop();
	Hoop h2 = new Hoop("HoopRight.png");
	MenuScreen ms = new MenuScreen();
	Scoreboard s1 = new Scoreboard();
	private int points1;
	private int points2;
	private int rand = (int) (Math.random() * 2 + 1);
	//private boolean far = false;
	//private boolean close = false;
	private boolean freeze = false;
	Rectangle basket1 = new Rectangle(1450, 400, 60, 10);
	Rectangle basket2 = new Rectangle(300, 350, 60, 10);
	Rectangle back1 = new Rectangle(1550, 250, 1, 100);
	Rectangle back2 = new Rectangle(200, 250, 1, 100);
	boolean PlayerSelect = false;
	boolean HighScore = false;
	Select ps = new Select();
	boolean Player1Select = false;
	boolean Player2Select = false;
	boolean Player1ball = false;
	boolean Player2ball = false;
	boolean gbox1 = false;
	boolean gbox2 = false;
	boolean gbox3 = false;
	boolean gameStart = false;
	boolean p1Right = false;
	boolean p2Right = false;
	boolean p1Left = false;
	boolean p2Left = false;
	int picks = 0;
	double timer = 100.0;
	boolean shot = false;
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		System.out.println(picks);
		ms.paint(g);

		if (PlayerSelect) {
			// randomly selects which gym to choose

			ps.paint(g);
			g.setColor(Color.green);
			if (gbox1) {
				g.fillRect(265, 760, 210, 140);
				if(picks==1) {
					Player1 = new Balanced(1);
					Player1.changePicture("/imgs/MediumPlayerDefault.png");
				}
				if(picks==2) {
					Player2 = new Balanced(2);
					Player2.changePicture("/imgs/MediumPlayerDefault.png");
				}
				ps.setB1(false);
			}
			if (gbox2) {
				g.fillRect(795, 760, 210, 140);
				if(picks==1) {
					Player1 = new Short(1);
					Player1.changePicture("/imgs/ShortPlayerDefault.png");
					
				}
				if(picks==2) {
					Player2 = new Short(2);
					Player2.changePicture("/imgs/ShortPlayerDefault.png");
					
				}
				ps.setB2(false);
			}
			if (gbox3) {
				g.fillRect(1340, 760, 210, 140);
				if(picks==1) {
					Player1 = new Tall(1);
					Player1.changePicture("/imgs/TallPlayerDefault.png");
				}
				if(picks==2) {
					Player2 = new Tall(2);
					Player2.changePicture("/imgs/TallPlayerDefault.png");
				}
				ps.setB3(false);
			}

			if (picks > 1) {
				gameStart = true;
				PlayerSelect = false;
			}
		}
		System.out.println(picks);
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
			Player1.paint(g);
			Player2.paint(g);

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

		if (HighScore&&(!gameStart&&!PlayerSelect)) {
			g.drawRect(0, 0, 1000, 2000);
		}

		// Font gameEndFont = new Font("SansSerif", Font.PLAIN,60);
		// Font restartFont = new Font("SansSerif", Font.PLAIN,25);

		g.setColor(Color.blue);
		g.fillRect(1550, 250, 1, 100);
		
		if (b1.hit(basket1)&&shot) {
			shot=false;
			points1 += 3;
			Player1.reset(1);
			Player2.reset(2);	
		}
		/*
		if (b1.hit(basket1)) {
			points1 += 2;
			Player1.reset(1);
			Player2.reset(2);
		}
		*/
		if (b1.hit(basket2)) {
			points2 += 3;
			Player1.reset(1);
			Player2.reset(2);
		}
		if (b1.hit(basket2)) {
			points2 += 2;
			Player1.reset(1);
			Player2.reset(2);
		}
		if (b1.getReset()) {
			freeze = false;
			b1.resetShot(false);
		}
		if(b1.hit(back1)) {
			b1.setVX(-3);
		}
		
		if(p1Right) {
			Player1.moveRight();
			b1.moveRight(Player1.getSpeed());
		}
		if(p2Right) {
			Player2.moveRight();
			b1.moveRight(Player2.getSpeed());
		}
		if(p1Left) {
			Player1.moveLeft();
			b1.moveLeft(Player1.getSpeed());
		}
		if(p2Left) {
			Player2.moveLeft();
			b1.moveLeft(Player2.getSpeed());
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
		if (ms.highscore(arg0) && PlayerSelect == false) {
			HighScore = true;
		}
		// if Menu Screen button is pressed
		if (ms.play(arg0) && HighScore == false) {
			PlayerSelect = true;
		}
		if(PlayerSelect) {
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
		}
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
		
		
		/*
		if (ps.changeScreen(arg0)) {
			if (Player1Select == true) {

				Player2Select = true;
			}
			Player1Select = true;

		}
		*/

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
		/*
		 * 65 = A
		 * 68 = D
		 * 87 = W
		 * 37 = Left Arrow
		 * 39 = Right Arrow 
		 * 
		 */
		
		if (freeze == false) {

			if (arg32.getKeyCode() == 65) {//A button
				p1Left = true;
			}
			if (arg32.getKeyCode() == 68) {//D button
				p1Right = true;
			}
			
			if (arg32.getKeyCode() == 87) {//W button
				Player1.moveStop();
				b1.moveStop();
				//b1.setVY(0);
				Player1.jump();
				b1.setVY(0.0);
				b1.setY(755);
				if(Player1.getX()>1000) {
					b1.thrown(1);
				}else {
					b1.thrown(1);
				}
				
				freeze = true;
				shot = true;
				//far code
				
				
				//close code
				
			
			
			}
			 
		}
		if (freeze == false) {
			if (arg32.getKeyCode() == 37) {
				p2Left = true;
			}
			if (arg32.getKeyCode() == 39) {
				p2Right = true;
			}

			if (arg32.getKeyCode() == 38) {
				Player2.moveStop();
				b1.moveStop();
				b1.setVY(0);
				Player2.jump();
				
				freeze = true;
				
				/*
				if (Player2.getX() > 1050) {
					//b1.leftfar();
					//far = true;
					System.out.println("far");

				}

				if (Player2.getX() < 1050) {
					if (Player2.getX() < 250) {
						//b1.leftclose();
						//close = true;
					} else {
						//b1.leftnormal();
						//close = true;
					}

				}
				
				*/
			}
			
		}
		

	}

	@Override
	public void keyReleased(KeyEvent arg32) {
		// TODO Auto-generated method stub
		if (arg32.getKeyCode() == 65) {
			if (freeze == false) {
				p1Left=false;
				Player1.moveStop();
				b1.moveStop();
			}

		}
		if (arg32.getKeyCode() == 68) {
			if (freeze == false) {
				p1Right=false;
				Player1.moveStop();
				b1.moveStop();
			}
		}
		if (arg32.getKeyCode() == 37) {
			if (freeze == false) {
				p2Left = false;
				Player2.moveStop();
				b1.moveStop();
			}

		}
		if (arg32.getKeyCode() == 39) {
			if (freeze == false) {
				p2Right = false;
				Player2.moveStop();
				b1.moveStop();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
