package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.ui.WInputField;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.os.Bundle;

public class InputFeildActivity extends Activity {
	
	private WInputField inputFeild;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_input_feild);
		inputFeild = (WInputField)findViewById(R.id.et_inputfeild);
		
	}

}
