
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * GameCourt
 * This class holds the primary game logic for how different objects interact
 * with one another. 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	private Paddle paddle; // the Black paddle, keyboard control
	private Ball ball; // the ball, bounces	
	//Add modularity for new levels 
	private Bricks bricks; // the bricks
	private Brick brick; // A particular brick
	Brick[][] brickArray; // BrickArray
	private Ball powerUp = null; // Generic PowerUp 

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text, i.e. "Running..."
	private JLabel lives;
	private Integer livesLeft = 3;
	private JLabel yourScore;
	private Integer currScoreVal = 0;

	// Game constants
	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 350;
	public static final int PADDLE_VELOCITY = 5;

	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 16;

	public GameCourt(JLabel status, JLabel score, JLabel livesLeft) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically with the given
		// INTERVAL. We
		// register an ActionListener with this timer, whose actionPerformed() method is
		// called each
		// time the timer triggers. We define a helper method called tick() that
		// actually does
		// everything that should be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle.setVx(-PADDLE_VELOCITY);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					paddle.setVx(PADDLE_VELOCITY);
				}
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_LEFT: {
					paddle.setVx(0);
					break;
					}

				}

			}
		});
		yourScore = score;
		this.status = status;
		lives = livesLeft;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		File file = new File("src/LevelData.txt");
		paddle = new Paddle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
		bricks = new Bricks(COURT_WIDTH, COURT_HEIGHT, file);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT, 5, Color.BLACK);

		playing = true;
		currScoreVal = 0;
		livesLeft = 3;
		status.setText("Running...");
		yourScore.setText("Your Score: 0");
		lives.setText("        Lives: " + livesLeft.toString() + "| ");

		// Making sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			// Advance the paddle and ball in their current direction.
			paddle.move();
			ball.move();
			
			//Ball intersects Paddle 
			if (ball.intersects(paddle) != 0) {
				ball.hitObj(paddle, ball.intersects(paddle));
			}
			//Ball intersects Brick. If Intersects then set Brick DNE accordingly 
			brickArray = bricks.getBricksArray();
			for (int x = 0; x < Bricks.getColumns(); x++) {
				for (int y = 0; y < Bricks.getRows(); y++) {
					brick = brickArray[x][y];
					if (ball.intersects(brick) != 0 && brick.exists()) {
						ball.hitObj(brick, ball.intersects(brick));
						brick.setDNE();
						/*if (!brick.containsPowerUp()) {
							powerUp = PowerUps.createPowerUp();
							powerUp.move();
						}*/ // Setting up the framework for PowerUps 
						currScoreVal++; // Incrementing Score by 1
						yourScore.setText("Your Score: " + currScoreVal.toString());
						status.setText("Good job!");

					}
				}

				ball.hitWall();

				// check for the round end/lose a life conditions
				if (livesLeft != 0) {
					if (ball.getPy() >= COURT_HEIGHT - 10) {
						playing = true;
						status.setText("You lost a life! Get Ready!");
						repaint();
						livesLeft--;
						int timedelay = 2000;
						if (livesLeft == 0) {
							timedelay = 1;
						}
						lives.setText("        Lives: " + livesLeft.toString() + "| ");
						try {
							Thread.sleep(timedelay);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						ball.setPx(250);
						ball.setPy(170);
						ball.setVx(-2);
						ball.setVy(-3);

					}
				}
				// Finish Game Conditions
				if (livesLeft == 0 || currScoreVal == Bricks.getBlocksCreated()) {
					playing = false;
					if (currScoreVal < Bricks.getBlocksCreated()){
					status.setText("You lose!");
					}
					if (currScoreVal == Bricks.getBlocksCreated()){
					status.setText("You Win!");	
					}
					
					String[] options = { "Yes", "No", "Save Score" };
					int response = JOptionPane.showOptionDialog(null,
							"Your Final Score:" + currScoreVal + "\n" + "Start New Game?\n", "Game Over",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					if (response == 0) {
						reset();
					}
					else if (response == 1) {
						System.exit(0);
					}
					//Handling the Highscores. Only the Top 5 are Printed 
					else if (response == 2) {
						String name = JOptionPane.showInputDialog("Enter your name?");
						Highscores.addNewScore(currScoreVal,name);
						Highscores.writeFile();
						JDialog highScoreDialog = new JDialog();
						JPanel highscores = new JPanel();
						highscores.setLayout(new BoxLayout(highscores, BoxLayout.PAGE_AXIS));
						ArrayList<String> entries = Highscores.topFiveEntries();
						JLabel highScore1 = new JLabel("              1: " + entries.get(0));
						JLabel highScore2 = new JLabel("              2: " + entries.get(1));
						JLabel highScore3 = new JLabel("              3: " + entries.get(2));
						JLabel highScore4 = new JLabel("              4: " + entries.get(3));
						JLabel highScore5 = new JLabel("              5: " + entries.get(4));
						JLabel highscoreTitle = new JLabel("         ~~~~~~TOP 5 HIGHSCORES~~~~~");
						highscores.add(highscoreTitle);
						highscores.add(highScore1);
						highscores.add(highScore2);
						highscores.add(highScore3);
						highscores.add(highScore4);
						highscores.add(highScore5);
						highScoreDialog.add(highscores);
						highScoreDialog.pack();
						highScoreDialog.setSize(300, 300);
						highscores.setVisible(true);
						highScoreDialog.setLocationRelativeTo(null);
						highScoreDialog.setVisible(true);
						currScoreVal = 0;
						livesLeft = 3;
					}
				}
				
				// update the display
				repaint();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (powerUp == null) {
		paddle.draw(g);
		bricks.draw(g);
		ball.draw(g);
		}
		//More PowerUp Framework
		/*else {
			paddle.draw(g);
			bricks.draw(g);
			ball.draw(g);
			powerUp.draw(g);
		}*/ 
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

	/**
	 * @return Remaining lives
	 */
	public int getLivesLeft() {
		return livesLeft;
	}

	public String getScore() {
		return yourScore.toString();
	}

	public void pauseGame() {
		playing = false;
	}

	public void resumeGame() {
		playing = true;
	}
}