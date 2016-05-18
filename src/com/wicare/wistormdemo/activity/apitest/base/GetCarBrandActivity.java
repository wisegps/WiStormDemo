package com.wicare.wistormdemo.activity.apitest.base;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WBaseApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.OnFailure;
import com.wicare.wistorm.http.OnSuccess;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.app.AppApplication;
import com.wicare.wistormdemo.utils.L;

/**
 * @author W
 *
 * 获取车辆品牌列表
 */
public class GetCarBrandActivity extends Activity {

	private Button btn_get;
	private TextView tv_response;
	private WBaseApi baseApi;
	private AppApplication app;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_car_brand_list);
		app = (AppApplication)getApplication();
		mContext = GetCarBrandActivity.this;
		btn_get = (Button)findViewById(R.id.btn_get);
		tv_response = (TextView)findViewById(R.id.tv_response);
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		baseApi = new WBaseApi();
		BaseVolley.init(mContext);
	}
	
	
	public void getCarBrand(View view){
		
		HashMap<String, String> params = new HashMap<String, String>();		
		params.put("access_token", app.access_token);
		params.put("id", ">0");//>0获取所有的车辆品牌
		params.put("page", "t_spell");
		params.put("sorts", "t_spell");//按照首字母排列
		params.put("limit", "-1");//-1 没有限制
		//返回字段
		String fields = "pid,name,url_icon,t_spell";
		baseApi.getBrands(params,fields, new OnSuccess() {
			
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
