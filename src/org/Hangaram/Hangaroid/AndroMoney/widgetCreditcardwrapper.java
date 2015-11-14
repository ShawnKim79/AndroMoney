package org.Hangaram.Hangaroid.AndroMoney;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class widgetCreditcardwrapper {
	View base;
	TextView tbCardTitle;
	TextView tbPaymentDay;
	TextView tbCloseDay;
	Button btnDelete;
	
	
	widgetCreditcardwrapper(View base)
	{
		this.base = base;
		
	}
	
	TextView getCardTitle()
	{
		if(tbCardTitle == null){
			tbCardTitle = (TextView)base.findViewById(R.id.widgetcreditcard_name);
			
		}
		return (tbCardTitle);
	}
	
	TextView getPaymentDay()
	{
		if(tbPaymentDay == null)
		{
			tbPaymentDay = (TextView)base.findViewById(R.id.widgetcreditcard_paymentday);
		}
		return (tbPaymentDay);
	}
	
	TextView getCloseDay()
	{
		if(tbCloseDay == null)
		{
			tbCloseDay = (TextView)base.findViewById(R.id.widgetcreditcard_closeday);
		}
		return (tbCloseDay);
	}
	
	Button getButtonDelete()
	{
		if(btnDelete == null)
		{
			btnDelete=(Button)base.findViewById(R.id.widgetcreditcard_btnDelete);
		}
		return (btnDelete);
	}

}
