/**
 *
 * Client Memory Game Instance, Display for the game
 *
 * @author Rohan Ganpayte
 * @maintainer Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Memory extends JPanel implements Game {


	/* Static */


	/* Properties */

	private ClientConnection cc;
	private GameMediator gm;
	private JButton[][] tiles;
	private JLabel[] players;
	private JLabel[] scores;
	private int gameID;
	private int counter;


	/* Constructors */

	public Memory(ClientConnection aCC, GameMediator aGM, int anID, String thePlayer, String anOpponent) {

		// Array Initialization & Default Values
		tiles = new JButton[3][6];
		scores = new JLabel[2];
		players = new JLabel[2];
		players[0] = new JLabel();
		players[1] = new JLabel();
		scores[0] = new JLabel();
		scores[1] = new JLabel();
		players[0].setText(thePlayer);
		players[1].setText(anOpponent);
		scores[0].setText("0");
		scores[1].setText("0");
		counter = 0;

		// Set ClientConnection
		setCC(aCC);

		// Set GameMediator
		setGM(aGM);

		// Set GameID
		setGameID(anID);

		// Initialize Display
		init();

	}


	/* Custom Methods */

	private void init() {

		// Border Layout
		setLayout(new BorderLayout());

		// Create a Grid to store JButton Tiles
		JPanel gameGrid = new JPanel();

		// Set Layout for GameGrid
		gameGrid.setLayout(new GridLayout(3, 6, 5, 5));

		// Prep Minimum Dimensions for Buttons
		Dimension buttonSize = new Dimension(55, 80);

		// Create Tiles
		for (int y=0; y < tiles.length; y++) {
			for (int x=0; x < tiles[y].length; x++) {

				// Create Instance
				tiles[y][x] = new JButton();

				// Set Alignment
				tiles[y][x].setVerticalTextPosition(AbstractButton.CENTER);
				tiles[y][x].setHorizontalTextPosition(AbstractButton.CENTER);

				// Set Display Options & Size
				tiles[y][x].setOpaque(true);
				tiles[y][x].setPreferredSize(buttonSize);

				// Add Event Handler
				tiles[y][x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						// Can I get the coordinates of this item easily?
						for (int j = 0; j < tiles.length; j++) {
							for (int i = 0; i < tiles[j].length; i++) {

								// If Match
								if (ae.getSource().equals(tiles[j][i]) && tiles[j][i].getIcon() == null && counter < 2) {

									// Create Command to send coordinates
									Hashtable<String, String> aCommand = new Hashtable<String, String>();
									aCommand.put("SYSTEM", "GAME");
									aCommand.put("COMMAND", "SHOW");
									aCommand.put("GAMEID", Integer.toString(getGameID()));
									aCommand.put("X", Integer.toString(j));
									aCommand.put("Y", Integer.toString(i));

									// Send Command
									getCC().sendCommand(aCommand);

								}

							}
						}

					}
				});

				// Add to Display
				gameGrid.add(tiles[y][x]);

			}
		}

		// Append a Player Scoreboard at the bottom!
		JPanel playerScore = new JPanel();
		playerScore.setLayout(new GridLayout(1, 4));
		playerScore.add(players[0]);
		playerScore.add(scores[0]);
		playerScore.add(players[1]);
		playerScore.add(scores[1]);

		// add GameGrid & Score Board to Display
		add(gameGrid, BorderLayout.CENTER);
		add(playerScore, BorderLayout.SOUTH);

	}

	public void updateScore(String playerName) {

		// Cycle Players
		for (int x = 0; x < players.length; x ++) {

			// Name Match
			if (players[x].getText().equals(playerName)) {

				// ++ Score
				scores[x].setText(Integer.toString(Integer.parseInt(scores[x].getText()) + 1));

			}

		}

		// Prep check for end-game condition
		int count = 0;
		for (int y=0; y < tiles.length; y++) {
			for (int x=0; x < tiles[y].length; x++) {

				// Count if Enabled
				if (tiles[y][x].isEnabled()) count++;

			}
		}

		// Check count for game over
		if (count == 0) {

			// Determine winner via scores
			String winner = "You Win";
			int scoreOne = Integer.parseInt(scores[0].getText());
			int scoreTwo = Integer.parseInt(scores[1].getText());
			if (scoreTwo > scoreOne) {
				winner = players[1].getText() + " Wins!";
			}

			// JOptionPane user
			JOptionPane.showMessageDialog(null, winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);

			// Kill Game Instance
			killGame();

		}

	}

	public void showTile(String anImage, int theX, int theY) {

		// Load Image Icon
		ImageIcon anIcon = new ImageIcon(getClass().getResource("images/" + anImage));

		// Set ImageIcon to specified coordinates
		tiles[theX][theY].setIcon(anIcon);

		// Increment Counter
		counter++;

		if (counter == 2) {

			// On second tile run this
			new Thread(new Runnable() {
				public void run() {

					try {
						Thread.sleep(3000);
					} catch (InterruptedException ie) {}

					// Remove all imageicons
					for (int j = 0; j < tiles.length; j++) {
						for (int i = 0; i < tiles[j].length; i++) {

							tiles[j][i].setIcon(null);

						}
					}

					// reset counter
					counter = 0;

				}
			}).start();

		}

	}

	public void removeTile(int theX, int theY) {

		// Disable & Hide JButton at given coordinates
		tiles[theX][theY].setEnabled(false);
		tiles[theX][theY].setVisible(false);

	}

	public void killGame() {

		// Send Kill Request to Game Mediator
		getGM().killGame(this);

		// Unset important values
		setCC(null);
		setGM(null);

	}



	/* Mutators */

	private void setGameID(int anID) {
		gameID = anID;
	}

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}

	private void setGM(GameMediator aGM) {
		gm = aGM;
	}


	/* Accessors */

	public int getGameID() {
		return gameID;
	}

	private ClientConnection getCC() {
		return cc;
	}

	private GameMediator getGM() {
		return gm;
	}


}