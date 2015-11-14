package org.Hangaram.Hangaroid.AndroMoney;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.*;
import android.content.Context;
import android.content.Intent;

public class ActHome extends Activity
{	
	static final int NEW_TRANSACTION_REQUEST = 0;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );	
		this.setContentView(R.layout.acthome);
		
		Button btn=(Button)findViewById(R.id.btnCallSMS);
        btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(), SMS.class));
			}
		});
	        
        Button btnCallTransaction =(Button)findViewById(R.id.btnCallTransaction);
        btnCallTransaction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(getApplicationContext(), ActTransaction.class), NEW_TRANSACTION_REQUEST);
			}
		});
        
        Button btnCallSetting = (Button)findViewById(R.id.btnSetting);
        btnCallSetting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult( new Intent(getApplicationContext(), setting.class), 0 );
			}
		});

        // by shkim for test
        Button btnTest = (Button)findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity( new Intent(getApplicationContext(), ActTest.class));
			}
		});
                

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == NEW_TRANSACTION_REQUEST ) {
            if (resultCode == RESULT_OK) {
                
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("New Transaction");
            b.setMessage("New Tranaction created.");
            b.show();
            // 货肺款 飘罚璃记 贸府
            }
        }
    }

}
