package com.example.robot;


import java.io.File;
import java.util.Date;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.android.market.api.model.Market.GetImageResponse;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.NET_DVR_JPEGPARA;

public class VideoFragment extends Fragment {
	private SoundPool soundPool;
    
	private Vibrator vibrator;

	private ImageView imgsetvideo;
	private ButtonListener btnListener;

	private SjrsSurfaceView mSurface;
	public static SjrsSurfaceView newSjrsSurfaceView;
	public static MonitorCameraInfo cameraInfo;
	View view;
	TextView tv1;
	TextView tv2;
	LinearLayout show;
	Boolean flag=false;
	public boolean alertFlag = false;
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	    view = inflater.inflate(R.layout.video_xml, container, false);
	    imgsetvideo=(ImageView)view.findViewById(R.id.imgsetvideo);
	    show=(LinearLayout)view.findViewById(R.id.showsetvideo);
	    tv1=(TextView)view.findViewById(R.id.textView1);
	    tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),PeizhiActivity.class);
				startActivity(intent);
				show.setVisibility(view.GONE);
			    flag=true;
			}
		});
	    tv2=(TextView)view.findViewById(R.id.textView2);
	    tv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),XuanquActivity.class);
				startActivity(intent);
				show.setVisibility(view.GONE);
			    flag=true;
			}
		});
	    show.setVisibility(view.GONE);
	    imgsetvideo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(flag){
				show.setVisibility(view.VISIBLE);
				flag=false;
				}
				else{
					show.setVisibility(view.GONE);
				    flag=true;
				}
			}
		});
	    findView();
		setListener();
		init();
		return view;
}
   private void findView() {
		// TODO Auto-generated method stub
		newSjrsSurfaceView = (SjrsSurfaceView)view.findViewById(R.id.video);

		
		btnListener = new ButtonListener();

	}
   private void setListener() {
		// TODO Auto-generated method stub
		

	}
   private void init() {
		// TODO Auto-generated method stub
		mSurface = new SjrsSurfaceView(getActivity());

	}
   @Override
	public void onResume() {
		super.onResume();

		if (!newSjrsSurfaceView.playFlag) {
			// 监控点信息类
			cameraInfo = new MonitorCameraInfo();

			SharedPreferences sp = PreferenceManager
					.getDefaultSharedPreferences(getActivity().getApplication());

			cameraInfo.serverip = sp.getString("serverIp", "192.168.1.99");
			cameraInfo.serverport =sp.getInt("serverPort",8000);
			cameraInfo.username = sp.getString("userName", "admin");
			cameraInfo.userpwd = sp.getString("userPwd", "admin123456");

			Intent intent =getActivity().getIntent();

			cameraInfo.channel = 1;

			Log.d("###########", cameraInfo.serverip);
			Log.d("############", cameraInfo.username);
			Log.d("#############", cameraInfo.userpwd);
			Log.i("#####", "port=" + cameraInfo.serverport);
			Log.i("#####", "channel=" + cameraInfo.channel);

			cameraInfo.describe = "监测点";

			newSjrsSurfaceView.setMonitorInfo(cameraInfo);

			newSjrsSurfaceView.login();

			newSjrsSurfaceView.startPlay();
		}
	}

	/**
	 * 暂停
	 */
	@Override
	public void onPause() {
		super.onPause();
		if (newSjrsSurfaceView.playFlag) {
			newSjrsSurfaceView.stopPlay();
		}
	}

	public void getJPEGPhoto() {
		NET_DVR_JPEGPARA jpeg = new NET_DVR_JPEGPARA();

		INT_PTR a = new INT_PTR();// ?
		System.out.println("返回长度：" + a);
		/* a.iValue = 1024; */

		byte[] num = new byte[1024 * 1024];

		jpeg.wPicSize = 2;

		jpeg.wPicQuality = 2;

		String picName = new Date().toString() + ".jpeg";

		File file = new File(cameraInfo.filepath);
		if (!file.exists())
			file.mkdirs();
		String path = file + "/" + picName;
		/** 1.userId 返回值 2.通道号 3.图像参数 4.路劲 */
		boolean is = mSurface.SjrsSurface().NET_DVR_CaptureJPEGPicture(
				cameraInfo.userId, cameraInfo.channel, jpeg, path);

		// 吐司 截图是否成功
		if (is == true) {
			Toast.makeText(getActivity(),
					"截图成功，保存路径为：内存/HK_DaChen_Picture", 1).show();
		} else {
			Toast.makeText(getActivity(), "截图失败", 1).show();
		}
		

	}
	public void startSaveVideo() {
		String videoName = new Date().toString() + ".mp4";

		File file = new File(cameraInfo.datapath);
		if (!file.exists())
			file.mkdirs();

		String saveDataPath = file + "/" + videoName;

		boolean save = mSurface.SjrsSurface().NET_DVR_SaveRealData(
				cameraInfo.playNum, saveDataPath);
		if (save == true) {
			Toast.makeText(getActivity(), "开始录像", 1).show();
		} else
			Toast.makeText(getActivity(), "开始录像失败", 1).show();

	}

	public void stopSaveData() {

		boolean stop = mSurface.SjrsSurface().NET_DVR_StopSaveRealData(
				cameraInfo.playNum);
		if (stop == true) {
			Toast.makeText(getActivity(),
					"停止录像：文件存储在内存/HK_Dachen_video", 1).show();
		} else
			Toast.makeText(getActivity(), "停止录像失败", 1).show();
	}

	public void startAlert() {

		soundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 2);
		soundPool.load(getActivity(), R.raw.alert, 1);
		soundPool
				.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

					@Override
					public void onLoadComplete(SoundPool soundPool, int arg1,
							int arg2) {
						soundPool.play(1, 1, 1, 0, -1, 1);
					}
				});

		vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 800, 50, 400, 30 };
		vibrator.vibrate(pattern, 2);

	}

	public void stopAlert() {

		soundPool.stop(1);

		vibrator.cancel();

	}
	
	 public void nocmv(){
		   int h=newSjrsSurfaceView.getHeight();
		   newSjrsSurfaceView.setTop(h-1);
	   }
	   public void showcmv(){
		   newSjrsSurfaceView.setTop(0);
	   }

	public class ButtonListener implements OnTouchListener, OnClickListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (v.getId()) {
			}
			return false;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			}
				
			}
		}
	}

