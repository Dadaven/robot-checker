package com.example.robot;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.preference.EditTextPreference;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Myhome extends Activity {
	//控件
	private TextView tv_updatepass;
	private TextView tv_exit;
	private TextView tv_about;

	private static final int SETTINGS_ADD_ID = 0;
	private static final int SECLECT_EQUIPMENT_ID = 1;
	private static final int EXIT_ID = 2;
	public static String name;
	/*
	 * 定义影像、地图、设置Fragment
	 */
	private SeterFragment setFragment;
	private MapFragment mapFragment;
	private VideoFragment videoFragment;
	/*
	 * 定义影像、地图、设置fragment的布局
	 */
	private View setLayout;
	private View mapLayout;
	private View videoLayout;
	
	/*
	 * 定义影像、地图、设置tab的图标
	 */
	private ImageView setImage;
	private ImageView mapImage;
	private ImageView videoImage;
	Leftmenu leftmenu;
	/*

	 */
	private TextView setText;
	private TextView mapText;
	private TextView videoText;
	ImageView opencehua;
	
	//ImageView opencehua1;
	/*
	 * 对Fragment进行管理
	 */
	private FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		ActionBar actionbar=getActionBar();
        actionbar.hide();
        if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
		setContentView(R.layout.myhome);
		initViews(); //初始化界面，并设置四个tab的监听
		initButton();
	    fragmentManager = getFragmentManager();
		setTabSelection(1); //第一次启动时开启第0个tab
	}

	/*
     * 根据传入的index，来设置开启的tab页面
     * @param index
     * index代表对应的下标，0对应关注，1对应广场，2对应我的
     */
//	 FragmentTransaction ft = getFragmentManager().beginTransaction();
//     GoodsListFragment goodsListFragment = new GoodsListFragment();
//     goodsListFragment.setArguments(bundle);
//     ft.addToBackStack(null).replace(R.id.secondview, goodsListFragment).commit();
    
	private void setTabSelection(int index) {
		// TODO Auto-generated method stub
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//隐藏所有的fragment，防止有多个界面显示在界面上
		switch(index){
		case 0:
			//当点击消息tab时，改变控件的图片和文字颜色
			videoImage.setImageResource(R.drawable.guanzhu);
			videoText.setTextColor(Color.RED);
			//tittle.setText("影  像");
			//如果messageFragment为空，则创建一个添加到界面上
			if(videoFragment == null){
				videoFragment = new VideoFragment();
				transaction.addToBackStack(null).replace(R.id.content, videoFragment).commit();
			} else {
				transaction.addToBackStack(null).replace(R.id.content, videoFragment).commit();
			}
			break;
		case 1:
			mapImage.setImageResource(R.drawable.guangchang);
			mapText.setTextColor(Color.CYAN);
			//tittle.setText("地  图");
			//如果contactsFragment为空，则创建一个添加到界面上
			if(mapFragment == null){
				mapFragment = new MapFragment();
				//videoFragment.getView().setVisibility(View.GONE);
				transaction.addToBackStack(null).replace(R.id.content, mapFragment).commit();
			} else {
				//如果contactsFragment不为空，则直接将它显示出来	
				//videoFragment.getView().setVisibility(View.GONE);
				mapFragment=new MapFragment();
				transaction.addToBackStack(null).replace(R.id.content, mapFragment).commit();
			}
			break;
		case 2:
			//当点击动态tab时，改变控件的图片和文字颜色
			setImage.setImageResource(R.drawable.my);
			setText.setTextColor(Color.YELLOW);
			//tittle.setText("警  报");
			//如果newsFragment为空，则创建一个添加到界面上
			if(setFragment == null){
				setFragment = new SeterFragment();
				transaction.addToBackStack(null).replace(R.id.content, setFragment).commit();
			} else {
				
				transaction.addToBackStack(null).replace(R.id.content, setFragment).commit();
			}
			
			break;
			default:
				videoFragment.nocmv();
				
			break;
				
		}
		//transaction.commit();
	}


	/*
	 * 隐藏所有的fragment
	 * @param transaction
	 *     用于对fragment进行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		// TODO Auto-generated method stub
		if(setFragment != null){
			transaction.hide(setFragment);
		}
		if(videoFragment != null){
			transaction.hide(videoFragment);
		}
		if(mapFragment != null){
			transaction.hide(mapFragment);
		}	
	}
	/*
	 * 清理之前的所有状态
	 */
	private void clearSelection() {
		// TODO Auto-generated method stub
		setImage.setImageResource(R.drawable.my_select);
		setText.setTextColor(Color.parseColor("#82858b"));
		mapImage.setImageResource(R.drawable.guangchang_select);
		mapText.setTextColor(Color.parseColor("#82858b"));
		videoImage.setImageResource(R.drawable.guanzhu_select);
		videoText.setTextColor(Color.parseColor("#82858b"));
	}
	/*
	 * 初始化界面，并设置三个tab的监听
	 */
	private void initViews() {
		
		leftmenu=(Leftmenu)findViewById(R.id.leftmenu);
		
		opencehua=(ImageView)findViewById(R.id.imageView2);
		opencehua.setVisibility(View.VISIBLE);
		opencehua.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//打开侧滑
				//Toast.makeText(MainActivity.this,"haha",Toast.LENGTH_SHORT).show();
				
				leftmenu.cehua();
			}
		});
		setLayout = findViewById(R.id.my_layout);
		setLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setTabSelection(2);
				
			}
		});
		mapLayout = findViewById(R.id.squer_layout);
		mapLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setTabSelection(1);
				
			}
		});
		videoLayout = findViewById(R.id.guanzhu_layout);
		videoLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setTabSelection(0);
				
			}
		});
		
		setImage = (ImageView) findViewById(R.id.set_image);
		mapImage = (ImageView) findViewById(R.id.map_image);
		videoImage = (ImageView) findViewById(R.id.video_image);
		
		setText = (TextView) findViewById(R.id.set_text);
		mapText = (TextView) findViewById(R.id.map_text);
		videoText = (TextView) findViewById(R.id.video_text);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, SETTINGS_ADD_ID, 0, "配置设备");
		menu.add(0, SECLECT_EQUIPMENT_ID, 0, "选取设备");
		menu.add(0, EXIT_ID, 0, "Exit");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {

		case SETTINGS_ADD_ID:
			

			break;

		case SECLECT_EQUIPMENT_ID:
			

			break;
		case EXIT_ID:
			// System.exit(0);
			finish();
			// android.os.Process.killProcess(android.os.Process.myPid());
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}

    public void initButton(){
    	tv_updatepass=(TextView)findViewById(R.id.tvpass);
	    tv_updatepass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in=new Intent(Myhome.this,Changepassword.class);
			  	startActivity(in);
			}
		});
	    tv_exit=(TextView)findViewById(R.id.tvexit);
	    tv_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in=new Intent(Myhome.this,LoginActivity.class);
			  	startActivity(in);
			}
		});
	    tv_about=(TextView)findViewById(R.id.tvabout);
	    tv_about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog("当前版本号：1.2.0");
			}
		});
    }
    private void showDialog(String mess) {
		new AlertDialog.Builder(this).setTitle("关于").setMessage(mess)
				.setNegativeButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	// TODO Auto-generated method stub
    	//super.onSaveInstanceState(outState);
    }


}
