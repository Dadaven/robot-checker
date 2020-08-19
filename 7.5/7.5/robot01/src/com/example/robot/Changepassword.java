package com.example.robot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Changepassword extends Activity {
	TextView user;
	TextView pass1;
	TextView pass2;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);
				user=(TextView)findViewById(R.id.user);
				pass1=(TextView)findViewById(R.id.pass1);
				pass2=(TextView)findViewById(R.id.pass2);
			    button=(Button)findViewById(R.id.button1);
			    button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String username=user.getText().toString();
						String pass=pass1.getText().toString();
						String passs=pass2.getText().toString();
						String a="admin";
						if(username.equals(a))
						{
							if(pass.equals(passs))
							{
								Toast.makeText(Changepassword.this,"密码修改成功!您的新密码为"+pass, Toast.LENGTH_LONG).show();
								//
								int MODE=Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE;
								String NAME="something";
								SharedPreferences shared=getSharedPreferences(NAME, MODE);
								SharedPreferences.Editor editor=shared.edit();
								editor.putString("password", pass);
								editor.commit();
					            Intent in=new Intent(Changepassword.this,LoginActivity.class);
								
								startActivity(in);	
							}else
							{
								Toast.makeText(Changepassword.this,"两次密码输入不一致", Toast.LENGTH_LONG).show();
							}
							
							
						}else
						{
							Toast.makeText(Changepassword.this,"用户名错误,无法找回密码", Toast.LENGTH_LONG).show();
						}
						
						
					}
				});
				
				
			}

	}








