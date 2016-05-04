package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.ui.WArcProView;
import com.wicare.wistorm.ui.WCircleProView;
import com.wicare.wistormdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ProgressViewActivity extends Activity {
	
	static final int UPDATA_UI = 0;
	
	private WArcProView arcView;//弧形进度UI
	private WCircleProView circleView;//圆形进度UI
	private WCircleProView circleViewPointRun;
	
	private SeekBar seekBar;
	private TextView tvArcProgress;
	private TextView tvCircleProgress;
	private TextView tvCircleProgressRunning;
	
	private String tv;
	
	boolean pointRunning = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_view);
		//内环形也动态绘制进度
        arcView = (WArcProView)findViewById(R.id.v_arc);
//        arcView.enableInsideScaleRing(true);
      //内环形也动态绘制进度
        circleView = (WCircleProView)findViewById(R.id.v_circle);
        circleView.enableInsideScaleRing(true);
      //只有外层圆环上的点转动
        circleViewPointRun = (WCircleProView)findViewById(R.id.v_circle_running);
        circleViewPointRun.enableScaleRingPointRun(false);
        
        tvArcProgress = (TextView)findViewById(R.id.tv_arc);
        tvCircleProgress = (TextView)findViewById(R.id.tv_circle);
        tvCircleProgressRunning = (TextView)findViewById(R.id.tv_circle_running);
        
        tvCircleProgressRunning.setText("GO");
        
        
        tvCircleProgressRunning.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pointRunning = !pointRunning;
				if(pointRunning){
					circleViewPointRun.enableScaleRingPointRun(true);
					tvCircleProgressRunning.setText("STOP");
				}else{
					circleViewPointRun.enableScaleRingPointRun(false);
					tvCircleProgressRunning.setText("GO");
				}
				
			}
		});
        
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
