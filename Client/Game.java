/**
 *
 * Game Client Interface, unifies Game Systems
 *
 * @author Casey DeLorme
 * @version 05-13-2012
 *
 */


// Imports


public interface Game {


	/* Static */


	/* Properties */


	/* Constructors */


	/* Custom Methods */

	public int getGameID();

	public void showTile(String anImage, int theX, int theY);

	public void removeTile(int theX, int theY);

	public void updateScore(String playerName);


	/* Mutators */


	/* Accessors */


}