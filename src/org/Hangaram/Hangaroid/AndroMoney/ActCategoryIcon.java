package org.Hangaram.Hangaroid.AndroMoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ActCategoryIcon extends Activity {
	ImageButton btnIcon01;
	ImageButton btnIcon02;
	ImageButton btnIcon03;
	ImageButton btnIcon04;
	ImageButton btnIcon05;
	ImageButton btnIcon06;
	ImageButton btnIcon07;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actcategoryicon);
		
		btnIcon01 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon01);
		btnIcon01.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon01.getTag().toString());
			}
		});
		btnIcon02 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon02);
		btnIcon02.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon02.getTag().toString());
			}
		});
		btnIcon03 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon03);
		btnIcon03.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon03.getTag().toString());
			}
		});
		btnIcon04 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon04);
		btnIcon04.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon04.getTag().toString());
			}
		});
		btnIcon05 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon05);
		btnIcon05.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon05.getTag().toString());
			}
		});
		btnIcon06 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon06);
		btnIcon06.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon06.getTag().toString());
			}
		});
		btnIcon07 = (ImageButton)findViewById(R.id.actcategoryicon_btnIcon07);
		btnIcon07.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				returnIconId(btnIcon07.getTag().toString());
			}
		});
	}
	
	public void returnIconId(String strIconId)
	{
		Intent intent = getIntent(); // 이 액티비티를 시작하게 한 인텐트를 호출
		
		intent.putExtra("data_IconId", strIconId);
		setResult(RESULT_OK,intent); // 추가 정보를 넣은 후 다시 인텐트를 반환합니다.
		finish(); // 액티비티 종료
	}
	

}
