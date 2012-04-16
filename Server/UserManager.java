/**
 *
 * Interface for User Factory
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports
import java.util.*;
import java.net.*;


public interface UserManager {


	/* Static */


	/* Properties */


	/* Constructors */

	public void addUser(Socket aSocket);

	public ArrayList<User> getUsers();


	/* Custom Methods */


	/* Mutators */


	/* Accessors */


}