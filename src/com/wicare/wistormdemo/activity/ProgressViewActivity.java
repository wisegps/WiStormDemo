package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.ui.WArcProView;
import com.wicare.wistorm.ui.WCircleProView;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ProgressViewActivity extends Activity {
	
	static final int UPDATA_UI = 0;
	
	private WArcProView arcView;//弧形进度UI
	private WCircleProView circleView;//圆形进度UI
	private SeekBar seekBar;
	private TextView tvArcProgress;
	private TextView tvCircleProgress;
	private String tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_view);
	       
        arcView = (WArcProView)findViewById(R.id.v_arc);
//        arcView.enableInsideScaleRing(true);//内环形也动态绘制进度
        
        circleView = (WCircleProView)findViewById(R.id.v_circle);
        circleView.enableInsideScaleRing(true);//内环形也动态绘制进度
        
        tvArcProgress = (TextView)findViewById(R.id.tv_arc);
        tvCircleProgress = (TextView)findViewById(R.id.tv_circle);
        
        seekBar = (SeekBar)findViewById(R.id.seek_bar) ;
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(listener); 
	}
	
  
    
    /**
     * seekbar监听
     */
    OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			circleView.setProgressOutsideRingRotateDegree(progress);
			circleView.setProgressInitInsideRingRotateDegree(progress);
			arcView.setProgressOutsideRingRotateDegree(progress);
//			arcView.setProgressInitInsideRingRotateDegree(progress);
			tv = String.valueOf(progress);
			mHandler.sendEmptyMessage(UPDATA_UI);
		}
	};
	

	  /**
     * Handler 
     */

	private Handler mHandler = new Handler() {

		@Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            
            switch (msg.what) {
            case UPDATA_UI:
            	tvArcProgress.setText(tv);
            	tvCircleProgress.setText(tv);
            	break;
            }
        }
    };	
        	

}
