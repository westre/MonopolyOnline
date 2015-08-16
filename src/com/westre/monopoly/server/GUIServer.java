package com.westre.monopoly.server;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GUIServer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static JTextArea text;
	private DefaultListModel<String> model;
	private JList<String> list;
	
	public GUIServer(int port) {
		setPreferredSize(new Dimension(640, 480));
		setLayout(new BorderLayout());
		
		add(getPlayerList(), "North");
		add(getLog(), "Center");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Monopoly Online");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		sendMessage("Started server on port " + port);
		//new MPServer(54555);
	}
	
	public JScrollPane getPlayerList() {
		
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Player List"));
		
		return scrollPane;
	}
	
	public JPanel getLog() {
		JPanel panel = new JPanel(new BorderLayout());
		text = new JTextArea();
		
		JScrollPane pane = new JScrollPane(text);
		text.setEditable(false);
		
		JTextField input = new JTextField();
		
		panel.add(pane, "Center");
		panel.add(input, "South");
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Console"));
		
		return panel;
	}
	
	public void sendMessage(String message) {
		text.append(message + "\n");
	}
	
	public void addPlayerName(String playerName) {
		model.addElement(playerName);
	}
	
	public void removePlayerName(String playerName) {
		model.removeElement(playerName);
	}
}
