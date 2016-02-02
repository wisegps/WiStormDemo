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
}
