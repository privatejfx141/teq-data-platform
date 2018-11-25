package com.devlopp.teq.reporting;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChart extends ApplicationFrame {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -6006119737348805994L;

    public BarChart(String applicationTitle, String chartTitle, String Xtitle, String Ytitle, CategoryDataset dataset) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(chartTitle, Xtitle, Ytitle, dataset, PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

}