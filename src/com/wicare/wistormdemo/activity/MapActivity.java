package com.wicare.wistormdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.wicare.wistorm.toolkit.WTrace;
import com.wicare.wistormdemo.R;

/**
 * MapActivity
 * 
 * @author c
 * @date 2015-10-23
 */
public class MapActivity extends WTrace {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/**
		 * 使用自定义布局页面，MapView 的 id = bmapView 
		 * 在父onCreate之前调用
		 */
		super.setCustomView(R.layout.activity_map);
		super.onCreate(savedInstanceState);
		// 1，初始化轨迹播放，添加监听
		super.initTrace();
		// 初始化位置监听
		super.initLocation();
	}

	
	/**
	 * 定位返回
	 */
	@Override
	public void onReceiveLocation(BDLocation location) {

		StringBuffer sb = new StringBuffer(256);
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\nradius : ");
		sb.append(location.getRadius());

		Log.i("MapActivity", "定位" + sb.toString());
		super.setMyLocation(location);

		// 测试设置地图中心坐标
		LatLng latLng = new LatLng(location.getLatitude(),
				location.getLongitude());

		/*---------------测试内容----------------*/
		// 测试滚到地图中心
		animateMapCenter(latLng);
		// 测试地理反位置编码
		reverseGeoCode(latLng);
		// 测试地理位置编码
		getGeoCode("深圳", "西丽崇文花园");

	}

	@Override
	protected void onResume() {
		super.onResume();
		// 2，开始播放
		super.startTrack(getData());
	}

	/**
	 * getData 自定义轨迹播放点
	 * 
	 * @return
	 */
	public List getData() {
		List list = new ArrayList();
		for (int i = 0; i < 100; i++) {
			float j = i * 0.12f;
			float z = i * 0.111f;
			LatLng latLng = new LatLng(26.597991586429284 + j,
					106.67043370089488 + z);
			list.add(latLng);
		}
		return list;

	}

	@Override
	public void onTrackListener(int index) {
		Log.i("MapActivity", index + "");
		Log.i("MapActivity", "onTrackListener1");
		if (index == 10) {
			// 3，暂停播放
			pauseTrack();
			// 四秒后继续播放
			SystemClock.sleep(2000);
			startTrack();
		}

		Log.i("MapActivity", "onTrackListener2");

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

}
