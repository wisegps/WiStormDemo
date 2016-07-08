package com.wicare.wistormdemo.activity.apitest.vehicle;

import java.util.HashMap;

import com.android.volley.VolleyError;
import com.baidu.mapapi.map.Text;
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
import android.widget.Toast;

/**   
 * @ClassName:  VehicleGetListActivity   
 * @Description:获取车辆列表   
 * @author: wu 
 * @date:   2016-5-5 上午10:17:14   
 *      
 */
public class VehicleGetListActivity extends Activity {

	private Context mContext;
	private WVehicleApi vehicleApi;
	private AppApplication app;
	
	private TextView tv_response;
	private EditText et_seller_id,et_sorts,et_limit;
	private String seller_id, sorts,limit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_list);
		mContext = VehicleGetListActivity.this;
		app = (AppApplication)getApplication();
		initView();
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		vehicleApi = new WVehicleApi(mContext);
		BaseVolley.init(VehicleGetListActivity.this);
	}
	
	
	private void initView(){
		et_seller_id = (EditText)findViewById(R.id.et_seller_id);
		et_sorts     = (EditText)findViewById(R.id.et_sorts);
		et_limit     = (EditText)findViewById(R.id.et_limit);
		tv_response  = (TextView)findViewById(R.id.tv_response);
	}
	
	
	
	public void getVehicleList(View view){
		seller_id = et_seller_id.getText().toString().trim();
		sorts = et_sorts.getText().toString().trim();
		limit = et_limit.getText().toString().trim();
		
		if(TextUtils.isEmpty(seller_id)){
			T.showShort(mContext, "seller_id 不能为空！！");
			return;
		}
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("access_token", app.access_token);
//		params.put("seller_id", seller_id);//商户ID  
		
		params.put("cust_id", seller_id);//商户ID
		
		
		
		params.put("sorts", sorts);
		params.put("limit", limit);
		String fields = "nick_name,cust_name,obj_id,cust_id,obj_name,device_id,car_brand_id," +
				"car_brand,car_series_id,car_series,car_type_id,car_type,frame_no," +
				"maintain_last_mileage,mileage,arrive_count,evaluate_count,last_arrive_time," +
				"business_status,evaluate_level";
		
		vehicleApi.list(params, fields, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				
				L.i("Vehicle", response);
				tv_response.setText(response);
				
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
			}
		});
	}
	
	
	
	
}
