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
	//�ؼ�
	private TextView tv_updatepass;
	private TextView tv_exit;
	private TextView tv_about;

	private static final int SETTINGS_ADD_ID = 0;
	private static final int SECLECT_EQUIPMENT_ID = 1;
	private static final int EXIT_ID = 2;
	public static String name;
	/*
	 * ����Ӱ�񡢵�ͼ������Fragment
	 */
	private SeterFragment setFragment;
	private MapFragment mapFragment;
	private VideoFragment videoFragment;
	/*
	 * ����Ӱ�񡢵�ͼ������fragment�Ĳ���
	 */
	private View setLayout;
	private View mapLayout;
	private View videoLayout;
	
	/*
	 * ����Ӱ�񡢵�ͼ������tab��ͼ��
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
	 * ��Fragment���й���
	 */
	private FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		ActionBar actionbar=getActionBar();
        actionbar.hide();
        if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //͸��״̬��
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //͸��������
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
		setContentView(R.layout.myhome);
		initViews(); //��ʼ�����棬�������ĸ�tab�ļ���
		initButton();
	    fragmentManager = getFragmentManager();
		setTabSelection(1); //��һ������ʱ������0��tab
	}

	/*
     * ���ݴ����index�������ÿ�����tabҳ��
     * @param index
     * index�����Ӧ���±꣬0��Ӧ��ע��1��Ӧ�㳡��2��Ӧ�ҵ�
     */
//	 FragmentTransaction ft = getFragmentManager().beginTransaction();
//     GoodsListFragment goodsListFragment = new GoodsListFragment();
//     goodsListFragment.setArguments(bundle);
//     ft.addToBackStack(null).replace(R.id.secondview, goodsListFragment).commit();
    
	private void setTabSelection(int index) {
		// TODO Auto-generated method stub
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//�������е�fragment����ֹ�ж��������ʾ�ڽ�����
		switch(index){
		case 0:
			//�������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ
			videoImage.setImageResource(R.drawable.guanzhu);
			videoText.setTextColor(Color.RED);
			//tittle.setText("Ӱ  ��");
			//���messageFragmentΪ�գ��򴴽�һ����ӵ�������
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
			//tittle.setText("��  ͼ");
			//���contactsFragmentΪ�գ��򴴽�һ����ӵ�������
			if(mapFragment == null){
				mapFragment = new MapFragment();
				//videoFragment.getView().setVisibility(View.GONE);
				transaction.addToBackStack(null).replace(R.id.content, mapFragment).commit();
			} else {
				//���contactsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����	
				//videoFragment.getView().setVisibility(View.GONE);
				mapFragment=new MapFragment();
				transaction.addToBackStack(null).replace(R.id.content, mapFragment).commit();
			}
			break;
		case 2:
			//�������̬tabʱ���ı�ؼ���ͼƬ��������ɫ
			setImage.setImageResource(R.drawable.my);
			setText.setTextColor(Color.YELLOW);
			//tittle.setText("��  ��");
			//���newsFragmentΪ�գ��򴴽�һ����ӵ�������
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
	 * �������е�fragment
	 * @param transaction
	 *     ���ڶ�fragment���в���������
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
	 * ����֮ǰ������״̬
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
	 * ��ʼ�����棬����������tab�ļ���
	 */
	private void initViews() {
		
		leftmenu=(Leftmenu)findViewById(R.id.leftmenu);
		
		opencehua=(ImageView)findViewById(R.id.imageView2);
		opencehua.setVisibility(View.VISIBLE);
		opencehua.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//�򿪲໬
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
		menu.add(0, SETTINGS_ADD_ID, 0, "�����豸");
		menu.add(0, SECLECT_EQUIPMENT_ID, 0, "ѡȡ�豸");
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
				showDialog("��ǰ�汾�ţ�1.2.0");
			}
		});
    }
    private void showDialog(String mess) {
		new AlertDialog.Builder(this).setTitle("����").setMessage(mess)
				.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
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
