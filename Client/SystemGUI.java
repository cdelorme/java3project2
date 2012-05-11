/**
 *
 * Client Primary GUI
 *
 * @author Casey DeLorme
 * @version 05-07-2012
 *
 */


// Imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SystemGUI extends JFrame {


	/* Static */


	/* Properties */

	//private ChatGUI cg;


	/* Constructors */

	public SystemGUI() {

		// Generate Primary Menu

		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		JMenuItem disco = new JMenuItem("Disconnect");
		JMenuItem exit = new JMenuItem("Exit");

		file.add(disco);
		file.add(exit);
		help.add(about);
		menu.add(file);
		menu.add(help);

		// Add Event Listeners
		exit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Exit
					System.exit(0);

				}
			}
		);
		disco.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// ClientInterpreter
					// Use the Commander to send a request
					// to the Client to reset!
					// Awaiting implementation

				}
			}
		);

	}


	/* Custom Methods */

	private void resizeMe() {

		// Pack to Components
		pack();

		// Reposition to Center Screen


	}


	/* Mutators */


	/* Accessors */


}