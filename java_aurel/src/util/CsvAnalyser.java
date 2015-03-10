package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CsvAnalyser {
	
	
	public static final String folder="C:/Users/Aurélien/ecoconduite-android-insa/eco-conduite_logs/";

	public static LinkedList<Point> generateListe(String fileName){
		String line = "";
		String cvsSplitBy = ";";
		LinkedList<Point> list = new LinkedList<Point>();
		BufferedReader br=null;
		boolean premiereLigne=true;
		
		File folderFile = new File(folder);
		String path=folderFile.getAbsolutePath();
		System.out.println(path);
		
		File file=new File(path+"/"+fileName);
		

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (!premiereLigne){
					String fields[]=line.split(cvsSplitBy);
					int stamp=Integer.parseInt(fields[1]);
					float speed=Float.parseFloat(fields[6]);
					Point point = new Point(stamp, speed);
					list.add(point);
					
				} else {
					premiereLigne=false;
				}

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
			
		}
		
		return list;

	}


	public static void main(String[] args) {
		LinkedList<Point> list=CsvAnalyser.generateListe("2014-02-24_18-17-01.csv");
		for (Point point : list) {
			System.out.println(point);
		}
	}

}
