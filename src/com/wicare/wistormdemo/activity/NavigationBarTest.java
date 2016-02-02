package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.ui.WNavigationBar;
import com.wicare.wistorm.ui.WNavigationBar.OnGridviewClickListener;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class NavigationBarTest extends Activity implements OnGridviewClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigationbar);
		WNavigationBar wn = (WNavigationBar) findViewById(R.id.wn);
		wn.setOnGridviewClickListener(this);
	}

	@Override
	public void onGridview_1_Click(int position) {
		// TODO Auto-generated method stub
		Toast.makeText(NavigationBarTest.this, "第一个页面" + "第 " +position + "个图标", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onGridview_2_Click(int position) {
		// TODO Auto-generated method stub
		Toast.makeText(NavigationBarTest.this, "第二个页面" + "第 " +position + "个图标", Toast.LENGTH_SHORT).show();
	}

}
