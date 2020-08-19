package com.example.robot;

import android.os.Environment;


public class MonitorCameraInfo {
	public String serverip = "";
	public int serverport = 0;
	public String username = "";
	public String userpwd = "";
	public int channel = 0;
	public String describe = "";

	public int userId = 0;
	
	public int playNum = 0;
	
	public String filepath = getSDRootPath() + "/HK_DaChen_Picture";
	
	public String datapath = getSDRootPath() + "/HK_Dachen_video";

	public MonitorCameraInfo() {
	}

	
	public static String getSDRootPath() {
		String sdDir = null;
		// еп╤о
		boolean sdCardExist = hasSdcard();
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
			return sdDir;
		} else {
			return null;
		}
	}

	
	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

}
