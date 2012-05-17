/**
 *
 * Game Client Interface, unifies Game Systems
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
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