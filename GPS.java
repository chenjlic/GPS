//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class GPS extends JFrame {
	
	private JPanel panel;
	private JRadioButton Symbol;
	private JRadioButton Address;
	private ButtonGroup group;
	private JScrollPane fromP;
	private JScrollPane toP;
	private JScrollPane resultp;
	private JList list = new JList();
	private JList list2 = new JList();
	private JButton dist = new JButton("Calculate Shortest Path Using Distance");
	private JButton time = new JButton("Calculate Shortest Path Using Time");
	private JTextArea res = new JTextArea(3, 48);
	private JLabel typeSelect;
	private Graph g = new Graph("MapInformation.txt");
	private JSeparator sepr = new JSeparator();
	
	public GPS() {
		panel = new JPanel();
		
		typeSelect = new JLabel("Select to use symbol or address:");
		typeSelect.setPreferredSize(new Dimension(400, 20));
		
		Symbol = new JRadioButton("Symbol");
		Address = new JRadioButton("Address");
		Symbol.setPreferredSize(new Dimension(150, 50));
	    Address.setPreferredSize(new Dimension(150, 50));
	    
		group = new ButtonGroup();
		group.add(Symbol);
		group.add(Address);
		
		//Populate array using methods
		String[] symbols = g.vertexArrSym("MapInformation.txt");
		String[] addresses = g.vertexArrAdd("MapInformation.txt");
		
		//Declaration of fromP info
		fromP = new JScrollPane(list);
		fromP.setPreferredSize(new Dimension(200,100));
		fromP.setBorder(new TitledBorder("Start Location"));
		
		//Declaration of toP info
		toP = new JScrollPane(list2);
		toP.setPreferredSize(new Dimension(200, 100));
		toP.setBorder(new TitledBorder("Final Destination"));
		
		//Result Pane 
		resultp = new JScrollPane(res);
		resultp.createHorizontalScrollBar();
		
		dist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText(null);
				res.setEditable(false);
				Graph.useDistCost = true;
				if(list.isSelectionEmpty() && list2.isSelectionEmpty()) {
					res.append("Please select a starting location and final destination.");
					return;
				} else if(list.isSelectionEmpty()) {
					res.append("Please select a starting location.");
					return;
				} else if(list2.isSelectionEmpty()) {
					res.append("Please select a final destination.");
					return;
				} else {
					Path shortest = Dijkstra.findShortestPath(g, (String)list.getSelectedValue(), (String)list2.getSelectedValue());
					if(shortest == null) {
						res.append("A path from " + (String)list.getSelectedValue() + " to " + (String)list2.getSelectedValue() + " does not exist.");
					} else {
						res.append(Dijkstra.findShortestPath(g, (String)list.getSelectedValue(), (String)list2.getSelectedValue()).toString());
					}
				}
			}
		});
		
		time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText(null);
				Graph.useDistCost = false;
				if(list.isSelectionEmpty() && list2.isSelectionEmpty()) {
					res.append("Please select a starting location and final destination.");
					return;
				} else if(list.isSelectionEmpty()) {
					res.append("Please select a starting location.");
					return;
				} else if(list2.isSelectionEmpty()) {
					res.append("Please select a final destination.");
					return;
				} else {
					Path shortest = Dijkstra.findShortestPath(g, (String)list.getSelectedValue(), (String)list2.getSelectedValue());
					if(shortest == null) {
						res.append("A path from " + (String)list.getSelectedValue() + " to " + (String)list2.getSelectedValue() + " does not exist.");
					} else {
						res.append(Dijkstra.findShortestPath(g, (String)list.getSelectedValue(), (String)list2.getSelectedValue()).toString());
					}
				}
			}
		});
		
		
		//Action Listener for symbol button
		Symbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clears lists
				list.removeAll();
				list2.removeAll();
				
				//Re-populate the lists
				list.setListData(symbols);
				list2.setListData(symbols);
				
				Graph.returnAddress = false;
			}
		});
		
		//Action Listener for address button
		Address.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clears lists
				list.removeAll();
				list2.removeAll();
				
				//Re-populate the lists
				list.setListData(addresses);
				list2.setListData(addresses);
				
				Graph.returnAddress = true;
			}
		});
		
		//Adding to JPanel
		panel.add(typeSelect);
		panel.add(Symbol);
		panel.add(Address);
		panel.add(sepr);
		panel.add(fromP);
		panel.add(toP);
		panel.add(dist);
		panel.add(time);
		panel.add(resultp);
		
		add(panel);
		
	}

	public static void main(String[] args) { 
		GPS t = new GPS(); 
		t.setBounds(500, 500, 500, 400);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setResizable(false);
		t.setTitle("GPS APP");
		t.setVisible(true);
	}

}