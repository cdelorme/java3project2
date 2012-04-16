/**
 *
 * User Factory, manages creation of User Objects
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports
import java.util.*;
import java.util.concurrent.locks.*;
import java.net.*;


public class UserFactory implements UserManager {


	/* Static */


	/* Properties */

	private ArrayList<User> users;
	private ReentrantLock _lock;
	private Commander c;


	/* Constructors */

	public UserFactory() {

		// Prepare Lock for Concurrency Protection
		_lock = new ReentrantLock();

		// Create Commander
		c = new ServerCommandSystem();

	}


	/* Custom Methods */

	public void addUser(Socket aSocket) {

		// Lock Concurrent Access
		_lock.lock();

		// Add new User to ArrayList
		users.add(new ServerClient(aSocket, c));

		// Start User Thread
		new Thread((Runnable) users.get(users.size()-1)).start();

		_lock.unlock();

	}


	/* Mutators */


	/* Accessors */

	public ArrayList<User> getUsers() {
		return users;
	}


}