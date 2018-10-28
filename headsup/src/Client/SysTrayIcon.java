package Client;

import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class SysTrayIcon implements Runnable{
    private PopupMenu popup; 
    private SystemTray tray;
    private Client client; 
	
	
	public SysTrayIcon(Client client) {
		this.client = client; 
	}
	@Override
	public void run()  {
		// TODO Auto-generated method stub
		System.out.println("DBG: Systray thread running");
		
		if(SystemTray.isSupported()) {
			String path = "activeShooter.png"; 
			String desc = "description"; 
			
		    ImageIcon icon = createImageIcon(path, desc);//tray icon doesn't take imageicon
		    //this is just because it has to be initalized and doesn't like being null. 
		    //it doesn't really find the image here, it just kind of thinks it does. 
		    Image image = Toolkit.getDefaultToolkit().getImage("activeIcon.png");
		   
			try {
				
				//the path while in dev is ../icons/activeIcon.png
				// this is in my c:/users/angela/git/headsup/headsup/bin/icons/activeIcon.png
				image = ImageIO.read(getClass().getResource("../icons/activeIcon.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			ActionListener exitListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting");
					System.exit(0);
					
				}
				
			};
		    System.out.println("Systemtray is supported");
			popup = new PopupMenu(); 
			tray = SystemTray.getSystemTray();
			TrayIcon trayIcon = new TrayIcon(image, "Alert System", popup); 
			trayIcon.setImageAutoSize(true);
			MenuItem exitItem = new MenuItem("exit"); 
			exitItem.addActionListener(exitListener);
			popup.add(exitItem);
			try {
				tray.add(trayIcon);
			}catch(AWTException e) {
				System.err.println("bad code: " + e);
			}
			
			
			
			
		}
	}
	private ImageIcon createImageIcon(String path, String description) {
		URL imgURL = getClass().getResource(path);
		if(imgURL != null) {
			System.out.println("DBG: SystrayIcon 45 Resource returned");
			return new ImageIcon(imgURL, description);
		}else {
			System.err.println("Couldn't find file: " + path); 
			return null; 
		}
		
		
		
		
	}

}
