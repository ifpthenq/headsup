package Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ServerGUI {

	JFrame frame;
	private JTextField textField;
	private JTextArea messages; 
	private Backend backend; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerGUI(Backend backend) {
		this.backend = backend; 
		initialize();
	
		
	}
	
	public ServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 607, 460);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textarea_1 = new JTextArea(75,241);
		JScrollPane scrollPane_1 = new JScrollPane(textarea_1);
		scrollPane_1.setBounds(75, 241, 299, 193);
		panel.add(scrollPane_1);
		this.messages = textarea_1; 
		textarea_1.append("Server GUI established");
		
		
		JScrollPane scrollPane_2 = new JScrollPane(messages);
		scrollPane_2.setBounds(384, 241, 203, 193);
		panel.add(scrollPane_2);
		
		JLabel lblServerMessages = new JLabel("Server Messages");
		lblServerMessages.setFont(new Font("Calibri", Font.BOLD, 16));
		lblServerMessages.setBounds(75, 216, 132, 14);
		panel.add(lblServerMessages);
		
		JLabel lblConnectedClients = new JLabel("Connected Clients");
		lblConnectedClients.setFont(new Font("Calibri", Font.BOLD, 16));
		lblConnectedClients.setBounds(384, 216, 132, 14);
		panel.add(lblConnectedClients);
		
		//load button images
			ImageIcon activeShooterIcon = null; 
			java.net.URL activeShooterURL = getClass().getClassLoader().getResource("activeShooter.png"); 
			if(activeShooterURL != null) {
				activeShooterIcon = new ImageIcon(activeShooterURL); 
				Image image = activeShooterIcon.getImage();
				Image newImg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); 
				activeShooterIcon = new ImageIcon(newImg);
			}
			
			ImageIcon attentionIcon = null; 
			java.net.URL attentionURL = getClass().getClassLoader().getResource("attention.png"); 
			if(attentionURL != null) {
				attentionIcon = new ImageIcon(attentionURL); 
				Image image = attentionIcon.getImage();
				Image newImg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); 
				attentionIcon = new ImageIcon(newImg);
			}
			
			ImageIcon fireIcon = null; 
			java.net.URL fireURL = getClass().getClassLoader().getResource("fire.png"); 
			if(fireURL != null) {
				fireIcon = new ImageIcon(fireURL); 
				Image image = fireIcon.getImage();
				Image newImg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); 
				fireIcon = new ImageIcon(newImg);
			}
			
			ImageIcon inspectorIcon = null; 
			java.net.URL inspectorURL = getClass().getClassLoader().getResource("inspector.png"); 
			if(inspectorURL != null) {
				inspectorIcon = new ImageIcon(inspectorURL); 
				Image image = inspectorIcon.getImage();
				Image newImg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); 
				inspectorIcon = new ImageIcon(newImg);
			}
			
			ImageIcon tornadoIcon = null; 
			java.net.URL tornadoURL = getClass().getClassLoader().getResource("tornado.png"); 
			if(tornadoURL != null) {
				tornadoIcon = new ImageIcon(tornadoURL); 
				Image image = tornadoIcon.getImage();
				Image newImg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); 
				tornadoIcon = new ImageIcon(newImg);
			}
			
			Border emptyBorder = BorderFactory.createEmptyBorder();
			
			
		
		
		
		
		
		JButton btnNewButton = new JButton("Tornado");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(tornadoIcon);
		btnNewButton.setBorder(emptyBorder);
		btnNewButton.setBounds(127, 39, 80, 80);
		panel.add(btnNewButton);
		
		JButton button = new JButton("Active Shooter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get all the printwriters of all of the sockets
				//for all of the connected computers
				backend.activateActiveShooter(); 
			}
		});
		button.setBackground(Color.WHITE);
		button.setIcon(activeShooterIcon);
		button.setBounds(208, 39, 80, 80);
		button.setBorder(emptyBorder);
		panel.add(button);
		
		JButton button_1 = new JButton("Fire");
		button_1.setBackground(Color.WHITE);
		button_1.setIcon(fireIcon);
		button_1.setBounds(289, 39, 80, 80);
		button_1.setBorder(emptyBorder);
		panel.add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.setIcon(inspectorIcon);
		button_2.setBounds(370, 39, 80, 80);
		button_2.setBorder(emptyBorder);
		panel.add(button_2);
		
		JLabel lblHeadsUpAlert = new JLabel("Heads UP! Alert System");
		lblHeadsUpAlert.setFont(new Font("Calibri", Font.BOLD, 16));
		lblHeadsUpAlert.setBounds(208, 11, 224, 27);
		panel.add(lblHeadsUpAlert);
		
		JButton button_3 = new JButton("Message All PCs");
		button_3.setIcon(attentionIcon);
		button_3.setBorder(emptyBorder);
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(447, 130, 80, 80);
		panel.add(button_3);
		
		textField = new JTextField();
		textField.setBounds(75, 185, 345, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterMessageTo = new JLabel("Enter message to send to all PCs");
		lblEnterMessageTo.setBounds(75, 163, 276, 14);
		panel.add(lblEnterMessageTo);
		
		
	}
}
