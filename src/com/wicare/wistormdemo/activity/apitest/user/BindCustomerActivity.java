package com.wicare.wistormdemo.activity.apitest.user;

import java.util.HashMap;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WUserApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.app.AppApplication;
import com.wicare.wistormdemo.utils.L;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * @author Wu 
 * 
 * 绑定客户
 *
 */
public class BindCustomerActivity extends Activity {

	private Context mContext;
	private WUserApi userApi;
	private AppApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind_customer);
		app = (AppApplication)getApplication();
		mContext = BindCustomerActivity.this;
		init();
	}
	
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi(mContext);
		BaseVolley.init(mContext);
	}
	
	
	
	public void bindCustomer(View view){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",app.access_token);
		params.put("_cust_id","1244");
		params.put("seller_id", "21");

		userApi.bind(params, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d("USER", response);
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});	
	}
	
	
}
