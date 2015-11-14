package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class ActTransaction extends Activity 
							implements View.OnClickListener, 
									   View.OnTouchListener, 
									   GridView.OnItemClickListener
{	
	
	static final int PICK_DATE_REQUEST = 0;
	
	DBAdapter dba = null;
	ViewFlipper flipper = null;
	Calculator calc = new Calculator();
	String strEquation = "";

	// 터치 이벤트 발생 지점의 x좌표 저장
    float x_down;
    float x_up;
    
    private Integer[] mThumbIds = null;
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		
		try
		{
			super.onCreate( savedInstanceState );	
			this.setContentView(R.layout.acttransaction);
			
			Utility.InsertWidget(this, R.id.TitleHolder, R.layout.widgettitle);
			Utility.InsertWidget(this, R.id.CalculatorHolder, R.layout.widgetcalculator);
			
			dba = new DBAdapter( this );
			
			// 금액 입력 위젯 설정
			Date date = new Date();
			String strDate = 
					String.format(
					"Date : %d-%02d-%02d %02d:%02d:%02d", 
					1900+date.getYear(), 
					date.getMonth(), 
					date.getDay(),
					date.getHours(), 
					date.getMinutes(), 
					date.getSeconds());
			
			((TextView)findViewById(R.id.acttransaction_textDate)).setText(strDate);
			((ImageButton)findViewById(R.id.acttransaction_btnDate)).setOnClickListener(this);
			((ImageButton)findViewById(R.id.acttransaction_btnMemo)).setOnClickListener(this);
			
			// 타이틀 위젯 설정
			((TextView)findViewById(R.id.Title)).setText("New Transaction");
			((Button)findViewById(R.id.btnLeft)).setText("Cancel");
			((Button)findViewById(R.id.btnRight)).setText("Save");
			((Button)findViewById(R.id.btnLeft)).setOnClickListener(this);
			((Button)findViewById(R.id.btnRight)).setOnClickListener(this);

			// 계산기 위젯 설정
			((Button)findViewById(R.id.opp)).setOnClickListener(this);
			((Button)findViewById(R.id.clp)).setOnClickListener(this);
			((Button)findViewById(R.id.clear)).setOnClickListener(this);
			((Button)findViewById(R.id.n0)).setOnClickListener(this);
			((Button)findViewById(R.id.n1)).setOnClickListener(this);
			((Button)findViewById(R.id.n2)).setOnClickListener(this);
			((Button)findViewById(R.id.n3)).setOnClickListener(this);
			((Button)findViewById(R.id.n4)).setOnClickListener(this);
			((Button)findViewById(R.id.n5)).setOnClickListener(this);
			((Button)findViewById(R.id.n6)).setOnClickListener(this);
			((Button)findViewById(R.id.n7)).setOnClickListener(this);
			((Button)findViewById(R.id.n8)).setOnClickListener(this);
			((Button)findViewById(R.id.n9)).setOnClickListener(this);
			((Button)findViewById(R.id.plus)).setOnClickListener(this);
			((Button)findViewById(R.id.minus)).setOnClickListener(this);
			((Button)findViewById(R.id.mul)).setOnClickListener(this);
			((Button)findViewById(R.id.div)).setOnClickListener(this);
			((Button)findViewById(R.id.equal)).setOnClickListener(this);
			((Button)findViewById(R.id.point)).setOnClickListener(this);
			((ImageButton)findViewById(R.id.back)).setOnClickListener(this);
			((ImageButton)findViewById(R.id.acttransaction_btnDate)).setOnClickListener(this);
			
			// 카테고리 선택 위젯 설정
			fillCategoryIcons();
			initCategorySelector();
			
		}
		
		catch( Exception e )
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public void onClick(View view)
	{
		try
		{
			EditText edit = (EditText)findViewById(R.id.editCost);
			int id = view.getId();

			////////////////////////////////////////////////
			// 계산기 버튼 이벤트 핸들러
			switch( id )
			{
				case R.id.opp 	: strEquation += "("; break;
				case R.id.clp 	: strEquation += ")"; break;
				case R.id.n0 	: strEquation += "0"; break;
				case R.id.n1 	: strEquation += "1"; break;
				case R.id.n2 	: strEquation += "2"; break;
				case R.id.n3 	: strEquation += "3"; break;
				case R.id.n4 	: strEquation += "4"; break;
				case R.id.n5 	: strEquation += "5"; break;
				case R.id.n6 	: strEquation += "6"; break;
				case R.id.n7 	: strEquation += "7"; break;
				case R.id.n8 	: strEquation += "8"; break;
				case R.id.n9 	: strEquation += "9"; break;
				case R.id.point : strEquation += "."; break;
				case R.id.plus	: strEquation += "+"; break;
				case R.id.minus	: strEquation += "-"; break;
				case R.id.mul	: strEquation += "×"; break;
				case R.id.div	: strEquation += "÷"; break;
			}
			
			if( id == R.id.back ) {
				if(strEquation.length() > 1)
					strEquation = strEquation.substring(0, strEquation.length() - 1 );
				else
					strEquation = "";
			}
			
			else if( id == R.id.clear ) {
				strEquation = "";
			}
			
			if( id == R.id.equal ) {
				calc.SetExpression(strEquation);
				String strRes = calc.GetResult(3);
				strEquation = strRes;
			}
			
			// 콤마 삽입
			if( strEquation.length() > 0 )
			{
/*				
				StringBuilder sb = new StringBuilder();
				
				int count = 0;
				int comma = 3;
				for( int i = strEquation.length() - 1 ; i >= 0 ; --i )
				{
					char c = strEquation.charAt(i);
					sb.append( c );
					
					if( c != '+' &&
						c != '-' &&
						c != '*' &&
						c != '×' &&
						c != '÷' &&
						c != '√' &&
						c != '^' &&
						c != '(' &&
						c != ')' )
						count++;
					
					if( count == comma && i != 0 )
					{
						sb.append( ',' );
						count = 0;
					}
				}
				
				edit.setText(sb.reverse());
*/
				edit.setText(strEquation);
			}
			else
				edit.setText("0");
	
			////////////////////////////////////////////////
			// 타이틀 버튼 이벤트 핸들러
			// Cancel
			if( id == R.id.btnLeft )
			{
				setResult( RESULT_CANCELED, null );
				finish();
			}

			// Save
			else if( id == R.id.btnRight )
			{
				setResult( RESULT_OK, null );
				finish();
			}
			
			if( id == R.id.acttransaction_btnDate )
			{
				startActivityForResult( new Intent( ActTransaction.this, DlgCalendar.class ), PICK_DATE_REQUEST );
//				DlgCalendar dlg = new DlgCalendar( this );
//				dlg.setTitle( "Pick date" );
//				dlg.show();
			}
/*
			Context context = getApplicationContext();
			CharSequence text = strEquation;
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
*/	
		}
		
		catch( Exception e )
		{
			System.out.println(e.getMessage());
		}
	} // End of onCreate()
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if ( requestCode == PICK_DATE_REQUEST ) {
            // 새로운 트랜젝션 처리
				Calendar cal = Calendar.getInstance();
				int year = data.getIntExtra( "year", cal.get( Calendar.YEAR ) );
				int month = data.getIntExtra( "month", cal.get( Calendar.MONTH ) ) + 1;
				int day = data.getIntExtra( "day", cal.get( Calendar.DAY_OF_MONTH ) );
				
				String strDate = 
					String.format(
					"Date : %d-%02d-%02d %02d:%02d:%02d", 
					year, 
					month, 
					day,
					cal.get( Calendar.HOUR_OF_DAY ), 
					cal.get( Calendar.MINUTE ), 
					cal.get( Calendar.SECOND ) );
			
				((TextView)findViewById(R.id.acttransaction_textDate)).setText(strDate);
            }
        }
    }
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return
//		if(v != flipper) return false;
		
		int min_moving = 20;
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			x_down = event.getY(); // 터치 시작지점 x좌표 저장			
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			x_up = event.getY(); 	// 터치 끝난지점 x좌표 저장
			
			if( x_up < x_down && 
				( x_down - x_up ) > min_moving ) {
				// 왼쪽 방향 에니메이션 지정
				flipper.setInAnimation(AnimationUtils.loadAnimation(this,
		        		R.anim.push_top_in));
		        flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
		        		R.anim.push_top_out));
		        		
		        // 다음 view 보여줌
				flipper.showNext();
			}
			else if ( x_up > x_down && 
					 ( x_up - x_down ) > min_moving ){
				// 오른쪽 방향 에니메이션 지정
				flipper.setInAnimation(AnimationUtils.loadAnimation(this,
		        		R.anim.push_bottom_in));
		        flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
		        		R.anim.push_bottom_out));
		        // 전 view 보여줌
				flipper.showPrevious();			
			}
		}
		
		return false;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		GridView grid = (GridView)parent;
		ImageView imgSelected = (ImageView)view;
		ImageView imgView = (ImageView)findViewById( R.id.ImageView01 );
		imgView.setPadding( 5, 5, 5, 5 );
		imgView.setImageDrawable(imgSelected.getDrawable());
	}
		
	boolean initCategorySelector()
	{
		Utility.InsertWidget( this, R.id.CategoryHolder, R.layout.widgetcategoryselector);
		flipper = (ViewFlipper)findViewById(R.id.widgetcatselector_viewFlipper);
		flipper.setOnTouchListener( this );
		
		flipper.addView( createGridView( this, mThumbIds, 32, 32 ) );
		flipper.addView( createGridView( this, mThumbIds, 32, 32 ) );
		
        return true;
	}
	
	boolean fillCategoryIcons()
	{
		dba.open();
		Cursor categoryCursor = dba.fetchAllCategory();
		
		if( categoryCursor.getCount() > 0 )
		{
			int index = 0;
			mThumbIds = new Integer[ categoryCursor.getCount() ];
			StringBuilder sb = new StringBuilder();
			String strIconId = "";
			
			if(categoryCursor.moveToFirst())
			{
				do {
					sb.setLength( 0 );
					sb.append("org.Hangaram.Hangaroid.AndroMoney:");
					sb.append( categoryCursor.getString(2) );
					strIconId = sb.toString();
					
					int resID = getResources().getIdentifier(strIconId, null, null );
					mThumbIds[index] = resID; 
					++index;
						
				} while(categoryCursor.moveToNext() == true);
			}
		}
		dba.close();
		
		return true;
	}
	
	GridView createGridView( Context context, Integer[] imageIds, int thumbWidth, int thumbHeight )
	{
		GridView grid = new GridView( this );
		
		GridView.LayoutParams params = new GridView.LayoutParams(
				LayoutParams.FILL_PARENT,
				70 );
		
		grid.setLayoutParams( params );
		grid.setNumColumns( GridView.AUTO_FIT );
		grid.setHorizontalSpacing( 2 );
		grid.setVerticalSpacing( 2 );
		grid.setColumnWidth( 34 );
		grid.setStretchMode( GridView.STRETCH_COLUMN_WIDTH );
		grid.setGravity( Gravity.CENTER );
		grid.setAdapter( new ImageAdapter( this, imageIds, thumbWidth, thumbHeight ) );
		grid.setOnTouchListener(this);
		grid.setBackgroundColor( 0xFFF0F0F0 );
		grid.setOnItemClickListener( this );
		
		return grid;
	}
}