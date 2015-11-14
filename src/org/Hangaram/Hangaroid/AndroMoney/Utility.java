package org.Hangaram.Hangaroid.AndroMoney;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Utility {
	public static View InsertWidget( Activity activity, int layoutid, int widgetid )
	{
		LinearLayout main = (LinearLayout)activity.findViewById( layoutid );
		View view = View.inflate( activity, widgetid, null );
		main.addView(view, new LinearLayout.LayoutParams( 
				main.getLayoutParams().width,
				main.getLayoutParams().height ));
		
		return view;
	}
	
	public static View InflateWidget( Context context, int widgetid )
	{
		View view = View.inflate( context, widgetid, null );
		return view;
	}

	private static final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location)
		{
			
		}
		public void onProviderDisabled(String provider) {}
		public void onProviderEnabled(String provider) {}
		public void onStatusChanged(String provider, int status, Bundle extras) {}
	};
	
	public static String getLocationString(Context context)
	{
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // ��Ȯ�� ����
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM); // ���� �Һ�
		criteria.setAltitudeRequired(false); // �� ��? ����
		criteria.setBearingRequired(false); // ���� ����?
		criteria.setSpeedRequired(false); // �ӵ�
		criteria.setCostAllowed(true); // ���
		
		LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE); 
		
		String provider = locationManager.getBestProvider(criteria, true);
		// 10�� �̻�, 100���� �̻� ���� üũ
		locationManager.requestLocationUpdates(provider, 10000, 100, locationListener); 
		Location location = locationManager.getLastKnownLocation(provider);
		if(location==null)
		{
			return null;
		}
		String strLocation = location.getLongitude() + ", " + location.getLatitude();
		return strLocation;		
	}
	
	public static void popupToast( Context context, String text, int duration )
	{
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();	
	}
}
