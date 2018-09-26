import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * DriverClass that holds main and a menu system.
 * 
 * @author Ryan Moore
 * @version 2.0
 *
 */
public class DriverClass extends JFrame {

	Scanner sc = new Scanner(System.in);
	SNCalc oSNC;
	String wholeBin;
	
	int[] input = new int[4];
	int suffix = 0;

	// GUI elements.
	JPanel subnetNum, wireAdd, firstHost, lastHost, broadAdd;
	JButton info, loopSubnets, rangeSubnets, newIP;
	JLabel ipL, subnetL, range1L, range2L;
	JTextField ip, subnetValue, rangeOne,infoTF, rangeTwo, infoField;
	JScrollPane scrollPane;
	JTable subnetTable;
	JTextArea infoArea;
	String[] columnNames = { "Subnet #:", "Wire Address: ", "First Host: ", "Last Host: ", "Broadcast:" };
	String[][] data;

	public DriverClass() {

		super("Subnet Calculator");
		super.setLayout(new FlowLayout());
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(600, 900);

		// subnetTable = new JTable(data, columnNames);
		scrollPane = new JScrollPane(subnetTable);
		
		
		
		ip = new JTextField(35);
		ipL = new JLabel("Enter IP Here");
		ip.add(ipL);

		subnetL = new JLabel("Ender Subnet mask here with no /");

		subnetValue = new JTextField(5);
		subnetValue.add(subnetL);
		ip.add(subnetValue);
		
		infoArea = new JTextArea();

		info = new JButton("See IP Info");
		info.addActionListener(new InfoHandler());

		newIP = new JButton("Set IP");
		newIP.addActionListener(new NewIPHandler());

		subnetNum = new JPanel();
		wireAdd = new JPanel();
		firstHost = new JPanel();
		lastHost = new JPanel();
		broadAdd = new JPanel();

		loopSubnets = new JButton("Loop Subnets");
		loopSubnets.addActionListener(new LoopSubnetHandler());
		loopSubnets.setVisible(false);
		
		rangeSubnets = new JButton("Enter the range of subnets");
		rangeSubnets.addActionListener(new RangeHandler());
		rangeSubnets.setVisible(false);
		
		info.setVisible(false);
		
		super.add(ipL);
		super.add(ip);
		super.add(subnetL);
		super.add(subnetValue);
		super.add(newIP);
		super.add(loopSubnets);

		super.add(rangeSubnets);
		super.add(info);

		super.add(subnetNum);
		super.add(wireAdd);
		super.add(firstHost);
		super.add(lastHost);
		super.add(broadAdd);

		ip.setText("20.20.20.20");
		subnetValue.setText("15");
	}

	/**
	 * Main method for the program
	 * 
	 * @param args
	 *            UNUSED Commandline arg
	 */
	public static void main(String[] args) {

		DriverClass dc = new DriverClass();
		dc.setVisible(true);

	}

	private class NewIPHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			// System.out.println("Please enter a ipAddress x.x.x.x");
			String holder = "";
			holder = ip.getText();

			String[] split = holder.split("\\."); // parse the string for the following format.

			// System.out.println("Please enter Subnet mask value (no '/' please)");
			suffix = Integer.parseInt(subnetValue.getText());

			wholeBin = "";

			for (int i = 0; i < input.length; i++) {
				input[i] = Integer.parseInt(split[i]);
				// this.wholeBin += Integer.toString(input[i], 2);
				wholeBin += String.format("%08d", Integer.valueOf(Integer.toBinaryString(input[i])));
			}
			try {
				oSNC = new SNCalc(suffix, wholeBin);
				ip.setText("");
				subnetValue.setText("");
				loopSubnets.setVisible(true);
				info.setVisible(true);
				rangeSubnets.setVisible(true);

			} catch (Exception e) {
				System.err.println("Not good values");
			}

		}
	}
		
	public void setTable() {
		remove(scrollPane); // so only 1 table exists at a time.
		data = oSNC.data;
		subnetTable = new JTable(data, columnNames);
		scrollPane = new JScrollPane(subnetTable);
		add(scrollPane);

		validate();
		repaint();
	}
	private class LoopSubnetHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			data = new String[oSNC.oSB.maxSubnets][5];
			oSNC.loopSubnets();
			setTable();
		}
	}

	private class RangeHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			rangeOne = new JTextField(10);
			rangeTwo = new JTextField(10);
			range1L = new JLabel("Please enter starting Range");
			range2L = new JLabel("Please enter ending Range");

			add(range1L);
			add(rangeOne);
			add(range2L);
			add(rangeTwo);

			JButton setRange = new JButton("Set the IPS");
			setRange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					oSNC.setRanges(Integer.parseInt(rangeOne.getText()), Integer.parseInt(rangeTwo.getText()));
					remove(rangeOne);
					remove(rangeTwo);
					remove(range1L);
					remove(range2L);
					remove(setRange);
					
					setTable();
				}
			});

			add(setRange);
			validate();
			repaint();
			
			
		}
	}

	private class InfoHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			//remove(infoL);
			remove(infoArea);
			infoArea = new JTextArea(oSNC.printIPInfo());
			
			//infoTF.setBounds(1, 1, 200, 200);
			add(infoArea);
			validate();
			repaint();
			
		}
	}
	
	
}
