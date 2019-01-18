import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class settingJ extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					settingJ frame = new settingJ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public settingJ() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 254, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 51, 210, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblServerIpAddress = new JLabel("Server IP Address");
		lblServerIpAddress.setBounds(10, 72, 190, 14);
		contentPane.add(lblServerIpAddress);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setBounds(10, 11, 230, 29);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblSettings);
		
		textField2 = new JTextField();
		textField2.setBounds(10, 97, 210, 20);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JLabel lblPasswordToConnect = new JLabel("Username");
		lblPasswordToConnect.setBounds(10, 117, 210, 14);
		contentPane.add(lblPasswordToConnect);
		
		JButton btnSaveSettings = new JButton("Save");
		btnSaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSaveSettings.setBounds(10, 188, 89, 23);
		contentPane.add(btnSaveSettings);
		
		JButton btnTest = new JButton("Test");
		btnTest.setBounds(120, 188, 89, 23);
		contentPane.add(btnTest);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1, 230, 290, 10);
		contentPane.add(separator);
		
		JLabel lblPinger = new JLabel("<html>Connection Status<br/>Host Unreachable<br/>test</br>");
		lblPinger.setBounds(10, 220, 210, 100);
		contentPane.add(lblPinger);
		//lblPinger.setText("");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 148, 210, 20);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 168, 210, 14);
		contentPane.add(lblPassword);
		
	}
}
