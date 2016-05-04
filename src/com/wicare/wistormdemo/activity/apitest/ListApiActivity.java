package com.wicare.wistormdemo.activity.apitest;

import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.widget.APIListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListApiActivity extends Activity {

	
	private String[] listTitles = {"基础接口","用户","车辆","设备终端","业务",};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_api);
		APIListAdapter adapter = new APIListAdapter(ListApiActivity.this, listTitles);
		ListView lv = (ListView)findViewById(R.id.lv_api);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(onItemClickListener);
	}
	
	
	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(ListApiActivity.this,ListApiMethodActivity.class);
			intent.putExtra("api_type", listTitles[position]);
			startActivity(intent);
		}
	};
	
}
