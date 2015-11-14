package org.Hangaram.Hangaroid.AndroMoney;

import java.util.ArrayList;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class widgetCategorywrapper {
	View base;
	TextView tbTitle;
	ImageButton btnUp;
	ImageButton btnDown;
	Button btnDelete;
	
	
	widgetCategorywrapper(View base)
	{
		this.base = base;
		
	}
	
	TextView getTitle()
	{
		if(tbTitle == null){
			tbTitle = (TextView)base.findViewById(R.id.widgetcategory_categoryTitle);
			
		}
		return (tbTitle);
	}
	
	ImageButton getButtonUp()
	{
		if(btnUp == null)
		{
			btnUp = (ImageButton)base.findViewById(R.id.widgetcategory_btnUp);
		}
		return (btnUp);
	}
	
	ImageButton getButtonDown()
	{
		if(btnDown == null)
		{
			btnDown = (ImageButton)base.findViewById(R.id.widgetcategory_btnDown);
		}
		return (btnDown);
	}
	
	Button getButtonDelete()
	{
		if(btnDelete == null)
		{
			btnDelete=(Button)base.findViewById(R.id.widgetcategory_btnDelete);
		}
		return (btnDelete);
	}

}
