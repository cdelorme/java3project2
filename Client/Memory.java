/**
 *
 * Client Memory Game Instance, Display for the game
 *
 * @author Rohan Ganpayte
 * @maintainer Casey DeLorme
 * @version 05-12-2012
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

	JButton[][] tiles;
	private int gameID;


	/* Constructors */

	public Memory(int anID) {

		// Set GameID
		setGameID(anID);

		// Setup 8x8 Array
		tiles = new JButton[6][8];

		// Initialize Display
		init();

	}


	/* Custom Methods */

	private void init() {

		// Set Layout
		setLayout(new GridLayout(6, 8, 5, 5));

		// Set Display Minimum Size
		setMinimumSize(new Dimension(480, 680));

		// Prep Minimum Dimensions for Buttons
		Dimension buttonSize = new Dimension(55, 80);

		// Test ImageIcon Creation
		//ImageIcon anIcon = new ImageIcon("images/1up.gif");

		// Create Tiles
		for (int y=0; y < tiles.length; y++) {
			for (int x=0; x < tiles[y].length; x++) {

				// Create Instance
				tiles[y][x] = new JButton();

				// Create Labeled Instance (For Testing)
				//tiles[x][y] = new JButton("x: " + x + ", y: " + y);

				// Set Icon (Testing)
				//tiles[x][y].setIcon(anIcon);

				// Set Alignment
				tiles[y][x].setVerticalTextPosition(AbstractButton.CENTER);
				tiles[y][x].setHorizontalTextPosition(AbstractButton.CENTER);


				// Set Display Options & Size
				tiles[y][x].setOpaque(true);
				tiles[y][x].setPreferredSize(buttonSize);

				// Set Event Handler
				//tiles[x][y].addActionListener(this);

				// Add to Display
				add(tiles[y][x]);

			}
		}

	}


	/* Mutators */

	private void setGameID(int anID) {
		gameID = anID;
	}


	/* Accessors */

	private int getGameID() {
		return gameID;
	}


}