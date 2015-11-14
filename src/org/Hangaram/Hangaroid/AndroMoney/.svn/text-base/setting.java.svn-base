package org.Hangaram.Hangaroid.AndroMoney;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class setting extends Activity {
	
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.setting);
		
		
		TabHost tabs=(TabHost)findViewById(R.id.tabhost);
		
		tabs.setup();
		
		TabHost.TabSpec spec=tabs.newTabSpec("tag1");
		spec.setContent(R.id.categorytab);
		spec.setIndicator("Category");
		tabs.addTab(spec);
		
		spec=tabs.newTabSpec("tag2");
		spec.setContent(R.id.creditcardtab);
		spec.setIndicator("Credit card");
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
		
	}
}
