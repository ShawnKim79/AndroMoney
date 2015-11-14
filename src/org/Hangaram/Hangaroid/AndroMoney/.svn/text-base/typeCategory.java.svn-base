package org.Hangaram.Hangaroid.AndroMoney;

import android.database.Cursor;

public class typeCategory {
	private static String Title;
	private static String IconId;
	private static boolean IsIncome;
	private static long FavoriteOrder;
	
	public typeCategory()
	{
	}
	
	public typeCategory(String title, String iconId, boolean isIncome, long favoriteOrder)
	{
		this.setTitle(title);
		this.setIconId(iconId);
		this.setIsIncome(isIncome);
		this.setFavoriteOrder(favoriteOrder);
	}
	
	public typeCategory(Cursor c)
	{
		this.setTitle(c.getString(1));
		this.setIconId(c.getString(2));
		this.setIsIncome(c.getInt(3)==1 ? true : false);
		this.setFavoriteOrder(c.getLong(4));
	}
	
	public void setTitle(String title)
	{
		Title = title;
	}
	
	public String getTitle()
	{
		return Title;
	}
	
	public void setIconId(String iconId)
	{
		IconId = iconId;
	}
	
	public String getIconId()
	{
		return IconId;
	}
	
	public void setIsIncome(boolean isIncome)
	{
		IsIncome = isIncome;
	}

	public boolean getIsIncome()
	{
		return IsIncome;
	}
	
	public boolean IsIncome()
	{
		return IsIncome;
	}
	
	public void setFavoriteOrder(long favoriteOrder)
	{
		FavoriteOrder = favoriteOrder;
	}
	
	public long getFavoriteOrder()
	{
		return FavoriteOrder;
	}
	
	//will be obsolete -> using : new typeCategory(Cursor c);
	public static typeCategory newInstanceByCursor(Cursor cursor)
	{
		return new typeCategory(cursor);
		/*
		typeCategory cate = new typeCategory();
		cate.setTitle(cursor.getString(1));
		cate.setIconId(cursor.getString(2));
		cate.setIsIncome(cursor.getInt(3)==1 ? true : false);
		cate.setFavoriteOrder(cursor.getLong(4));
		return cate;
		*/
	}
}
