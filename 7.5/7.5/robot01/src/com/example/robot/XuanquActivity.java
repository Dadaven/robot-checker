package com.example.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class XuanquActivity  extends Activity {
	private Button view_btn;
	private List<Map<String, Object>> mData;
    private ListView lv;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_xuanqu);
	ActionBar actionbar=getActionBar();
    actionbar.hide();
    if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
        //͸��״̬��
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //͸��������
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
    lv=(ListView)findViewById(R.id.listView1);
	mData = getData();
	MyAdapter adapter = new MyAdapter(this);
	lv.setAdapter(adapter);
	lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			VideoFragment.cameraInfo.channel=arg2+1;
			Intent intent=new Intent(XuanquActivity.this,Myhome.class);
			intent.putExtra("channel", VideoFragment.cameraInfo.channel);
		    startActivity(intent);
		}
	});
}

private List<Map<String, Object>> getData() {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	Map<String, Object> map = new HashMap<String, Object>();
	map.put("title", "ͨ�� 1");
	map.put("info", "���Ŷ�·");
	map.put("img", R.drawable.ic_camera);
	list.add(map);

	map = new HashMap<String, Object>();
	map.put("title", "ͨ�� 2 ");
	map.put("info", "����̨����");
	map.put("img", R.drawable.ic_camera);
	list.add(map);

	map = new HashMap<String, Object>();
	map.put("title", "ͨ�� 3 ");
	map.put("info", "԰������");
	map.put("img", R.drawable.ic_camera);
	list.add(map);
	
	map = new HashMap<String, Object>();
	map.put("title", "ͨ�� 4 ");
	map.put("info", "ͣ����");
	map.put("img", R.drawable.ic_camera);
	list.add(map);
	
	map = new HashMap<String, Object>();
	map.put("title", "ͨ�� 5 ");
	map.put("info", "����");
	map.put("img", R.drawable.ic_camera);
	list.add(map);

	return list;
}



public final class ViewHolder {
	public ImageView img;
	public TextView title;
	public TextView info;
	public Button viewBtn;
	//public Button deletBtn;
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
		
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {

			holder = new ViewHolder();

			convertView = mInflater.inflate(R.layout.onexuanqu,
					null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.info = (TextView) convertView.findViewById(R.id.info);
			holder.viewBtn = (Button) convertView
					.findViewById(R.id.view_btn);
		
			
			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		holder.img.setBackgroundResource((Integer) mData.get(position).get(
				"img"));
		holder.title.setText((String) mData.get(position).get("title"));
		holder.info.setText((String) mData.get(position).get("info"));
		
		

		//final View view=convertView;
		final int p = position;
		//final int yulan = holder.viewBtn.getId();
		
		
		holder.viewBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			Log.d("#######", "position"+p);
			switch (p) {
			case 0:

				if (VideoFragment.newSjrsSurfaceView.playFlag) {
					VideoFragment.newSjrsSurfaceView.stopPlay();				
				}else{
					VideoFragment.cameraInfo.channel=1;
					Intent intent=new Intent(XuanquActivity.this,Myhome.class);
					intent.putExtra("channel", VideoFragment.cameraInfo.channel);
				    startActivity(intent);
				}
				break;
			case 1:
				Log.d("#######", "����Ԥ����ť"+p);
				if (VideoFragment.newSjrsSurfaceView.playFlag) {
					VideoFragment.newSjrsSurfaceView.stopPlay();
				
				}else{
					
					VideoFragment.cameraInfo.channel=2;
					Intent intent=new Intent(XuanquActivity.this,Myhome.class);
					intent.putExtra("channel", VideoFragment.cameraInfo.channel);
				    startActivity(intent);
		
				}
				break;
			case 2:
				Log.d("#######", "����Ԥ����ť"+p);
				if (VideoFragment.newSjrsSurfaceView.playFlag) {
					VideoFragment.newSjrsSurfaceView.stopPlay();
				
				}else{
					
					VideoFragment.cameraInfo.channel=3;
					Intent intent=new Intent(XuanquActivity.this,Myhome.class);
					intent.putExtra("channel", VideoFragment.cameraInfo.channel);
				    startActivity(intent);
		
				}
				break;
			case 3:
				Log.d("#######", "����Ԥ����ť"+p);
				if (VideoFragment.newSjrsSurfaceView.playFlag) {
					VideoFragment.newSjrsSurfaceView.stopPlay();
				
				}else{
					
					VideoFragment.cameraInfo.channel=4;
					Intent intent=new Intent(XuanquActivity.this,Myhome.class);
					intent.putExtra("channel", VideoFragment.cameraInfo.channel);
				    startActivity(intent);
		
				}
				break;
			case 4:
				Log.d("#######", "����Ԥ����ť"+p);
				if (VideoFragment.newSjrsSurfaceView.playFlag) {
					VideoFragment.newSjrsSurfaceView.stopPlay();
				
				}else{
					
					VideoFragment.cameraInfo.channel=49;
					Intent intent=new Intent(XuanquActivity.this,Myhome.class);
					intent.putExtra("channel", VideoFragment.cameraInfo.channel);
				    startActivity(intent);
		
				}
				break;
			

			default:
				break;
			}
				
			}
		});
		return convertView;
	}
}
}
