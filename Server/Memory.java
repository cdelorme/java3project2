/**
 *
 * Server Memory Game Instance Data Storage System
 *
 * @author Rohan Ganpayte
 * @maintainer Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;


public class Memory implements Game {


	/* Static */

	private static String[] images = {
		"1up.gif",
		"coin10.gif",
		"coin20.gif",
		"flower.gif",
		"mushroom.gif",
		"star.gif"
	};

	/* Properties */

	private int gameID;
	private int turn;
	private User[] players;
	private ArrayList<MemoryTile> tiles;
	private MemoryTile lastSelected;
	private GameMediator gm;


	/* Constructors */

	public Memory(GameMediator aGM, int aGameID, User[] users) {

		// Set defaults
		turn = 0;
		lastSelected = null;

		// Prepare Tile Array List
		setTiles(new ArrayList<MemoryTile>());

		// Set GM
		setGM(aGM);

		// Set Game Instance ID
		setGameID(aGameID);

		// Assign Players
		players = users;

		// Initialize the Game Instance
		init();

	}


	/* Custom Methods */

	private void init() {

		// Create Temporary Storage Array
		ArrayList<MemoryTile> tmpTiles = new ArrayList<MemoryTile>();

		// Create 3 by 6 instances of tiles
		for (int x=0; x < 3; x++) {
			for (int y=0; y < 6; y++) {

				// Add New Instance to Array
				tmpTiles.add(new MemoryTile(x, y));

			}
		}

		// Create a random object
		Random myRandom = new Random();

		// While tmpTiles contains elements
		while (tmpTiles.size() > 0) {

			// Select Random Image
			int anImage = myRandom.nextInt(images.length);

			// Now pick one from tmpImages
			int rndTile = myRandom.nextInt(tmpTiles.size());

			// Assign the image to the tile
			tmpTiles.get(rndTile).setImage(images[anImage]);

			// Transfer the record to the tiles array
			getTiles().add(tmpTiles.remove(rndTile));

			// Rinse & repeat the same operation
			rndTile = myRandom.nextInt(tmpTiles.size());
			tmpTiles.get(rndTile).setImage(images[anImage]);
			getTiles().add(tmpTiles.remove(rndTile));

		}

		// Method to send game info to both users
		startGame();

	}

	private void startGame() {

		// Create new Command to start the game instance
		Hashtable<String, String> aCommand = new Hashtable<String, String>();
		aCommand.put("SYSTEM", "GAME");
		aCommand.put("COMMAND", "NEWGAME");
		aCommand.put("GAMEID", Integer.toString(getGameID()));

		// Loop both players & use xor to send each the appropriate create game message
		for (int x = 0; x < players.length; x++) {

			// Set Opponent Username via xor
			aCommand.put("OPPONENT", players[(x^1)].getUserName());
			players[x].sendCommand(aCommand);

		}

	}

	public void showTile(User aUser, int theX, int theY) {

		// Check that it is aUser's turn
		if (players[turn].equals(aUser)) {

			String tileName = "";
			MemoryTile tmp = null;

			// Find Matching Tile to Display
			for (MemoryTile mt : getTiles()) {

				// If Match Found
				if (mt.getX() == theX && mt.getY() == theY) {

					// Apply image name
					tileName = mt.getImage();

					// Grab Tile
					tmp = mt;

				}

			}

			if (!tileName.equals("")) {

				// Create aCommand & Set Tile Image Name at selected Coordinates
				Hashtable<String, String> aCommand = new Hashtable<String, String>();
				aCommand.put("SYSTEM", "GAME");
				aCommand.put("COMMAND", "SHOW");
				aCommand.put("GAMEID", Integer.toString(getGameID()));
				aCommand.put("X", Integer.toString(theX));
				aCommand.put("Y", Integer.toString(theY));
				aCommand.put("TILENAME", tileName);

				// Return to user
				aUser.sendCommand(aCommand);

				// if lastSelected is null
				if (lastSelected == null) {

					// Set to Selected
					lastSelected = tmp;

				} else {

					// Compare Images
					if (lastSelected.getImage().equals(tileName)) {

						// Create Update Score Command
						Hashtable<String, String> aCommand2 = new Hashtable<String, String>();
						aCommand2.put("SYSTEM", "GAME");
						aCommand2.put("COMMAND", "UPDATE");
						aCommand2.put("GAMEID", Integer.toString(getGameID()));
						aCommand2.put("PLAYER", aUser.getUserName());
						aCommand2.put("X1", Integer.toString(theX));
						aCommand2.put("Y1", Integer.toString(theY));
						aCommand2.put("X2", Integer.toString(lastSelected.getX()));
						aCommand2.put("Y2", Integer.toString(lastSelected.getY()));

						// Send Command to both users
						for (User u : players) {

							u.sendCommand(aCommand2);

						}

						// Remove lastSelected & tmp
						getTiles().remove(tmp);
						getTiles().remove(lastSelected);

						// Check if any tiles remain
						if (getTiles().size() == 0) {

							// Kill Game Method
							killGame();

						}

					}

					// Null lastSelected
					lastSelected = null;

					// Swap Turns
					turn ^= 1;

				}

			}

		}

	}

	private void killGame() {

		// Call to factory via mediator to end self via gameID
		getGM().killGame(this);

		// Unset users to new array of size 0
		players = new User[0];

	}


	/* Mutators */

	private void setTiles(ArrayList<MemoryTile> someTiles) {
		tiles = someTiles;
	}

	private void setGameID(int anID) {
		gameID = anID;
	}

	private void setGM(GameMediator aGM) {
		gm = aGM;
	}


	/* Accessors */

	private ArrayList<MemoryTile> getTiles() {
		return tiles;
	}

	public int getGameID() {
		return gameID;
	}

	private GameMediator getGM() {
		return gm;
	}

	public User[] getPlayers() {
		return players;
	}


}