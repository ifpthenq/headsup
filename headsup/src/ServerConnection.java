import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerConnection implements Runnable{
	//these are created in Client and passed in on instantiation
	private Socket socket; 
	private String clientName; 
	private LinkedList<String> messageSendList; 
	private boolean messagesWaiting = false ;
	
	public ServerConnection(Socket socket, String clientName) {
		this.socket = socket; 
		this.clientName = clientName; 
		messageSendList = new LinkedList<String>(); 
	}
	
	@Override
	public void run() {
		System.out.println(clientName + " is preparing a Control Client Connection");
		System.out.println("client port : " + socket.getLocalPort());
		System.out.println("Server : " + socket.getRemoteSocketAddress()+ " : " + socket.getPort());
		try {
			//grab the input and output stream from the socket and hold them with
			//pw and serverSays
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), false); 
			InputStream serverSays = socket.getInputStream();
			Scanner readWhatServerSays = new Scanner(serverSays); 
			
			
			while(!socket.isClosed()) {
				//print what is coming into the socket from the server
				if(serverSays.available() > 0) {
					if(readWhatServerSays.hasNextLine()) {
						System.out.println(readWhatServerSays.nextLine());
					}
				}
				//send anything coming in from the user to the server by 
				//printing to the socket output buffer
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
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public void addMessage(String message) {
		synchronized (messageSendList){
			messagesWaiting = true; 
			messageSendList.push(message);
		}
		
	}

}
