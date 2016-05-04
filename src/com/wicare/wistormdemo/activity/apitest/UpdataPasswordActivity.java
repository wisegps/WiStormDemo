package com.wicare.wistormdemo.activity.apitest;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.wicare.wistorm.api.WCommApi;
import com.wicare.wistorm.api.WUserApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.utils.L;
import com.wicare.wistormdemo.utils.T;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdataPasswordActivity extends Activity {

	private static final String TAG = "UpdataPasswordActivity";
	private TextView tvBack;
	private TextView tvTitle;
	
	private EditText edAccount;
	private EditText edPassword;
	private EditText edVolidCode;
	private EditText edPasswordAgain;
	
	private Button btnGetVolid;
	private Button btnUpdataPassword;
	
	private String volidCode = "";//验证码
	private String account = "";
	private String password = "";
	private String passwordAgain = "";
	
	public WUserApi userApi;
	public WCommApi commApi;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_updata_password);
		
		tvBack = (TextView)findViewById(R.id.tv_back);
		tvTitle = (TextView)findViewById(R.id.tv_title);
		tvTitle.setText("重置密码");
		
		edAccount = (EditText)findViewById(R.id.ed_account);
		edPassword = (EditText)findViewById(R.id.ed_password);
		edVolidCode = (EditText)findViewById(R.id.ed_volid);
		edPasswordAgain = (EditText)findViewById(R.id.ed_password_again);
		
		btnGetVolid = (Button)findViewById(R.id.btn_getvolid);
		btnUpdataPassword = (Button)findViewById(R.id.btn_updata_password);
		
		tvBack.setOnClickListener(onClickListener);
		btnGetVolid.setOnClickListener(onClickListener);
		btnUpdataPassword.setOnClickListener(onClickListener);
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi();
		commApi = new WCommApi();
		BaseVolley.init(UpdataPasswordActivity.this);
	}
	
	/**
	 * OnClickListener
	 */
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_back:
				finish();
				break;
			case R.id.btn_getvolid:
				sendVolidCode();
				break;
			case R.id.btn_updata_password:
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
			T.showShort(UpdataPasswordActivity.this, "账号不能为空");
			return;
		}
		commApi.sendSMS(account, 2, new OnSuccess() {
			
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
				T.showShort(UpdataPasswordActivity.this, "验证码已经发送到" + account + "中");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取后台发送到手机的ya
	 */
	private void isVolidCodeTrue(){		
		volidCode = edVolidCode.getText().toString().trim();
		if("".equals(volidCode) || "".equals(account)){
			T.showShort(UpdataPasswordActivity.this, "帐号或验证码不能为空");
			return;
		}else {
			userApi.volidCode(account, volidCode, new OnSuccess() {
				
				@Override
				protected void onSuccess(String response) {
					// TODO Auto-generated method stub
					L.d(TAG, response);
					try {
						JSONObject object = new JSONObject(response);
						if(object.getBoolean("valid") == true){
							L.d(TAG, "验证码正确");
							updataPassword();
						}else {
							T.showShort(UpdataPasswordActivity.this, "验证码不对请从新填写");
						}
					} catch (Exception e) {
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
	 * 重置密码
	 */
	private void updataPassword(){
		password = edPassword.getText().toString().trim();
		passwordAgain = edPasswordAgain.getText().toString().trim();	
		if("".equals(password) || "".equals(passwordAgain)){
			T.showShort(UpdataPasswordActivity.this, "密码不能为空");	
			return;
		}
		if(!password.equals(passwordAgain)){
			T.showShort(UpdataPasswordActivity.this, "两次输入的密码不一致");	
			return;
		}
		userApi.reset(account, password, "2", volidCode,new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d(TAG, "修改密码返回信息：" + response);
				try {
					JSONObject object = new JSONObject(response);
					if("0".equals(object.getString("status_code"))){
						L.d(TAG, "修改密码成功");
						T.showShort(UpdataPasswordActivity.this, "修改密码成功");
						UpdataPasswordActivity.this.finish();
					}else {
						T.showShort(UpdataPasswordActivity.this, "修改密码失败");		
					}
				} catch (Exception e) {
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
