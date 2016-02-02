package com.wicare.wistormdemo.activity;

import com.wicare.wistorm.ui.WFragmentCards;
import com.wicare.wistormdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * 卡片管理 CardManageActivity
 * 
 * @author c
 * @date 2015-12-2
 */
public class CardManageActivity extends FragmentActivity implements
		OnClickListener {

	private WFragmentCards cardsFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cards);

		cardsFragment = new WFragmentCards();

		
		this.getSupportFragmentManager().beginTransaction()
				.add(R.id.llytContent, cardsFragment, "").commit();
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
			Fragment fragmentChild = new FragmentCard();
			cardsFragment.addCard(fragmentChild, "test1");
			fragmentChild = new FragmentCard();
			cardsFragment.addCard(fragmentChild, "test2");
			fragmentChild = new FragmentCard();
			cardsFragment.addCard(fragmentChild, "test3");
			break;
		case R.id.btnDelete:
			cardsFragment.removeAll();
			break;
		case R.id.btnTop:
			cardsFragment.top("test3");
			break;
		}
	}

}
