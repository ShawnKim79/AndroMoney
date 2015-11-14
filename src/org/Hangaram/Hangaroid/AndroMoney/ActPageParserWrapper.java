package org.Hangaram.Hangaroid.AndroMoney;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ActPageParserWrapper {
	View base;
	EditText phoneNumber;
	Spinner CardName;
	Spinner DateLine;
	Spinner DateRegular;
	Spinner PurposeLine;
	Spinner PurposeRegular;
	Spinner CashLine;
	Spinner CashRegular;
	
	ActPageParserWrapper(View view)
	{
		
		this.base = view;
	}
	
	View getActPageParserWrapper()
	{
		return(base);
	}
	
	EditText getPhoneNumber()
	{
		if(phoneNumber == null)
		{
			this.phoneNumber = (EditText)base.findViewById(R.id.actpageparser_etPhoneNumber);
		}
		return(phoneNumber);
	}
	Spinner getCardName()
	{
		if(CardName == null)
		{
			this.CardName = (Spinner)base.findViewById(R.id.actpageparser_SpinnerCard);
		}
		return(CardName);
	}
	
	
	

}
