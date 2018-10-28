import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/*
 * This is a second client connection into the server
 * used to alter the state of the server from an
 * external control source
 * 
 * i need to be able to set variable while the server
 * blocks in it's "connect" method.
 */
public class Control {
	private String host = "localhost"; 
	private int serverPortNumber = 8444; 
	private int ClientPortNumber = 8445;
	private String clientName = "control client";
	
	
	
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		Control controlClient = new Control();
		controlClient.start(read);

	}

	private void start(Scanner read) {
		try {
			//bind a new socket
			Socket socket = new Socket(host, serverPortNumber); 
			Thread.sleep(100); //wait for network communication
			//use the new socket to instantiate a server connection
			
			ServerConnection serverConnection = new ServerConnection(socket, clientName); 
			
			//spin the new server connection off into a thread
			Thread connectionThread = new Thread(serverConnection);
			connectionThread.start(); 
			while(connectionThread.isAlive()) {
				//if the user types anything, send that to message list on the server connection
				if(read.hasNextLine()) {
					serverConnection.addMessage(read.nextLine());
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
