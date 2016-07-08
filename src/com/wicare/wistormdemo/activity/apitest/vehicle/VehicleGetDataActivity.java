package com.wicare.wistormdemo.activity.apitest.vehicle;

import java.util.HashMap;

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
import android.widget.TextView;

/**   
 * @ClassName:  VehicleGetDataActivity   
 * @Description:获取车辆信息   
 * @author: Wu 
 * @date:   2016-5-3 上午9:56:32   
 *      
 */
public class VehicleGetDataActivity extends Activity {
	
	private TextView tv_vehicle_data;
	private EditText et_obj_id;
	private WVehicleApi vehicleApi;
	public AppApplication application;
	private String obj_id;
	private String token;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_get_data);
		mContext = VehicleGetDataActivity.this;
		et_obj_id = (EditText)findViewById(R.id.ed_obj_id);
		tv_vehicle_data = (TextView)findViewById(R.id.tv_vehicle_data);
		application = (AppApplication)getApplication();
		token = application.access_token;
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		vehicleApi = new WVehicleApi(mContext);
		BaseVolley.init(VehicleGetDataActivity.this);
	}
	
	public void getVehicleData(View view){
		obj_id = et_obj_id.getText().toString().trim();
		if(TextUtils.isEmpty(obj_id)){
			T.showShort(VehicleGetDataActivity.this, "obj_id 不能为空");
			return;
		}
		
		String fields = "nick_name," +
						"cust_name," +
						"obj_id," +
						"cust_id," +
						"seller_id," +
						"obj_name," +
						"active_gps_data," +
						"device_id," +
						"car_brand_id," +
						"car_brand," +
						"car_series_id," +
						"car_series," +
						"car_type_id," +
						"car_type," +
						"frame_no," +
						"maintain_last_mileage," +
						"mileage," +
						"insurance_no," +
						"maintain_company," +
						"insurance_company," +
						"insurance_tel," +
						"maintain_tel," +
						"gas_no";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("obj_id", obj_id);
		params.put("access_token", token);
		
		vehicleApi.get(params,fields, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				L.i("Vehicle", response);
				
				if(!TextUtils.isEmpty(response)){
					tv_vehicle_data.setText(response);
					T.showShort(VehicleGetDataActivity.this, "获取车辆信息成功！");
				}
				
				
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
			}
		});
	}	
}
