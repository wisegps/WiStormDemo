package com.wicare.wistormdemo.activity.apitest.device;

import java.util.HashMap;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WDeviceApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.app.AppApplication;
import com.wicare.wistormdemo.utils.T;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * @author wu
 * // 获取设备信息
 * 参数:(传入设备的 序列号)
 */
public class GetDeviceActivity extends Activity {
	
	private Context mContext;
	private AppApplication app;
	
	private WDeviceApi deviceApi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_get);
		
		mContext = GetDeviceActivity.this;
		app = (AppApplication)getApplication();
		init();
	}

	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		deviceApi = new WDeviceApi(mContext);
		BaseVolley.init(mContext);
	}
	
	public void getDevice(View view){
		T.showShort(mContext, "click");
	
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",app.access_token);
		params.put("serial","你的序列号");
		
		deviceApi.get(params, "", new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
