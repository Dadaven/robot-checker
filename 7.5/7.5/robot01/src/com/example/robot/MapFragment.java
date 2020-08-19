package com.example.robot;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.modle.RobtClient;
import com.example.robot.entiy.Bj;
import com.example.robot.entiy.Messages;
import com.example.dao.*;

public class MapFragment extends Fragment {
	 
	
	
	LocationClient mLocClient;
    double x,y;
    String wendu;
    String shidu;
    String CO;
    String speed;
    String press;
    boolean isFirstLoc = true; // �Ƿ��״ζ�λ
    private LatLng point;
    private Dialog dialog;
	MapView mMapView = null;
	boolean flag=false;
	private TextView dialog_text;
	public MyLocationListenner myListener = new MyLocationListenner();
	BaiduMap mBaiduMap = null;// mMapView.getMap();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		 SDKInitializer.initialize(getActivity().getApplicationContext());
		  
		  View view = inflater.inflate(R.layout.map_xml, container, false);
	      
		
		  initHandler();
		  mMapView = (MapView)view.findViewById(R.id.bmapView);  
		  
		  mBaiduMap = mMapView.getMap(); 
		  mBaiduMap.setMyLocationEnabled(true);
          // ��λ��ʼ��
          mLocClient = new LocationClient(getActivity());
          mLocClient.registerLocationListener(myListener);
          LocationClientOption option = new LocationClientOption();
          option.setOpenGps(true); // ��gps
          option.setCoorType("bd09ll"); // ������������
          option.setScanSpan(1000);
          mLocClient.setLocOption(option);
          mLocClient.start();
		   
		  //��ͨ��ͼ  
//		  mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
          mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
        	  InfoWindow mInfoWindow;
  			@Override
  			public boolean onMarkerClick(Marker arg0) {
  				// TODO Auto-generated method stub
  				
  		        
  				if(flag==false){
  					flag=true;
  					TextView tv_x;
  	  				TextView tv_y;
  	  				TextView tv_tem;
  	  				TextView tv_hum;
  	  				TextView tv_co;
  	  				TextView tv_speed;
  	  				TextView tv_press;
  	  				
  	  			View viewCache = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.pop_layout, null);
  	  			tv_x=((TextView)viewCache.findViewById(R.id.textView2));
  	  	      tv_y=((TextView)viewCache.findViewById(R.id.textView4));
  	  	      tv_tem=((TextView)viewCache.findViewById(R.id.textView6));
  	  	      tv_hum=((TextView)viewCache.findViewById(R.id.textView8));
  	  	      tv_co=((TextView)viewCache.findViewById(R.id.textView10));
  	  	      tv_speed=((TextView)viewCache.findViewById(R.id.textView12));
  	  	      tv_press=((TextView)viewCache.findViewById(R.id.textView14));
  				tv_x.setText(String.valueOf(x));
  				tv_y.setText(String.valueOf(y));
  				tv_tem.setText(wendu+"��C");
  				tv_hum.setText(shidu+"%RH");
  				tv_co.setText(CO);
  				tv_speed.setText(speed);
  				tv_press.setText(press);
  	  			LatLng pt = new LatLng(x, y);  
  	  				//����InfoWindow , ���� view�� �������꣬ y ��ƫ���� 
  	  				 mInfoWindow = new InfoWindow(viewCache, pt, -47);  
  	  				//��ʾInfoWindow  
  	  				mBaiduMap.showInfoWindow(mInfoWindow);
  				}
  				else{
  				mBaiduMap.hideInfoWindow();
  					flag=false;
  				}
  				return true;
  			}
  		}); 
	     return view;
		
	}
	 public void updatemap(Messages messages){
		 
			x =messages.getX();
			y =messages.getY();
			wendu=messages.getWendu();
			shidu=messages.getShidu();
			CO=messages.getCo();
			speed=messages.getSped();
			press=messages.getPress();
			Toast.makeText(getActivity(), ""+x+y, Toast.LENGTH_LONG).show();

			point = new LatLng(x,y);
			BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
			OverlayOptions options = new MarkerOptions().icon(icon).position(point);
			mBaiduMap.addOverlay(options);
			
			
			//�趨���ĵ����� 
			//LatLng cenpt = new LatLng(30.663791,104.07281); 
			//�����ͼ״̬
			MapStatus mMapStatus = new MapStatus.Builder()
			.target(point)
			.zoom(19)
			.build();
			//����MapStatusUpdate�����Ա�������ͼ״̬��Ҫ�����ı仯

			MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
			//�ı��ͼ״̬
			mBaiduMap.setMapStatus(mMapStatusUpdate);
			mBaiduMap = mMapView.getMap();  
		}
	 private void initHandler() {

			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.obj == null) {
						Log.d("initSocket", "handleMessage is null");
						return;
					}

					Log.d("initSocket", "handleMessage");
					try {
						JSONObject json = (JSONObject) msg.obj;
						String userName = json.getString("UserName");

						StringBuilder sb = new StringBuilder();
						int length = json.getJSONArray("Message").length();
							String item = json.getJSONArray("Message").getString(length-1);
							Messages rmessage=Messages.getInstance();
							int[] jb=rmessage.setdate(item);
							if(jb!=null){
								if(jb[0]==1){
									//����
									Bj bj=new Bj("�� ��", rmessage.getWendu(),gettime());
									showVoiceDialog(1);
									BjDao bjdao=new BjDao();
									bjdao.addblog(bj);
								}
								if(jb[1]==1){
									//��Ũ��
									showVoiceDialog(2);
									Bj bj=new Bj("��Ũ��", rmessage.getWendu(),gettime());
									BjDao bjdao=new BjDao();
									bjdao.addblog(bj);
								}
								if(jb[2]==1){
									//�͵�ѹ
									showVoiceDialog(3);
									Bj bj=new Bj("�͵�ѹ", rmessage.getWendu(),gettime());
									BjDao bjdao=new BjDao();
									bjdao.addblog(bj);
								}
							}
							if(flag==false){
								updatemap(rmessage);
							}
							else{
								
							}
						}
					 catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			RobtClient proxy = RobtClient.getInstance();
			
			proxy.putHandler("Send", handler);
		}
	 
	 
	 String gettime(){
		 Time t=new Time(); // or Time t=new Time("GMT+8"); ����Time Zone���ϡ�  
		 t.setToNow(); // ȡ��ϵͳʱ�䡣  
		 int year = t.year;  
		 int month = t.month;  
		 int date = t.monthDay;  
		 int hour = t.hour; // 0-23  
		 int minute = t.minute;  
		 int second = t.second;  
		 return year+"."+month+"."+date+" "+hour+":"+minute+":"+second;
	 }
	 
		//¼��ʱ��ʾDialog
		void showVoiceDialog(int i){
			dialog = new Dialog(getActivity(),R.style.DialogStyle);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			dialog.setContentView(R.layout.my_dialog);
			dialog_text=(TextView)dialog.findViewById(R.id.textView2);
			switch(i){
			case 1:{
				dialog_text.setText("��  ��");
				//������ݿ�
			}
			break;
			case 2:{
				dialog_text.setText("��Ũ��");
			}
			break;
			case 3:{
				dialog_text.setText("��  ѹ");
			}
			break;
			}
			dialog.show();
		}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	 public class MyLocationListenner implements BDLocationListener {

	        @Override
	        public void onReceiveLocation(BDLocation location) {
	            // map view ���ٺ��ڴ����½��յ�λ��
	            if (location == null || mMapView == null) {
	                return;
	            }
	            MyLocationData locData = new MyLocationData.Builder()
	                    .accuracy(location.getRadius())
	                            // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
	                    .direction(100).latitude(location.getLatitude())
	                    .longitude(location.getLongitude()).build();
	            mBaiduMap.setMyLocationData(locData);
	            if (isFirstLoc) {
	                isFirstLoc = false;
	                LatLng ll = new LatLng(location.getLatitude(),
	                        location.getLongitude());
	                MapStatus.Builder builder = new MapStatus.Builder();
	                builder.target(ll).zoom(18.0f);
	                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
	            }
	        }
	        public void onReceivePoi(BDLocation poiLocation) {
	        }
	 }
}
