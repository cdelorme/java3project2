/**
 *
 * Interface for User Factory
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.util.*;
import java.net.*;


public interface UserManager {


	/* Static */


	/* Properties */


	/* Constructors */


	/* Custom Methods */

	public void addUser(Socket aSocket);

	public ArrayList<User> getUsers();

	public void removeUser(User aUser);


	/* Mutators */


	/* Accessors */


}