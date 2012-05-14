/**
 * Server Game Interface Definition
 *
 * @author Casey DeLorme
 * @version 05-12-2012
 *
 */


// Imports


public interface Game {


	/* Static */


	/* Properties */


	/* Constructors */


	/* Custom Methods */

	public int getGameID();

	public void showTile(User aUser, int theX, int theY);

	public User[] getPlayers();


	/* Mutators */


	/* Accessors */


}