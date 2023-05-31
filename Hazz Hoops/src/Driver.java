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
	Rectangle basket1 = new Rectangle(1450, 375, 80, 10);
	Rectangle basket2 = new Rectangle(265, 375, 80, 10);
	Rectangle back1 = new Rectangle(1550, 250, 1, 110);
	Rectangle back2 = new Rectangle(275, 250, 1, 110);
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
	boolean possession = true;
	boolean Player1Block;
	boolean Player2Block;
	String player1Type = "";
	String player2Type = "";
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		//System.out.println(picks);
		ms.paint(g);
		
		if (PlayerSelect) {
			//paints the select screen
			ps.paint(g);
			g.setColor(Color.green);
			
			//if box 1 is clicked
			if (gbox1) {
				if(picks==1) {
					Player1 = new Balanced(1);
					Player1.changePicture("/imgs/MediumPlayerDefault.png");
					g.fillRect(265, 760, 210, 140);
					player1Type="Medium";
				}
				if(picks==2) {
					Player2 = new Balanced(2);
					Player2.changePicture("/imgs/MediumPlayerDefault.png");
					player2Type="Medium";
				}
				ps.setB1(false);
				gbox1 = false;
			}
			
			
			if (gbox2) {
				g.fillRect(795, 760, 210, 140);
				if(picks==1) {
					Player1 = new Short(1);
					Player1.changePicture("/imgs/ShortPlayerDefault.png");
					player1Type="Short";
				}
				if(picks==2) {
					Player2 = new Short(2);
					Player2.changePicture("/imgs/ShortPlayerDefault.png");
					player2Type="Short";
				}
				ps.setB2(false);
				gbox2 = false;
			}
			
			
			if (gbox3) {
				g.fillRect(1340, 760, 210, 140);
				if(picks==1) {
					Player1 = new Tall(1);
					Player1.changePicture("/imgs/TallPlayerDefault.png");
					player1Type="Tall";
				}
				if(picks==2) {
					Player2 = new Tall(2);
					Player2.changePicture("/imgs/TallPlayerDefault.png");
					player2Type="Tall";
				}
				ps.setB3(false);
				gbox3 = false;
			}

			if (picks > 1) {
				gameStart = true;
				PlayerSelect = false;
			}
		}
		
		//System.out.println(picks);
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
					timer -= 0.25;
				}
			}
			g.drawString("" + points1, 765, 225);
			g.drawString("" + points2, 945, 225);
		}
		
		//selecting Highscore
		if (HighScore&&(!gameStart&&!PlayerSelect)) {
			g.drawRect(0, 0, 1000, 2000);
		}
		
		g.setColor(Color.green);
		g.fillOval(860,600,10,10);
		
		//init ball starts with player 1 (left player)
		b1.setPossession(possession);
		
		
		//if ball makes it in hoop +3 points
		if (b1.hit(basket1) && shot) {
			shot = false;
			if(Player1.getX() <= 860) {
				points1 += 3;				
			}else {
				points1+=2;
			}
			
			
			Player1.reset(1);
			Player2.reset(2);	
		}
		if (b1.hit(basket2) && shot) {
			shot = false;
			if(Player2.getX() >= 860) {
				points2 += 3;				
			}else {
				points2+=2;
			}
			Player1.reset(1);
			Player2.reset(2);
		}
		
		
		
		
		
		
		if (b1.getReset()) {
			freeze = false;
			if(possession) {
				possession = false;
			}else if(possession == false){
				possession = true;
			}
			b1.resetShot(false);
		}
		if(b1.hit(back1)) {
			b1.setVX(-5);
		}
		if(b1.hit(back2)) {
			b1.setVX(-5);
		}
		
		if(p1Right) {
			Player1.moveRight();
			if(possession) {
				Player1.moveRight();
				b1.setX(Player1.getX()+165);
			}
			
		}
		if(p2Right) {
			Player2.moveRight();
			if(possession==false) {
				b1.setX(Player2.getX()+35);
			}
			
		}
		if(p1Left) {
			Player1.moveLeft();
			if(possession) {
				b1.setX(Player1.getX()+140);
			}
			
		}
		if(p2Left) {
			Player2.moveLeft();
			if(possession==false) {
				b1.setX(Player2.getX()+35);
			}
		}
		
		//block code
		if(Player1Block) {
			if(Player1.block(b1.getX(), b1.getY())) {
				b1.setVY(3);
				b1.setVX(0);
				Player1.reset(1);
				Player2.reset(2);
				Player1Block=false;
				possession = false;
			}
		}
		if(Player2Block) {
			if(Player2.block(b1.getX(), b1.getY())) {
				b1.setVY(3);
				b1.setVX(0);
				Player1.reset(1);
				Player2.reset(2);
				Player2Block=false;
				possession = true;
			}
		}
		if(Player1.getVY()>0) {
			Player1Block=false;
			Player1.changePicture("/imgs/"+player1Type+"PlayerDefault.png");
		}
		if(Player2.getVY()>0) {
			Player2Block=false;
			Player2.changePicture("/imgs/"+player2Type+"PlayerDefault.png");
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
		
		//playerSelect Screen
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
		//System.out.println(arg32.getKeyCode());
		/*
		 * 65 = A
		 * 68 = D
		 * 87 = W
		 * 83 = S
		 * 37 = Left Arrow
		 * 39 = Right Arrow 
		 * 38 = Up Arrow
		 * 40 = Down Arrow
		 */
		
		if (freeze == false) {

			if (arg32.getKeyCode() == 65) {//A button
				p1Left = true;
			}
			if (arg32.getKeyCode() == 68) {//D button
				p1Right = true;
			}
			
			if (arg32.getKeyCode() == 87) {//W button
				p1Left=false;
				p1Right=false;
				p2Left=false;
				p2Right=false;
				Player1.moveStop();
				Player2.moveStop();
				b1.moveStop();
				Player1.jump();
				if(possession) {
				b1.setVY(0.0);
				b1.thrown(1);
				
				freeze = true;
				shot = true;
				b1.setShot(true);
				}
			}
			 
		}
		if(possession==false) {
			if (arg32.getKeyCode() == 83) {//S button
				//insert block method for player 1
				Player1Block = true;
				Player1.changePicture("/imgs/"+player1Type+"PlayerJump.png");
			}
		}
		if(possession) {
			if (arg32.getKeyCode() == 40) {//down arrow button
				//insert block method for player 2
				Player2Block = true;
				Player2.changePicture("/imgs/"+player2Type+"PlayerJump.png");
				
			}
		}
		
		
		if (freeze == false) {
			//move left
			if (arg32.getKeyCode() == 37) {
				p2Left = true;
			}
			//move right
			if (arg32.getKeyCode() == 39) {
				p2Right = true;
			}
			
			
			
			//jump button
			if (arg32.getKeyCode() == 38) {
				p1Left=false;
				p1Right=false;
				p2Left=false;
				p2Right=false;
				Player1.moveStop();
				Player2.moveStop();
				b1.moveStop();
				Player2.jump();
				if(possession==false) {
				b1.setVY(0.0);
				b1.thrown(2);
				
				freeze = true;
				shot = true;
				b1.setShot(true);
				}
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
