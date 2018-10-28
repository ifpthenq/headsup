package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection implements Runnable {
	private Socket socket; 
	private PrintWriter pw; 
	private Server server; 
	private Scanner in; 
	
	public ClientConnection(Server server, Socket socket) {
		this.server = server; 
		this.socket = socket; 
	}
	
	PrintWriter getPrintWriter() {
		return pw; 
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.pw = new PrintWriter(socket.getOutputStream(), false); 
			this.in = new Scanner(socket.getInputStream()); 
			
		    //while the socket is connected, grab what's in the input stream and write it
			while(!socket.isClosed()) {
				if(in.hasNextLine()) {
					String clientSays = in.nextLine(); 
					
					for(ClientConnection a : server.getClients()) {
						PrintWriter clientPW = a.getPrintWriter(); 
						if(clientPW != null) {
							clientPW.write(clientSays + "\r\n");
							clientPW.flush();
						}
					}
				}
			}
			//if it disconnects, close the input stream scanner.
			in.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
