package com.example.modle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.robot.VideoFragment;
import com.example.robot.entiy.Messages;
import com.example.robot.entiy.RobotUser;

public class RobtClient implements Runnable{
	

	private static Socket client;
	private Context context;

	private static RobtClient instance = null;

    public void changecontext(Context con){
    	this.context=con;	
    }
	private void initMap() {
		this.handlerMap = new HashMap<String, Handler>();
	}

	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		instance = null;
	}
	
	private void startThread() {
		new Thread(new Runnable()  {
			@Override
			public void run() {

				while (true) {
					if (client == null || !client.isConnected()) {
						continue;
					}

					BufferedReader reader;
					try {
						reader = new BufferedReader(new InputStreamReader(
								client.getInputStream()));
						String line = reader.readLine();
						Log.d("initSocket", "line:" + line);
						//VideoFragment.tex_x.setText(line);
						if (line.equals("")) {
							continue;
						}
                        
						JSONObject json = new JSONObject(line);
						String method = json.getString("Method");
						Log.d("initSocket", "method:" + method);
						if (method.equals("")
								|| !handlerMap.containsKey(method)) {
							Log.d("initSocket", "handlerMap not method");
							continue;
						}
						Log.d("initSocket", "通知:执行到了" );
						Handler handler = handlerMap.get(method);
						if (handler == null) {
							Log.d("initSocket", "handler is null");
							continue;
						}
						Log.d("initSocket", "handler:" + method);
						Object obj = json.getJSONObject("Result");
						Messages rmessage=Messages.getInstance();
						rmessage.setCo(obj.toString());
						Log.d("initSocket", "Result:" + obj);
						Message msg = new Message();
						msg.obj = obj;
						handler.sendMessage(msg);

					} catch (IOException e) {

					} catch (JSONException e) {

					}
				}
			}
		}).start();
		
	}

	private static Map<String, Handler> handlerMap;

	public void putHandler(String methodnName, Handler handler) {
		Log.d("测试", "刚进入方法");
		Log.d("测试", handlerMap.size()+"个数据");
		this.removeHandler(methodnName);
//		if(handler!=null)
//		{
//		Log.d("测试", "为空");
        this.handlerMap.put(methodnName, handler);
//		}
		
	}

	public void removeHandler(String methodnName) {
		if (this.handlerMap.containsKey(methodnName)) {
			Log.d("测试", "包含了数据 ");
			this.handlerMap.remove(methodnName);
		}
	}

	public void logon(String userName) {
		Log.d("initSocket", "logon");
		try {
			OutputStreamWriter osw = new OutputStreamWriter(client
					.getOutputStream());

			BufferedWriter writer = new BufferedWriter(osw);

			JSONObject param = new JSONObject();
			param.put("UserName", userName.replace("\n", " "));

			JSONObject json = this.getJSONData("Logon", param);

			writer.write(json.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		Log.d("initSocket", "Send");
		try {
			OutputStreamWriter osw = new OutputStreamWriter(client
					.getOutputStream());

			BufferedWriter writer = new BufferedWriter(osw);

			JSONArray array = new JSONArray();
			for (String item : message.split("\n")) {
				array.put(item);
			}

			JSONObject param = new JSONObject();
			param.put("Message", array);

			param.put("UserName", RobotUser.getInstance().getUsername());

			JSONObject json = this.getJSONData("Send", param);

			writer.write(json.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static RobtClient getInstance() {
		if (instance == null) {

			synchronized (RobotUser.class) {
				if (instance == null) {
					try {
						RobotUser chartInfo = RobotUser.getInstance();
						client = new Socket(chartInfo.getIp(), chartInfo
								.getNum_port());
						instance = new RobtClient();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
				}
			}
		}
		return instance;
	}

	private JSONObject getJSONData(String methodName, JSONObject param) {
		JSONObject json = new JSONObject();
		try {
			json.put("Method", methodName);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			json.put("DateTime", format.format(new Date(0)));
			json.put("Param", param);
			return json;
		} catch (JSONException e) {
			return null;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//新的线程
		this.initMap();
		this.startThread();
		if (instance == null) {
			
						synchronized (RobotUser.class) {
							if (instance == null) {
								try {
									RobotUser robotInfo = RobotUser.getInstance();
									client = new Socket(robotInfo.getIp(), robotInfo
											.getNum_port());
									instance = new RobtClient();
								} catch (UnknownHostException e) {
									// TODO Auto-generated catch block
								} catch (IOException e) {
									// TODO Auto-generated catch block
								}
							}
						}
					}
		if (instance == null) {
			return;
		}
		
		RobotUser robotInfo = RobotUser.getInstance();
		instance.logon(robotInfo.getUsername());
		
	}

}
