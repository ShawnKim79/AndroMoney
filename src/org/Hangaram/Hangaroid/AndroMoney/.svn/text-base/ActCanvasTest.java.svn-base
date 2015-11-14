package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ActCanvasTest extends Activity {
	
	private class onDateChangedListener implements ViewCalendar.DateChangedListener {

		@Override
		public void onDateChanged( View view, Calendar calendar ) {
			// TODO Auto-generated method stub
			
			StringBuilder sb = new StringBuilder();
			sb.append( calendar.get( Calendar.YEAR ) );
			sb.append( '-' );
			sb.append( calendar.get( Calendar.MONTH ) );
			sb.append( '-' );
			sb.append( calendar.get( Calendar.DAY_OF_MONTH ) );
			
			Utility.popupToast(view.getContext(), sb.toString(), 1 );
		}
	}
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );	
		
		ViewCalendar cal = new ViewCalendar( this );
		cal.onDateChangedListener = new onDateChangedListener(); 
		this.setContentView( cal );
		
	}
}
