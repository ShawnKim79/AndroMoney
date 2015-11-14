package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Date;

import android.app.TabActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TabHost;

public class main extends TabActivity
{
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		
		try
		{
			// DB - shkim 2010-03-08 AM 00:00
			DBAdapter dbAdt = new DBAdapter(this);
			//dbAdt.open();
			//dbAdt.insertEvent("Test Event", "2010-03-01", "2010-03-09");
			//dbAdt.insertSMS("2010-03-09 23:31:09","010-1112-3334", "assa~~", false, "127.00, 37.69");
			//dbAdt.insertTransaction(1, 1, 1, 1, 20000, new Date(110,3,8,18,39,23), "Test 02", "Photo 02", "123, 0000", 1);
			//dbAdt.insertTransaction(1, 1, 1, 1, 20010, "2010-03-08 18:39:23", "Test 03", "Photo 03", "124, 0002", 2);
			//dbAdt.close();
			
			 TabHost tabHost = this.getTabHost();
			 
			 // ActHome »ðÀÔ
			 tabHost.addTab( tabHost.newTabSpec("one" )
					 		 .setIndicator( "Home", getResources().getDrawable(R.drawable.home ) )
					 		 .setContent( new Intent( this, ActHome.class ) ) );
			 
			// ActSearch »ðÀÔ
			 tabHost.addTab( tabHost.newTabSpec( "two" )
					 		 .setIndicator( "Search", getResources().getDrawable(R.drawable.search ) )
					 		 .setContent( new Intent( this, ActSearch.class ) ) );
			 
			 // ActReport »ðÀÔ
			 tabHost.addTab( tabHost.newTabSpec( "three" )
					 		 .setIndicator( "Report", getResources().getDrawable(R.drawable.report ) )
					 		 .setContent( new Intent( this, ActReport.class ) ) );
			 
			// ActSetting »ðÀÔ
			 tabHost.addTab( tabHost.newTabSpec( "four" )
					 		 .setIndicator("Setting", getResources().getDrawable(R.drawable.setting) )
					 		 .setContent( new Intent( this, ActSetting.class ) ) );
			 
			 tabHost.addTab( tabHost.newTabSpec( "five" )
					 		 .setIndicator("Canvas", getResources().getDrawable(R.drawable.chart_bar) )
					 		 .setContent( new Intent( this, ActCanvasTest.class ) ) );
		}
		
		catch( Exception e )
		{
			
		}
	}
}