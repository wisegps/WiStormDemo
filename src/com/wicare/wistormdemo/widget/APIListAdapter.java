package com.wicare.wistormdemo.widget;

import java.util.ArrayList;
import java.util.List;

import com.wicare.wistormdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class APIListAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> listDatas = new ArrayList<String>();
	
	public APIListAdapter(Context context,String[]data){
		this.mContext = context;
		this.listDatas = getListData(data);
	}
	
	private List<String> getListData(String[]data){
		List<String> listDatas = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			listDatas.add(data[i]);
		}
		return listDatas;	
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder viewHolder;
		if(convertView == null){
			view = LayoutInflater.from(mContext).inflate(R.layout.item_api_list, null);
			viewHolder = new ViewHolder();			
			viewHolder.tv_name = (TextView)view.findViewById(R.id.tv_name);
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder =(ViewHolder)view.getTag();	
		}
		viewHolder.tv_name.setText(listDatas.get(position));
		return view;
	}
	
	class ViewHolder{
		TextView tv_name;
	}
	
	

}
