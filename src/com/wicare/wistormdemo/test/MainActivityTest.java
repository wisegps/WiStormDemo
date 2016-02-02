package com.wicare.wistormdemo.test;

import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.activity.MainActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.widget.Button;

/**
 * MainActivityTest
 * 
 * @author c
 * @date 2015-10-23
 */
public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mainActivity;

	/**
	 * @param activityClass
	 */
	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mainActivity = getActivity();
		
		//
	}

	/**
	 * 测试一下点击 btn_date_select
	 */
	public void testClick() {
		mainActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mainActivity.findViewById(R.id.btn_date_select).performClick();
			}

		});
		
		try {
			new Thread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
