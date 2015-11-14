package org.Hangaram.Hangaroid.AndroMoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SMS extends Activity {
	
	
	private Spinner categotySpinner;
	private Button btnConfirm;
	private String[] items;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        
        this.categotySpinner = (Spinner)findViewById(R.id.SpinnerCategory);
        this.btnConfirm = (Button)findViewById(R.id.btnConfirm);
        
        this.categotySpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				// 선택한 카테고리로 SMS 메시지 분류 작업 필요...
				btnConfirm.setText(items[position]);
				
				
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.btnConfirm.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					
			}
        	
        });
        
        SetCategory();
        
    }
	
	private void SetCategory()
	{
		items = new String[]{"의", "식", "주", "지름"};
		ArrayAdapter<String> categoryList = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, items);
		categoryList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.categotySpinner.setAdapter(categoryList);
	}
	

}
