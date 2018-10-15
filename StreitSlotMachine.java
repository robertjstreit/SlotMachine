import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


/**
The InfoPanel is used for all information the user needs to play
the slots, as well as some dimensions for the text displaying
the said information. It also includes get and set functions for
the messages to be displayed to the screen so they can be updated
with each spin.
*/
class InfoPanel extends JPanel {
	private String message;
	public InfoPanel() {
		setPreferredSize(new Dimension(500,100));
		message = "Balance: $1.00";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String m) {
		message = m;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(message,50,50);
	}
}
/**
The SlotPanel is used to generate random colors and shapes that
can later be displayed to the screen. With each roll, the Spin 
function re-randomizes the three "slots."
*/
class SlotPanel extends JPanel {
	private Color color;
	private String shape;
	public int colorCode;
	public int shapeCode;
	private Random rndColor = new Random();
	private Random rndShape = new Random();
	public void Spin() {
		colorCode = rndColor.nextInt(3);
		shapeCode = rndShape.nextInt(2);
		if (colorCode == 0) {
			color = Color.RED;
		} 
		if (colorCode == 1) {
			color = Color.GREEN;
		}
		if (colorCode == 2) {
			color = Color.BLUE;
		}
	}	
	public SlotPanel() {
		Spin();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		if (shapeCode == 0) {
			g.fillRect(20,50,100,100);
		} else {
			g.fillOval(20,50,100,100);
		}
	}
}

/**
The PanelFrame class is responsible for actually displaying the
aforementioned panels. It first sets the size, name, and type of 
the window itself. It has north, center, and south panels, each 
with its own functionalities. The north panel implements the
InfoPanel, the center implements the SlotPanels, and the south 
implements the buttons themselves. Each button contains the 
necessary events that need to happen with each button press, such
as changing each slot, adjusting the balance (adding $ for three
matches and subtracting for anything else), and the end of game
conditions. Each button also includes repaint() in order to allow 
every new spin to be displayed to the screen. 
*/

class PanelFrame extends JFrame {
	private double balance;
	private int result;
	public PanelFrame() {
		balance = 1;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,500,500);
		setTitle("This Game is Rigged!");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		InfoPanel panNorth = new InfoPanel();
		c.add(panNorth,BorderLayout.NORTH);
		JPanel panCenter = new JPanel();
		panCenter.setLayout(new GridLayout(1,3));
		SlotPanel panSlotLeft = new SlotPanel();
		SlotPanel panSlotMid = new SlotPanel();
		SlotPanel panSlotRight = new SlotPanel();
		panCenter.add(panSlotLeft);
		panCenter.add(panSlotMid);
		panCenter.add(panSlotRight);
		c.add(panCenter,BorderLayout.CENTER);
		JPanel panSouth = new JPanel();
		JButton btnMax = new JButton("Bet Max");
		panSouth.add(btnMax);
		btnMax.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panSlotLeft.Spin();
					panSlotMid.Spin();
					panSlotRight.Spin();
					if (panSlotLeft.colorCode == panSlotMid.colorCode && panSlotMid.colorCode == panSlotRight.colorCode && panSlotLeft.shapeCode == panSlotMid.shapeCode && panSlotMid.shapeCode == panSlotRight.shapeCode) {
						balance = balance + .5;
					} else {
					balance = balance - .5;
					}
					if (balance <= 0) {
						btnMax.setEnabled(false);
						panNorth.setMessage("You have no money left. Game over.");
					} else {
					panNorth.setMessage("Balance: $" + String.valueOf(balance));
					}
					repaint();
				}
			}
		);
		JButton btnMin = new JButton("Bet Min");
		panSouth.add(btnMin);
		btnMin.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panSlotLeft.Spin();
					panSlotMid.Spin();
					panSlotRight.Spin();
					if (panSlotLeft.colorCode == panSlotMid.colorCode && panSlotMid.colorCode == panSlotRight.colorCode && panSlotLeft.shapeCode == panSlotMid.shapeCode && panSlotMid.shapeCode == panSlotRight.shapeCode) {
						balance = balance + .1;
					} else {
					balance = balance - .1;
					}
					if (balance <= 0) {
						btnMin.setEnabled(false);
						panNorth.setMessage("You have no money left. Game over.");
					} else {
					panNorth.setMessage("Balance: $" + String.valueOf(balance));
					}
					repaint();
				}
			}
		);
		JButton btnSpin = new JButton("Spin");
		panSouth.add(btnSpin);
		btnSpin.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panSlotLeft.Spin();
					panSlotMid.Spin();
					panSlotRight.Spin();
					if (panSlotLeft.colorCode == panSlotMid.colorCode && panSlotMid.colorCode == panSlotRight.colorCode && panSlotLeft.shapeCode == panSlotMid.shapeCode && panSlotMid.shapeCode == panSlotRight.shapeCode) {
						balance = balance + .25;
					} else {
					balance = balance - .25;
					}
					if (balance <= 0) {
						btnSpin.setEnabled(false);
						panNorth.setMessage("You have no money left. Game over.");
					} else {
					panNorth.setMessage("Balance: $" + String.valueOf(balance));
					}
					repaint();
				}
			}
		);
		c.add(panSouth,BorderLayout.SOUTH);
	}
}

/**
The main function simply creates the PanelFrame interface and makes
it visible to the user. 
*/

public class StreitSlotMachine {
	public static void main(String[] args) {
		PanelFrame pf = new PanelFrame();
		pf.setVisible(true);
	}
}