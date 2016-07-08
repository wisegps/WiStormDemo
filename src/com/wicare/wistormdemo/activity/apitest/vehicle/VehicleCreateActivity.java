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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**   
 * @ClassName:  VehicleCreateActivity   
 * @Description:创建车辆信息  
 * @author: Wu
 * @date:   2016-5-5 上午10:18:23   
 *      
 */
public class VehicleCreateActivity extends Activity{
	
	static final String TAG = "VehicleCreateActivity";
	private EditText 	et_obj_id,     //车辆id 
						et_cust_id,	   //用户id
						et_cust_name,
						et_obj_name,
						et_nick_name,
						et_device_id,
						et_car_brand_id,
						et_car_brand,
						et_car_series_id,
						et_car_series,
						et_car_type_id,
						et_car_type,
						et_vio_city_name,
						et_vio_location,
						et_engine_no,
						et_frame_no,
						
						et_reg_no,
						et_last_query,
						et_insurance_company,
						et_insurance_tel,
						et_insurance_date,
						et_insurance_no,
						et_annual_inspect_date,
						et_maintain_company,
						et_maintain_tel,
						et_maintain_last_mileage,
						et_maintain_last_date,
						et_maintain_next_mileage,
						et_mileage,
						et_gas_no,
						et_fuel_ratio,
						et_fuel_price,
						et_buy_date;
	
	private String  	tv_obj_id,     //车辆id 
						tv_cust_id,	   //用户id
						tv_cust_name,
						tv_obj_name,
						tv_nick_name,
						tv_device_id,
						tv_car_brand_id,
						tv_car_brand,
						tv_car_series_id,
						tv_car_series,
						tv_car_type_id,
						tv_car_type,
						tv_vio_city_name,
						tv_vio_location,
						tv_engine_no,
						tv_frame_no,
	
						tv_reg_no,
						tv_last_query,
						tv_insurance_company,
						tv_insurance_tel,
						tv_insurance_date,
						tv_insurance_no,
						tv_annual_inspect_date,
						tv_maintain_company,
						tv_maintain_tel,
						tv_maintain_last_mileage,
						tv_maintain_last_date,
						tv_maintain_next_mileage,
						tv_mileage,
						tv_gas_no,
						tv_fuel_ratio,
						tv_fuel_price,
						tv_buy_date;
	
	private WVehicleApi vehicleApi;
	public AppApplication application;
	
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_create);
		application = (AppApplication)getApplication();
		mContext = VehicleCreateActivity.this;
		initView();
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		vehicleApi = new WVehicleApi(mContext);
		BaseVolley.init(VehicleCreateActivity.this);
	}
	
	private void initView(){
		et_obj_id = (EditText)findViewById(R.id.ed_obj_id);
		et_cust_id = (EditText)findViewById(R.id.ed_cust_id);
		et_cust_name = (EditText)findViewById(R.id.ed_cust_name);
		et_obj_name = (EditText)findViewById(R.id.ed_obj_name);
		et_nick_name = (EditText)findViewById(R.id.ed_nick_name);
		et_device_id = (EditText)findViewById(R.id.ed_device_id);
		et_car_brand_id = (EditText)findViewById(R.id.ed_car_brand_id);
		et_car_brand = (EditText)findViewById(R.id.ed_car_brand);
		et_car_series_id = (EditText)findViewById(R.id.ed_car_series_id);
		et_car_series = (EditText)findViewById(R.id.ed_car_series);
		et_car_type_id = (EditText)findViewById(R.id.ed_car_type_id);
		et_car_type = (EditText)findViewById(R.id.ed_car_type);
		et_vio_city_name = (EditText)findViewById(R.id.ed_vio_city_name);
		et_vio_location = (EditText)findViewById(R.id.ed_vio_location);
		et_engine_no = (EditText)findViewById(R.id.ed_engine_no);
		et_frame_no = (EditText)findViewById(R.id.ed_frame_no);	
		
		et_reg_no = (EditText)findViewById(R.id.ed_reg_no);
		et_last_query = (EditText)findViewById(R.id.ed_last_query);
		et_insurance_company = (EditText)findViewById(R.id.ed_insurance_company);
		et_insurance_tel = (EditText)findViewById(R.id.ed_insurance_tel);
		et_insurance_date = (EditText)findViewById(R.id.ed_insurance_date);
		et_insurance_no = (EditText)findViewById(R.id.ed_insurance_no);
		et_annual_inspect_date = (EditText)findViewById(R.id.ed_annual_inspect_date);
		et_maintain_company = (EditText)findViewById(R.id.ed_maintain_company);
		et_maintain_tel = (EditText)findViewById(R.id.ed_maintain_tel);
		et_maintain_last_mileage = (EditText)findViewById(R.id.ed_maintain_last_mileage);
		et_maintain_last_date = (EditText)findViewById(R.id.ed_maintain_last_date);
		et_maintain_next_mileage = (EditText)findViewById(R.id.ed_maintain_next_mileage);
		et_mileage = (EditText)findViewById(R.id.ed_mileage);
		et_gas_no = (EditText)findViewById(R.id.ed_gas_no);
		et_fuel_ratio = (EditText)findViewById(R.id.ed_fuel_ratio);
		et_fuel_price = (EditText)findViewById(R.id.ed_fuel_price);
		et_buy_date = (EditText)findViewById(R.id.ed_buy_date);	
	}
	
	public void createVehicle(View view){

		
		
		
		
		String token     = application.access_token;
	
		tv_obj_id        = et_obj_id.getText().toString().trim();    
		tv_cust_id       = et_cust_id.getText().toString().trim();  	   
		tv_cust_name     = et_cust_name.getText().toString().trim();  
		tv_obj_name      = et_obj_name.getText().toString().trim();  
		tv_nick_name     = et_nick_name.getText().toString().trim(); 
		tv_device_id     = et_device_id.getText().toString().trim();  
		tv_car_brand_id  = et_car_brand_id.getText().toString().trim();  
		tv_car_brand     = et_car_brand.getText().toString().trim();  
		tv_car_series_id = et_car_series_id.getText().toString().trim();  
		tv_car_series    = et_car_series.getText().toString().trim();  
		tv_car_type_id   = et_car_type_id.getText().toString().trim();  
		tv_car_type      = et_car_type.getText().toString().trim();  
		tv_vio_city_name = et_vio_city_name.getText().toString().trim();  
		tv_vio_location  = et_vio_location.getText().toString().trim();  
		tv_engine_no     = et_engine_no.getText().toString().trim();  
		tv_frame_no      = et_frame_no.getText().toString().trim();  
		
		
		tv_reg_no = et_reg_no.getText().toString().trim();  
		tv_last_query = et_last_query.getText().toString().trim();  
		tv_insurance_company = et_insurance_company.getText().toString().trim();  
		tv_insurance_tel  = et_insurance_tel.getText().toString().trim();  
		tv_insurance_date = et_insurance_date.getText().toString().trim();  
		tv_insurance_no   = et_insurance_no.getText().toString().trim();  
		tv_annual_inspect_date = et_annual_inspect_date.getText().toString().trim();  
		tv_maintain_company    = et_maintain_company.getText().toString().trim();  
		tv_maintain_tel        = et_maintain_tel.getText().toString().trim();  
		tv_maintain_last_mileage = et_maintain_last_mileage.getText().toString().trim();  
		tv_maintain_last_date    = et_maintain_last_date.getText().toString().trim();  
		tv_maintain_next_mileage = et_maintain_next_mileage.getText().toString().trim();  
		tv_mileage     = et_mileage.getText().toString().trim();  
		tv_gas_no      = et_gas_no.getText().toString().trim();  
		tv_fuel_ratio  = et_fuel_ratio.getText().toString().trim();  
		tv_fuel_price  = et_fuel_price.getText().toString().trim();  
		tv_buy_date    = et_buy_date.getText().toString().trim();  

		if(TextUtils.isEmpty(tv_cust_id)){
			T.showShort(VehicleCreateActivity.this, "cust_id 不能为空");
			return;
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		params.put("obj_id",  tv_obj_id);
		params.put("cust_id", tv_cust_id);
		params.put("cust_name", tv_cust_name);
		params.put("obj_name", tv_obj_name);
		params.put("nick_name", tv_nick_name);
		params.put("device_id", tv_device_id);
		params.put("car_brand_id", tv_car_brand_id);
		params.put("car_brand", tv_car_brand);
		params.put("car_series_id", tv_car_series_id);
		params.put("car_series", tv_car_series);
		params.put("car_type_id", tv_car_type_id);
		params.put("car_type", tv_car_type);
		params.put("vio_city_name", tv_vio_city_name);
		params.put("vio_location", tv_vio_location);
		params.put("engine_no", tv_engine_no);
		params.put("frame_no", tv_frame_no);
		
		params.put("reg_no", tv_reg_no);
		params.put("last_query", tv_last_query);
		params.put("insurance_company", tv_insurance_company);
		params.put("insurance_tel", tv_insurance_tel);
		params.put("insurance_date", tv_insurance_date);
		params.put("insurance_no", tv_insurance_no);
		params.put("annual_inspect_date", tv_annual_inspect_date);
		params.put("maintain_company", tv_maintain_company);
		params.put("maintain_tel", tv_maintain_tel);
		params.put("maintain_last_mileage", tv_maintain_last_mileage);
		params.put("maintain_last_date", tv_maintain_last_date);
		params.put("maintain_next_mileage", tv_maintain_next_mileage);
		params.put("mileage", tv_mileage);
		params.put("gas_no", tv_gas_no);
		params.put("fuel_ratio", tv_fuel_ratio);
		params.put("fuel_price", tv_fuel_price);
		params.put("buy_date", tv_buy_date);

		vehicleApi.create(params, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.i("Vehicle", response);
				try {
					JSONObject obj = new JSONObject(response);
					
					if("0".equals(obj.getString("status_code"))){
						T.showShort(VehicleCreateActivity.this, "创建车辆成功！");
					}else{
						T.showShort(VehicleCreateActivity.this, "创建车辆失败！");
					}
				} catch (JSONException e){
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
