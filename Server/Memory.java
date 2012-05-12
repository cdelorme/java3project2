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
	private ArrayList<MemoryTile> tiles;


	/* Constructors */

	public Memory(int aGameID) {

		// Prepare Tile Array List
		setTiles(new ArrayList<MemoryTile>());

		// Set Game Instance ID
		setGameID(aGameID);

		// Initialize the Game Instance
		init();

	}


	/* Custom Methods */

	private void init() {

		// Create Temporary Storage Array
		ArrayList<MemoryTile> tmpTiles = new ArrayList<MemoryTile>();

		// Create 64 MemoryTile instances
		for (int y=0; y < 8; y++) {
			for (int x=0; x < 8; x++) {

				// Add New Instance to Array
				tmpTiles.add(new MemoryTile(x, y));

			}
		}

		// While tmpTiles contains elements
		while (tmpTiles.size() > 0) {

			// Randomly Select Image
			int anImage = (int) Math.floor(Math.random() * images.length);

			// Now pick one from tmpImages
			int rndTile = (int) Math.floor(Math.random() * tmpTiles.size());

			// Assign the image to the tile
			tmpTiles.get(rndTile).setImage(images[anImage]);

			// Transfer the record to the tiles array
			getTiles().add(tmpTiles.remove(rndTile));

			// Rinse & repeat the same operation
			rndTile = (int) Math.floor(Math.random() * tmpTiles.size());
			tmpTiles.get(rndTile).setImage(images[anImage]);
			getTiles().add(tmpTiles.remove(rndTile));

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