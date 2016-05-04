package com.wicare.wistormdemo.activity.apitest;

import com.wicare.wistormdemo.R;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListApiMethodActivity extends Activity {

	private String[] base_api_list     = {"获取车品牌表","获取车系列列表","获取车款列表","获取天气信息"};
	private String[] user_api_list     = {"获取token","创建客户信息","绑定客户","更新客户信息","获取客户信息","获取客户列表"};
	private String[] vehicle_api_list  = {"创建车辆信息","更新车辆信息","获取车辆信息","获取车辆列表","删除车辆"};
	private String[] business_api_list = {"创建业务信息","更新业务","更新业务离店状态","获取业务列表","获取业务统计"};
	private String[] device_api_list   = {"获取设备列表","获取电压曲线及水温曲线","更新设备信息"};
	
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
				
				break;
			case 1:
				
				break;
			case 2:
				if(position == 0){
					Intent i_vehicle_create = new Intent(ListApiMethodActivity.this,VehicleCreateActivity.class);
					startActivity(i_vehicle_create);
				}else if(position == 1){
					Intent i_vehicle_create = new Intent(ListApiMethodActivity.this,VehicleUpdataActivity.class);
					startActivity(i_vehicle_create);
				}else if(position == 2){
					Intent i_vehicle_create = new Intent(ListApiMethodActivity.this,VehicleGetDataActivity.class);
					startActivity(i_vehicle_create);
				}else if(position == 3){
					Intent i_vehicle_create = new Intent(ListApiMethodActivity.this,VehicleGetListActivity.class);
					startActivity(i_vehicle_create);
				}else if(position == 4){
					Intent i_vehicle_create = new Intent(ListApiMethodActivity.this,VehicleDeleteActivity.class);
					startActivity(i_vehicle_create);
				}
				break;
			case 3:
	
				break;
			case 4:
	
				break;
	
			}
			
			Toast.makeText(ListApiMethodActivity.this, "id=" + id, Toast.LENGTH_SHORT).show();
			
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
			adapter = new APIListAdapter(ListApiMethodActivity.this, business_api_list);
		}else if(api_type.equals("业务")){
			TYPE_API = 4;
			adapter = new APIListAdapter(ListApiMethodActivity.this, device_api_list);
		}
	}
	
}
