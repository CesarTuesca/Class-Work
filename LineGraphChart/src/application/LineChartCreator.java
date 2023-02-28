package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class LineChartCreator extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			NumberAxis xAxis = new NumberAxis(0,90, 10);
			xAxis.setLabel("Number of values in fibonacci sequence");
			
			NumberAxis yAxis = new NumberAxis(0,3000000, 250000);
			yAxis.setLabel("Runtime (nanoseconds)");
			
			LineChart lineChart = new LineChart(xAxis, yAxis);
			
			XYChart.Series series = new XYChart.Series();
			series.setName("Iterative Fibonacci Program Runtimes");
			series.getData().add(new XYChart.Data(0, 0));
			series.getData().add(new XYChart.Data(10, 843000));
			series.getData().add(new XYChart.Data(20, 820200));
			series.getData().add(new XYChart.Data(30, 906400));
			series.getData().add(new XYChart.Data(40, 1058300));
			series.getData().add(new XYChart.Data(50, 1106400));
			series.getData().add(new XYChart.Data(60, 1233200));
			series.getData().add(new XYChart.Data(70, 1262100));
			series.getData().add(new XYChart.Data(80, 1436600));
			series.getData().add(new XYChart.Data(90, 1830500));
			
			XYChart.Series series2 = new XYChart.Series();
			series2.setName("Recursive Fibonacci Program Runtimes");
			series2.getData().add(new XYChart.Data(0, 0));
			series2.getData().add(new XYChart.Data(10, 897400));
			series2.getData().add(new XYChart.Data(20, 772400));
			series2.getData().add(new XYChart.Data(30, 956700));
			series2.getData().add(new XYChart.Data(40, 976200));
			series2.getData().add(new XYChart.Data(50, 1216500));
			series2.getData().add(new XYChart.Data(60, 1179500));
			series2.getData().add(new XYChart.Data(70, 1419800));
			series2.getData().add(new XYChart.Data(80, 1834900));
			series2.getData().add(new XYChart.Data(90, 1621300));
			
			lineChart.getData().addAll(series, series2);
			lineChart.setTitle("Fibonacci Line Chart");
			
			Group root = new Group(lineChart);
			Scene scene = new Scene(root, 600, 400);
			primaryStage.setTitle("Fibonacci Line Chart");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
