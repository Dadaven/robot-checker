package com.example.robot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class Show_Activity extends Activity {

	boolean isFirstIn = false;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    // �ӳ�2��
    private static final long SPLASH_DELAY_MILLIS = 2000;

    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    /**
     * Handler:��ת����ͬ����
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case GO_HOME:
                goHome();
                break;
            case GO_GUIDE:
                goGuide();
                break;
            }
            super.handleMessage(msg);
        }
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_);
        init();
		
	}
	 private void init() {
	        // ��ȡSharedPreferences����Ҫ������
	        // ʹ��SharedPreferences����¼�����ʹ�ô���
	        SharedPreferences preferences = getSharedPreferences(
	                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

	        // ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬��true��ΪĬ��ֵ
	        isFirstIn = preferences.getBoolean("isFirstIn", true);

	        // �жϳ�����ڼ������У�����ǵ�һ����������ת���������棬������ת��������
	        if (!isFirstIn) {
	            // ʹ��Handler��postDelayed������3���ִ����ת��MainActivity
	            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
	        } else {
	            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
	        }

	    }
	 private void goHome() {
	        Intent intent = new Intent(Show_Activity.this, LoginActivity.class);
	        Show_Activity.this.startActivity(intent);
	        Show_Activity.this.finish();
	    }
	 private void goGuide() {
	        Intent intent = new Intent(Show_Activity.this, GuideActivity.class);
	        Show_Activity.this.startActivity(intent);
	        Show_Activity.this.finish();
	    }
	
	

}
