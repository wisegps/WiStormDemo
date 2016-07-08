package com.wicare.wistormdemo.activity.apitest.vehicle;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WVehicleApi;
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
 * @ClassName:  VehicleDeleteActivity   
 * @Description:删除车辆 
 * @author: Wu 
 * @date:   2016-5-5 上午10:18:01   
 *      
 */
public class VehicleDeleteActivity extends Activity {
	
	private Context mContext;
	private WVehicleApi vehicleApi;
	private AppApplication app;
	
	private EditText et_obj_id;
	private String obj_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_delete);
		mContext = VehicleDeleteActivity.this;
		app = (AppApplication)getApplication();
		et_obj_id = (EditText)findViewById(R.id.et_obj_id);
		init();
		
	}
	
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		vehicleApi = new WVehicleApi(mContext);
		BaseVolley.init(mContext);
	}
	
	
	public void deleteVehicle(View view){
		obj_id = et_obj_id.getText().toString().trim();
		if(TextUtils.isEmpty(obj_id)){
			T.showShort(mContext, "车辆ID不能为空");
			return;
		}
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", app.access_token);
		params.put("obj_id", obj_id);

		vehicleApi.delete(params, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.i("Vehicle", response);
				
				
				try {
					JSONObject obj = new JSONObject(response);
					if("0".equalsIgnoreCase(obj.getString("status_code"))){
						T.showShort(mContext, "删除车辆信息成功");
					}else{
						T.showShort(mContext, "删除车辆信息失败");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new OnFailure() { 
			
			@Override
			protected void onFailure(VolleyError error) {
				
			}
		});
		
	}
	
	
}
