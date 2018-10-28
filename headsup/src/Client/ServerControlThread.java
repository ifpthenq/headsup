package Client;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerControlThread implements Runnable {
	private Socket socket; 
	private String clientName; 
	private Boolean isAlive; 
	private LinkedList<String> messageSendList; 
	private boolean messagesWaiting = false ;
	
	public ServerControlThread(Socket socket, String clientName) {
		this.socket = socket; 
		this.clientName = clientName; 
		messageSendList = new LinkedList<String>(); 
	}
	
	public void addMessage(String message) {
		synchronized (messageSendList){
			messagesWaiting = true; 
			messageSendList.push(message);
		}
	}
	
	@Override
	public void run() {
		System.out.println(clientName + " is preparing connectin");
		System.out.println("client port : " + socket.getLocalPort());
		System.out.println("Server : " + socket.getRemoteSocketAddress()+ " : " + socket.getPort());
		
		try {
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), false); 
			InputStream serverSays = socket.getInputStream(); 
			Scanner readWhatServerSays = new Scanner(serverSays); 
			
			while(!socket.isClosed()) {
				if(serverSays.available() > 0) {
					if(readWhatServerSays.hasNextLine()) {
						System.out.println(readWhatServerSays.nextLine());
						
					}
				}
				
				if(messagesWaiting) {
					String nextMessage = ""; 
					synchronized(messageSendList) {
						nextMessage = messageSendList.pop(); 
						messagesWaiting = !messageSendList.isEmpty(); 
					}
					pw.println(clientName + " > " + nextMessage);
					pw.flush();
				}
				
				
			}
			readWhatServerSays.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
