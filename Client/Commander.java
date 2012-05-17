/**
 *
 * Commander Interface
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports


public interface Commander {


	/* Static */


	/* Properties */


	/* Constructors */

	public void interpret(Object aCommand);

	public void removeInterpreter(String systemName);

	public void addInterpreter(Interpreter anInterpreter);


	/* Custom Methods */


	/* Mutators */


	/* Accessors */


}