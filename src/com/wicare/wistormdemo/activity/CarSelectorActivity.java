package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.toolkit.WCarBrandSelector;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CarSelectorActivity extends Activity implements OnClickListener {
	
	static final String TAG = "CarSelectorActivity";
	static final int CAR_SELECTOR_RESULT_CODE = 1;
	private Button carSelector;
	private TextView carMsg;//返回信息
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_selector);
		carSelector = (Button) findViewById(R.id.btn_selector);
		carMsg = (TextView) findViewById(R.id.tv_car_result);
		carSelector.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(CarSelectorActivity.this,WCarBrandSelector.class);
		startActivityForResult(i, CAR_SELECTOR_RESULT_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
		   case CAR_SELECTOR_RESULT_CODE:
		    Bundle car_msg = data.getExtras(); 
		    String brank   = car_msg.getString("brank"); //汽车品牌
		    String series  = car_msg.getString("series");//汽车型号
		    String type    = car_msg.getString("type");  //汽车款式
		    String brankId  = car_msg.getString("brankId"); //汽车品牌id
		    String seriesId = car_msg.getString("seriesId");//汽车型号ID
		    String typeId   = car_msg.getString("typeId");  //汽车款式ID
		    String logoUrl   = car_msg.getString("logoUrl");//汽车品牌logo 图片的路径
		    carMsg.setText("车辆：" + brank + "  " + series + "  " + type);
		    Log.e(TAG,brankId + "---" + seriesId + "---" + typeId + "---" + logoUrl);
		    break;
		}
	}
}
