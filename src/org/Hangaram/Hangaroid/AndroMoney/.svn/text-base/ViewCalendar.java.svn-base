package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.format.Formatter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class ViewCalendar extends View implements OnTouchListener {

	// Listener Classes
	public interface DateChangedListener {
		public void onDateChanged( View view, Calendar calendar );
	}
	
	// Event Triggers
	public DateChangedListener onDateChangedListener; 	
	
	// Flags
	private final static int		DT_LEFT						= 0x00000001;
	private final static int		DT_RIGHT 					= 0x00000002;
	private final static int		DT_TOP						= 0x00000004;
	private final static int		DT_BOTTOM 					= 0x00000008;
	private final static int		DT_CENTER					= 0x00000010;
	private final static int		DT_VCENTER					= 0x00000020;
	
	// Sizes
	private final static int		HEADER_HEIGHT				= 50;
	private final static int		DATE_HEIGHT					= 25;
	private final static int		HEADER_TEXT_HEIGHT			= 20;
	private final static int		DATE_TEXT_HEIGHT			= 12;
	private final static int		DAY_TEXT_HEIGHT				= 14;
		
	// Colors
	//// Header
	private final static int		COLOR_HEADER_BK				= Color.argb( 255, 150, 150, 150 );
	private final static int		COLOR_HEADER_BORDER			= Color.argb( 255,  50,  50,  50 );
	private final static int		COLOR_HEADER_TEXT			= Color.argb( 255, 255, 255, 255 );
	private final static int		COLOR_HEADER_TEXT_SHADOW	= Color.argb( 255,   0,   0,   0 );
	
	//// Alpha
	private final static int 		ALPHA_ARROW_NORMAL			= 50;
	private final static int 		ALPHA_ARROW_ACTIVE			= 100;
	
	//// Date
	private final static int		COLOR_DATE_BK				= Color.argb( 255, 220, 220, 220 );
	private final static int		COLOR_DATE_BORDER			= Color.argb( 255,   0,   0,   0 );
	private final static int		COLOR_DATE_TEXT				= Color.argb( 255,  80,  80,  80 );
	private final static int		COLOR_DATE_TEXT_SHADOW		= Color.argb( 255, 255, 255, 255 );
	
	//// Day
	private final static int		COLOR_DAY_BK_TODAY			= Color.argb( 255, 120, 240, 120 );
	private final static int		COLOR_DAY_BK_NORMAL			= Color.argb( 255, 220, 220, 220 );
	private final static int		COLOR_DAY_BK_SELECTED		= Color.argb( 255, 120, 120, 255 );
	private final static int		COLOR_DAY_BK_SPECIAL1		= Color.argb( 255, 255, 220, 220 );
	private final static int		COLOR_DAY_BK_SPECIAL2		= Color.argb( 255, 220, 255, 220 );
	private final static int		COLOR_DAY_BORDER			= Color.argb( 255, 180, 180, 180 );
	private final static int		COLOR_DAY_BORDER_DARK		= Color.argb( 255,  35,  35, 132 );
	private final static int		COLOR_DAY_BORDER_BLACK		= Color.argb( 255,   0,   0,   0 );
	private final static int		COLOR_DAY_BEVEL				= Color.argb( 255, 255, 255, 255 );
	private final static int		COLOR_DAY_TEXT_NORMAL		= Color.argb( 255,   0,   0,   0 );
	private final static int		COLOR_DAY_TEXT_INACTIVE		= Color.argb( 255, 170, 170, 170 );
	private final static int		COLOR_DAY_TEXT_SELECTED		= Color.argb( 255, 255, 255, 255 );
	private final static int		COLOR_DAY_TEXT_TODAY		= Color.argb( 255, 255,   0, 255 );
	private final static int		COLOR_DAY_TEXT_WEEKEND		= Color.argb( 255,   0,   0, 255 );
	
	// General Objects
	private Rect[][] 				mRects;
	private Rect[]					mRectDays;
	private Rect					mRectView;
	private Rect[]					mRectArrow;
	private int	[]					mAlphaArrow = { 
													ALPHA_ARROW_NORMAL, 
													ALPHA_ARROW_NORMAL, 
													ALPHA_ARROW_NORMAL, 
													ALPHA_ARROW_NORMAL };
	
	private boolean					mShowHeader = true;
	private int						mMonth;
	private int						mToday;
	
	// Graphics Objects
	private Paint					mPaint;
	PaintFlagsDrawFilter 			mSetFilter;
	PaintFlagsDrawFilter 			mResetFilter; 
	
	// Date Objects
	private Calendar				mSelectedDate = null;
	private Calendar				mThisDate = null;
	private Calendar[][] 			mArrDates = null;
	private Vector<Calendar>		mvEventDates = new Vector<Calendar>();

	// String Objects
	private StringBuilder			mStringBuilder = new StringBuilder();
	private String					mStrHeader;
	private String[] 				mStrDays = new String[] {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	private String[] 				mStrMonths = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	
	public ViewCalendar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mRectArrow = new Rect[4];
		mRectArrow[0] = new Rect();
		mRectArrow[1] = new Rect();
		mRectArrow[2] = new Rect();
		mRectArrow[3] = new Rect();
		mThisDate = Calendar.getInstance();
		mSelectedDate = Calendar.getInstance();
		fillDates( mThisDate );
		setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
		initGraphics();
	}
	
	/////////////////////////////////////////////////////////////////
	// functions
	
	private void initGraphics()
	{
		mPaint = new Paint();
		mSetFilter = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG); 
		mResetFilter = new PaintFlagsDrawFilter( Paint.FILTER_BITMAP_FLAG, 0);
	}
	
	private Rect[][] createGrid()
	{
		//Array of rectangles representing the calendar
		
		if( mRectView != null )
		{
			Rect[][] rectTemp = new Rect[7][6];
			
			//header rectangles
			//
			mRectDays = new Rect[7];
			
			int nWidth = mRectView.width() - 1;
			int nHeight = mRectView.height() - 1;
			
			float fXX = 0;
			float fYY = 0;
			float fXSize = ( nWidth / 7.0f );
			float fYSize = 0;
			
			if( mShowHeader )
			{
				fYY = HEADER_HEIGHT;
				fYSize = ( ( nHeight - ( HEADER_HEIGHT + DATE_HEIGHT ) ) / 6.0f );
			}
			else
				fYSize = ( ( nHeight - ( DATE_HEIGHT ) ) / 6.0f );
			
			for(int day = 0 ; day < 7 ; day++ )
			{
				Rect r1 = new Rect( (int)fXX, (int)fYY, (int)( fXX + fXSize ), (int)( fYY + DATE_HEIGHT ) );
				fXX += fXSize;
				mRectDays[day] = new Rect( r1 );
	
			}
			
			if( mShowHeader )
				fYY = HEADER_HEIGHT + DATE_HEIGHT;
			else
				fYY = DATE_HEIGHT;
				
			for(int week = 0 ; week < 6 ; week++)
			{
				fXX=0;
				for(int day = 0 ; day < 7 ; day++)
				{
					Rect r = new Rect( (int)fXX, (int)fYY, (int)( fXX + fXSize ), (int)( fYY + fYSize ) );
					fXX += fXSize;
					rectTemp[day][week] = new Rect( r );
				}
				fYY += fYSize;
			}
			return rectTemp;
		}
		return null;
	}
	
	private void fillDates( Calendar calendar )
	{
		try
		{
			//grid column
			int intDayofWeek = 0;
			//grid row
			int intWeek = 0;
	
			//total day counter
			int intTotalDays = -1;
	
			int nYear = calendar.get( Calendar.YEAR );
			int nMonth = calendar.get( Calendar.MONTH );
			int nDay = calendar.get( Calendar.DAY_OF_MONTH );
			
			mMonth = nMonth;
			mToday = nDay;
			
			mStringBuilder.setLength(0 );
			mStringBuilder.append( mStrMonths[ nMonth ] );
			mStringBuilder.append( ' ' );
			mStringBuilder.append( nYear );
			mStrHeader = mStringBuilder.toString();//String.format( "%s %d", mStrMonths[ nMonth ], nYear );
			
			Calendar calPrevMonth = new GregorianCalendar( 
															calendar.get( Calendar.YEAR ), 
															calendar.get( Calendar.MONTH ), 
															calendar.get( Calendar.DAY_OF_MONTH ),
															calendar.get( Calendar.HOUR_OF_DAY ),
															calendar.get( Calendar.MINUTE ),
															calendar.get( Calendar.SECOND ) );
			
			Calendar calNextMonth = new GregorianCalendar( 
															calendar.get( Calendar.YEAR ), 
															calendar.get( Calendar.MONTH ), 
															calendar.get( Calendar.DAY_OF_MONTH ),
															calendar.get( Calendar.HOUR_OF_DAY ),
															calendar.get( Calendar.MINUTE ),
															calendar.get( Calendar.SECOND ) );
			
			calPrevMonth.add( Calendar.MONTH, -1 );
			calNextMonth.add( Calendar.MONTH, 1 );
						
			//number of days in active month
			int intCurrDays = calendar.getActualMaximum( Calendar.DAY_OF_MONTH );
			
			//number of days in active month
			int intPrevDays = calPrevMonth.getActualMaximum( Calendar.DAY_OF_MONTH );
			
			//number of days in active month
			int intNextDays = calNextMonth.getActualMaximum( Calendar.DAY_OF_MONTH );
	
			Calendar[] datesCurr = new GregorianCalendar[intCurrDays];
			Calendar[] datesPrev = new GregorianCalendar[intPrevDays];
			Calendar[] datesNext = new GregorianCalendar[intNextDays];
	
			for(int i = 0 ; i < intCurrDays ; i++)
			{
				datesCurr[i] = new GregorianCalendar(calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), i + 1 );
			}
	
			for(int i = 0;i<intPrevDays;i++)
			{
				datesPrev[i] = new GregorianCalendar(calPrevMonth.get( Calendar.YEAR ), calPrevMonth.get( Calendar.MONTH ), i + 1 );
			}
	
			for(int i = 0;i<intNextDays;i++)
			{
				datesNext[i] = new GregorianCalendar(calNextMonth.get( Calendar.YEAR ), calNextMonth.get( Calendar.MONTH ), i + 1 );
			}
	
			//where does the first day of the week land?		
			intDayofWeek = datesCurr[0].get( Calendar.DAY_OF_WEEK ) - 1;
	
			mArrDates = new GregorianCalendar[7][6];
	
			for( int intDay = 0 ; intDay < intCurrDays ; intDay++ )
			{
				//populate array of dates for active month, this is used to tell what day of the week each day is
	
				intDayofWeek = datesCurr[intDay].get( Calendar.DAY_OF_WEEK ) - 1;
	
				//fill the array with the day numbers
				mArrDates[intDayofWeek][intWeek] = datesCurr[intDay];
				if( intDayofWeek == 6 )
				{
					intWeek++;
				}
	
				//Back fill any days from the previous month
				//this is does here because I needed to know where the first day of the active month fell in the grid
				if( intDay == 0 )
				{
					//Days in previous month
					int intDaysPrev = calPrevMonth.getActualMaximum( Calendar.DAY_OF_MONTH );
	
					//if the first day of the active month is not sunday, count backwards and fill in day number
					if( intDayofWeek > 0 )
					{
						for(int i = intDayofWeek - 1 ; i >= 0 ; i-- )
						{
							mArrDates[i][0] = datesPrev[intDaysPrev - 1];
							intDaysPrev--;
							intTotalDays++;
						}
					}
				}
				
				intTotalDays++;
			}//for
			
			//fill in the remaining days of the grid with the beginning of the next month
			intTotalDays++;
			//what row did we leave off in for active month?
			int intRow = intTotalDays/7;
	
			int intCol;
	
			int intDayNext=0;
	
			for(int i = intRow ; i < 6 ; i++)
			{
				intCol = intTotalDays - ( intRow * 7 );
				for( int j = intCol ; j < 7 ; j++ )
				{
					mArrDates[j][i] = datesNext[intDayNext];
					intDayNext++;
					intTotalDays++;
				}	
				intRow++;
			}
		}
		
		catch( Exception e )
		{
			System.out.println( String.format( "Exception( on fillDates) - %s", e.getMessage() ) );
		}
	}
	
	public static void draw3DRect( Canvas canvas, Rect rect, int c1, int c2 )
	{
		Paint paint = new Paint();
		
		paint.setColor( c1 );
		canvas.drawLine(rect.left, rect.top, rect.right, rect.top, paint );
		canvas.drawLine(rect.left, rect.top, rect.left, rect.bottom, paint );
		
		paint.setColor( c2 );
		canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, paint );
		canvas.drawLine(rect.right, rect.bottom, rect.left, rect.bottom, paint );
	}
	
	public static void drawBox( Canvas canvas, Rect rect, Paint paint )
	{
		canvas.drawLine(rect.left, rect.top, rect.right, rect.top, paint );
		canvas.drawLine(rect.left, rect.top, rect.left, rect.bottom, paint );
		canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, paint );
		canvas.drawLine(rect.right, rect.bottom, rect.left, rect.bottom, paint );
	}
	
	public static void drawText( Canvas canvas, String text, Rect rect, int flags, Paint paint )
	{
		Rect bound = new Rect();
		Point origin = new Point();
		
		// Text의 바운드를 구한다.
		paint.getTextBounds( text, 0, text.length(), bound );
		bound.top = Math.abs( bound.top );
		
		// 그려질 위치를 구한다.
		if( ( flags & DT_LEFT ) > 0 )
			origin.x = rect.left;
		
		else if( ( flags & DT_RIGHT ) > 0 )
			origin.x = rect.right - bound.width();
		
		else if( ( flags & DT_CENTER ) > 0 )
		{
			if( rect.width() >= bound.width() )
				origin.x = (int)( rect.left + ( rect.width() - bound.width() ) / 2 );
			else
				origin.x = (int)( rect.left - ( bound.width() - rect.width() ) / 2 );
		}
		
		if( ( flags & DT_TOP ) > 0 )
			origin.y = rect.top;
		
		else if( ( flags & DT_BOTTOM ) > 0 )
			origin.y = rect.bottom - bound.height();
		
		else if( ( flags & DT_VCENTER ) > 0 )
		{
			if( rect.height() >= bound.height() )
				origin.y = (int)( rect.top + ( rect.height() - bound.height() ) / 2 );
			else
				origin.y = (int)( rect.top - ( bound.height() - rect.height() ) / 2 );
		}
		 
		canvas.drawText( text, origin.x, origin.y, paint);
	}
	
	private void drawCalendarIcon( Canvas canvas )
	{
		try
		{
			String str = "";	
			Paint paint = new Paint();
			Resources res = getResources();
			Bitmap icon = BitmapFactory.decodeResource( res, R.drawable.calendar_date );
			Bitmap bitmap = Bitmap.createBitmap( icon.getWidth(), icon.getHeight(), Bitmap.Config.ARGB_8888 );
			Canvas imgcanvas = new Canvas( bitmap );
			
			// Antialias 필터 적용
			paint.setAntiAlias( true );
			imgcanvas.setDrawFilter( mSetFilter );
			// 아이콘 회전
			imgcanvas.rotate( 15, icon.getWidth() / 2.0f, icon.getHeight() / 2.0f );
			imgcanvas.drawBitmap( icon, 0, 0, paint );
			// 필터 적용 해제
			imgcanvas.setDrawFilter( mResetFilter );
			
			Rect rect = new Rect( 0, 11, bitmap.getWidth(), bitmap.getHeight() );
			if( mSelectedDate != null )
			{
				mStringBuilder.delete( 0, mStringBuilder.length() );
				mStringBuilder.append(  mSelectedDate.get( Calendar.DAY_OF_MONTH ) );
				str = mStringBuilder.toString(); //String.format( "%d", mSelectedDate.get( Calendar.DAY_OF_MONTH ) );
			}
			paint.setAntiAlias( true );
			paint.setColor( Color.BLACK );
			paint.setTextSize( 14f );
			paint.setFakeBoldText( true );
			rect.offset( -2, 1 );
			drawText( imgcanvas, str, rect, DT_CENTER | DT_VCENTER, paint );
			paint.setFakeBoldText( false );
			rect.top = 0;
			canvas.drawBitmap( bitmap, mRectView.width() - bitmap.getWidth() - 5, 3, paint );
		}
		
		catch(Exception e)
		{
			System.out.println( e.getMessage() );
		}
	}
	
	private void drawArrowIcon( Canvas canvas )
	{
		try
		{
			Paint paint = new Paint();
			Resources res = getResources();
			Bitmap icon = null;
			
			// arrow for month
			
			// fast_left
			paint.setAlpha( mAlphaArrow[0] );
			icon = BitmapFactory.decodeResource( res, R.drawable.arrow_fast_left );
			canvas.drawBitmap( icon, mRectView.width() / 2 - ( 75 + icon.getWidth() ) , 10, paint );
			mRectArrow[0].left = mRectView.width() / 2 - ( 75 + icon.getWidth() );
			mRectArrow[0].top = 10;
			mRectArrow[0].right = mRectArrow[0].left + icon.getWidth();
			mRectArrow[0].bottom = mRectArrow[0].top + icon.getHeight();
			
			// left
			paint.setAlpha( mAlphaArrow[1] );
			icon = BitmapFactory.decodeResource( res, R.drawable.arrow_left );
			canvas.drawBitmap( icon, mRectView.width() / 2 - ( 40 + icon.getWidth() ) , 10, paint );
			mRectArrow[1].left = mRectView.width() / 2 - ( 40 + icon.getWidth() );
			mRectArrow[1].top = 10;
			mRectArrow[1].right = mRectArrow[1].left + icon.getWidth();
			mRectArrow[1].bottom = mRectArrow[1].top + icon.getHeight();
			
			// right
			paint.setAlpha( mAlphaArrow[2] );
			icon = BitmapFactory.decodeResource( res, R.drawable.arrow_right );
			canvas.drawBitmap( icon, mRectView.width() / 2 + 40, 10, paint );
			mRectArrow[2].left = mRectView.width() / 2 + 40;
			mRectArrow[2].top = 10;
			mRectArrow[2].right = mRectArrow[2].left + icon.getWidth();
			mRectArrow[2].bottom = mRectArrow[2].top + icon.getHeight();
			
			// fast_right		
			paint.setAlpha( mAlphaArrow[3] );
			icon = BitmapFactory.decodeResource( res, R.drawable.arrow_fast_right );
			canvas.drawBitmap( icon, mRectView.width() / 2 + 75, 10, paint );
			mRectArrow[3].left = mRectView.width() / 2 + 75;
			mRectArrow[3].top = 10;
			mRectArrow[3].right = mRectArrow[3].left + icon.getWidth();
			mRectArrow[3].bottom = mRectArrow[3].top + icon.getHeight();
		}
		
		catch(Exception e)
		{
			System.out.println( e.getMessage() );
		}
	}
	
	private void drawHeader( Canvas canvas )
	{
		Rect rect = new Rect( mRectView );
		
		rect.left--;
		rect.right++;
		rect.bottom = HEADER_HEIGHT;
		
		mPaint.setAntiAlias( false );
		
		// background
		mPaint.setStyle( Style.FILL );
		mPaint.setColor( COLOR_HEADER_BK );
		canvas.drawRect( rect, mPaint );
		
		// border
		mPaint.setStyle( Style.STROKE );
		mPaint.setColor( COLOR_HEADER_BORDER );
		canvas.drawRect( rect, mPaint );
		
		// text shadow
		mPaint.setAntiAlias( true );
		mPaint.setFakeBoldText( true );
		mPaint.setStyle( Style.FILL );
		mPaint.setTextSize( HEADER_TEXT_HEIGHT );
		mPaint.setColor( COLOR_HEADER_TEXT_SHADOW );
		rect.offset( 1, 1 );
		drawText( canvas, mStrHeader, rect, DT_CENTER | DT_VCENTER, mPaint );
		
		// text
		mPaint.setColor( COLOR_HEADER_TEXT );
		rect.offset( -1, -1 );
		drawText( canvas, mStrHeader, rect, DT_CENTER | DT_VCENTER, mPaint );
		
		mPaint.setFakeBoldText( false );
	}
	
	private void drawDate( Canvas canvas )
	{
		Rect rect = new Rect( mRectView );
		
		rect.left--;
		rect.right++;
		rect.top = 0;
		rect.bottom = 0;
		
		if( mShowHeader )
		{
			rect.top = HEADER_HEIGHT;
			rect.bottom = HEADER_HEIGHT;
		}
		
		rect.bottom += DATE_HEIGHT;
		
		mPaint.setAntiAlias( false );
		
		// background
		mPaint.setStyle( Style.FILL );
		mPaint.setColor( COLOR_DATE_BK );
		canvas.drawRect( rect, mPaint );
		
		// border
		mPaint.setStyle( Style.STROKE );
		mPaint.setColor( COLOR_DATE_BORDER );
		canvas.drawRect( rect, mPaint );
		
		// text shadow
		mPaint.setAntiAlias( true );
		mPaint.setTextSize( DATE_TEXT_HEIGHT );
//		mPaint.setFakeBoldText( true );
		
		for( int day = 0 ; day < 7 ; ++day )
		{
			rect = new Rect( mRectDays[day] );

			mPaint.setColor( COLOR_DATE_TEXT_SHADOW );
			rect.offset( 1, 1 );
			drawText( canvas, this.mStrDays[day], rect, DT_CENTER | DT_VCENTER, mPaint );
			rect.offset( -1, -1 );
			
			// text
			mPaint.setColor( COLOR_DATE_TEXT );
			drawText( canvas, this.mStrDays[day], rect, DT_CENTER | DT_VCENTER, mPaint );
			rect.offset( -1, 0 );
			drawText( canvas, this.mStrDays[day], rect, DT_CENTER | DT_VCENTER, mPaint );
		}
		mPaint.setFakeBoldText( false );
	}
	
	private void drawDayBox( Canvas canvas, Rect rect, String text, int clrbk, int clrborder, int clrbevel, int clrtext, boolean selected )
	{
		Rect r = new Rect( rect );

		// background
		mPaint.setAntiAlias( false );
		mPaint.setStyle( Style.FILL );
		mPaint.setColor( clrbk );
		canvas.drawRect( r, mPaint );
		
		// border
		mPaint.setStyle( Style.STROKE );
		mPaint.setColor( clrborder );
		canvas.drawRect( r, mPaint );
		
		// bevel
		if( !selected )
		{
			mPaint.setColor( clrbevel );
			canvas.drawLine( r.left + 1, r.top + 1, r.right - 1, r.top + 1, mPaint );
			canvas.drawLine( r.right - 1, r.top + 1, r.right - 1, r.bottom - 1, mPaint );
		}
		
		else
		{
			Rect inner = new Rect( r );
			
			inner.inset( 1, 1 );
			mPaint.setColor( COLOR_DAY_BORDER_DARK );
			mPaint.setAlpha( 100 );
			drawBox( canvas, inner, mPaint );
			
			mPaint.setAlpha( 70 );
			canvas.drawLine( inner.left, inner.top + 1, inner.right, inner.top + 1, mPaint);
			canvas.drawLine( inner.left, inner.bottom - 1, inner.right, inner.bottom - 1, mPaint);
			canvas.drawLine( inner.left + 1, inner.top, inner.left + 1, inner.bottom, mPaint);
			canvas.drawLine( inner.right - 1, inner.top, inner.right - 1, inner.bottom, mPaint);
			
			mPaint.setAlpha( 30 );
			canvas.drawLine( inner.left, inner.top + 2, inner.right, inner.top + 2, mPaint);
			canvas.drawLine( inner.left, inner.bottom - 2, inner.right, inner.bottom - 2, mPaint);
			canvas.drawLine( inner.left + 2, inner.top, inner.left + 2, inner.bottom, mPaint);
			canvas.drawLine( inner.right - 2, inner.top, inner.right - 2, inner.bottom, mPaint);
			
			mPaint.setAlpha( 10 );
			canvas.drawLine( inner.left, inner.top + 3, inner.right, inner.top + 3, mPaint);
			canvas.drawLine( inner.left, inner.bottom - 3, inner.right, inner.bottom - 3, mPaint);
			canvas.drawLine( inner.left + 3, inner.top, inner.left + 3, inner.bottom, mPaint);
			canvas.drawLine( inner.right - 3, inner.top, inner.right - 3, inner.bottom, mPaint);
		}

		// text
		mPaint.setAntiAlias( true );
		mPaint.setStyle( Style.FILL );
		mPaint.setTextSize( DAY_TEXT_HEIGHT );
		mPaint.setColor( clrtext );
		mPaint.setFakeBoldText( true );
		drawText( canvas, text, r, DT_CENTER | DT_VCENTER, mPaint );
		mPaint.setFakeBoldText( false );

	}
	
	private void drawCalendar( Canvas canvas )
	{
		boolean today = false;
		boolean weekend = false;
		
		int selectedday = -1;
		int selectedweek = -1;
		
		String str = "";
		Calendar calendar;
		
		Rect rect = new Rect( mRectView );
		
		rect.top = DATE_HEIGHT;
		
		if( mShowHeader )
			rect.top += HEADER_HEIGHT;
		
		//graphics object for paint event
		
		if( mRects == null )
			return;
		
		if( mArrDates == null )
			return;
		
		for( int week = 0 ; week < 6 ; ++week )
		{
			for( int day = 0 ; day < 7 ; ++day )
			{
				calendar = mArrDates[day][week];
				rect = mRects[day][week];
				
				mStringBuilder.setLength( 0 );
				mStringBuilder.append( calendar.get( Calendar.DAY_OF_MONTH ) );
				str = mStringBuilder.toString();//String.format( "%d", calendar.get( Calendar.DAY_OF_MONTH ) );
			
				if( day == 0 || day == 6 )
					weekend = true;
				else
					weekend = false;
				
				if( calendar.get( Calendar.MONTH ) == mMonth )
				{
					if( mSelectedDate != null )
					{
						if( calendar.get( Calendar.YEAR ) == mSelectedDate.get( Calendar.YEAR ) &&
							calendar.get( Calendar.MONTH ) == mSelectedDate.get( Calendar.MONTH ) &&
							calendar.get( Calendar.DAY_OF_MONTH ) == mSelectedDate.get( Calendar.DAY_OF_MONTH ) )
						{
							selectedday = day;
							selectedweek = week;
						}
					}
					else
					{
						selectedday = -1;
						selectedweek = -1;
					}
					
					drawDayBox(
							canvas,
							rect,
							str,
							COLOR_DAY_BK_NORMAL,
							COLOR_DAY_BORDER,
							COLOR_DAY_BEVEL,
							( weekend ? COLOR_DAY_TEXT_WEEKEND : COLOR_DAY_TEXT_NORMAL ),
							false
							);

				}
				
				else
				{
					drawDayBox(
							canvas,
							rect,
							str,
							COLOR_DAY_BK_NORMAL,
							COLOR_DAY_BORDER,
							COLOR_DAY_BEVEL,
							COLOR_DAY_TEXT_INACTIVE,
							false
							);

				}
			}	
		}
		
		if( selectedday != -1 &&
			selectedweek != -1 )
		{
			if( selectedday == 0 || 
				selectedday == 6 )
				weekend = true;
			else
				weekend = false;
			
			calendar = mArrDates[selectedday][selectedweek];
			rect = mRects[selectedday][selectedweek];
			
			mStringBuilder.setLength( 0 );
			mStringBuilder.append( calendar.get( Calendar.DAY_OF_MONTH ) );
			str = mStringBuilder.toString();//String.format( "%d", calendar.get( Calendar.DAY_OF_MONTH ) );
			
			drawDayBox(
					canvas,
					rect,
					str,
					COLOR_DAY_BK_SELECTED,
					COLOR_DAY_BORDER_BLACK,
					COLOR_DAY_BEVEL,
					( weekend ? COLOR_DAY_TEXT_WEEKEND : COLOR_DAY_TEXT_SELECTED ),
					true
					);
		}
	}
	
	private Calendar HitTest( float x, float y )
	{
		Calendar calendar = null;
		Rect rect = new Rect( mRectView );
		
		rect.top = DATE_HEIGHT;
		if( mShowHeader )
			rect.top += HEADER_HEIGHT;
		
		if( rect.contains( (int)x, (int)y ) )
		{
			for(int week = 0 ; week < 6 ; week++)
			{
				for(int day = 0 ; day < 7 ; day++)
				{
					if( mRects[day][week].contains( (int)x, (int)y ) )
					{
						calendar = mArrDates[day][week];
					}
				}
			}
		}
		
		return calendar;
	}
	
	public Calendar getSelectedDate()
	{
		return mSelectedDate;
	}
	
	public Calendar getCurrentDate()
	{
		return mThisDate;
	}
	
	public void moveToNow()
	{
		try
		{
			mThisDate = Calendar.getInstance();
			mSelectedDate = mThisDate;
			fillDates( mThisDate );
			
			if( onDateChangedListener != null )
				onDateChangedListener.onDateChanged( this, mThisDate );
			
			invalidate();
		}
		
		catch( Exception e )
		{
			
		}
	}
	/////////////////////////////////////////////////////////////////
	// event Handlers
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		
		Calendar calendar = HitTest( x, y );
//		String toast = String.format("%d-%02d-%02d", calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ) + 1, calendar.get( Calendar.DAY_OF_MONTH ) );
//		Utility.popupToast( this.getContext(), toast, Toast.LENGTH_SHORT );
		
		if( calendar != null )
		{
			mSelectedDate = new GregorianCalendar( calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), calendar.get( Calendar.DAY_OF_MONTH ) );
			
			if( mThisDate.get( Calendar.MONTH ) != mSelectedDate.get( Calendar.MONTH ))
			{
				mThisDate = mSelectedDate;
				fillDates( mThisDate );
			}
			
			if( this.onDateChangedListener != null )
				onDateChangedListener.onDateChanged( this, mSelectedDate );
			
			invalidate();
		}
		
		if( mRectArrow != null )
		{
			if( event.getAction() == MotionEvent.ACTION_DOWN )
			{
				for( int i = 0 ; i < mRectArrow.length ; ++i )
				{
					if( mRectArrow[i].contains( (int)x, (int)y ) )
					{
						mAlphaArrow[i] = ALPHA_ARROW_ACTIVE;
						
						switch( i )
						{
							case 0 : // 1 Year earlier
								mThisDate.add( Calendar.YEAR, -1 );
								fillDates( mThisDate );
								invalidate();
								break;
								
							case 1 : // 1 Month earlier
								mThisDate.add( Calendar.MONTH, -1 );
								fillDates( mThisDate );
								invalidate();
								break;
								
							case 2 : // 1 month later
								mThisDate.add( Calendar.MONTH, 1 );
								fillDates( mThisDate );
								invalidate();
								break;
								
							case 3 : // 1 Month later
								mThisDate.add( Calendar.YEAR, 1 );
								fillDates( mThisDate );
								invalidate();
								break;
						}
					}
				}
			}
			
			if( event.getAction() == MotionEvent.ACTION_UP )
			{
				for( int i = 0 ; i < mRectArrow.length ; ++i )
					mAlphaArrow[i] = ALPHA_ARROW_NORMAL;
				
				invalidate();
			}
		}
		
		if( mRectArrow != null &&
			event.getAction() == MotionEvent.ACTION_UP )
		{
			for( int i = 0 ; i < mRectArrow.length ; ++i )
				mAlphaArrow[i] = ALPHA_ARROW_NORMAL;
			
			invalidate();
		}
		
		return false;
	}
	
	@Override
	protected void onDraw( Canvas canvas )
	{
		drawHeader( canvas );
		drawDate( canvas );
		drawCalendar( canvas );
		drawCalendarIcon( canvas );
		drawArrowIcon( canvas );
	}
	
	@Override
	protected void onSizeChanged( int w, int h, int oldw, int oldh )
	{
		super.onSizeChanged(w, h, oldw, oldh);
		
		if( mRectView == null )
			mRectView = new Rect( 0, 0, w, h );
		else
		{
			mRectView.left = 0;
			mRectView.top = 0;
			mRectView.right = w;
			mRectView.bottom = ( h );
		}
		
		mRects = createGrid();
	}
}

