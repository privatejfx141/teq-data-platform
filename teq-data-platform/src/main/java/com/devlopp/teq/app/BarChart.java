package com.devlopp.teq.app;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart extends ApplicationFrame {
   
   public BarChart( String applicationTitle , String chartTitle, CategoryDataset dataSet, String Xtitle, String Ytitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(chartTitle, Xtitle, Ytitle, createDataset(),          
         PlotOrientation.VERTICAL, true, true, false);
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   public static CategoryDataset createDataset( ) {
      String cat1 = "CAT1";        
      String cat2 = "CAT2";                
      String one = "one";        
      String two = "two";        
      String three = "three";            
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

      dataset.addValue( 1.0 , cat1 , one );        
      dataset.addValue( 2.0 , cat1 , two );        
      dataset.addValue( 3.0 , cat1 , three ); 
      
      dataset.addValue( 4.0 , cat2 , one );        
      dataset.addValue( 6.0 , cat2 , two );       
      dataset.addValue( 8.0 , cat2 , three );        
      
      return dataset; 
   }
   
   public static void main( String[ ] args ) {
	  CategoryDataset d = createDataset();
	  String title = "Tittle";
	  String Xtitle = "xTittle";
	  String Ytitle = "yTittle";
      BarChart chart = new BarChart("DB Report", title, d, Xtitle, Ytitle);
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}