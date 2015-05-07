package ecoDriver;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;

import Controller.Controller;

public class EcoDriver {

	
	public static void main(String[] args){
		
		
		 JFileChooser chooser = new JFileChooser();
		    int result = chooser.showOpenDialog(null);
		    switch (result) {
			    case JFileChooser.APPROVE_OPTION:
			      System.out.println("Approve (Open or Save) was clicked");
			      // opens the window where the messages will be received and sent
			      System.out.println();
			      
			      try{
			    	  String path = "";
			    	  List<String> lines_list = Files.readAllLines(Paths.get(chooser.getSelectedFile().getAbsolutePath()), StandardCharsets.UTF_8);
			    	  for(int i=0; i<lines_list.size(); i++){
				    	   path = path.concat(lines_list.get(i));
				      }
			    	  
			    	  Controller controller = new Controller(path);
			    	  
			      }catch(Exception e){
			    	  System.out.println("Fichier inconnu...");
			      }
			      
				  
			      break;
			    case JFileChooser.CANCEL_OPTION:
			      System.out.println("Cancel or the close-dialog icon was clicked");
			      break;
			    case JFileChooser.ERROR_OPTION:
			      System.out.println("Error");
			      break;
		    }
		
		
		
	}
	
}
