package org.Hangaram.Hangaroid.AndroMoney;

import android.database.Cursor;

public class typeCreditcard {
	private long Ldx;
	private String Title;
	private int PaymentDay;
	private int ClosingDay;
	private long LimitCost;
	
	public typeCreditcard()
	{
		
	}
	
	public typeCreditcard(long ldx, String title, int paymentDay, int closingDay, long limitCost)
	{
		this.setldx(ldx);
		this.setTitle(title);
		this.setPaymentDay(paymentDay);
		this.setClosingDay(closingDay);
		this.setLimitCost(limitCost);
	}
	
	public typeCreditcard(Cursor c)
	{
		this.setldx(c.getLong(0));
		this.setTitle(c.getString(1));
		this.setPaymentDay(c.getInt(2));
		this.setClosingDay(c.getInt(3));
		this.setLimitCost(c.getLong(4));
	}
	
	public void setldx(long ldx)
	{
		Ldx = ldx;
	}
	public long getldx()
	{
		return Ldx;
	}
	public void setTitle(String title)
	{
		Title = title;
	}
	
	public String getTitle()
	{
		return Title;
	}
	
	public void setPaymentDay(int paymentDay)
	{
		PaymentDay = paymentDay;
	}
	
	public int getPaymentDay()
	{
		return PaymentDay;
	}
	
	public void setLimitCost(long limitCost)
	{
		LimitCost = limitCost;
	}
	
	public long getLimitCost()
	{
		return LimitCost;
	}

	public int getClosingDay() {
		return ClosingDay;
	}

	public void setClosingDay(int closingDay) {
		ClosingDay = closingDay;
	}

	//will be obsolete -> using : new typeCreditcard(Cursor c);
	public static typeCreditcard newInstanceByCursor(Cursor cursor)
	{
		return new typeCreditcard(cursor);
		/*
		typeCreditcard card = new typeCreditcard();
		
		card.setTitle(cursor.getString(1));
		card.setPaymentDay(cursor.getInt(2));
		card.setClosingDay(cursor.getInt(3));
		card.setLimitCost(cursor.getLong(4));
		
		return card;
		*/
	}
}
