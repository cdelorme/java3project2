/**
 *
 * Server Memory Game Instance Data Storage System
 *
 * @author Rohan Ganpayte
 * @maintainer Casey DeLorme
 * @version 05-12-2012
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


	/* Constructors */

	public Memory(int aGameID, User[] users) {

		// Set turn to 0
		turn = 0;

		// Prepare Tile Array List
		setTiles(new ArrayList<MemoryTile>());

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

		// Create 64 MemoryTile instances
		//for (int y=0; y < 8; y++) {
		for (int y=0; y < 6; y++) {
			for (int x=0; x < 8; x++) {

				// Add New Instance to Array
				tmpTiles.add(new MemoryTile(x, y));

			}
		}

		// Create a random object
		Random myRandom = new Random();

		// While tmpTiles contains elements
		while (tmpTiles.size() > 0) {

			int anImage = myRandom.nextInt(images.length);
			// Randomly Select Image
			//int anImage = (int) Math.floor(Math.random() * images.length);

			// Now pick one from tmpImages
			//int rndTile = (int) Math.floor(Math.random() * tmpTiles.size());
			int rndTile = myRandom.nextInt(tmpTiles.size());

			// Assign the image to the tile
			tmpTiles.get(rndTile).setImage(images[anImage]);

			// Transfer the record to the tiles array
			getTiles().add(tmpTiles.remove(rndTile));

			// Rinse & repeat the same operation
			//rndTile = (int) Math.floor(Math.random() * tmpTiles.size());
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


	/* Mutators */

	private void setTiles(ArrayList<MemoryTile> someTiles) {
		tiles = someTiles;
	}

	private void setGameID(int anID) {
		gameID = anID;
	}


	/* Accessors */

	private ArrayList<MemoryTile> getTiles() {
		return tiles;
	}

	public int getGameID() {
		return gameID;
	}


}