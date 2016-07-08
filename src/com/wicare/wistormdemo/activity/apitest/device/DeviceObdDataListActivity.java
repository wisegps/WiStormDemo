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
 * 获取电压曲线及水温曲线
 */
public class DeviceObdDataListActivity extends Activity {

	private String TAG= "DEVICE_TEST";
	
	private Context mContext;
	private AppApplication app;
	private WDeviceApi deviceApi;
	
	private TextView tv_response;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_obd_data);
		
		mContext = DeviceObdDataListActivity.this;
		app = (AppApplication)getApplication();
		tv_response = (TextView)findViewById(R.id.tv_response);
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		deviceApi = new WDeviceApi(mContext);
		BaseVolley.init(mContext);
	}
	
	public void getDeviceObdDataList(View view){
		
		String fields = "rcv_time,obd_data";
		deviceApi.getObdDataList(getParams(), fields, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				L.d(TAG, response);
				tv_response.setText(response);
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				L.e(TAG, error.toString());
			}
		});
		
	}
	
	private HashMap<String, String> getParams(){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", app.access_token);
		params.put("serial", "你的设备的序列号");
		params.put("rcv_time", "2016-05-30 00:00:00@2016-06-10 15:59:59");
//		params.put("page", "");
		params.put("min_id", "0");
		params.put("max_id", "0");
		params.put("limit", "2");
		return params;
	}
}
