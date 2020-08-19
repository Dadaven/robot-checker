package com.example.robot;

import org.MediaPlayer.PlayM4.Player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.hikvision.netsdk.ExceptionCallBack;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_CLIENTINFO;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.RealPlayCallBack;

public class SjrsSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {

	private HCNetSDK videoCtr;
	private Player myPlayer = null;
	public int playPort = -1;
	public boolean playFlag = false;
	public MonitorCameraInfo cameraInfo = null;

	private SurfaceHolder holder = null;

	public SjrsSurfaceView(Context paramContext) {
		super(paramContext);
		initSurfaceView();

	}

	public SjrsSurfaceView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		initSurfaceView();
	}

	public SjrsSurfaceView(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet);
		initSurfaceView();
	}

	public void initSurfaceView() {
		getHolder().addCallback(this);
	}

	public HCNetSDK SjrsSurface() {

		if (videoCtr == null) {
			videoCtr = new HCNetSDK();
		}

		return videoCtr;
	}

	public boolean onDown(MotionEvent paramMotionEvent) {
		return false;
	}

	public boolean onFling(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
		return false;
	}

	public void onLongPress(MotionEvent paramMotionEvent) {
	}

	public boolean onScroll(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
		return false;
	}

	public void onShowPress(MotionEvent paramMotionEvent) {
	}

	public boolean onSingleTapUp(MotionEvent paramMotionEvent) {
		return false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1,
			int paramInt2, int paramInt3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
		holder = this.getHolder();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
	}

	public void setMonitorInfo(MonitorCameraInfo setMonitorInfo) {
		this.cameraInfo = setMonitorInfo;
	}

	public void pausePaly(int flag) {
		myPlayer.pause(playPort, flag);
	}

	/**
	 * 停止播放&释放资源
	 */
	public void stopPlay() {
		try {
			playFlag = false;
			videoCtr.NET_DVR_StopRealPlay(playPort);
			videoCtr.NET_DVR_Logout_V30(cameraInfo.userId);
			cameraInfo.userId = -1;
			videoCtr.NET_DVR_Cleanup();

			if (myPlayer != null) {
				myPlayer.stop(playPort);
				myPlayer.closeStream(playPort);
				myPlayer.freePort(playPort);
				playPort = -1;

				destroyDrawingCache();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void login() {

		myPlayer = Player.getInstance();
		SjrsSurface();
     //初始化库
		videoCtr.NET_DVR_Init();

		videoCtr.NET_DVR_SetExceptionCallBack(mExceptionCallBack);

		videoCtr.NET_DVR_SetConnectTime(60000);

		playPort = myPlayer.getPort();
		NET_DVR_DEVICEINFO_V30 deviceInfo = new NET_DVR_DEVICEINFO_V30();
       //返回用户id，以便后面的预览
		cameraInfo.userId = videoCtr.NET_DVR_Login_V30(cameraInfo.serverip,
				cameraInfo.serverport, cameraInfo.username, cameraInfo.userpwd,
				deviceInfo);

		System.out.println("下面是设备信息************************");
		System.out.println("userId=" + cameraInfo.userId);
		System.out.println("通道开始=" + deviceInfo.byStartChan);
		System.out.println("通道个数=" + deviceInfo.byChanNum);
		System.out.println("设备类型=" + deviceInfo.byDVRType);
		System.out.println("ip通道个数=" + deviceInfo.byIPChanNum);
		System.out.println("通道号" + cameraInfo.channel);
		System.out.println("图片保存路径" + cameraInfo.filepath);
       //通道数组
		byte[] sbbyte = deviceInfo.sSerialNumber;
		String sNo = "";
		for (int i = 0; i < sbbyte.length; i++) {
			sNo += String.valueOf(sbbyte[i]);
		}

		System.out.println("设备序列号=" + sNo);
		System.out.println("************************");

	}

	public void startPlay() {
		try {

			NET_DVR_CLIENTINFO clientInfo = new NET_DVR_CLIENTINFO();

			clientInfo.lChannel = cameraInfo.channel;

			clientInfo.lLinkMode = 0x80000000;

			clientInfo.sMultiCastIP = null;

			cameraInfo.playNum = videoCtr.NET_DVR_RealPlay_V30(
					cameraInfo.userId, clientInfo, mRealPlayCallBack, false);
			System.out.println("playFlags=" + cameraInfo.playNum);
			System.out.println("GetLastError="
					+ videoCtr.NET_DVR_GetLastError());

		} catch (Exception e) {
			e.printStackTrace();

			stopPlay();
		}
	}

	private ExceptionCallBack mExceptionCallBack = new ExceptionCallBack() {

		@Override
		public void fExceptionCallBack(int arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			System.out.println("异常回掉函数运行！");
		}
	};

	private RealPlayCallBack mRealPlayCallBack = new RealPlayCallBack() {
		@Override
		public void fRealDataCallBack(int lRealHandle, int dataType,
				byte[] paramArrayOfByte, int byteLen) {

			if (playPort == -1)
				return;

			switch (dataType) {
			case 1:

				if (myPlayer.openStream(playPort, paramArrayOfByte, byteLen,
						1024 * 1024)) {

					if (myPlayer.setStreamOpenMode(playPort, 1)) {

						if (myPlayer.play(playPort, holder)) {
							playFlag = true;
						} else {
							playError(3);
						}
					} else {
						playError(2);
					}
				} else {
					playError(1);
				}
				break;
			case 4:
				if (playFlag
						&& myPlayer.inputData(playPort, paramArrayOfByte,
								byteLen)) {
					playFlag = true;
				} else {
					playError(4);
					playFlag = false;
				}
			}
		}
	};

	private void playError(int step) {

		switch (step) {
		case 1:
			System.out.println("openStream error,step=" + step);
			break;
		case 2:
			System.out.println("setStreamOpenMode error,step=" + step);
			break;
		case 3:
			System.out.println("play error,step=" + step);
			break;
		case 4:
			System.out.println("inputData error,step=" + step);
			break;
		}
		stopPlay();
	}
}