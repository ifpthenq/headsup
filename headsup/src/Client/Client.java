package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private String host; 
	private int clientPortNumber; 
	private int serverPortNumber; 
	private Scanner userInputScanner; 
	private String clientName; 
	
	public Client() {
		this.host = "localhost"; 
		this.clientPortNumber = 8443; 
		this.serverPortNumber = 8442; 
		this.clientName = "Client 1";
	}
	
	public void setHost(String host) {
		this.host = host; 
	}
	
	public void setClientPortNumber(int clientPortNumber) {
		this.clientPortNumber = clientPortNumber;
	}
	
	public void setServerPortNumber(int serverPortNumber) {
		this.serverPortNumber = serverPortNumber; 
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName; 
	}
	
	
	//start program here
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		Client client = new Client(); 
		client.start(read); 
		
	}

	private void start(Scanner read) {
		
		
		
		try {
			SysTrayIcon systrayIcon = new SysTrayIcon(this); 
			Thread systrayThread = new Thread(systrayIcon);
			systrayThread.start();
			
			Socket socket = new Socket(host, serverPortNumber); 
			Thread.sleep(100); //wait for network communication
			ServerControlThread serverControlThread = new ServerControlThread(socket, clientName); 
			Thread connectionThread = new Thread(serverControlThread); 
			connectionThread.start();
			while(connectionThread.isAlive()) {
				if(read.hasNextLine()) {
					serverControlThread.addMessage(read.nextLine());
				}
				
			}
		}catch(IOException e) {
			System.err.println("Client could not connect");
			e.printStackTrace(); 
		}catch(InterruptedException e) {
			System.out.println("Interrupted"); 
		}
		
	}
	
	
}
