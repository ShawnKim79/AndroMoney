package org.Hangaram.Hangaroid.AndroMoney;

import android.database.Cursor;
import java.text.ParseException;
import java.util.Date;

public class typeTransaction {
	private static long Cost;
	private static long Creditcard_Idx;
	private static long Event_Idx;
	private static long Category_Idx;
	private static long SMS_Idx;
	private static Date BillingTime;
	private static String Content;
	private static String Photo;
	private static String GPSLocation;
	private static long InstallmentPlan;
	
	public typeTransaction()
	{
		
	}
	
	public typeTransaction(long cost, long creditcardIdx, long eventIdx, long categoryIdx, long smsIdx, String billingTime, String content, String photo, String gpsLocation, long installmentPlan)
	{
		this.setCost(cost);
		this.setCreditcard_Idx(creditcardIdx);
		this.setEvent_Idx(eventIdx);
		this.setCategory_Idx(categoryIdx);
		this.setSMS_Idx(smsIdx);
		this.setBillingTime(billingTime);
		this.setContent(content);
		this.setPhoto(photo);
		this.setGPSLocation(gpsLocation);
		this.setInstallmentPlan(installmentPlan);
	}
	
	public typeTransaction(long cost, long creditcardIdx, long eventIdx, long categoryIdx, long smsIdx, Date billingTime, String content, String photo, String gpsLocation, long installmentPlan)
	{
		this.setCost(cost);
		this.setCreditcard_Idx(creditcardIdx);
		this.setEvent_Idx(eventIdx);
		this.setCategory_Idx(categoryIdx);
		this.setSMS_Idx(smsIdx);
		this.setBillingTime(billingTime);
		this.setContent(content);
		this.setPhoto(photo);
		this.setGPSLocation(gpsLocation);
		this.setInstallmentPlan(installmentPlan);	
	}
	
	public typeTransaction(Cursor c)
	{
		this.setCreditcard_Idx(c.getLong(1));
		this.setEvent_Idx(c.getLong(2)); 
		this.setCategory_Idx(c.getLong(3));
		this.setSMS_Idx(c.getLong(4));
		this.setCost(c.getLong(5));
		this.setBillingTime(c.getString(6));
		this.setContent(c.getString(7));
		this.setPhoto(c.getString(8));
		this.setGPSLocation(c.getString(9));
		this.setInstallmentPlan(c.getLong(10));
	}
	
	public long getCost() {
		return Cost;
	}
	public void setCost(long cost) {
		Cost = cost;
	}
	public long getCreditcard_Idx() {
		return Creditcard_Idx;
	}
	public void setCreditcard_Idx(long creditcardIdx) {
		Creditcard_Idx = creditcardIdx;
	}
	public long getEvent_Idx() {
		return Event_Idx;
	}
	public void setEvent_Idx(long eventIdx) {
		Event_Idx = eventIdx;
	}
	public long getCategory_Idx() {
		return Category_Idx;
	}
	public void setCategory_Idx(long categoryIdx) {
		Category_Idx = categoryIdx;
	}
	public long getSMS_Idx() {
		return SMS_Idx;
	}
	public void setSMS_Idx(long smsIdx) {
		SMS_Idx = smsIdx;
	}
	public Date getBillingTime() {
		return BillingTime;
	}
	public void setBillingTime(Date billingTime) {
		BillingTime = billingTime;
	}
	public void setBillingTime(String billingTime) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			BillingTime = dateFormat.parse(billingTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getGPSLocation() {
		return GPSLocation;
	}
	public void setGPSLocation(String gpsLocation) {
		GPSLocation = gpsLocation;
	}
	public long getInstallmentPlan() {
		return InstallmentPlan;
	}
	public void setInstallmentPlan(long installmentPlan) {
		InstallmentPlan = installmentPlan;
	}
	
	//will be obsolete -> using : new typeTransaction(Cursor c);
	public static typeTransaction newInstanceByCursor(Cursor cursor)
	{
		return new typeTransaction(cursor);
		/*
		typeTransaction trans = new typeTransaction();
		
		trans.setCreditcard_Idx(cursor.getLong(1));
		trans.setEvent_Idx(cursor.getLong(2)); 
		trans.setCategory_Idx(cursor.getLong(3));
		trans.setSMS_Idx(cursor.getLong(4));
		trans.setCost(cursor.getLong(5));

		trans.setBillingTime(cursor.getString(6));

		trans.setContent(cursor.getString(7));
		trans.setPhoto(cursor.getString(8));
		trans.setGPSLocation(cursor.getString(9));
		trans.setInstallmentPlan(cursor.getLong(10));
		
		return trans;
		*/
	}
}
