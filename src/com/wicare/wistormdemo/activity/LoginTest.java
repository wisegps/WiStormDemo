package com.wicare.wistormdemo.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.android.common.logging.Log;
import com.wicare.wistorm.toolkit.WLoginActivity;
import com.wicare.wistormdemo.R;

/**   
 * @ClassName:  LoginTest   
 * @Description:TODO(登陆功能)   
 * @author: wu  
 * @date:   2016-4-21 下午3:03:17   
 *      
 */
public class LoginTest extends WLoginActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
	}

	@Override
	protected void onClickRegister() {
		// TODO Auto-generated method stub
		Toast.makeText(LoginTest.this, "注册账号", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onClickUpdataPassword() {
		// TODO Auto-generated method stub
		Toast.makeText(LoginTest.this, "找回密码", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void setUpView() {
		// TODO Auto-generated method stub
		setLogo(R.drawable.ic_launcher);
		setBackgroundColor(getResources().getColor(R.color.blue_press));
	}

	@Override
	protected void onLoginSuccess(String response) {
		// TODO Auto-generated method stub
		Toast.makeText(LoginTest.this, "登陆成功", Toast.LENGTH_SHORT).show();
		Log.d("Login",  "登陆成功" + response);
	}
	
	@Override
	protected void onLoginFail() {
		// TODO Auto-generated method stub
		Toast.makeText(LoginTest.this, "登陆失败", Toast.LENGTH_SHORT).show();
	}

}
