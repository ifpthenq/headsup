package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;


public class Server {
	
	private int portNumber; 
	private List<ClientConnection> clients; 
	private Boolean serverRunning; 
	private ServerSocket srvrSocket = null; 
	private int controlPortNumber = 8444;
	private ClientConnection controlConnection = null; 
	private ServerSocket controlSrvrSocket = null; 
	
	public Server() {
		this.portNumber = 8442; 
		this.serverRunning = false;
		this.clients = null; 
		
	}
	
	public void setPort(int i) {
		this.portNumber = i; 
	}
	
	
	public void start() {
		//client connections will be stored in a list
		clients = new ArrayList<ClientConnection>(); 
		//setup the connection to the control client
		
		try {
			/*System.out.println("Creating connection to control thread");
			controlSrvrSocket = new ServerSocket(this.controlPortNumber);
			Socket ctlsocket = controlSrvrSocket.accept(); 
			System.out.println("accepted a connection from : " + ctlsocket.getRemoteSocketAddress());
			ClientConnection ctlclient = new ClientConnection(this, ctlsocket); 
			Thread ctlthread = new Thread(ctlclient);
			ctlthread.start(); 
			*/
			//launches a thread, which launches the web server
			String backendName = "Backend"; 
			Backend backend = new Backend(backendName, false, this); 
			Thread backendThread = new Thread(backend); 
			backendThread.start();
			System.out.println("DBG: Backend thread launched, control continues from Server line 54");
			
			//creates a Server Socket at the specified port
			srvrSocket = new ServerSocket(this.portNumber); 
			this.serverRunning = true; 
			connect(srvrSocket); 
		    
		}catch (IOException e) {
			System.err.println("Could not create ServerSocket on port" + this.portNumber);
			System.exit(1);
		}
	}
	
	public void stop() {
		srvrSocket = null; 
	}
	
	private void connect(ServerSocket srvrSocket) {
		System.out.println("Server Started on port: " + this.portNumber);
		
		//loop run continuously accepting clients
		//serverRunning must be turned off by some external means
		while(serverRunning) {
			System.out.println("hello");
			try {
				
				Socket socket = srvrSocket.accept(); 
				System.out.println("accepted a connection from : " + socket.getRemoteSocketAddress());
				//we create a new clientConnection and give it this server and the socket we just made
				ClientConnection client = new ClientConnection(this, socket); 
				Thread thread = new Thread(client);
				thread.start(); 
				clients.add(client);
				
				serverRunning = false; 
			}catch(IOException e) {
				System.out.println("Did not accept a client connection");
			}
		}
		
	}

	public List<ClientConnection> getClients(){
		return clients; 
	}
	
	public static void createGUI() {
		JFrame frame = new JFrame("Heads UP"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.add(new ServerJPanel());
		frame.pack(); 
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Server server = new Server(); 
		server.start(); 
		
		
		
	}
	
}


