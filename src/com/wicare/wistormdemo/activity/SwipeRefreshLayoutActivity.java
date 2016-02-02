package com.wicare.wistormdemo.activity;

import java.util.ArrayList;
import java.util.List;

import com.wicare.wistormdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author wu
 * 
 * SwipeRefreshLayout 下拉刷新
 *
 */
public class SwipeRefreshLayoutActivity extends Activity implements OnRefreshListener {
	
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView mList;
	private List<String> mData = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private int num = 0;
	
	@SuppressLint("ResourceAsColor") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swiperefreshlayout_activity);
		mList = (ListView)findViewById(R.id.lv);
		initData();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
		mList.setAdapter(adapter);
		swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);

       /* 1、setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener):设置手势滑动监听器。

        2、setProgressBackgroundColor(int colorRes):设置进度圈的背景色。

        3、setColorSchemeResources(int… colorResIds):设置进度动画的颜色。

        4、setRefreshing(Boolean refreshing):设置组件的刷洗状态。

        5、setSize(int size):设置进度圈的大小，只有两个值：DEFAULT、LARGE*/

        swipeRefreshLayout.setColorSchemeResources(R.color.color_swipe_1,
                R.color.color_swipe_2, R.color.color_swipe_3, R.color.color_swipe_4);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.color_swipe_background);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setProgressViewOffset(true,0,100);//设置 view的位置偏移
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    /**
     * Handler
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

	@Override
	public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {	
				mData.add("SwipeRefreshLayout下拉刷新："  + num);
				num++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
     
    }

		
	/**
	 * 初始化数据
	 */
	private void initData(){
		for(num=0;num<10;num++){
			mData.add("SwipeRefreshLayout下拉刷新：" + num);
		}
	}
	
}
