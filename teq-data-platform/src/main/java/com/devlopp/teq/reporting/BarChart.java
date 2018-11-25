package com.devlopp.teq.reporting;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart extends ApplicationFrame {
   
   public BarChart( String applicationTitle , String chartTitle, CategoryDataset dataSet, String Xtitle, String Ytitle, CategoryDataset dataset ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(chartTitle, Xtitle, Ytitle, dataset,          
         PlotOrientation.VERTICAL, true, true, false);
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
}