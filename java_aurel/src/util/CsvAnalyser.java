package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvAnalyser {
	
	
	public static final String folder="C:/Users/Aurélien/ecoconduite-android-insa/eco-conduite_logs/";

	public static List generateListe(String fileName){
		String line = "";
		String cvsSplitBy = ",";
		BufferedReader br=null;
		
		File folderFile = new File(folder);
		String path=folderFile.getAbsolutePath();
		System.out.println(path);
		
		File file=new File(path+"/"+fileName);
		

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {

				System.out.println(line);

			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

	}


	public static void main(String[] args) {
		CsvAnalyser.generateListe("2014-02-24_18-17-01.csv");
	}

}
