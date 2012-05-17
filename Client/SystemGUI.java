/**
 *
 * Client Primary GUI
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
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
		JMenu instructions = new JMenu("Instructions");
		JMenu help = new JMenu("Help");
		JMenuItem disco = new JMenuItem("Disconnect");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem chat = new JMenuItem("Chat Instructions");
		JMenuItem game = new JMenuItem("Game Instructions");
		JMenuItem about = new JMenuItem("About");

		// Menu Assembly
		file.add(disco);
		file.add(exit);
		help.add(about);
		instructions.add(chat);
		instructions.add(game);
		menu.add(file);
		menu.add(instructions);
		menu.add(help);

		// Create Mnemonics
		file.setMnemonic('F');
		disco.setMnemonic('D');
		exit.setMnemonic('X');
		instructions.setMnemonic('I');
		chat.setMnemonic('C');
		game.setMnemonic('G');
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
		chat.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Instructional Message for Chat System
					JOptionPane.showMessageDialog(null, "Type a message into the text area below the ChatBox.  You may send a message by clicking the button next to the chat, or by hitting the enter key.\nTo send a private message using the format \"@username\" at the start of the message.  If that user is not connected, no message will be sent.", "Chat Instructions", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		);
		game.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Instructional Message for Game System
					JOptionPane.showMessageDialog(null, "Memory is a tile matching game.  Each player takes turn flipping two tiles.  If a match is found the tiles are removed and the player earns a point.  Whoever has the most points once all times are removed wins.", "Game Instructions", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		);
		about.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Print JOptionPane with Details on Project
					JOptionPane.showMessageDialog(null, "Description: Multi-Threaded Server with MatchMaking Memory Game System.\nWritten By:  Group 4 (219-03)", "Java 3 (219) - Project #2", JOptionPane.INFORMATION_MESSAGE);

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

		// Size to Contained Items
		pack();

        // Set current size as Minimum Size
        setMinimumSize(new Dimension(getWidth(), getHeight()));

		// Reposition to Center Screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - (getWidth())) / 2, (dim.height - (getHeight())) / 2);

		// Set Chat Focus
		getCG().setChatFocus();

	}

	public void resizeMe() {

		// Size to Contained Items
		pack();

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