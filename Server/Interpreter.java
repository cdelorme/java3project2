/**
 *
 * Interpreter Interface unifies CLI System
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.util.Hashtable;


public interface Interpreter {


	/* Static */


	/* Properties */


	/* Constructors */


	/* Custom Methods */

	public void interpret(Hashtable aCommand, User aUser);


	/* Mutators */


	/* Accessors */

	public String getSystem();


}