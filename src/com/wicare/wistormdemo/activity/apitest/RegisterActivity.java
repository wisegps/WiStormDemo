package com.wicare.wistormdemo.activity.apitest;

import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WCommApi;
import com.wicare.wistorm.api.WUserApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.utils.L;
import com.wicare.wistormdemo.utils.T;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	
	private static final String TAG = "RegisterActivity";
	private TextView tvBack;
	private TextView tvTitle;
	
	private EditText edAccount;
	private EditText edPassword;
	private EditText edVolidCode;
	private EditText edPasswordAgain;
	
	private Button btnGetVolid;
	private Button btnRegister;
	
	private String volidCode = "";//验证码
	private String account = "";
	private String password = "";
	private String passwordAgain = "";
	
	public WUserApi userApi;
	public WCommApi commApi;
	
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		mContext = RegisterActivity.this;
		
		tvBack = (TextView)findViewById(R.id.tv_back);
		tvTitle = (TextView)findViewById(R.id.tv_title);
		tvTitle.setText("注册");
		edAccount = (EditText)findViewById(R.id.ed_account);
		edPassword = (EditText)findViewById(R.id.ed_password);
		edVolidCode = (EditText)findViewById(R.id.ed_volid);
		edPasswordAgain = (EditText)findViewById(R.id.ed_password_again);
		
		btnGetVolid = (Button)findViewById(R.id.btn_getvolid);
		btnRegister = (Button)findViewById(R.id.btn_register);
		
		tvBack.setOnClickListener(onClickListener);
		btnGetVolid.setOnClickListener(onClickListener);
		btnRegister.setOnClickListener(onClickListener);
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi(mContext);
		commApi = new WCommApi(mContext);
		BaseVolley.init(RegisterActivity.this);
	}
	
	/**
	 * OnClickListener
	 */
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.tv_back:
				finish();
				break;
			case R.id.btn_getvolid:
				sendVolidCode();
				break;
			case R.id.btn_register:
				isVolidCodeTrue();
				break;
			
			}
		} 
	};

	
	/**
	 * 发送验证码到手机或者邮箱帐号
	 */
	private void sendVolidCode(){
		account = edAccount.getText().toString().trim();
		if("".equals(account)){
			T.showShort(RegisterActivity.this, "账号不能为空");
			return;
		}
		commApi.sendSMS(account, 1,new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d(TAG,response);
				parseSendVolidCode(response);
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
	private void parseSendVolidCode(String strJson){
		try {
			JSONObject object = new JSONObject(strJson);
			if("0".equals(object.getString("status_code"))){
				T.showShort(RegisterActivity.this, "验证码已经发送到" + account + "中");	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取后台发送到手机的ya
	 */
	private void isVolidCodeTrue(){		
		volidCode = edVolidCode.getText().toString().trim();
		if("".equals(volidCode) || "".equals(account)){
			T.showShort(RegisterActivity.this, "帐号或验证码不能为空");
			return;
		}else {
			userApi.volidCode(account, volidCode,new OnSuccess() {
				
				@Override
				protected void onSuccess(String response) {
					L.d(TAG, response);
					try {
						JSONObject object = new JSONObject(response);
						if(object.getBoolean("valid") == true){
							L.d(TAG, "验证码正确");
							registerAccount();
						}else {
							T.showShort(RegisterActivity.this, "验证码不对请从新填写");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}	
				}
			},new OnFailure() {
				
				@Override
				protected void onFailure(VolleyError error) {
					// TODO Auto-generated method stub	
				}
			});
		}	
	}
	

	/**
	 * 注册
	 */
	private void registerAccount(){
		password = edPassword.getText().toString().trim();
		passwordAgain = edPasswordAgain.getText().toString().trim();	
		if("".equals(password) || "".equals(passwordAgain)){
			T.showShort(RegisterActivity.this, "密码不能为空");
			return;
		}
		if(!password.equals(passwordAgain)){
			// TODO Auto-generated method stub
			T.showShort(RegisterActivity.this, "两次输入的密码不一致");				
			return;
		}
		userApi.register(account, password, volidCode,new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				L.d(TAG, "注册返回信息 ：" + response);
				try {
					JSONObject object = new JSONObject(response);
					if("0".equals(object.getString("status_code"))){
						T.showShort(RegisterActivity.this, "注册成功");
						RegisterActivity.this.finish();
					}else{
						// TODO Auto-generated method stub
						T.showShort(RegisterActivity.this, "注册失败");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		},new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
			}
		});	
	}
	
}
