import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
	public void run() {

		// Top-level frame in which game components live
		final JFrame frame = new JFrame("Arkanoid");

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);

		// Top Panel Labels
		final JLabel score = new JLabel("| Your Score: 0");
		final JLabel lives = new JLabel("        Lives: 3 |");
		final JLabel highScore = new JLabel("| High Score: " + Highscores.getHighScore());

		// Main playing area
		final GameCourt court = new GameCourt(status, score, lives);
		frame.add(court, BorderLayout.CENTER);

		// Top Panel
		// Note here that when we add an action listener to the reset button, we define
		// it as an
		// anonymous inner class that is an instance of ActionListener with its
		// actionPerformed()
		// method overridden. When the button is pressed, actionPerformed() will be
		// called.
		final JPanel control_panel = new JPanel();
		final JButton reset = new JButton("New Game");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		final JButton instructions = new JButton("Instructions");
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.pauseGame();
				JOptionPane.showMessageDialog(frame,
						"Bounce the ball against the Bricks.\nDestroy all the bricks to win!"
								+ "\n\n        You only have 3 lives. Good luck!" + "\n\nUse your Arrow Keys to control the paddle \n"
								+ "       (PowerUps coming soon in Ver 2.0)\n\n\n\n"
								+ "\nCreated by Satya Prafful"
								+ "\nVer 1.0, 2017",
						"Instructions", JOptionPane.PLAIN_MESSAGE);
				court.resumeGame();
				court.requestFocusInWindow();
				// Pause for another 1 second
			}
		});

		control_panel.add(reset);
		control_panel.add(instructions);
		control_panel.add(lives);
		control_panel.add(score);
		control_panel.add(highScore);

		frame.add(control_panel, BorderLayout.NORTH);

		// Put the frame on the screen
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/**
	 * Main method run to start and run the game. Initializes the GUI elements
	 * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
	 * this in your final submission.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
		Highscores.readFile();
	}
}