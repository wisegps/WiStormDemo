package com.wicare.wistormdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wicare.wistormdemo.R;

/**
 * 
 * @author c 卡片模板
 * 
 */
public class FragmentCard extends Fragment implements OnClickListener {

	private ImageView iv_card_menu;// 下拉箭头
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.activity_fragment_card, container,
					false);
		} else {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		}

		iv_card_menu = (ImageView) view.findViewById(R.id.iv_card_menu);
		iv_card_menu.setOnClickListener(this);


		return view;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_card_menu:
			break;
		}

	}


}
