package com.wicare.wistormdemo.activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.baidu.navisdk.util.cache.ImageCache;
import com.wicare.wistorm.toolkit.LruImageCache;
import com.wicare.wistorm.toolkit.WCloud;
import com.wicare.wistormdemo.R;

/**
 * WCacheActivity 实现下载图片缓存功能
 * 
 * @author c
 * @date 2015-11-16
 */
public class WCacheActivity extends Activity implements OnClickListener,
		Callback {

	private RequestQueue queue ;
	private ImageView imgView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wcache);
		queue = Volley.newRequestQueue(this);
		imgView = (ImageView) findViewById(R.id.iv_Cache);
	}

	/*
	 * @param arg0
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.btnDownload:
			download();
			break;
		}

	}

	/**
	 * 下载文件后显示 download
	 */
	public void download() {
		/**
		 * 显示
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				LruImageCache imageCache = LruImageCache.instance(WCacheActivity.this);
				ImageLoader loader = new ImageLoader(queue, imageCache);
				ImageListener listener = ImageLoader.getImageListener(imgView, R.drawable.ic_launcher, R.drawable.ic_launcher);
				loader.get("http://www.bibibaba.cn/statics/images/pictures/2-5-2.jpg", listener);
			}

		}, 1);
	}

	/*
	 * @param arg0
	 * 
	 * @return
	 * 
	 * @see android.os.Handler.Callback#handleMessage(android.os.Message)
	 */
	@Override
	public boolean handleMessage(Message arg0) {

		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

}
