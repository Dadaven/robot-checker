package com.example.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dao.BjDao;
import com.example.robot.XuanquActivity.MyAdapter;
import com.example.robot.XuanquActivity.ViewHolder;
import com.example.robot.entiy.Bj;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SeterFragment extends Fragment {
	private ListView lv;
	private List<Map<String, Object>> mData;
 @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	 View setLayout = inflater.inflate(R.layout.set_xml, container, false);
	 lv=(ListView)setLayout.findViewById(R.id.listView1);
	   mData = getData();
		MyAdapter adapter = new MyAdapter(getActivity());
		lv.setAdapter(adapter);
	 
    return setLayout;
}
 
 private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		BjDao bjdao=new BjDao();
		List<Bj> bjs=bjdao.getdata();
       for(int i=0;i<bjs.size();i++){
    	Bj bj=bjs.get(i);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type",bj.getBjType());
		map.put("place",bj.getBjPlace());
		map.put("time", bj.getBjTime());
		list.add(map);
       }
		return list;
	}
 public final class ViewHolder {
		public 	TextView type;
		public TextView place;
		public TextView time;
	}
 public class MyAdapter extends BaseAdapter {
	
		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int arg0, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();

				convertView = mInflater.inflate(R.layout.onejingbao,
						null);
				holder.type = (TextView) convertView.findViewById(R.id.tvtype);
				holder.place = (TextView) convertView.findViewById(R.id.tvplace);
				holder.time = (TextView) convertView.findViewById(R.id.tvtime);
				Map<String, Object> map =mData.get(arg0);
				holder.type.setText(map.get("type").toString());
				holder.place.setText(map.get("place").toString());
				holder.time.setText(map.get("time").toString());
				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();
			}

			return convertView;
		}
 }
}
