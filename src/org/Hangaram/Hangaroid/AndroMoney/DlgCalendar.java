package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/*
public class DlgCalendar extends Dialog{
	public DlgCalendar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setContentView( R.layout.dlgcalendar );
		ViewCalendar cal = new ViewCalendar( this.getContext() );
		LinearLayout holder = (LinearLayout)findViewById( R.id.dlgcalendar_calendarholder );
		holder.addView( cal );
	}
}
*/

public class DlgCalendar extends Activity implements OnClickListener {
	
	private ViewCalendar cal = null;
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		// TODO Auto-generated constructor stub
		this.setContentView( R.layout.dlgcalendar );
		cal = new ViewCalendar( this );
		LinearLayout holder = (LinearLayout)findViewById( R.id.dlgcalendar_calendarholder );
		holder.addView( cal );
		Button btnok = (Button)findViewById( R.id.dlgcalendar_btnok );
		Button btncancel = (Button)findViewById( R.id.dlgcalendar_btncancel );
		btnok.setOnClickListener( this );
		btncancel.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		if( id == R.id.dlgcalendar_btnok )
		{
			Intent intent = getIntent();
			Calendar date = cal.getSelectedDate();
			intent.putExtra( "year", date.get( Calendar.YEAR ) );
			intent.putExtra( "month", date.get( Calendar.MONTH ) );
			intent.putExtra( "day", date.get( Calendar.DAY_OF_MONTH ) );
			setResult( RESULT_OK, intent );
			finish();
		}
		
		else if( id == R.id.dlgcalendar_btncancel )
		{
			setResult( RESULT_CANCELED );
			finish();
		}
	}
}
