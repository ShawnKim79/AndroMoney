package org.Hangaram.Hangaroid.AndroMoney;

public class CreditCardModel {
	long ldx;
	String cardName;
	int nPaymentDay;
	int nCloseDay;
	int nLimit;
	
	CreditCardModel(long ldx, String strCardName, int nPaymentDay, int nCloseDay, int nLimit)
	{
		this.ldx = ldx;
		this.cardName = strCardName;
		this.nPaymentDay = nPaymentDay;
		this.nCloseDay = nCloseDay;
		this.nLimit = nLimit;
	}
	
	public long getldx()
	{
		return (this.ldx);
	}
	public String getCardName()
	{
		return (this.cardName);
	}
	public int getPaymentDay()
	{
		return (this.nPaymentDay);
	}
	public int getCloseDay()
	{
		return (this.nCloseDay);
	}
	public int getLimit()
	{
		return (this.nLimit);
	}
	
}
