package org.Hangaram.Hangaroid.AndroMoney;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActTest extends Activity{

	Button btnRefresh;
	EditText txtMessage;
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acttest);
	    
	    btnRefresh = (Button) findViewById(R.id.btnRefresh);
	    txtMessage = (EditText) findViewById(R.id.editData);
	            
	    btnRefresh.setOnClickListener( new View.OnClickListener() {
	    	public void onClick(View v)
	    	{
	    		RefreshData();
	    	}
	    });
	}
	
	private void RefreshData()
	{
		String strData = "";
		DBAdapter db = new DBAdapter(this);
		db.open();
		Cursor c = db.fetchAllCategory();
		//Cursor c = db.fetchAllCreditcard();
		//Cursor c = db.fetchAllEvent();
		//Cursor c = db.fetchAllSMS();
		//Cursor c = db.fetchAllTransaction();
		if(c.moveToFirst())
		{
			do
			{
				/*
				typeTransaction trans = typeTransaction.newInstanceByCursor(c);
				strData += trans.getContent();
				strData += " | ";
				strData += trans.getGPSLocation();
				strData += " | ";
				strData += trans.getPhoto();
				strData += " | ";
				strData += trans.getBillingTime().toGMTString();
				strData += " | ";
				strData += trans.getCategory_Idx();
				strData += " | ";
				strData += trans.getCost();
				strData += " | ";
				strData += trans.getCreditcard_Idx();
				strData += " | ";
				strData += trans.getEvent_Idx();
				strData += " | ";
				strData += trans.getInstallmentPlan();
				strData += " | ";
				strData += trans.getSMS_Idx();
				strData += "\n";
				*/
				/*
				typeSMS sms = typeSMS.newInstanceByCursor(c);
				strData += sms.getContent();
				strData += " | ";
				strData += sms.getGPSLocation();
				strData += " | ";
				strData += sms.getPhoneNumber();
				strData += " | ";
				strData += sms.getReceiveTime();
				strData += "\n";
				*/
				
				/*
				strData += c.getString(0);
				strData += " | ";
				strData += c.getString(1);
				strData += " | ";
				strData += c.getString(2);
				strData += " | ";
				strData += c.getString(3);
				strData += "\n";
				*/
				/*
				typeEvent event = typeEvent.newInstanceByCursor(c);
				strData += event.getTitle();
				strData += " | ";
				strData += event.getBeginDate().toGMTString();
				strData += " | ";
				strData += event.getEndDate().toGMTString();
				strData += "\n";
				
				typeCreditcard card = typeCreditcard.newInstanceByCursor(c);
				strData += card.getTitle();
				strData += " | ";
				strData += card.getClosingDay();
				strData += " | ";
				strData += card.getPaymentDay();
				strData += " | ";
				strData += card.getLimitCost();
				strData += "\n";
				
				typeCategory cate = typeCategory.newInstanceByCursor(c);
				strData += cate.getTitle();
				strData += " | ";
				strData += cate.getIconId();
				strData += " | ";
				strData += cate.IsIncome()==true ? "TRUE" : "FALSE";
				strData += " | ";
				strData += cate.getFavoriteOrder();
				strData += "\n";
				*/
				typeCategory cate = new typeCategory(c);
				strData += cate.getTitle();
				strData += " | ";
				strData += cate.getIconId();
				strData += " | ";
				strData += cate.IsIncome()==true ? "TRUE" : "FALSE";
				strData += " | ";
				strData += cate.getFavoriteOrder();
				strData += "\n";
				
				
			} while(c.moveToNext());
		}
		db.close();	
		txtMessage.setText(strData);
	}
}