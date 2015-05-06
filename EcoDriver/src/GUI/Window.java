package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.SocketServer;
import server.SocketServer.OnMessageReceived;


public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextArea messagesArea;
	private JButton sendButton;
	private JTextField message;
	private JButton startServer;
	private SocketServer mServer;
	
	
	private JPanel speed_control_jpanel;
	
	private JLabel speed_jlabel;
	private JTextField acceleration_intensity_jtextfield;
	private JButton send_acceleration_intensity_button;
	
	
	private String path;

	
	public Window(String path){

		super("EcoDriver");
		
		this.path = path;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // *** center the app ***
		
		/*
		
		this.speed_jlabel = new JLabel("0 km/h");
		this.speed_jlabel.setFont(new Font("Verdana", 1, 100));
		this.speed_jlabel.setHorizontalAlignment(JLabel.CENTER);
		this.speed_jlabel.setVerticalAlignment(JLabel.CENTER);
		
		this.acceleration_intensity_jtextfield = new JTextField();
		this.send_acceleration_intensity_button = new JButton("Go");
		
		
		this.speed_control_jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints c_jpanel = new GridBagConstraints();
		c_jpanel.insets = new Insets(0, 0, 0, 0); //insets définir la marge entre les composant
		c_jpanel.fill = GridBagConstraints.BOTH;
        
		c_jpanel.gridx = 0; //selection de la case ou mettre l'élément
		c_jpanel.gridy = 0; //selection de la case ou mettre l'élément
		c_jpanel.weightx = 0;
		c_jpanel.weighty = 1;
		c_jpanel.gridheight = 1;
		c_jpanel.gridwidth = 2;
        this.speed_control_jpanel.add(this.speed_jlabel, c_jpanel);
        
        c_jpanel.gridx = 0; //selection de la case ou mettre l'élément
        c_jpanel.gridy = 1; //selection de la case ou mettre l'élément
        c_jpanel.weightx = 1;
        c_jpanel.weighty = 0;
        c_jpanel.gridheight = 1;
        c_jpanel.gridwidth = 1;
        this.speed_control_jpanel.add(this.acceleration_intensity_jtextfield, c_jpanel);
        
        
        c_jpanel.gridx = 1; //selection de la case ou mettre l'élément
        c_jpanel.gridy = 1; //selection de la case ou mettre l'élément
        c_jpanel.weightx = 0;
        c_jpanel.weighty = 0;
        c_jpanel.gridheight = 1;
        c_jpanel.gridwidth = 1;
        this.speed_control_jpanel.add(this.send_acceleration_intensity_button, c_jpanel);
        
        
        this.add(this.speed_control_jpanel);
        
        //this.pack();
        this.setSize(800, 500);
	    this.setVisible(true);
        
        
	    
	    
	    */
	    
	    
		
		

		JPanel panelFields = new JPanel();
		panelFields.setLayout(new BoxLayout(panelFields, BoxLayout.X_AXIS));

		JPanel panelFields2 = new JPanel();
		panelFields2.setLayout(new BoxLayout(panelFields2, BoxLayout.X_AXIS));

		
		messagesArea = new JTextArea();
		messagesArea.setColumns(30);
		messagesArea.setRows(10);
		messagesArea.setEditable(false);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String messageText = message.getText();
				if(messageText.toString().trim().equals(""))
				{
					message.setText("");
					message.requestFocus();
					return;
				}
				// add message to the message area
				//messageText = "Server: " + messageText;
				messageText = path;
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}
		});

		startServer = new JButton("Start");
		startServer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// disable the start button
				startServer.setEnabled(false);
				messagesArea.append("Server Started, now start Android Client");
				// creates the object OnMessageReceived asked by the DispatcherServer
				// constructor
				mServer = new SocketServer(new SocketServer.OnMessageReceived()
				{
					@Override
					public void messageReceived(String message)
					{
						System.out.println("Msg Recieved");
						messagesArea.append("\n" + message);
					}
				});
				mServer.start();

			}
		});

		// the box where the user enters the text (EditText is called in
		// Android)
		message = new JTextField();
		message.setSize(200, 20);
		message.setScrollOffset(1);

		// add the buttons and the text fields to the panel
		JScrollPane sp = new JScrollPane(messagesArea);
		panelFields.add(sp);
		panelFields.add(startServer);

		panelFields2.add(message);
		panelFields2.add(sendButton);

		getContentPane().add(panelFields);
		getContentPane().add(panelFields2);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		 this.setSize(800, 500);
		 this.setVisible(true);
	
	}
}
