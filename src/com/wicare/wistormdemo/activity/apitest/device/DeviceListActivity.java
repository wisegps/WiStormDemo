package com.wicare.wistormdemo.activity.apitest.device;

import java.util.HashMap;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WDeviceApi;
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
import android.widget.TextView;

/**
 * @author w
 * 获取设备信息列表
 */
public class DeviceListActivity extends Activity {

	private String TAG= "DEVICE_TEST";
	
	private Context mContext;
	private AppApplication app;
	private WDeviceApi deviceApi;
	
	private TextView tv_response;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_list);
		mContext = DeviceListActivity.this;
		app = (AppApplication)getApplication();
		tv_response = (TextView)findViewById(R.id.tv_response);
		init();
		
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		deviceApi = new WDeviceApi();
		BaseVolley.init(mContext);
	}
	
	
	public void getDeviceList(View view){
		
		String fields = "device_id,serial,active_gps_data";
		
		deviceApi.getDevicelist(getParams(), fields, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				L.d(TAG, response);
				tv_response.setText(response);
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				
			}
		});
	}
	
	/**
	 * @return
	 */
	private HashMap<String, String> getParams(){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", app.access_token);
		params.put("dealer_id", app.cust_id);//cust_id 和user_id 相同
		params.put("sorts", "device_id");
//		params.put("page", "");
		params.put("min_id", "0");
		params.put("max_id", "0");
		params.put("limit", "-1");
		return params;
	}
	
}
