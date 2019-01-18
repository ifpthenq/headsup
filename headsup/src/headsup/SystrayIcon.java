package headsup;

import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SystrayIcon {
	
	static TrayIcon trayIcon;
	private SystrayJFrame sj = null; 
	public SystrayIcon() {
		show();
	}
	public SystrayIcon(SystrayJFrame systrayJFrame) {
		this.sj = systrayJFrame; 
		show(); 
	}
	private void show() {
		if(!SystemTray.isSupported()) {
			System.out.println("Your Computer DOesn't support Systray Icons");
			System.exit(0);
			
		}
		trayIcon = new TrayIcon(createIcon("/headsup/headsupIcon.png","Icon")); 
		trayIcon.setToolTip("HeadsUp Silent Alert System");
		final SystemTray tray = SystemTray.getSystemTray();
		final PopupMenu menu = new PopupMenu(); 
		MenuItem alarm = new MenuItem("Alarms"); 
		menu.add(alarm);
		alarm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not Supported yet."); 
				
			}
			
		});
		menu.addSeparator();
		
		MenuItem settings = new MenuItem("Settings"); 
		menu.add(settings); 
		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Not sure what this does! ");
			//	sj.setVisible(true);
				JFrame settingsFrame = new JFrame("Settings"); 
				settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel contentPane = new JPanel(); 
				contentPane.setLayout(null);
				settingsFrame.setContentPane(contentPane);
				
				settingsFrame.setBounds(400, 400, 254, 352);
				
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				settingsFrame.setContentPane(contentPane);
				contentPane.setLayout(null);
				//ip
				JTextField textField = new JTextField();
				textField.setBounds(10, 51, 210, 20);
				textField.setText(sj.getIP());
				textField.setColumns(10);
				contentPane.add(textField);
				
				JLabel lblServerIpAddress = new JLabel("Server IP Address");
				lblServerIpAddress.setBounds(10, 72, 190, 14);
				contentPane.add(lblServerIpAddress);
				
				JLabel lblSettings = new JLabel("Settings");
				lblSettings.setBounds(10, 11, 230, 29);
				lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
				lblSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
				contentPane.add(lblSettings);
				//username
				JTextField textField2 = new JTextField();
				textField2.setBounds(10, 97, 210, 20);
				textField2.setText(sj.getUser());
				contentPane.add(textField2);
				textField2.setColumns(10);
				
				JLabel lblPasswordToConnect = new JLabel("Username");
				lblPasswordToConnect.setBounds(10, 117, 210, 14);
				contentPane.add(lblPasswordToConnect);
				//password
				JTextField textField3 = new JTextField();
				textField3.setColumns(10);
				textField3.setText(sj.getPW());
				textField3.setBounds(10, 148, 210, 20);
				contentPane.add(textField3);
				
				
				JButton btnSaveSettings = new JButton("Save");
				btnSaveSettings.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String textFieldValue = textField.getText();
						sj.setIP(textFieldValue);
						String username = textField2.getText();
						sj.setUser(username); 
						String password = textField3.getText();
						sj.setPW(password);
						sj.writeConfigFile();
						
					}
				});
				btnSaveSettings.setBounds(10, 188, 89, 23);
				contentPane.add(btnSaveSettings);
				
				JButton btnTest = new JButton("Test");
				btnTest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				btnTest.setBounds(120, 188, 89, 23);
				contentPane.add(btnTest);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(1, 230, 290, 10);
				contentPane.add(separator);
				
				JLabel lblPinger = new JLabel("<html>Connection Status<br/>Host Unreachable<br/>test</br>");
				lblPinger.setBounds(10, 220, 210, 100);
				contentPane.add(lblPinger);
				//lblPinger.setText("");
				
				
				
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setBounds(10, 168, 210, 14);
				contentPane.add(lblPassword);
				settingsFrame.setVisible(true);
				
				//sj.launchSettings();
			}
			
		});
		
	
		menu.addSeparator();
		
		MenuItem exit = new MenuItem("Exit"); 
		menu.add(exit);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		
		
		
		trayIcon.setPopupMenu(menu);
		
		try {
			tray.add(trayIcon);
		}catch (Exception e) {
			
		}
		
	}
	
	protected static Image createIcon(String path, String desc) {
		URL imageURL = SystrayIcon.class.getResource(path);
		
		return (new ImageIcon(imageURL, desc).getImage());
		
	}
}
