package com.example.robot;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PeizhiActivity extends Activity {

	private boolean flag=false;
	private EditText ip1;
	private EditText port1;
	private EditText username1;
	private EditText password1;
	private TextView tvok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_peizhi);
		ActionBar actionbar=getActionBar();
        actionbar.hide();
        if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
		//textviews
		ip1=(EditText)findViewById(R.id.textView3);
		port1=(EditText)findViewById(R.id.textView4);
		username1=(EditText)findViewById(R.id.textView5);
		password1=(EditText)findViewById(R.id.textView6);
		tvok=(TextView)findViewById(R.id.textView7);
		tvok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.putExtra("serverIp",ip1.getText().toString());
//				intent.putExtra("serverPort", Integer.parseInt(port1.getText().toString()));
//				intent.putExtra("userName",username1.getText().toString());
//				intent.putExtra("userPwd", password1.getText().toString());
//				int MODE=Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE;
//				String NAME="videosomething";
//				SharedPreferences sp=getSharedPreferences(NAME, MODE);
//				Editor editor = sp.edit();//获取编辑器
//				editor.putString("serverIp",ip1.getText().toString());
//				editor.putInt("serverPort", 8000);
//				editor.putString("userName",username1.getText().toString());
//				editor.putString("userPwd", password1.getText().toString());
//				editor.commit();//提交修改
				finish();
			}
		});

	}
	

}
