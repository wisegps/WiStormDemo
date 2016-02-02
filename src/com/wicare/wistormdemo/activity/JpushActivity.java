package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.toolkit.WJpush;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * JpushActivity
 * 
 * @author c
 * @date 2015-11-6
 */
public class JpushActivity extends Activity implements OnClickListener {

	private EditText editName;

	private Button btnSetting;

	private TextView tvMessage;

	public static String MESSAGE_RECEIVED_ACTION = "android.intent.action.MSG_BROADCAST";
	private WJpush jpush;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jpush);
		jpush = new WJpush(this);
		editName = (EditText) findViewById(R.id.editName);
		btnSetting = (Button) findViewById(R.id.btnSetting);
		tvMessage = (TextView) findViewById(R.id.tvMessage);
		btnSetting.setOnClickListener(this);
		// 注册一个广播接收器，接收通知传过来的消息
		MyReceiver receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(receiver, filter);
	}

	class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra("msg");
			tvMessage.setText(msg);

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		jpush.resume();
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.btnSetting) {
			
			String alias = editName.getText().toString();
			Log.i("JpushActivity", alias);
			jpush.setAlias(alias);
		}
	}

}
