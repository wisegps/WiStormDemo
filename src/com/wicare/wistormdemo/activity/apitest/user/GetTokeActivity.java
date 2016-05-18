package com.wicare.wistormdemo.activity.apitest.user;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WUserApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.utils.L;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Wu
 * 
 * 获取access_token
 */
public class GetTokeActivity extends Activity {

	private TextView tv_access_token;
	private EditText et_account,et_pwd;
	private Context mContext;
	private WUserApi userApi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_token);
		tv_access_token = (TextView)findViewById(R.id.tv_token);
		et_account = (EditText)findViewById(R.id.et_account);
		et_pwd = (EditText)findViewById(R.id.et_pwd);
		mContext = GetTokeActivity.this;
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi();
		BaseVolley.init(mContext);
	}
	
	
	public void getAccess_token(View view){
		String account = et_account.getText().toString().trim();
		String password = et_pwd.getText().toString().trim();
		
		if(TextUtils.isEmpty(account)||TextUtils.isEmpty(password)){
			Toast.makeText(mContext, "账号密码不能为空！", Toast.LENGTH_SHORT).show();
			return;
		}
		
		userApi.getToken(account, password, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d("USER", response);
				try {
					JSONObject obj = new JSONObject(response);
					if("0".equals(obj.getString("status_code"))){
						Toast.makeText(mContext, "获取token成功！", Toast.LENGTH_SHORT).show();
						tv_access_token.setText(response);
					}else{
						Toast.makeText(mContext, "获取token失败！", Toast.LENGTH_SHORT).show();
					}	
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
			}
		});	
	}
	
	
}
