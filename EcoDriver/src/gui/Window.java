package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;


public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Controller controller;
	private JPanel speed_control_jpanel;
	
	private JLabel speed_jlabel;
	private JTextField acceleration_intensity_jtextfield;
	private JButton send_acceleration_intensity_button;
	

	
	public Window(Controller controller){
	
		super("EcoDriver");
		
		this.controller = controller;

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // *** center the app ***
		
		
		this.speed_jlabel = new JLabel("0.0 km/h");
		this.speed_jlabel.setFont(new Font("Verdana", 1, 100));
		this.speed_jlabel.setHorizontalAlignment(JLabel.CENTER);
		this.speed_jlabel.setVerticalAlignment(JLabel.CENTER);
		
		this.acceleration_intensity_jtextfield = new JTextField();
		this.send_acceleration_intensity_button = new JButton("Go");
		
		this.send_acceleration_intensity_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				controller.processAccelerationIntensitymodification(Float.parseFloat(acceleration_intensity_jtextfield.getText()));
				acceleration_intensity_jtextfield.setText("");
			}
		});
		
		
		
		
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
        
        this.setSize(800, 500);
	    this.setVisible(true);
        
	   	
	}
	
	public void processSpeedModification(float speed){
		
		DecimalFormat df = new DecimalFormat("##.#");
		df.setRoundingMode(RoundingMode.DOWN);
		this.speed_jlabel.setText(df.format(speed) + " km/h");
	}
}
