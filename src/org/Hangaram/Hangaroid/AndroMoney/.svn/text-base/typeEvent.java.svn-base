package org.Hangaram.Hangaroid.AndroMoney;

import java.text.ParseException;
import java.util.Date;

import android.database.Cursor;

public class typeEvent {
	public static String Title;
	public static Date BeginDate;
	public static Date EndDate;
	
	public typeEvent()
	{
		
	}
	
	public typeEvent(String title, Date beginDate, Date endDate)
	{
		this.setTitle(title);
		this.setBeginDate(beginDate);
		this.setEndDate(endDate);
	}
	
	public typeEvent(Cursor c)
	{
		this.setTitle(c.getString(1));
		this.setBeginDate(c.getString(2));
		this.setEndDate(c.getString(3));
	}

	public typeEvent(String title, String beginDate, String endDate)
	{
		this.setTitle(title);
		this.setBeginDate(beginDate);
		this.setEndDate(endDate);
	}	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Date getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(Date beginDate) {
		BeginDate = beginDate;
	}
	public void setBeginDate(String beginDate) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			BeginDate = dateFormat.parse(beginDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public void setEndDate(String endDate) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			EndDate = dateFormat.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// will be obsolete -> using : new typeEvent(Cursor c);	
	public static typeEvent newInstanceByCursor(Cursor cursor)
	{
		return new typeEvent(cursor);
		/*
		typeEvent event = new typeEvent();
		event.setTitle(cursor.getString(1));
		event.setBeginDate(cursor.getString(2));
		event.setEndDate(cursor.getString(3));
		return event;
		*/
	}
}
