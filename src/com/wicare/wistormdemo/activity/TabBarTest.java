package com.wicare.wistormdemo.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.wicare.wistorm.ui.WTabBar;
import com.wicare.wistorm.ui.WTabBar.OnTabChangedListener;
import com.wicare.wistormdemo.R;

public class TabBarTest extends Activity implements OnTabChangedListener{
	//选项卡未选中图片
	private int[] imgNormal = { 
		R.drawable.ico_tab_home,
		R.drawable.ico_tab_friends,
		R.drawable.ico_tab_msg,
		R.drawable.ico_tab_more };
	//选项卡选中图片
	private int[] imgPress = { 
		R.drawable.ic_launcher,
		R.drawable.ic_launcher,
		R.drawable.ic_launcher, 
		R.drawable.ic_launcher };
	//选项卡标题
	private String[] itemNames = {"一号","二号","三号","四号"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_tabbar);
		WTabBar ll = (WTabBar) findViewById(R.id.tabbarLayout);
        ll.setOnTabChangedListener(this);
        ll.setTabText(itemNames); //设置 选项卡的标题
        ll.setTabTextColor(Color.BLUE, Color.RED);//设置选项卡的标题选中和未选中颜色
        ll.setTabImage(imgNormal, imgPress);//设置选项卡选中和未选中的图片
		
		
	}
	@Override
	public void onTabClick(int index) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			Toast.makeText(TabBarTest.this, "选项卡一", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(TabBarTest.this, "选项卡二", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(TabBarTest.this, "选项卡三", Toast.LENGTH_SHORT).show();
			break;
	
		case 3:
			Toast.makeText(TabBarTest.this, "选项卡四", Toast.LENGTH_SHORT).show();
			break;
	
		default:
			break;
		}
	}
	
	
	
	
	
	

}
