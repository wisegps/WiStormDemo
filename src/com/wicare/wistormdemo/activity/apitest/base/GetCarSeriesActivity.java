package com.wicare.wistormdemo.activity.apitest.base;

import java.util.HashMap;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WBaseApi;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Wu
 * 
 * 获取车系列表
 */
public class GetCarSeriesActivity extends Activity {
	private Button btn_get;
	private TextView tv_response;
	private WBaseApi baseApi;
	private AppApplication app;
	private Context mContext;
	private EditText et_pid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_car_series_types_list);
		app = (AppApplication)getApplication();
		mContext = GetCarSeriesActivity.this;
		initView();
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		baseApi = new WBaseApi();
		BaseVolley.init(mContext);
	}
	
	/**
	 * 初始化UI 
	 */
	private void initView(){
		btn_get = (Button)findViewById(R.id.btn_get);
		tv_response = (TextView)findViewById(R.id.tv_response);
		btn_get.setText("获取车系列");
		et_pid = (EditText)findViewById(R.id.et_pid);
		et_pid.setHint("车品牌id(如：奥迪品牌ID：9)");
	}
	
	
	public void getlist(View view){
		String pid = et_pid.getText().toString().trim();
		HashMap<String, String> params = new HashMap<String, String>();		
		params.put("access_token", app.access_token);
		params.put("pid", pid);//车系列的id (车系列id就是车品牌返回数据中的 id)
		params.put("page", "go_name");
		params.put("sorts", "go_name");//按照首字母排列
		params.put("limit", "-1");//-1 没有限制
		//返回字段
		String fields = "pid,name,show_name,go_id,go_name";
		baseApi.getSeries(params,fields, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				// TODO Auto-generated method stub
				L.d("BASE", response);
				tv_response.setText(response);
			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});		
	}	
}
