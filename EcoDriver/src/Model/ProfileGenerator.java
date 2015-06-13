package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class ProfileGenerator {
	
	public ProfileGenerator(){}
	
	public static ArrayList<double[]> generateProfile(String[] speeds, String[] times) throws Exception{
		
		if(speeds.length != times.length)
			throw new Exception("Argument incorrects");
		
		ArrayList<double[]> resultat = new ArrayList<double[]>();
		BufferedReader speeds_br = null;
		BufferedReader times_br = null;
		String speed_value = "";
		String time_value = "";
		
		for(int i=0; i < speeds.length; ++i){
			
			speeds_br = new BufferedReader(new FileReader(speeds[i]));
			times_br = new BufferedReader(new FileReader(times[i]));
			
			while ((speed_value = speeds_br.readLine()) != null &&  (time_value = times_br.readLine()) != null ) {
				resultat.add(new double[]{Double.parseDouble(time_value),Double.parseDouble(speed_value)});
			}
						
			speeds_br.close();
			times_br.close();
			
		}
		
		Collections.sort(resultat, new Comparator<double[]>() {
            public int compare(double[] doubles, double[] otherDoubles) {
                if (doubles[1] < otherDoubles[1]){
                	return 1;
                }else{
                	return -1;
                }
            }
        });
		
		
		return resultat;
			
		
	}
	
	
	
}
