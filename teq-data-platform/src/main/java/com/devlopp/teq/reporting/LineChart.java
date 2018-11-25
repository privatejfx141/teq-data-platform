package com.devlopp.teq.reporting;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame {

   public LineChart( String applicationTitle , String chartTitle, String Xname, String Yname, DefaultCategoryDataset dataset) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, Xname, Yname, dataset,
         PlotOrientation.VERTICAL, true,true,false);
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   

}