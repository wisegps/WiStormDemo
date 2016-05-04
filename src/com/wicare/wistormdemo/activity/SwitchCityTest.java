package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.toolkit.WCitySelector;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SwitchCityTest extends Activity implements OnClickListener {
	
	private static final int REQUEST_CODE = 0;
	private Button mSwitchCity;
	private TextView cityCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_city);
        cityCall = (TextView) findViewById(R.id.city_callback);
        mSwitchCity = (Button) findViewById(R.id.btn_switch_city);
        mSwitchCity.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Intent i = new Intent(SwitchCityTest.this,WCitySelector.class);
		startActivityForResult(i, REQUEST_CODE);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode) {
		case 0:
			try{
				cityCall.setText(data.getExtras().getString("cityName"));
            }catch (Exception e) {
                e.printStackTrace();
            }
			break;
		}
	}
	

}
