package org.Hangaram.Hangaroid.AndroMoney;

import java.text.ParseException;
import android.database.Cursor;
import java.util.Date;

public class typeSMS {
	public static Date ReceiveTime;
	public static String PhoneNumber;
	public static String Content;
	public static boolean TransFlag;
	public static String GPSLocation;

	public typeSMS()
	{
		
	}

	public typeSMS(String receiveTime, String phoneNumber, String content, boolean transFlag, String gpsLocation)
	{
		this.setReceiveTime(receiveTime);
		this.setPhoneNumber(phoneNumber);
		this.setContent(content);
		this.setTransFlag(transFlag);
		this.setGPSLocation(gpsLocation);
	}
		
	public typeSMS(Date receiveTime, String phoneNumber, String content, boolean transFlag, String gpsLocation)
	{
		this.setReceiveTime(receiveTime);
		this.setPhoneNumber(phoneNumber);
		this.setContent(content);
		this.setTransFlag(transFlag);
		this.setGPSLocation(gpsLocation);
	}
	
	public typeSMS(Cursor c)
	{
		this.setReceiveTime(c.getString(1));
		this.setPhoneNumber(c.getString(2));
		this.setContent(c.getString(3));
		this.setTransFlag(c.getInt(4)==1 ? true : false);
		this.setGPSLocation(c.getString(5));
	}
	
	public void setReceiveTime(Date receiveTime)
	{
		ReceiveTime = receiveTime;
	}
	
	public void setReceiveTime(String receiveTime)
	{
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ReceiveTime = dateFormat.parse(receiveTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date getReceiveTime()
	{
		return ReceiveTime;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		PhoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber()
	{
		return PhoneNumber;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public boolean isTransFlag() {
		return TransFlag;
	}

	public void setTransFlag(boolean transFlag) {
		TransFlag = transFlag;
	}

	public String getGPSLocation() {
		return GPSLocation;
	}

	public void setGPSLocation(String gpsLocation) {
		GPSLocation = gpsLocation;
	}

	//will be obsolete -> using : new typeSMS(Cursor c);
	public static typeSMS newInstanceByCursor(Cursor cursor)
	{
		return new typeSMS(cursor);
		/*
		typeSMS sms = new typeSMS();
		sms.setReceiveTime(cursor.getString(1));		
		sms.setPhoneNumber(cursor.getString(2));
		sms.setContent(cursor.getString(3));
		sms.setTransFlag(cursor.getInt(4)==1 ? true : false);
		sms.setGPSLocation(cursor.getString(5));
		return sms;
		*/
	}
}
