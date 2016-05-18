package com.wicare.wistormdemo.activity.apitest.user;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.wicare.wistorm.api.WUserApi;
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
 * @author Wu
 *
 * 创建客户信息
 */
public class CreateCustomerActivity extends Activity{

	private EditText et_mode,
					et_seller_id,
					et_cust_type,
					et_mobile,
					et_obj_name,
					et_car_brand_id,
					et_car_brand,
					et_car_series_id,
					et_car_series,
					et_car_type_id,
					et_car_type,
					et_frame_no,
					et_mileage,
					et_if_arrive,
					et_business_type,
					et_business_content;
	
	private String  mode,
					seller_id,
					cust_type,
					mobile,
					obj_name,
					car_brand_id,
					car_brand,
					car_series_id,
					car_series,
					car_type_id,
					car_type,
					frame_no,
					mileage,
					if_arrive,
					business_type,
					business_content;

	private Context mContext;
	private WUserApi userApi;
	private AppApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_customer);
		app = (AppApplication)getApplication();
		mContext = CreateCustomerActivity.this;
		initView();
		init();
	}
	
	/**
	 * wistorm api接口网络请求初始化
	 */
	private void init(){
		userApi = new WUserApi();
		BaseVolley.init(mContext);
	}
	
	public void createCustomer(View view){
		mode = et_mode.getText().toString().trim();
		seller_id = et_seller_id.getText().toString().trim();
		cust_type = et_cust_type.getText().toString().trim();
		mobile = et_mobile.getText().toString().trim();
		obj_name = et_obj_name.getText().toString().trim();
		car_brand_id = et_car_brand_id.getText().toString().trim();
		car_brand = et_car_brand.getText().toString().trim();
		car_series_id = et_car_series_id.getText().toString().trim();
		car_series = et_car_series.getText().toString().trim();
		car_type_id = et_car_type_id.getText().toString().trim();
		car_type = et_car_type.getText().toString().trim();
		frame_no = et_frame_no.getText().toString().trim();
		mileage = et_mileage.getText().toString().trim();
		if_arrive = et_if_arrive.getText().toString().trim();
		business_type = et_business_type.getText().toString().trim();
		business_content = et_business_content.getText().toString().trim();
		if(TextUtils.isEmpty(seller_id)){
			T.showShort(mContext, "请填写完信息");
			return;
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",app.access_token);
		params.put("mode", mode);
		params.put("seller_id", seller_id);
		params.put("cust_type", cust_type);
		params.put("mobile", mobile);
		params.put("obj_name", obj_name);
		params.put("frame_no", frame_no);
		params.put("car_brand_id", car_brand_id);
		params.put("car_brand", car_brand);
		params.put("car_series_id", car_series_id);
		params.put("car_series", car_series);
		params.put("car_type_id", car_type_id);
		params.put("car_type", car_type);
		params.put("mileage", mileage);
		params.put("if_arrive", if_arrive);
		params.put("business_type", business_type);
		params.put("business_content", business_content);

		userApi.create(params, new OnSuccess() {
			
			@Override
			protected void onSuccess(String response) {
				L.d("USER", response);
//				{"status_code":0,"cust_id":1453,"obj_id":3185}
//				{"status_code":0,"cust_id":1454,"obj_id":3186}
//				{"status_code":0,"cust_id":1455,"obj_id":3187}
//				{"status_code":0,"cust_id":1456,"obj_id":3188}


				try {
					JSONObject obj = new JSONObject(response);
					if("0".equals(obj.getString("status_code"))){
						T.showShort(mContext, "创建客户成功");
					}else{
						T.showShort(mContext, "创建客户失败");
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		}, new OnFailure() {
			
			@Override
			protected void onFailure(VolleyError error) {
			}
		});	
	}
	
	private void initView(){
		et_mode = (EditText)findViewById(R.id.et_mode);
		et_seller_id = (EditText)findViewById(R.id.et_seller_id);
		et_cust_type = (EditText)findViewById(R.id.et_cust_type);
		et_mobile = (EditText)findViewById(R.id.et_mobile);
		et_obj_name = (EditText)findViewById(R.id.et_obj_name);
		et_car_brand_id = (EditText)findViewById(R.id.ed_car_brand_id);
		et_car_brand = (EditText)findViewById(R.id.ed_car_brand);
		et_car_series_id = (EditText)findViewById(R.id.ed_car_series_id);
		et_car_series = (EditText)findViewById(R.id.ed_car_series);
		et_car_type_id = (EditText)findViewById(R.id.ed_car_type_id);
		et_car_type = (EditText)findViewById(R.id.ed_car_type);
		et_frame_no = (EditText)findViewById(R.id.ed_frame_no);
		et_mileage = (EditText)findViewById(R.id.ed_mileage);
		et_if_arrive = (EditText)findViewById(R.id.ed_if_arrive);
		et_business_type = (EditText)findViewById(R.id.ed_business_type);
		et_business_content = (EditText)findViewById(R.id.ed_business_content);
	}
	
}
