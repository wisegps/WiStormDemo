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

import com.wicare.wistorm.toolkit.WCloud;
import com.wicare.wistormdemo.R;

/**
 * WCloadActivity 实现上传下载图片功能
 * 
 * @author c
 * @date 2015-11-16
 */
public class WCloudActivity extends Activity implements OnClickListener,
		Callback {

	WCloud wcloud;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wcload);
		wcloud = new WCloud(new Handler(this));
	}

	/*
	 * @param arg0
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.btnUpload:
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, 1);
			break;
		case R.id.btnDownload:
			download();
			break;
		}

	}

	/**
	 * 下载文件并2秒后显示 download
	 */
	public void download() {
		final String sdPath = Environment.getExternalStorageDirectory()
				.getPath() + "/a.png";
		wcloud.download("test1c", sdPath);

		/**
		 * 显示
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Bitmap bitmap = BitmapFactory.decodeFile(sdPath);
				ImageView img = (ImageView) findViewById(R.id.iv_cloud);
				img.setImageBitmap(bitmap);
			}

		}, 2000);
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

		if (requestCode == 1 && resultCode == Activity.RESULT_OK
				&& null != data) {

			/*
			 * 获取所选文件路径
			 */
			Uri uri = data.getData();
			String filePath = Uri2Path.getPath(this, uri);
			/*
			 * 首先上传图片
			 */
			wcloud.uploadImg("test1c", filePath);
			Toast.makeText(this, "上传中。。。。", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 
	 * Uri2Path
	 * 
	 * @author c 把uri路径转换成文件路径
	 * @date 2015-11-17
	 */
	static class Uri2Path {
		/**
		 * Get a file path from a Uri. This will get the the path for Storage
		 * Access Framework Documents, as well as the _data field for the
		 * MediaStore and other file-based ContentProviders.
		 * 
		 * @param context
		 *            The context.
		 * @param uri
		 *            The Uri to query.
		 * @author paulburke
		 */
		@SuppressLint("NewApi")
		public static String getPath(final Context context, final Uri uri) {

			final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

			// DocumentProvider
			if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
				// ExternalStorageProvider
				if (isExternalStorageDocument(uri)) {
					final String docId = DocumentsContract.getDocumentId(uri);
					final String[] split = docId.split(":");
					final String type = split[0];

					if ("primary".equalsIgnoreCase(type)) {
						return Environment.getExternalStorageDirectory() + "/"
								+ split[1];
					}

					// TODO handle non-primary volumes
				}
				// DownloadsProvider
				else if (isDownloadsDocument(uri)) {

					final String id = DocumentsContract.getDocumentId(uri);
					final Uri contentUri = ContentUris.withAppendedId(
							Uri.parse("content://downloads/public_downloads"),
							Long.valueOf(id));

					return getDataColumn(context, contentUri, null, null);
				}
				// MediaProvider
				else if (isMediaDocument(uri)) {
					final String docId = DocumentsContract.getDocumentId(uri);
					final String[] split = docId.split(":");
					final String type = split[0];

					Uri contentUri = null;
					if ("image".equals(type)) {
						contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
					} else if ("video".equals(type)) {
						contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
					} else if ("audio".equals(type)) {
						contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
					}

					final String selection = "_id=?";
					final String[] selectionArgs = new String[] { split[1] };

					return getDataColumn(context, contentUri, selection,
							selectionArgs);
				}
			}
			// MediaStore (and general)
			else if ("content".equalsIgnoreCase(uri.getScheme())) {
				return getDataColumn(context, uri, null, null);
			}
			// File
			else if ("file".equalsIgnoreCase(uri.getScheme())) {
				return uri.getPath();
			}

			return null;
		}

		/**
		 * Get the value of the data column for this Uri. This is useful for
		 * MediaStore Uris, and other file-based ContentProviders.
		 * 
		 * @param context
		 *            The context.
		 * @param uri
		 *            The Uri to query.
		 * @param selection
		 *            (Optional) Filter used in the query.
		 * @param selectionArgs
		 *            (Optional) Selection arguments used in the query.
		 * @return The value of the _data column, which is typically a file
		 *         path.
		 */
		public static String getDataColumn(Context context, Uri uri,
				String selection, String[] selectionArgs) {

			Cursor cursor = null;
			final String column = "_data";
			final String[] projection = { column };

			try {
				cursor = context.getContentResolver().query(uri, projection,
						selection, selectionArgs, null);
				if (cursor != null && cursor.moveToFirst()) {
					final int column_index = cursor
							.getColumnIndexOrThrow(column);
					return cursor.getString(column_index);
				}
			} finally {
				if (cursor != null)
					cursor.close();
			}
			return null;
		}

		/**
		 * @param uri
		 *            The Uri to check.
		 * @return Whether the Uri authority is ExternalStorageProvider.
		 */
		public static boolean isExternalStorageDocument(Uri uri) {
			return "com.android.externalstorage.documents".equals(uri
					.getAuthority());
		}

		/**
		 * @param uri
		 *            The Uri to check.
		 * @return Whether the Uri authority is DownloadsProvider.
		 */
		public static boolean isDownloadsDocument(Uri uri) {
			return "com.android.providers.downloads.documents".equals(uri
					.getAuthority());
		}

		/**
		 * @param uri
		 *            The Uri to check.
		 * @return Whether the Uri authority is MediaProvider.
		 */
		public static boolean isMediaDocument(Uri uri) {
			return "com.android.providers.media.documents".equals(uri
					.getAuthority());
		}
	}

}
