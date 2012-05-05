/**
 *
 * User Factory, manages creation of User Objects
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.net.Socket;


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
		setCommander(new CommandSystem(this));

		// Prepare ArrayList
		setUsers(new ArrayList<User>());

	}


	/* Custom Methods */

	public void addUser(Socket aSocket) {

		// Lock Concurrent Access
		_lock.lock();

		// Add new User to ArrayList
		getUsers().add(new Client(aSocket, getCommander()));

		// Start User Thread
		new Thread((Runnable) getUsers().get(getUsers().size()-1)).start();

		_lock.unlock();

	}

	public void removeUser(User aUser) {

		// For each user if match delete
		for (User u : getUsers()) {

			if (u.equals(aUser)) getUsers().remove(u);

		}

	}


	/* Mutators */

	private void setUsers(ArrayList<User> list) {
		users = list;
	}

	private void setCommander(Commander aCommander) {
		c = aCommander;
	}


	/* Accessors */

	public ArrayList<User> getUsers() {
		return users;
	}

	private Commander getCommander() {
		return c;
	}


}