package com.wicare.wistormdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.wicare.wistorm.ui.WInputField;
import com.wicare.wistormdemo.R;

public class SearchBarTest extends Activity implements OnClickListener {
	
	/** 自定义的搜索框 */
	private WInputField etSearch = null;
	/** 返回 */
	private ImageView ivBack = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_bar);
		
		ivBack   = (ImageView) findViewById(R.id.iv_back);
		ivBack .setOnClickListener(this);
		etSearch = (WInputField) findViewById(R.id.et_search);
	}

	@Override
	public void onClick(View v) {
		this.finish();
	}
	
	
}
