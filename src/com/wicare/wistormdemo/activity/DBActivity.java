package com.wicare.wistormdemo.activity;

import java.util.List;

import com.wicare.wistorm.toolkit.WSql;
import com.wicare.wistormdemo.R;
import com.wicare.wistormdemo.entity.CarInfo;
import com.wicare.wistormdemo.model.DBCarInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * DBActivity
 * 
 * @author c
 * @date 2015-11-10
 */
public class DBActivity extends Activity implements OnClickListener {
	private DBCarInfo dbCarInfo;
	private int lastId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db);
		dbCarInfo = new DBCarInfo(this);
	}

	/*
	 * @param arg0
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnAdd:
			CarInfo carInfo = new CarInfo();

			carInfo.setId(0);
			carInfo.setLat("22.994950");
			carInfo.setLon("101.32434");
			dbCarInfo.insert(carInfo);
			break;
		case R.id.btnDelete:
			CarInfo delete = new CarInfo();
			delete.setId(lastId--);
			dbCarInfo.delete(delete);
			break;
		case R.id.btnUpdate:
			dbCarInfo.deleteAll();
			break;
		case R.id.btnQuery:
			String str = "";
			List<CarInfo> list = dbCarInfo.queryAll();
			for (CarInfo c : list) {
				str += "id:" + c.getId() + "lat: " + c.getLat() + "lon: "
						+ c.getLon() + "\n";
				lastId =  c.getId() ;
			}

			TextView tv = (TextView) findViewById(R.id.tvInfo);
			tv.setText(str);
			break;
		}

	}

}
