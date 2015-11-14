package org.Hangaram.Hangaroid.AndroMoney;

import java.text.Format;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter
{
	private static final String DB_NAME = "AndroMoney.db";
	private static final int DB_VERSION = 8;
	
	private static final String DB_TB_NAME_CATEGORY = "Category";
	private static final String DB_CATEGORY_IDX = "Idx";
	private static final String DB_CATEGORY_TITLE = "Title";
	private static final String DB_CATEGORY_ICONID = "IconId";
	private static final String DB_CATEGORY_ISINCOME = "IsIncome";
	private static final String DB_CATEGORY_FAVORITEORDER = "FavoriteOrder";
		
	private static final String DB_TB_NAME_CREDITCARD = "Creditcard";
	private static final String DB_CREDITCARD_IDX = "Idx";
	private static final String DB_CREDITCARD_TITLE = "Title";
	private static final String DB_CREDITCARD_PAYMENTDAY = "PaymentDay";
	private static final String DB_CREDIRCARD_CLOSINGDAY = "ClosingDay";
	private static final String DB_CREDITCARD_LIMITCOST = "LimitCost";
	
	private static final String DB_TB_NAME_EVENT = "Event";
	private static final String DB_EVENT_IDX = "Idx";
	private static final String DB_EVENT_TITLE = "Title";
	private static final String DB_EVENT_BEGINDATE = "BeginDate";
	private static final String DB_EVENT_ENDDATE = "EndDate";
	
	private static final String DB_TB_NAME_SMS = "SMS";
	private static final String DB_SMS_IDX = "Idx";
	private static final String DB_SMS_RECEIVETIME = "ReceiveTime";
	private static final String DB_SMS_PHONENUMBER = "PhoneNumber";
	private static final String DB_SMS_CONTENT = "Content";
	private static final String DB_SMS_TRANSFLAG = "TransFlag";
	private static final String DB_SMS_GPSLOCATION = "GPSLocation";
	
	private static final String DB_TB_NAME_SMSPARSINGRULE = "SMSParsingRule";
	private static final String DB_SMSPARSINGRULE_IDX = "Idx";
	private static final String DB_SMSPARSINGRULE_CREDITCARDIDX = "Creditcard_Idx";
	private static final String DB_SMSPARSINGRULE_PHONENUMBER = "PhoneNumber";
	private static final String DB_SMSPARSINGRULE_RULE = "Rule";
	
	private static final String DB_TB_NAME_TRANSACTION = "MoneyTransaction";
	private static final String DB_TRANSACTION_IDX = "Idx";
	private static final String DB_TRANSACTION_CREDITCARDIDX = "Creditcard_Idx";
	private static final String DB_TRANSACTION_EVENTIDX = "Event_Idx";
	private static final String DB_TRANSACTION_CATEGORYIDX = "Category_Idx";
	private static final String DB_TRANSACTION_SMSIDX = "SMS_Idx";
	private static final String DB_TRANSACTION_COST = "Cost";
	private static final String DB_TRANSACTION_BILLINGTIME = "BillingTime";
	private static final String DB_TRANSACTION_CONTENT = "Content";
	private static final String DB_TRANSACTION_PHOTO = "Photo";
	private static final String DB_TRANSACTION_GPSLOCATION = "GPSLocation";
	private static final String DB_TRANSACTION_INSTALLMENTPLAN = "InstallmentPlan";
	
	private static final String DB_CREATE_CATEGORY =
		"CREATE TABLE [" + DB_TB_NAME_CATEGORY + "] ("
		+ "["+DB_CATEGORY_IDX+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," 
		+ "["+DB_CATEGORY_TITLE+"] CHAR(52) NOT NULL," 
		+ "["+DB_CATEGORY_ICONID+"] CHAR(20)," 
		+ "["+DB_CATEGORY_ISINCOME+"] BOOL,"
		+ "["+DB_CATEGORY_FAVORITEORDER+"] INTEGER DEFAULT -1"
		+ ");";
	
	private static final String DB_CREATE_CREDITCARD =
		"CREATE TABLE [" + DB_TB_NAME_CREDITCARD + "] ("
		+ "["+DB_CREDITCARD_IDX+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," 
		+ "["+DB_CREDITCARD_TITLE+"] CHAR(52) NOT NULL,"
		+ "["+DB_CREDITCARD_PAYMENTDAY+"] INTEGER,"
		+ "["+DB_CREDIRCARD_CLOSINGDAY+"] INTEGER,"
		+ "["+DB_CREDITCARD_LIMITCOST+"] INTEGER"
		+ ");";
	
	private static final String DB_CREATE_EVENT =
		"CREATE TABLE ["+DB_TB_NAME_EVENT+"] ("
		+ "["+DB_EVENT_IDX+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," 
		+ "["+DB_EVENT_TITLE+"] CHAR(52) NOT NULL," 
		+ "["+DB_EVENT_BEGINDATE+"] DATE,"
		+ "["+DB_EVENT_ENDDATE+"] DATE"
		+ ");";
	
	private static final String DB_CREATE_SMS =
		"CREATE TABLE ["+DB_TB_NAME_SMS+"] ("
		+ "["+DB_SMS_IDX+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," 
		+ "["+DB_SMS_RECEIVETIME+"] DATETIME NOT NULL," 
		+ "["+DB_SMS_PHONENUMBER+"] CHAR(52) NOT NULL," 
		+ "["+DB_SMS_CONTENT+"] CHAR(255) NOT NULL," 
		+ "["+DB_SMS_TRANSFLAG+"] BOOL NOT NULL DEFAULT FALSE," 
		+ "["+DB_SMS_GPSLOCATION+"] CHAR(255)"
		+ ");";
	
	private static final String DB_CREATE_SMSPARSINGRULE =
		"CREATE TABLE ["+DB_TB_NAME_SMSPARSINGRULE+"] ("
		+ "["+DB_SMSPARSINGRULE_IDX+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," 
		+ "["+DB_SMSPARSINGRULE_CREDITCARDIDX+"] INTEGER NOT NULL," 
		+ "["+DB_SMSPARSINGRULE_PHONENUMBER+"] CHAR(52)," 
		+ "["+DB_SMSPARSINGRULE_RULE+"] CHAR(255)"
		+ ");";
	
	private static final String DB_CREATE_TRANSACTION =
		"CREATE TABLE ["+DB_TB_NAME_TRANSACTION +"] ("
		+ "["+DB_TRANSACTION_IDX +"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," 
		+ "["+DB_TRANSACTION_CREDITCARDIDX+"] INTEGER NOT NULL,"
		+ "["+DB_TRANSACTION_EVENTIDX+"] INTEGER NOT NULL,"
		+ "["+DB_TRANSACTION_CATEGORYIDX+"] INTEGER NOT NULL," 
		+ "["+DB_TRANSACTION_SMSIDX+"] INTEGER,"
		+ "["+DB_TRANSACTION_COST+"] INTEGER NOT NULL," 
		+ "["+DB_TRANSACTION_BILLINGTIME+"] DATETIME NOT NULL," 
		+ "["+DB_TRANSACTION_CONTENT+"] CHAR(255),"
		+ "["+DB_TRANSACTION_PHOTO+"] CHAR(255),"
		+ "["+DB_TRANSACTION_GPSLOCATION+"] CHAR(255),"
		+ "["+DB_TRANSACTION_INSTALLMENTPLAN+"] INTEGER NOT NULL"
		+ ");";

	
	private boolean m_bIsCreated = false;
	public class MyDBHelper extends SQLiteOpenHelper
	{
		public MyDBHelper(Context context)
		{
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DB_CREATE_CATEGORY);
			db.execSQL(DB_CREATE_CREDITCARD);
			db.execSQL(DB_CREATE_EVENT);
			db.execSQL(DB_CREATE_SMS);
			db.execSQL(DB_CREATE_SMSPARSINGRULE);
			db.execSQL(DB_CREATE_TRANSACTION);
			
			m_bIsCreated = true;
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + " To " + newVersion);

			// 지우고 새로 생성할 게 아니라 마이그레이션 과정이 필요.. alter 같은.
			db.execSQL("DROP TABLE IF EXISTS " + DB_TB_NAME_CATEGORY);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TB_NAME_CREDITCARD);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TB_NAME_EVENT);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TB_NAME_SMS);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TB_NAME_SMSPARSINGRULE);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TB_NAME_TRANSACTION);
			
			onCreate(db);
		}
	}
	private static final String TAG = "DBAdapter";
	private MyDBHelper m_DbHelper;
	private SQLiteDatabase m_Db;
	
	public DBAdapter(Context ctx)
	{
		m_DbHelper = new MyDBHelper(ctx);
	}
	
	public DBAdapter open() throws SQLException
	{
		m_Db = m_DbHelper.getWritableDatabase();
		
		if(m_bIsCreated==true) insertDefaultData();
		return this;
	}
	
	public void close()
	{
		m_DbHelper.close();
	}

	// sms
	public long insertSMS(String strRcvTime, String strPhoneNumber, String strContent, boolean bTransFlag, String strGps)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_SMS_RECEIVETIME, strRcvTime);
		contentVal.put(DB_SMS_PHONENUMBER, strPhoneNumber);
		contentVal.put(DB_SMS_CONTENT, strContent);
		contentVal.put(DB_SMS_TRANSFLAG, bTransFlag==true?1:0);
		contentVal.put(DB_SMS_GPSLOCATION, strGps);
		
		return m_Db.insert(DB_TB_NAME_SMS, null, contentVal);
	}

	public long insertSMS(Date rcvTime, String strPhoneNumber, String strContent, boolean bTransFlag, String strGps)
	{
		ContentValues contentVal = new ContentValues();
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strRcvTime = formatter.format(rcvTime);
		contentVal.put(DB_SMS_RECEIVETIME, strRcvTime);
		contentVal.put(DB_SMS_PHONENUMBER, strPhoneNumber);
		contentVal.put(DB_SMS_CONTENT, strContent);
		contentVal.put(DB_SMS_TRANSFLAG, bTransFlag==true?1:0);
		contentVal.put(DB_SMS_GPSLOCATION, strGps);
		
		return m_Db.insert(DB_TB_NAME_SMS, null, contentVal);
	}

	public long insertSMS(typeSMS sms)
	{
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strReceiveTime = formatter.format(sms.getReceiveTime());
		
		return insertSMS(strReceiveTime,
						sms.getPhoneNumber(),
						sms.getContent(),
						sms.isTransFlag(),
						sms.getGPSLocation()
						);
	}	
	//
	public boolean deleteSMS(long idx)
	{
		return m_Db.delete(DB_TB_NAME_SMS, DB_SMS_IDX +"=" + idx, null) > 0;
	}
	
	public boolean updateSMS_TransFlag(long idx, boolean bTransFlag)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_SMS_TRANSFLAG, bTransFlag==true?1:0);
		return m_Db.update(DB_TB_NAME_SMS, contentVal, DB_SMS_IDX+"="+idx, null) > 0;
	}
	
	public Cursor fetchAllSMS()
	{
		return m_Db.query(DB_TB_NAME_SMS,
				new String[]{DB_SMS_IDX, DB_SMS_RECEIVETIME, DB_SMS_PHONENUMBER, DB_SMS_CONTENT, DB_SMS_TRANSFLAG, DB_SMS_GPSLOCATION},
				null, null, null, null, null);
	}
	
	public Cursor fetchSMS(long idx)
	{
		Cursor cursor = m_Db.query(true, DB_TB_NAME_SMS,
				new String[]{DB_SMS_IDX, DB_SMS_RECEIVETIME, DB_SMS_PHONENUMBER, DB_SMS_CONTENT, DB_SMS_TRANSFLAG, DB_SMS_GPSLOCATION},
				DB_SMS_IDX + "=" + idx,
				null, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return cursor;
	}
	
	// Category
	public long insertCategory(String strTitle, String strIconId, boolean bIsIncome, long nFavotiteOrder)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_CATEGORY_TITLE, strTitle);
		contentVal.put(DB_CATEGORY_ICONID, strIconId);
		contentVal.put(DB_CATEGORY_ISINCOME, bIsIncome==true?1:0);
		contentVal.put(DB_CATEGORY_FAVORITEORDER, nFavotiteOrder);
				
		return m_Db.insert(DB_TB_NAME_CATEGORY, null, contentVal);
	}
	
	public long insertCategory(typeCategory category)
	{
		return insertCategory(category.getTitle(),
								category.getIconId(),
								category.IsIncome(),
								category.getFavoriteOrder()
								);
	}
		
	public boolean deleteCategory(long idx)
	{
		return m_Db.delete(DB_TB_NAME_CATEGORY, DB_CATEGORY_IDX +"=" + idx, null) > 0;
	}
	
	public boolean updateCategory(long idx, String strTitle, String strIconId, boolean bIsIncome, long nFavotiteOrder)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_CATEGORY_TITLE, strTitle);
		contentVal.put(DB_CATEGORY_ICONID, strIconId);
		contentVal.put(DB_CATEGORY_ISINCOME, bIsIncome==true?1:0);
		contentVal.put(DB_CATEGORY_FAVORITEORDER, nFavotiteOrder);
		return m_Db.update(DB_TB_NAME_CATEGORY, contentVal, DB_CATEGORY_IDX+"="+idx, null) > 0;
	}

	public boolean updateCategory(long idx, typeCategory category)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_CATEGORY_TITLE, category.getTitle());
		contentVal.put(DB_CATEGORY_ICONID, category.getIconId());
		contentVal.put(DB_CATEGORY_ISINCOME, category.IsIncome());
		contentVal.put(DB_CATEGORY_FAVORITEORDER, category.getFavoriteOrder());
		return m_Db.update(DB_TB_NAME_CATEGORY, contentVal, DB_CATEGORY_IDX+"="+idx, null) > 0;
	}	

	public boolean updateCategoryFavoriteOrder(long idx, long nFavotiteOrder)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_CATEGORY_FAVORITEORDER, nFavotiteOrder);
		return m_Db.update(DB_TB_NAME_CATEGORY, contentVal, DB_CATEGORY_IDX+"="+idx, null) > 0;
	}
	
	public Cursor fetchAllCategory()
	{
		return m_Db.query(DB_TB_NAME_CATEGORY,
				new String[]{DB_CATEGORY_IDX, DB_CATEGORY_TITLE, DB_CATEGORY_ICONID, DB_CATEGORY_ISINCOME, DB_CATEGORY_FAVORITEORDER},
				null, null, null, null, null);
	}
	
	public Cursor fetchCategory(long idx)
	{
		Cursor cursor = m_Db.query(true, DB_TB_NAME_CATEGORY,
				new String[]{DB_CATEGORY_IDX, DB_CATEGORY_TITLE, DB_CATEGORY_ICONID, DB_CATEGORY_ISINCOME, DB_CATEGORY_FAVORITEORDER},
				DB_CATEGORY_IDX + "=" + idx,
				null, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return cursor;
	}

	// Credit Card
	public long insertCreditcard( String strTitle, int nPaymentDay, int nClosingDay, long nLimitCost)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_CREDITCARD_TITLE, strTitle);
		contentVal.put(DB_CREDITCARD_PAYMENTDAY, nPaymentDay);
		contentVal.put(DB_CREDIRCARD_CLOSINGDAY, nClosingDay);
		contentVal.put(DB_CREDITCARD_LIMITCOST, nLimitCost);
		
		return m_Db.insert(DB_TB_NAME_CREDITCARD, null, contentVal);
	}

	public long insertCreditcard( typeCreditcard card )
	{
		return this.insertCreditcard(card.getTitle(),
									card.getPaymentDay(),
									card.getClosingDay(),
									card.getLimitCost());
	}	
	
	public boolean deleteCreditcard(long idx)
	{
		return m_Db.delete(DB_TB_NAME_CREDITCARD, DB_CREDITCARD_IDX + "=" + idx, null) > 0;
	}
	
	public boolean updateCreditcard(long idx, String strTitle, int nPaymentDay, int nClosingDay, long nLimitCost)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_CREDITCARD_TITLE, strTitle);
		contentVal.put(DB_CREDITCARD_PAYMENTDAY, nPaymentDay);
		contentVal.put(DB_CREDIRCARD_CLOSINGDAY, nClosingDay);
		contentVal.put(DB_CREDITCARD_LIMITCOST, nLimitCost);
		return m_Db.update(DB_TB_NAME_CREDITCARD, contentVal, DB_CREDITCARD_IDX+"="+idx, null) > 0;
	}

	public boolean updateCreditcard(long idx, typeCreditcard card)
	{
		return this.updateCreditcard(idx,
				card.getTitle(),
				card.getPaymentDay(),
				card.getClosingDay(),
				card.getLimitCost());
	}
	
	public Cursor fetchAllCreditcard()
	{
		return m_Db.query(DB_TB_NAME_CREDITCARD,
				new String[]{DB_CREDITCARD_IDX, DB_CREDITCARD_TITLE, DB_CREDITCARD_PAYMENTDAY, DB_CREDIRCARD_CLOSINGDAY, DB_CREDITCARD_LIMITCOST},
				null, null, null, null, null);
	}
	
	public Cursor fetchCreditcard(long idx)
	{
		Cursor cursor = m_Db.query(true, DB_TB_NAME_CREDITCARD,
				new String[]{DB_CREDITCARD_IDX, DB_CREDITCARD_TITLE, DB_CREDITCARD_PAYMENTDAY, DB_CREDIRCARD_CLOSINGDAY, DB_CREDITCARD_LIMITCOST},
				DB_CREDITCARD_IDX + "=" + idx,
				null, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return cursor;
	}
	
	// Event
	public long insertEvent( String strTitle, String strBeginDate, String strEndDate)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_EVENT_TITLE, strTitle);
		contentVal.put(DB_EVENT_BEGINDATE, strBeginDate);
		contentVal.put(DB_EVENT_ENDDATE, strEndDate);
		
		return m_Db.insert(DB_TB_NAME_EVENT, null, contentVal);
	}

	public long insertEvent( String strTitle, Date beginDate, Date endDate)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_EVENT_TITLE, strTitle);

		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strBeginDate = formatter.format(beginDate);
		contentVal.put(DB_EVENT_BEGINDATE, strBeginDate);	
		String strEndDate = formatter.format(endDate);
		contentVal.put(DB_EVENT_ENDDATE, strEndDate);
		
		return m_Db.insert(DB_TB_NAME_EVENT, null, contentVal);
	}
	
	
	public long insertEvent(typeEvent event)
	{
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strBeginDate = formatter.format(event.getBeginDate());
		String strEndDate = formatter.format(event.getEndDate());
		
		return insertEvent(event.getTitle(), strBeginDate, strEndDate);
	}

	public boolean deleteEvent(long idx)
	{
		return m_Db.delete(DB_TB_NAME_EVENT, DB_EVENT_IDX + "=" + idx, null) > 0;
	}

	public boolean updateEvent(long idx, String strTitle, String strBeginDate, String strEndDate)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_EVENT_TITLE, strTitle);
		contentVal.put(DB_EVENT_BEGINDATE, strBeginDate);
		contentVal.put(DB_EVENT_ENDDATE, strEndDate);
		return m_Db.update(DB_TB_NAME_EVENT, contentVal, DB_EVENT_IDX+"="+idx, null) > 0;
	}
	
	public boolean updateEvent(long idx, String strTitle, Date beginDate, Date endDate)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_EVENT_TITLE, strTitle);
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strBeginDate = formatter.format(beginDate);
		contentVal.put(DB_EVENT_BEGINDATE, strBeginDate);	
		String strEndDate = formatter.format(endDate);
		contentVal.put(DB_EVENT_ENDDATE, strEndDate);
		
		return m_Db.update(DB_TB_NAME_EVENT, contentVal, DB_EVENT_IDX+"="+idx, null) > 0;
	}
	public boolean updateEvent(long idx, typeEvent event)
	{
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strBeginDate = formatter.format(event.getBeginDate());
		String strEndDate = formatter.format(event.getEndDate());
		
		return updateEvent(idx, event.getTitle(), strBeginDate, strEndDate);
	}
	
	public Cursor fetchAllEvent()
	{
		return m_Db.query(DB_TB_NAME_EVENT,
				new String[]{DB_EVENT_IDX, DB_EVENT_TITLE, DB_EVENT_BEGINDATE, DB_EVENT_ENDDATE},
				null, null, null, null, null);
	}
	
	public Cursor fetchEvent(long idx)
	{
		Cursor cursor = m_Db.query(true, DB_TB_NAME_EVENT,
				new String[]{DB_EVENT_IDX, DB_EVENT_TITLE, DB_EVENT_BEGINDATE, DB_EVENT_ENDDATE},
				DB_EVENT_IDX + "=" + idx,
				null, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return cursor;
	}
	
	// SMS Parsing Rule
	public long insertSMSParsingRule( long lCreditcard_Idx, String strPhoneNumber, String strRule)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_SMSPARSINGRULE_CREDITCARDIDX, lCreditcard_Idx);
		contentVal.put(DB_SMSPARSINGRULE_PHONENUMBER, strPhoneNumber);
		contentVal.put(DB_SMSPARSINGRULE_RULE, strRule);
		
		return m_Db.insert(DB_TB_NAME_SMSPARSINGRULE, null, contentVal);
	}

	public boolean deleteSMSParsingRule(long idx)
	{
		return m_Db.delete(DB_TB_NAME_SMSPARSINGRULE, DB_SMSPARSINGRULE_IDX + "=" + idx, null) > 0;
	}
	
	public boolean updateSMSParsingRule(long idx, long lCreditcard_Idx, String strPhoneNumber, String strRule)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_SMSPARSINGRULE_CREDITCARDIDX, lCreditcard_Idx);
		contentVal.put(DB_SMSPARSINGRULE_PHONENUMBER, strPhoneNumber);
		contentVal.put(DB_SMSPARSINGRULE_RULE, strRule);
		
		return m_Db.update(DB_TB_NAME_SMSPARSINGRULE, contentVal, DB_SMSPARSINGRULE_IDX + "=" + idx, null) > 0;
	}
	
	public Cursor fetchAllSMSParsingRule()
	{
		return m_Db.query(DB_TB_NAME_SMSPARSINGRULE,
				new String[]{DB_SMSPARSINGRULE_IDX, DB_SMSPARSINGRULE_CREDITCARDIDX, DB_SMSPARSINGRULE_PHONENUMBER, DB_SMSPARSINGRULE_RULE},
				null, null, null, null, null);
	}
	
	public Cursor fetchSMSParsingRule(long idx)
	{
		Cursor cursor = m_Db.query(true, DB_TB_NAME_SMSPARSINGRULE,
				new String[]{DB_SMSPARSINGRULE_IDX, DB_SMSPARSINGRULE_CREDITCARDIDX, DB_SMSPARSINGRULE_PHONENUMBER, DB_SMSPARSINGRULE_RULE},
				DB_SMSPARSINGRULE_IDX + "=" + idx,
				null, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return cursor;
	}
	
	// Transaction
	public long insertTransaction( long lCreditcard_Idx, long lEvent_Idx, long lCategory_Idx, long lSMS_Idx, long nCost, String strBillingTime, String strContent, String strPhoto, String strGPSLocation, long nInstallmentPlan)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_TRANSACTION_CREDITCARDIDX, lCreditcard_Idx);
		contentVal.put(DB_TRANSACTION_EVENTIDX, lEvent_Idx);
		contentVal.put(DB_TRANSACTION_CATEGORYIDX, lCategory_Idx);
		contentVal.put(DB_TRANSACTION_SMSIDX, lSMS_Idx);
		contentVal.put(DB_TRANSACTION_COST, nCost);
		contentVal.put(DB_TRANSACTION_BILLINGTIME, strBillingTime);
		contentVal.put(DB_TRANSACTION_CONTENT, strContent);
		contentVal.put(DB_TRANSACTION_PHOTO, strPhoto);
		contentVal.put(DB_TRANSACTION_GPSLOCATION, strGPSLocation);
		contentVal.put(DB_TRANSACTION_INSTALLMENTPLAN, nInstallmentPlan);
		
		return m_Db.insert(DB_TB_NAME_TRANSACTION, null, contentVal);
	}	

	public long insertTransaction( long lCreditcard_Idx, long lEvent_Idx, long lCategory_Idx, long lSMS_Idx, long nCost, Date billingTime, String strContent, String strPhoto, String strGPSLocation, long nInstallmentPlan)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_TRANSACTION_CREDITCARDIDX, lCreditcard_Idx);
		contentVal.put(DB_TRANSACTION_EVENTIDX, lEvent_Idx);
		contentVal.put(DB_TRANSACTION_CATEGORYIDX, lCategory_Idx);
		contentVal.put(DB_TRANSACTION_SMSIDX, lSMS_Idx);
		contentVal.put(DB_TRANSACTION_COST, nCost);
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strBillingTime = formatter.format(billingTime);
		contentVal.put(DB_TRANSACTION_BILLINGTIME, strBillingTime);
		contentVal.put(DB_TRANSACTION_CONTENT, strContent);
		contentVal.put(DB_TRANSACTION_PHOTO, strPhoto);
		contentVal.put(DB_TRANSACTION_GPSLOCATION, strGPSLocation);
		contentVal.put(DB_TRANSACTION_INSTALLMENTPLAN, nInstallmentPlan);
		
		return m_Db.insert(DB_TB_NAME_TRANSACTION, null, contentVal);
	}	
	
	public long insertTransaction(typeTransaction trans)
	{
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strBillingTime = formatter.format(trans.getBillingTime());
	
		return this.insertTransaction(trans.getCreditcard_Idx(),
										trans.getEvent_Idx(),
										trans.getCategory_Idx(),
										trans.getSMS_Idx(),
										trans.getCost(),
										strBillingTime,
										trans.getContent(),
										trans.getPhoto(),
										trans.getGPSLocation(),
										trans.getInstallmentPlan()
									);
	}
	
	public boolean deleteTransaction(long idx)
	{
		return m_Db.delete(DB_TB_NAME_TRANSACTION, DB_TRANSACTION_IDX + "=" + idx, null) > 0;
	}

	public boolean updateTransaction(long idx, long lCreditcard_Idx, long lEvent_Idx, long lCategory_Idx, long lSMS_Idx, long nCost, String strBillingTime, String strContent, String strPhoto, String strGPSLocation, long nInstallmentPlan)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_TRANSACTION_CREDITCARDIDX, lCreditcard_Idx);
		contentVal.put(DB_TRANSACTION_EVENTIDX, lEvent_Idx);
		contentVal.put(DB_TRANSACTION_CATEGORYIDX, lCategory_Idx);
		contentVal.put(DB_TRANSACTION_SMSIDX, lSMS_Idx);
		contentVal.put(DB_TRANSACTION_COST, nCost);
		contentVal.put(DB_TRANSACTION_BILLINGTIME, strBillingTime);
		contentVal.put(DB_TRANSACTION_CONTENT, strContent);
		contentVal.put(DB_TRANSACTION_PHOTO, strPhoto);
		contentVal.put(DB_TRANSACTION_GPSLOCATION, strGPSLocation);
		contentVal.put(DB_TRANSACTION_INSTALLMENTPLAN, nInstallmentPlan);
		
		return m_Db.update(DB_TB_NAME_TRANSACTION, contentVal, DB_TRANSACTION_IDX + "=" + idx, null) > 0;
	}	
	
	public boolean updateTransaction(long idx, long lCreditcard_Idx, long lEvent_Idx, long lCategory_Idx, long lSMS_Idx, long nCost, Date billingTime, String strContent, String strPhoto, String strGPSLocation, long nInstallmentPlan)
	{
		ContentValues contentVal = new ContentValues();
		contentVal.put(DB_TRANSACTION_CREDITCARDIDX, lCreditcard_Idx);
		contentVal.put(DB_TRANSACTION_EVENTIDX, lEvent_Idx);
		contentVal.put(DB_TRANSACTION_CATEGORYIDX, lCategory_Idx);
		contentVal.put(DB_TRANSACTION_SMSIDX, lSMS_Idx);
		contentVal.put(DB_TRANSACTION_COST, nCost);
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strBillingTime = formatter.format(billingTime);
		contentVal.put(DB_TRANSACTION_BILLINGTIME, strBillingTime);
		contentVal.put(DB_TRANSACTION_CONTENT, strContent);
		contentVal.put(DB_TRANSACTION_PHOTO, strPhoto);
		contentVal.put(DB_TRANSACTION_GPSLOCATION, strGPSLocation);
		contentVal.put(DB_TRANSACTION_INSTALLMENTPLAN, nInstallmentPlan);
		
		return m_Db.update(DB_TB_NAME_TRANSACTION, contentVal, DB_TRANSACTION_IDX + "=" + idx, null) > 0;
	}

	public boolean updateTransaction(long idx, typeTransaction trans)
	{
		Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strBillingDate = formatter.format(trans.getBillingTime());

		return updateTransaction(idx,
										trans.getCreditcard_Idx(),
										trans.getEvent_Idx(),
										trans.getCategory_Idx(),
										trans.getSMS_Idx(),
										trans.getCost(),
										strBillingDate,
										trans.getContent(),
										trans.getPhoto(),
										trans.getGPSLocation(),
										trans.getInstallmentPlan()
									);
	}
	
	
	public Cursor fetchAllTransaction()
	{
		return m_Db.query(DB_TB_NAME_TRANSACTION,
				new String[]{DB_TRANSACTION_IDX, DB_TRANSACTION_CREDITCARDIDX, DB_TRANSACTION_EVENTIDX, DB_TRANSACTION_CATEGORYIDX, DB_TRANSACTION_SMSIDX, DB_TRANSACTION_COST, DB_TRANSACTION_BILLINGTIME, DB_TRANSACTION_CONTENT, DB_TRANSACTION_PHOTO, DB_TRANSACTION_GPSLOCATION, DB_TRANSACTION_INSTALLMENTPLAN},
				null, null, null, null, null);
	}

	public Cursor fetchTransaction(long idx)
	{
		Cursor cursor = m_Db.query(true, DB_TB_NAME_TRANSACTION,
				new String[]{DB_TRANSACTION_IDX, DB_TRANSACTION_CREDITCARDIDX, DB_TRANSACTION_EVENTIDX, DB_TRANSACTION_CATEGORYIDX, DB_TRANSACTION_SMSIDX, DB_TRANSACTION_COST, DB_TRANSACTION_BILLINGTIME, DB_TRANSACTION_CONTENT, DB_TRANSACTION_PHOTO, DB_TRANSACTION_GPSLOCATION, DB_TRANSACTION_INSTALLMENTPLAN},
				DB_TB_NAME_TRANSACTION + "=" + idx,
				null, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return cursor;
	}
	
	
	
	public boolean insertDefaultData()
	{
		this.insertCreditcard("Cash", 0, 0, 0);
		
		this.insertCategory("Food", "icon0", false, 0);
		this.insertCategory("Love", "icon1", false, 0);
		this.insertCategory("Alcohol", "icon2", false, 0);
		this.insertCategory("Clothes", "icon3", false, 0);
		/*
		typeCategory category = new typeCategory();
		category.setFavoriteOrder(1);
		category.setIconId("icon1");
		category.setIsIncome(false);
		category.setTitle("Love");
		this.insertCategory(category);
		
		typeTransaction trans = new typeTransaction();
		trans.setBillingTime("2010-03-08 18:35:00");
		trans.setCategory_Idx(0);
		trans.setContent("this is sparta~~~");
		trans.setCost(10000);
		trans.setCreditcard_Idx(0);
		trans.setEvent_Idx(-1);
		trans.setGPSLocation("137.111, 37.000");
		trans.setInstallmentPlan(0);
		trans.setPhoto("photo 00");
		trans.setSMS_Idx(0);
		this.insertTransaction(trans);
		
		this.insertTransaction(1, 1, 1, 1, 20000, new Date(110,3,8,18,39,23), "Test 02", "Photo 02", "123, 0000", 1);
		this.insertTransaction(1, 1, 1, 1, 20010, "2010-03-08 18:39:23", "Test 03", "Photo 03", "124, 0002", 2);
		
		typeCreditcard card = new typeCreditcard();
		card.setLimitCost(1000000);
		card.setClosingDay(30);
		card.setPaymentDay(10);
		card.setTitle("테스트");
		this.insertCreditcard(card);
		
		typeEvent event = new typeEvent();
		event.setBeginDate(new Date(110, 3,8,15,41,29));
		event.setEndDate("2010-03-08 18:41:21");
		event.setTitle("my event");
		this.insertEvent(event);
		*/
		
		return true;
	}
}
