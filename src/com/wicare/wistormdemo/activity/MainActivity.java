package com.wicare.wistormdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.wicare.wistorm.ui.WBottomPopupWindow;
import com.wicare.wistorm.ui.WBottomPopupWindow.OnItemClickListener;
import com.wicare.wistorm.ui.WDateSelector;
import com.wicare.wistorm.ui.WDateSelector.OnDateChangedListener;
import com.wicare.wistorm.ui.WDialog;
import com.wicare.wistorm.ui.WDialog.Builder;
import com.wicare.wistorm.ui.WLoading;
import com.wicare.wistorm.ui.WTimeSelector;
import com.wicare.wistorm.ui.WTimeSelector.OnTimeChangedListener;
import com.wicare.wistormdemo.R;

/**
 * @author Administrator
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	final String TAG = MainActivity.this.toString();

	private Button btnDateSelect;
	private Button btnTimeSelect;
	private Button btnSearchBar;// 跳转到搜索框
	private Button btnPopupWindow;// 底部弹出 pop
	private Button btnAlertDialog;// 自定义弹框
	private Button btnLoading;// 加载动画
	private Button btnLogin;// 登陆
	private Button btnTabBar;// 导航栏
	private Button btnBaiduMap;// 百度地图
	private Button btnSwitchCity;// 城市选择
	private Button btnJpush;// 消息推送
	private Button btnSlidingMenu;// 侧滑菜单
	private Button btnDB;// 数据库操作
	private Button btnShareSdk;// 分享
	private Button btnZxing;// 二维码
	private Button btn_sms;// 短信验证码
	private Button btnInputfeild;// 输入框
	private Button btnWCloud;// 阿里云
	private Button btnWCache;// 图片缓存
	private Button btnCarSelector;// 汽车选择
	private Button btnNavigation;// 页面导航
	private Button btnProgressbar;// 进度条
	private Button btnCharts;// 统计图表
	private Button btnAboutApp;// 关于
	private Button btnAccount;// 我的帐号
	private Button btnCards;// 卡片管理
	private Button btnProView;//圆形弧形进度UI
	private Button btnListview;//上下拉刷新
	
	

	final String itemsDialog[] = { "张三", "李四", "王五" };

	WBottomPopupWindow mPoppupWindow;
	private WLoading mWLoading = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnDateSelect = (Button) findViewById(R.id.btn_date_select);
		btnDateSelect.setOnClickListener(this);

		btnTimeSelect = (Button) findViewById(R.id.btn_time_select);
		btnTimeSelect.setOnClickListener(this);

		btnSearchBar = (Button) findViewById(R.id.btn_search_bar);
		btnSearchBar.setOnClickListener(this);

		btnPopupWindow = (Button) findViewById(R.id.btn_buttom_pop);
		btnPopupWindow.setOnClickListener(this);

		btnAlertDialog = (Button) findViewById(R.id.btn_alert_dialog);
		btnAlertDialog.setOnClickListener(this);

		btnLoading = (Button) findViewById(R.id.btn_loading);
		btnLoading.setOnClickListener(this);

		btnLogin = (Button) findViewById(R.id.btn_Login);
		btnLogin.setOnClickListener(this);

		btnTabBar = (Button) findViewById(R.id.btn_tabbar);
		btnTabBar.setOnClickListener(this);

		btnBaiduMap = (Button) findViewById(R.id.btn_map);
		btnBaiduMap.setOnClickListener(this);

		btnJpush = (Button) findViewById(R.id.btn_jpush);
		btnJpush.setOnClickListener(this);

		btnSwitchCity = (Button) findViewById(R.id.btn_switch_city);
		btnSwitchCity.setOnClickListener(this);

		btnDB = (Button) findViewById(R.id.btn_db);
		btnDB.setOnClickListener(this);
		btnSlidingMenu = (Button) findViewById(R.id.btn_slidingmenu);
		btnSlidingMenu.setOnClickListener(this);

		btnShareSdk = (Button) findViewById(R.id.btn_share_activity);
		btnShareSdk.setOnClickListener(this);
		btnZxing = (Button) findViewById(R.id.btn_zxing);
		btnZxing.setOnClickListener(this);

		btn_sms = (Button) findViewById(R.id.btn_sms);
		btn_sms.setOnClickListener(this);

		btnInputfeild = (Button) findViewById(R.id.btn_inputfeild);
		btnInputfeild.setOnClickListener(this);

		btnWCloud = (Button) findViewById(R.id.btn_wcloud);
		btnWCloud.setOnClickListener(this);

		btnWCache = (Button) findViewById(R.id.btn_wcache);
		btnWCache.setOnClickListener(this);

		btnCarSelector = (Button) findViewById(R.id.btn_carSelector);
		btnCarSelector.setOnClickListener(this);

		btnNavigation = (Button) findViewById(R.id.btn_navigation);
		btnNavigation.setOnClickListener(this);

		btnProgressbar = (Button) findViewById(R.id.btn_h_progressbar);
		btnProgressbar.setOnClickListener(this);

		btnCharts = (Button) findViewById(R.id.btn_charts);
		btnCharts.setOnClickListener(this);

		btnAboutApp = (Button) findViewById(R.id.btn_about_app);
		btnAboutApp.setOnClickListener(this);

		btnAccount = (Button) findViewById(R.id.btn_my_account);
		btnAccount.setOnClickListener(this);

		btnCards = (Button) findViewById(R.id.btn_cards);
		btnCards.setOnClickListener(this);

		btnProView = (Button) findViewById(R.id.btn_progress_view);
		btnProView.setOnClickListener(this);
		
		
		btnListview = (Button) findViewById(R.id.btn_listview);
		btnListview.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_date_select:// 日期选择
			WDateSelector mDateSelector = new WDateSelector(this);

			mDateSelector.setDate();
			mDateSelector.setOnDateChangedListener(new OnDateChangedListener() {

				@Override
				public void onDateChanged(String year, String month, String day) {
					// TODO Auto-generated method stub
					btnDateSelect.setText(year + "-" + month + "-" + day);
				}
			});

			break;

		case R.id.btn_time_select:// 时间选择
			WTimeSelector mTimeSelector = new WTimeSelector(this);
			mTimeSelector.setTime();
			mTimeSelector.setOnTimeChangedListener(new OnTimeChangedListener() {

				@Override
				public void onTimeChanged(String hour, String minute) {
					// TODO Auto-generated method stub
					btnTimeSelect.setText(hour + ":" + minute);
				}
			});
			break;

		case R.id.btn_search_bar:// 搜索框
			Intent intentSearchBar = new Intent(MainActivity.this,
					SearchBarTest.class);
			startActivity(intentSearchBar);
			break;

		case R.id.btn_buttom_pop:// 底部弹出pop
			List<String> items = new ArrayList<String>();
			items.add("按键一");
			items.add("按键二");
			items.add("按键三");
			final WBottomPopupWindow popView = new WBottomPopupWindow(this);
			popView.initView(findViewById(R.id.main));
			popView.setData(items);
			popView.SetOnItemClickListener(new OnItemClickListener() {

				@Override
				public void OnItemClick(int index) {
					// TODO Auto-generated method stub
					switch (index) {
					case 0:
						popView.dismiss();
						break;

					case 1:
						popView.dismiss();
						break;
					case 2:
						popView.dismiss();
						break;
					}
				}
			});
			break;

		case R.id.btn_alert_dialog:

			WDialog.Builder builder = new Builder(MainActivity.this);
			builder.setTitle("自定义菜单");
			// builder.setMessage("00");
			builder.setItems(itemsDialog);// 列表式菜单
			builder.setPositiveButton("OK",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			builder.setNegativeButton("Cancel",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			builder.setOnClickListener(listViewOnClickListener);// 设置列表菜单点击事件监听
			builder.create().show();
			break;

		case R.id.btn_loading:
			startProgressDialog();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					stopProgressDialog();
				}
			}, 6000);
			break;

//		case R.id.btn_listview_push_refresh:
//			Intent intentListView = new Intent(MainActivity.this,
//					ListViewTest.class);
//			startActivity(intentListView);
//			break;

		case R.id.btn_Login:
			Intent intentLogin = new Intent(MainActivity.this, LoginTest.class);
			startActivity(intentLogin);
			break;

		case R.id.btn_tabbar:
			Intent intentTabBar = new Intent(MainActivity.this,
					TabBarTest.class);
			startActivity(intentTabBar);
			break;
		case R.id.btn_map:
			Intent intentMap = new Intent(MainActivity.this, MapActivity.class);
			startActivity(intentMap);
			break;

		case R.id.btn_switch_city:
			Intent intentSwitchCity = new Intent(MainActivity.this,
					SwitchCityTest.class);
			startActivity(intentSwitchCity);
			break;
		case R.id.btn_jpush:
			Intent intentJPush = new Intent(MainActivity.this,
					JpushActivity.class);
			startActivity(intentJPush);
			break;
		case R.id.btn_db:
			Intent intentDB = new Intent(MainActivity.this, DBActivity.class);
			startActivity(intentDB);
			break;

		case R.id.btn_slidingmenu:

			Intent intentSlidingmenu = new Intent(MainActivity.this,
					SlidingMenuTest.class);
			startActivity(intentSlidingmenu);
			break;

		case R.id.btn_share_activity:
			Intent intentShare = new Intent(MainActivity.this,
					ShareActivity.class);
			startActivity(intentShare);
			break;
		case R.id.btn_zxing:
			Intent intentZxing = new Intent(MainActivity.this,
					ZxingActivityTest.class);
			startActivity(intentZxing);
			break;
		case R.id.btn_sms:
			Intent intentSms = new Intent(MainActivity.this, WSMS.class);
			startActivity(intentSms);
			break;

		case R.id.btn_inputfeild:
			Intent intentInputfeild = new Intent(MainActivity.this,
					InputFeildActivity.class);
			startActivity(intentInputfeild);
			break;
		case R.id.btn_wcloud:
			Intent icloud = new Intent(MainActivity.this, WCloudActivity.class);
			startActivity(icloud);
			break;

		case R.id.btn_wcache:
			Intent icache = new Intent(MainActivity.this, WCacheActivity.class);
			startActivity(icache);
			break;

		case R.id.btn_carSelector:
			Intent intentCarSelector = new Intent(MainActivity.this,
					CarSelectorActivity.class);
			startActivity(intentCarSelector);
			break;

		case R.id.btn_navigation:
			Intent intentNavigation = new Intent(MainActivity.this,
					NavigationBarTest.class);
			startActivity(intentNavigation);
			break;

		case R.id.btn_h_progressbar:
			Intent intentProgressbar = new Intent(MainActivity.this,
					HorizontalProgressbarTest.class);
			startActivity(intentProgressbar);
			break;

		case R.id.btn_charts:
			Intent intentChart = new Intent(MainActivity.this, ChartTest.class);
			startActivity(intentChart);
			break;

		case R.id.btn_about_app:
			Intent intentAboutAPP = new Intent(MainActivity.this,
					AboutApp.class);
			startActivity(intentAboutAPP);
			break;

		case R.id.btn_my_account:
			Intent intentAccount = new Intent(MainActivity.this,
					AccountActivityTest.class);
			startActivity(intentAccount);
			break;

		case R.id.btn_cards:
			Intent intentCards = new Intent(MainActivity.this,
					CardManageActivity.class);
			startActivity(intentCards);
			break;
			
		case R.id.btn_progress_view:
			Intent intentProView = new Intent(MainActivity.this,
					ProgressViewActivity.class);
			startActivity(intentProView);
			break;
			
		case R.id.btn_listview:
			Intent intent_listview = new Intent(MainActivity.this,
					SwipeRefreshLayoutActivity.class);
			startActivity(intent_listview);
			break;

		default:
			break;
		}
	}

	/**
	 * 自定义弹框 列表菜单点击事件
	 */
	private android.widget.AdapterView.OnItemClickListener listViewOnClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(MainActivity.this, itemsDialog[position],
					Toast.LENGTH_SHORT).show();

		}
	};

	/**
	 * 开始显示加载
	 */
	private void startProgressDialog() {
		if (mWLoading == null) {
			mWLoading = WLoading.createDialog(this);
			mWLoading.setMessage("加载中...");
		}
		mWLoading.show();
	}

	/**
	 * 关闭加载提示
	 */
	private void stopProgressDialog() {
		if (mWLoading != null) {
			mWLoading.dismiss();
			mWLoading = null;
		}
	}
}
