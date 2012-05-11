/**
 *
 * Commander Interface
 *
 * @author Casey DeLorme
 * @version 05-05-2012
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