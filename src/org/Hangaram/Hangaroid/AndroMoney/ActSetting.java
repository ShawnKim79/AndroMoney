package org.Hangaram.Hangaroid.AndroMoney;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ActSetting extends Activity
{	
	ViewFlipper flipper;
	EditText categoryTitle;
	CheckBox chkCategoryType;
	ListView lvCategoryList;
	ListView lvCreditCardList;
	
	View categoryPage;
	View cardPage;
	View parserPage;
	ActPageParserWrapper pageParser;
	
	String CategoryIconId;
	
	DBAdapter dba = new DBAdapter(this);
	
	Calendar paymentday = Calendar.getInstance();
	Calendar closeday = Calendar.getInstance();
	
	widgetCategorywrapper selectioncategory;
	
	typeCreditcard[] cardArray;
	String strParsingRule;
	typeCreditcard selectionRuleTarget;
	
	DatePickerDialog.OnDateSetListener payment_d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			paymentday.set(Calendar.YEAR, year);
			paymentday.set(Calendar.MONTH, monthOfYear);
			paymentday.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
		}
	};
	DatePickerDialog.OnDateSetListener close_d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			closeday.set(Calendar.YEAR, year);
			closeday.set(Calendar.MONTH, monthOfYear);
			closeday.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
		}
	};
	
	
	
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actsetting);
		
		//categoryList = new ArrayList<typeCategory>();
		
		Utility.InsertWidget(this, R.id.actsetting_LinearLayoutTitle, R.layout.widgettitle);
		
		lvCategoryList = (ListView)findViewById(R.id.actsetting_categoryList);
		lvCreditCardList = (ListView)findViewById(R.id.actsetting_CreditCardList);
		
		categoryPage = (View)findViewById(R.layout.actpagecategory);
		cardPage = (View)findViewById(R.layout.actpagecreditcard);
		//parserPage = (View)findViewById(R.layout.actpageparser);
		//pageParser = new ActPageParserWrapper((View)findViewById(R.layout.actpageparser));
		
		Button priv = (Button)findViewById(R.id.btnLeft);
		Button next = (Button)findViewById(R.id.btnRight);
		flipper = (ViewFlipper)findViewById(R.id.actsetting_details);
		
		
		flipper.addView( Utility.InflateWidget(this, R.layout.actpagecategory ) );
		flipper.addView( Utility.InflateWidget(this, R.layout.actpagecreditcard ) );
		flipper.addView( Utility.InflateWidget(this, R.layout.actpageparser) );
		
		categoryTitle = (EditText)findViewById(R.id.actsetting_editCategoryName);
		
		((TextView)findViewById(R.id.Title)).setText("Setting");
		((Button)findViewById(R.id.btnLeft)).setText("Priv");
		((Button)findViewById(R.id.btnRight)).setText("Next");
		
		// 초기 생성시 데이터 로드.
		this.fetchCategory();
		this.fetchCreditcard();
		this.InitParserPage();
		
		priv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flipper.showPrevious();
			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flipper.showNext();
			}
		});
		
		// 카테고리 정보 입력
		Button btnInsertCategory = (Button)findViewById(R.id.actsetting_btnInputCategory);
		btnInsertCategory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					dba.open();
					dba.insertCategory(categoryTitle.getText().toString(), CategoryIconId, chkCategoryType.isChecked(), 0);
					dba.close();
					categoryTitle.setText("");
				}catch(Exception ex)
				{
					ex.toString();
				}
				fetchCategory();
				
			}
		});
		
		// 카드정보 입력
		Button btnInsertCreditCard = (Button)findViewById(R.id.actsetting_btnInputCreditCard);
		btnInsertCreditCard.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub\
				try{
					EditText cardName = (EditText)findViewById(R.id.actsetting_editCardName);
					EditText cardLimt = (EditText)findViewById(R.id.actsetting_editCardLimit);
					EditText cardPayment = (EditText)findViewById(R.id.actsetting_editPaymentDay);
					EditText cardClose = (EditText)findViewById(R.id.actsetting_editCloseDay);
					dba.open();
					dba.insertCreditcard(cardName.getText().toString(), 
							Integer.parseInt(cardPayment.getText().toString()), 
							Integer.parseInt(cardClose.getText().toString()), 
							Long.parseLong(cardLimt.getText().toString()));
					dba.close();
					fetchCreditcard();
					cardName.setText("");
					cardLimt.setText("Card Limit");
					cardPayment.setText("Payment Day");
					cardClose.setText("Close Day");
					
				}catch(Exception ex)
				{
					ex.toString();
				}
				fetchCreditcard();
			}
		});
		
		// Icon 파일 선택 UI팝법
		Button btnIconSelect = (Button)findViewById(R.id.actsetting_btnSettingIconId);
		btnIconSelect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ActSetting.this, ActCategoryIcon.class);
				startActivityForResult(i, 1);
			}
		});
		
		chkCategoryType = (CheckBox)findViewById(R.id.actsetting_chkContentTypeToggle);
		chkCategoryType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(!isChecked)
				{
					chkCategoryType.setText("Income");
				}else
				{
					chkCategoryType.setText("Outcome");
				}
				
			}
		});
		

		// 카드 금액한도
		EditText cardLimit = (EditText)findViewById(R.id.actsetting_editCardLimit);
		cardLimit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					((EditText)v).setText("");
				}
			}
		});
		
		// 카드 지불일
		EditText cardPayment = (EditText)findViewById(R.id.actsetting_editPaymentDay);
		cardPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					((EditText)v).setText("");
				}
			}
		});
		
		// 카드 결산일
		EditText cardClose = (EditText)findViewById(R.id.actsetting_editCloseDay);
		cardClose.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					((EditText)v).setText("");
				}
			}
		});
		
		// Parser Page Part
		Button btnSaveParserRule = (Button)findViewById(R.id.actpageparser_btnParserRuleSave);
		btnSaveParserRule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				if(strParsingRule.length() == 0)
				{
					return;
				}
				if(selectionRuleTarget == null)
				{
					return;
				}
				EditText etPhoneNumber = (EditText)findViewById(R.id.actpageparser_etPhoneNumber);
				
				typeParsingRule newRule = new typeParsingRule(
						selectionRuleTarget.getldx(), 
						etPhoneNumber.getText().toString(),
						strParsingRule);
				dba.open();
				dba.close();
				
			}
		});
		Spinner spinner_ParserCard = (Spinner)findViewById(R.id.actpageparser_SpinnerCard);
		spinner_ParserCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				typeCreditcard selection = (typeCreditcard)cardArray[arg2];
				selectionRuleTarget = selection;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		RadioGroup parserGroup = (RadioGroup)findViewById(R.id.actpageparser_ParserGroup);
		parserGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				String parseId2 = getResources().getResourceEntryName(checkedId);
				RadioButton selection = (RadioButton)findViewById(checkedId);
				//String strTag = selection.getTag().toString();
				strParsingRule = selection.getTag().toString();
				
			}
		});
	
		

	}
	/// end onCreate()
	
	// 파싱룰 그룹 선택
	
	
	/// 아이콘 설정 결과.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK) // 액티비티가 정상적으로 종료되었을 경우
		{
			if(requestCode==1) // InformationInput에서 호출한 경우에만 처리합니다.
			{               // 받아온 아이콘 정보를 저장해둔다.
				this.CategoryIconId = data.getStringExtra("data_IconId");
			}
		}
	}
	
	public void fetchCategory()
	{
		try{

			final ArrayList<CategoryModel> categoryList = new ArrayList<CategoryModel>();
			dba.open();
			Cursor categoryCursor = dba.fetchAllCategory();
			if(categoryCursor.moveToFirst())
			{
				do {

					CategoryModel m = new CategoryModel(
							categoryCursor.getString(1),
							categoryCursor.getString(2),
							(categoryCursor.getInt(3)==1 ? true : false),
							categoryCursor.getLong(4),
							categoryCursor.getLong(0));
					
					categoryList.add(m);
					
					
				} while(categoryCursor.moveToNext()==true);
			}
			final CategoryAdapter chAdapter = new CategoryAdapter(this, categoryList);
			if(lvCategoryList == null)
			{
				lvCategoryList = (ListView)findViewById(R.id.actsetting_categoryList);
			}
			lvCategoryList.setAdapter(chAdapter);
			dba.close();
			
			chAdapter.notifyDataSetChanged();
			
		}catch(Exception ex)
		{
			ex.toString();
		}
	}
	
	public void fetchCreditcard()
	{
		try{
			final ArrayList<CreditCardModel> cardList = new ArrayList<CreditCardModel>();
			dba.open();
			Cursor cardCursor = dba.fetchAllCreditcard();
			if(cardCursor.moveToFirst())
			{
				do {
					CreditCardModel m = new CreditCardModel(
							cardCursor.getLong(0),
							cardCursor.getString(1),
							cardCursor.getInt(2),
							cardCursor.getInt(3),
							cardCursor.getInt(4));
					
					cardList.add(m);
				} while(cardCursor.moveToNext()==true);
			}
			final CreditCardAdapter ccAdapter = new CreditCardAdapter(this, cardList);
			if(lvCreditCardList == null)
			{
				lvCreditCardList = (ListView)findViewById(R.id.actsetting_CreditCardList);
			}
			lvCreditCardList.setAdapter(ccAdapter);
			dba.close();
			
			ccAdapter.notifyDataSetChanged();
			
		}catch(Exception ex)
		{
			ex.toString();
		}
		
	}
	
	private void InitParserPage()
	{
		Spinner parserpage_CardList = (Spinner)findViewById(R.id.actpageparser_SpinnerCard);
		ArrayList<typeCreditcard> cardList = new ArrayList<typeCreditcard>();
		ArrayList<String> cardName = new ArrayList<String>();
		
		dba.open();
		Cursor cardCursor = dba.fetchAllCreditcard();
		
		if(cardCursor.moveToFirst())
		{
			do {
				typeCreditcard card = new typeCreditcard(cardCursor);
				cardList.add(card);
				cardName.add(card.getTitle());
			} while(cardCursor.moveToNext()==true);
		}
		
		dba.close();
		if(cardList.size() != 0){
			cardArray = new typeCreditcard[cardList.size()];
			cardList.toArray(cardArray);
			
			ArrayAdapter<String> cardAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, cardName);
					
			cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			parserpage_CardList.setAdapter(cardAdapter);
		}
		
		
	}
	
	private CategoryModel getCategoryModel(int position)
	{
		return ((CategoryAdapter)lvCategoryList.getAdapter()).getItem(position);
	}
	
	private CreditCardModel getCreditCardModel(int position)
	{
		
		return ((CreditCardAdapter)lvCreditCardList.getAdapter()).getItem(position);
		
	}

	// Category Data와 연동됨.
	class CategoryAdapter extends ArrayAdapter<CategoryModel>
	{
		Activity context;
		
		CategoryAdapter(Activity context, ArrayList<CategoryModel> list)
		{
			super(context, R.layout.widgetcategory, list);
			this.context = context;
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View row = convertView;
			widgetCategorywrapper categorywrapper;
			ImageButton btnUp;
			ImageButton btnDown;
			Button btnDelete;
			TextView tvTitle;
			
			if(row == null)
			{
				LayoutInflater inflater = context.getLayoutInflater();
				row = inflater.inflate(R.layout.widgetcategory, null);
				categorywrapper = new widgetCategorywrapper(row);
				row.setTag(categorywrapper);
				btnUp = categorywrapper.getButtonUp();
				btnDown = categorywrapper.getButtonDown();
				btnDelete = categorywrapper.getButtonDelete();
				tvTitle = categorywrapper.getTitle();
				
				// 카테고리 업
				btnUp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try{
							dba.open();
							Integer myPosition  = (Integer)v.getTag();
							CategoryModel toUp = getCategoryModel(myPosition);
							CategoryModel toDown = getCategoryModel(myPosition - 1);
							dba.updateCategory(toUp.getldx(), 
									toUp.getTitle(), toUp.getIconId(), toUp.getIsIncome(),
									toDown.getFavorite());
							dba.updateCategory(toDown.getldx(),
									toDown.getTitle(),
									toDown.getIconId(),
									toDown.getIsIncome(),
									toUp.getFavorite());
						}catch(Exception ex)
						{
							
						}finally
						{
							dba.close();
						}
						fetchCategory();
						
					}
				});
				
				// 카테고리 다운
				btnDown.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try{
							dba.open();
							Integer myPosition  = (Integer)v.getTag();
							CategoryModel toDown = getCategoryModel(myPosition);
							CategoryModel toUp = getCategoryModel(myPosition + 1);
							dba.updateCategory(toUp.getldx(), 
									toUp.getTitle(), toUp.getIconId(), toUp.getIsIncome(),
									toDown.getFavorite());
							dba.updateCategory(toDown.getldx(),
									toDown.getTitle(),
									toDown.getIconId(),
									toDown.getIsIncome(),
									toUp.getFavorite());
							
							dba.close();
							
							fetchCategory();
						}catch(Exception ex)
						{
							
						}finally
						{
							dba.close();
						}
					}
				});
				
				// 카테고리 삭제
				OnClickListener deleteClickListener = new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Integer myPosition  = (Integer)v.getTag();
						CategoryModel categoryModel = getCategoryModel(myPosition);
						dba.open();
						dba.deleteCategory(categoryModel.getldx());
						dba.close();
						
						fetchCategory();
					}
					
				};
				btnDelete.setOnClickListener(deleteClickListener);
				
				
				
			}else
			{
				categorywrapper= (widgetCategorywrapper)row.getTag();
				tvTitle = categorywrapper.getTitle();
				btnUp = categorywrapper.getButtonUp();
				btnDown = categorywrapper.getButtonDown();
				btnDelete = categorywrapper.getButtonDelete();
				
			}
			
			CategoryModel category = getCategoryModel(position);
			categorywrapper.getTitle().setText(category.getTitle());
			btnUp.setTag(new Integer(position));
			btnDown.setTag(new Integer(position));
			btnDelete.setTag(new Integer(position));
			tvTitle.setTag(new Integer(position));
			
				
			return row;
		}
	}
	
	
	// CreditCard 정보 관리
	class CreditCardAdapter extends ArrayAdapter<CreditCardModel>
	{
		Activity context;
		
		CreditCardAdapter(Activity context, ArrayList<CreditCardModel> list)
		{
			super(context, R.layout.widgetcreditcard, list);
			this.context = context;
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View row = convertView;
			widgetCreditcardwrapper creditcardwrapper;
			Button btnDelete;
			TextView tvCardTitle;
			TextView tvPaymentDay;
			TextView tvCloseDay;
			
			if(row == null)
			{
				LayoutInflater inflater = context.getLayoutInflater();
				row = inflater.inflate(R.layout.widgetcreditcard, null);
				creditcardwrapper = new widgetCreditcardwrapper(row);
				row.setTag(creditcardwrapper);
				tvCardTitle = creditcardwrapper.getCardTitle();
				tvPaymentDay = creditcardwrapper.getPaymentDay();
				tvCloseDay = creditcardwrapper.getCloseDay();
				btnDelete = creditcardwrapper.getButtonDelete();
							
				OnClickListener deleteClickListener = new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Integer myPosition  = (Integer)v.getTag();
						CreditCardModel cardModel = getCreditCardModel(myPosition);
						
						dba.open();
						dba.deleteCreditcard(cardModel.getldx());
						dba.close();
						
						fetchCreditcard();
					}
					
				};
				btnDelete.setOnClickListener(deleteClickListener);
				
				
				
			}else
			{
				creditcardwrapper= (widgetCreditcardwrapper)row.getTag();
				tvCardTitle = creditcardwrapper.getCardTitle();
				btnDelete = creditcardwrapper.getButtonDelete();
				tvPaymentDay = creditcardwrapper.getPaymentDay();
				tvCloseDay = creditcardwrapper.getCloseDay();
			}
						
			CreditCardModel card = getCreditCardModel(position);
			creditcardwrapper.getCardTitle().setText(card.getCardName());
			creditcardwrapper.getPaymentDay().setText(String.valueOf(card.getPaymentDay()));
			creditcardwrapper.getCloseDay().setText(String.valueOf(card.getCloseDay()));
			
			btnDelete.setTag(new Integer(position));
			
				
			return row;
		}
	}
	
	

	
}
