package sample;

import javafx.application.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.*;
import java.io.*;
import javafx.scene.text.Text;
import javafx.scene.Group;


@SuppressWarnings({"rawtypes", "unchecked"})
public class Main extends Application {
//
//    /* PROJECT: You will set these values based on the data file. */
    private int yAxisMin, yAxisMax; // the min and max of claims paid
    private String dataLabel; // the year of the data

    private static final int TICK_INTERVAL = 1000; // used to customize the visual display

    /* PROJECT: Test with all three valid file names and one invalid file name. */
//    private final String fileName = "Disability_Insurance_2016.csv";
    // private final String fileName = "Disability_Insurance_2017.csv";
//     private final String fileName = "Disability_Insurance_2018.csv";
     private final String fileName = "Disability_Insurance_BadFileName.csv";

    // use this file ONLY if completing the extra credit
//     private final String fileName = "Disability_Insurance_2016-2018_ExtraCredit.csv";
    
    
    @Override
    public void start(Stage stage) {

        
        /* creates the chart, x-axis, and y-axis */
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        BarChart barChart = new BarChart(xAxis, yAxis);
        barChart.setTitle("Disability Insurance Claims Paid");
        xAxis.setLabel("Month");
        yAxis.setLabel("Claims Paid");
        yAxis.setTickUnit(TICK_INTERVAL);

        /* PROJECT: This is the series for the chart. You will add data to this series.  */
        Series claimsPaidByMonth = new Series();
        boolean success = fillData(claimsPaidByMonth);

        /* PROJECT: Once the fillData method is done, you have set the yAxisMin, yAxisMax,
         * and dataLabel variables. This code now updates the display using those values. */
        int yAxisDisplayMin = getVisuallyAppealingAxisValue(yAxisMin);
        int yAxisDisplayMax = getVisuallyAppealingAxisValue(yAxisMax);
        yAxis.setLowerBound(yAxisDisplayMin-TICK_INTERVAL);
        yAxis.setUpperBound(yAxisDisplayMax+TICK_INTERVAL);
        claimsPaidByMonth.setName(dataLabel);

        // adds the data series to the chart, creates a scene, sets the stage, and shows!
        barChart.getData().add(claimsPaidByMonth);
        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    /* PROJECT: Your code goes in this method. I've added comments below
     * to remind you of what this method needs to do. */
    private boolean fillData(Series series) {
        /* PROJECT: YOUR CODE GOES HERE! */
        String filePath = "/Users/jack/IdeaProjects/TestOnceMore/src/sample/";
        String finalFilePath = filePath + fileName;
        try {
            BufferedReader read = new BufferedReader(new FileReader(finalFilePath));
            String line = read.readLine();
            dataLabel = line;
        }
        catch(FileNotFoundException ex) {
            Alert alert = new Alert(AlertType.ERROR, "The file: " + fileName + " does not exist", ButtonType.CLOSE);
            alert.showAndWait();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        // read in the data file
        // get the dataLabel (the year) from the first line of the file
        // use a Scanner to parse the remaining lines of the file, which each contain a month name and value
        // create a Data object to add to the series for each month/value pair: series.getData().add(new Data(monthLabel, monthValue));
        // calculate the min and max values seen and update yAxisMin and yAxisMax based on these values
        // use exception handling to account for a bad file. if a file is invalid, display an alert

        return true; // this line here only so the code compiles; remove and updated as appropriate!
    }

    private int getVisuallyAppealingAxisValue(int value) {
        return (value + (TICK_INTERVAL-1)) / TICK_INTERVAL  * TICK_INTERVAL;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}