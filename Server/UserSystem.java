/**
 *
 * User Command Interpreter
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.net.Socket;
import java.util.Hashtable;
import java.util.ArrayList;


public class UserSystem implements Interpreter {


	/* Static */

	private static String SYSTEM = "USER";


	/* Properties */

	private UserManager um;


	/* Constructors */

	public UserSystem(UserManager aUM) {

		// Set User Manager
		setUM(aUM);

	}


	/* Custom Methods */

	public void interpret(Hashtable aCommand, User aUser) {

		// Check Command
		String cmd = (String) aCommand.get("COMMAND");

		if (cmd.equals("SETNAME")) {

			// Get Name
			String aName = (String) aCommand.get("USERNAME");

			// Get Users
			ArrayList<User> list = getUM().getUsers();

			// For Loop to compare names
			for (User u : list) {

				if (u.getUserName().equals(aName)) {

					// Print Server Error
					System.out.println("Encountered duplicate User Name, discarding duplicate connection: " + aName);

					// Close Socket
					getUM().getUsers().get(getUM().getUsers().indexOf(aUser)).close();

					// Match Found, close connection in rejection
					removeUser(aUser);

					// Set aName to null
					aName = null;

				}

			}

			// if aName != null then set name
			if (aName != null) aUser.setUserName(aName);

		} else if (cmd.equals("REMOVE")) {

			// Remove user from arraylist
			removeUser(aUser);

		}// Append else-if's

	}

	private void removeUser(User aUser) {

		// Remove User Object from List
		getUM().getUsers().remove(aUser);

	}


	/* Mutators */

	private void setUM(UserManager aUM) {
		um = aUM;
	}


	/* Accessors */

	public String getSystem() {
		return this.SYSTEM;
	}

	public UserManager getUM() {
		return um;
	}


}