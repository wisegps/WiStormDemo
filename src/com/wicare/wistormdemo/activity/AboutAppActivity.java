package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.R;
import com.wicare.wistorm.toolkit.WAboutApp;

import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Wu
 * 关于
 */
public class AboutAppActivity extends WAboutApp {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAppLogo(R.drawable.ic_launcher);
		setAppCopyright("Copyright @ 2015-2016 Wicare");
		setAppVersion("Wistorm V 1.0.1");
	}

	@Override
	protected void onClickUpdata() {
		// TODO Auto-generated method stub
		Toast.makeText(AboutAppActivity.this, getResources().getString(R.string.app_checkupdata) + "...", Toast.LENGTH_SHORT).show();
	}

}
