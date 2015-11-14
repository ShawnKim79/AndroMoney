package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Date;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver  extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		//String str = "";
		if(bundle != null)
		{
			Object [] pdus = (Object[])bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			boolean bDBInserted = false;
			for(int i=0; i<msgs.length; i++)
			{
				msgs[i] = SmsMessage.createFromPdu( (byte[])pdus[i] );

				// 결제 SMS인가 확인
				if(IsBillingSMS(msgs[i])==true)
				{
					// 맞으면 DB 입력
					bDBInserted = insertSMS2DB(context, msgs[i]);
				}
			}
			// @@ DB 입력할 때만 토스트 띄우도록 수정하면 되겠다.
			if(bDBInserted)
			{
				Toast toast = Toast.makeText(context, "AndroMoney :\nNew Billing SMS was received.", Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}

	public boolean IsBillingSMS(SmsMessage sms)
	{
		String strPhone = sms.getOriginatingAddress();
		String strCont = sms.getMessageBody().toString();
		// @@ 간이
		if(strPhone=="") return false;
		else if(strCont=="") return false;
		
		return true;
	}
	
	public boolean insertSMS2DB(Context context, SmsMessage sms)
	{
		String strPhone = sms.getOriginatingAddress();
		/*
		String strCont = "";
		try {
			strCont = new String( sms.getMessageBody().getBytes(), "UNICODE");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String strCont = sms.getMessageBody();
		String strGps = Utility.getLocationString(context);
		//if(strGps==null) strGps = "";
		
		Date rcvTime = new Date(); // 문자에 있는 날짜로. @@
		
		DBAdapter dbAdt = new DBAdapter(context);
		dbAdt.open();
		dbAdt.insertSMS(rcvTime, strPhone, strCont, false, strGps);
		dbAdt.close();
		return true;
	}	

}
