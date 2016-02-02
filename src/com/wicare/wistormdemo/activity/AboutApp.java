package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.R;
import com.wicare.wistorm.toolkit.WAboutApp;

import android.os.Bundle;

/**
 * @author Wu
 * 关于
 */
public class AboutApp extends WAboutApp {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAppLogo(R.drawable.ws_ico_baba);
		setAppCopyright("Copyright @ 2015-2016 Wicare");
		setAppVersion("叭叭 V 1.0.1");
	}

}
