import java.util.LinkedList;

import util.Chart;
import util.CsvAnalyser;
import util.Point;


public class Main {
	
	public static void main(String[] args) {
		System.out.println("Logiciel d'analyse de la trace");
				System.out.println("Aurélien Tamas Leloup");
		LinkedList<Point> list=CsvAnalyser.generateListe("2014-02-24_18-17-01.csv");
		Chart chart =new Chart(list);
		chart.draw();
	}

}
