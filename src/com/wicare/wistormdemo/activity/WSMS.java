package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.R;
import com.wicare.wistorm.api.WSMSApi;
import com.wicare.wistorm.api.WSMSApi.SMSListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * WSMS 手机短信验证页面
 * 
 * @author c
 * @date 2015-11-12
 */
public class WSMS extends Activity implements OnClickListener {

	private WSMSApi api;
	private EditText etPhone;
	private String phone;
	
	private Button btnGetCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ws_sms);
		api = new WSMSApi(this, smsListener);
		
		etPhone = (EditText) findViewById(R.id.etPhone);
		btnGetCode = (Button) findViewById(R.id.btnGetCode);
		btnGetCode.setOnClickListener(this);
		
		
	}

	/**
	 * 实现smslistener接口
	 * 返回手机验证码
	 */
	private SMSListener smsListener = new SMSListener() {

		@Override
		public void onCodeResponse(String response) {
				Log.i("WSMS", response);
				Toast.makeText(WSMS.this, response, Toast.LENGTH_LONG).show();
		}

	};
	
	
	/*
	* @param arg0 
	* @see android.view.View.OnClickListener#onClick(android.view.View) 
	*/
	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.btnGetCode){
			phone = etPhone.getText().toString().trim();
			api.getVerifyCode(phone);
			
		}
	}

}
