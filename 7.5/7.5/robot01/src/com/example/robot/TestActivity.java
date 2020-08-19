package com.example.robot;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.modle.MessageAdapter;
import com.example.modle.MessageRecord;
import com.example.modle.RobtClient;
import com.example.robot.entiy.Messages;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class TestActivity extends Activity {
	public static TextView tex_x;
	TextView tex_y;
	TextView tex_wendu;
	TextView tex_shidu;
	TextView tex_co;
	EditText ed;
	Button but;
	private ListView ltvMessage;
	private MessageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		initview();
		   initHandler();
		
	}
	  private void initview(){
		   tex_x=(TextView)findViewById(R.id.textView1);
		   tex_y=(TextView)findViewById(R.id.textView2);
		   tex_wendu=(TextView)findViewById(R.id.textView3);
		   tex_shidu=(TextView)findViewById(R.id.textView4);
		   tex_co=(TextView)findViewById(R.id.textView5);
		   tex_co.setText(Messages.getInstance().getCo());
		   ed=(EditText)findViewById(R.id.editText1);
		   but=(Button)findViewById(R.id.button1);
		   ltvMessage = (ListView)findViewById(R.id.listView1);
		   ltvMessage.setEnabled(false);
		  ltvMessage.setAdapter(this.adapter);
		   but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str=ed.getText().toString();
				RobtClient proxy = RobtClient.getInstance();
				proxy.sendMessage(str);
				ed.setText("");
			}
		});	   
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
							tex_co.setText(item);
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

}


