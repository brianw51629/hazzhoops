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
import java.util.Arrays;
import javax.swing.JLabel;

public class Driver extends JPanel implements ActionListener, MouseListener, KeyListener {

	Gym bckg = new Gym();
	OutsideCourt obckg = new OutsideCourt();
	Character Player1 = new Character();
	Character Player2 = new Character();
	Ball b1 = new Ball();
	Hoop h1 = new Hoop("HoopLeft.png", "left");
	Hoop h2 = new Hoop("HoopLeft.png", "right");
	MenuScreen ms = new MenuScreen();
	Scoreboard s1 = new Scoreboard();
	HighScore hs = new HighScore();
	private int points1;
	private int points2;
	private int rand = (int) (Math.random() * 2 + 1);
	private boolean freeze = false;
	boolean freeze2 = false;
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
	double timer = 300.0;
	double endTimer = 50.0;
	boolean gameEnd = false;
	boolean shot = false;
	boolean possession = true; //true is possession for P1, false is possession for P2
	boolean Player1Block;
	boolean Player2Block;
	String player1Type = "";
	String player2Type = "";
	int[] list = new int[10];
	boolean updateHS = false;
	Music bg = new Music("background.wav", true);

	Music bang = new Music("bangSound.wav", false);
	Music block = new Music("block sound.wav", false);
	Music buzz = new Music("endBuzzer.wav", false);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		ms.paint(g);
		if(updateHS) {
			hs.list(list);
			updateHS = false;
		}
		if (PlayerSelect) {
			// paints the select screen
			ps.paint(g);
			g.setColor(Color.green);

			// if box 1 is clicked
			if (gbox1) {
				if (picks == 1) {
					Player1 = new Balanced(1);
					Player1.changePicture("/imgs/MediumPlayerDefault.png");
					g.fillRect(265, 760, 210, 140);
					player1Type = "Medium";
				}
				if (picks == 2) {
					Player2 = new Balanced(2);
					Player2.changePicture("/imgs/MediumPlayerDefault.png");
					player2Type = "Medium";
				}
				ps.setB1(false);
				gbox1 = false;
			}
			//if box 2 is clicked
			if (gbox2) {
				g.fillRect(795, 760, 210, 140);
				if (picks == 1) {
					Player1 = new Short(1);
					Player1.changePicture("/imgs/ShortPlayerDefault.png");
					player1Type = "Short";
				}
				if (picks == 2) {
					Player2 = new Short(2);
					Player2.changePicture("/imgs/ShortPlayerDefault.png");
					player2Type = "Short";
				}
				ps.setB2(false);
				gbox2 = false;
			}
			//if box 3 is clicked
			if (gbox3) {
				g.fillRect(1340, 760, 210, 140);
				if (picks == 1) {
					Player1 = new Tall(1);
					Player1.changePicture("/imgs/TallPlayerDefault.png");
					player1Type = "Tall";
				}
				if (picks == 2) {
					Player2 = new Tall(2);
					Player2.changePicture("/imgs/TallPlayerDefault.png");
					player2Type = "Tall";
				}
				ps.setB3(false);
				gbox3 = false;
			}
			//if both players select
			if (picks > 1) {
				gameStart = true;
				PlayerSelect = false;
				bg.play();
			}
		}
		
		//painting the actual game
		if (gameStart) {
			if (rand == 1) { //chooses between gym or outside court
				bckg.paint(g);
			} else if (rand == 2) {
				obckg.paint(g);
				h1.changePicture("OutHoop.png");
				h2.changePicture("OutHoop.png");
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
			
			//timer
			if (gameStart) {
				g.drawString("Time: " + ((int) timer / 10), 750, 100);
				if (timer >= 0) {
					timer -= 0.25;
				}
				if (timer == 0) {
					buzz.play();
				}
			}
			g.drawString("" + points1, 765, 225);
			g.drawString("" + points2, 945, 225);
		}

		// selecting Highscore
		if (HighScore && (!gameStart && !PlayerSelect)) {
			Font BIGplainFont = new Font("SanSerif", Font.PLAIN, 75);
			g.fillRect(0, 0, 2000, 2000);
			g.setColor(Color.white);
			g.setFont(BIGplainFont);
			g.drawString("HighScores", 650, 100);
			for(int i = 0;i<10;i++) {
				g.drawString(list[i]+"", 800, (i*75)+200);
			}
		}
		if (timer == 0) {
			gameEnd = true;
		}
		
		//Code for saying who wins
		if (gameEnd) {
			g.setColor(Color.black);
			freeze = true;
			if (points1 > points2) {
				g.drawString("Player 1 Wins", 700, 350);
			} else if (points2 > points1) {
				g.drawString("Player 2 Wins", 700, 350);
			} else {
				g.drawString("TIE", 790, 350);
			}
			if (endTimer >= 0) {
				endTimer -= 0.25;
			}
			if (endTimer == 0) {
				updateList1();
				updateList2();
				gameStart = false;
				PlayerSelect = false;
				HighScore = true;
				updateHS = true;
			}

		}

		// initially ball starts with player 1 (left player)
		b1.setPossession(possession);

		// Player 1 makes the shot
		if (b1.hit(basket1) && shot) {
			bang.play();
			shot = false;
			if (Player1.getX() <= 860) { // +3 points
				points1 += 3;
			} else { // +2 points
				points1 += 2;
			}
			freeze = false;
			freeze2 = false;

			Player1.reset(1);
			Player2.reset(2);
			
			b1.setShot(true);
		}
		
		//Player 2 makes the shot
		if (b1.hit(basket2) && shot) {
			bang.play();
			shot = false;
			if (Player2.getX() >= 860) {
				points2 += 3;
			} else {
				points2 += 2;
			}
			
			freeze = false;
			freeze2 = false;
			
			Player1.reset(1);
			Player2.reset(2);
			
			b1.setShot(true);
		}
		
		
		//if the ball gets reset then it swaps the possession of the ball
		if (b1.getReset()) {
			freeze = false;
			if (possession) { //P1 makes the shot
				possession = false; //P2 gets the ball next
			} else if (possession == false) { //P2 has the ball
				possession = true; //then P1 gets the shot
			}
			b1.resetShot(false);
		}
		
		
		//bounces off the back board
		if (b1.hit(back1)) {
			b1.setVX(-5);
		}
		if (b1.hit(back2)) {
			b1.setVX(-5);
		}
		
		
		
		//player moving right
		if (p1Right) {
			Player1.moveRight();
			if (possession) {
				Player1.moveRight();
				b1.setX(Player1.getX() + 165);
			}
		}
		if (p2Right) {
			Player2.moveRight();
			if (possession == false) {
				b1.setX(Player2.getX() + 35);
			}
		}
		
		//player moving left
		if (p1Left) {
			Player1.moveLeft();
			if (possession) { //has ball
				b1.setX(Player1.getX() + 140);
			}
		}
		if (p2Left) {
			Player2.moveLeft();
			if (possession == false) { //has ball
				b1.setX(Player2.getX() + 35);
			}
		}

		// block code
		if (Player1Block) {
			if (Player1.block(b1.getX(), b1.getY())) {
				block.play();
				b1.setVY(3);
				b1.setVX(0);
				freeze = false;
				freeze2 = false;
				possession = true; //if blocked then possession goes to P2
				Player1Block = false;
			}
		}
		if (Player2Block) {
			if (Player2.block(b1.getX(), b1.getY())) {
				block.play();
				b1.setVY(3);
				b1.setVX(0);
				freeze = false;
				freeze2 = false;
				possession = false; //if blocked then possession goes to P1
				Player2Block = false;
			}
		}
		
		
		//if player 1 is jumping then he is not blocking
		if (Player1.getVY() > 0) {
			Player1Block = false;
			Player1.changePicture("/imgs/" + player1Type + "PlayerDefault.png");
		}
		if (Player2.getVY() > 0) {
			Player2Block = false;
			Player2.changePicture("/imgs/" + player2Type + "PlayerDefault.png");
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
		list=Arrays.copyOf(hs.origList(), 10);
	}

	public static void main(String[] arg) {
		Driver f = new Driver();
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		// playerSelect Screen
		if (PlayerSelect) {
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
	
	//saving lists for the high score code
	public void updateList1() {
		for(int i = 0;i<10;i++) {
			if(points1>list[i]) {
				for(int j = list.length-1;j>i;j--) {
					list[j]=list[j-1];
				}
				list[i]=(points1);
				break;
			}
		}
	}
	public void updateList2() {
		for(int i = 0;i<10;i++) {
			if(points2>list[i]) {
				for(int j = list.length-1;j>i;j--) {
					list[j]=list[j-1];
				}
				list[i]=(points2);
				break;
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
		// System.out.println(arg32.getKeyCode());
		/*
		 * 65 = A 68 = D 87 = W 83 = S 37 = Left Arrow 39 = Right Arrow 38 = Up Arrow 40
		 * = Down Arrow
		 */
		if (gameStart) {
			if (freeze == false) {

				if (arg32.getKeyCode() == 65) {// A button
					p1Left = true;
				}
				if (arg32.getKeyCode() == 68) {// D button
					p1Right = true;
				}

				if (arg32.getKeyCode() == 87) {// W button
					p1Left = false;
					p1Right = false;
					//p2Left = false;
					//p2Right = false;
					Player1.moveStop();
					//Player2.moveStop();

					if (possession) {
						b1.moveStop();
						Player1.jump();
						b1.setVY(0.0);
						b1.thrown(1);

						freeze = true;
						shot = true;
						//b1.setShot(true);
					}
				}

			}
			if (possession == false) { //if P1 doesn't have the ball then he can block
				if (arg32.getKeyCode() == 83) {// S button
					// insert block method for player 1
					Player1Block = true;
					Player1.changePicture("/imgs/" + player1Type + "PlayerJump.png");
				}
			}
			if (possession) { //if P2 doesn't have the ball then he can block
				if (arg32.getKeyCode() == 40) {// down arrow button
					// insert block method for player 2
					Player2Block = true;
					Player2.changePicture("/imgs/" + player2Type + "PlayerJump.png");

				}
			}

			if (freeze2 == false) {
				// move left
				if (arg32.getKeyCode() == 37) { //left arrow button
					p2Left = true;
				}
				// move right
				if (arg32.getKeyCode() == 39) { //right arrow button
					p2Right = true;
				}

				// jump button
				if (arg32.getKeyCode() == 38) { //up arrow button
					p1Left = false;
					p1Right = false;
					//p2Left = false;
					//p2Right = false;
					Player1.moveStop();
					//Player2.moveStop();

					if (possession == false) {//if player 2 has possession
						b1.moveStop();
						Player2.jump();
						b1.setVY(0.0);
						b1.thrown(2);

						freeze2 = true;
						shot = true;
						//b1.setShot(true);
					}
				}

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg32) {
		// TODO Auto-generated method stub
		if (gameStart) {
			if (arg32.getKeyCode() == 65) { //A button
				if (freeze == false) {
					p1Left = false;
					Player1.moveStop();
					b1.moveStop();
				}

			}
			if (arg32.getKeyCode() == 68) { //D button
				if (freeze == false) {
					p1Right = false;
					Player1.moveStop();
					b1.moveStop();
				}
			}
			if (arg32.getKeyCode() == 37) { //Left arrow
				if (freeze2 == false) {
					p2Left = false;
					Player2.moveStop();
					b1.moveStop();
				}

			}
			if (arg32.getKeyCode() == 39) { //Right arrow
				if (freeze2 == false) {
					p2Right = false;
					Player2.moveStop();
					b1.moveStop();
				}
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
