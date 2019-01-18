package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingConstants;

public class SystrayJFrame extends JFrame {
    SystrayIcon tray = new SystrayIcon(this); 
	private JPanel contentPane;
	private String IP = "192.168.0.5";
	private JLabel lblAlert2 = new JLabel("");
	private String password = null; 
	private String username = null; 

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					SystrayJFrame frame = new SystrayJFrame();
					frame.setVisible(false);
					
					
				//	ArrayList<String> alarms = new ArrayList<String>();
					
					TimerTask task = new TimerTask() {

						@Override
						public void run() {
							//start timer to hide window
							TimerTask task2 = new TimerTask() {

								@Override
								public void run() {
									frame.setVisible(false);
									
								}
								
							};
							Timer timer2 = new Timer();
							long delay2 = 6000L;
							timer2.schedule(task2, delay2);
							
							/*
							 * Check for an alarm every 8 seconds. If there is one, 
							 * repaint the window with the alarm and
							 * , make the window visible
							 */
							try {
								//ArrayList<String> alarms = new ArrayList<String>();
								//alarms = frame.getAlerts();
								
								Map<String, String> alarms = new HashMap<String, String>();
								alarms = frame.getAlertsMap();
								String lbl = new String("<html>");
								if(alarms.size() > 0) {
									for(Map.Entry<String, String> i : alarms.entrySet()) {
										//System.out.println("alarm: " + i);
										if(i.getKey().equals("0")) {
											//Active Shooter - Orange
											frame.contentPane.setBackground(new Color(255, 69, 0));
										}else if(i.getKey().equals("1")){
											//Custom Alert 1 - purple
											frame.contentPane.setBackground(new Color(87, 24, 69));
										}else if(i.getKey().equals("2")) {
											//Custom Alert 2 - red
											frame.contentPane.setBackground(new Color(144, 12, 62));
										}else if(i.getKey().equals("3")) {
											//Custom Alert 3 - bright red
											frame.contentPane.setBackground(new Color(199, 0, 57));
										}else {
											
										}
										lbl = lbl + i.getValue() + "<br/>"; 
										
										
									}//end for
									lbl = lbl + "</html>";
									frame.lblAlert2.setText(lbl);
									frame.repaint();
								}else {
									System.out.println("there are no alarms");
								}
								
								frame.setVisible(true);
								
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					};
					
					Timer timer = new Timer(); 
					long delay = 0; 
					long intervalPeriod = 1 * 8000; 
					timer.scheduleAtFixedRate(task, delay, intervalPeriod);
					
					
				 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});
	}

	/**
	 * Create the frame.
	 */
	public SystrayJFrame() {
		readConfigFile();
		setTitle("Alert !!");
		setResizable(false);
		setFocusable(false);
		setAlwaysOnTop(true);
		//pack();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(130, 130, 218, 120);
		 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		//System.out.println(defaultScreen.getDefaultConfiguration().getBounds());
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
	    int x = (int) rect.getMaxX() - this.getWidth();
	    int y = (int) rect.getMaxY() - this.getHeight();
	    x = x - 100; 
	    y = y - 100; 
	    setLocation(x,y); 
	
		  
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 69, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		JLabel lblAlert = new JLabel("ALERT");
		lblAlert.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlert.setForeground(new Color(255, 255, 255));
		lblAlert.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlert.setBounds(10, 11, 198, 25);
		contentPane.add(lblAlert);
		*/
		
	    this.lblAlert2 = new JLabel("ALERT");
		lblAlert2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlert2.setForeground(new Color(255, 255, 255));
		lblAlert2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlert2.setBounds(10, 11, 198, 98);
		contentPane.add(lblAlert2);
		
		this.setVisible(false);
	}
	
	
	
	/*
	 * REPLACED WITH FUNCTION THAT RETURNS MAP
	 */
	private ArrayList<String> getAlerts() throws Exception {
		//DefaultHttpClient httpClient = new DefaultHttpClient();
		ArrayList<String> list = new ArrayList<String>(); 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet getRequest = new HttpGet("http://" + this.IP + "/resources/alarms.php"); 
			getRequest.addHeader("accept", "text/html");
			HttpResponse response = httpClient.execute(getRequest); 
			int statusCode = response.getStatusLine().getStatusCode(); 
			System.out.println(statusCode);
			HttpEntity httpEntity = response.getEntity(); 
			String output = EntityUtils.toString(httpEntity);
			System.out.println(output);
			
			Document dom = loadXMLFromString(output); 
			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();
			if(nl != null) {
				int length = nl.getLength(); 
				for (int i=0; i < length; i++) {
					if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element)nl.item(i); 
						if (el.getNodeName().contains("alarm")) {
							String alarmMessage = el.getElementsByTagName("message").item(0).getTextContent();
							//System.out.println(alarmMessage);
							list.add(alarmMessage); 
						}
					}
				}
			}
			
		}finally {
			httpClient.close();
		}
		return list;
	}
	
	
	private Map<String, String> getAlertsMap() throws Exception {
		//DefaultHttpClient httpClient = new DefaultHttpClient();
		Map<String, String> list = new HashMap<String ,String>(); 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//TEST LINE
		this.IP = "192.168.0.5";
		try {
			HttpGet getRequest = new HttpGet("http://" + this.IP + "/resources/alarms.php"); 
			getRequest.addHeader("accept", "text/html");
			HttpResponse response = httpClient.execute(getRequest); 
			int statusCode = response.getStatusLine().getStatusCode(); 
			System.out.println(statusCode);
			HttpEntity httpEntity = response.getEntity(); 
			String output = EntityUtils.toString(httpEntity);
			System.out.println(output);
			
			Document dom = loadXMLFromString(output); 
			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();
			if(nl != null) {
				int length = nl.getLength(); 
				for (int i=0; i < length; i++) {
					if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element)nl.item(i); 
						if (el.getNodeName().contains("alarm")) {
							String alarmMessage = el.getElementsByTagName("message").item(0).getTextContent();
							String alarmpk = el.getElementsByTagName("pk").item(0).getTextContent();
							list.put(alarmpk, alarmMessage);
							
						}
					}
				}
			}
			
		}finally {
			httpClient.close();
		}
		return list;
	}
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	
	public void setIP(String IP) {
		this.IP = IP; 
	}
	public String getIP() {
		return this.IP;
	}
	public void setPW(String PW) {
		this.password = PW; 
	}
	public String getPW() {
		return this.password;
	}
	public void setUser(String UN) {
		this.username = UN; 
	}
	public String getUser() {
		return this.username;
	}
	
	public void writeConfigFile() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//Root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("config"); 
			doc.appendChild(rootElement);
			
			//first branch
			Element branch = doc.createElement("settings");
			rootElement.appendChild(branch); 
			
						
			Element IPElement = doc.createElement("IP");
			IPElement.appendChild(doc.createTextNode(this.IP));
			branch.appendChild(IPElement);
			
			Element UserElement = doc.createElement("username"); 
			UserElement.appendChild(doc.createTextNode(this.username));
			branch.appendChild(UserElement);
			
			Element PassElement = doc.createElement("password");
			PassElement.appendChild(doc.createTextNode(this.password));
			branch.appendChild(PassElement);
			
			
			
			//write
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("config.xml")); 
			transformer.transform(source, result);
			
		}catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
	
	public void readConfigFile() {
		try {
			File fxmlFIle = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fxmlFIle);
			doc.getDocumentElement().normalize();
			System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
			Node rooter = doc.getDocumentElement();
			NodeList nl2 = rooter.getChildNodes();
			Node nNode = nl2.item(0);
			
			if(nl2.item(0).getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element)nl2.item(0); 
				if (el.getNodeName().contains("settings")) {
					this.IP = el.getElementsByTagName("IP").item(0).getTextContent();
					this.username = el.getElementsByTagName("username").item(0).getTextContent(); 
					this.password = el.getElementsByTagName("password").item(0).getTextContent();
					
				}
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
