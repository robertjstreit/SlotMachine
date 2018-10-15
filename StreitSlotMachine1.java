import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class InfoPanel extends JPanel {
	public InfoPanel() {
		setPreferredSize(new Dimension(500,100));
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Welcome to JSlotMachine 1.0",50,50);
	}
}

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
	}	
	public SlotPanel() {
		if(colorCode == 0) {
			color = Color.RED;
		} else if (colorCode == 1) {
			color = Color.GREEN;
		} else {
			color = Color.BLUE;
		}
	}
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

class PanelFrame extends JFrame {
	private double balance = 1;
	private int result;
	private SlotPanel slots;
	public PanelFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,500,500);
		setTitle("This Game is Rigged!");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
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
					balance = balance - .1;
					if (panSlotLeft.colorCode == panSlotMid.colorCode && panSlotMid.colorCode == panSlotRight.colorCode && panSlotLeft.shapeCode == panSlotMid.shapeCode && panSlotMid.shapeCode == panSlotRight.shapeCode) {
						balance = balance + .5;
					} else {
					balance = balance - .5;
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
					balance = balance - .25;
					if (panSlotLeft.colorCode == panSlotMid.colorCode && panSlotMid.colorCode == panSlotRight.colorCode && panSlotLeft.shapeCode == panSlotMid.shapeCode && panSlotMid.shapeCode == panSlotRight.shapeCode) {
						balance = balance + .5;
					} else {
					balance = balance - .5;
					}
					repaint();
				}
			}
		);
		c.add(panSouth,BorderLayout.SOUTH);
		InfoPanel panNorth = new InfoPanel();
		c.add(panNorth,BorderLayout.NORTH);
	}
}

public class StreitSlotMachine {
	public static void main(String[] args) {
		PanelFrame pf = new PanelFrame();
		pf.setVisible(true);
	}
}