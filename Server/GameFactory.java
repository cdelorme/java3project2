/**
 *
 * Game Factory, creates and maintains Game Instances!
 *
 * @author Casey DeLorme
 * @version 05-12-2012
 *
 */


// Imports
import java.util.ArrayList;
import java.util.Random;


public class GameFactory {


	/* Static */

	private static int MAX_GAMES = 1000;


	/* Properties */

	private ArrayList<Game> games;


	/* Constructors */

	public GameFactory() {

		// Prepare Games ArrayList
		setGames(new ArrayList<Game>());

	}


	/* Custom Methods */

	public void addGame(User aChallenger, User aRecipient) {

		// This will accept two User objects to create a new game instance


		// First it checks that the games list is within the MAX_GAMES limit of 1000
		if (games.size() < MAX_GAMES) {

			// Create a Random Object to generate GameID's
			Random myRandom = new Random();
			int gameID = -1;

			// Now we loop until gameID has been assigned
			while (gameID == -1) {

				// Set GameID to new random
				gameID = myRandom.nextInt(MAX_GAMES);

				// If Taken reset to -1
				if (GameIDTaken(gameID)) gameID = -1;

			}

			// Create new Game Instance passing the GameID and users as a new array
			getGames().add(new Memory(gameID, new User[]{aChallenger, aRecipient}));

		} else {

			// This might be used to generate a command using the incomplete MESSAGE system
			// It will inform both users that the game server is full and no game could be created.

		}

	}

	public boolean GameIDTaken(int anID) {

		boolean ret = false;

		// Loop all games
		for (Game g : getGames()) {

			// If Match Found game taken
			if (g.getGameID() == anID) ret = true;

		}

		return ret;

	}


	/* Mutators */

	public void setGames(ArrayList<Game> theGames) {
		games = theGames;
	}


	/* Accessors */

	public ArrayList<Game> getGames() {
		return games;
	}


}