package com.wicare.wistormdemo.activity.apitest;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WUserApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.app.AppApplication;
import com.wicare.wistormdemo.utils.L;
import com.wicare.wistormdemo.utils.T;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginAPITestActivity extends Activity{
	
	private static final String TAG = "LoginActivity";
	private EditText edAccount;
	private EditText edPassword;
	private Button btnLogin;
	private TextView tvUpdataPassword;
	private TextView tvRegister;
	
	private String account;
	private String password;

	public WUserApi userApi;
	public AppApplication application;

	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_api_test);
		pref = getSharedPreferences("user", MODE_PRIVATE);
		editor = getSharedPreferences("user", MODE_PRIVATE).edit();
		account = pref.getString("account", "");
		application = (AppApplication)getApplication();
		edAccount  = (EditText)findViewById(R.id.ed_account);
		edAccount.setText(account);
		edPassword = (EditText)findViewById(R.id.ed_password);
		btnLogin   = (Button)findViewById(R.id.btn_login);
		tvUpdataPassword = (TextView)findViewById(R.id.tv_password);
		tvRegister = (TextView)findViewById(R.id.tv_register);		
		btnLogin.setOnClickListener(onClickListener);
		tvUpdataPassword.setOnClickListener(onClickListener);
		tvRegister.setOnClickListener(onClickListener);
		init();
	}
	
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		BaseVolley.init(LoginAPITestActivity.this);
		userApi = new WUserApi();
	}
	
	
	
	
	/**
	 * OnClickListener
	 */
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_login:
				login();
				break;
			case R.id.tv_password:
				Intent intent_password = new Intent(LoginAPITestActivity.this,UpdataPasswordActivity.class);
				startActivity(intent_password);
				break;
				
			case R.id.tv_register:
				Intent intent_register = new Intent(LoginAPITestActivity.this,RegisterActivity.class);
				startActivity(intent_register);
				break;
			
			}
		}
	};

	
	
	/**
	 * login
	 */
	private void login(){
		account  = edAccount.getText() .toString().trim();
		password = edPassword.getText().toString().trim();		
		if("".equals(account) || "".equals(password)){
			T.showShort(LoginAPITestActivity.this, "账号或者密码不能为空");
			return;
		}
		
		editor.putString("account", account);
		editor.commit();
		userApi.login(account, password, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				parseLogin(response);
			}
		},new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	/**
	 * @param strJson
	 */
	private void parseLogin(String strJson){
		L.d(TAG,"=======" + strJson);	
		try {
			JSONObject object = new JSONObject(strJson);			
			application.access_token = object.getString("access_token");
			application.cust_id      = object.getString("cust_id");
			application.cust_name    = object.getString("cust_name");
			
			L.d(TAG,application.cust_id     + "\n" 
					+ application.cust_name + "\n" 
					+ application.access_token);
			finish();
			Intent i = new Intent(LoginAPITestActivity.this,ListApiActivity.class);
			startActivity(i);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
