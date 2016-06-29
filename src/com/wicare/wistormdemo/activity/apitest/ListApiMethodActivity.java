package com.wicare.wistormdemo.activity.apitest;

import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.activity.apitest.base.GetCarBrandActivity;
import com.wicare.wistormdemo.activity.apitest.base.GetCarSeriesActivity;
import com.wicare.wistormdemo.activity.apitest.base.GetCarTypeActivity;
import com.wicare.wistormdemo.activity.apitest.device.DeviceObdDataListActivity;
import com.wicare.wistormdemo.activity.apitest.device.DeviceListActivity;
import com.wicare.wistormdemo.activity.apitest.device.GetDeviceActivity;
import com.wicare.wistormdemo.activity.apitest.device.UpdataDeviceActivity;
import com.wicare.wistormdemo.activity.apitest.user.BindCustomerActivity;
import com.wicare.wistormdemo.activity.apitest.user.CreateCustomerActivity;
import com.wicare.wistormdemo.activity.apitest.user.GetCustomerDataActivity;
import com.wicare.wistormdemo.activity.apitest.user.GetCustomerListActivity;
import com.wicare.wistormdemo.activity.apitest.user.GetTokeActivity;
import com.wicare.wistormdemo.activity.apitest.user.UpdataCustomerActivity;
import com.wicare.wistormdemo.activity.apitest.vehicle.VehicleCreateActivity;
import com.wicare.wistormdemo.activity.apitest.vehicle.VehicleGetDataActivity;
import com.wicare.wistormdemo.activity.apitest.vehicle.VehicleDeleteActivity;
import com.wicare.wistormdemo.activity.apitest.vehicle.VehicleGetListActivity;
import com.wicare.wistormdemo.activity.apitest.vehicle.VehicleUpdataActivity;
import com.wicare.wistormdemo.widget.APIListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListApiMethodActivity extends Activity {

	private String[] base_api_list     = {"获取车品牌表","获取车系列列表","获取车款列表","获取天气信息"};
	private String[] user_api_list     = {"获取token","创建客户信息","绑定客户","更新客户信息","获取客户信息","获取客户列表"};
	private String[] vehicle_api_list  = {"创建车辆信息","更新车辆信息","获取车辆信息","获取车辆列表","删除车辆"};
	private String[] business_api_list = {"创建业务信息","更新业务","更新业务离店状态","获取业务列表","获取业务统计"};
	private String[] device_api_list   = {"获取设备信息","获取设备列表","获取电压曲线及水温曲线","更新设备信息"};
	
	private int TYPE_API = 0;
	private APIListAdapter adapter;
	private String api_type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_api);
		
		Intent intent = getIntent();
		api_type = intent.getStringExtra("api_type");
		setApiType(api_type);
		ListView lv = (ListView)findViewById(R.id.lv_api);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(onItemClickListener);
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			switch (TYPE_API) {
			case 0:
				if(position == 0){
					Intent i_brand= new Intent(ListApiMethodActivity.this,GetCarBrandActivity.class);
					startActivity(i_brand);
				}else if(position == 1){
					Intent i_series= new Intent(ListApiMethodActivity.this,GetCarSeriesActivity.class);
					startActivity(i_series);
				}else if(position == 2){
					Intent i_type= new Intent(ListApiMethodActivity.this,GetCarTypeActivity.class);
					startActivity(i_type);
				}
				break;
			case 1:
				if(position == 0){
					Intent i_token = new Intent(ListApiMethodActivity.this,GetTokeActivity.class);
					startActivity(i_token);
				}else if(position == 1){
					Intent i_create = new Intent(ListApiMethodActivity.this,CreateCustomerActivity.class);
					startActivity(i_create);
				}else if(position == 2){
					Intent i_bind = new Intent(ListApiMethodActivity.this,BindCustomerActivity.class);
					startActivity(i_bind);
				}else if(position == 3){
					Intent i_updata = new Intent(ListApiMethodActivity.this,UpdataCustomerActivity.class);
					startActivity(i_updata);
				}else if(position == 4){
					Intent i_get = new Intent(ListApiMethodActivity.this,GetCustomerDataActivity.class);
					startActivity(i_get);
				}else if(position == 5){
					Intent i_get_list = new Intent(ListApiMethodActivity.this,GetCustomerListActivity.class);
					startActivity(i_get_list);
				}
				break;
			case 2:
				if(position == 0){
					Intent i_vehicle_create = new Intent(ListApiMethodActivity.this,VehicleCreateActivity.class);
					startActivity(i_vehicle_create);
				}else if(position == 1){
					Intent i_vehicle_updata = new Intent(ListApiMethodActivity.this,VehicleUpdataActivity.class);
					startActivity(i_vehicle_updata);
				}else if(position == 2){
					Intent i_vehicle_get = new Intent(ListApiMethodActivity.this,VehicleGetDataActivity.class);
					startActivity(i_vehicle_get);
				}else if(position == 3){
					Intent i_vehicle_list = new Intent(ListApiMethodActivity.this,VehicleGetListActivity.class);
					startActivity(i_vehicle_list);
				}else if(position == 4){
					Intent i_vehicle_delete = new Intent(ListApiMethodActivity.this,VehicleDeleteActivity.class);
					startActivity(i_vehicle_delete);
				}
				break;
			case 3:
				if(position == 0){
					Intent i_get_device = new Intent(ListApiMethodActivity.this,GetDeviceActivity.class);
					startActivity(i_get_device);
				}else if(position == 1){
					Intent i_list_device = new Intent(ListApiMethodActivity.this,DeviceListActivity.class);
					startActivity(i_list_device);
				}else if(position == 2){
					Intent i_list_device = new Intent(ListApiMethodActivity.this,DeviceObdDataListActivity.class);
					startActivity(i_list_device);
				}else if(position == 3){
					Intent i_updata_device = new Intent(ListApiMethodActivity.this,UpdataDeviceActivity.class);
					startActivity(i_updata_device);
				}
				break;
			case 4:
	
				break;
	
			}	
		}
	};
	
	private void setApiType(String api_type){
		if(api_type.equals("基础接口")){
			TYPE_API = 0;
			adapter = new APIListAdapter(ListApiMethodActivity.this, base_api_list);
		}else if(api_type.equals("用户")){
			TYPE_API = 1;
			adapter = new APIListAdapter(ListApiMethodActivity.this, user_api_list);
		}else if(api_type.equals("车辆")){
			TYPE_API = 2;
			adapter = new APIListAdapter(ListApiMethodActivity.this, vehicle_api_list);
		}else if(api_type.equals("设备终端")){
			TYPE_API = 3;
			adapter = new APIListAdapter(ListApiMethodActivity.this, device_api_list);
		}else if(api_type.equals("业务")){
			TYPE_API = 4;
			adapter = new APIListAdapter(ListApiMethodActivity.this, business_api_list);
		}
	}
	
}
