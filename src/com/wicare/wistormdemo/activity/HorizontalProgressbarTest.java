package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.ui.WHorizontalProgressBarWithNumber;
import com.wicare.wistormdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author Wu
 * 水平进度条
 */
public class HorizontalProgressbarTest extends Activity{
	
	private static final int PROGRESS_UPDATE = 1;
	
	WHorizontalProgressBarWithNumber progressbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_horizontal_progressbar);
		
		progressbar = (WHorizontalProgressBarWithNumber)findViewById(R.id.h_progressbar);
		progressbar.setMax(100);//设置进度最大值
		progressbar.setProgressBarColor(Color.BLUE);//设置进度颜色
		
		mHandler.sendEmptyMessage(PROGRESS_UPDATE);//更新进度条
	}


	@SuppressLint("HandlerLeak") 
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int progress = progressbar.getProgress();
			progressbar.setProgress(++progress);
			if (progress >= 100) {
				mHandler.removeMessages(PROGRESS_UPDATE);
			}
			mHandler.sendEmptyMessageDelayed(PROGRESS_UPDATE, 100);
		};
	};
}
