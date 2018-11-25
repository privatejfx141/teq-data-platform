package com.devlopp.teq.reporting;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;
import com.devlopp.teq.databasepreset.DatabasePresetQuery;
import com.devlopp.teq.databasepreset.DatabasePresetQueryHelper;



public class generateReport {
	
	/**
	 * Create a visual bar line graph to to show the user of a service over time
	 * 
	 * @param serviceType name of the service type
	 * @param yearStart year to start tracking use of the service
	 * @param yearEnd year to stop tracking use of the service
	 */
	public static void generateTrendsInService(String serviceType, Integer yearStart, Integer yearEnd) {
		List<Date> dates = null;
		try {
			dates = DatabasePresetQueryHelper.getListOfStartDates(serviceType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		int count = 0;
		Integer currYear = yearStart;
		for(int i=1; currYear<=yearEnd; i++) {
			count = 0;
			for(Date date : dates) {
				if (date.getYear() == currYear)	{
					count ++;
				}
			}
			dataset.addValue(count, "Number of People Using " + serviceType, currYear.toString());
			currYear ++;
		}
		LineChart chart = new LineChart("Chart", "Year: # People using " + serviceType, "Year",
										"Number of People", dataset);
		chart.pack( );
	    RefineryUtilities.centerFrameOnScreen( chart );
	    chart.setVisible( true );
		
	}
	/**
	 * Create a visual pie chart to show percentage of ages in the database
	 */
	public static void generateChartofAge() {
		String interval = "";
		int AGE_INTERVALS = 10;
		int minAge;
		int maxAge;
		DefaultPieDataset dataset = new DefaultPieDataset( );
		int i;
		for (i = 1; i<=AGE_INTERVALS; i++) {
			minAge = 1+(10*(i-1));
			maxAge = 10*i;
			try {
				interval = DatabasePresetQuery.getPercentageOfClientsWithinAgeRange(minAge, maxAge);
				interval = "20.0%";
			} catch (SQLException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
			double percent = Double.parseDouble(interval.substring(0, interval.length()-1));
			dataset.setValue( "Age " + minAge + " to " + maxAge , percent );
		}
	
		PieChart chart = new PieChart( "Percentage of Age Ranges", "Percentage of Age Ranges", dataset );  
		chart.setSize( 560 , 367 );    
	    RefineryUtilities.centerFrameOnScreen( chart );    
	    chart.setVisible( true ); 
		
		
	}
	
	/**
	 * Create a visual bar graph to show use of each service type
	 */
	public static void generateChartOfServicesUsed() {
		DefaultCategoryDataset dataset =  new DefaultCategoryDataset( ); 
		String numUsers = "";
		int i;
		try {
			numUsers = DatabasePresetQuery.getNumberUsersServices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arrayNumUsers = numUsers.split("\n");
		for(i=0;i<arrayNumUsers.length;i++) {
			if (i == 0) {
				//pass
			} else {
				int lastIndex = arrayNumUsers[i].length();
				String templateType = arrayNumUsers[i].substring(0, arrayNumUsers[i].indexOf(":"));
				String strNumUsers = arrayNumUsers[i].substring(lastIndex-1,lastIndex);
				Double doubleNumUsers = Double.parseDouble(strNumUsers);
				dataset.addValue(doubleNumUsers, templateType, "a");   
			}
		}
		BarChart chart = new BarChart("Number of People Using Services", "Number of People Using Services", "Service Type", "Number of People", dataset);
	    chart.pack( );        
	    RefineryUtilities.centerFrameOnScreen( chart );        
	    chart.setVisible( true ); 
	
	}
	
}
