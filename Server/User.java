/**
 *
 * User Interface, limits access to defined "User" methods
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports


public interface User {


	/* Static */


	/* Properties */


	/* Constructors */


	/* Custom Methods */

	public void sendCommand(Object aCommand);

	public void close();


	/* Mutators */

	public void setUserName(String aName);


	/* Accessors */

	public String getUserName();


}