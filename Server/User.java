/**
 *
 * User Interface, limits access to defined "User" methods
 *
 * @author Casey DeLorme
 * @version 05-05-2012
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