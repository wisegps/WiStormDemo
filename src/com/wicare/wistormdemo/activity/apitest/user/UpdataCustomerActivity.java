package com.wicare.wistormdemo.activity.apitest.user;

import java.util.HashMap;

import org.json.JSONException;
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
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * @author Wu
 *
 * 更新客户信息
 */
public class UpdataCustomerActivity extends Activity {
	
	private EditText et_cust_id,et_cust_name,et_remark;
	
	private Context mContext;
	private WUserApi userApi;
	private AppApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updata_customer);
		mContext = UpdataCustomerActivity.this;
		app = (AppApplication)getApplication();
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
	
	public void updataCustomer(View view){
		String cust_id = et_cust_id.getText().toString().trim();
		String cust_name = et_cust_name.getText().toString().trim();
		String remark = et_remark.getText().toString().trim();
		if(TextUtils.isEmpty(cust_id)){
			T.showShort(mContext, "Cust_id 不能为空");
			return;
		}
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", app.access_token);
		params.put("_cust_id", cust_id);
		params.put("cust_name", cust_name);
		params.put("remark", remark);
		userApi.update(params, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d("USER", response);
				
				try {
					JSONObject obj = new JSONObject(response);
					
					if("0".equals(obj.getString("status_code"))){
						T.showShort(mContext, "更新客户信息成功");
					}else{
						T.showShort(mContext, "更新客户信息失败");
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
	
	/**
	 * 初始化UI
	 */
	private void initView(){
		et_cust_id = (EditText)findViewById(R.id.et_cust_id);
		et_cust_name = (EditText)findViewById(R.id.et_cust_name);
		et_remark = (EditText)findViewById(R.id.et_remark);
	}
	
	
}
