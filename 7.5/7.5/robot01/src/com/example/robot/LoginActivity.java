package com.example.robot;






import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modle.RobtClient;
import com.example.robot.entiy.RobotUser;


public class LoginActivity extends Activity {
Button login;
TextView username;
TextView password;
EditText ed;
ImageButton eye;
public static RobtClient robotclient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		login=(Button)findViewById(R.id.login);
		
		username=(TextView)findViewById(R.id.ip);
		password=(TextView)findViewById(R.id.ports);
		ed=(EditText)findViewById(R.id.editText1);
		readsomething();
		//readvideoshard();
		
	}
	public void onclick(View v){
		String username1=username.getText().toString();
		String password1=password.getText().toString();
		
		String ip=ed.getText().toString();
		RobotUser robotuser = RobotUser.getInstance();
		robotuser.setIp(ip);
		String a=robotuser.getUsername();
		String b=robotuser.getPassword();
		
		
//		if(username1.equals(a) && password1.equals(b))
//		{
			RobtClient proxy =new RobtClient();
			Thread thread=new Thread(proxy);
			thread.start();
			Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_LONG).show();
			Intent in=new Intent(LoginActivity.this,Myhome.class);
			startActivity(in);
//		}else
//		{
//			Toast.makeText(LoginActivity.this,"用户名或密码错误,请检查", Toast.LENGTH_LONG).show();
//		}
		
		
		
	}
	public void readsomething(){
		int MODE=Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE;
		String NAME="something";
		SharedPreferences shared=getSharedPreferences(NAME, MODE);
		String name=shared.getString("username","admin");
		String ip=shared.getString("ip", "192.168.107.100");
		int video_port=shared.getInt("videoport",8888);
		int num_port=shared.getInt("numport",1234);
		String password=shared.getString("password","root");
		username.setText(name);
		RobotUser robotuser = RobotUser.getInstance();
		robotuser.setUsername(name);
		robotuser.setPassword(password);
		robotuser.setIp(ip);
		robotuser.setNum_port(num_port);
		robotuser.setVideo_port(video_port);

	}
	public void readvideoshard(){
		int MODE=Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE;
		String NAME="videosomething";
		SharedPreferences sp=getSharedPreferences(NAME, MODE);
		Editor editor = sp.edit();//获取编辑器
		editor.putString("serverIp","192.168.1.99");
		editor.putInt("serverPort",Integer.parseInt("8000"));
		editor.putString("userName","admin");
		editor.putString("userPwd", "admin123456");
		editor.commit();//提交修改
	}
	private void showDialog(String mess) {
		new AlertDialog.Builder(this).setTitle("信息").setMessage(mess)
				.setNegativeButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}
   

}
