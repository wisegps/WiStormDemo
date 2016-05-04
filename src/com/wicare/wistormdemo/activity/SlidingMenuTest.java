package com.wicare.wistormdemo.activity;

import android.os.Bundle;
import android.view.Window;

import com.wicare.wistorm.ui.WSlidingMenu;


public class SlidingMenuTest extends WSlidingMenu {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);	
		
	}

	@Override
	protected void setUpView() {
		// TODO Auto-generated method stub
		//可以在这里设置侧滑菜单的 左边侧滑栏和右边的内容栏。还可以设置侧滑菜单的属性
	}
}
