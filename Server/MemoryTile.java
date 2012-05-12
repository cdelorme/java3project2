/**
 * Memory Tile used to track image positions server-side
 *
 * @author Rohan Ganpayte
 * @maintainer Casey DeLorme
 * @version 05-12-2012
 *
 */


// Imports


public class MemoryTile {


	/* Static */


	/* Properties */

	private String image;
	private int x;
	private int y;


	/* Constructors */

	public MemoryTile(int anX, int aY) {

		// Set Coordinates
		setX(anX);
		setY(aY);

	}


	/* Custom Methods */


	/* Mutators */

	private void setX(int anX) {
		x = anX;
	}

	private void setY(int aY) {
		y = aY;
	}

	public void setImage(String anImage) {
		image = anImage;
	}


	/* Accessors */

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getImage() {
		return image;
	}


}