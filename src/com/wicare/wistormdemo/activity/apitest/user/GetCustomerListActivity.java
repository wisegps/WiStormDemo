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
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Wu
 * 
 * 获取客户列表信息
 *
 */
public class GetCustomerListActivity extends Activity {

	private Context mContext;
	private WUserApi userApi;
	private AppApplication app;
	
	private TextView tv_response;
	private EditText et_seller_id,et_sorts,et_limit;
	private String seller_id, sorts,limit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_customer_list);
		app = (AppApplication)getApplication();
		mContext = GetCustomerListActivity.this;
		initView();
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi(mContext);
		BaseVolley.init(mContext);
	}
	
	private void initView(){
		et_seller_id = (EditText)findViewById(R.id.et_seller_id);
		et_sorts     = (EditText)findViewById(R.id.et_sorts);
		et_limit     = (EditText)findViewById(R.id.et_limit);
		tv_response  = (TextView)findViewById(R.id.tv_response);
	}
	
	
	public void getCustomerList(View view){	
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",app.access_token);
		params.put("seller_id", "21");//商户ID  
		params.put("sorts", "cust_name");
		params.put("limit", "20");
		//返回的字段
		String fields = "cust_id,cust_name,cust_type,mobile,create_time";
		userApi.getList(params, fields, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d("USER", response);
				tv_response.setText(response);
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
