package com.devlopp.teq.app;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;



public class PieChart extends JFrame {

    public PieChart(String applicationTitle, String chartTitle, PieDataset dataset) {
        super(applicationTitle);
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset
     */
    public static  PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("cat1", 1);
        result.setValue("cat2", 3);
        result.setValue("cat4", 5);
        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(150);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
    

    public static void main(String[] args) {
    	PieDataset dataset = createDataset();
    	PieChart demo = new PieChart("App title", "Graph title", dataset);
    	demo.pack();
    	demo.setVisible(true);
    }
}