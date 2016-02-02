package com.wicare.wistormdemo.activity;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

import com.wicare.wistorm.toolkit.WJpushReceiver;

/**
 * MJpushReceiver
 * 
 * @author c
 * @date 2015-11-6
 */
public class MJpushReceiver extends WJpushReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		super.onReceive(context, intent);

		//接收自定义消息
		if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			// 接收到推送下来的自定义消息
			String msg = intent.getExtras().getString(
					JPushInterface.EXTRA_MESSAGE);
			Intent msgIntent = new Intent();
			msgIntent.setAction(JpushActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra("msg", "接收到的自定义消息："+msg);
			context.sendBroadcast(msgIntent);

		//接收到通知
		}else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

        	String result = intent.getExtras().getString(JPushInterface.EXTRA_EXTRA);
        	Intent msgIntent = new Intent();
			msgIntent.setAction(JpushActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra("msg", "接收到的通知："+result);
			context.sendBroadcast(msgIntent);
		}
	}
}
