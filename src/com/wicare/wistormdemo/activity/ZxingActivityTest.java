package com.wicare.wistormdemo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.wicare.wistorm.toolkit.WZxingActivity;
import com.wicare.wistorm.ui.wzxing.WZxingBitmapUtil;
import com.wicare.wistormdemo.R;


public class ZxingActivityTest extends Activity implements OnClickListener {
	
	private EditText resultZxing;
	private Button scanZxing;
	private Button createZxing;
	private ImageView zxingResultImg;
	protected int mScreenWidth ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wzxing_activity);
		
		resultZxing = (EditText) findViewById(R.id.scan_result) ;
		scanZxing = (Button) findViewById(R.id.btn_scan);
		scanZxing.setOnClickListener(this);
		createZxing = (Button) findViewById(R.id.btn_create_zxing);
		createZxing.setOnClickListener(this);
		zxingResultImg = (ImageView) findViewById(R.id.iv_zixng_result);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_scan:
			// 调用ZXIng开源项目源码  扫描二维码
			Intent openCameraIntent = new Intent(ZxingActivityTest.this,
					WZxingActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;
		case R.id.btn_create_zxing:
			createZxingResultImg();
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 取得返回信息
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultZxing.setText(scanResult);
		}
	}
	
	private void createZxingResultImg(){
		String uri = resultZxing.getText().toString();
		if(uri.equals("")){
			Dialog alertDialog = new AlertDialog.Builder(this) 
				.setTitle("警告")
	            .setMessage("二维码信息不能为空")
	            .create(); 
	        alertDialog.show(); 
			return;
		}
		Bitmap bitmap;
		try {
			bitmap = WZxingBitmapUtil.createQRCode(uri, mScreenWidth);
			if(bitmap != null){
				zxingResultImg.setImageBitmap(bitmap);
			}
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

}
