/**
 *
 * Client Primary GUI
 *
 * @author Casey DeLorme
 * @version 05-11-2012
 *
 */


// Imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SystemGUI extends JFrame implements SystemMediator {


	/* Static */


	/* Properties */

	private ClientMediator cm;
	private ClientConnection cc;
	private ChatGUI cg;
	private GameFactory gf;
	private Commander cs;


	/* Constructors */

	public SystemGUI(ClientMediator aCM, Commander aC, ClientConnection aCC) {

		// Set Commander
		setC(aC);

		// Assign ClientMediator
		setCM(aCM);

		// Assign the ClientConnection
		setCC(aCC);

		// Initialize Display
		init();

	}


	/* Custom Methods */

	private void init() {

		// Window Initialization
		setTitle(getCC().getUserName() + " - Client GUI");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Menu Creation
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		JMenuItem disco = new JMenuItem("Disconnect");
		JMenuItem exit = new JMenuItem("Exit");

		// Menu Assembly
		file.add(disco);
		file.add(exit);
		help.add(about);
		menu.add(file);
		menu.add(help);

		// Create Mnemonics
		file.setMnemonic('F');
		disco.setMnemonic('D');
		exit.setMnemonic('X');
		help.setMnemonic('H');
		about.setMnemonic('A');

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

					// Hide Self
					setVisible(false);

					// Close Connection
					getCC().close();

					// Unset CC reference
					setCC(null);

					// Dispose of Self
					dispose();

				}
			}
		);
		about.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Print JOptionPane with Details on Project
					JOptionPane.showMessageDialog(null, "Java 219\nMulti Threaded Client Server Application\nGame:  Memory /w Match Making Server", "Project Information", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		);

		// Create ChatGUI
		setCG(new ChatGUI(getCC()));

		// Create Chat Interpreter and pass to Commander
		getC().addInterpreter(new ChatSystem(getCG()));

		// Create GameFactory
		setGF(new GameFactory(getCC(), this));

		// Create GameInterpreter and pass to Commander
		getC().addInterpreter(new GameSystem(getGF()));

		// Append Display Components to Self
		add(menu, BorderLayout.NORTH);
		add(getCG(), BorderLayout.CENTER);
		add(getGF(), BorderLayout.EAST);

		// Resize - May not be needed here anymore
		resizeMe();

		// Set Chat Focus
		getCG().setChatFocus();

	}

	public void resizeMe() {

		// Size to Contained Items
		pack();

		// Reposition to Center Screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - (getWidth())) / 2, (dim.height - (getHeight())) / 2);

        // Set current size as Minimum Size
        setMinimumSize(new Dimension(getWidth(), getHeight()));

        // Adjust Visibility
		setVisible(true);

	}


	/* Mutators */

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}

	private void setCG(ChatGUI aCG) {
		cg = aCG;
	}

	private void setGF(GameFactory aGF) {
		gf = aGF;
	}

	private void setCM(ClientMediator aCM) {
		cm = aCM;
	}

	public void setC(Commander aC) {
		cs = aC;
	}


	/* Accessors */

	private ClientConnection getCC() {
		return cc;
	}

	private ChatGUI getCG() {
		return cg;
	}

	private GameFactory getGF() {
		return gf;
	}

	private ClientMediator getCM() {
		return cm;
	}

	private Commander getC() {
		return cs;
	}


}