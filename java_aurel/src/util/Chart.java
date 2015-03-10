package util;

import java.util.LinkedList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class Chart {
	
	private LinkedList<Point> data;
	
	public static final String nomAppli="Ecoconduite";
	public static final String graphe="Vitesse en fonction du temps";
	
	public Chart(LinkedList<Point> list){
		this.data=list;
	}
	
	
	public LinkedList<Point> getData() {
		return data;
	}





	public void setData(LinkedList<Point> data) {
		this.data = data;
	}

	public void draw(){
		XYSeries series = new XYSeries(Chart.graphe);
		for (Point point : data) {
			series.add(point.getStamp(),point.getSpeed());
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		ApplicationFrame frame = new ApplicationFrame(Chart.nomAppli);
		XYSplineRenderer spline = new XYSplineRenderer();

		NumberAxis xax = new NumberAxis("Temps");
		NumberAxis yax = new NumberAxis("Vitesse");
		XYPlot plot = new XYPlot(dataset, xax, yax, spline);
		JFreeChart chart = new JFreeChart(plot);

		ChartPanel chartPanel = new ChartPanel(chart);
		frame.setContentPane(chartPanel);
		frame.pack();
		frame.setVisible(true);
		
	}




}
