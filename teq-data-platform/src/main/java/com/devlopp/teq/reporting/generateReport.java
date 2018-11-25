package com.devlopp.teq.reporting;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import com.devlopp.teq.databasepreset.DatabasePresetQuery;
import com.devlopp.teq.databasepreset.DatabasePresetQueryHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;


public class generateReport {
	
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
}
