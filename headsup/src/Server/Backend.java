/*
 * The purpose of this thread is to launch the user interface
 *  and the backend
 * api and just stay alive while the server is running 
 */

package Server;

import java.awt.EventQueue;
import java.io.PrintWriter;

public class Backend implements Runnable {
	
	private String test;
	private Boolean isClosed; 
	public Server server; 
	public Backend backend; 
	
	public Backend(String a, Boolean b, Server server) {
	    System.out.println("DBG: Backend constructor checkpoint reached");
		this.test = a; 
		this.isClosed = b; 
		this.server = server; 
		this.backend = this; 
	}
	
		@Override
	public void run() {
	    System.out.println("DBG: Backend run method checkpoint reached");
	   // ServerGUI serverGUI = new ServerGUI(this);
	    //ServerGUI serverGUI = new ServerGUI();
	    
	    EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI(backend);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	    System.out.println("DBG: ServerGUI closed, control returned to Backend line 27");
	//	while(!isClosed) {
	//		
	//	}
		
	}

		public void activateActiveShooter() {
			System.out.println("DBG: active shooter button invoked a method in Backend");
			System.out.println("DBG: number of connectected clients is"); 
			System.out.println(server.getClients().size());
			
			for(ClientConnection a: server.getClients()) {
				PrintWriter clientPW = a.getPrintWriter(); 
				if(clientPW != null) {
					clientPW.write("Active sh00ter!" + "\r\n");
					clientPW.flush();
				}
			}
		}
    
}
