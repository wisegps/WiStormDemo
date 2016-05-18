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
import com.wicare.wistormdemo.utils.T;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Wu
 *
 * 获取客户信息
 */
public class GetCustomerDataActivity extends Activity {
	
	
	private Context mContext;
	private WUserApi userApi;
	
	private AppApplication app;
	private TextView tv_customer_data;
	private EditText et_cust_id;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_customer_data);
		mContext = GetCustomerDataActivity.this;
		app = (AppApplication)getApplication();
		tv_customer_data = (TextView)findViewById(R.id.tv_customer_data);
		et_cust_id = (EditText)findViewById(R.id.et_cust_id);
		init();
	}
	
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi();
		BaseVolley.init(mContext);
	}
	
	
	public void getCustomerData(View view){
	
		String cust_id = et_cust_id.getText().toString().trim();
		
		if(TextUtils.isEmpty(cust_id)){
			T.showShort(mContext, "请输入 cust_id");
			return;
		}
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",app.access_token);
		params.put("cust_id",cust_id);
		userApi.get(params, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d("USER", response);
				tv_customer_data.setText(response);
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
	
}
